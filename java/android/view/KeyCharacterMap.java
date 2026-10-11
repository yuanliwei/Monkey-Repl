package android.view;

import android.os.Parcel;
import android.os.Parcelable;

public class KeyCharacterMap implements Parcelable {
    public static final int BUILT_IN_KEYBOARD = 0;
    public static final int VIRTUAL_KEYBOARD = -1;
    public static final int NUMERIC = 1;
    public static final int PREDICTIVE = 2;
    public static final int ALPHA = 3;
    public static final int FULL = 4;
    public static final int SPECIAL_FUNCTION = 5;
    public static final char HEX_INPUT = '\uEF00';
    public static final char PICKER_DIALOG_INPUT = '\uEF01';
    public static final int MODIFIER_BEHAVIOR_CHORDED = 0;
    public static final int MODIFIER_BEHAVIOR_CHORDED_OR_TOGGLED = 1;
    public static final int COMBINING_ACCENT = 0x80000000;
    public static final int COMBINING_ACCENT_MASK = 0x7FFFFFFF;

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static boolean deviceHasKey(int i) {
        throw new RuntimeException("Stub!");
    }

    public static KeyCharacterMap load(int virtualKeyboard) {
        throw new RuntimeException("Stub!");
    }

    public KeyEvent[] getEvents(char[] chars) {
        throw new RuntimeException("Stub!");
    }
}