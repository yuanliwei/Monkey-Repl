package android.os;

public interface PowerManager extends android.os.IInterface {
    public static final int WAKE_REASON_UNKNOWN = 0;
    public static final int WAKE_REASON_POWER_BUTTON = 1;
    public static final int WAKE_REASON_APPLICATION = 2;
    public static final int WAKE_REASON_PLUGGED_IN = 3;
    public static final int WAKE_REASON_GESTURE = 4;
    public static final int WAKE_REASON_CAMERA_LAUNCH = 5;
    public static final int WAKE_REASON_WAKE_KEY = 6;
    public static final int WAKE_REASON_WAKE_MOTION = 7;
    public static final int WAKE_REASON_HDMI = 8;
    public static final int WAKE_REASON_LID = 9;
}
