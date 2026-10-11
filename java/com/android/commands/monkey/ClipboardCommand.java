package com.android.commands.monkey;

import java.util.List;

import org.json.JSONObject;

import android.content.ClipData;
import android.content.IClipboard;
import android.os.ServiceManager;

/** clipboard 命令实现（JSON 参数，调 framework API，多版本签名回退） */
public class ClipboardCommand {

    private static final String PKG = "com.android.shell";

    static IClipboard getService() throws Exception {
        return IClipboard.Stub.asInterface(ServiceManager.getService("clipboard"));
    }

    /** 入口：clipboard <json> */
    public static MonkeySource.MonkeyCommandReturn execute(List<String> command) {
        if (command.size() < 2) {
            return new MonkeySource.MonkeyCommandReturn(false, "usage: clipboard <json>");
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
        IClipboard clipboard = getService();
        if (clipboard == null) return new MonkeySource.MonkeyCommandReturn(false, "clipboard service not found");

        switch (action) {
            case "has":
                return new MonkeySource.MonkeyCommandReturn(true, String.valueOf(hasText(clipboard)));
            case "get": {
                String text = getText(clipboard);
                return new MonkeySource.MonkeyCommandReturn(true, text == null ? "" : text);
            }
            case "clear":
                clipboard.clearPrimaryClip(PKG);
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            default:
                return new MonkeySource.MonkeyCommandReturn(false, "unknown action: " + action);
        }
    }

    /** 是否有剪贴板文本（高版本 4 参 → 低版本 1 参） */
    static boolean hasText(IClipboard clipboard) {
        try {
            return clipboard.hasClipboardText(PKG, null, 0, 0);
        } catch (Throwable e) {
            try {
                return clipboard.hasClipboardText(PKG);
            } catch (Throwable e2) {
                return false;
            }
        }
    }

    /** 读取剪贴板文本（高版本 7 参 → 低版本 1 参） */
    static String getText(IClipboard clipboard) {
        ClipData clip = null;
        try {
            clip = clipboard.getPrimaryClip(PKG, null, null, null, 0, 0, false);
        } catch (Throwable e) {
            try {
                clip = clipboard.getPrimaryClip(PKG);
            } catch (Throwable e2) {
                return null;
            }
        }
        if (clip == null || clip.getItemCount() == 0) return null;
        CharSequence text = clip.getItemAt(0).getText();
        return text == null ? null : text.toString();
    }
}
