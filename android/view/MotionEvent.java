package android.view;

import android.annotation.TestApi;
import android.graphics.Matrix;
import android.os.Parcel;
import android.os.Parcelable;

public final class MotionEvent extends InputEvent {
    public static final int INVALID_POINTER_ID = -1;
    public static final int ACTION_MASK = 0xff;
    public static final int ACTION_DOWN = 0;
    public static final int ACTION_UP = 1;
    public static final int ACTION_MOVE = 2;
    public static final int ACTION_CANCEL = 3;
    public static final int ACTION_OUTSIDE = 4;
    public static final int ACTION_POINTER_DOWN = 5;
    public static final int ACTION_POINTER_UP = 6;
    public static final int ACTION_HOVER_MOVE = 7;
    public static final int ACTION_SCROLL = 8;
    public static final int ACTION_HOVER_ENTER = 9;
    public static final int ACTION_HOVER_EXIT = 10;
    public static final int ACTION_BUTTON_PRESS = 11;
    public static final int ACTION_BUTTON_RELEASE = 12;
    public static final int ACTION_POINTER_INDEX_MASK = 0xff00;
    public static final int ACTION_POINTER_INDEX_SHIFT = 8;
    @Deprecated
    public static final int ACTION_POINTER_1_DOWN = ACTION_POINTER_DOWN | 0x0000;
    @Deprecated
    public static final int ACTION_POINTER_2_DOWN = ACTION_POINTER_DOWN | 0x0100;
    @Deprecated
    public static final int ACTION_POINTER_3_DOWN = ACTION_POINTER_DOWN | 0x0200;
    @Deprecated
    public static final int ACTION_POINTER_1_UP = ACTION_POINTER_UP | 0x0000;
    @Deprecated
    public static final int ACTION_POINTER_2_UP = ACTION_POINTER_UP | 0x0100;
    @Deprecated
    public static final int ACTION_POINTER_3_UP = ACTION_POINTER_UP | 0x0200;
    @Deprecated
    public static final int ACTION_POINTER_ID_MASK = 0xff00;
    @Deprecated
    public static final int ACTION_POINTER_ID_SHIFT = 8;
    public static final int FLAG_WINDOW_IS_OBSCURED = 0x1;
    public static final int FLAG_WINDOW_IS_PARTIALLY_OBSCURED = 0x2;
    public static final int FLAG_HOVER_EXIT_PENDING = 0x4;
    public static final int FLAG_IS_GENERATED_GESTURE = 0x8;
    public static final int FLAG_TAINTED = 0x80000000;
    public static final int FLAG_TARGET_ACCESSIBILITY_FOCUS = 0x40000000;
    public static final int EDGE_TOP = 0x00000001;
    public static final int EDGE_BOTTOM = 0x00000002;
    public static final int EDGE_LEFT = 0x00000004;
    public static final int EDGE_RIGHT = 0x00000008;
    public static final int AXIS_X = 0;
    public static final int AXIS_Y = 1;
    public static final int AXIS_PRESSURE = 2;
    public static final int AXIS_SIZE = 3;
    public static final int AXIS_TOUCH_MAJOR = 4;
    public static final int AXIS_TOUCH_MINOR = 5;
    public static final int AXIS_TOOL_MAJOR = 6;
    public static final int AXIS_TOOL_MINOR = 7;
    public static final int AXIS_ORIENTATION = 8;
    public static final int AXIS_VSCROLL = 9;
    public static final int AXIS_HSCROLL = 10;
    public static final int AXIS_Z = 11;
    public static final int AXIS_RX = 12;
    public static final int AXIS_RY = 13;
    public static final int AXIS_RZ = 14;
    public static final int AXIS_HAT_X = 15;
    public static final int AXIS_HAT_Y = 16;
    public static final int AXIS_LTRIGGER = 17;
    public static final int AXIS_RTRIGGER = 18;
    public static final int AXIS_THROTTLE = 19;
    public static final int AXIS_RUDDER = 20;
    public static final int AXIS_WHEEL = 21;
    public static final int AXIS_GAS = 22;
    public static final int AXIS_BRAKE = 23;
    public static final int AXIS_DISTANCE = 24;
    public static final int AXIS_TILT = 25;
    public static final int AXIS_SCROLL = 26;
    public static final int AXIS_RELATIVE_X = 27;
    public static final int AXIS_RELATIVE_Y = 28;
    public static final int AXIS_GENERIC_1 = 32;
    public static final int AXIS_GENERIC_2 = 33;
    public static final int AXIS_GENERIC_3 = 34;
    public static final int AXIS_GENERIC_4 = 35;
    public static final int AXIS_GENERIC_5 = 36;
    public static final int AXIS_GENERIC_6 = 37;
    public static final int AXIS_GENERIC_7 = 38;
    public static final int AXIS_GENERIC_8 = 39;
    public static final int AXIS_GENERIC_9 = 40;
    public static final int AXIS_GENERIC_10 = 41;
    public static final int AXIS_GENERIC_11 = 42;
    public static final int AXIS_GENERIC_12 = 43;
    public static final int AXIS_GENERIC_13 = 44;
    public static final int AXIS_GENERIC_14 = 45;
    public static final int AXIS_GENERIC_15 = 46;
    public static final int AXIS_GENERIC_16 = 47;
    public static final int BUTTON_PRIMARY = 1 << 0;
    public static final int BUTTON_SECONDARY = 1 << 1;
    public static final int BUTTON_TERTIARY = 1 << 2;
    public static final int BUTTON_BACK = 1 << 3;
    public static final int BUTTON_FORWARD = 1 << 4;
    public static final int BUTTON_STYLUS_PRIMARY = 1 << 5;
    public static final int BUTTON_STYLUS_SECONDARY = 1 << 6;
    public static final int TOOL_TYPE_UNKNOWN = 0;
    public static final int TOOL_TYPE_FINGER = 1;
    public static final int TOOL_TYPE_STYLUS = 2;
    public static final int TOOL_TYPE_MOUSE = 3;
    public static final int TOOL_TYPE_ERASER = 4;

