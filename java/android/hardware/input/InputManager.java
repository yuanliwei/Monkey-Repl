package android.hardware.input;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.annotation.IntDef;
import android.annotation.SdkConstant;
import android.annotation.SdkConstant.SdkConstantType;
import android.annotation.SystemService;
import android.content.Context;
import android.os.IBinder;
import android.view.InputDevice;
import android.view.InputEvent;

@SystemService(Context.INPUT_SERVICE)
public final class InputManager {
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final int MIN_POINTER_SPEED = -7;
    public static final int MAX_POINTER_SPEED = 7;
    public static final int DEFAULT_POINTER_SPEED = 0;   
    public static final int INJECT_INPUT_EVENT_MODE_ASYNC = 0; // see InputDispatcher.h
    public static final int INJECT_INPUT_EVENT_MODE_WAIT_FOR_RESULT = 1; // see InputDispatcher.h
    public static final int INJECT_INPUT_EVENT_MODE_WAIT_FOR_FINISH = 2; // see InputDispatcher.h

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(prefix = { "SWITCH_STATE_" }, value = {})
    public @interface SwitchState {
    }

    public static final int SWITCH_STATE_UNKNOWN = -1;
    public static final int SWITCH_STATE_OFF = 0;
    public static final int SWITCH_STATE_ON = 1;

    public static InputManager getInstance() {
        throw new RuntimeException("Stub!");
    }

    public InputDevice getInputDevice(int id) {
        throw new RuntimeException("Stub!");
    }

    public InputDevice getInputDeviceByDescriptor(String descriptor) {
        throw new RuntimeException("Stub!");
    }

    public int[] getInputDeviceIds() {
        throw new RuntimeException("Stub!");
    }

    public boolean isInputDeviceEnabled(int id) {
        throw new RuntimeException("Stub!");
    }

    public void enableInputDevice(int id) {
        throw new RuntimeException("Stub!");
    }

    public void disableInputDevice(int id) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterInputDeviceListener(InputDeviceListener listener) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterOnTabletModeChangedListener(OnTabletModeChangedListener listener) {
        throw new RuntimeException("Stub!");
    }

    public int getPointerSpeed(Context context) {
        throw new RuntimeException("Stub!");
    }

    public void setPointerSpeed(Context context, int speed) {
        throw new RuntimeException("Stub!");
    }

    public void tryPointerSpeed(int speed) {
        throw new RuntimeException("Stub!");
    }

    public boolean[] deviceHasKeys(int[] keyCodes) {
        throw new RuntimeException("Stub!");
    }

    public boolean[] deviceHasKeys(int id, int[] keyCodes) {
        throw new RuntimeException("Stub!");
    }

    public boolean injectInputEvent(InputEvent event, int mode) {
        throw new RuntimeException("Stub!");
    }

    public void setPointerIconType(int iconId) {
        throw new RuntimeException("Stub!");
    }

    public void requestPointerCapture(IBinder windowToken, boolean enable) {
        throw new RuntimeException("Stub!");
    }

    public interface InputDeviceListener {
    }

    public interface OnTabletModeChangedListener {
    }

}