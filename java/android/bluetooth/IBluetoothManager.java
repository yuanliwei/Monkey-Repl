package android.bluetooth;

import android.content.AttributionSource;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/** IBluetoothManager stub（编译期占位，运行时用真机类） */
public interface IBluetoothManager extends IInterface {

    public static abstract class Stub extends Binder implements IBluetoothManager {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IBluetoothManager asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    boolean enable(AttributionSource source);

    boolean enableNoAutoConnect(AttributionSource source);

    boolean disable(AttributionSource source, boolean persist);

    int getState();

    String getAddress(AttributionSource source);

    String getName(AttributionSource source);

    IBinder registerAdapter(IBluetoothManagerCallback callback);

    void unregisterAdapter(IBluetoothManagerCallback callback);
}