    @Override
    protected void finalize() throws Throwable {
        throw new RuntimeException("Stub!");
    }

    static public MotionEvent obtain(long downTime, long eventTime, int action, int pointerCount,
            PointerProperties[] pointerProperties, PointerCoords[] pointerCoords, int metaState, int buttonState,
            float xPrecision, float yPrecision, int deviceId, int edgeFlags, int source, int flags) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    static public MotionEvent obtain(long downTime, long eventTime, int action, int pointerCount, int[] pointerIds,
            PointerCoords[] pointerCoords, int metaState, float xPrecision, float yPrecision, int deviceId,
            int edgeFlags, int source, int flags) {
        throw new RuntimeException("Stub!");
    }

    static public MotionEvent obtain(long downTime, long eventTime, int action, float x, float y, float pressure,
            float size, int metaState, float xPrecision, float yPrecision, int deviceId, int edgeFlags) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    static public MotionEvent obtain(long downTime, long eventTime, int action, int pointerCount, float x, float y,
            float pressure, float size, int metaState, float xPrecision, float yPrecision, int deviceId,
            int edgeFlags) {
        throw new RuntimeException("Stub!");
    }

    static public MotionEvent obtain(long downTime, long eventTime, int action, float x, float y, int metaState) {
        throw new RuntimeException("Stub!");
    }

    static public MotionEvent obtain(MotionEvent other) {
        throw new RuntimeException("Stub!");
    }

    static public MotionEvent obtainNoHistory(MotionEvent other) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public MotionEvent copy() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public final void recycle() {
        throw new RuntimeException("Stub!");
    }

    public final void scale(float scale) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public final int getDeviceId() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public final int getSource() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public final void setSource(int source) {
        throw new RuntimeException("Stub!");
    }

    public final int getAction() {
        throw new RuntimeException("Stub!");
    }

    public final int getActionMasked() {
        throw new RuntimeException("Stub!");
    }

    public final int getActionIndex() {
        throw new RuntimeException("Stub!");
    }

    public final boolean isTouchEvent() {
        throw new RuntimeException("Stub!");
    }

    public final int getFlags() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public final boolean isTainted() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public final void setTainted(boolean tainted) {
        throw new RuntimeException("Stub!");
    }

    public final boolean isTargetAccessibilityFocus() {
        throw new RuntimeException("Stub!");
    }

