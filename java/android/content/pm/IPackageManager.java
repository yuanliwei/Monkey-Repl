package android.content.pm;

import android.content.Intent;
import android.os.Parcelable;
import android.os.RemoteException;

public interface IPackageManager extends android.os.IInterface {

    public static abstract class Stub extends android.os.Binder implements IPackageManager {

        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IPackageManager asInterface(android.os.IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public android.os.IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    public ResolveInfo resolveIntent(Intent intent, String type, int i, int myUserId);

    ParceledListSlice<Parcelable> queryIntentActivities(Intent intent, String resolvedType, int flags, int userId);

    public int getPermissionFlags(String name, String pkg, int myUserId);

    public ParceledListSlice<Parcelable> getInstalledPackages(int flags, int userId);

    public PermissionInfo getPermissionInfo(String perm, String string, int i);

    public ApplicationInfo getApplicationInfo(String packageName, int i, int myUserId) throws RemoteException;

    public int checkPermission(String name, String mPkg, int myUserId) throws RemoteException;

    public void grantRuntimePermission(String mPkg, String name, int myUserId);

    public void revokeRuntimePermission(String mPkg, String name, int myUserId);

}
