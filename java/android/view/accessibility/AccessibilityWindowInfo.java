package android.view.accessibility;

import java.util.concurrent.atomic.AtomicInteger;
import android.annotation.Nullable;
import android.annotation.TestApi;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.accessibility.AccessibilityEvent.WindowsChangeTypes;

public final class AccessibilityWindowInfo implements Parcelable {
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_SYSTEM = 3;
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int ACTIVE_WINDOW_ID = Integer.MAX_VALUE;
    public static final int UNDEFINED_WINDOW_ID = -1;
    public static final int ANY_WINDOW_ID = -2;
    public static final int PICTURE_IN_PICTURE_ACTION_REPLACER_WINDOW_ID = -3;

    @Nullable
    public CharSequence getTitle() {
        throw new RuntimeException("Stub!");
    }

    public void setTitle(CharSequence title) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void setType(int type) {
        throw new RuntimeException("Stub!");
    }

    public int getLayer() {
        throw new RuntimeException("Stub!");
    }

    public void setLayer(int layer) {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo getRoot() {
        throw new RuntimeException("Stub!");
    }

    public void setAnchorId(long anchorId) {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo getAnchor() {
        throw new RuntimeException("Stub!");
    }

    public void setPictureInPicture(boolean pictureInPicture) {
        throw new RuntimeException("Stub!");
    }

    public boolean isInPictureInPictureMode() {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityWindowInfo getParent() {
        throw new RuntimeException("Stub!");
    }

    public void setParentId(int parentId) {
        throw new RuntimeException("Stub!");
    }

    public int getId() {
        throw new RuntimeException("Stub!");
    }

    public void setId(int id) {
        throw new RuntimeException("Stub!");
    }

    public void setConnectionId(int connectionId) {
        throw new RuntimeException("Stub!");
    }

    public void getBoundsInScreen(Rect outBounds) {
        throw new RuntimeException("Stub!");
    }

    public void setBoundsInScreen(Rect bounds) {
        throw new RuntimeException("Stub!");
    }

    public boolean isActive() {
        throw new RuntimeException("Stub!");
    }

    public void setActive(boolean active) {
        throw new RuntimeException("Stub!");
    }

    public boolean isFocused() {
        throw new RuntimeException("Stub!");
    }

    public void setFocused(boolean focused) {
        throw new RuntimeException("Stub!");
    }

    public boolean isAccessibilityFocused() {
        throw new RuntimeException("Stub!");
    }

    public void setAccessibilityFocused(boolean focused) {
        throw new RuntimeException("Stub!");
    }

    public int getChildCount() {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityWindowInfo getChild(int index) {
        throw new RuntimeException("Stub!");
    }

    public void addChild(int childId) {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityWindowInfo obtain() {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityWindowInfo obtain(AccessibilityWindowInfo info) {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    public static void setNumInstancesInUseCounter(AtomicInteger counter) {
        throw new RuntimeException("Stub!");
    }

    public void recycle() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public boolean changed(AccessibilityWindowInfo other) {
        throw new RuntimeException("Stub!");
    }

    @WindowsChangeTypes
    public int differenceFrom(AccessibilityWindowInfo other) {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<AccessibilityWindowInfo> CREATOR = new Creator<AccessibilityWindowInfo>() {
        @Override
        public AccessibilityWindowInfo createFromParcel(Parcel parcel) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public AccessibilityWindowInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}