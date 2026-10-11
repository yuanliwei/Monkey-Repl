package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/** IBluetoothManagerCallback stub（编译期占位，运行时用真机类） */
public interface IBluetoothManagerCallback extends IInterface {

    public static abstract class Stub extends Binder implements IBluetoothManagerCallback {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IBluetoothManagerCallback asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    void onBluetoothServiceUp(IBinder bluetoothService);

    void onBluetoothServiceDown();

    void onBluetoothOn();

    void onBluetoothOff();
}
