
package android.view.accessibility;

import java.util.List;

import android.os.Bundle;

public abstract class AccessibilityNodeProvider {

    public static final int HOST_VIEW_ID = -1;

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) {
        throw new RuntimeException("Stub!");
    }

    public void addExtraDataToAccessibilityNodeInfo(int virtualViewId, AccessibilityNodeInfo info, String extraDataKey,
            Bundle arguments) {
        throw new RuntimeException("Stub!");
    }

    public boolean performAction(int virtualViewId, int action, Bundle arguments) {
        throw new RuntimeException("Stub!");
    }

    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String text, int virtualViewId) {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo findFocus(int focus) {
        throw new RuntimeException("Stub!");
    }
}
