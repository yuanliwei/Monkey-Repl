package android.app;

import java.util.List;
import java.util.concurrent.TimeoutException;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TestApi;
import android.annotation.UnsupportedAppUsage;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.UserHandle;
import android.view.InputEvent;
import android.view.Surface;
import android.view.WindowContentFrameStats;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

public final class UiAutomation {
    public static final int ROTATION_UNFREEZE = -2;
    public static final int ROTATION_FREEZE_CURRENT = -1;
    public static final int ROTATION_FREEZE_0 = Surface.ROTATION_0;
    public static final int ROTATION_FREEZE_90 = Surface.ROTATION_90;
    public static final int ROTATION_FREEZE_180 = Surface.ROTATION_180;
    public static final int ROTATION_FREEZE_270 = Surface.ROTATION_270;
    public static final int FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES = 0x00000001;

    public static interface OnAccessibilityEventListener {
        public void onAccessibilityEvent(AccessibilityEvent event);
    }

    public static interface AccessibilityEventFilter {
        public boolean accept(AccessibilityEvent event);
    }

    @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.P, trackingBug = 115609023)
    public UiAutomation(Looper looper, IUiAutomationConnection connection) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.P, trackingBug = 115609023)
    public void connect() {
        throw new RuntimeException("Stub!");
    }

    public void connect(int flags) {
        throw new RuntimeException("Stub!");
    }

    public int getFlags() {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.P, trackingBug = 115609023)
    public void disconnect() {
        throw new RuntimeException("Stub!");
    }

    public int getConnectionId() {
        throw new RuntimeException("Stub!");
    }

    public boolean isDestroyed() {
        throw new RuntimeException("Stub!");
    }

    public void setOnAccessibilityEventListener(OnAccessibilityEventListener listener) {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    public void destroy() {
        throw new RuntimeException("Stub!");
    }

    public void adoptShellPermissionIdentity() {
        throw new RuntimeException("Stub!");
    }

    public void dropShellPermissionIdentity() {
        throw new RuntimeException("Stub!");
    }

    public final boolean performGlobalAction(int action) {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo findFocus(int focus) {
        throw new RuntimeException("Stub!");
    }

    public final AccessibilityServiceInfo getServiceInfo() {
        throw new RuntimeException("Stub!");
    }

    public final void setServiceInfo(AccessibilityServiceInfo info) {
        throw new RuntimeException("Stub!");
    }

    public List<AccessibilityWindowInfo> getWindows() {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityNodeInfo getRootInActiveWindow() {
        throw new RuntimeException("Stub!");
    }

    public boolean injectInputEvent(InputEvent event, boolean sync) {
        throw new RuntimeException("Stub!");
    }

    public boolean setRotation(int rotation) {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityEvent executeAndWaitForEvent(Runnable command, AccessibilityEventFilter filter,
            long timeoutMillis) throws TimeoutException {
        throw new RuntimeException("Stub!");
    }

    public void waitForIdle(long idleTimeoutMillis, long globalTimeoutMillis) throws TimeoutException {
        throw new RuntimeException("Stub!");
    }

    public Bitmap takeScreenshot() {
        throw new RuntimeException("Stub!");
    }

    public void setRunAsMonkey(boolean enable) {
        throw new RuntimeException("Stub!");
    }

    public boolean clearWindowContentFrameStats(int windowId) {
        throw new RuntimeException("Stub!");
    }

    public WindowContentFrameStats getWindowContentFrameStats(int windowId) {
        throw new RuntimeException("Stub!");
    }

    public void clearWindowAnimationFrameStats() {
        throw new RuntimeException("Stub!");
    }

    public WindowAnimationFrameStats getWindowAnimationFrameStats() {
        throw new RuntimeException("Stub!");
    }

    public void grantRuntimePermission(String packageName, String permission) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    @TestApi
    public boolean grantRuntimePermission(String packageName, String permission, UserHandle userHandle) {
        throw new RuntimeException("Stub!");
    }

    public void grantRuntimePermissionAsUser(String packageName, String permission, UserHandle userHandle) {
        throw new RuntimeException("Stub!");
    }

    public void revokeRuntimePermission(String packageName, String permission) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    @TestApi
    public boolean revokeRuntimePermission(String packageName, String permission, UserHandle userHandle) {
        throw new RuntimeException("Stub!");
    }

    public void revokeRuntimePermissionAsUser(String packageName, String permission, UserHandle userHandle) {
        throw new RuntimeException("Stub!");
    }

    public ParcelFileDescriptor executeShellCommand(String command) {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    public ParcelFileDescriptor[] executeShellCommandRw(String command) {
        throw new RuntimeException("Stub!");
    }
}