
package android.util;

import android.annotation.UnsupportedAppUsage;

public final class Log {

    public static final int VERBOSE = 2;

    public static final int DEBUG = 3;

    public static final int INFO = 4;

    public static final int WARN = 5;

    public static final int ERROR = 6;

    public static final int ASSERT = 7;

    public static class TerribleFailure extends Exception {

        private static final long serialVersionUID = 4582908433970648294L;

        TerribleFailure(String msg, Throwable cause) {
            throw new RuntimeException("Stub!");
        }
    }

    public interface TerribleFailureHandler {
        void onTerribleFailure(String tag, TerribleFailure what, boolean system);
    }

    private static TerribleFailureHandler sWtfHandler = new TerribleFailureHandler() {
        public void onTerribleFailure(String tag, TerribleFailure what, boolean system) {
            throw new RuntimeException("Stub!");
        }
    };

    private Log() {
        throw new RuntimeException("Stub!");
    }

    public static int v(String tag, String msg) {
        throw new RuntimeException("Stub!");
    }

    public static int v(String tag, String msg, Throwable tr) {
        throw new RuntimeException("Stub!");
    }

    public static int d(String tag, String msg) {
        throw new RuntimeException("Stub!");
    }

    public static int d(String tag, String msg, Throwable tr) {
        throw new RuntimeException("Stub!");
    }

    public static int i(String tag, String msg) {
        throw new RuntimeException("Stub!");
    }

    public static int i(String tag, String msg, Throwable tr) {
        throw new RuntimeException("Stub!");
    }

    public static int w(String tag, String msg) {
        throw new RuntimeException("Stub!");
    }

    public static int w(String tag, String msg, Throwable tr) {
        throw new RuntimeException("Stub!");
    }

    public static native boolean isLoggable(String tag, int level);

    public static int w(String tag, Throwable tr) {
        throw new RuntimeException("Stub!");
    }

    public static int e(String tag, String msg) {
        throw new RuntimeException("Stub!");
    }

    public static int e(String tag, String msg, Throwable tr) {
        throw new RuntimeException("Stub!");
    }

    public static int wtf(String tag, String msg) {
        throw new RuntimeException("Stub!");
    }

    public static int wtfStack(String tag, String msg) {
        throw new RuntimeException("Stub!");
    }

    public static int wtf(String tag, Throwable tr) {
        throw new RuntimeException("Stub!");
    }

    public static int wtf(String tag, String msg, Throwable tr) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    static int wtf(int logId, String tag, String msg, Throwable tr, boolean localStack, boolean system) {
        throw new RuntimeException("Stub!");
    }

    static void wtfQuiet(int logId, String tag, String msg, boolean system) {
        throw new RuntimeException("Stub!");
    }

    public static TerribleFailureHandler setWtfHandler(TerribleFailureHandler handler) {
        throw new RuntimeException("Stub!");
    }

    public static String getStackTraceString(Throwable tr) {
        throw new RuntimeException("Stub!");
    }

    public static int println(int priority, String tag, String msg) {
        throw new RuntimeException("Stub!");
    }

    public static final int LOG_ID_MAIN = 0;
    public static final int LOG_ID_RADIO = 1;
    public static final int LOG_ID_EVENTS = 2;
    public static final int LOG_ID_SYSTEM = 3;
    public static final int LOG_ID_CRASH = 4;

    @UnsupportedAppUsage
    public static native int println_native(int bufID, int priority, String tag, String msg);

    public static int printlns(int bufID, int priority, String tag, String msg, Throwable tr) {
        throw new RuntimeException("Stub!");
    }

    static class PreloadHolder {
        public final static int LOGGER_ENTRY_MAX_PAYLOAD = 0;
    }
}
