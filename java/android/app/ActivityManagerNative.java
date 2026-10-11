
package android.app;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public abstract class ActivityManagerNative extends Binder implements IActivityManager {
    static public IActivityManager asInterface(IBinder obj) {
        throw new RuntimeException("Stub!");
    }

    static public IActivityManager getDefault() {
        throw new RuntimeException("Stub!");
    }

    static public boolean isSystemReady() {
        throw new RuntimeException("Stub!");
    }

    static public void broadcastStickyIntent(Intent intent, String permission, int userId) {
        throw new RuntimeException("Stub!");
    }

    public ActivityManagerNative() {
        throw new RuntimeException("Stub!");
    }

    public IBinder asBinder() {
        throw new RuntimeException("Stub!");
    }

}
