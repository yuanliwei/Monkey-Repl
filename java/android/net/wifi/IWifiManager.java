package android.net.wifi;

import android.net.DhcpInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;

import com.android.wifi.x.com.android.modules.utils.ParceledListSlice;

public interface IWifiManager extends IInterface {

    public static abstract class Stub extends Binder implements IWifiManager {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IWifiManager asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    int addOrUpdateNetwork(WifiConfiguration config, String packageName, Bundle bundle);

    ParceledListSlice<WifiConfiguration> getConfiguredNetworks(String packageName, String featureId, boolean flag);

    boolean setWifiEnabled(String packageName, boolean enable);

    WifiInfo getConnectionInfo(String packageName, String featureId);

    DhcpInfo getDhcpInfo(String packageName);

    boolean enableNetwork(int netId, boolean disableOthers, String packageName);

    boolean disableNetwork(int netId, String packageName);

    boolean reconnect(String packageName);

    boolean disconnect(String packageName);

    void save(WifiConfiguration config, IActionListener listener, String packageName);

    void connect(WifiConfiguration config, int netId, IActionListener listener, String packageName, Bundle bundle);

    void forget(int netId, IActionListener listener, String packageName);
}
