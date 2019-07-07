
package android.view;

import android.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class InputEvent implements Parcelable {
    protected static final int PARCEL_TOKEN_MOTION_EVENT = 1;
    protected static final int PARCEL_TOKEN_KEY_EVENT = 2;

    protected int mSeq;

    protected boolean mRecycled;

    public abstract int getDeviceId();

    public final InputDevice getDevice() {
        throw new RuntimeException("Stub!");
    }

    public abstract int getSource();

    public abstract void setSource(int source);

    public boolean isFromSource(int source) {
        throw new RuntimeException("Stub!");
    }

    public abstract InputEvent copy();

    public void recycle() {
        throw new RuntimeException("Stub!");
    }

    public void recycleIfNeededAfterDispatch() {
        throw new RuntimeException("Stub!");
    }

    protected void prepareForReuse() {
        throw new RuntimeException("Stub!");
    }

    public abstract boolean isTainted();

    public abstract void setTainted(boolean tainted);

    public abstract long getEventTime();

    public abstract long getEventTimeNano();

    public abstract void cancel();

    @UnsupportedAppUsage
    public int getSequenceNumber() {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<InputEvent> CREATOR = new Parcelable.Creator<InputEvent>() {
        public InputEvent createFromParcel(Parcel in) {
            throw new RuntimeException("Stub!");
        }

        public InputEvent[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}
