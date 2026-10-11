package com.android.commands.monkey;

import java.util.List;

import org.json.JSONObject;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.ComponentName;
import android.view.accessibility.AccessibilityNodeInfo;


/** wait 命令实现（轮询等待条件满足，JSON 参数） */
public class WaitCommand {

    static final long POLL_INTERVAL = 200;

    /** 入口：wait <json> */
    public static MonkeySource.MonkeyCommandReturn execute(List<String> command) {
        if (command.size() < 2) {
            return new MonkeySource.MonkeyCommandReturn(false, "usage: wait <json>");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < command.size(); i++) {
            if (i > 1) sb.append(" ");
            sb.append(command.get(i));
        }
        try {
            JSONObject json = new JSONObject(sb.toString());
            String action = json.getString("action");
            long timeout = json.optLong("timeout", 10000);
            boolean gone = json.optBoolean("gone", false);
            return wait(action, json, timeout, gone);
        } catch (Exception e) {
            return new MonkeySource.MonkeyCommandReturn(false, "error: " + e.getMessage());
        }
    }

    static MonkeySource.MonkeyCommandReturn wait(String action, JSONObject json, long timeout, boolean gone) throws Exception {
        long deadline = System.currentTimeMillis() + timeout;
        while (true) {
            boolean hit = check(action, json);
            // gone=true: 等待条件不满足；gone=false: 等待条件满足
            boolean satisfied = gone ? !hit : hit;
            if (satisfied) {
                return new MonkeySource.MonkeyCommandReturn(true, "ok");
            }
            if (System.currentTimeMillis() >= deadline) {
                return new MonkeySource.MonkeyCommandReturn(false, "timeout after " + timeout + "ms");
            }
            Thread.sleep(POLL_INTERVAL);
        }
    }

    /** 检查条件当前是否命中 */
    static boolean check(String action, JSONObject json) throws Exception {
        switch (action) {
            case "activity":
                return matchActivity(json.getString("activity"));
            case "view":
                return matchView(json.getString("text"), null);
            case "id":
                return matchView(null, json.getString("id"));
            default:
                return false;
        }
    }

    /** 顶层 activity 是否匹配（全名或子串） */
    static boolean matchActivity(String pattern) {
        try {
            IActivityManager am = ActivityManager.getService();
            List<ActivityManager.RunningTaskInfo> tasks = null;
            try {
                tasks = am.getTasks(1);
            } catch (Throwable e) {
                try { tasks = am.getTasks(1, 0); } catch (Throwable e1) { return false; }
            }
            if (tasks == null || tasks.isEmpty()) return false;
            ComponentName cn = tasks.get(0).topActivity;
            if (cn == null) return false;
            String full = cn.getPackageName() + "/" + cn.getClassName();
            return full.contains(pattern) || full.equals(pattern);
        } catch (Throwable e) {
            return false;
        }
    }

    /** 控件树中是否有匹配 text 或 id 的节点 */
    static boolean matchView(String text, String id) {
        try {
            AccessibilityNodeInfo root = MonkeySourceViews.getActiveRoot();
            if (root == null) return false;
            return findNode(root, text, id);
        } catch (Throwable e) {
            return false;
        }
    }

    /** 递归查找节点（text 子串匹配，id 精确匹配） */
    static boolean findNode(AccessibilityNodeInfo node, String text, String id) {
        if (node == null) return false;
        if (text != null) {
            CharSequence t = node.getText();
            if (t != null && t.toString().contains(text)) return true;
        }
        if (id != null) {
            String vid = node.getViewIdResourceName();
            if (id.equals(vid)) return true;
        }
        int count = node.getChildCount();
        for (int i = 0; i < count; i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null && findNode(child, text, id)) return true;
        }
        return false;
    }
}
