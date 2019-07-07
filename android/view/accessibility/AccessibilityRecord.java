
package android.view.accessibility;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcelable;
import android.view.View;

public class AccessibilityRecord {
    protected static final boolean DEBUG_CONCISE_TOSTRING = false;

    boolean mSealed;
    int mBooleanProperties = 0;
    long mSourceNodeId = AccessibilityNodeInfo.UNDEFINED_NODE_ID;
    int mSourceWindowId = AccessibilityWindowInfo.UNDEFINED_WINDOW_ID;

    CharSequence mClassName;
    CharSequence mContentDescription;
    CharSequence mBeforeText;
    Parcelable mParcelableData;

    final List<CharSequence> mText = new ArrayList<CharSequence>();

    AccessibilityRecord() {
        throw new RuntimeException("Stub!");
    }

    public void setSource(View source) {
        throw new RuntimeException("Stub!");
    }

    public void setWindowId(int windowId) {
        throw new RuntimeException("Stub!");
    }

    public int getWindowId() {
        throw new RuntimeException("Stub!");
    }

    public boolean isChecked() {
        throw new RuntimeException("Stub!");
    }

    public void setChecked(boolean isChecked) {
        throw new RuntimeException("Stub!");
    }

    public boolean isEnabled() {
        throw new RuntimeException("Stub!");
    }

    public void setEnabled(boolean isEnabled) {
        throw new RuntimeException("Stub!");
    }

    public boolean isPassword() {
        throw new RuntimeException("Stub!");
    }

    public void setPassword(boolean isPassword) {
        throw new RuntimeException("Stub!");
    }

    public boolean isFullScreen() {
        throw new RuntimeException("Stub!");
    }

    public void setFullScreen(boolean isFullScreen) {
        throw new RuntimeException("Stub!");
    }

    public boolean isScrollable() {
        throw new RuntimeException("Stub!");
    }

    public void setScrollable(boolean scrollable) {
        throw new RuntimeException("Stub!");
    }

    public boolean isImportantForAccessibility() {
        throw new RuntimeException("Stub!");
    }

    public void setImportantForAccessibility(boolean importantForAccessibility) {
        throw new RuntimeException("Stub!");
    }

    public int getItemCount() {
        throw new RuntimeException("Stub!");
    }

    public void setItemCount(int itemCount) {
        throw new RuntimeException("Stub!");
    }

    public int getCurrentItemIndex() {
        throw new RuntimeException("Stub!");
    }

    public void setCurrentItemIndex(int currentItemIndex) {
        throw new RuntimeException("Stub!");
    }

    public int getFromIndex() {
        throw new RuntimeException("Stub!");
    }

    public void setFromIndex(int fromIndex) {
        throw new RuntimeException("Stub!");
    }

    public int getToIndex() {
        throw new RuntimeException("Stub!");
    }

    public void setToIndex(int toIndex) {
        throw new RuntimeException("Stub!");
    }

    public int getScrollX() {
        throw new RuntimeException("Stub!");
    }

    public void setScrollX(int scrollX) {
        throw new RuntimeException("Stub!");
    }

    public int getScrollY() {
        throw new RuntimeException("Stub!");
    }

    public void setScrollY(int scrollY) {
        throw new RuntimeException("Stub!");
    }

    public int getScrollDeltaX() {
        throw new RuntimeException("Stub!");
    }

    public void setScrollDeltaX(int scrollDeltaX) {
        throw new RuntimeException("Stub!");
    }

    public int getScrollDeltaY() {
        throw new RuntimeException("Stub!");
    }

    public void setScrollDeltaY(int scrollDeltaY) {
        throw new RuntimeException("Stub!");
    }

    public int getMaxScrollX() {
        throw new RuntimeException("Stub!");
    }

    public void setMaxScrollX(int maxScrollX) {
        throw new RuntimeException("Stub!");
    }

    public int getMaxScrollY() {
        throw new RuntimeException("Stub!");
    }

    public void setMaxScrollY(int maxScrollY) {
        throw new RuntimeException("Stub!");
    }

    public int getAddedCount() {
        throw new RuntimeException("Stub!");
    }

    public void setAddedCount(int addedCount) {
        throw new RuntimeException("Stub!");
    }

    public int getRemovedCount() {
        throw new RuntimeException("Stub!");
    }

    public void setRemovedCount(int removedCount) {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getClassName() {
        throw new RuntimeException("Stub!");
    }

    public void setClassName(CharSequence className) {
        throw new RuntimeException("Stub!");
    }

    public List<CharSequence> getText() {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getBeforeText() {
        throw new RuntimeException("Stub!");
    }

    public void setBeforeText(CharSequence beforeText) {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getContentDescription() {
        throw new RuntimeException("Stub!");
    }

    public void setContentDescription(CharSequence contentDescription) {
        throw new RuntimeException("Stub!");
    }

    public Parcelable getParcelableData() {
        throw new RuntimeException("Stub!");
    }

    public void setParcelableData(Parcelable parcelableData) {
        throw new RuntimeException("Stub!");
    }

    public long getSourceNodeId() {
        throw new RuntimeException("Stub!");
    }

    public void setConnectionId(int connectionId) {
        throw new RuntimeException("Stub!");
    }

    public void setSealed(boolean sealed) {
        throw new RuntimeException("Stub!");
    }

    boolean isSealed() {
        throw new RuntimeException("Stub!");
    }

    void enforceSealed() {
        throw new RuntimeException("Stub!");
    }

    void enforceNotSealed() {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityRecord obtain(AccessibilityRecord record) {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityRecord obtain() {
        throw new RuntimeException("Stub!");
    }

    public void recycle() {
        throw new RuntimeException("Stub!");
    }

    void init(AccessibilityRecord record) {
        throw new RuntimeException("Stub!");
    }

    void clear() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    StringBuilder appendTo(StringBuilder builder) {
        throw new RuntimeException("Stub!");
    }
}
