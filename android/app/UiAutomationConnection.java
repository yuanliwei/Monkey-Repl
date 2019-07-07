
package android.app;

import android.accessibilityservice.IAccessibilityServiceClient;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.ParcelFileDescriptor;
import android.view.InputEvent;
import android.view.WindowContentFrameStats;

public final class UiAutomationConnection extends IUiAutomationConnection.Stub {

    @Override
    public void connect(IAccessibilityServiceClient client, int flags) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void disconnect() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean injectInputEvent(InputEvent event, boolean sync) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean setRotation(int rotation) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Bitmap takeScreenshot(Rect crop, int rotation) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean clearWindowContentFrameStats(int windowId) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public WindowContentFrameStats getWindowContentFrameStats(int windowId) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void clearWindowAnimationFrameStats() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public android.app.WindowAnimationFrameStats getWindowAnimationFrameStats() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void executeShellCommand(String command, ParcelFileDescriptor sink, ParcelFileDescriptor source) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void grantRuntimePermission(String packageName, String permission, int userId) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void revokeRuntimePermission(String packageName, String permission, int userId) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void adoptShellPermissionIdentity(int uid) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void dropShellPermissionIdentity() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void shutdown() {
        throw new RuntimeException("Stub!");
    }

}
