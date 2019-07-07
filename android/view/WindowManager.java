
package android.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import android.annotation.IntDef;
import android.annotation.SystemApi;
import android.annotation.SystemService;
import android.annotation.TestApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.accessibility.AccessibilityNodeInfo;

@SystemService(Context.WINDOW_SERVICE)
public interface WindowManager extends ViewManager {

    int DOCKED_INVALID = -1;
    int DOCKED_LEFT = 1;
    int DOCKED_TOP = 2;
    int DOCKED_RIGHT = 3;
    int DOCKED_BOTTOM = 4;

    String INPUT_CONSUMER_PIP = "pip_input_consumer";
    String INPUT_CONSUMER_NAVIGATION = "nav_input_consumer";
    String INPUT_CONSUMER_WALLPAPER = "wallpaper_input_consumer";
    String INPUT_CONSUMER_RECENTS_ANIMATION = "recents_animation_input_consumer";

    int TRANSIT_UNSET = -1;

    int TRANSIT_NONE = 0;

    int TRANSIT_ACTIVITY_OPEN = 6;

    int TRANSIT_ACTIVITY_CLOSE = 7;

    int TRANSIT_TASK_OPEN = 8;

    int TRANSIT_TASK_CLOSE = 9;

    int TRANSIT_TASK_TO_FRONT = 10;

    int TRANSIT_TASK_TO_BACK = 11;

    int TRANSIT_WALLPAPER_CLOSE = 12;

    int TRANSIT_WALLPAPER_OPEN = 13;

    int TRANSIT_WALLPAPER_INTRA_OPEN = 14;

    int TRANSIT_WALLPAPER_INTRA_CLOSE = 15;

    int TRANSIT_TASK_OPEN_BEHIND = 16;

    int TRANSIT_TASK_IN_PLACE = 17;

    int TRANSIT_ACTIVITY_RELAUNCH = 18;

    int TRANSIT_DOCK_TASK_FROM_RECENTS = 19;

    int TRANSIT_KEYGUARD_GOING_AWAY = 20;

    int TRANSIT_KEYGUARD_GOING_AWAY_ON_WALLPAPER = 21;

    int TRANSIT_KEYGUARD_OCCLUDE = 22;

    int TRANSIT_KEYGUARD_UNOCCLUDE = 23;

    int TRANSIT_TRANSLUCENT_ACTIVITY_OPEN = 24;

    int TRANSIT_TRANSLUCENT_ACTIVITY_CLOSE = 25;

    int TRANSIT_CRASHING_ACTIVITY_CLOSE = 26;

    @IntDef(prefix = { "TRANSIT_" }, value = { TRANSIT_UNSET, TRANSIT_NONE, TRANSIT_ACTIVITY_OPEN,
            TRANSIT_ACTIVITY_CLOSE, TRANSIT_TASK_OPEN, TRANSIT_TASK_CLOSE, TRANSIT_TASK_TO_FRONT, TRANSIT_TASK_TO_BACK,
            TRANSIT_WALLPAPER_CLOSE, TRANSIT_WALLPAPER_OPEN, TRANSIT_WALLPAPER_INTRA_OPEN,
            TRANSIT_WALLPAPER_INTRA_CLOSE, TRANSIT_TASK_OPEN_BEHIND, TRANSIT_TASK_IN_PLACE, TRANSIT_ACTIVITY_RELAUNCH,
            TRANSIT_DOCK_TASK_FROM_RECENTS, TRANSIT_KEYGUARD_GOING_AWAY, TRANSIT_KEYGUARD_GOING_AWAY_ON_WALLPAPER,
            TRANSIT_KEYGUARD_OCCLUDE, TRANSIT_KEYGUARD_UNOCCLUDE, TRANSIT_TRANSLUCENT_ACTIVITY_OPEN,
            TRANSIT_TRANSLUCENT_ACTIVITY_CLOSE, TRANSIT_CRASHING_ACTIVITY_CLOSE })
    @Retention(RetentionPolicy.SOURCE)
    @interface TransitionType {
    }

    int TRANSIT_FLAG_KEYGUARD_GOING_AWAY_TO_SHADE = 0x1;

    int TRANSIT_FLAG_KEYGUARD_GOING_AWAY_NO_ANIMATION = 0x2;

    int TRANSIT_FLAG_KEYGUARD_GOING_AWAY_WITH_WALLPAPER = 0x4;

