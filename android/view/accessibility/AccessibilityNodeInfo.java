package android.view.accessibility;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import android.annotation.Nullable;
import android.annotation.TestApi;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import android.util.LongArray;
import android.view.View;

public class AccessibilityNodeInfo implements Parcelable {
    public static final int UNDEFINED_CONNECTION_ID = -1;
    public static final int UNDEFINED_SELECTION_INDEX = -1;
    public static final int UNDEFINED_ITEM_ID = Integer.MAX_VALUE;
    public static final int ROOT_ITEM_ID = Integer.MAX_VALUE - 1;
    public static final long UNDEFINED_NODE_ID = makeNodeId(UNDEFINED_ITEM_ID, UNDEFINED_ITEM_ID);
    public static final long ROOT_NODE_ID = makeNodeId(ROOT_ITEM_ID, AccessibilityNodeProvider.HOST_VIEW_ID);
    public static final int FLAG_PREFETCH_PREDECESSORS = 0x00000001;
    public static final int FLAG_PREFETCH_SIBLINGS = 0x00000002;
    public static final int FLAG_PREFETCH_DESCENDANTS = 0x00000004;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 0x00000008;
    public static final int FLAG_REPORT_VIEW_IDS = 0x00000010;
    // Actions.
    public static final int ACTION_FOCUS = 0x00000001;
    public static final int ACTION_CLEAR_FOCUS = 0x00000002;
    public static final int ACTION_SELECT = 0x00000004;
    public static final int ACTION_CLEAR_SELECTION = 0x00000008;
    public static final int ACTION_CLICK = 0x00000010;
    public static final int ACTION_LONG_CLICK = 0x00000020;
    public static final int ACTION_ACCESSIBILITY_FOCUS = 0x00000040;
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 0x00000080;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 0x00000100;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 0x00000200;
    public static final int ACTION_NEXT_HTML_ELEMENT = 0x00000400;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 0x00000800;
    public static final int ACTION_SCROLL_FORWARD = 0x00001000;
    public static final int ACTION_SCROLL_BACKWARD = 0x00002000;
    public static final int ACTION_COPY = 0x00004000;
    public static final int ACTION_PASTE = 0x00008000;
    public static final int ACTION_CUT = 0x00010000;
    public static final int ACTION_SET_SELECTION = 0x00020000;
    public static final int ACTION_EXPAND = 0x00040000;
    public static final int ACTION_COLLAPSE = 0x00080000;
    public static final int ACTION_DISMISS = 0x00100000;
    public static final int ACTION_SET_TEXT = 0x00200000;
    public static final int LAST_LEGACY_STANDARD_ACTION = ACTION_SET_TEXT;
    // Action arguments
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_X = "ACTION_ARGUMENT_MOVE_WINDOW_X";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_Y = "ACTION_ARGUMENT_MOVE_WINDOW_Y";
    public static final String ACTION_ARGUMENT_ACCESSIBLE_CLICKABLE_SPAN = "android.view.accessibility.action.ACTION_ARGUMENT_ACCESSIBLE_CLICKABLE_SPAN";
    // Focus types
    public static final int FOCUS_INPUT = 1;
    public static final int FOCUS_ACCESSIBILITY = 2;
    // Movement granularities
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 0x00000001;
    public static final int MOVEMENT_GRANULARITY_WORD = 0x00000002;
    public static final int MOVEMENT_GRANULARITY_LINE = 0x00000004;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 0x00000008;
    public static final int MOVEMENT_GRANULARITY_PAGE = 0x00000010;
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY";
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX";
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH";
    public static final String EXTRA_DATA_REQUESTED_KEY = "android.view.accessibility.AccessibilityNodeInfo.extra_data_requested";

    // Boolean attributes.
    public static int getAccessibilityViewId(long accessibilityNodeId) {
        throw new RuntimeException("Stub!");
    }

    public static int getVirtualDescendantId(long accessibilityNodeId) {
        throw new RuntimeException("Stub!");
    }

