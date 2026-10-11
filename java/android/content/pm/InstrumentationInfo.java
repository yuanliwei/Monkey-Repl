
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

public class InstrumentationInfo extends PackageItemInfo implements Parcelable {
    public static final Parcelable.Creator<InstrumentationInfo> CREATOR = new Parcelable.Creator<InstrumentationInfo>() {
        public InstrumentationInfo createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public InstrumentationInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
    public String targetPackage;

    public String targetProcesses;

    public String sourceDir;

    public String publicSourceDir;

    public String[] splitNames;

    public String[] splitSourceDirs;

    public String[] splitPublicSourceDirs;

    public SparseArray<int[]> splitDependencies;

    public String dataDir;

    public String deviceProtectedDataDir;
    public String credentialProtectedDataDir;

    public String primaryCpuAbi;

    public String secondaryCpuAbi;

    public String nativeLibraryDir;

    public String secondaryNativeLibraryDir;

    public boolean handleProfiling;

    public boolean functionalTest;

    public InstrumentationInfo() {
        throw new RuntimeException("Stub!");
    }

    public InstrumentationInfo(InstrumentationInfo orig) {
        throw new RuntimeException("Stub!");
    }

    public String toString() {
        return "InstrumentationInfo{" + Integer.toHexString(System.identityHashCode(this)) + " " + packageName + "}";
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

}