    @IntDef(flag = true, prefix = { "TRANSIT_FLAG_" }, value = { TRANSIT_FLAG_KEYGUARD_GOING_AWAY_TO_SHADE,
            TRANSIT_FLAG_KEYGUARD_GOING_AWAY_NO_ANIMATION, TRANSIT_FLAG_KEYGUARD_GOING_AWAY_WITH_WALLPAPER, })
    @Retention(RetentionPolicy.SOURCE)
    @interface TransitionFlags {
    }

    public static class BadTokenException extends RuntimeException {

        private static final long serialVersionUID = 7924551348970480348L;

        public BadTokenException() {
            throw new RuntimeException("Stub!");
        }

        public BadTokenException(String name) {
            throw new RuntimeException("Stub!");
        }
    }

    public static class InvalidDisplayException extends RuntimeException {

        private static final long serialVersionUID = -5398016791788150715L;

        public InvalidDisplayException() {
            throw new RuntimeException("Stub!");
        }

        public InvalidDisplayException(String name) {
            throw new RuntimeException("Stub!");
        }
    }

    public Display getDefaultDisplay();

    public void removeViewImmediate(View view);

    public interface KeyboardShortcutsReceiver {
        void onKeyboardShortcutsReceived(List<KeyboardShortcutGroup> result);
    }

    final int TAKE_SCREENSHOT_FULLSCREEN = 1;

    final int TAKE_SCREENSHOT_SELECTED_REGION = 2;

    public static final String PARCEL_KEY_SHORTCUTS_ARRAY = "shortcuts_array";

    public void requestAppKeyboardShortcuts(final KeyboardShortcutsReceiver receiver, int deviceId);

    @SystemApi
    public Region getCurrentImeTouchRegion();

    public static class LayoutParams extends ViewGroup.LayoutParams implements Parcelable {
        public int x;

        public int y;

        public float horizontalWeight;

        public float verticalWeight;

        public int type;

        public static final int FIRST_APPLICATION_WINDOW = 1;

        public static final int TYPE_BASE_APPLICATION = 1;

        public static final int TYPE_APPLICATION = 2;

        public static final int TYPE_APPLICATION_STARTING = 3;

        public static final int TYPE_DRAWN_APPLICATION = 4;

        public static final int LAST_APPLICATION_WINDOW = 99;

        public static final int FIRST_SUB_WINDOW = 1000;

        public static final int TYPE_APPLICATION_PANEL = FIRST_SUB_WINDOW;

        public static final int TYPE_APPLICATION_MEDIA = FIRST_SUB_WINDOW + 1;

        public static final int TYPE_APPLICATION_SUB_PANEL = FIRST_SUB_WINDOW + 2;

        public static final int TYPE_APPLICATION_ATTACHED_DIALOG = FIRST_SUB_WINDOW + 3;

        public static final int TYPE_APPLICATION_MEDIA_OVERLAY = FIRST_SUB_WINDOW + 4;

        public static final int TYPE_APPLICATION_ABOVE_SUB_PANEL = FIRST_SUB_WINDOW + 5;

        public static final int LAST_SUB_WINDOW = 1999;

        public static final int FIRST_SYSTEM_WINDOW = 2000;

        public static final int TYPE_STATUS_BAR = FIRST_SYSTEM_WINDOW;

        public static final int TYPE_SEARCH_BAR = FIRST_SYSTEM_WINDOW + 1;

        public static final int TYPE_PHONE = FIRST_SYSTEM_WINDOW + 2;

        public static final int TYPE_SYSTEM_ALERT = FIRST_SYSTEM_WINDOW + 3;

        public static final int TYPE_KEYGUARD = FIRST_SYSTEM_WINDOW + 4;

        public static final int TYPE_TOAST = FIRST_SYSTEM_WINDOW + 5;

        public static final int TYPE_SYSTEM_OVERLAY = FIRST_SYSTEM_WINDOW + 6;

        public static final int TYPE_PRIORITY_PHONE = FIRST_SYSTEM_WINDOW + 7;

        public static final int TYPE_SYSTEM_DIALOG = FIRST_SYSTEM_WINDOW + 8;

        public static final int TYPE_KEYGUARD_DIALOG = FIRST_SYSTEM_WINDOW + 9;

        public static final int TYPE_SYSTEM_ERROR = FIRST_SYSTEM_WINDOW + 10;

        public static final int TYPE_INPUT_METHOD = FIRST_SYSTEM_WINDOW + 11;

        public static final int TYPE_INPUT_METHOD_DIALOG = FIRST_SYSTEM_WINDOW + 12;

