package com.android.commands.monkey;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.json.JSONObject;

/** shell 命令实现：执行 shell 命令（JSON 参数或直接参数） */
public class ShellCommand {

    /** 入口：shell <命令> */
    public static MonkeySource.MonkeyCommandReturn execute(List<String> command) {
        if (command.size() < 2) {
            return new MonkeySource.MonkeyCommandReturn(false, "usage: shell <cmd>");
        }
        // 从第 2 个元素起拼接（JSON 形式 {"cmd":"..."} 或直接命令）
        String cmd;
        String joined = join(command, 1);
        if (joined.trim().startsWith("{")) {
            try {
                JSONObject json = new JSONObject(joined);
                cmd = json.getString("cmd");
                if (json.has("timeout")) {
                    long timeout = json.optLong("timeout", 30000);
                    return run(cmd, timeout);
                }
            } catch (Exception e) {
                return new MonkeySource.MonkeyCommandReturn(false, "error: " + e.getMessage());
            }
        } else {
            cmd = joined;
        }
        return run(cmd, 30000);
    }

    static String join(List<String> command, int from) {
        StringBuilder sb = new StringBuilder();
        for (int i = from; i < command.size(); i++) {
            if (i > from) sb.append(" ");
            sb.append(command.get(i));
        }
        return sb.toString();
    }

    static MonkeySource.MonkeyCommandReturn run(String cmd, long timeout) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[] { "sh", "-c", cmd });
            final Process p = process;
            final StringBuilder output = new StringBuilder();
            // 后台读 stdout + stderr（合并），避免缓冲区满阻塞
            Thread reader = new Thread(new Runnable() {
                public void run() {
                    try {
                        InputStream is = p.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String line;
                        while ((line = br.readLine()) != null) {
                            if (output.length() > 0) output.append("\n");
                            output.append(line);
                        }
                    } catch (Exception ignored) {
                    }
                }
            });
            reader.setDaemon(true);
            reader.start();

            boolean finished = waitFor(process, timeout);
            if (!finished) {
                process.destroy();
                return new MonkeySource.MonkeyCommandReturn(false, "timeout after " + timeout + "ms");
            }
            reader.join(1000);
            int exit = process.exitValue();
            String out = output.toString();
            JSONObject o = new JSONObject();
            o.put("exitCode", exit);
            o.put("output", out);
            return new MonkeySource.MonkeyCommandReturn(exit == 0, o.toString());
        } catch (Exception e) {
            return new MonkeySource.MonkeyCommandReturn(false, "error: " + e.getMessage());
        } finally {
            if (process != null) process.destroy();
        }
    }

    /** 等待进程结束，带超时 */
    static boolean waitFor(Process process, long timeout) {
        long deadline = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < deadline) {
            try {
                process.exitValue();
                return true;
            } catch (IllegalThreadStateException e) {
                // 未结束
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                return false;
            }
        }
        return false;
    }
}
