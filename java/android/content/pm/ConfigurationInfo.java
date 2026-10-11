
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class ConfigurationInfo implements Parcelable {
    public int reqTouchScreen;

    public int reqKeyboardType;

    public int reqNavigation;

    public static final int INPUT_FEATURE_HARD_KEYBOARD = 0x00000001;

    public static final int INPUT_FEATURE_FIVE_WAY_NAV = 0x00000002;

    public int reqInputFeatures = 0;

    public static final int GL_ES_VERSION_UNDEFINED = 0;
    public int reqGlEsVersion;

    public ConfigurationInfo() {
        throw new RuntimeException("Stub!");
    }

    public ConfigurationInfo(ConfigurationInfo orig) {
        throw new RuntimeException("Stub!");
    }

    public String toString() {
        return "ConfigurationInfo{" + Integer.toHexString(System.identityHashCode(this)) + " touchscreen = "
                + reqTouchScreen + " inputMethod = " + reqKeyboardType + " navigation = " + reqNavigation
                + " reqInputFeatures = " + reqInputFeatures + " reqGlEsVersion = " + reqGlEsVersion + "}";
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel dest, int parcelableFlags) {
        throw new RuntimeException("Stub!");
    }

    public static final Creator<ConfigurationInfo> CREATOR = new Creator<ConfigurationInfo>() {
        public ConfigurationInfo createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public ConfigurationInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    public String getGlEsVersion() {
        throw new RuntimeException("Stub!");
    }
}
