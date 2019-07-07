
package android.view.accessibility;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.annotation.IntDef;
import android.os.Parcel;
import android.os.Parcelable;

public final class AccessibilityEvent extends AccessibilityRecord implements Parcelable {
    public static final boolean DEBUG_ORIGIN = false;

    public static final int INVALID_POSITION = -1;

    @Deprecated
    public static final int MAX_TEXT_LENGTH = 500;

    public static final int TYPE_VIEW_CLICKED = 0x00000001;

    public static final int TYPE_VIEW_LONG_CLICKED = 0x00000002;

    public static final int TYPE_VIEW_SELECTED = 0x00000004;

    public static final int TYPE_VIEW_FOCUSED = 0x00000008;

    public static final int TYPE_VIEW_TEXT_CHANGED = 0x00000010;

    public static final int TYPE_WINDOW_STATE_CHANGED = 0x00000020;

    public static final int TYPE_NOTIFICATION_STATE_CHANGED = 0x00000040;

    public static final int TYPE_VIEW_HOVER_ENTER = 0x00000080;

    public static final int TYPE_VIEW_HOVER_EXIT = 0x00000100;

    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 0x00000200;

    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 0x00000400;

    public static final int TYPE_WINDOW_CONTENT_CHANGED = 0x00000800;

    public static final int TYPE_VIEW_SCROLLED = 0x00001000;

    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 0x00002000;

    public static final int TYPE_ANNOUNCEMENT = 0x00004000;

    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 0x00008000;

    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 0x00010000;

    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 0x00020000;

    public static final int TYPE_GESTURE_DETECTION_START = 0x00040000;

    public static final int TYPE_GESTURE_DETECTION_END = 0x00080000;

    public static final int TYPE_TOUCH_INTERACTION_START = 0x00100000;

    public static final int TYPE_TOUCH_INTERACTION_END = 0x00200000;

    public static final int TYPE_WINDOWS_CHANGED = 0x00400000;

    public static final int TYPE_VIEW_CONTEXT_CLICKED = 0x00800000;

    public static final int TYPE_ASSIST_READING_CONTEXT = 0x01000000;

    public static final int CONTENT_CHANGE_TYPE_UNDEFINED = 0x00000000;

    public static final int CONTENT_CHANGE_TYPE_SUBTREE = 0x00000001;

    public static final int CONTENT_CHANGE_TYPE_TEXT = 0x00000002;

    public static final int CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION = 0x00000004;

    public static final int CONTENT_CHANGE_TYPE_PANE_TITLE = 0x00000008;

    public static final int CONTENT_CHANGE_TYPE_PANE_APPEARED = 0x00000010;

    public static final int CONTENT_CHANGE_TYPE_PANE_DISAPPEARED = 0x00000020;

    public static final int WINDOWS_CHANGE_ADDED = 0x00000001;

    public static final int WINDOWS_CHANGE_REMOVED = 0x00000002;

    public static final int WINDOWS_CHANGE_TITLE = 0x00000004;

    public static final int WINDOWS_CHANGE_BOUNDS = 0x00000008;

    public static final int WINDOWS_CHANGE_LAYER = 0x00000010;

    public static final int WINDOWS_CHANGE_ACTIVE = 0x00000020;

    public static final int WINDOWS_CHANGE_FOCUSED = 0x00000040;

    public static final int WINDOWS_CHANGE_ACCESSIBILITY_FOCUSED = 0x00000080;

    public static final int WINDOWS_CHANGE_PARENT = 0x00000100;

    public static final int WINDOWS_CHANGE_CHILDREN = 0x00000200;

    public static final int WINDOWS_CHANGE_PIP = 0x00000400;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(flag = true, prefix = { "WINDOWS_CHANGE_" }, value = { WINDOWS_CHANGE_ADDED, WINDOWS_CHANGE_REMOVED,
            WINDOWS_CHANGE_TITLE, WINDOWS_CHANGE_BOUNDS, WINDOWS_CHANGE_LAYER, WINDOWS_CHANGE_ACTIVE,
            WINDOWS_CHANGE_FOCUSED, WINDOWS_CHANGE_ACCESSIBILITY_FOCUSED, WINDOWS_CHANGE_PARENT,
            WINDOWS_CHANGE_CHILDREN, WINDOWS_CHANGE_PIP })
    public @interface WindowsChangeTypes {
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(flag = true, prefix = { "CONTENT_CHANGE_TYPE_" }, value = { CONTENT_CHANGE_TYPE_UNDEFINED,
            CONTENT_CHANGE_TYPE_SUBTREE, CONTENT_CHANGE_TYPE_TEXT, CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION,
            CONTENT_CHANGE_TYPE_PANE_TITLE })
    public @interface ContentChangeTypes {
    }

