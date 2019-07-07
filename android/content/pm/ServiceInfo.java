
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceInfo extends ComponentInfo implements Parcelable {
    public String permission;

    public static final int FLAG_STOP_WITH_TASK = 0x0001;

    public static final int FLAG_ISOLATED_PROCESS = 0x0002;

    public static final int FLAG_EXTERNAL_SERVICE = 0x0004;

    public static final int FLAG_VISIBLE_TO_INSTANT_APP = 0x100000;

    public static final int FLAG_SINGLE_USER = 0x40000000;

    public int flags;

    public ServiceInfo() {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public ServiceInfo(ServiceInfo orig) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public String toString() {
        return "ServiceInfo{" + Integer.toHexString(System.identityHashCode(this)) + " " + name + "}";
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel dest, int parcelableFlags) {
        throw new RuntimeException("Stub!");
    }

    public static final Creator<ServiceInfo> CREATOR = new Creator<ServiceInfo>() {
        public ServiceInfo createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public ServiceInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    private ServiceInfo(Parcel source) {
        super(null);
        throw new RuntimeException("Stub!");
    }
}
