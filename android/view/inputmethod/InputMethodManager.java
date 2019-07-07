
package android.view.inputmethod;

import android.annotation.UnsupportedAppUsage;

public final class InputMethodManager {
    static final boolean DEBUG = false;
    static final String TAG = "InputMethodManager";

    static final String PENDING_EVENT_COUNTER = "aq:imm";

    @UnsupportedAppUsage
    static InputMethodManager sInstance;

    public static final int CONTROL_WINDOW_VIEW_HAS_FOCUS = 1 << 0;

    public static final int CONTROL_WINDOW_IS_TEXT_EDITOR = 1 << 1;

    public static final int CONTROL_WINDOW_FIRST = 1 << 2;

    public static final int CONTROL_START_INITIAL = 1 << 8;

    static final long INPUT_METHOD_NOT_RESPONDING_TIMEOUT = 2500;

    public static final int DISPATCH_IN_PROGRESS = -1;

    public static final int DISPATCH_NOT_HANDLED = 0;

    public static final int DISPATCH_HANDLED = 1;

    public static final int SHOW_IM_PICKER_MODE_AUTO = 0;
    public static final int SHOW_IM_PICKER_MODE_INCLUDE_AUXILIARY_SUBTYPES = 1;
    public static final int SHOW_IM_PICKER_MODE_EXCLUDE_AUXILIARY_SUBTYPES = 2;

    public static InputMethodManager getInstance() {
        throw new RuntimeException("Stub!");
    }
}
