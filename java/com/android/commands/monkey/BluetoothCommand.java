package com.android.commands.monkey;

import java.util.List;

import org.json.JSONObject;

import android.bluetooth.IBluetoothManager;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.IBluetooth;
import android.content.AttributionSource;
import android.os.ServiceManager;

/** 蓝牙命令实现（JSON 参数，调用 framework API） */
public class BluetoothCommand {

    static IBluetoothManager getService() {
        return IBluetoothManager.Stub.asInterface(ServiceManager.getService("bluetooth_manager"));
    }

    /** 入口：bluetooth <json> */
    public static MonkeySource.MonkeyCommandReturn execute(List<String> command) {
        if (command.size() < 2) {
            return new MonkeySource.MonkeyCommandReturn(false, "usage: bluetooth <json>");
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
        IBluetoothManager mgr = getService();
        if (mgr == null) return new MonkeySource.MonkeyCommandReturn(false, "bluetooth service not found");
        AttributionSource source = AttributionSource.myAttributionSource();

        switch (action) {
            case "on":
                return ok(mgr.enable(source));
            case "off":
                return ok(mgr.disable(source, true));
            case "state":
                return new MonkeySource.MonkeyCommandReturn(true, stateName(mgr.getState()));
            case "info":
                return info(mgr, source);
            case "scan":
                return scan(mgr, json);
            case "scanning":
                return scanning(mgr);
            case "devices":
                return devices(mgr);
            case "bond":
                return bond(mgr, json);
            default:
                return new MonkeySource.MonkeyCommandReturn(false, "unknown action: " + action);
        }
    }

    static MonkeySource.MonkeyCommandReturn ok(boolean b) {
        return new MonkeySource.MonkeyCommandReturn(b, b ? "ok" : "failed");
    }

    static MonkeySource.MonkeyCommandReturn info(IBluetoothManager mgr, AttributionSource source) throws Exception {
        JSONObject o = new JSONObject();
        o.put("state", stateName(mgr.getState()));
        o.put("stateCode", mgr.getState());
        o.put("address", mgr.getAddress(source));
        o.put("name", mgr.getName(source));
        o.put("enabled", mgr.getState() == 12);
        return new MonkeySource.MonkeyCommandReturn(true, o.toString());
    }

    /** 状态码转名（BluetoothAdapter 常量） */
    static String stateName(int state) {
        switch (state) {
            case 10: return "OFF";
            case 11: return "TURNING_ON";
            case 12: return "ON";
            case 13: return "TURNING_OFF";
            default: return "UNKNOWN(" + state + ")";
        }
    }

    /** 获取 IBluetooth（registerAdapter 返回当前 IBluetooth 的 IBinder） */
    static IBluetooth getBluetooth(IBluetoothManager mgr) throws Exception {
        android.bluetooth.IBluetoothManagerCallback callback = new android.bluetooth.IBluetoothManagerCallback.Stub() {
            @Override public void onBluetoothServiceUp(android.os.IBinder b) {}
            @Override public void onBluetoothServiceDown() {}
            @Override public void onBluetoothOn() {}
            @Override public void onBluetoothOff() {}
        };
        android.os.IBinder binder = mgr.registerAdapter(callback);
        if (binder == null) return null;
        return IBluetooth.Stub.asInterface(binder);
    }

    static MonkeySource.MonkeyCommandReturn scan(IBluetoothManager mgr, JSONObject json) throws Exception {
        IBluetooth bt = getBluetooth(mgr);
        if (bt == null) return new MonkeySource.MonkeyCommandReturn(false, "bluetooth not available");
        AttributionSource source = AttributionSource.myAttributionSource();
        boolean enable = json.optBoolean("enable", true);
        return ok(enable ? bt.startDiscovery(source) : bt.cancelDiscovery(source));
    }

    static MonkeySource.MonkeyCommandReturn scanning(IBluetoothManager mgr) throws Exception {
        IBluetooth bt = getBluetooth(mgr);
        if (bt == null) return new MonkeySource.MonkeyCommandReturn(false, "bluetooth not available");
        return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(bt.isDiscovering(AttributionSource.myAttributionSource())));
    }

    static MonkeySource.MonkeyCommandReturn devices(IBluetoothManager mgr) throws Exception {
        IBluetooth bt = getBluetooth(mgr);
        if (bt == null) return new MonkeySource.MonkeyCommandReturn(false, "bluetooth not available");
        AttributionSource source = AttributionSource.myAttributionSource();
        List<BluetoothDevice> list = bt.getBondedDevices(source);
        org.json.JSONArray arr = new org.json.JSONArray();
        if (list != null) {
            for (BluetoothDevice d : list) arr.put(deviceJson(bt, d, source));
        }
        return new MonkeySource.MonkeyCommandReturn(true, arr.toString());
    }

    static JSONObject deviceJson(IBluetooth bt, BluetoothDevice d, AttributionSource source) throws Exception {
        JSONObject o = new JSONObject();
        o.put("address", d.getAddress());
        o.put("name", bt.getRemoteName(d, source));
        o.put("alias", bt.getRemoteAlias(d, source));
        o.put("bondState", bondName(bt.getBondState(d, source)));
        o.put("type", bt.getRemoteType(d, source));
        o.put("class", bt.getRemoteClass(d, source));
        return o;
    }

    static MonkeySource.MonkeyCommandReturn bond(IBluetoothManager mgr, JSONObject json) throws Exception {
        IBluetooth bt = getBluetooth(mgr);
        if (bt == null) return new MonkeySource.MonkeyCommandReturn(false, "bluetooth not available");
        AttributionSource source = AttributionSource.myAttributionSource();
        String address = json.getString("address");
        String op = json.optString("op", "create");
        if ("create".equals(op)) {
            // 配对未配对设备需构造 BluetoothDevice（hidden 构造，app_process 下不可豁免）
            return new MonkeySource.MonkeyCommandReturn(false,
                    "create(bond new device) unsupported: BluetoothDevice hidden constructor inaccessible in app_process");
        }
        // 其余操作基于已配对设备（从 getBondedDevices 取，避免直接构造 hidden 构造）
        BluetoothDevice device = findBonded(bt, source, address);
        if (device == null) return new MonkeySource.MonkeyCommandReturn(false, "not bonded: " + address);
        switch (op) {
            case "remove":
                return ok(bt.removeBond(device, source));
            case "cancel":
                return ok(bt.cancelBondProcess(device, source));
            case "state":
                return new MonkeySource.MonkeyCommandReturn(true, bondName(bt.getBondState(device, source)));
            case "connect":
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(bt.getConnectionState(device, source)));
            default:
                return new MonkeySource.MonkeyCommandReturn(false, "unknown bond op: " + op);
        }
    }

    /** 在已配对设备中按地址查找 */
    static BluetoothDevice findBonded(IBluetooth bt, AttributionSource source, String address) throws Exception {
        List<BluetoothDevice> list = bt.getBondedDevices(source);
        if (list != null) {
            for (BluetoothDevice d : list) {
                if (d.getAddress().equalsIgnoreCase(address)) return d;
            }
        }
        return null;
    }

    static String bondName(int state) {
        switch (state) {
            case 10: return "NONE";
            case 11: return "BONDING";
            case 12: return "BONDED";
            default: return "UNKNOWN(" + state + ")";
        }
    }
}
