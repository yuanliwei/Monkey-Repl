package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

public class GraphicBuffer implements Parcelable {
    public static final int USAGE_SW_READ_NEVER = 0x0;
    public static final int USAGE_SW_READ_RARELY = 0x2;
    public static final int USAGE_SW_READ_OFTEN = 0x3;
    public static final int USAGE_SW_READ_MASK = 0xF;
    public static final int USAGE_SW_WRITE_NEVER = 0x0;
    public static final int USAGE_SW_WRITE_RARELY = 0x20;
    public static final int USAGE_SW_WRITE_OFTEN = 0x30;
    public static final int USAGE_SW_WRITE_MASK = 0xF0;
    public static final int USAGE_SOFTWARE_MASK = USAGE_SW_READ_MASK | USAGE_SW_WRITE_MASK;
    public static final int USAGE_PROTECTED = 0x4000;
    public static final int USAGE_HW_TEXTURE = 0x100;
    public static final int USAGE_HW_RENDER = 0x200;
    public static final int USAGE_HW_2D = 0x400;
    public static final int USAGE_HW_COMPOSER = 0x800;
    public static final int USAGE_HW_VIDEO_ENCODER = 0x10000;
    public static final int USAGE_HW_MASK = 0x71F00;

    public static GraphicBuffer create(int width, int height, int format, int usage) {
        throw new RuntimeException("Stub!");
    }

    private GraphicBuffer(int width, int height, int format, int usage, long nativeObject) {
        throw new RuntimeException("Stub!");
    }

    public static GraphicBuffer createFromExisting(int width, int height, int format, int usage,
            long unwrappedNativeObject) {
        throw new RuntimeException("Stub!");
    }

    public int getWidth() {
        throw new RuntimeException("Stub!");
    }

    public int getHeight() {
        throw new RuntimeException("Stub!");
    }

    public int getFormat() {
        throw new RuntimeException("Stub!");
    }

    public int getUsage() {
        throw new RuntimeException("Stub!");
    }

    public Canvas lockCanvas() {
        throw new RuntimeException("Stub!");
    }

    public Canvas lockCanvas(Rect dirty) {
        throw new RuntimeException("Stub!");
    }

    public void unlockCanvasAndPost(Canvas canvas) {
        throw new RuntimeException("Stub!");
    }

    public void destroy() {
        throw new RuntimeException("Stub!");
    }

    public boolean isDestroyed() {
        throw new RuntimeException("Stub!");
    }

    @Override
    protected void finalize() throws Throwable {
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

    public static final Parcelable.Creator<GraphicBuffer> CREATOR = new Parcelable.Creator<GraphicBuffer>() {
        public GraphicBuffer createFromParcel(Parcel in) {
            throw new RuntimeException("Stub!");
        }

        public GraphicBuffer[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}