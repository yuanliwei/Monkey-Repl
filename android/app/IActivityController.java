package android.app;

import android.content.Intent;

public interface IActivityController extends android.os.IInterface {

    public static abstract class Stub extends android.os.Binder implements IActivityController {

        public Stub() {
        throw new RuntimeException("Stub!");
    }

        public static IActivityController asInterface(android.os.IBinder obj) {
        throw new RuntimeException("Stub!");
    }

        @Override
        public android.os.IBinder asBinder() {
        throw new RuntimeException("Stub!");
    }
    }

    boolean activityStarting(Intent intent, String pkg);
    
    boolean activityResuming(String pkg);
    
    boolean appCrashed(String processName, int pid,
            String shortMsg, String longMsg,
            long timeMillis, String stackTrace);
    
    int appEarlyNotResponding(String processName, int pid, String annotation);

    int appNotResponding(String processName, int pid, String processStats);

    int systemNotResponding(String msg);
}
