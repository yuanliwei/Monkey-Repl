package android.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.annotation.IdRes;
import android.annotation.IntDef;
import android.annotation.UiThread;
import android.graphics.drawable.Drawable;
import android.util.LayoutDirection;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityEventSource;

@UiThread
public class View implements Drawable.Callback, KeyEvent.Callback, AccessibilityEventSource {
    public static boolean DEBUG_DRAW = false;
    protected static final String VIEW_LOG_TAG = "View";
    public static boolean mDebugViewAttributes = false;
    public static final int NO_ID = -1;
    public static final int LAST_APP_AUTOFILL_ID = Integer.MAX_VALUE / 2;
    static boolean sUseZeroUnspecifiedMeasureSpec = false;
    static boolean sTextureViewIgnoresDrawableSetters = false;
    protected static boolean sPreserveMarginParamsInLayoutParamConversion;
    static boolean sCascadedDragDrop;
    static boolean sHasFocusableExcludeAutoFocusable;

    @IntDef({ NOT_FOCUSABLE, FOCUSABLE, FOCUSABLE_AUTO })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Focusable {
    }

    public static final int NOT_FOCUSABLE = 0x00000000;
    public static final int FOCUSABLE = 0x00000001;
    public static final int FOCUSABLE_AUTO = 0x00000010;

