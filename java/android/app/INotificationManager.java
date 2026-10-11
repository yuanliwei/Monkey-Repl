package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.service.notification.StatusBarNotification;

/** INotificationManager stub（编译期占位，运行时用真机类） */
public interface INotificationManager extends IInterface {

    public static abstract class Stub extends Binder implements INotificationManager {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static INotificationManager asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    StatusBarNotification[] getActiveNotifications(String callingPkg) throws RemoteException;

    void cancelAllNotifications(String pkg, int userId) throws RemoteException;

    boolean areNotificationsEnabled(String pkg) throws RemoteException;
}