        public static final int TYPE_WALLPAPER = FIRST_SYSTEM_WINDOW + 13;

        public static final int TYPE_STATUS_BAR_PANEL = FIRST_SYSTEM_WINDOW + 14;

        public static final int TYPE_SECURE_SYSTEM_OVERLAY = FIRST_SYSTEM_WINDOW + 15;

        public static final int TYPE_DRAG = FIRST_SYSTEM_WINDOW + 16;

        public static final int TYPE_STATUS_BAR_SUB_PANEL = FIRST_SYSTEM_WINDOW + 17;

        public static final int TYPE_POINTER = FIRST_SYSTEM_WINDOW + 18;

        public static final int TYPE_NAVIGATION_BAR = FIRST_SYSTEM_WINDOW + 19;

        public static final int TYPE_VOLUME_OVERLAY = FIRST_SYSTEM_WINDOW + 20;

        public static final int TYPE_BOOT_PROGRESS = FIRST_SYSTEM_WINDOW + 21;

        public static final int TYPE_INPUT_CONSUMER = FIRST_SYSTEM_WINDOW + 22;

        public static final int TYPE_DREAM = FIRST_SYSTEM_WINDOW + 23;

        public static final int TYPE_NAVIGATION_BAR_PANEL = FIRST_SYSTEM_WINDOW + 24;

        public static final int TYPE_DISPLAY_OVERLAY = FIRST_SYSTEM_WINDOW + 26;

        public static final int TYPE_MAGNIFICATION_OVERLAY = FIRST_SYSTEM_WINDOW + 27;

        public static final int TYPE_PRIVATE_PRESENTATION = FIRST_SYSTEM_WINDOW + 30;

        public static final int TYPE_VOICE_INTERACTION = FIRST_SYSTEM_WINDOW + 31;

        public static final int TYPE_ACCESSIBILITY_OVERLAY = FIRST_SYSTEM_WINDOW + 32;

        public static final int TYPE_VOICE_INTERACTION_STARTING = FIRST_SYSTEM_WINDOW + 33;

        public static final int TYPE_DOCK_DIVIDER = FIRST_SYSTEM_WINDOW + 34;

        public static final int TYPE_QS_DIALOG = FIRST_SYSTEM_WINDOW + 35;

        public static final int TYPE_SCREENSHOT = FIRST_SYSTEM_WINDOW + 36;

        public static final int TYPE_PRESENTATION = FIRST_SYSTEM_WINDOW + 37;

        public static final int TYPE_APPLICATION_OVERLAY = FIRST_SYSTEM_WINDOW + 38;

        public static final int LAST_SYSTEM_WINDOW = 2999;

        public static final int INVALID_WINDOW_TYPE = -1;

        public static boolean isSystemAlertWindowType(int type) {
            throw new RuntimeException("Stub!");
        }

        public static final int MEMORY_TYPE_NORMAL = 0;
        public static final int MEMORY_TYPE_HARDWARE = 1;
        public static final int MEMORY_TYPE_GPU = 2;
        public static final int MEMORY_TYPE_PUSH_BUFFERS = 3;

        public int memoryType;

        public static final int FLAG_ALLOW_LOCK_WHILE_SCREEN_ON = 0x00000001;

        public static final int FLAG_DIM_BEHIND = 0x00000002;

        public static final int FLAG_BLUR_BEHIND = 0x00000004;

        public static final int FLAG_NOT_FOCUSABLE = 0x00000008;

        public static final int FLAG_NOT_TOUCHABLE = 0x00000010;

        public static final int FLAG_NOT_TOUCH_MODAL = 0x00000020;

        public static final int FLAG_TOUCHABLE_WHEN_WAKING = 0x00000040;

        public static final int FLAG_KEEP_SCREEN_ON = 0x00000080;

        public static final int FLAG_LAYOUT_IN_SCREEN = 0x00000100;

        public static final int FLAG_LAYOUT_NO_LIMITS = 0x00000200;

        public static final int FLAG_FULLSCREEN = 0x00000400;

        public static final int FLAG_FORCE_NOT_FULLSCREEN = 0x00000800;

        public static final int FLAG_DITHER = 0x00001000;

        public static final int FLAG_SECURE = 0x00002000;

        public static final int FLAG_SCALED = 0x00004000;

        public static final int FLAG_IGNORE_CHEEK_PRESSES = 0x00008000;

        public static final int FLAG_LAYOUT_INSET_DECOR = 0x00010000;

        public static final int FLAG_ALT_FOCUSABLE_IM = 0x00020000;

