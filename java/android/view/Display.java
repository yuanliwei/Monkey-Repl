
package android.view;

import android.annotation.UnsupportedAppUsage;
import android.util.DisplayMetrics;

public final class Display {

    public static final int DEFAULT_DISPLAY = 0;

    public static final int INVALID_DISPLAY = -1;

    public static final int FLAG_SUPPORTS_PROTECTED_BUFFERS = 1 << 0;

    public static final int FLAG_SECURE = 1 << 1;

    public static final int FLAG_PRIVATE = 1 << 2;

    public static final int FLAG_PRESENTATION = 1 << 3;

    public static final int FLAG_ROUND = 1 << 4;

    public static final int FLAG_CAN_SHOW_WITH_INSECURE_KEYGUARD = 1 << 5;

    public static final int FLAG_SCALING_DISABLED = 1 << 30;

    @UnsupportedAppUsage
    public static final int TYPE_UNKNOWN = 0;

    public static final int TYPE_BUILT_IN = 1;

    @UnsupportedAppUsage
    public static final int TYPE_HDMI = 2;

    @UnsupportedAppUsage
    public static final int TYPE_WIFI = 3;

    public static final int TYPE_OVERLAY = 4;

    @UnsupportedAppUsage
    public static final int TYPE_VIRTUAL = 5;

    public static final int STATE_UNKNOWN = 0;

    public static final int STATE_OFF = 1;

    public static final int STATE_ON = 2;

    public static final int STATE_DOZE = 3;

    public static final int STATE_DOZE_SUSPEND = 4;

    public static final int STATE_VR = 5;

    public static final int STATE_ON_SUSPEND = 6;

    public static final int COLOR_MODE_INVALID = -1;

    public static final int COLOR_MODE_DEFAULT = 0;

    public static final int COLOR_MODE_BT601_625 = 1;
    public static final int COLOR_MODE_BT601_625_UNADJUSTED = 2;
    public static final int COLOR_MODE_BT601_525 = 3;
    public static final int COLOR_MODE_BT601_525_UNADJUSTED = 4;
    public static final int COLOR_MODE_BT709 = 5;
    public static final int COLOR_MODE_DCI_P3 = 6;
    public static final int COLOR_MODE_SRGB = 7;
    public static final int COLOR_MODE_ADOBE_RGB = 8;
    public static final int COLOR_MODE_DISPLAY_P3 = 9;

    public static final int REMOVE_MODE_MOVE_CONTENT_TO_PRIMARY = 0;
    public static final int REMOVE_MODE_DESTROY_CONTENT = 1;

    public int getWidth() {
        throw new RuntimeException("Stub!");
    }

    public int getHeight() {
        throw new RuntimeException("Stub!");
    }

    public void getMetrics(DisplayMetrics dm) {
        throw new RuntimeException("Stub!");
    }

    public int getRotation() {
        return 0;
    }

}
