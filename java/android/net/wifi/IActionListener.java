package android.net.wifi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

public interface IActionListener extends IInterface {

    public static abstract class Stub extends Binder implements IActionListener {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IActionListener asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    void onSuccess();

    void onFailure(int reason);
}
