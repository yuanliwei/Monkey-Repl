package android.view;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public interface IWindowManager extends android.os.IInterface {
    public static abstract class Stub extends android.os.Binder implements IWindowManager {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IWindowManager asInterface(android.os.IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public android.os.IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    boolean startViewServer(int port); // Transaction #1

    boolean stopViewServer(); // Transaction #2

    boolean isViewServerRunning(); // Transaction #3

    void getInitialDisplaySize(int displayId, Point size);

    void getBaseDisplaySize(int displayId, Point size);

    void setForcedDisplaySize(int displayId, int width, int height);

    void clearForcedDisplaySize(int displayId);

    int getInitialDisplayDensity(int displayId);

    int getBaseDisplayDensity(int displayId);

    void setForcedDisplayDensityForUser(int displayId, int density, int userId);

    void clearForcedDisplayDensityForUser(int displayId, int userId);

    void setForcedDisplayScalingMode(int displayId, int mode); // 0 = auto, 1 = disable

    void setOverscan(int displayId, int left, int top, int right, int bottom);

    // These can only be called when holding the MANAGE_APP_TOKENS permission.
    void setEventDispatching(boolean enabled);

    void addWindowToken(IBinder token, int type, int displayId);

    void removeWindowToken(IBinder token, int displayId);

    void setFocusedApp(IBinder token, boolean moveFocusNow);

    void prepareAppTransition(int transit, boolean alwaysKeepCurrent);

    int getPendingAppTransition();

    void overridePendingAppTransitionScaleUp(int startX, int startY, int startWidth, int startHeight);

    void overridePendingAppTransitionClipReveal(int startX, int startY, int startWidth, int startHeight);

    void overridePendingAppTransitionInPlace(String packageName, int anim);

    void executeAppTransition();

    void endProlongedAnimations();

    // Re-evaluate the current orientation from the caller's state.
    // If there is a change, the new Configuration is returned and the
    // caller must call setNewConfiguration() sometime later.
    Configuration updateOrientationFromAppTokens(Configuration currentConfig, IBinder freezeThisOneIfNeeded,
            int displayId);

    // Notify window manager of the new display override configuration. Returns an
    // array of stack
    // ids that were affected by the update, ActivityManager should resize these
    // stacks.
    int[] setNewDisplayOverrideConfiguration(Configuration overrideConfig, int displayId);

    void startFreezingScreen(int exitAnim, int enterAnim);

    void stopFreezingScreen();

    // these require DISABLE_KEYGUARD permission
    void disableKeyguard(IBinder token, String tag);

    void reenableKeyguard(IBinder token);

    boolean isKeyguardLocked();

    boolean isKeyguardSecure();

    // Requires INTERACT_ACROSS_USERS_FULL permission
    void setSwitchingUser(boolean switching);

    void closeSystemDialogs(String reason);

    // These can only be called with the SET_ANIMATON_SCALE permission.
    float getAnimationScale(int which);

    float[] getAnimationScales();

    void setAnimationScale(int which, float scale);

    void setAnimationScales(float[] scales);

    float getCurrentAnimatorScale();

    // For testing
    void setInTouchMode(boolean showFocus);

    // For StrictMode flashing a red border on violations from the UI
    // thread. The uid/pid is implicit from the Binder call, and the Window
    // Manager uses that to determine whether or not the red border should
    // actually be shown. (it will be ignored that pid doesn't have windows
    // on screen)
    void showStrictModeViolation(boolean on);

    // Proxy to set the system property for whether the flashing
    // should be enabled. The 'enabled' value is null or blank for
    // the system default (differs per build variant) or any valid
    // boolean string as parsed by SystemProperties.getBoolean().
    void setStrictModeVisualIndicatorPreference(String enabled);

    void refreshScreenCaptureDisabled(int userId);

    // These can only be called with the SET_ORIENTATION permission.
    void updateRotation(boolean alwaysSendConfiguration, boolean forceRelayout);

    int getDefaultDisplayRotation();

    int getPreferredOptionsPanelGravity();

    void freezeRotation(int rotation) throws RemoteException;

    void thawRotation();

    boolean isRotationFrozen();

    Bitmap screenshotWallpaper();

    void statusBarVisibilityChanged(int visibility);

    void setRecentsVisibility(boolean visible);

    void setPipVisibility(boolean visible);

    void setShelfHeight(boolean visible, int shelfHeight);

    void setNavBarVirtualKeyHapticFeedbackEnabled(boolean enabled);

    boolean hasNavigationBar();

    int getNavBarPosition();

    void lockNow(Bundle options);

    boolean isSafeModeEnabled();

    void enableScreenIfNeeded();

    boolean clearWindowContentFrameStats(IBinder token);

    WindowContentFrameStats getWindowContentFrameStats(IBinder token);

    int getDockedStackSide();

    void setDockedStackDividerTouchRegion(Rect touchableRegion);

    void setResizeDimLayer(boolean visible, int targetWindowingMode, float alpha);

    void getStableInsets(int displayId, Rect outInsets);

    boolean destroyInputConsumer(String name);

    Region getCurrentImeTouchRegion();

    void startWindowTrace();

    void stopWindowTrace();

    boolean isWindowTraceEnabled();

    void requestUserActivityNotification();

    void dontOverrideDisplayInfo(int displayId);
}