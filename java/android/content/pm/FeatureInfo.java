
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class FeatureInfo implements Parcelable {
    public String name;

    public int version;

    public static final int GL_ES_VERSION_UNDEFINED = 0;

    public int reqGlEsVersion;

    public static final int FLAG_REQUIRED = 0x0001;

    public int flags;

    public FeatureInfo() {
        throw new RuntimeException("Stub!");
    }

    public FeatureInfo(FeatureInfo orig) {
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
    public void writeToParcel(Parcel dest, int parcelableFlags) {
        throw new RuntimeException("Stub!");
    }

    public static final Creator<FeatureInfo> CREATOR = new Creator<FeatureInfo>() {
        @Override
        public FeatureInfo createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public FeatureInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    public String getGlEsVersion() {
        throw new RuntimeException("Stub!");
    }
}
