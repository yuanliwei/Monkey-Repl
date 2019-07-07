
package android.graphics;

import android.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PixelFormat {
    @IntDef({ UNKNOWN, TRANSLUCENT, TRANSPARENT, OPAQUE })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Opacity {
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ RGBA_8888, RGBX_8888, RGBA_F16, RGBA_1010102, RGB_888, RGB_565 })
    public @interface Format {
    }

    public static final int UNKNOWN = 0;

    public static final int TRANSLUCENT = -3;

    public static final int TRANSPARENT = -2;

    public static final int OPAQUE = -1;

    public static final int RGBA_8888 = 1;
    public static final int RGBX_8888 = 2;
    public static final int RGB_888 = 3;
    public static final int RGB_565 = 4;

    @Deprecated
    public static final int RGBA_5551 = 6;
    @Deprecated
    public static final int RGBA_4444 = 7;
    @Deprecated
    public static final int A_8 = 8;
    @Deprecated
    public static final int L_8 = 9;
    @Deprecated
    public static final int LA_88 = 0xA;
    @Deprecated
    public static final int RGB_332 = 0xB;

    @Deprecated
    public static final int YCbCr_422_SP = 0x10;

    @Deprecated
    public static final int YCbCr_420_SP = 0x11;

    @Deprecated
    public static final int YCbCr_422_I = 0x14;

    public static final int RGBA_F16 = 0x16;
    public static final int RGBA_1010102 = 0x2B;

    @Deprecated
    public static final int JPEG = 0x100;

    public int bytesPerPixel;
    public int bitsPerPixel;

    public static void getPixelFormatInfo(@Format int format, PixelFormat info) {
        throw new RuntimeException("Stub!");
    }

    public static boolean formatHasAlpha(@Format int format) {
        throw new RuntimeException("Stub!");
    }

    public static boolean isPublicFormat(@Format int format) {
        throw new RuntimeException("Stub!");
    }
}
