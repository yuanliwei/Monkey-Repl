package android.app;

import java.util.List;

import android.content.ComponentName;
import android.content.IIntentReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public interface IActivityManager extends android.os.IInterface {

    public static abstract class Stub extends android.os.Binder implements IActivityManager {

        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IActivityManager asInterface(android.os.IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public android.os.IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    void setActivityController(IActivityController watcher, boolean imAMonkey);

    void setActivityController(IActivityController watcher);

    Intent registerReceiver(IApplicationThread caller, String callerPackage, IIntentReceiver receiver,
            IntentFilter filter, String requiredPermission, int userId, int flags);

    void unregisterReceiver(IIntentReceiver receiver);

    void signalPersistentProcesses(int signalUsr1);

    int startActivity(IApplicationThread caller, String callingPackage, Intent intent, String resolvedType,
            IBinder resultTo, String resultWho, int requestCode, int flags, ProfilerInfo profilerInfo, Bundle options)
            throws RemoteException;

    boolean startInstrumentation(ComponentName className, String profileFile, int flags, Bundle arguments,
            IInstrumentationWatcher watcher, IUiAutomationConnection connection, int userId, String abiOverride)
            throws RemoteException;

    List<ActivityManager.RunningTaskInfo> getTasks(int maxNum);

    List<ActivityManager.RunningTaskInfo> getTasks(int maxNum, int flags);

    public List<ActivityManager.RunningTaskInfo> getTasks(int maxNum, int flags, IThumbnailReceiver receiver)
            throws RemoteException;
}
