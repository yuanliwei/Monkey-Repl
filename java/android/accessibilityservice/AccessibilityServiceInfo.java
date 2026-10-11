package android.accessibilityservice;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.annotation.IntDef;
import android.annotation.UnsupportedAppUsage;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Parcel;
import android.os.Parcelable;

public class AccessibilityServiceInfo implements Parcelable {
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 0x00000001;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 0x00000002;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 0x00000004;
    public static final int CAPABILITY_CAN_REQUEST_FILTER_KEY_EVENTS = 0x00000008;
    public static final int CAPABILITY_CAN_CONTROL_MAGNIFICATION = 0x00000010;
    public static final int CAPABILITY_CAN_PERFORM_GESTURES = 0x00000020;
    public static final int CAPABILITY_CAN_REQUEST_FINGERPRINT_GESTURES = 0x00000040;
    public static final int FEEDBACK_SPOKEN = 0x0000001;
    public static final int FEEDBACK_HAPTIC = 0x0000002;
    public static final int FEEDBACK_AUDIBLE = 0x0000004;
    public static final int FEEDBACK_VISUAL = 0x0000008;
    public static final int FEEDBACK_GENERIC = 0x0000010;
    public static final int FEEDBACK_BRAILLE = 0x0000020;
    public static final int FEEDBACK_ALL_MASK = 0xFFFFFFFF;
    public static final int DEFAULT = 0x0000001;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 0x0000002;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 0x0000004;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 0x00000008;
    public static final int FLAG_REPORT_VIEW_IDS = 0x00000010;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 0x00000020;
    public static final int FLAG_RETRIEVE_INTERACTIVE_WINDOWS = 0x00000040;
    public static final int FLAG_ENABLE_ACCESSIBILITY_VOLUME = 0x00000080;
    public static final int FLAG_REQUEST_ACCESSIBILITY_BUTTON = 0x00000100;
    public static final int FLAG_REQUEST_FINGERPRINT_GESTURES = 0x00000200;
    public static final int FLAG_FORCE_DIRECT_BOOT_AWARE = 0x00010000;
    public int eventTypes;
    public String[] packageNames;

    @IntDef(flag = true, prefix = { "FEEDBACK_" }, value = { FEEDBACK_AUDIBLE, FEEDBACK_GENERIC, FEEDBACK_HAPTIC,
            FEEDBACK_SPOKEN, FEEDBACK_VISUAL, FEEDBACK_BRAILLE })
    @Retention(RetentionPolicy.SOURCE)
    public @interface FeedbackType {
    }

    @FeedbackType
    public int feedbackType;
    public long notificationTimeout;
    public int flags;
    public boolean crashed;

    public AccessibilityServiceInfo() {
        throw new RuntimeException("Stub!");
    }

    public AccessibilityServiceInfo(ResolveInfo resolveInfo, Context context)
            throws XmlPullParserException, IOException {
    }

    public void updateDynamicallyConfigurableProperties(AccessibilityServiceInfo other) {
        throw new RuntimeException("Stub!");
    }

    public void setComponentName(ComponentName component) {
        throw new RuntimeException("Stub!");
    }

    public ComponentName getComponentName() {
        throw new RuntimeException("Stub!");
    }

    public String getId() {
        throw new RuntimeException("Stub!");
    }

    public ResolveInfo getResolveInfo() {
        throw new RuntimeException("Stub!");
    }

    public String getSettingsActivityName() {
        throw new RuntimeException("Stub!");
    }

    public boolean getCanRetrieveWindowContent() {
        throw new RuntimeException("Stub!");
    }

    public int getCapabilities() {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public void setCapabilities(int capabilities) {
        throw new RuntimeException("Stub!");
    }

    public CharSequence loadSummary(PackageManager packageManager) {
        throw new RuntimeException("Stub!");
    }

    public String getDescription() {
        throw new RuntimeException("Stub!");
    }

    public String loadDescription(PackageManager packageManager) {
        throw new RuntimeException("Stub!");
    }

    public boolean isDirectBootAware() {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel parcel, int flagz) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public static String feedbackTypeToString(int feedbackType) {
        throw new RuntimeException("Stub!");
    }

    public static String flagToString(int flag) {
        throw new RuntimeException("Stub!");
    }

    public static String capabilityToString(int capability) {
        throw new RuntimeException("Stub!");
    }

    public List<CapabilityInfo> getCapabilityInfos() {
        throw new RuntimeException("Stub!");
    }

    public List<CapabilityInfo> getCapabilityInfos(Context context) {
        throw new RuntimeException("Stub!");
    }

    public static final class CapabilityInfo {
        public final int capability;
        public final int titleResId;
        public final int descResId;

        public CapabilityInfo(int capability, int titleResId, int descResId) {
            throw new RuntimeException("Stub!");
        }
    }

    public static final Parcelable.Creator<AccessibilityServiceInfo> CREATOR = new Parcelable.Creator<AccessibilityServiceInfo>() {
        public AccessibilityServiceInfo createFromParcel(Parcel parcel) {
            throw new RuntimeException("Stub!");
        }

        public AccessibilityServiceInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}