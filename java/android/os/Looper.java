
package android.os;

import android.os.Handler.Callback;

public final class Looper {

    public static Looper myLooper() {
        throw new RuntimeException("Stub!");
    }

    public MessageQueue mQueue;

    public static Callback getMainLooper() {
        throw new RuntimeException("Stub!");
    }

    public static void prepare() {
    }

    public static void loop() {

    }

}
