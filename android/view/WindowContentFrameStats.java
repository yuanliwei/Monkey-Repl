
package android.view;

import android.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public final class WindowContentFrameStats extends FrameStats implements Parcelable {

    public WindowContentFrameStats() {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public void init(long refreshPeriodNano, long[] framesPostedTimeNano, long[] framesPresentedTimeNano,
            long[] framesReadyTimeNano) {
        throw new RuntimeException("Stub!");
    }

    public long getFramePostedTimeNano(int index) {
        throw new RuntimeException("Stub!");
    }

    public long getFrameReadyTimeNano(int index) {
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

    public static final Parcelable.Creator<WindowContentFrameStats> CREATOR = new Creator<WindowContentFrameStats>() {
        @Override
        public WindowContentFrameStats createFromParcel(Parcel parcel) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public WindowContentFrameStats[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}
