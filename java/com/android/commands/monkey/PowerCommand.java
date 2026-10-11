package com.android.commands.monkey;

import java.util.List;

import org.json.JSONObject;

import android.os.IPowerManager;
import android.os.ServiceManager;

/** power 命令实现（JSON 参数，调用 framework API） */
public class PowerCommand {

    static IPowerManager getService() throws Exception {
        return IPowerManager.Stub.asInterface(ServiceManager.getService("power"));
    }

    /** 入口：power <json> */
    public static MonkeySource.MonkeyCommandReturn execute(List<String> command) {
        if (command.size() < 2) {
            return new MonkeySource.MonkeyCommandReturn(false, "usage: power <json>");
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
        IPowerManager power = getService();
        if (power == null) return new MonkeySource.MonkeyCommandReturn(false, "power service not found");

        switch (action) {
            case "reboot": {
                String reason = json.optString("reason", null);
                power.reboot(false, reason, false);
                return new MonkeySource.MonkeyCommandReturn(true, "rebooting");
            }
            case "sleep":
            case "screenoff": {
                power.goToSleep(0, 0, 0);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            case "wakeup": {
                power.wakeUp(0);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            case "nap": {
                long time = json.optLong("time", 0);
                power.nap(time);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            case "interactive":
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(power.isInteractive()));
            case "powersave":
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(power.isPowerSaveMode()));
            case "shutdownreason":
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(power.getLastShutdownReason()));
            default:
                return new MonkeySource.MonkeyCommandReturn(false, "unknown action: " + action);
        }
    }
}
