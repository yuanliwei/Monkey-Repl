package android.graphics;

import java.awt.color.ColorSpace;
import java.io.OutputStream;
import java.nio.Buffer;

import android.annotation.CheckResult;
import android.annotation.ColorInt;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.WorkerThread;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;

public final class Bitmap implements Parcelable {
    public static final int DENSITY_NONE = 0;
    public int mDensity = 3;
    public static volatile int sPreloadTracingNumInstantiatedBitmaps;
    public static volatile long sPreloadTracingTotalBitmapsSize;

    public static void setDefaultDensity(int density) {
        throw new RuntimeException("Stub!");
    }

    public long getNativeInstance() {
        throw new RuntimeException("Stub!");
    }

    public int getDensity() {
        throw new RuntimeException("Stub!");
    }

    public void setDensity(int density) {
        throw new RuntimeException("Stub!");
    }

    public void reconfigure(int width, int height, Config config) {
        throw new RuntimeException("Stub!");
    }

    public void setWidth(int width) {
        throw new RuntimeException("Stub!");
    }

    public void setHeight(int height) {
        throw new RuntimeException("Stub!");
    }

    public void setConfig(Config config) {
        throw new RuntimeException("Stub!");
    }

    public void setNinePatchChunk(byte[] chunk) {
        throw new RuntimeException("Stub!");
    }

    public void recycle() {
        throw new RuntimeException("Stub!");
    }

    public final boolean isRecycled() {
        throw new RuntimeException("Stub!");
    }

    public int getGenerationId() {
        throw new RuntimeException("Stub!");
    }

    public enum Config {
        ALPHA_8(1), RGB_565(3), @Deprecated
        ARGB_4444(4), ARGB_8888(5), RGBA_F16(6), HARDWARE(7);
        final int nativeInt;

        Config(int ni) {
            this.nativeInt = ni;
        }

        static Config nativeToConfig(int ni) {
            throw new RuntimeException("Stub!");
        }
    }

    public void copyPixelsToBuffer(Buffer dst) {
        throw new RuntimeException("Stub!");
    }

    public void copyPixelsFromBuffer(Buffer src) {
        throw new RuntimeException("Stub!");
    }

    public Bitmap copy(Config config, boolean isMutable) {
        throw new RuntimeException("Stub!");
    }

    public Bitmap createAshmemBitmap() {
        throw new RuntimeException("Stub!");
    }

    public Bitmap createAshmemBitmap(Config config) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createScaledBitmap(@NonNull Bitmap src, int dstWidth, int dstHeight, boolean filter) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(@NonNull Bitmap src) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(@NonNull Bitmap source, int x, int y, int width, int height) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(@NonNull Bitmap source, int x, int y, int width, int height, @Nullable Matrix m,
            boolean filter) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(int width, int height, @NonNull Config config) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(@Nullable DisplayMetrics display, int width, int height, @NonNull Config config) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(int width, int height, @NonNull Config config, boolean hasAlpha) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(int width, int height, @NonNull Config config, boolean hasAlpha,
            @NonNull ColorSpace colorSpace) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(@Nullable DisplayMetrics display, int width, int height, @NonNull Config config,
            boolean hasAlpha) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(@Nullable DisplayMetrics display, int width, int height, @NonNull Config config,
            boolean hasAlpha, @NonNull ColorSpace colorSpace) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(@NonNull @ColorInt int[] colors, int offset, int stride, int width, int height,
            @NonNull Config config) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(@NonNull DisplayMetrics display, @NonNull @ColorInt int[] colors, int offset,
            int stride, int width, int height, @NonNull Config config) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(@NonNull @ColorInt int[] colors, int width, int height, Config config) {
        throw new RuntimeException("Stub!");
    }

    public static Bitmap createBitmap(@Nullable DisplayMetrics display, @NonNull @ColorInt int colors[], int width,
            int height, @NonNull Config config) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getNinePatchChunk() {
        throw new RuntimeException("Stub!");
    }

    public void getOpticalInsets(@NonNull Rect outInsets) {
        throw new RuntimeException("Stub!");
    }

    public enum CompressFormat {
        JPEG(0), PNG(1), WEBP(2);
        CompressFormat(int nativeInt) {
            throw new RuntimeException("Stub!");
        }

        final int nativeInt;
    }

    @WorkerThread
    public boolean compress(CompressFormat format, int quality, OutputStream stream) {
        throw new RuntimeException("Stub!");
    }

    public final boolean isMutable() {
        throw new RuntimeException("Stub!");
    }

    public final void makeImmutable() {
        throw new RuntimeException("Stub!");
    }

    public final boolean isPremultiplied() {
        throw new RuntimeException("Stub!");
    }

    public final void setPremultiplied(boolean premultiplied) {
        throw new RuntimeException("Stub!");
    }

    public final int getWidth() {
        throw new RuntimeException("Stub!");
    }

    public final int getHeight() {
        throw new RuntimeException("Stub!");
    }

    public int getScaledWidth(Canvas canvas) {
        throw new RuntimeException("Stub!");
    }

    public int getScaledHeight(Canvas canvas) {
        throw new RuntimeException("Stub!");
    }

    public int getScaledWidth(DisplayMetrics metrics) {
        throw new RuntimeException("Stub!");
    }

    public int getScaledHeight(DisplayMetrics metrics) {
        throw new RuntimeException("Stub!");
    }

    public int getScaledWidth(int targetDensity) {
        throw new RuntimeException("Stub!");
    }

    public int getScaledHeight(int targetDensity) {
        throw new RuntimeException("Stub!");
    }

    static public int scaleFromDensity(int size, int sdensity, int tdensity) {
        throw new RuntimeException("Stub!");
    }

    public final int getRowBytes() {
        throw new RuntimeException("Stub!");
    }

    public final int getByteCount() {
        throw new RuntimeException("Stub!");
    }

    public final int getAllocationByteCount() {
        throw new RuntimeException("Stub!");
    }

    public final Config getConfig() {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasAlpha() {
        throw new RuntimeException("Stub!");
    }

    public void setHasAlpha(boolean hasAlpha) {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasMipMap() {
        throw new RuntimeException("Stub!");
    }

    public final void setHasMipMap(boolean hasMipMap) {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    public final ColorSpace getColorSpace() {
        throw new RuntimeException("Stub!");
    }

    public void eraseColor(@ColorInt int c) {
        throw new RuntimeException("Stub!");
    }

    @ColorInt
    public int getPixel(int x, int y) {
        throw new RuntimeException("Stub!");
    }

    public void getPixels(@ColorInt int[] pixels, int offset, int stride, int x, int y, int width, int height) {
        throw new RuntimeException("Stub!");
    }

    public void setPixel(int x, int y, @ColorInt int color) {
        throw new RuntimeException("Stub!");
    }

    public void setPixels(@ColorInt int[] pixels, int offset, int stride, int x, int y, int width, int height) {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<Bitmap> CREATOR = new Parcelable.Creator<Bitmap>() {
        public Bitmap createFromParcel(Parcel p) {
            throw new RuntimeException("Stub!");
        }

        public Bitmap[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel p, int flags) {
        throw new RuntimeException("Stub!");
    }

    @CheckResult
    public Bitmap extractAlpha() {
        throw new RuntimeException("Stub!");
    }

    @CheckResult
    public Bitmap extractAlpha(Paint paint, int[] offsetXY) {
        throw new RuntimeException("Stub!");
    }

    public boolean sameAs(Bitmap other) {
        throw new RuntimeException("Stub!");
    }

    public void prepareToDraw() {
        throw new RuntimeException("Stub!");
    }
}