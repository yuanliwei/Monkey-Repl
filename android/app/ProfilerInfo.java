
package android.app;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

public class ProfilerInfo implements Parcelable {

    public final String profileFile;

    public ParcelFileDescriptor profileFd;

    public final int samplingInterval;

    public final boolean autoStopProfiler;

    public final boolean streamingOutput;

    public final String agent;

    public final boolean attachAgentDuringBind;

    public ProfilerInfo(String filename, ParcelFileDescriptor fd, int interval, boolean autoStop, boolean streaming,
            String agent, boolean attachAgentDuringBind) {
        throw new RuntimeException("Stub!");
    }

    public ProfilerInfo(ProfilerInfo in) {
        throw new RuntimeException("Stub!");
    }

    public ProfilerInfo setAgent(String agent, boolean attachAgentDuringBind) {
        throw new RuntimeException("Stub!");
    }

    public void closeFd() {
        if (profileFd != null) {
            throw new RuntimeException("Stub!");
        }
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<ProfilerInfo> CREATOR = new Parcelable.Creator<ProfilerInfo>() {
        @Override
        public ProfilerInfo createFromParcel(Parcel in) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public ProfilerInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    @Override
    public boolean equals(Object o) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Stub!");
    }
}
