package com.android.commands.monkey;

import java.util.List;

import org.json.JSONObject;

/** cmd 命令实现：执行 cmd <service> <args>（覆盖 vibrator/location/telephony/media_session 等） */
public class CmdCommand {

    /** 入口：cmd <json> */
    public static MonkeySource.MonkeyCommandReturn execute(List<String> command) {
        if (command.size() < 2) {
            return new MonkeySource.MonkeyCommandReturn(false, "usage: cmd <json>");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < command.size(); i++) {
            if (i > 1) sb.append(" ");
            sb.append(command.get(i));
        }
        try {
            JSONObject json = new JSONObject(sb.toString());
            String service = json.getString("service");
            String args = json.optString("args", "");
            long timeout = json.optLong("timeout", 15000);
            String full = "cmd " + service + (args.isEmpty() ? "" : " " + args);
            return ShellCommand.run(full, timeout);
        } catch (Exception e) {
            return new MonkeySource.MonkeyCommandReturn(false, "error: " + e.getMessage());
        }
    }
}
