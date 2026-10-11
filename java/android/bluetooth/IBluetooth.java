package android.bluetooth;

import java.util.List;

import android.content.AttributionSource;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/** IBluetooth stub（编译期占位，运行时用真机类） */
public interface IBluetooth extends IInterface {

    public static abstract class Stub extends Binder implements IBluetooth {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IBluetooth asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    int getState();

    String getAddress(AttributionSource source);

    String getName(AttributionSource source);

    boolean setName(String name, AttributionSource source);

    boolean startDiscovery(AttributionSource source);

    boolean cancelDiscovery(AttributionSource source);

    boolean isDiscovering(AttributionSource source);

    List<BluetoothDevice> getBondedDevices(AttributionSource source);

    int getBondState(BluetoothDevice device, AttributionSource source);

    boolean createBond(BluetoothDevice device, int transport, AttributionSource source);

    boolean removeBond(BluetoothDevice device, AttributionSource source);

    boolean cancelBondProcess(BluetoothDevice device, AttributionSource source);

    int getConnectionState(BluetoothDevice device, AttributionSource source);

    String getRemoteName(BluetoothDevice device, AttributionSource source);

    String getRemoteAlias(BluetoothDevice device, AttributionSource source);

    int getRemoteClass(BluetoothDevice device, AttributionSource source);

    int getRemoteType(BluetoothDevice device, AttributionSource source);
}
