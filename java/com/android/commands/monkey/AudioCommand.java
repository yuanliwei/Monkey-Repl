package com.android.commands.monkey;

import java.util.List;

import org.json.JSONObject;

import android.media.IAudioService;
import android.os.ServiceManager;

/** audio 命令实现（JSON 参数，调用 framework API） */
public class AudioCommand {

    private static final String PKG = "com.android.shell";

    static IAudioService getService() throws Exception {
        return IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
    }

    /** 入口：audio <json> */
    public static MonkeySource.MonkeyCommandReturn execute(List<String> command) {
        if (command.size() < 2) {
            return new MonkeySource.MonkeyCommandReturn(false, "usage: audio <json>");
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
        IAudioService audio = getService();
        if (audio == null) return new MonkeySource.MonkeyCommandReturn(false, "audio service not found");

        switch (action) {
            case "get": {
                int stream = streamOf(json);
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(audio.getStreamVolume(stream)));
            }
            case "set": {
                int stream = streamOf(json);
                int index = json.getInt("index");
                audio.setStreamVolume(stream, index, 0, PKG);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            case "adjust": {
                int stream = streamOf(json);
                int direction = json.optInt("direction", 1); // 1=up -1=down
                audio.adjustStreamVolume(stream, direction, 0, PKG);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            case "max": {
                int stream = streamOf(json);
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(audio.getStreamMaxVolume(stream)));
            }
            case "min": {
                int stream = streamOf(json);
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(audio.getStreamMinVolume(stream)));
            }
            case "mute": {
                int stream = streamOf(json);
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(audio.isStreamMute(stream)));
            }
            case "mastermute":
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(audio.isMasterMute()));
            case "setmastermute": {
                boolean mute = json.getBoolean("mute");
                audio.setMasterMute(mute, 0, PKG, 0, null);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            case "ringermode":
                return new MonkeySource.MonkeyCommandReturn(true, ringerName(audio.getRingerModeExternal()));
            case "setringermode": {
                int mode = json.optInt("mode", 2);
                audio.setRingerModeExternal(mode, PKG);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            case "musicactive":
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(audio.isMusicActive(false)));
            default:
                return new MonkeySource.MonkeyCommandReturn(false, "unknown action: " + action);
        }
    }

    /** stream 名或数字转 streamType */
    static int streamOf(JSONObject json) {
        if (json.has("stream")) {
            Object v = json.opt("stream");
            if (v instanceof Number) return ((Number) v).intValue();
            return streamType(String.valueOf(v));
        }
        return 3; // 默认 MUSIC
    }

    static int streamType(String name) {
        switch (name.toLowerCase()) {
            case "voice": case "call": return 0;
            case "system": return 1;
            case "ring": return 2;
            case "music": return 3;
            case "alarm": return 4;
            case "notification": case "notif": return 5;
            case "bluetooth": case "sco": return 6;
            case "dtmf": return 8;
            case "accessibility": return 10;
            default: return 3;
        }
    }

    static String ringerName(int mode) {
        switch (mode) {
            case 0: return "SILENT";
            case 1: return "VIBRATE";
            case 2: return "NORMAL";
            default: return "UNKNOWN(" + mode + ")";
        }
    }
}
