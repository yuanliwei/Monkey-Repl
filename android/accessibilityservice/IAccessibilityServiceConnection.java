package android.accessibilityservice;

import java.util.List;

import android.content.pm.ParceledListSlice;
import android.graphics.Region;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.accessibility.AccessibilityWindowInfo;

public interface IAccessibilityServiceConnection extends android.os.IInterface {

    public static abstract class Stub extends android.os.Binder implements IAccessibilityServiceConnection {

        public Stub() {
        throw new RuntimeException("Stub!");
    }

        public static IAccessibilityServiceConnection asInterface(android.os.IBinder obj) {
        throw new RuntimeException("Stub!");
    }

        @Override
        public android.os.IBinder asBinder() {
        throw new RuntimeException("Stub!");
    }
    }

    void setServiceInfo(AccessibilityServiceInfo info);

    String[] findAccessibilityNodeInfoByAccessibilityId(int accessibilityWindowId, long accessibilityNodeId,
            int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, long threadId,
            Bundle arguments);

    String[] findAccessibilityNodeInfosByText(int accessibilityWindowId, long accessibilityNodeId, String text,
            int interactionId, IAccessibilityInteractionConnectionCallback callback, long threadId);

    String[] findAccessibilityNodeInfosByViewId(int accessibilityWindowId, long accessibilityNodeId, String viewId,
            int interactionId, IAccessibilityInteractionConnectionCallback callback, long threadId);

    String[] findFocus(int accessibilityWindowId, long accessibilityNodeId, int focusType, int interactionId,
            IAccessibilityInteractionConnectionCallback callback, long threadId);

    String[] focusSearch(int accessibilityWindowId, long accessibilityNodeId, int direction, int interactionId,
            IAccessibilityInteractionConnectionCallback callback, long threadId);

    boolean performAccessibilityAction(int accessibilityWindowId, long accessibilityNodeId, int action,
            Bundle arguments, int interactionId, IAccessibilityInteractionConnectionCallback callback, long threadId);

    AccessibilityWindowInfo getWindow(int windowId);

    List<AccessibilityWindowInfo> getWindows();

    AccessibilityServiceInfo getServiceInfo();

    boolean performGlobalAction(int action);

    void disableSelf();

    void setOnKeyEventResult(boolean handled, int sequence);

    float getMagnificationScale();

    float getMagnificationCenterX();

    float getMagnificationCenterY();

    Region getMagnificationRegion();

    boolean resetMagnification(boolean animate);

    boolean setMagnificationScaleAndCenter(float scale, float centerX, float centerY, boolean animate);

    void setMagnificationCallbackEnabled(boolean enabled);

    boolean setSoftKeyboardShowMode(int showMode);

    void setSoftKeyboardCallbackEnabled(boolean enabled);

    boolean isAccessibilityButtonAvailable();

    void sendGesture(int sequence, ParceledListSlice<?> gestureSteps);

    boolean isFingerprintGestureDetectionAvailable() throws RemoteException;
}