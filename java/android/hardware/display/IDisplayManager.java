package android.hardware.display;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.view.DisplayInfo;

/** IDisplayManager stub（编译期占位，运行时用真机类） */
public interface IDisplayManager extends IInterface {

    public static abstract class Stub extends Binder implements IDisplayManager {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IDisplayManager asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    float getBrightness(int displayId) throws RemoteException;

    void setBrightness(int displayId, float brightness) throws RemoteException;

    DisplayInfo getDisplayInfo(int displayId) throws RemoteException;

    int[] getDisplayIds(boolean includeDisabled) throws RemoteException;
}