    public final void setTargetAccessibilityFocus(boolean targetsFocus) {
        throw new RuntimeException("Stub!");
    }

    public final boolean isHoverExitPending() {
        throw new RuntimeException("Stub!");
    }

    public void setHoverExitPending(boolean hoverExitPending) {
        throw new RuntimeException("Stub!");
    }

    public final long getDownTime() {
        throw new RuntimeException("Stub!");
    }

    public final void setDownTime(long downTime) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public final long getEventTime() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public final long getEventTimeNano() {
        throw new RuntimeException("Stub!");
    }

    public final float getX() {
        throw new RuntimeException("Stub!");
    }

    public final float getY() {
        throw new RuntimeException("Stub!");
    }

    public final float getPressure() {
        throw new RuntimeException("Stub!");
    }

    public final float getSize() {
        throw new RuntimeException("Stub!");
    }

    public final float getTouchMajor() {
        throw new RuntimeException("Stub!");
    }

    public final float getTouchMinor() {
        throw new RuntimeException("Stub!");
    }

    public final float getToolMajor() {
        throw new RuntimeException("Stub!");
    }

    public final float getToolMinor() {
        throw new RuntimeException("Stub!");
    }

    public final float getOrientation() {
        throw new RuntimeException("Stub!");
    }

    public final float getAxisValue(int axis) {
        throw new RuntimeException("Stub!");
    }

    public final int getPointerCount() {
        throw new RuntimeException("Stub!");
    }

    public final int getPointerId(int pointerIndex) {
        throw new RuntimeException("Stub!");
    }

    public final int getToolType(int pointerIndex) {
        throw new RuntimeException("Stub!");
    }

    public final int findPointerIndex(int pointerId) {
        throw new RuntimeException("Stub!");
    }

    public final float getX(int pointerIndex) {
        throw new RuntimeException("Stub!");
    }

    public final float getY(int pointerIndex) {
        throw new RuntimeException("Stub!");
    }

    public final float getPressure(int pointerIndex) {
        throw new RuntimeException("Stub!");
    }

    public final float getSize(int pointerIndex) {
        throw new RuntimeException("Stub!");
    }

    public final float getTouchMajor(int pointerIndex) {
        throw new RuntimeException("Stub!");
    }

    public final float getTouchMinor(int pointerIndex) {
        throw new RuntimeException("Stub!");
    }

    public final float getToolMajor(int pointerIndex) {
        throw new RuntimeException("Stub!");
    }

    public final float getToolMinor(int pointerIndex) {
        throw new RuntimeException("Stub!");
    }

    public final float getOrientation(int pointerIndex) {
        throw new RuntimeException("Stub!");
    }

    public final float getAxisValue(int axis, int pointerIndex) {
        throw new RuntimeException("Stub!");
    }

    public final void getPointerCoords(int pointerIndex, PointerCoords outPointerCoords) {
        throw new RuntimeException("Stub!");
    }

    public final void getPointerProperties(int pointerIndex, PointerProperties outPointerProperties) {
        throw new RuntimeException("Stub!");
    }

    public final int getMetaState() {
        throw new RuntimeException("Stub!");
    }

