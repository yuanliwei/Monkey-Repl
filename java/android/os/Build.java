package android.os;

import android.Manifest;
import android.annotation.RequiresPermission;
import android.annotation.SystemApi;

public class Build {
    public static final String UNKNOWN = "unknown";
    public static final String ID = getString("ro.build.id");
    public static final String DISPLAY = getString("ro.build.display.id");
    public static final String PRODUCT = getString("ro.product.name");
    public static final String DEVICE = getString("ro.product.device");
    public static final String BOARD = getString("ro.product.board");
    public static final String CPU_ABI = null;
    public static final String CPU_ABI2 = null;
    public static final String MANUFACTURER = getString("ro.product.manufacturer");
    public static final String BRAND = getString("ro.product.brand");
    public static final String MODEL = getString("ro.product.model");
    public static final String BOOTLOADER = getString("ro.bootloader");
    public static final String RADIO = "getString(TelephonyProperties.PROPERTY_BASEBAND_VERSION)";
    public static final String HARDWARE = getString("ro.hardware");
    public static final boolean IS_EMULATOR = getString("ro.kernel.qemu").equals("1");
    // IMPORTANT: This field should be initialized via a function call to
    // prevent its value being inlined in the app during compilation because
    // we will later set it to the value based on the app's target SDK.
    public static final String SERIAL = getString("no.such.thing");

    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSerial() {
        throw new RuntimeException("Stub!");
    }

    public static final String[] SUPPORTED_ABIS = getStringList("ro.product.cpu.abilist", ",");
    public static final String[] SUPPORTED_32_BIT_ABIS = getStringList("ro.product.cpu.abilist32", ",");
    public static final String[] SUPPORTED_64_BIT_ABIS = getStringList("ro.product.cpu.abilist64", ",");

    public static class VERSION {
        public static final String INCREMENTAL = getString("ro.build.version.incremental");
        public static final String RELEASE = getString("ro.build.version.release");
        public static final String BASE_OS = SystemProperties.get("ro.build.version.base_os", "");
        public static final String SECURITY_PATCH = SystemProperties.get("ro.build.version.security_patch", "");
        @Deprecated
        public static final String SDK = getString("ro.build.version.sdk");
        public static final int SDK_INT = SystemProperties.getInt("ro.build.version.sdk", 0);
        public static final int PREVIEW_SDK_INT = SystemProperties.getInt("ro.build.version.preview_sdk", 0);
        public static final String CODENAME = getString("ro.build.version.codename");

        public static final String[] ACTIVE_CODENAMES = new String[0];
        public static final int RESOURCES_SDK_INT = SDK_INT + ACTIVE_CODENAMES.length;
    }

    public static class VERSION_CODES {
        public static final int CUR_DEVELOPMENT = 0;
        public static final int BASE = 1;
        public static final int BASE_1_1 = 2;
        public static final int CUPCAKE = 3;
        public static final int DONUT = 4;
        public static final int ECLAIR = 5;
        public static final int ECLAIR_0_1 = 6;
        public static final int ECLAIR_MR1 = 7;
        public static final int FROYO = 8;
        public static final int GINGERBREAD = 9;
        public static final int GINGERBREAD_MR1 = 10;
        public static final int HONEYCOMB = 11;
        public static final int HONEYCOMB_MR1 = 12;
        public static final int HONEYCOMB_MR2 = 13;
        public static final int ICE_CREAM_SANDWICH = 14;
        public static final int ICE_CREAM_SANDWICH_MR1 = 15;
        public static final int JELLY_BEAN = 16;
        public static final int JELLY_BEAN_MR1 = 17;
        public static final int JELLY_BEAN_MR2 = 18;
        public static final int KITKAT = 19;
        public static final int KITKAT_WATCH = 20;
        public static final int L = 21;
        public static final int LOLLIPOP = 21;
        public static final int LOLLIPOP_MR1 = 22;
        public static final int M = 23;
        public static final int N = 24;
        public static final int N_MR1 = 25;
        public static final int O = 26;
        public static final int O_MR1 = 27;
        public static final int P = 28;
    }

    public static final String TYPE = getString("ro.build.type");
    public static final String TAGS = getString("ro.build.tags");
    public static final String FINGERPRINT = deriveFingerprint();

    private static String deriveFingerprint() {
        throw new RuntimeException("Stub!");
    }

    public static void ensureFingerprintProperty() {
        throw new RuntimeException("Stub!");
    }

    public static final boolean IS_TREBLE_ENABLED = SystemProperties.getBoolean("ro.treble.enabled", false);

    public static boolean isBuildConsistent() {
        throw new RuntimeException("Stub!");
    }

    // The following properties only make sense for internal engineering builds.
    public static final long TIME = 1000;
    public static final String USER = getString("ro.build.user");
    public static final String HOST = getString("ro.build.host");
    public static final boolean IS_DEBUGGABLE = SystemProperties.getInt("ro.debuggable", 0) == 1;
    public static final boolean IS_ENG = "eng".equals(TYPE);
    public static final boolean IS_USERDEBUG = "userdebug".equals(TYPE);
    public static final boolean IS_USER = "user".equals(TYPE);
    public static final boolean IS_CONTAINER = SystemProperties.getBoolean("ro.boot.container", false);
    @SystemApi
    public static final boolean PERMISSIONS_REVIEW_REQUIRED = SystemProperties.getInt("ro.permission_review_required",
            0) == 1;

    public static String getRadioVersion() {
        throw new RuntimeException("Stub!");
    }

    private static String getString(String property) {
        throw new RuntimeException("Stub!");
    }

    private static String[] getStringList(String property, String separator) {
        throw new RuntimeException("Stub!");
    }
}