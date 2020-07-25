package android.content;

import android.os.Bundle;
import android.os.RemoteException;

public interface IIntentReceiver extends android.os.IInterface {

    public static abstract class Stub extends android.os.Binder implements IIntentReceiver {

        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IIntentReceiver asInterface(android.os.IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public android.os.IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    void performReceive(Intent intent, int resultCode, String data, Bundle extras, boolean ordered, boolean sticky,
            int sendingUser) throws RemoteException;
}