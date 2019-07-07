
package android.accessibilityservice;

import java.util.List;

import android.view.accessibility.AccessibilityNodeInfo;

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
 
}
