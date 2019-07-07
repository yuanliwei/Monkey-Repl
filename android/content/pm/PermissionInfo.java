
package android.content.pm;

import android.annotation.IntDef;
import android.annotation.SystemApi;
import android.annotation.TestApi;
import android.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PermissionInfo extends PackageItemInfo implements Parcelable {
    public static final int PROTECTION_NORMAL = 0;

    public static final int PROTECTION_DANGEROUS = 1;

    public static final int PROTECTION_SIGNATURE = 2;

    @Deprecated
    public static final int PROTECTION_SIGNATURE_OR_SYSTEM = 3;

    @IntDef(flag = false, prefix = { "PROTECTION_" }, value = { PROTECTION_NORMAL, PROTECTION_DANGEROUS,
            PROTECTION_SIGNATURE, PROTECTION_SIGNATURE_OR_SYSTEM, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Protection {
    }

    public static final int PROTECTION_FLAG_PRIVILEGED = 0x10;

    @Deprecated
    public static final int PROTECTION_FLAG_SYSTEM = 0x10;

    public static final int PROTECTION_FLAG_DEVELOPMENT = 0x20;

    public static final int PROTECTION_FLAG_APPOP = 0x40;

    public static final int PROTECTION_FLAG_PRE23 = 0x80;

    public static final int PROTECTION_FLAG_INSTALLER = 0x100;

    public static final int PROTECTION_FLAG_VERIFIER = 0x200;

    public static final int PROTECTION_FLAG_PREINSTALLED = 0x400;

    public static final int PROTECTION_FLAG_SETUP = 0x800;

    public static final int PROTECTION_FLAG_INSTANT = 0x1000;

    public static final int PROTECTION_FLAG_RUNTIME_ONLY = 0x2000;

    @SystemApi
    public static final int PROTECTION_FLAG_OEM = 0x4000;

    @TestApi
    public static final int PROTECTION_FLAG_VENDOR_PRIVILEGED = 0x8000;

    @SystemApi
    @TestApi
    public static final int PROTECTION_FLAG_SYSTEM_TEXT_CLASSIFIER = 0x10000;

    @IntDef(flag = true, prefix = { "PROTECTION_FLAG_" }, value = { PROTECTION_FLAG_PRIVILEGED, PROTECTION_FLAG_SYSTEM,
            PROTECTION_FLAG_DEVELOPMENT, PROTECTION_FLAG_APPOP, PROTECTION_FLAG_PRE23, PROTECTION_FLAG_INSTALLER,
            PROTECTION_FLAG_VERIFIER, PROTECTION_FLAG_PREINSTALLED, PROTECTION_FLAG_SETUP, PROTECTION_FLAG_INSTANT,
            PROTECTION_FLAG_RUNTIME_ONLY, PROTECTION_FLAG_OEM, PROTECTION_FLAG_VENDOR_PRIVILEGED,
            PROTECTION_FLAG_SYSTEM_TEXT_CLASSIFIER, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProtectionFlags {
    }

    @Deprecated
    public static final int PROTECTION_MASK_BASE = 0xf;

    @Deprecated
    public static final int PROTECTION_MASK_FLAGS = 0xfff0;

    @Deprecated
    public int protectionLevel;

    public String group;

    public static final int FLAG_COSTS_MONEY = 1 << 0;

    @SystemApi
    public static final int FLAG_REMOVED = 1 << 1;

    public static final int FLAG_INSTALLED = 1 << 30;

    public static final Creator<PermissionInfo> CREATOR = new Creator<PermissionInfo>() {
        @Override
        public PermissionInfo createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public PermissionInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
    public int flags;

    public int descriptionRes;

    @SystemApi
    public int requestRes;

    public CharSequence nonLocalizedDescription;

    public static int fixProtectionLevel(int level) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public static String protectionToString(int level) {
        throw new RuntimeException("Stub!");
    }

    public PermissionInfo() {
        throw new RuntimeException("Stub!");
    }

    public PermissionInfo(PermissionInfo orig) {
        throw new RuntimeException("Stub!");
    }

    public CharSequence loadDescription(PackageManager pm) {

        if (descriptionRes != 0) {
            throw new RuntimeException("Stub!");
        }
        return null;
    }

    @Protection
    public int getProtection() {
        throw new RuntimeException("Stub!");
    }

    @ProtectionFlags
    public int getProtectionFlags() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        return "PermissionInfo{" + Integer.toHexString(System.identityHashCode(this)) + " " + name + "}";
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

}
