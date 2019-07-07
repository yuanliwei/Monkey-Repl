
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.PatternMatcher;

public final class ProviderInfo extends ComponentInfo implements Parcelable {

    public String authority = null;

    public String readPermission = null;

    public String writePermission = null;

    public boolean grantUriPermissions = false;

    public PatternMatcher[] uriPermissionPatterns = null;

    public PathPermission[] pathPermissions = null;

    public boolean multiprocess = false;

    public int initOrder = 0;

    public static final int FLAG_VISIBLE_TO_INSTANT_APP = 0x100000;

    public static final int FLAG_SINGLE_USER = 0x40000000;

    public int flags = 0;

    @Deprecated
    public boolean isSyncable = false;

    public ProviderInfo() {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public ProviderInfo(ProviderInfo orig) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel out, int parcelableFlags) {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<ProviderInfo> CREATOR = new Parcelable.Creator<ProviderInfo>() {
        public ProviderInfo createFromParcel(Parcel in) {
            throw new RuntimeException("Stub!");
        }

        public ProviderInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    public String toString() {
        return "ContentProviderInfo{name=" + authority + " className=" + name + "}";
    }

    private ProviderInfo(Parcel in) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }
}
