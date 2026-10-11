package android.accessibilityservice;

import java.util.List;

import android.view.accessibility.AccessibilityNodeInfo;

public interface IAccessibilityInteractionConnectionCallback extends android.os.IInterface {

    public static abstract class Stub extends android.os.Binder implements IAccessibilityInteractionConnectionCallback {

        public Stub() {
        throw new RuntimeException("Stub!");
    }

        public static IAccessibilityInteractionConnectionCallback asInterface(android.os.IBinder obj) {
        throw new RuntimeException("Stub!");
    }

        @Override
        public android.os.IBinder asBinder() {
        throw new RuntimeException("Stub!");
    }
    }

    void setFindAccessibilityNodeInfoResult(AccessibilityNodeInfo info, int interactionId);

    void setFindAccessibilityNodeInfosResult(List<AccessibilityNodeInfo> infos, int interactionId);

    void setPerformAccessibilityActionResult(boolean succeeded, int interactionId);
}
