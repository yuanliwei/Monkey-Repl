package android.net.wifi;

import android.net.IpConfiguration;
import java.util.BitSet;

public class WifiConfiguration {
    public String SSID;
    public String BSSID;
    public String preSharedKey;
    public String[] wepKeys;
    public int wepTxKeyIndex;
    public int networkId;
    public int status;
    public int priority;
    public boolean hiddenSSID;
    public BitSet allowedKeyManagement;
    public BitSet allowedProtocols;
    public BitSet allowedAuthAlgorithms;
    public BitSet allowedGroupCiphers;
    public BitSet allowedPairwiseCiphers;
    public IpConfiguration mIpConfiguration;

    public WifiConfiguration() {
        throw new RuntimeException("Stub!");
    }

    public IpConfiguration getIpConfiguration() {
        throw new RuntimeException("Stub!");
    }
}