    @IntDef({ VISIBLE, INVISIBLE, GONE })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }

    public static final int VISIBLE = 0x00000000;
    public static final int INVISIBLE = 0x00000004;
    public static final int GONE = 0x00000008;
    static final int VISIBILITY_MASK = 0x0000000C;
    public static final String AUTOFILL_HINT_EMAIL_ADDRESS = "emailAddress";
    public static final String AUTOFILL_HINT_NAME = "name";
    public static final String AUTOFILL_HINT_USERNAME = "username";
    public static final String AUTOFILL_HINT_PASSWORD = "password";
    public static final String AUTOFILL_HINT_PHONE = "phone";
    public static final String AUTOFILL_HINT_POSTAL_ADDRESS = "postalAddress";
    public static final String AUTOFILL_HINT_POSTAL_CODE = "postalCode";
    public static final String AUTOFILL_HINT_CREDIT_CARD_NUMBER = "creditCardNumber";
    public static final String AUTOFILL_HINT_CREDIT_CARD_SECURITY_CODE = "creditCardSecurityCode";
    public static final String AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DATE = "creditCardExpirationDate";
    public static final String AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_MONTH = "creditCardExpirationMonth";
    public static final String AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_YEAR = "creditCardExpirationYear";
    public static final String AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DAY = "creditCardExpirationDay";

    @IntDef(prefix = { "AUTOFILL_TYPE_" }, value = { AUTOFILL_TYPE_NONE, AUTOFILL_TYPE_TEXT, AUTOFILL_TYPE_TOGGLE,
            AUTOFILL_TYPE_LIST, AUTOFILL_TYPE_DATE })
    @Retention(RetentionPolicy.SOURCE)
    public @interface AutofillType {
    }

    public static final int AUTOFILL_TYPE_NONE = 0;
    public static final int AUTOFILL_TYPE_TEXT = 1;
    public static final int AUTOFILL_TYPE_TOGGLE = 2;
    public static final int AUTOFILL_TYPE_LIST = 3;
    public static final int AUTOFILL_TYPE_DATE = 4;

    @IntDef(prefix = { "IMPORTANT_FOR_AUTOFILL_" }, value = { IMPORTANT_FOR_AUTOFILL_AUTO, IMPORTANT_FOR_AUTOFILL_YES,
            IMPORTANT_FOR_AUTOFILL_NO, IMPORTANT_FOR_AUTOFILL_YES_EXCLUDE_DESCENDANTS,
            IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS })
    @Retention(RetentionPolicy.SOURCE)
    public @interface AutofillImportance {
    }

    public static final int IMPORTANT_FOR_AUTOFILL_AUTO = 0x0;
    public static final int IMPORTANT_FOR_AUTOFILL_YES = 0x1;
    public static final int IMPORTANT_FOR_AUTOFILL_NO = 0x2;
    public static final int IMPORTANT_FOR_AUTOFILL_YES_EXCLUDE_DESCENDANTS = 0x4;
    public static final int IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS = 0x8;

    @IntDef(flag = true, prefix = { "AUTOFILL_FLAG_" }, value = { AUTOFILL_FLAG_INCLUDE_NOT_IMPORTANT_VIEWS })
    @Retention(RetentionPolicy.SOURCE)
    public @interface AutofillFlags {
    }

    public static final int AUTOFILL_FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 0x1;
    static final int ENABLED = 0x00000000;
    static final int DISABLED = 0x00000020;
    static final int ENABLED_MASK = 0x00000020;
    static final int WILL_NOT_DRAW = 0x00000080;
    static final int DRAW_MASK = 0x00000080;
    static final int SCROLLBARS_NONE = 0x00000000;
    static final int SCROLLBARS_HORIZONTAL = 0x00000100;
    static final int SCROLLBARS_VERTICAL = 0x00000200;
    static final int SCROLLBARS_MASK = 0x00000300;
    static final int FILTER_TOUCHES_WHEN_OBSCURED = 0x00000400;
    static final int OPTIONAL_FITS_SYSTEM_WINDOWS = 0x00000800;
    static final int FADING_EDGE_NONE = 0x00000000;
    static final int FADING_EDGE_HORIZONTAL = 0x00001000;
    static final int FADING_EDGE_VERTICAL = 0x00002000;
    static final int FADING_EDGE_MASK = 0x00003000;
    static final int CLICKABLE = 0x00004000;
    static final int DRAWING_CACHE_ENABLED = 0x00008000;
    static final int SAVE_DISABLED = 0x000010000;
    static final int SAVE_DISABLED_MASK = 0x000010000;
    static final int WILL_NOT_CACHE_DRAWING = 0x000020000;
    static final int FOCUSABLE_IN_TOUCH_MODE = 0x00040000;

    @Deprecated
    public static final int DRAWING_CACHE_QUALITY_LOW = 0x00080000;
    @Deprecated
    public static final int DRAWING_CACHE_QUALITY_HIGH = 0x00100000;
    static final int DRAWING_CACHE_QUALITY_MASK = 0x00180000;
    static final int LONG_CLICKABLE = 0x00200000;
    static final int DUPLICATE_PARENT_STATE = 0x00400000;
    static final int CONTEXT_CLICKABLE = 0x00800000;

    @IntDef(prefix = { "SCROLLBARS_" }, value = { SCROLLBARS_INSIDE_OVERLAY, SCROLLBARS_INSIDE_INSET,
            SCROLLBARS_OUTSIDE_OVERLAY, SCROLLBARS_OUTSIDE_INSET })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollBarStyle {
    }

    public static final int SCROLLBARS_INSIDE_OVERLAY = 0;
    public static final int SCROLLBARS_INSIDE_INSET = 0x01000000;
    public static final int SCROLLBARS_OUTSIDE_OVERLAY = 0x02000000;
    public static final int SCROLLBARS_OUTSIDE_INSET = 0x03000000;
    static final int SCROLLBARS_INSET_MASK = 0x01000000;
    static final int SCROLLBARS_OUTSIDE_MASK = 0x02000000;
    static final int SCROLLBARS_STYLE_MASK = 0x03000000;
    public static final int KEEP_SCREEN_ON = 0x04000000;
    public static final int SOUND_EFFECTS_ENABLED = 0x08000000;
    public static final int HAPTIC_FEEDBACK_ENABLED = 0x10000000;
    static final int PARENT_SAVE_DISABLED = 0x20000000;
    static final int PARENT_SAVE_DISABLED_MASK = 0x20000000;
    static final int TOOLTIP = 0x40000000;

    @IntDef(flag = true, prefix = { "FOCUSABLES_" }, value = { FOCUSABLES_ALL, FOCUSABLES_TOUCH_MODE })
    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusableMode {
    }

    public static final int FOCUSABLES_ALL = 0x00000000;
    public static final int FOCUSABLES_TOUCH_MODE = 0x00000001;

    @IntDef(prefix = { "FOCUS_" }, value = { FOCUS_BACKWARD, FOCUS_FORWARD, FOCUS_LEFT, FOCUS_UP, FOCUS_RIGHT,
            FOCUS_DOWN })
    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusDirection {
    }

    @IntDef(prefix = { "FOCUS_" }, value = { FOCUS_LEFT, FOCUS_UP, FOCUS_RIGHT, FOCUS_DOWN })
    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusRealDirection {
    } // Like @FocusDirection, but without forward/backward

    public static final int FOCUS_BACKWARD = 0x00000001;
    public static final int FOCUS_FORWARD = 0x00000002;
    public static final int FOCUS_LEFT = 0x00000011;
    public static final int FOCUS_UP = 0x00000021;
    public static final int FOCUS_RIGHT = 0x00000042;
    public static final int FOCUS_DOWN = 0x00000082;
    public static final int MEASURED_SIZE_MASK = 0x00ffffff;
    public static final int MEASURED_STATE_MASK = 0xff000000;
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;
    public static final int MEASURED_STATE_TOO_SMALL = 0x01000000;

    int mMeasuredWidth;
    int mMeasuredHeight;
    boolean mRecreateDisplayList = false;
    @IdRes
    int mID = NO_ID;
    // ID for accessibility purposes. This ID must be unique for every window
    protected Object mTag = null;
    // for mPrivateFlags:
    static final int PFLAG_WANTS_FOCUS = 0x00000001;
    static final int PFLAG_FOCUSED = 0x00000002;
    static final int PFLAG_SELECTED = 0x00000004;
    static final int PFLAG_IS_ROOT_NAMESPACE = 0x00000008;
    static final int PFLAG_HAS_BOUNDS = 0x00000010;
    static final int PFLAG_DRAWN = 0x00000020;
    static final int PFLAG_DRAW_ANIMATION = 0x00000040;
    static final int PFLAG_SKIP_DRAW = 0x00000080;
    static final int PFLAG_REQUEST_TRANSPARENT_REGIONS = 0x00000200;
    static final int PFLAG_DRAWABLE_STATE_DIRTY = 0x00000400;
    static final int PFLAG_MEASURED_DIMENSION_SET = 0x00000800;
    static final int PFLAG_FORCE_LAYOUT = 0x00001000;
    static final int PFLAG_LAYOUT_REQUIRED = 0x00002000;
    static final int PFLAG_DRAWING_CACHE_VALID = 0x00008000;
    static final int PFLAG_ANIMATION_STARTED = 0x00010000;
    static final int PFLAG_ALPHA_SET = 0x00040000;
    static final int PFLAG_SCROLL_CONTAINER = 0x00080000;
    static final int PFLAG_SCROLL_CONTAINER_ADDED = 0x00100000;
    static final int PFLAG_DIRTY = 0x00200000;
    static final int PFLAG_DIRTY_OPAQUE = 0x00400000;
    static final int PFLAG_DIRTY_MASK = 0x00600000;
    static final int PFLAG_OPAQUE_BACKGROUND = 0x00800000;
    static final int PFLAG_OPAQUE_SCROLLBARS = 0x01000000;
    static final int PFLAG_OPAQUE_MASK = 0x01800000;
    static final int PFLAG_CANCEL_NEXT_UP_EVENT = 0x04000000;
    static final int PFLAG_ACTIVATED = 0x40000000;
    static final int PFLAG_INVALIDATED = 0x80000000;
    static final int PFLAG2_DRAG_CAN_ACCEPT = 0x00000001;
    static final int PFLAG2_DRAG_HOVERED = 0x00000002;

    @Retention(RetentionPolicy.SOURCE)
    // Not called LayoutDirection to avoid conflict with
    // android.util.LayoutDirection
    public @interface LayoutDir {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResolvedLayoutDir {
    }

    public static final int LAYOUT_DIRECTION_UNDEFINED = LayoutDirection.UNDEFINED;
    public static final int LAYOUT_DIRECTION_LTR = LayoutDirection.LTR;
    public static final int LAYOUT_DIRECTION_RTL = LayoutDirection.RTL;
    public static final int LAYOUT_DIRECTION_INHERIT = LayoutDirection.INHERIT;
    public static final int LAYOUT_DIRECTION_LOCALE = LayoutDirection.LOCALE;
    static final int PFLAG2_LAYOUT_DIRECTION_MASK_SHIFT = 2;
    static final int PFLAG2_LAYOUT_DIRECTION_MASK = 0x00000003 << PFLAG2_LAYOUT_DIRECTION_MASK_SHIFT;
    static final int PFLAG2_LAYOUT_DIRECTION_RESOLVED_RTL = 4 << PFLAG2_LAYOUT_DIRECTION_MASK_SHIFT;
    static final int PFLAG2_LAYOUT_DIRECTION_RESOLVED = 8 << PFLAG2_LAYOUT_DIRECTION_MASK_SHIFT;
    static final int LAYOUT_DIRECTION_RESOLVED_DEFAULT = LAYOUT_DIRECTION_LTR;
    public static final int TEXT_DIRECTION_INHERIT = 0;
    public static final int TEXT_DIRECTION_FIRST_STRONG = 1;
    public static final int TEXT_DIRECTION_ANY_RTL = 2;
    public static final int TEXT_DIRECTION_LTR = 3;
    public static final int TEXT_DIRECTION_RTL = 4;
    public static final int TEXT_DIRECTION_LOCALE = 5;
    public static final int TEXT_DIRECTION_FIRST_STRONG_LTR = 6;
    public static final int TEXT_DIRECTION_FIRST_STRONG_RTL = 7;
    static final int TEXT_DIRECTION_RESOLVED_DEFAULT = TEXT_DIRECTION_FIRST_STRONG;
    static final int PFLAG2_TEXT_DIRECTION_MASK_SHIFT = 6;
    static final int PFLAG2_TEXT_DIRECTION_RESOLVED = 0x00000008 << PFLAG2_TEXT_DIRECTION_MASK_SHIFT;
    static final int PFLAG2_TEXT_DIRECTION_RESOLVED_MASK_SHIFT = 10;
    static final int PFLAG2_TEXT_DIRECTION_RESOLVED_MASK = 0x00000007 << PFLAG2_TEXT_DIRECTION_RESOLVED_MASK_SHIFT;
    static final int PFLAG2_TEXT_DIRECTION_RESOLVED_DEFAULT = TEXT_DIRECTION_RESOLVED_DEFAULT << PFLAG2_TEXT_DIRECTION_RESOLVED_MASK_SHIFT;

    @IntDef(prefix = { "TEXT_ALIGNMENT_" }, value = { TEXT_ALIGNMENT_INHERIT, TEXT_ALIGNMENT_GRAVITY,
            TEXT_ALIGNMENT_CENTER, TEXT_ALIGNMENT_TEXT_START, TEXT_ALIGNMENT_TEXT_END, TEXT_ALIGNMENT_VIEW_START,
            TEXT_ALIGNMENT_VIEW_END })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TextAlignment {
    }

    public static final int TEXT_ALIGNMENT_INHERIT = 0;
    public static final int TEXT_ALIGNMENT_GRAVITY = 1;
    public static final int TEXT_ALIGNMENT_TEXT_START = 2;
    public static final int TEXT_ALIGNMENT_TEXT_END = 3;
    public static final int TEXT_ALIGNMENT_CENTER = 4;
    public static final int TEXT_ALIGNMENT_VIEW_START = 5;
    public static final int TEXT_ALIGNMENT_VIEW_END = 6;
    static final int TEXT_ALIGNMENT_RESOLVED_DEFAULT = TEXT_ALIGNMENT_GRAVITY;
    static final int PFLAG2_TEXT_ALIGNMENT_MASK_SHIFT = 13;
    static final int PFLAG2_TEXT_ALIGNMENT_RESOLVED = 0x00000008 << PFLAG2_TEXT_ALIGNMENT_MASK_SHIFT;
    static final int PFLAG2_TEXT_ALIGNMENT_RESOLVED_MASK_SHIFT = 17;
    static final int PFLAG2_TEXT_ALIGNMENT_RESOLVED_MASK = 0x00000007 << PFLAG2_TEXT_ALIGNMENT_RESOLVED_MASK_SHIFT;
    // Accessiblity constants for mPrivateFlags2
    static final int PFLAG2_IMPORTANT_FOR_ACCESSIBILITY_SHIFT = 20;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0x00000000;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 0x00000001;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 0x00000002;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 0x00000004;
    static final int IMPORTANT_FOR_ACCESSIBILITY_DEFAULT = IMPORTANT_FOR_ACCESSIBILITY_AUTO;
    static final int PFLAG2_IMPORTANT_FOR_ACCESSIBILITY_MASK = (IMPORTANT_FOR_ACCESSIBILITY_AUTO
            | IMPORTANT_FOR_ACCESSIBILITY_YES | IMPORTANT_FOR_ACCESSIBILITY_NO
            | IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS) << PFLAG2_IMPORTANT_FOR_ACCESSIBILITY_SHIFT;
    static final int PFLAG2_ACCESSIBILITY_LIVE_REGION_SHIFT = 23;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0x00000000;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 0x00000001;
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 0x00000002;
    static final int ACCESSIBILITY_LIVE_REGION_DEFAULT = ACCESSIBILITY_LIVE_REGION_NONE;
    static final int PFLAG2_ACCESSIBILITY_LIVE_REGION_MASK = (ACCESSIBILITY_LIVE_REGION_NONE
            | ACCESSIBILITY_LIVE_REGION_POLITE
            | ACCESSIBILITY_LIVE_REGION_ASSERTIVE) << PFLAG2_ACCESSIBILITY_LIVE_REGION_SHIFT;
    static final int PFLAG2_ACCESSIBILITY_FOCUSED = 0x04000000;
    static final int PFLAG2_SUBTREE_ACCESSIBILITY_STATE_CHANGED = 0x08000000;
    static final int PFLAG2_VIEW_QUICK_REJECTED = 0x10000000;
    static final int PFLAG2_PADDING_RESOLVED = 0x20000000;
    static final int PFLAG2_DRAWABLE_RESOLVED = 0x40000000;
    static final int PFLAG2_HAS_TRANSIENT_STATE = 0x80000000;
    static final int ALL_RTL_PROPERTIES_RESOLVED = PFLAG2_LAYOUT_DIRECTION_RESOLVED | PFLAG2_TEXT_DIRECTION_RESOLVED
            | PFLAG2_TEXT_ALIGNMENT_RESOLVED | PFLAG2_PADDING_RESOLVED | PFLAG2_DRAWABLE_RESOLVED;
    // There are a couple of flags left in mPrivateFlags2
    static final int PFLAG3_VIEW_IS_ANIMATING_TRANSFORM = 0x1;
    static final int PFLAG3_VIEW_IS_ANIMATING_ALPHA = 0x2;
    static final int PFLAG3_IS_LAID_OUT = 0x4;
    static final int PFLAG3_MEASURE_NEEDED_BEFORE_LAYOUT = 0x8;
    static final int PFLAG3_CALLED_SUPER = 0x10;
    static final int PFLAG3_APPLYING_INSETS = 0x20;
    static final int PFLAG3_FITTING_SYSTEM_WINDOWS = 0x40;
    static final int PFLAG3_NESTED_SCROLLING_ENABLED = 0x80;
    static final int PFLAG3_SCROLL_INDICATOR_TOP = 0x0100;
    static final int PFLAG3_SCROLL_INDICATOR_BOTTOM = 0x0200;
    static final int PFLAG3_SCROLL_INDICATOR_LEFT = 0x0400;
    static final int PFLAG3_SCROLL_INDICATOR_RIGHT = 0x0800;
    static final int PFLAG3_SCROLL_INDICATOR_START = 0x1000;
    static final int PFLAG3_SCROLL_INDICATOR_END = 0x2000;
    static final int DRAG_MASK = PFLAG2_DRAG_CAN_ACCEPT | PFLAG2_DRAG_HOVERED;
    static final int SCROLL_INDICATORS_NONE = 0x0000;

    static final int SCROLL_INDICATORS_TO_PFLAGS3_LSHIFT = 8;

    public static final int SCROLL_INDICATOR_TOP = PFLAG3_SCROLL_INDICATOR_TOP >> SCROLL_INDICATORS_TO_PFLAGS3_LSHIFT;
    public static final int SCROLL_INDICATOR_BOTTOM = PFLAG3_SCROLL_INDICATOR_BOTTOM >> SCROLL_INDICATORS_TO_PFLAGS3_LSHIFT;
    public static final int SCROLL_INDICATOR_LEFT = PFLAG3_SCROLL_INDICATOR_LEFT >> SCROLL_INDICATORS_TO_PFLAGS3_LSHIFT;
    public static final int SCROLL_INDICATOR_RIGHT = PFLAG3_SCROLL_INDICATOR_RIGHT >> SCROLL_INDICATORS_TO_PFLAGS3_LSHIFT;
    public static final int SCROLL_INDICATOR_START = PFLAG3_SCROLL_INDICATOR_START >> SCROLL_INDICATORS_TO_PFLAGS3_LSHIFT;
    public static final int SCROLL_INDICATOR_END = PFLAG3_SCROLL_INDICATOR_END >> SCROLL_INDICATORS_TO_PFLAGS3_LSHIFT;
    static final int PFLAG3_ASSIST_BLOCKED = 0x4000;
    static final int PFLAG3_IMPORTANT_FOR_AUTOFILL_SHIFT = 19;
    static final int PFLAG3_IMPORTANT_FOR_AUTOFILL_MASK = (IMPORTANT_FOR_AUTOFILL_AUTO | IMPORTANT_FOR_AUTOFILL_YES
            | IMPORTANT_FOR_AUTOFILL_NO | IMPORTANT_FOR_AUTOFILL_YES_EXCLUDE_DESCENDANTS
            | IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS) << PFLAG3_IMPORTANT_FOR_AUTOFILL_SHIFT;
    static final int PFLAG3_TEMPORARY_DETACH = 0x2000000;
    static final int PFLAG3_NOTIFY_AUTOFILL_ENTER_ON_LAYOUT = 0x8000000;
    public static final int OVER_SCROLL_ALWAYS = 0;
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
    public static final int OVER_SCROLL_NEVER = 2;
    public static final int SYSTEM_UI_FLAG_VISIBLE = 0;
    public static final int SYSTEM_UI_FLAG_LOW_PROFILE = 0x00000001;
    public static final int SYSTEM_UI_FLAG_HIDE_NAVIGATION = 0x00000002;
    public static final int SYSTEM_UI_FLAG_FULLSCREEN = 0x00000004;
    public static final int SYSTEM_UI_FLAG_LAYOUT_STABLE = 0x00000100;
    public static final int SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION = 0x00000200;
    public static final int SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN = 0x00000400;
    public static final int SYSTEM_UI_FLAG_IMMERSIVE = 0x00000800;
    public static final int SYSTEM_UI_FLAG_IMMERSIVE_STICKY = 0x00001000;
    public static final int SYSTEM_UI_FLAG_LIGHT_STATUS_BAR = 0x00002000;
    public static final int SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR = 0x00000010;
    @Deprecated
    public static final int STATUS_BAR_HIDDEN = SYSTEM_UI_FLAG_LOW_PROFILE;
    @Deprecated
    public static final int STATUS_BAR_VISIBLE = SYSTEM_UI_FLAG_VISIBLE;
    public static final int STATUS_BAR_DISABLE_EXPAND = 0x00010000;
    public static final int STATUS_BAR_DISABLE_NOTIFICATION_ICONS = 0x00020000;
    public static final int STATUS_BAR_DISABLE_NOTIFICATION_ALERTS = 0x00040000;
    public static final int STATUS_BAR_DISABLE_NOTIFICATION_TICKER = 0x00080000;
    public static final int STATUS_BAR_DISABLE_SYSTEM_INFO = 0x00100000;
    public static final int STATUS_BAR_DISABLE_HOME = 0x00200000;
    public static final int STATUS_BAR_DISABLE_BACK = 0x00400000;
    public static final int STATUS_BAR_DISABLE_CLOCK = 0x00800000;
    public static final int STATUS_BAR_DISABLE_RECENT = 0x01000000;
    public static final int STATUS_BAR_DISABLE_SEARCH = 0x02000000;
    public static final int STATUS_BAR_TRANSIENT = 0x04000000;
    public static final int NAVIGATION_BAR_TRANSIENT = 0x08000000;
    public static final int STATUS_BAR_UNHIDE = 0x10000000;
    public static final int NAVIGATION_BAR_UNHIDE = 0x20000000;
    public static final int STATUS_BAR_TRANSLUCENT = 0x40000000;
    public static final int NAVIGATION_BAR_TRANSLUCENT = 0x80000000;
    public static final int NAVIGATION_BAR_TRANSPARENT = 0x00008000;
    public static final int STATUS_BAR_TRANSPARENT = 0x00000008;
    public static final int SYSTEM_UI_TRANSPARENT = NAVIGATION_BAR_TRANSPARENT | STATUS_BAR_TRANSPARENT;
    public static final int PUBLIC_STATUS_BAR_VISIBILITY_MASK = 0x00003FF7;
    public static final int SYSTEM_UI_CLEARABLE_FLAGS = SYSTEM_UI_FLAG_LOW_PROFILE | SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | SYSTEM_UI_FLAG_FULLSCREEN;
    public static final int SYSTEM_UI_LAYOUT_FLAGS = SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

    @IntDef(flag = true, prefix = { "FIND_VIEWS_" }, value = { FIND_VIEWS_WITH_TEXT,
            FIND_VIEWS_WITH_CONTENT_DESCRIPTION })
    @Retention(RetentionPolicy.SOURCE)
    public @interface FindViewFlags {
    }

    public static final int FIND_VIEWS_WITH_TEXT = 0x00000001;
    public static final int FIND_VIEWS_WITH_CONTENT_DESCRIPTION = 0x00000002;
    public static final int FIND_VIEWS_WITH_ACCESSIBILITY_NODE_PROVIDERS = 0x00000004;
    public static final int ACCESSIBILITY_CURSOR_POSITION_UNDEFINED = -1;
    public static final int SCREEN_STATE_OFF = 0x0;
    public static final int SCREEN_STATE_ON = 0x1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_HORIZONTAL = 1 << 0;
    public static final int SCROLL_AXIS_VERTICAL = 1 << 1;
    public int mPrivateFlags;
    int mPrivateFlags2;
    int mPrivateFlags3;
    int mSystemUiVisibility;
    int mTransientStateCount = 0;
    int mWindowAttachCount;
    int mViewFlags;

    static class TransformationInfo {
        float mAlpha = 1f;
        float mTransitionAlpha = 1f;
    }

    public TransformationInfo mTransformationInfo;
    protected int mLeft;
    protected int mRight;
    protected int mTop;
    protected int mBottom;
    protected int mScrollX;
    protected int mScrollY;
    protected int mPaddingLeft = 0;
    protected int mPaddingRight = 0;
    protected int mPaddingTop;
    protected int mPaddingBottom;
    protected int mUserPaddingRight;
    protected int mUserPaddingBottom;
    protected int mUserPaddingLeft;
    int mUserPaddingStart;
    int mUserPaddingEnd;
    int mUserPaddingLeftInitial;
    int mUserPaddingRightInitial;
    int mOldWidthMeasureSpec = Integer.MIN_VALUE;
    int mOldHeightMeasureSpec = Integer.MIN_VALUE;

    @Override
    public void sendAccessibilityEvent(int eventType) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendAccessibilityEventUnchecked(AccessibilityEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    public int getWidth() {
        throw new RuntimeException("Stub!");
    }

    public int getHeight() {
        throw new RuntimeException("Stub!");
    }

    public interface OnSystemUiVisibilityChangeListener {
        public void onSystemUiVisibilityChange(int visibility);
    }

    public interface OnAttachStateChangeListener {
        public void onViewAttachedToWindow(View v);

        public void onViewDetachedFromWindow(View v);
    }

}