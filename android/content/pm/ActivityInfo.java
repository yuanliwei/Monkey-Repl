package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class ActivityInfo extends ComponentInfo implements Parcelable {
    public static final Parcelable.Creator<ActivityInfo> CREATOR = new Parcelable.Creator<ActivityInfo>() {
        public ActivityInfo createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public ActivityInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
    public int theme;
    public static final int LAUNCH_MULTIPLE = 0;
    public static final int LAUNCH_SINGLE_TOP = 1;
    public static final int LAUNCH_SINGLE_TASK = 2;
    public static final int LAUNCH_SINGLE_INSTANCE = 3;
    public int launchMode;
    public static final int DOCUMENT_LAUNCH_NONE = 0;
    public static final int DOCUMENT_LAUNCH_INTO_EXISTING = 1;
    public static final int DOCUMENT_LAUNCH_ALWAYS = 2;
    public static final int DOCUMENT_LAUNCH_NEVER = 3;
    public int documentLaunchMode;
    public static final int PERSIST_ROOT_ONLY = 0;
    public static final int PERSIST_NEVER = 1;
    public static final int PERSIST_ACROSS_REBOOTS = 2;
    public int persistableMode;
    public int maxRecents;
    public String permission;
    public String taskAffinity;
    public String targetActivity;
    public String launchToken;
    public static final int RESIZE_MODE_UNRESIZEABLE = 0;
    public static final int RESIZE_MODE_RESIZEABLE_VIA_SDK_VERSION = 1;
    public static final int RESIZE_MODE_RESIZEABLE = 2;
    public static final int RESIZE_MODE_RESIZEABLE_AND_PIPABLE_DEPRECATED = 3;
    public static final int RESIZE_MODE_FORCE_RESIZEABLE = 4;
    public static final int RESIZE_MODE_FORCE_RESIZABLE_LANDSCAPE_ONLY = 5;
    public static final int RESIZE_MODE_FORCE_RESIZABLE_PORTRAIT_ONLY = 6;
    public static final int RESIZE_MODE_FORCE_RESIZABLE_PRESERVE_ORIENTATION = 7;
    public int resizeMode = RESIZE_MODE_RESIZEABLE;
    public float maxAspectRatio;
    public String requestedVrComponent;
    public static final int COLOR_MODE_DEFAULT = 0;
    public static final int COLOR_MODE_WIDE_COLOR_GAMUT = 1;
    public static final int COLOR_MODE_HDR = 2;

    public @interface ColorMode {
    }

    @ColorMode
    public int colorMode = COLOR_MODE_DEFAULT;
    public static final int FLAG_MULTIPROCESS = 0x0001;
    public static final int FLAG_FINISH_ON_TASK_LAUNCH = 0x0002;
    public static final int FLAG_CLEAR_TASK_ON_LAUNCH = 0x0004;
    public static final int FLAG_ALWAYS_RETAIN_TASK_STATE = 0x0008;
    public static final int FLAG_STATE_NOT_NEEDED = 0x0010;
    public static final int FLAG_EXCLUDE_FROM_RECENTS = 0x0020;
    public static final int FLAG_ALLOW_TASK_REPARENTING = 0x0040;
    public static final int FLAG_NO_HISTORY = 0x0080;
    public static final int FLAG_FINISH_ON_CLOSE_SYSTEM_DIALOGS = 0x0100;
    public static final int FLAG_HARDWARE_ACCELERATED = 0x0200;
    public static final int FLAG_SHOW_FOR_ALL_USERS = 0x0400;
    public static final int FLAG_IMMERSIVE = 0x0800;
    public static final int FLAG_RELINQUISH_TASK_IDENTITY = 0x1000;
    public static final int FLAG_AUTO_REMOVE_FROM_RECENTS = 0x2000;
    public static final int FLAG_RESUME_WHILE_PAUSING = 0x4000;
    public static final int FLAG_ENABLE_VR_MODE = 0x8000;
    public static final int FLAG_ALWAYS_FOCUSABLE = 0x40000;
    public static final int FLAG_VISIBLE_TO_INSTANT_APP = 0x100000;
    public static final int FLAG_IMPLICITLY_VISIBLE_TO_INSTANT_APP = 0x200000;
    public static final int FLAG_SUPPORTS_PICTURE_IN_PICTURE = 0x400000;
    public static final int FLAG_SHOW_WHEN_LOCKED = 0x800000;
    public static final int FLAG_TURN_SCREEN_ON = 0x1000000;
    public static final int FLAG_SYSTEM_USER_ONLY = 0x20000000;
    public static final int FLAG_SINGLE_USER = 0x40000000;
    public static final int FLAG_ALLOW_EMBEDDED = 0x80000000;
    public int flags;

    public @interface ScreenOrientation {
    }

    public static final int SCREEN_ORIENTATION_UNSET = -2;
    public static final int SCREEN_ORIENTATION_UNSPECIFIED = -1;
    public static final int SCREEN_ORIENTATION_LANDSCAPE = 0;
    public static final int SCREEN_ORIENTATION_PORTRAIT = 1;
    public static final int SCREEN_ORIENTATION_USER = 2;
    public static final int SCREEN_ORIENTATION_BEHIND = 3;
    public static final int SCREEN_ORIENTATION_SENSOR = 4;
    public static final int SCREEN_ORIENTATION_NOSENSOR = 5;
    public static final int SCREEN_ORIENTATION_SENSOR_LANDSCAPE = 6;
    public static final int SCREEN_ORIENTATION_SENSOR_PORTRAIT = 7;
    public static final int SCREEN_ORIENTATION_REVERSE_LANDSCAPE = 8;
    public static final int SCREEN_ORIENTATION_REVERSE_PORTRAIT = 9;
    public static final int SCREEN_ORIENTATION_FULL_SENSOR = 10;
    public static final int SCREEN_ORIENTATION_USER_LANDSCAPE = 11;
    public static final int SCREEN_ORIENTATION_USER_PORTRAIT = 12;
    public static final int SCREEN_ORIENTATION_FULL_USER = 13;
    public static final int SCREEN_ORIENTATION_LOCKED = 14;
    @ScreenOrientation
    public int screenOrientation = SCREEN_ORIENTATION_UNSPECIFIED;

    public @interface Config {
    }

    public static final int CONFIG_MCC = 0x0001;
    public static final int CONFIG_MNC = 0x0002;
    public static final int CONFIG_LOCALE = 0x0004;
    public static final int CONFIG_TOUCHSCREEN = 0x0008;
    public static final int CONFIG_KEYBOARD = 0x0010;
    public static final int CONFIG_KEYBOARD_HIDDEN = 0x0020;
    public static final int CONFIG_NAVIGATION = 0x0040;
    public static final int CONFIG_ORIENTATION = 0x0080;
    public static final int CONFIG_SCREEN_LAYOUT = 0x0100;
    public static final int CONFIG_UI_MODE = 0x0200;
    public static final int CONFIG_SCREEN_SIZE = 0x0400;
    public static final int CONFIG_SMALLEST_SCREEN_SIZE = 0x0800;
    public static final int CONFIG_DENSITY = 0x1000;
    public static final int CONFIG_LAYOUT_DIRECTION = 0x2000;
    public static final int CONFIG_COLOR_MODE = 0x4000;
    public static final int CONFIG_ASSETS_PATHS = 0x80000000;
    public static final int CONFIG_FONT_SCALE = 0x40000000;
    public static final int CONFIG_WINDOW_CONFIGURATION = 0x20000000;

    public ActivityInfo(ServiceInfo orig) {
        super(orig);
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
}