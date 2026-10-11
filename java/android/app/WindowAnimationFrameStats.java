package android.app;

import android.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.FrameStats;

public final class WindowAnimationFrameStats extends FrameStats implements Parcelable {
    public WindowAnimationFrameStats() {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public void init(long refreshPeriodNano, long[] framesPresentedTimeNano) {
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

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public static final Creator<WindowAnimationFrameStats> CREATOR = new Creator<WindowAnimationFrameStats>() {
        @Override
        public WindowAnimationFrameStats createFromParcel(Parcel parcel) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public WindowAnimationFrameStats[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}
