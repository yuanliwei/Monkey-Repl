
package android.util;

import android.annotation.UnsupportedAppUsage;

public class DisplayMetrics {
    public static final int DENSITY_LOW = 120;

    public static final int DENSITY_MEDIUM = 160;

    public static final int DENSITY_TV = 213;

    public static final int DENSITY_HIGH = 240;

    public static final int DENSITY_260 = 260;

    public static final int DENSITY_280 = 280;

    public static final int DENSITY_300 = 300;

    public static final int DENSITY_XHIGH = 320;

    public static final int DENSITY_340 = 340;

    public static final int DENSITY_360 = 360;

    public static final int DENSITY_400 = 400;

    public static final int DENSITY_420 = 420;

    public static final int DENSITY_440 = 440;

    public static final int DENSITY_XXHIGH = 480;

    public static final int DENSITY_560 = 560;

    public static final int DENSITY_600 = 600;

    public static final int DENSITY_XXXHIGH = 640;

    public static final int DENSITY_DEFAULT = DENSITY_MEDIUM;

    public static final float DENSITY_DEFAULT_SCALE = 1.0f / DENSITY_DEFAULT;

    @Deprecated
    @UnsupportedAppUsage
    public static int DENSITY_DEVICE = getDeviceDensity();

    public static final int DENSITY_DEVICE_STABLE = getDeviceDensity();

    public int widthPixels;
    public int heightPixels;
    public float density;
    public int densityDpi;
    public float scaledDensity;
    public float xdpi;
    public float ydpi;

    @UnsupportedAppUsage
    public int noncompatWidthPixels;
    @UnsupportedAppUsage
    public int noncompatHeightPixels;
    public float noncompatDensity;
    @UnsupportedAppUsage
    public int noncompatDensityDpi;
    public float noncompatScaledDensity;
    public float noncompatXdpi;
    public float noncompatYdpi;

    public DisplayMetrics() {
        throw new RuntimeException("Stub!");
    }

    public void setTo(DisplayMetrics o) {
        throw new RuntimeException("Stub!");
    }

    public void setToDefaults() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean equals(Object o) {
        throw new RuntimeException("Stub!");
    }

    public boolean equals(DisplayMetrics other) {
        throw new RuntimeException("Stub!");
    }

    public boolean equalsPhysical(DisplayMetrics other) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        return "DisplayMetrics{density=" + density + ", width=" + widthPixels + ", height=" + heightPixels
                + ", scaledDensity=" + scaledDensity + ", xdpi=" + xdpi + ", ydpi=" + ydpi + "}";
    }

    private static int getDeviceDensity() {
        throw new RuntimeException("Stub!");
    }
}
