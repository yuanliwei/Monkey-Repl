
package android.app;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.security.auth.login.Configuration;

import android.annotation.IntDef;
import android.annotation.Nullable;
import android.content.ComponentCallbacks2;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.IBinder;

public abstract class Service extends ContextWrapper implements ComponentCallbacks2 {
    public static final int STOP_FOREGROUND_REMOVE = 1 << 0;

    public static final int STOP_FOREGROUND_DETACH = 1 << 1;

    @IntDef(flag = true, prefix = { "STOP_FOREGROUND_" }, value = { STOP_FOREGROUND_REMOVE, STOP_FOREGROUND_DETACH })
    @Retention(RetentionPolicy.SOURCE)
    public @interface StopForegroundFlags {
    }

    public Service() {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public final Application getApplication() {
        throw new RuntimeException("Stub!");
    }

    public void onCreate() {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void onStart(Intent intent, int startId) {
        throw new RuntimeException("Stub!");
    }

    public static final int START_CONTINUATION_MASK = 0xf;

    public static final int START_STICKY_COMPATIBILITY = 0;

    public static final int START_STICKY = 1;

    public static final int START_NOT_STICKY = 2;

    public static final int START_REDELIVER_INTENT = 3;

    @IntDef(flag = false, prefix = { "START_" }, value = { START_STICKY_COMPATIBILITY, START_STICKY, START_NOT_STICKY,
            START_REDELIVER_INTENT, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface StartResult {
    }

    public static final int START_TASK_REMOVED_COMPLETE = 1000;

    public static final int START_FLAG_REDELIVERY = 0x0001;

    public static final int START_FLAG_RETRY = 0x0002;

    @IntDef(flag = true, prefix = { "START_FLAG_" }, value = { START_FLAG_REDELIVERY, START_FLAG_RETRY, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface StartArgFlags {
    }

    public @StartResult int onStartCommand(Intent intent, @StartArgFlags int flags, int startId) {
        throw new RuntimeException("Stub!");
    }

    public void onDestroy() {
        throw new RuntimeException("Stub!");
    }

    public void onConfigurationChanged(Configuration newConfig) {
        throw new RuntimeException("Stub!");
    }

    public void onLowMemory() {
        throw new RuntimeException("Stub!");
    }

    public void onTrimMemory(int level) {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    public abstract IBinder onBind(Intent intent);

    public boolean onUnbind(Intent intent) {
        throw new RuntimeException("Stub!");
    }

    public void onRebind(Intent intent) {
        throw new RuntimeException("Stub!");
    }

    public void onTaskRemoved(Intent rootIntent) {
        throw new RuntimeException("Stub!");
    }

    public final void stopSelf() {
        throw new RuntimeException("Stub!");
    }

    public final void stopSelf(int startId) {
        throw new RuntimeException("Stub!");
    }

    public final boolean stopSelfResult(int startId) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public final void setForeground(boolean isForeground) {
        throw new RuntimeException("Stub!");
    }

    public final void stopForeground(boolean removeNotification) {
        throw new RuntimeException("Stub!");
    }

    public final void stopForeground(@StopForegroundFlags int flags) {
        throw new RuntimeException("Stub!");
    }

    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        throw new RuntimeException("Stub!");
    }

    public final void detachAndCleanUp() {
        throw new RuntimeException("Stub!");
    }

    final String getClassName() {
        throw new RuntimeException("Stub!");
    }

}