    @IntDef(flag = true, prefix = { "TYPE_" }, value = { TYPE_VIEW_CLICKED, TYPE_VIEW_LONG_CLICKED, TYPE_VIEW_SELECTED,
            TYPE_VIEW_FOCUSED, TYPE_VIEW_TEXT_CHANGED, TYPE_WINDOW_STATE_CHANGED, TYPE_NOTIFICATION_STATE_CHANGED,
            TYPE_VIEW_HOVER_ENTER, TYPE_VIEW_HOVER_EXIT, TYPE_TOUCH_EXPLORATION_GESTURE_START,
            TYPE_TOUCH_EXPLORATION_GESTURE_END, TYPE_WINDOW_CONTENT_CHANGED, TYPE_VIEW_SCROLLED,
            TYPE_VIEW_TEXT_SELECTION_CHANGED, TYPE_ANNOUNCEMENT, TYPE_VIEW_ACCESSIBILITY_FOCUSED,
            TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED, TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY,
            TYPE_GESTURE_DETECTION_START, TYPE_GESTURE_DETECTION_END, TYPE_TOUCH_INTERACTION_START,
            TYPE_TOUCH_INTERACTION_END, TYPE_WINDOWS_CHANGED, TYPE_VIEW_CONTEXT_CLICKED, TYPE_ASSIST_READING_CONTEXT })
    @Retention(RetentionPolicy.SOURCE)
    public @interface EventType {
    }

    public static final int TYPES_ALL_MASK = 0xFFFFFFFF;

    int mMovementGranularity;
    int mAction;
    int mContentChangeTypes;
    int mWindowChangeTypes;

    public StackTraceElement[] originStackTrace = null;

    private AccessibilityEvent() {
        throw new RuntimeException("Stub!");
    }

    public @EventType int getEventType() {
        throw new RuntimeException("Stub!");
    }

    @ContentChangeTypes
    public int getContentChangeTypes() {
        throw new RuntimeException("Stub!");
    }

    public void setContentChangeTypes(@ContentChangeTypes int changeTypes) {
        throw new RuntimeException("Stub!");
    }

    @WindowsChangeTypes
    public int getWindowChanges() {
        throw new RuntimeException("Stub!");
    }

    public void setWindowChanges(@WindowsChangeTypes int changes) {
        throw new RuntimeException("Stub!");
    }

    public void setEventType(@EventType int eventType) {
        throw new RuntimeException("Stub!");
    }

    public long getEventTime() {
        throw new RuntimeException("Stub!");
    }

    public void setEventTime(long eventTime) {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getPackageName() {
        throw new RuntimeException("Stub!");
    }

    public void setPackageName(CharSequence packageName) {
        throw new RuntimeException("Stub!");
    }

    public void setMovementGranularity(int granularity) {
        throw new RuntimeException("Stub!");
    }

    public int getMovementGranularity() {
        throw new RuntimeException("Stub!");
    }

    public void setAction(int action) {
        throw new RuntimeException("Stub!");
    }

    public int getAction() {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityEvent obtainWindowsChangedEvent(int windowId, int windowChangeTypes) {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityEvent obtain(int eventType) {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityEvent obtain(AccessibilityEvent event) {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityEvent obtain() {
        throw new RuntimeException("Stub!");
    }

    public static String eventTypeToString(int eventType) {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<AccessibilityEvent> CREATOR = new Parcelable.Creator<AccessibilityEvent>() {
        public AccessibilityEvent createFromParcel(Parcel parcel) {
            throw new RuntimeException("Stub!");
        }

        public AccessibilityEvent[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }
}
