package com.android.commands.monkey;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.net.DhcpInfo;
import android.net.IpConfiguration;
import android.net.LinkAddress;
import android.net.StaticIpConfiguration;
import android.net.wifi.IWifiManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.os.ServiceManager;

import com.android.wifi.x.com.android.modules.utils.ParceledListSlice;

/** wifi 命令实现（JSON 参数，调用 framework API） */
public class NetworkCommand {

    private static final String PKG = "com.android.shell";

    static IWifiManager getService() {
        return IWifiManager.Stub.asInterface(ServiceManager.getService("wifi"));
    }

    /** 入口：wifi <json> */
    public static MonkeySource.MonkeyCommandReturn execute(List<String> command) {
        if (command.size() < 2) {
            return new MonkeySource.MonkeyCommandReturn(false, "usage: wifi <json>");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < command.size(); i++) {
            if (i > 1) sb.append(" ");
            sb.append(command.get(i));
        }
        try {
            JSONObject json = new JSONObject(sb.toString());
            String action = json.getString("action");
            return dispatch(action, json);
        } catch (Exception e) {
            return new MonkeySource.MonkeyCommandReturn(false, "error: " + e.getMessage());
        }
    }

    static MonkeySource.MonkeyCommandReturn dispatch(String action, JSONObject json) throws Exception {
        IWifiManager wifi = getService();
        if (wifi == null) return new MonkeySource.MonkeyCommandReturn(false, "wifi service not found");

        switch (action) {
            case "on":
                return ok(wifi.setWifiEnabled(PKG, true));
            case "off":
                return ok(wifi.setWifiEnabled(PKG, false));
            case "info":
                return info(wifi);
            case "list":
                return list(wifi);
            case "setStatic":
                return setStatic(wifi, json);
            case "setDhcp":
                return setDhcp(wifi, json);
            case "connect":
                return connect(wifi, json);
            case "disconnect":
                return ok(wifi.disconnect(PKG));
            case "reconnect":
                return ok(wifi.reconnect(PKG));
            case "enable":
                return ok(wifi.enableNetwork(json.getInt("networkId"), true, PKG));
            case "disable":
                return ok(wifi.disableNetwork(json.getInt("networkId"), PKG));
            case "forget":
                wifi.forget(json.getInt("networkId"), null, PKG);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            case "save":
                return save(wifi, json);
            default:
                return new MonkeySource.MonkeyCommandReturn(false, "unknown action: " + action);
        }
    }

    static MonkeySource.MonkeyCommandReturn ok(boolean b) {
        return new MonkeySource.MonkeyCommandReturn(b, b ? "ok" : "failed");
    }

    static MonkeySource.MonkeyCommandReturn info(IWifiManager wifi) throws Exception {
        WifiInfo wi = wifi.getConnectionInfo(PKG, null);
        DhcpInfo dhcp = wifi.getDhcpInfo(PKG);
        JSONObject o = new JSONObject();
        if (wi != null) {
            o.put("ssid", wi.getSSID());
            o.put("bssid", wi.getBSSID());
            o.put("ip", intToIp(wi.getIpAddress()));
            o.put("rssi", wi.getRssi());
            o.put("linkSpeed", wi.getLinkSpeed());
            o.put("frequency", wi.getFrequency());
            o.put("networkId", wi.getNetworkId());
            o.put("mac", wi.getMacAddress());
        }
        if (dhcp != null) {
            o.put("dhcpIp", intToIp(dhcp.ipAddress));
            o.put("gateway", intToIp(dhcp.gateway));
            o.put("netmask", intToIp(dhcp.netmask));
            o.put("dns1", intToIp(dhcp.dns1));
            o.put("dns2", intToIp(dhcp.dns2));
        }
        return new MonkeySource.MonkeyCommandReturn(true, o.toString());
    }

    static MonkeySource.MonkeyCommandReturn list(IWifiManager wifi) throws Exception {
        ParceledListSlice slice = wifi.getConfiguredNetworks(PKG, null, false);
        List<?> configs = slice == null ? new ArrayList<>() : slice.getList();
        JSONArray arr = new JSONArray();
        for (Object obj : configs) {
            WifiConfiguration c = (WifiConfiguration) obj;
            JSONObject o = new JSONObject();
            o.put("networkId", c.networkId);
            o.put("SSID", c.SSID);
            o.put("status", c.status);
            o.put("priority", c.priority);
            o.put("ipAssignment", String.valueOf(c.getIpConfiguration() == null ? null
                    : c.getIpConfiguration().getIpAssignment()));
            arr.put(o);
        }
        return new MonkeySource.MonkeyCommandReturn(true, arr.toString());
    }

