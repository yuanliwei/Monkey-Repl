package android.hardware.display;

import android.annotation.UnsupportedAppUsage;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;

public final class DisplayManagerGlobal {
    public static final int EVENT_DISPLAY_ADDED = 1;
    public static final int EVENT_DISPLAY_CHANGED = 2;
    public static final int EVENT_DISPLAY_REMOVED = 3;

    @UnsupportedAppUsage
    public static DisplayManagerGlobal getInstance() {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public int[] getDisplayIds() {
        throw new RuntimeException("Stub!");
    }

    public Display getCompatibleDisplay(int displayId, Resources resources) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public Display getRealDisplay(int displayId) {
        throw new RuntimeException("Stub!");
    }

    public void startWifiDisplayScan() {
        throw new RuntimeException("Stub!");
    }

    public void stopWifiDisplayScan() {
        throw new RuntimeException("Stub!");
    }

    public void connectWifiDisplay(String deviceAddress) {
        throw new RuntimeException("Stub!");
    }

    public void pauseWifiDisplay() {
        throw new RuntimeException("Stub!");
    }

    public void resumeWifiDisplay() {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public void disconnectWifiDisplay() {
        throw new RuntimeException("Stub!");
    }

    public void renameWifiDisplay(String deviceAddress, String alias) {
        throw new RuntimeException("Stub!");
    }

    public void forgetWifiDisplay(String deviceAddress) {
        throw new RuntimeException("Stub!");
    }

    public void requestColorMode(int displayId, int colorMode) {
        throw new RuntimeException("Stub!");
    }

    public void setSaturationLevel(float level) {
        throw new RuntimeException("Stub!");
    }

    public Point getStableDisplaySize() {
        throw new RuntimeException("Stub!");
    }

    public void setTemporaryBrightness(int brightness) {
        throw new RuntimeException("Stub!");
    }

    public void setTemporaryAutoBrightnessAdjustment(float adjustment) {
        throw new RuntimeException("Stub!");
    }

    public void sendDisplayEvent(int displayId, int event) {
        throw new RuntimeException("Stub!");
    }

    public void clearEvents() {
        throw new RuntimeException("Stub!");
    }
}