package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/** IConnectivityManager stub（编译期占位，运行时用真机类） */
public interface IConnectivityManager extends IInterface {

    public static abstract class Stub extends Binder implements IConnectivityManager {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IConnectivityManager asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    NetworkInfo getActiveNetworkInfo() throws RemoteException;

    Network getActiveNetwork() throws RemoteException;

    Network[] getAllNetworks() throws RemoteException;

    boolean isActiveNetworkMetered() throws RemoteException;

    NetworkCapabilities getNetworkCapabilities(Network network, String callingPackageName, String callingAttributionTag) throws RemoteException;

    void setAirplaneMode(boolean enable) throws RemoteException;
}