        public static final int FLAG_WATCH_OUTSIDE_TOUCH = 0x00040000;

        public static final int FLAG_SHOW_WHEN_LOCKED = 0x00080000;

        public static final int FLAG_SHOW_WALLPAPER = 0x00100000;

        public static final int FLAG_TURN_SCREEN_ON = 0x00200000;

        public static final int FLAG_DISMISS_KEYGUARD = 0x00400000;

        public static final int FLAG_SPLIT_TOUCH = 0x00800000;

        public static final int FLAG_HARDWARE_ACCELERATED = 0x01000000;

        public static final int FLAG_LAYOUT_IN_OVERSCAN = 0x02000000;

        public static final int FLAG_TRANSLUCENT_STATUS = 0x04000000;

        public static final int FLAG_TRANSLUCENT_NAVIGATION = 0x08000000;

        public static final int FLAG_LOCAL_FOCUS_MODE = 0x10000000;

        public static final int FLAG_SLIPPERY = 0x20000000;

        public static final int FLAG_LAYOUT_ATTACHED_IN_DECOR = 0x40000000;

        public static final int FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS = 0x80000000;

        public int flags;

        public static final int PRIVATE_FLAG_FAKE_HARDWARE_ACCELERATED = 0x00000001;

        public static final int PRIVATE_FLAG_FORCE_HARDWARE_ACCELERATED = 0x00000002;

        public static final int PRIVATE_FLAG_WANTS_OFFSET_NOTIFICATIONS = 0x00000004;

        public static final int PRIVATE_FLAG_SHOW_FOR_ALL_USERS = 0x00000010;

        @TestApi
        public static final int PRIVATE_FLAG_NO_MOVE_ANIMATION = 0x00000040;

        public static final int PRIVATE_FLAG_COMPATIBLE_WINDOW = 0x00000080;

        public static final int PRIVATE_FLAG_SYSTEM_ERROR = 0x00000100;

        public static final int PRIVATE_FLAG_INHERIT_TRANSLUCENT_DECOR = 0x00000200;

        public static final int PRIVATE_FLAG_KEYGUARD = 0x00000400;

        public static final int PRIVATE_FLAG_DISABLE_WALLPAPER_TOUCH_EVENTS = 0x00000800;

        public static final int PRIVATE_FLAG_FORCE_STATUS_BAR_VISIBLE_TRANSPARENT = 0x00001000;

        public static final int PRIVATE_FLAG_PRESERVE_GEOMETRY = 0x00002000;

        public static final int PRIVATE_FLAG_FORCE_DECOR_VIEW_VISIBILITY = 0x00004000;

        public static final int PRIVATE_FLAG_WILL_NOT_REPLACE_ON_RELAUNCH = 0x00008000;

        public static final int PRIVATE_FLAG_LAYOUT_CHILD_WINDOW_IN_PARENT_FRAME = 0x00010000;

        public static final int PRIVATE_FLAG_FORCE_DRAW_STATUS_BAR_BACKGROUND = 0x00020000;

        public static final int PRIVATE_FLAG_SUSTAINED_PERFORMANCE_MODE = 0x00040000;

        public static final int PRIVATE_FLAG_HIDE_NON_SYSTEM_OVERLAY_WINDOWS = 0x00080000;

        public static final int PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY = 0x00100000;

        public static final int PRIVATE_FLAG_ACQUIRES_SLEEP_TOKEN = 0x00200000;

        public static final int PRIVATE_FLAG_IS_SCREEN_DECOR = 0x00400000;

        public static final int PRIVATE_FLAG_STATUS_BAR_EXPANDED = 0x00800000;

        public static final int NEEDS_MENU_UNSET = 0;

        public static final int NEEDS_MENU_SET_TRUE = 1;

        public static final int NEEDS_MENU_SET_FALSE = 2;

        public int needsMenuKey = NEEDS_MENU_UNSET;

        public static boolean mayUseInputMethod(int flags) {
            throw new RuntimeException("Stub!");
        }

        public static final int SOFT_INPUT_MASK_STATE = 0x0f;

        public static final int SOFT_INPUT_STATE_UNSPECIFIED = 0;

        public static final int SOFT_INPUT_STATE_UNCHANGED = 1;

        public static final int SOFT_INPUT_STATE_HIDDEN = 2;

        public static final int SOFT_INPUT_STATE_ALWAYS_HIDDEN = 3;

        public static final int SOFT_INPUT_STATE_VISIBLE = 4;

