
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public final class SigningInfo implements Parcelable {

    public SigningInfo() {
        super();
        throw new RuntimeException("Stub!");
    }

    public SigningInfo(SigningInfo orig) {
        throw new RuntimeException("Stub!");
    }

    public boolean hasMultipleSigners() {
        throw new RuntimeException("Stub!");
    }

    public boolean hasPastSigningCertificates() {
        throw new RuntimeException("Stub!");
    }

    public Signature[] getSigningCertificateHistory() {
        throw new RuntimeException("Stub!");
    }

    public Signature[] getApkContentsSigners() {
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

    public static final Parcelable.Creator<SigningInfo> CREATOR = new Parcelable.Creator<SigningInfo>() {
        @Override
        public SigningInfo createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public SigningInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}
