package android.graphics;

import android.annotation.AnyThread;
import android.annotation.ColorInt;
import android.annotation.ColorLong;
import android.annotation.IntRange;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.Size;
import android.annotation.SuppressAutoDoc;

@AnyThread
@SuppressAutoDoc
public class Color {
    @ColorInt
    public static final int BLACK = 0xFF000000;
    @ColorInt
    public static final int DKGRAY = 0xFF444444;
    @ColorInt
    public static final int GRAY = 0xFF888888;
    @ColorInt
    public static final int LTGRAY = 0xFFCCCCCC;
    @ColorInt
    public static final int WHITE = 0xFFFFFFFF;
    @ColorInt
    public static final int RED = 0xFFFF0000;
    @ColorInt
    public static final int GREEN = 0xFF00FF00;
    @ColorInt
    public static final int BLUE = 0xFF0000FF;
    @ColorInt
    public static final int YELLOW = 0xFFFFFF00;
    @ColorInt
    public static final int CYAN = 0xFF00FFFF;
    @ColorInt
    public static final int MAGENTA = 0xFFFF00FF;
    @ColorInt
    public static final int TRANSPARENT = 0;

    public Color() {
        throw new RuntimeException("Stub!");
    }

    private Color(float r, float g, float b, float a) {
        throw new RuntimeException("Stub!");
    }

    public boolean isWideGamut() {
        throw new RuntimeException("Stub!");
    }

    public boolean isSrgb() {
        throw new RuntimeException("Stub!");
    }

    @IntRange(from = 4, to = 5)
    public int getComponentCount() {
        throw new RuntimeException("Stub!");
    }

    @ColorLong
    public long pack() {
        throw new RuntimeException("Stub!");
    }

    @ColorInt
    public int toArgb() {
        throw new RuntimeException("Stub!");
    }

    public float red() {
        throw new RuntimeException("Stub!");
    }

    public float green() {
        throw new RuntimeException("Stub!");
    }

    public float blue() {
        throw new RuntimeException("Stub!");
    }

    public float alpha() {
        throw new RuntimeException("Stub!");
    }

    @NonNull
    @Size(min = 4, max = 5)
    public float[] getComponents() {
        throw new RuntimeException("Stub!");
    }

    @NonNull
    @Size(min = 4)
    public float[] getComponents(@Nullable @Size(min = 4) float[] components) {
        throw new RuntimeException("Stub!");
    }

    public float getComponent(@IntRange(from = 0, to = 4) int component) {
        throw new RuntimeException("Stub!");
    }

    public float luminance() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean equals(Object o) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    @Override
    @NonNull
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public static float red(@ColorLong long color) {
        throw new RuntimeException("Stub!");
    }

    public static float green(@ColorLong long color) {
        throw new RuntimeException("Stub!");
    }

    public static float blue(@ColorLong long color) {
        throw new RuntimeException("Stub!");
    }

    public static float alpha(@ColorLong long color) {
        throw new RuntimeException("Stub!");
    }

    public static boolean isSrgb(@ColorLong long color) {
        throw new RuntimeException("Stub!");
    }

    public static boolean isWideGamut(@ColorLong long color) {
        throw new RuntimeException("Stub!");
    }

    @ColorInt
    public static int toArgb(@ColorLong long color) {
        throw new RuntimeException("Stub!");
    }

    @NonNull
    public static Color valueOf(@ColorInt int color) {
        throw new RuntimeException("Stub!");
    }

    @NonNull
    public static Color valueOf(@ColorLong long color) {
        throw new RuntimeException("Stub!");
    }

    @NonNull
    public static Color valueOf(float r, float g, float b) {
        throw new RuntimeException("Stub!");
    }

    @NonNull
    public static Color valueOf(float r, float g, float b, float a) {
        throw new RuntimeException("Stub!");
    }

    @ColorLong
    public static long pack(@ColorInt int color) {
        throw new RuntimeException("Stub!");
    }

    @ColorLong
    public static long pack(float red, float green, float blue) {
        throw new RuntimeException("Stub!");
    }

    @ColorLong
    public static long pack(float red, float green, float blue, float alpha) {
        throw new RuntimeException("Stub!");
    }

    public static float luminance(@ColorLong long color) {
        throw new RuntimeException("Stub!");
    }

    private static float saturate(float v) {
        throw new RuntimeException("Stub!");
    }

    @IntRange(from = 0, to = 255)
    public static int alpha(int color) {
        throw new RuntimeException("Stub!");
    }

    @IntRange(from = 0, to = 255)
    public static int red(int color) {
        throw new RuntimeException("Stub!");
    }

    @IntRange(from = 0, to = 255)
    public static int green(int color) {
        throw new RuntimeException("Stub!");
    }

    @IntRange(from = 0, to = 255)
    public static int blue(int color) {
        throw new RuntimeException("Stub!");
    }

    @ColorInt
    public static int rgb(@IntRange(from = 0, to = 255) int red, @IntRange(from = 0, to = 255) int green,
            @IntRange(from = 0, to = 255) int blue) {
        throw new RuntimeException("Stub!");
    }

    @ColorInt
    public static int rgb(float red, float green, float blue) {
        throw new RuntimeException("Stub!");
    }

    @ColorInt
    public static int argb(@IntRange(from = 0, to = 255) int alpha, @IntRange(from = 0, to = 255) int red,
            @IntRange(from = 0, to = 255) int green, @IntRange(from = 0, to = 255) int blue) {
        throw new RuntimeException("Stub!");
    }

    @ColorInt
    public static int argb(float alpha, float red, float green, float blue) {
        throw new RuntimeException("Stub!");
    }

    public static float luminance(@ColorInt int color) {
        throw new RuntimeException("Stub!");
    }

    @ColorInt
    public static int parseColor(@Size(min = 1) String colorString) {
        throw new RuntimeException("Stub!");
    }

    public static void RGBToHSV(@IntRange(from = 0, to = 255) int red, @IntRange(from = 0, to = 255) int green,
            @IntRange(from = 0, to = 255) int blue, @Size(3) float hsv[]) {
        throw new RuntimeException("Stub!");
    }

    public static void colorToHSV(@ColorInt int color, @Size(3) float hsv[]) {
        throw new RuntimeException("Stub!");
    }

    @ColorInt
    public static int HSVToColor(@Size(3) float hsv[]) {
        throw new RuntimeException("Stub!");
    }

    @ColorInt
    public static int HSVToColor(@IntRange(from = 0, to = 255) int alpha, @Size(3) float hsv[]) {
        throw new RuntimeException("Stub!");
    }

    private static native void nativeRGBToHSV(int red, int greed, int blue, float hsv[]);

    private static native int nativeHSVToColor(int alpha, float hsv[]);

    @ColorInt
    public static int getHtmlColor(@NonNull String color) {
        throw new RuntimeException("Stub!");
    }
}
