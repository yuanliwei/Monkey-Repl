package android.app;

import android.accessibilityservice.IAccessibilityServiceClient;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.ParcelFileDescriptor;
import android.view.InputEvent;
import android.view.WindowContentFrameStats;

public interface IUiAutomationConnection extends android.os.IInterface {

    public static abstract class Stub extends android.os.Binder implements IUiAutomationConnection {

        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IUiAutomationConnection asInterface(android.os.IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public android.os.IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    void connect(IAccessibilityServiceClient client, int flags);

    void disconnect();

    boolean injectInputEvent(InputEvent event, boolean sync);

    boolean setRotation(int rotation);

    Bitmap takeScreenshot(Rect crop, int rotation);

    boolean clearWindowContentFrameStats(int windowId);

    WindowContentFrameStats getWindowContentFrameStats(int windowId);

    void clearWindowAnimationFrameStats();

    WindowAnimationFrameStats getWindowAnimationFrameStats();

    void executeShellCommand(String command, ParcelFileDescriptor sink, ParcelFileDescriptor source);

    void grantRuntimePermission(String packageName, String permission, int userId);

    void revokeRuntimePermission(String packageName, String permission, int userId);

    void adoptShellPermissionIdentity(int uid);

    void dropShellPermissionIdentity();

    void shutdown();
}
