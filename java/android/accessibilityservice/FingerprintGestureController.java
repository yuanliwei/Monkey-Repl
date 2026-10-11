
package android.accessibilityservice;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.os.Handler;

public final class FingerprintGestureController {
    public static final int FINGERPRINT_GESTURE_SWIPE_RIGHT = 0x00000001;

    public static final int FINGERPRINT_GESTURE_SWIPE_LEFT = 0x00000002;

    public static final int FINGERPRINT_GESTURE_SWIPE_UP = 0x00000004;

    public static final int FINGERPRINT_GESTURE_SWIPE_DOWN = 0x00000008;

    public FingerprintGestureController(IAccessibilityServiceConnection connection) {
        throw new RuntimeException("Stub!");
    }

    public boolean isGestureDetectionAvailable() {
        throw new RuntimeException("Stub!");
    }

    public void registerFingerprintGestureCallback(@NonNull FingerprintGestureCallback callback,
            @Nullable Handler handler) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterFingerprintGestureCallback(FingerprintGestureCallback callback) {
        throw new RuntimeException("Stub!");
    }

    public void onGestureDetectionActiveChanged(boolean active) {
        throw new RuntimeException("Stub!");
    }

    public void onGesture(int gesture) {
        throw new RuntimeException("Stub!");
    }

    public abstract static class FingerprintGestureCallback {
        public void onGestureDetectionAvailabilityChanged(boolean available) {
        throw new RuntimeException("Stub!");
    }

        public void onGestureDetected(int gesture) {
        throw new RuntimeException("Stub!");
    }
    }
}
