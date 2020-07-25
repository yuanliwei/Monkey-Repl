
package android.content.pm;

import android.annotation.Nullable;
import android.os.Parcel;
import android.os.Parcelable;

public class PackageInfo implements Parcelable {
    public String packageName;

    public String[] splitNames;

    @Deprecated
    public int versionCode;

    public int versionCodeMajor;

    public long getLongVersionCode() {
        throw new RuntimeException("Stub!");
    }

    public void setLongVersionCode(long longVersionCode) {
        throw new RuntimeException("Stub!");
    }

    public static long composeLongVersionCode(int major, int minor) {
        throw new RuntimeException("Stub!");
    }

    public String versionName;

    public int baseRevisionCode;

    public int[] splitRevisionCodes;

    public String sharedUserId;

    public int sharedUserLabel;

    public ApplicationInfo applicationInfo;

    public long firstInstallTime;

    public long lastUpdateTime;

    public int[] gids;

    public ActivityInfo[] activities;

    public ActivityInfo[] receivers;

    public ServiceInfo[] services;

    public ProviderInfo[] providers;

    public InstrumentationInfo[] instrumentation;

    public PermissionInfo[] permissions;

    public String[] requestedPermissions;

    public int[] requestedPermissionsFlags;

    public static final int REQUESTED_PERMISSION_REQUIRED = 1 << 0;

    public static final int REQUESTED_PERMISSION_GRANTED = 1 << 1;

    public Signature[] signatures;

    public SigningInfo signingInfo;

    public ConfigurationInfo[] configPreferences;

    public FeatureInfo[] reqFeatures;

    public FeatureGroupInfo[] featureGroups;

    public static final int INSTALL_LOCATION_UNSPECIFIED = -1;

    public static final int INSTALL_LOCATION_AUTO = 0;

    public static final int INSTALL_LOCATION_INTERNAL_ONLY = 1;

    public static final int INSTALL_LOCATION_PREFER_EXTERNAL = 2;

    public int installLocation = INSTALL_LOCATION_INTERNAL_ONLY;

    public boolean isStub;

    public boolean coreApp;

    public boolean requiredForAllUsers;

    public String restrictedAccountType;

    public String requiredAccountType;

    public String overlayTarget;

    public String overlayCategory;

    public int overlayPriority;

    boolean mOverlayIsStatic;

    public int compileSdkVersion;

    @Nullable
    public String compileSdkVersionCodename;

    public boolean isApex;

    public PackageInfo() {
        throw new RuntimeException("Stub!");
    }

    public boolean isOverlayPackage() {
        throw new RuntimeException("Stub!");
    }

    public boolean isStaticOverlayPackage() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        return "PackageInfo{" + Integer.toHexString(System.identityHashCode(this)) + " " + packageName + "}";
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int parcelableFlags) {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<PackageInfo> CREATOR = new Parcelable.Creator<PackageInfo>() {
        @Override
        public PackageInfo createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public PackageInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

}
