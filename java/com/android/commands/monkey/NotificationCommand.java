package com.android.commands.monkey;

import java.util.List;

import org.json.JSONObject;

import android.app.INotificationManager;
import android.os.ServiceManager;

/** notification 命令实现（JSON 参数，调用 framework API） */
public class NotificationCommand {

    private static final String PKG = "com.android.shell";

    static INotificationManager getService() throws Exception {
        return INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
    }

    /** 入口：notification <json> */
    public static MonkeySource.MonkeyCommandReturn execute(List<String> command) {
        if (command.size() < 2) {
            return new MonkeySource.MonkeyCommandReturn(false, "usage: notification <json>");
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
        INotificationManager nm = getService();
        if (nm == null) return new MonkeySource.MonkeyCommandReturn(false, "notification service not found");

        switch (action) {
            case "list":
                // getActiveNotifications 需 ACCESS_NOTIFICATIONS 权限（shell 无）
                return new MonkeySource.MonkeyCommandReturn(false, "permission denied; use: shell {\"cmd\":\"dumpsys notification --noredact\"}");
            case "cancelall": {
                String pkg = json.optString("package", null);
                nm.cancelAllNotifications(pkg, 0);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            case "enabled": {
                String pkg = json.optString("package", PKG);
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(nm.areNotificationsEnabled(pkg)));
            }
            default:
                return new MonkeySource.MonkeyCommandReturn(false, "unknown action: " + action);
        }
    }
}
