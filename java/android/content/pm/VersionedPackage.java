package android.content.pm;

import android.annotation.IntRange;
import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class VersionedPackage implements Parcelable {

    @Retention(RetentionPolicy.SOURCE)
    @IntRange(from = PackageManager.VERSION_CODE_HIGHEST)
    public @interface VersionCode {
    }

    public VersionedPackage(@NonNull String packageName, @VersionCode int versionCode) {
        throw new RuntimeException("Stub!");
    }

    public VersionedPackage(@NonNull String packageName, @VersionCode long versionCode) {
        throw new RuntimeException("Stub!");
    }

    public @NonNull String getPackageName() {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public @VersionCode int getVersionCode() {
        throw new RuntimeException("Stub!");
    }

    public @VersionCode long getLongVersionCode() {
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
    public void writeToParcel(Parcel parcel, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static final Creator<VersionedPackage> CREATOR = new Creator<VersionedPackage>() {
        @Override
        public VersionedPackage createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public VersionedPackage[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}