    public final int getButtonState() {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    public final void setButtonState(int buttonState) {
        throw new RuntimeException("Stub!");
    }

    public final int getActionButton() {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    public final void setActionButton(int button) {
        throw new RuntimeException("Stub!");
    }

    public final float getRawX() {
        throw new RuntimeException("Stub!");
    }

    public final float getRawY() {
        throw new RuntimeException("Stub!");
    }

    public final float getXPrecision() {
        throw new RuntimeException("Stub!");
    }

    public final float getYPrecision() {
        throw new RuntimeException("Stub!");
    }

    public final int getHistorySize() {
        throw new RuntimeException("Stub!");
    }

    public final long getHistoricalEventTime(int pos) {
        throw new RuntimeException("Stub!");
    }

    public final long getHistoricalEventTimeNano(int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalX(int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalY(int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalPressure(int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalSize(int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalTouchMajor(int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalTouchMinor(int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalToolMajor(int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalToolMinor(int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalOrientation(int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalAxisValue(int axis, int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalX(int pointerIndex, int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalY(int pointerIndex, int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalPressure(int pointerIndex, int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalSize(int pointerIndex, int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalTouchMajor(int pointerIndex, int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalTouchMinor(int pointerIndex, int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalToolMajor(int pointerIndex, int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalToolMinor(int pointerIndex, int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalOrientation(int pointerIndex, int pos) {
        throw new RuntimeException("Stub!");
    }

    public final float getHistoricalAxisValue(int axis, int pointerIndex, int pos) {
        throw new RuntimeException("Stub!");
    }

    public final void getHistoricalPointerCoords(int pointerIndex, int pos, PointerCoords outPointerCoords) {
        throw new RuntimeException("Stub!");
    }

    public final int getEdgeFlags() {
        throw new RuntimeException("Stub!");
    }

    public final void setEdgeFlags(int flags) {
        throw new RuntimeException("Stub!");
    }

    public final void setAction(int action) {
        throw new RuntimeException("Stub!");
    }

    public final void offsetLocation(float deltaX, float deltaY) {
        throw new RuntimeException("Stub!");
    }

    public final void setLocation(float x, float y) {
        throw new RuntimeException("Stub!");
    }

    public final void transform(Matrix matrix) {
        throw new RuntimeException("Stub!");
    }

    public final void addBatch(long eventTime, float x, float y, float pressure, float size, int metaState) {
        throw new RuntimeException("Stub!");
    }

    public final void addBatch(long eventTime, PointerCoords[] pointerCoords, int metaState) {
        throw new RuntimeException("Stub!");
    }

    public final boolean addBatch(MotionEvent event) {
        throw new RuntimeException("Stub!");
    }

    public final boolean isWithinBoundsNoHistory(float left, float top, float right, float bottom) {
        throw new RuntimeException("Stub!");
    }

    public final MotionEvent clampNoHistory(float left, float top, float right, float bottom) {
        throw new RuntimeException("Stub!");
    }

    public final int getPointerIdBits() {
        throw new RuntimeException("Stub!");
    }

    public final MotionEvent split(int idBits) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public static String actionToString(int action) {
        throw new RuntimeException("Stub!");
    }

    public static String axisToString(int axis) {
        throw new RuntimeException("Stub!");
    }

    public static int axisFromString(String symbolicName) {
        throw new RuntimeException("Stub!");
    }

    public static String buttonStateToString(int buttonState) {
        throw new RuntimeException("Stub!");
    }

    public static String toolTypeToString(int toolType) {
        throw new RuntimeException("Stub!");
    }

    public final boolean isButtonPressed(int button) {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<MotionEvent> CREATOR = new Parcelable.Creator<MotionEvent>() {
        public MotionEvent createFromParcel(Parcel in) {
            throw new RuntimeException("Stub!");
        }

        public MotionEvent[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    public static MotionEvent createFromParcelBody(Parcel in) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public final void cancel() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel out, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static final class PointerCoords {
        public PointerCoords() {
            throw new RuntimeException("Stub!");
        }

        public PointerCoords(PointerCoords other) {
            throw new RuntimeException("Stub!");
        }

        public static PointerCoords[] createArray(int size) {
            throw new RuntimeException("Stub!");
        }

        public float x;
        public float y;
        public float pressure;
        public float size;
        public float touchMajor;
        public float touchMinor;
        public float toolMajor;
        public float toolMinor;
        public float orientation;

        public void clear() {
            throw new RuntimeException("Stub!");
        }

        public void copyFrom(PointerCoords other) {
            throw new RuntimeException("Stub!");
        }

        public float getAxisValue(int axis) {
            throw new RuntimeException("Stub!");
        }

        public void setAxisValue(int axis, float value) {
            throw new RuntimeException("Stub!");
        }
    }

    public static final class PointerProperties {
        public PointerProperties() {
            throw new RuntimeException("Stub!");
        }

        public PointerProperties(PointerProperties other) {
            throw new RuntimeException("Stub!");
        }

        public static PointerProperties[] createArray(int size) {
            throw new RuntimeException("Stub!");
        }

        public int id;
        public int toolType;

        public void clear() {
            throw new RuntimeException("Stub!");
        }

        public void copyFrom(PointerProperties other) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public boolean equals(Object other) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public int hashCode() {
            throw new RuntimeException("Stub!");
        }
    }
}