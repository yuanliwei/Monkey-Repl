package android.bluetooth;


/** BluetoothDevice stub（编译期占位，运行时用真机类） */
public class BluetoothDevice {

    public static final int BOND_NONE = 10;
    public static final int BOND_BONDING = 11;
    public static final int BOND_BONDED = 12;

    public static final int DEVICE_TYPE_UNKNOWN = 0;
    public static final int DEVICE_TYPE_CLASSIC = 1;
    public static final int DEVICE_TYPE_LE = 2;
    public static final int DEVICE_TYPE_DUAL = 3;

    public BluetoothDevice(String address) {
        throw new RuntimeException("Stub!");
    }

    public String getAddress() {
        throw new RuntimeException("Stub!");
    }

    public int getAddressType() {
        throw new RuntimeException("Stub!");
    }

    public String getName() {
        throw new RuntimeException("Stub!");
    }

    public String getAlias() {
        throw new RuntimeException("Stub!");
    }

    public BluetoothClass getBluetoothClass() {
        throw new RuntimeException("Stub!");
    }

    public int getBondState() {
        throw new RuntimeException("Stub!");
    }

    public int getDeviceType() {
        throw new RuntimeException("Stub!");
    }
}
