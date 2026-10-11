package com.android.commands.monkey;

import java.util.List;

import org.json.JSONObject;

/** app 命令实现（JSON 参数，内部调用 shell/am） */
public class AppCommand {

    /** 入口：app <json> */
    public static MonkeySource.MonkeyCommandReturn execute(List<String> command) {
        if (command.size() < 2) {
            return new MonkeySource.MonkeyCommandReturn(false, "usage: app <json>");
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
        switch (action) {
            case "start": {
                String pkg = json.getString("package");
                // 用 monkey 启动 launcher activity（最通用）
                return ShellCommand.run("monkey -p " + pkg + " -c android.intent.category.LAUNCHER 1", 15000);
            }
            case "stop": {
                String pkg = json.getString("package");
                return ShellCommand.run("am force-stop " + pkg, 10000);
            }
            case "current": {
                return ShellCommand.run("dumpsys activity activities | grep -E 'mResumedActivity|topResumedActivity' | head -1", 10000);
            }
            case "list": {
                return ShellCommand.run("pm list packages -3", 15000);
            }
            default:
                return new MonkeySource.MonkeyCommandReturn(false, "unknown action: " + action);
        }
    }
}
