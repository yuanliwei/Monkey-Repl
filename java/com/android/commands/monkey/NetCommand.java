package com.android.commands.monkey;

import java.util.List;

import org.json.JSONObject;

import android.net.IConnectivityManager;
import android.net.NetworkInfo;
import android.os.ServiceManager;

/** net 命令实现（JSON 参数，调用 framework API） */
public class NetCommand {

    static IConnectivityManager getService() throws Exception {
        return IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity"));
    }

    /** 入口：net <json> */
    public static MonkeySource.MonkeyCommandReturn execute(List<String> command) {
        if (command.size() < 2) {
            return new MonkeySource.MonkeyCommandReturn(false, "usage: net <json>");
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
        IConnectivityManager cm = getService();
        if (cm == null) return new MonkeySource.MonkeyCommandReturn(false, "connectivity service not found");

        switch (action) {
            case "info":
                return info(cm);
            case "metered":
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(cm.isActiveNetworkMetered()));
            case "airplane": {
                boolean enable = json.optBoolean("enable", true);
                cm.setAirplaneMode(enable);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            default:
                return new MonkeySource.MonkeyCommandReturn(false, "unknown action: " + action);
        }
    }

    static MonkeySource.MonkeyCommandReturn info(IConnectivityManager cm) throws Exception {
        NetworkInfo ni = cm.getActiveNetworkInfo();
        JSONObject o = new JSONObject();
        if (ni == null) {
            o.put("connected", false);
        } else {
            o.put("type", ni.getType());
            o.put("typeName", ni.getTypeName());
            o.put("subtypeName", ni.getSubtypeName());
            o.put("state", String.valueOf(ni.getState()));
            o.put("connected", ni.isConnected());
            o.put("available", ni.isAvailable());
            o.put("roaming", ni.isRoaming());
            o.put("extraInfo", ni.getExtraInfo());
        }
        return new MonkeySource.MonkeyCommandReturn(true, o.toString());
    }
}
