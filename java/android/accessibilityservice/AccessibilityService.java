
package android.accessibilityservice;

import android.app.Service;

public abstract class AccessibilityService extends Service {

    public static final int GESTURE_SWIPE_UP = 1;

    public static final int GESTURE_SWIPE_DOWN = 2;

    public static final int GESTURE_SWIPE_LEFT = 3;

    public static final int GESTURE_SWIPE_RIGHT = 4;

    public static final int GESTURE_SWIPE_LEFT_AND_RIGHT = 5;

    public static final int GESTURE_SWIPE_RIGHT_AND_LEFT = 6;

    public static final int GESTURE_SWIPE_UP_AND_DOWN = 7;

    public static final int GESTURE_SWIPE_DOWN_AND_UP = 8;

    public static final int GESTURE_SWIPE_LEFT_AND_UP = 9;

    public static final int GESTURE_SWIPE_LEFT_AND_DOWN = 10;

    public static final int GESTURE_SWIPE_RIGHT_AND_UP = 11;

    public static final int GESTURE_SWIPE_RIGHT_AND_DOWN = 12;

    public static final int GESTURE_SWIPE_UP_AND_LEFT = 13;

    public static final int GESTURE_SWIPE_UP_AND_RIGHT = 14;

    public static final int GESTURE_SWIPE_DOWN_AND_LEFT = 15;

    public static final int GESTURE_SWIPE_DOWN_AND_RIGHT = 16;

    public static final String SERVICE_INTERFACE =
        "android.accessibilityservice.AccessibilityService";

    public static final String SERVICE_META_DATA = "android.accessibilityservice";

    public static final int GLOBAL_ACTION_BACK = 1;

    public static final int GLOBAL_ACTION_HOME = 2;

    public static final int GLOBAL_ACTION_RECENTS = 3;

    public static final int GLOBAL_ACTION_NOTIFICATIONS = 4;

    public static final int GLOBAL_ACTION_QUICK_SETTINGS = 5;

    public static final int GLOBAL_ACTION_POWER_DIALOG = 6;

    public static final int GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN = 7;

    public static final int GLOBAL_ACTION_LOCK_SCREEN = 8;

    public static final int GLOBAL_ACTION_TAKE_SCREENSHOT = 9;
 
}
