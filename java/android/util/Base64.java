package android.util;

public class Base64 {
    public static final int DEFAULT = 0;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int CRLF = 4;
    public static final int URL_SAFE = 8;
    public static final int NO_CLOSE = 16;

    public static byte[] decode(String str, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static byte[] decode(byte[] input, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static byte[] decode(byte[] input, int offset, int len, int flags) {
        throw new RuntimeException("Stub!");
    }

    public int maxOutputSize(int len) {
        throw new RuntimeException("Stub!");
    }

    public boolean process(byte[] input, int offset, int len, boolean finish) {
        throw new RuntimeException("Stub!");
    }

    public static String encodeToString(byte[] input, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static String encodeToString(byte[] input, int offset, int len, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static byte[] encode(byte[] input, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static byte[] encode(byte[] input, int offset, int len, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static final int LINE_GROUPS = 19;
}
