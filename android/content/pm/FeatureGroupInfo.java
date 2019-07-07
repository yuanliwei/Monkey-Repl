package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public final class FeatureGroupInfo implements Parcelable {

    public FeatureInfo[] features;

    public FeatureGroupInfo() {
        throw new RuntimeException("Stub!");
    }

    public FeatureGroupInfo(FeatureGroupInfo other) {
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

    public static final Creator<FeatureGroupInfo> CREATOR = new Creator<FeatureGroupInfo>() {
        @Override
        public FeatureGroupInfo createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public FeatureGroupInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}