    public static long makeNodeId(int accessibilityViewId, int virtualDescendantId) {
        throw new RuntimeException("Stub!");
    }

    public void setSource(View source) {
        throw new RuntimeException("Stub!");
    }

    public void setSource(View root, int virtualDescendantId) {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo findFocus(int focus) {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo focusSearch(int direction) {
        throw new RuntimeException("Stub!");
    }

    public int getWindowId() {
        throw new RuntimeException("Stub!");
    }

    public boolean refresh(Bundle arguments, boolean bypassCache) {
        throw new RuntimeException("Stub!");
    }

    public boolean refresh() {
        throw new RuntimeException("Stub!");
    }

    public boolean refreshWithExtraData(String extraDataKey, Bundle args) {
        throw new RuntimeException("Stub!");
    }

    public LongArray getChildNodeIds() {
        throw new RuntimeException("Stub!");
    }

    public long getChildId(int index) {
        throw new RuntimeException("Stub!");
    }

    public int getChildCount() {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo getChild(int index) {
        throw new RuntimeException("Stub!");
    }

    public void addChild(View child) {
        throw new RuntimeException("Stub!");
    }

    public void addChildUnchecked(View child) {
        throw new RuntimeException("Stub!");
    }

    public boolean removeChild(View child) {
        throw new RuntimeException("Stub!");
    }

    public void addChild(View root, int virtualDescendantId) {
        throw new RuntimeException("Stub!");
    }

    public boolean removeChild(View root, int virtualDescendantId) {
        throw new RuntimeException("Stub!");
    }

    public List<AccessibilityAction> getActionList() {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public int getActions() {
        throw new RuntimeException("Stub!");
    }

    public void addAction(AccessibilityAction action) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void addAction(int action) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void removeAction(int action) {
        throw new RuntimeException("Stub!");
    }

    public boolean removeAction(AccessibilityAction action) {
        throw new RuntimeException("Stub!");
    }

    public void removeAllActions() {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo getTraversalBefore() {
        throw new RuntimeException("Stub!");
    }

    public void setTraversalBefore(View view) {
        throw new RuntimeException("Stub!");
    }

    public void setTraversalBefore(View root, int virtualDescendantId) {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo getTraversalAfter() {
        throw new RuntimeException("Stub!");
    }

    public void setTraversalAfter(View view) {
        throw new RuntimeException("Stub!");
    }

    public void setTraversalAfter(View root, int virtualDescendantId) {
        throw new RuntimeException("Stub!");
    }

    public List<String> getAvailableExtraData() {
        throw new RuntimeException("Stub!");
    }

    public void setAvailableExtraData(List<String> extraDataKeys) {
        throw new RuntimeException("Stub!");
    }

    public void setMaxTextLength(int max) {
        throw new RuntimeException("Stub!");
    }

    public int getMaxTextLength() {
        throw new RuntimeException("Stub!");
    }

    public void setMovementGranularities(int granularities) {
        throw new RuntimeException("Stub!");
    }

    public int getMovementGranularities() {
        throw new RuntimeException("Stub!");
    }

    public boolean performAction(int action) {
        throw new RuntimeException("Stub!");
    }

    public boolean performAction(int action, Bundle arguments) {
        throw new RuntimeException("Stub!");
    }

    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String text) {
        throw new RuntimeException("Stub!");
    }

    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId(String viewId) {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityWindowInfo getWindow() {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo getParent() {
        throw new RuntimeException("Stub!");
    }

    public long getParentNodeId() {
        throw new RuntimeException("Stub!");
    }

    public void setParent(View parent) {
        throw new RuntimeException("Stub!");
    }

    public void setParent(View root, int virtualDescendantId) {
        throw new RuntimeException("Stub!");
    }

    public void getBoundsInParent(Rect outBounds) {
        throw new RuntimeException("Stub!");
    }

    public void setBoundsInParent(Rect bounds) {
        throw new RuntimeException("Stub!");
    }

    public void getBoundsInScreen(Rect outBounds) {
        throw new RuntimeException("Stub!");
    }

    public Rect getBoundsInScreen() {
        throw new RuntimeException("Stub!");
    }

    public void setBoundsInScreen(Rect bounds) {
        throw new RuntimeException("Stub!");
    }

    public boolean isCheckable() {
        throw new RuntimeException("Stub!");
    }

    public void setCheckable(boolean checkable) {
        throw new RuntimeException("Stub!");
    }

    public boolean isChecked() {
        throw new RuntimeException("Stub!");
    }

    public void setChecked(boolean checked) {
        throw new RuntimeException("Stub!");
    }

    public boolean isFocusable() {
        throw new RuntimeException("Stub!");
    }

    public void setFocusable(boolean focusable) {
        throw new RuntimeException("Stub!");
    }

    public boolean isFocused() {
        throw new RuntimeException("Stub!");
    }

    public void setFocused(boolean focused) {
        throw new RuntimeException("Stub!");
    }

    public boolean isVisibleToUser() {
        throw new RuntimeException("Stub!");
    }

    public void setVisibleToUser(boolean visibleToUser) {
        throw new RuntimeException("Stub!");
    }

    public boolean isAccessibilityFocused() {
        throw new RuntimeException("Stub!");
    }

    public void setAccessibilityFocused(boolean focused) {
        throw new RuntimeException("Stub!");
    }

    public boolean isSelected() {
        throw new RuntimeException("Stub!");
    }

    public void setSelected(boolean selected) {
        throw new RuntimeException("Stub!");
    }

    public boolean isClickable() {
        throw new RuntimeException("Stub!");
    }

    public void setClickable(boolean clickable) {
        throw new RuntimeException("Stub!");
    }

    public boolean isLongClickable() {
        throw new RuntimeException("Stub!");
    }

    public void setLongClickable(boolean longClickable) {
        throw new RuntimeException("Stub!");
    }

    public boolean isEnabled() {
        throw new RuntimeException("Stub!");
    }

    public void setEnabled(boolean enabled) {
        throw new RuntimeException("Stub!");
    }

    public boolean isPassword() {
        throw new RuntimeException("Stub!");
    }

    public void setPassword(boolean password) {
        throw new RuntimeException("Stub!");
    }

    public boolean isScrollable() {
        throw new RuntimeException("Stub!");
    }

    public void setScrollable(boolean scrollable) {
        throw new RuntimeException("Stub!");
    }

    public boolean isEditable() {
        throw new RuntimeException("Stub!");
    }

    public void setEditable(boolean editable) {
        throw new RuntimeException("Stub!");
    }

    public void setPaneTitle(@Nullable CharSequence paneTitle) {
        throw new RuntimeException("Stub!");
    }

    public @Nullable CharSequence getPaneTitle() {
        throw new RuntimeException("Stub!");
    }

    public int getDrawingOrder() {
        throw new RuntimeException("Stub!");
    }

    public void setDrawingOrder(int drawingOrderInParent) {
        throw new RuntimeException("Stub!");
    }

    public CollectionInfo getCollectionInfo() {
        throw new RuntimeException("Stub!");
    }

    public void setCollectionInfo(CollectionInfo collectionInfo) {
        throw new RuntimeException("Stub!");
    }

    public CollectionItemInfo getCollectionItemInfo() {
        throw new RuntimeException("Stub!");
    }

    public void setCollectionItemInfo(CollectionItemInfo collectionItemInfo) {
        throw new RuntimeException("Stub!");
    }

    public RangeInfo getRangeInfo() {
        throw new RuntimeException("Stub!");
    }

    public void setRangeInfo(RangeInfo rangeInfo) {
        throw new RuntimeException("Stub!");
    }

    public boolean isContentInvalid() {
        throw new RuntimeException("Stub!");
    }

    public void setContentInvalid(boolean contentInvalid) {
        throw new RuntimeException("Stub!");
    }

    public boolean isContextClickable() {
        throw new RuntimeException("Stub!");
    }

    public void setContextClickable(boolean contextClickable) {
        throw new RuntimeException("Stub!");
    }

    public int getLiveRegion() {
        throw new RuntimeException("Stub!");
    }

    public void setLiveRegion(int mode) {
        throw new RuntimeException("Stub!");
    }

    public boolean isMultiLine() {
        throw new RuntimeException("Stub!");
    }

    public void setMultiLine(boolean multiLine) {
        throw new RuntimeException("Stub!");
    }

    public boolean canOpenPopup() {
        throw new RuntimeException("Stub!");
    }

    public void setCanOpenPopup(boolean opensPopup) {
        throw new RuntimeException("Stub!");
    }

    public boolean isDismissable() {
        throw new RuntimeException("Stub!");
    }

    public void setDismissable(boolean dismissable) {
        throw new RuntimeException("Stub!");
    }

    public boolean isImportantForAccessibility() {
        throw new RuntimeException("Stub!");
    }

    public void setImportantForAccessibility(boolean important) {
        throw new RuntimeException("Stub!");
    }

    public boolean isScreenReaderFocusable() {
        throw new RuntimeException("Stub!");
    }

    public void setScreenReaderFocusable(boolean screenReaderFocusable) {
        throw new RuntimeException("Stub!");
    }

    public boolean isShowingHintText() {
        throw new RuntimeException("Stub!");
    }

    public void setShowingHintText(boolean showingHintText) {
        throw new RuntimeException("Stub!");
    }

    public boolean isHeading() {
        throw new RuntimeException("Stub!");
    }

    public void setHeading(boolean isHeading) {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getPackageName() {
        throw new RuntimeException("Stub!");
    }

    public void setPackageName(CharSequence packageName) {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getClassName() {
        throw new RuntimeException("Stub!");
    }

    public void setClassName(CharSequence className) {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getText() {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getOriginalText() {
        throw new RuntimeException("Stub!");
    }

    public void setText(CharSequence text) {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getHintText() {
        throw new RuntimeException("Stub!");
    }

    public void setHintText(CharSequence hintText) {
        throw new RuntimeException("Stub!");
    }

    public void setError(CharSequence error) {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getError() {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getContentDescription() {
        throw new RuntimeException("Stub!");
    }

    public void setContentDescription(CharSequence contentDescription) {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    public CharSequence getTooltipText() {
        throw new RuntimeException("Stub!");
    }

    public void setTooltipText(@Nullable CharSequence tooltipText) {
        throw new RuntimeException("Stub!");
    }

    public void setLabelFor(View labeled) {
        throw new RuntimeException("Stub!");
    }

    public void setLabelFor(View root, int virtualDescendantId) {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo getLabelFor() {
        throw new RuntimeException("Stub!");
    }

    public void setLabeledBy(View label) {
        throw new RuntimeException("Stub!");
    }

    public void setLabeledBy(View root, int virtualDescendantId) {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo getLabeledBy() {
        throw new RuntimeException("Stub!");
    }

    public void setViewIdResourceName(String viewIdResName) {
        throw new RuntimeException("Stub!");
    }

    public String getViewIdResourceName() {
        throw new RuntimeException("Stub!");
    }

    public int getTextSelectionStart() {
        throw new RuntimeException("Stub!");
    }

    public int getTextSelectionEnd() {
        throw new RuntimeException("Stub!");
    }

    public void setTextSelection(int start, int end) {
        throw new RuntimeException("Stub!");
    }

    public int getInputType() {
        throw new RuntimeException("Stub!");
    }

    public void setInputType(int inputType) {
        throw new RuntimeException("Stub!");
    }

    public Bundle getExtras() {
        throw new RuntimeException("Stub!");
    }

    public boolean hasExtras() {
        throw new RuntimeException("Stub!");
    }

    public void setConnectionId(int connectionId) {
        throw new RuntimeException("Stub!");
    }

    public int getConnectionId() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void setSourceNodeId(long sourceId, int windowId) {
        throw new RuntimeException("Stub!");
    }

    public long getSourceNodeId() {
        throw new RuntimeException("Stub!");
    }

    public void setSealed(boolean sealed) {
        throw new RuntimeException("Stub!");
    }

    public boolean isSealed() {
        throw new RuntimeException("Stub!");
    }

    protected void enforceSealed() {
        throw new RuntimeException("Stub!");
    }

    protected void enforceNotSealed() {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityNodeInfo obtain(View source) {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityNodeInfo obtain(View root, int virtualDescendantId) {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityNodeInfo obtain() {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityNodeInfo obtain(AccessibilityNodeInfo info) {
        throw new RuntimeException("Stub!");
    }

    public void recycle() {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    public static void setNumInstancesInUseCounter(AtomicInteger counter) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    public void writeToParcelNoRecycle(Parcel parcel, int flags) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean equals(Object object) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public static String idToString(long accessibilityId) {
        throw new RuntimeException("Stub!");
    }

    public static final class AccessibilityAction {
        public static final ArraySet<AccessibilityAction> sStandardActions = new ArraySet<>();
        public static final AccessibilityAction ACTION_FOCUS = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_FOCUS);
        public static final AccessibilityAction ACTION_CLEAR_FOCUS = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLEAR_FOCUS);
        public static final AccessibilityAction ACTION_SELECT = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_SELECT);
        public static final AccessibilityAction ACTION_CLEAR_SELECTION = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLEAR_SELECTION);
        public static final AccessibilityAction ACTION_CLICK = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLICK);
        public static final AccessibilityAction ACTION_LONG_CLICK = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_LONG_CLICK);
        public static final AccessibilityAction ACTION_ACCESSIBILITY_FOCUS = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS);
        public static final AccessibilityAction ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        public static final AccessibilityAction ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
        public static final AccessibilityAction ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
        public static final AccessibilityAction ACTION_NEXT_HTML_ELEMENT = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_NEXT_HTML_ELEMENT);
        public static final AccessibilityAction ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_PREVIOUS_HTML_ELEMENT);
        public static final AccessibilityAction ACTION_SCROLL_FORWARD = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
        public static final AccessibilityAction ACTION_SCROLL_BACKWARD = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD);
        public static final AccessibilityAction ACTION_COPY = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_COPY);
        public static final AccessibilityAction ACTION_PASTE = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_PASTE);
        public static final AccessibilityAction ACTION_CUT = new AccessibilityAction(AccessibilityNodeInfo.ACTION_CUT);
        public static final AccessibilityAction ACTION_SET_SELECTION = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_SET_SELECTION);
        public static final AccessibilityAction ACTION_EXPAND = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_EXPAND);
        public static final AccessibilityAction ACTION_COLLAPSE = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_COLLAPSE);
        public static final AccessibilityAction ACTION_DISMISS = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_DISMISS);
        public static final AccessibilityAction ACTION_SET_TEXT = new AccessibilityAction(
                AccessibilityNodeInfo.ACTION_SET_TEXT);
        public static final AccessibilityAction ACTION_SHOW_ON_SCREEN = new AccessibilityAction(0);
        public static final AccessibilityAction ACTION_SCROLL_TO_POSITION = new AccessibilityAction(0);
        public static final AccessibilityAction ACTION_SCROLL_UP = new AccessibilityAction(0);
        public static final AccessibilityAction ACTION_SCROLL_LEFT = new AccessibilityAction(0);
        public static final AccessibilityAction ACTION_SCROLL_DOWN = new AccessibilityAction(0);
        public static final AccessibilityAction ACTION_SCROLL_RIGHT = new AccessibilityAction(0);
        public static final AccessibilityAction ACTION_CONTEXT_CLICK = new AccessibilityAction(0);
        public static final AccessibilityAction ACTION_SET_PROGRESS = new AccessibilityAction(0);
        public static final AccessibilityAction ACTION_MOVE_WINDOW = new AccessibilityAction(0);
        public static final AccessibilityAction ACTION_SHOW_TOOLTIP = new AccessibilityAction(0);
        public static final AccessibilityAction ACTION_HIDE_TOOLTIP = new AccessibilityAction(0);

        public AccessibilityAction(int actionFocus) {
        }

        public int getId() {
            throw new RuntimeException("Stub!");
        }

        public CharSequence getLabel() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public int hashCode() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public boolean equals(Object other) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    public static final class RangeInfo {
        public static final int RANGE_TYPE_INT = 0;
        public static final int RANGE_TYPE_FLOAT = 1;
        public static final int RANGE_TYPE_PERCENT = 2;

        public static RangeInfo obtain(RangeInfo other) {
            throw new RuntimeException("Stub!");
        }

        public static RangeInfo obtain(int type, float min, float max, float current) {
            throw new RuntimeException("Stub!");
        }

        public int getType() {
            throw new RuntimeException("Stub!");
        }

        public float getMin() {
            throw new RuntimeException("Stub!");
        }

        public float getMax() {
            throw new RuntimeException("Stub!");
        }

        public float getCurrent() {
            throw new RuntimeException("Stub!");
        }

        void recycle() {
            throw new RuntimeException("Stub!");
        }
    }

    public static final class CollectionInfo {
        public static final int SELECTION_MODE_NONE = 0;
        public static final int SELECTION_MODE_SINGLE = 1;
        public static final int SELECTION_MODE_MULTIPLE = 2;

        public static CollectionInfo obtain(CollectionInfo other) {
            throw new RuntimeException("Stub!");
        }

        public static CollectionInfo obtain(int rowCount, int columnCount, boolean hierarchical) {
            throw new RuntimeException("Stub!");
        }

        public static CollectionInfo obtain(int rowCount, int columnCount, boolean hierarchical, int selectionMode) {
            throw new RuntimeException("Stub!");
        }

        public int getRowCount() {
            throw new RuntimeException("Stub!");
        }

        public int getColumnCount() {
            throw new RuntimeException("Stub!");
        }

        public boolean isHierarchical() {
            throw new RuntimeException("Stub!");
        }

        public int getSelectionMode() {
            throw new RuntimeException("Stub!");
        }

        void recycle() {
            throw new RuntimeException("Stub!");
        }
    }

    public static final class CollectionItemInfo {
        public static CollectionItemInfo obtain(CollectionItemInfo other) {
            throw new RuntimeException("Stub!");
        }

        public static CollectionItemInfo obtain(int rowIndex, int rowSpan, int columnIndex, int columnSpan,
                boolean heading) {
            throw new RuntimeException("Stub!");
        }

        public static CollectionItemInfo obtain(int rowIndex, int rowSpan, int columnIndex, int columnSpan,
                boolean heading, boolean selected) {
            throw new RuntimeException("Stub!");
        }

        public int getColumnIndex() {
            throw new RuntimeException("Stub!");
        }

        public int getRowIndex() {
            throw new RuntimeException("Stub!");
        }

        public int getColumnSpan() {
            throw new RuntimeException("Stub!");
        }

        public int getRowSpan() {
            throw new RuntimeException("Stub!");
        }

        public boolean isHeading() {
            throw new RuntimeException("Stub!");
        }

        public boolean isSelected() {
            throw new RuntimeException("Stub!");
        }

        void recycle() {
            throw new RuntimeException("Stub!");
        }
    }

    public static final Parcelable.Creator<AccessibilityNodeInfo> CREATOR = new Parcelable.Creator<AccessibilityNodeInfo>() {
        @Override
        public AccessibilityNodeInfo createFromParcel(Parcel parcel) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public AccessibilityNodeInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}