        public static final int SOFT_INPUT_STATE_ALWAYS_VISIBLE = 5;

        public static final int SOFT_INPUT_MASK_ADJUST = 0xf0;

        public static final int SOFT_INPUT_ADJUST_UNSPECIFIED = 0x00;

        public static final int SOFT_INPUT_ADJUST_RESIZE = 0x10;

        public static final int SOFT_INPUT_ADJUST_PAN = 0x20;

        public static final int SOFT_INPUT_ADJUST_NOTHING = 0x30;

        public static final int SOFT_INPUT_IS_FORWARD_NAVIGATION = 0x100;

        @Retention(RetentionPolicy.SOURCE)
        @IntDef(flag = true, prefix = { "SOFT_INPUT_" }, value = { SOFT_INPUT_STATE_UNSPECIFIED,
                SOFT_INPUT_STATE_UNCHANGED, SOFT_INPUT_STATE_HIDDEN, SOFT_INPUT_STATE_ALWAYS_HIDDEN,
                SOFT_INPUT_STATE_VISIBLE, SOFT_INPUT_STATE_ALWAYS_VISIBLE, SOFT_INPUT_ADJUST_UNSPECIFIED,
                SOFT_INPUT_ADJUST_RESIZE, SOFT_INPUT_ADJUST_PAN, SOFT_INPUT_ADJUST_NOTHING,
                SOFT_INPUT_IS_FORWARD_NAVIGATION, })
        public @interface SoftInputModeFlags {
        }

        @SoftInputModeFlags
        public int softInputMode;

        public int gravity;

        public float horizontalMargin;

        public float verticalMargin;

        public final Rect surfaceInsets = new Rect();

        public boolean hasManualSurfaceInsets;

        public boolean preservePreviousSurfaceInsets = true;

        public int format;

        public int windowAnimations;

        public float alpha = 1.0f;

        public float dimAmount = 1.0f;

        public static final float BRIGHTNESS_OVERRIDE_NONE = -1.0f;

        public static final float BRIGHTNESS_OVERRIDE_OFF = 0.0f;

        public static final float BRIGHTNESS_OVERRIDE_FULL = 1.0f;

        public float screenBrightness = BRIGHTNESS_OVERRIDE_NONE;

        public float buttonBrightness = BRIGHTNESS_OVERRIDE_NONE;

        public static final int ROTATION_ANIMATION_UNSPECIFIED = -1;

        public static final int ROTATION_ANIMATION_ROTATE = 0;

        public static final int ROTATION_ANIMATION_CROSSFADE = 1;

        public static final int ROTATION_ANIMATION_JUMPCUT = 2;

        public static final int ROTATION_ANIMATION_SEAMLESS = 3;

        public int rotationAnimation = ROTATION_ANIMATION_ROTATE;

        public IBinder token = null;

        public String packageName = null;

        public int screenOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;

        public float preferredRefreshRate;

        public int preferredDisplayModeId;

        public int systemUiVisibility;

        public int subtreeSystemUiVisibility;

        public boolean hasSystemUiListeners;

        @Retention(RetentionPolicy.SOURCE)
        @IntDef(flag = true, value = { LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT, LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES,
                LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER })
        @interface LayoutInDisplayCutoutMode {
        }

        @LayoutInDisplayCutoutMode
        public int layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;

        public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT = 0;

        public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS = 1;

        public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES = 1;

        public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER = 2;

        public static final int INPUT_FEATURE_DISABLE_POINTER_GESTURES = 0x00000001;

        public static final int INPUT_FEATURE_NO_INPUT_CHANNEL = 0x00000002;

        public static final int INPUT_FEATURE_DISABLE_USER_ACTIVITY = 0x00000004;

        public int inputFeatures;

        public long userActivityTimeout = -1;

        public long accessibilityIdOfAnchor = AccessibilityNodeInfo.UNDEFINED_NODE_ID;

        @TestApi
        public CharSequence accessibilityTitle;

        public long hideTimeoutMilliseconds = -1;

        @ActivityInfo.ColorMode

        public LayoutParams() {
            throw new RuntimeException("Stub!");
        }

        public LayoutParams(int _type) {
            throw new RuntimeException("Stub!");
        }

        public LayoutParams(int _type, int _flags) {
            throw new RuntimeException("Stub!");
        }

        public LayoutParams(int _type, int _flags, int _format) {
            throw new RuntimeException("Stub!");
        }

