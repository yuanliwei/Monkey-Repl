
package android.net;

import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;

public class NetworkInfo implements Parcelable {

    public enum State {
        CONNECTING, CONNECTED, SUSPENDED, DISCONNECTING, DISCONNECTED, UNKNOWN
    }

    public enum DetailedState {
        IDLE, SCANNING, CONNECTING, AUTHENTICATING, OBTAINING_IPADDR, CONNECTED, SUSPENDED, DISCONNECTING, DISCONNECTED,
        FAILED, BLOCKED, VERIFYING_POOR_LINK, CAPTIVE_PORTAL_CHECK
    }

    public NetworkInfo(int type, int subtype, String typeName, String subtypeName) {
        throw new RuntimeException("Stub!");
    }

    public NetworkInfo(NetworkInfo source) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void setType(int type) {
        throw new RuntimeException("Stub!");
    }

    public int getSubtype() {
        throw new RuntimeException("Stub!");
    }

    public void setSubtype(int subtype, String subtypeName) {
        throw new RuntimeException("Stub!");
    }

    public String getTypeName() {
        throw new RuntimeException("Stub!");
    }

    public String getSubtypeName() {
        throw new RuntimeException("Stub!");
    }

    public boolean isConnectedOrConnecting() {
        throw new RuntimeException("Stub!");
    }

    public boolean isConnected() {
        throw new RuntimeException("Stub!");
    }

    public boolean isAvailable() {
        throw new RuntimeException("Stub!");
    }

    public void setIsAvailable(boolean isAvailable) {
        throw new RuntimeException("Stub!");
    }

    public boolean isFailover() {
        throw new RuntimeException("Stub!");
    }

    public void setFailover(boolean isFailover) {
        throw new RuntimeException("Stub!");
    }

    public boolean isRoaming() {
        throw new RuntimeException("Stub!");
    }

    public void setRoaming(boolean isRoaming) {
        throw new RuntimeException("Stub!");
    }

    public State getState() {
        throw new RuntimeException("Stub!");
    }

    public @NonNull DetailedState getDetailedState() {
        throw new RuntimeException("Stub!");
    }

    public void setDetailedState(DetailedState detailedState, String reason, String extraInfo) {
        throw new RuntimeException("Stub!");
    }

    public void setExtraInfo(String extraInfo) {
        throw new RuntimeException("Stub!");
    }

    public String getReason() {
        throw new RuntimeException("Stub!");
    }

    public String getExtraInfo() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static final Creator<NetworkInfo> CREATOR = new Creator<NetworkInfo>() {
        @Override
        public NetworkInfo createFromParcel(Parcel in) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public NetworkInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}