    static MonkeySource.MonkeyCommandReturn setStatic(IWifiManager wifi, JSONObject json) throws Exception {
        WifiConfiguration config = findOrCreate(wifi, json.getString("ssid"));
        String ip = json.getString("ip");
        int prefix = json.optInt("prefix", 24);
        String gateway = json.getString("gateway");
        JSONArray dnsArr = json.optJSONArray("dns");

        StaticIpConfiguration staticConfig = new StaticIpConfiguration();
        staticConfig.ipAddress = new LinkAddress(ip + "/" + prefix);
        staticConfig.gateway = InetAddress.getByName(gateway);
        // dnsServers 是 final ArrayList（构造时已初始化），直接 add 不赋值
        if (dnsArr != null) {
            for (int i = 0; i < dnsArr.length(); i++) {
                staticConfig.dnsServers.add(InetAddress.getByName(dnsArr.getString(i)));
            }
        }

        IpConfiguration ipConfig = new IpConfiguration();
        ipConfig.setIpAssignment(IpConfiguration.IpAssignment.STATIC);
        ipConfig.setStaticIpConfiguration(staticConfig);
        ipConfig.setProxySettings(IpConfiguration.ProxySettings.NONE);

        setField(config, "mIpConfiguration", ipConfig);

        int ret = wifi.addOrUpdateNetwork(config, PKG, null);
        return new MonkeySource.MonkeyCommandReturn(ret >= 0, "networkId=" + ret);
    }

    static MonkeySource.MonkeyCommandReturn setDhcp(IWifiManager wifi, JSONObject json) throws Exception {
        WifiConfiguration config = findOrCreate(wifi, json.getString("ssid"));
        IpConfiguration ipConfig = new IpConfiguration();
        ipConfig.setIpAssignment(IpConfiguration.IpAssignment.DHCP);
        ipConfig.setProxySettings(IpConfiguration.ProxySettings.NONE);
        setField(config, "mIpConfiguration", ipConfig);
        int ret = wifi.addOrUpdateNetwork(config, PKG, null);
        return new MonkeySource.MonkeyCommandReturn(ret >= 0, "networkId=" + ret);
    }

    static MonkeySource.MonkeyCommandReturn connect(IWifiManager wifi, JSONObject json) throws Exception {
        WifiConfiguration config = findOrCreate(wifi, json.getString("ssid"));
        wifi.connect(config, config.networkId, null, PKG, null);
        return new MonkeySource.MonkeyCommandReturn(true, "ok");
    }

    static MonkeySource.MonkeyCommandReturn save(IWifiManager wifi, JSONObject json) throws Exception {
        WifiConfiguration config = new WifiConfiguration();
        config.SSID = "\"" + json.getString("ssid") + "\"";
        if (json.has("password")) {
            config.preSharedKey = "\"" + json.getString("password") + "\"";
        }
        BitSet km = new BitSet();
        km.set(0); // WPA-EAP? 简单置位；具体按需
        config.allowedKeyManagement = km;
        int ret = wifi.addOrUpdateNetwork(config, PKG, null);
        return new MonkeySource.MonkeyCommandReturn(ret >= 0, "networkId=" + ret);
    }

    /** 找已配置网络（按 SSID），没有则新建 */
    static WifiConfiguration findOrCreate(IWifiManager wifi, String ssid) throws Exception {
        String quoted = "\"" + ssid + "\"";
        ParceledListSlice slice = wifi.getConfiguredNetworks(PKG, null, false);
        if (slice != null) {
            for (Object obj : slice.getList()) {
                WifiConfiguration c = (WifiConfiguration) obj;
                if (quoted.equals(c.SSID) || ssid.equals(c.SSID)) {
                    return c;
                }
            }
        }
        WifiConfiguration c = new WifiConfiguration();
        c.SSID = quoted;
        return c;
    }

    /** 反射设字段 */
    static void setField(Object obj, String name, Object value) throws Exception {
        Field f = obj.getClass().getDeclaredField(name);
        f.setAccessible(true);
        f.set(obj, value);
    }

    static String intToIp(int ip) {
        return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 24) & 0xFF);
    }
}