        public LayoutParams(int w, int h, int _type, int _flags, int _format) {
            throw new RuntimeException("Stub!");
        }

        public LayoutParams(int w, int h, int xpos, int ypos, int _type, int _flags, int _format) {
            throw new RuntimeException("Stub!");
        }

        public final void setTitle(CharSequence title) {
            throw new RuntimeException("Stub!");
        }

        public final CharSequence getTitle() {
            throw new RuntimeException("Stub!");
        }

        public final void setSurfaceInsets(View view, boolean manual, boolean preservePrevious) {
            throw new RuntimeException("Stub!");
        }

        public void setColorMode(@ActivityInfo.ColorMode int colorMode) {
            throw new RuntimeException("Stub!");
        }

        @ActivityInfo.ColorMode
        public int getColorMode() {
            throw new RuntimeException("Stub!");
        }

        @SystemApi
        public final void setUserActivityTimeout(long timeout) {
            throw new RuntimeException("Stub!");
        }

        @SystemApi
        public final long getUserActivityTimeout() {
            throw new RuntimeException("Stub!");
        }

        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        public void writeToParcel(Parcel out, int parcelableFlags) {
            throw new RuntimeException("Stub!");
        }

        public static final Parcelable.Creator<LayoutParams> CREATOR = new Parcelable.Creator<LayoutParams>() {
            public LayoutParams createFromParcel(Parcel in) {
                throw new RuntimeException("Stub!");
            }

            public LayoutParams[] newArray(int size) {
                throw new RuntimeException("Stub!");
            }
        };

        public LayoutParams(Parcel in) {
            throw new RuntimeException("Stub!");
        }

        public static final int LAYOUT_CHANGED = 1 << 0;
        public static final int TYPE_CHANGED = 1 << 1;
        public static final int FLAGS_CHANGED = 1 << 2;
        public static final int FORMAT_CHANGED = 1 << 3;
        public static final int ANIMATION_CHANGED = 1 << 4;
        public static final int DIM_AMOUNT_CHANGED = 1 << 5;
        public static final int TITLE_CHANGED = 1 << 6;
        public static final int ALPHA_CHANGED = 1 << 7;
        public static final int MEMORY_TYPE_CHANGED = 1 << 8;
        public static final int SOFT_INPUT_MODE_CHANGED = 1 << 9;
        public static final int SCREEN_ORIENTATION_CHANGED = 1 << 10;
        public static final int SCREEN_BRIGHTNESS_CHANGED = 1 << 11;
        public static final int ROTATION_ANIMATION_CHANGED = 1 << 12;
        public static final int BUTTON_BRIGHTNESS_CHANGED = 1 << 13;
        public static final int SYSTEM_UI_VISIBILITY_CHANGED = 1 << 14;
        public static final int SYSTEM_UI_LISTENER_CHANGED = 1 << 15;
        public static final int INPUT_FEATURES_CHANGED = 1 << 16;
        public static final int PRIVATE_FLAGS_CHANGED = 1 << 17;
        public static final int USER_ACTIVITY_TIMEOUT_CHANGED = 1 << 18;
        public static final int TRANSLUCENT_FLAGS_CHANGED = 1 << 19;
        public static final int SURFACE_INSETS_CHANGED = 1 << 20;
        public static final int PREFERRED_REFRESH_RATE_CHANGED = 1 << 21;
        public static final int NEEDS_MENU_KEY_CHANGED = 1 << 22;
        public static final int PREFERRED_DISPLAY_MODE_ID = 1 << 23;
        public static final int ACCESSIBILITY_ANCHOR_CHANGED = 1 << 24;
        @TestApi
        public static final int ACCESSIBILITY_TITLE_CHANGED = 1 << 25;
        public static final int COLOR_MODE_CHANGED = 1 << 26;
        public static final int EVERYTHING_CHANGED = 0xffffffff;

        // internal buffer to backup/restore parameters under compatibility mode.

        public final int copyFrom(LayoutParams o) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public String debug(String output) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public String toString() {
            throw new RuntimeException("Stub!");
        }

        public void dumpDimensions(StringBuilder sb) {
            throw new RuntimeException("Stub!");
        }

        public String toString(String prefix) {
            throw new RuntimeException("Stub!");
        }

        public void scale(float scale) {
            throw new RuntimeException("Stub!");
        }

        void backup() {
            throw new RuntimeException("Stub!");
        }

        void restore() {
            throw new RuntimeException("Stub!");
        }

        public boolean isFullscreen() {
            throw new RuntimeException("Stub!");
        }
    }
}
