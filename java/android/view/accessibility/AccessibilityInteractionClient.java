package android.view.accessibility;

import java.util.List;
import android.accessibilityservice.IAccessibilityInteractionConnectionCallback;
import android.os.Bundle;

public final class AccessibilityInteractionClient extends IAccessibilityInteractionConnectionCallback.Stub {
    public static final int NO_ID = -1;

    @Override
    public void setFindAccessibilityNodeInfoResult(AccessibilityNodeInfo info, int interactionId) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void setFindAccessibilityNodeInfosResult(List<AccessibilityNodeInfo> infos, int interactionId) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void setPerformAccessibilityActionResult(boolean succeeded, int interactionId) {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo findAccessibilityNodeInfoByAccessibilityId(int connectionId, int accessibilityWindowId,
            long accessibilityNodeId, boolean bypassCache, int prefetchFlags, Bundle arguments) {
        throw new RuntimeException("Stub!");
    }

    public static AccessibilityInteractionClient getInstance() {
        throw new RuntimeException("Stub!");
    }

    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId(int connectionId, int activeWindowId,
            long rootNodeId, String viewId) {
        throw new RuntimeException("Stub!");
    }

    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(int connectionId, int activeWindowId,
            long rootNodeId, String text) {
        throw new RuntimeException("Stub!");
    }
}