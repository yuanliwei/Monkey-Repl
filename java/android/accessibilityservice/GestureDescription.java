package android.accessibilityservice;

import java.util.List;

import android.annotation.IntRange;
import android.annotation.NonNull;
import android.graphics.Path;
import android.os.Parcel;
import android.os.Parcelable;

public final class GestureDescription {
    public static int getMaxStrokeCount() {
        throw new RuntimeException("Stub!");
    }

    public static long getMaxGestureDuration() {
        throw new RuntimeException("Stub!");
    }

    private GestureDescription() {
        throw new RuntimeException("Stub!");
    }

    private GestureDescription(List<StrokeDescription> strokes) {
        throw new RuntimeException("Stub!");
    }

    public int getStrokeCount() {
        throw new RuntimeException("Stub!");
    }

    public StrokeDescription getStroke(@IntRange(from = 0) int index) {
        throw new RuntimeException("Stub!");
    }

    public static class Builder {
        public Builder addStroke(@NonNull StrokeDescription strokeDescription) {
            throw new RuntimeException("Stub!");
        }

        public GestureDescription build() {
            throw new RuntimeException("Stub!");
        }
    }

    public static class StrokeDescription {

        public StrokeDescription(@NonNull Path path, @IntRange(from = 0) long startTime,
                @IntRange(from = 0) long duration) {
            throw new RuntimeException("Stub!");
        }

        public StrokeDescription(@NonNull Path path, @IntRange(from = 0) long startTime,
                @IntRange(from = 0) long duration, boolean willContinue) {
            throw new RuntimeException("Stub!");
        }

        public Path getPath() {
            throw new RuntimeException("Stub!");
        }

        public long getStartTime() {
            throw new RuntimeException("Stub!");
        }

        public long getDuration() {
            throw new RuntimeException("Stub!");
        }

        public int getId() {
            throw new RuntimeException("Stub!");
        }

        public StrokeDescription continueStroke(Path path, long startTime, long duration, boolean willContinue) {
            throw new RuntimeException("Stub!");
        }

        public boolean willContinue() {
            throw new RuntimeException("Stub!");
        }

        public int getContinuedStrokeId() {
            throw new RuntimeException("Stub!");
        }

        float getLength() {
            throw new RuntimeException("Stub!");
        }

        boolean getPosForTime(long time, float[] pos) {
            throw new RuntimeException("Stub!");
        }

        boolean hasPointForTime(long time) {
            throw new RuntimeException("Stub!");
        }
    }

    public static class TouchPoint implements Parcelable {
        public int mStrokeId;
        public int mContinuedStrokeId;
        public boolean mIsStartOfPath;
        public boolean mIsEndOfPath;
        public float mX;
        public float mY;

        public TouchPoint() {
            throw new RuntimeException("Stub!");
        }

        public TouchPoint(TouchPoint pointToCopy) {
            throw new RuntimeException("Stub!");
        }

        public TouchPoint(Parcel parcel) {
            throw new RuntimeException("Stub!");
        }

        public void copyFrom(TouchPoint other) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public String toString() {
            return "TouchPoint{" + "mStrokeId=" + mStrokeId + ", mContinuedStrokeId=" + mContinuedStrokeId
                    + ", mIsStartOfPath=" + mIsStartOfPath + ", mIsEndOfPath=" + mIsEndOfPath + ", mX=" + mX + ", mY="
                    + mY + '}';
        }

        @Override
        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            throw new RuntimeException("Stub!");
        }

        public static final Parcelable.Creator<TouchPoint> CREATOR = new Parcelable.Creator<TouchPoint>() {
            public TouchPoint createFromParcel(Parcel in) {
                throw new RuntimeException("Stub!");
            }

            public TouchPoint[] newArray(int size) {
                throw new RuntimeException("Stub!");
            }
        };
    }

    public static class GestureStep implements Parcelable {
        public long timeSinceGestureStart;
        public int numTouchPoints;
        public TouchPoint[] touchPoints;

        public GestureStep(long timeSinceGestureStart, int numTouchPoints, TouchPoint[] touchPointsToCopy) {
            throw new RuntimeException("Stub!");
        }

        public GestureStep(Parcel parcel) {
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

        public static final Parcelable.Creator<GestureStep> CREATOR = new Parcelable.Creator<GestureStep>() {
            public GestureStep createFromParcel(Parcel in) {
                throw new RuntimeException("Stub!");
            }

            public GestureStep[] newArray(int size) {
                throw new RuntimeException("Stub!");
            }
        };
    }

    public static class MotionEventGenerator {
        public static List<GestureStep> getGestureStepsFromGestureDescription(GestureDescription description,
                int sampleTimeMs) {
            throw new RuntimeException("Stub!");
        }

    }
}