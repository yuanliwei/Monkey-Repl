package com.android.commands.monkey;

import java.util.List;

import org.json.JSONObject;

import android.hardware.display.IDisplayManager;
import android.os.ServiceManager;
import android.view.DisplayInfo;
import android.view.IWindowManager;

/** display 命令实现（JSON 参数，调用 framework API） */
public class DisplayCommand {

    private static final int DEFAULT_DISPLAY = 0;

    static IDisplayManager getService() throws Exception {
        return IDisplayManager.Stub.asInterface(ServiceManager.getService("display"));
    }

    static IWindowManager getWindowManager() throws Exception {
        return IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
    }

    /** 入口：display <json> */
    public static MonkeySource.MonkeyCommandReturn execute(List<String> command) {
        if (command.size() < 2) {
            return new MonkeySource.MonkeyCommandReturn(false, "usage: display <json>");
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
        IDisplayManager dm = getService();
        if (dm == null) return new MonkeySource.MonkeyCommandReturn(false, "display service not found");
        int displayId = json.optInt("displayId", DEFAULT_DISPLAY);

        switch (action) {
            case "brightness":
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(dm.getBrightness(displayId)));
            case "setbrightness": {
                float brightness = (float) json.getDouble("brightness");
                dm.setBrightness(displayId, brightness);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            case "info":
                return info(dm, displayId);
            case "rotate": {
                int rotation = json.getInt("rotation"); // 0/1/2/3
                IWindowManager wm = getWindowManager();
                if (wm == null) return new MonkeySource.MonkeyCommandReturn(false, "window service not found");
                wm.freezeRotation(rotation);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            case "unfreeze": {
                IWindowManager wm = getWindowManager();
                if (wm == null) return new MonkeySource.MonkeyCommandReturn(false, "window service not found");
                wm.thawRotation();
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            default:
                return new MonkeySource.MonkeyCommandReturn(false, "unknown action: " + action);
        }
    }

    static MonkeySource.MonkeyCommandReturn info(IDisplayManager dm, int displayId) throws Exception {
        DisplayInfo info = dm.getDisplayInfo(displayId);
        JSONObject o = new JSONObject();
        if (info == null) return new MonkeySource.MonkeyCommandReturn(false, "display " + displayId + " not found");
        o.put("displayId", info.displayId);
        o.put("name", info.name);
        o.put("appWidth", info.appWidth);
        o.put("appHeight", info.appHeight);
        o.put("logicalWidth", info.logicalWidth);
        o.put("logicalHeight", info.logicalHeight);
        o.put("densityDpi", info.logicalDensityDpi);
        o.put("rotation", info.rotation);
        o.put("state", info.state);
        return new MonkeySource.MonkeyCommandReturn(true, o.toString());
    }
}
