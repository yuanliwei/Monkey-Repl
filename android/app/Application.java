package android.app;

import android.annotation.CallSuper;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Bundle;

public class Application extends ContextWrapper implements ComponentCallbacks2 {
    public interface ActivityLifecycleCallbacks {
        void onActivityCreated(Activity activity, Bundle savedInstanceState);

        void onActivityStarted(Activity activity);

        void onActivityResumed(Activity activity);

        void onActivityPaused(Activity activity);

        void onActivityStopped(Activity activity);

        void onActivitySaveInstanceState(Activity activity, Bundle outState);

        void onActivityDestroyed(Activity activity);
    }

    public interface OnProvideAssistDataListener {
        public void onProvideAssistData(Activity activity, Bundle data);
    }

    public Application() {
        super(null);
        throw new RuntimeException("Stub!");
    }

    @CallSuper
    public void onCreate() {
        throw new RuntimeException("Stub!");
    }

    @CallSuper
    public void onTerminate() {
        throw new RuntimeException("Stub!");
    }

    @CallSuper
    public void onConfigurationChanged(Configuration newConfig) {
        Object[] callbacks = collectComponentCallbacks();
        if (callbacks != null) {
            throw new RuntimeException("Stub!");
        }
    }

    @CallSuper
    public void onLowMemory() {
        Object[] callbacks = collectComponentCallbacks();
        if (callbacks != null) {
            throw new RuntimeException("Stub!");
        }
    }

    @CallSuper
    public void onTrimMemory(int level) {
        Object[] callbacks = collectComponentCallbacks();
        if (callbacks != null) {
            throw new RuntimeException("Stub!");
        }
    }

    public void registerComponentCallbacks(ComponentCallbacks callback) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        throw new RuntimeException("Stub!");
    }

    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        throw new RuntimeException("Stub!");
    }

    public void registerOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
        throw new RuntimeException("Stub!");
    }

    public static String getProcessName() {
        throw new RuntimeException("Stub!");
    }

    private Object[] collectComponentCallbacks() {
        throw new RuntimeException("Stub!");
    }
}