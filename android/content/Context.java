
package android.content;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

import javax.security.auth.login.Configuration;

import android.annotation.CheckResult;
import android.annotation.IntDef;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.RequiresPermission;
import android.annotation.StringDef;
import android.annotation.SystemApi;
import android.annotation.TestApi;
import android.annotation.UserIdInt;
import android.app.IApplicationThread;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;

public abstract class Context {
    @IntDef(flag = true, prefix = { "MODE_" }, value = { MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE,
            MODE_APPEND, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface FileMode {
    }

    @IntDef(flag = true, prefix = { "MODE_" }, value = { MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE,
            MODE_MULTI_PROCESS, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface PreferencesMode {
    }

    @IntDef(flag = true, prefix = { "MODE_" }, value = { MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE,
            MODE_ENABLE_WRITE_AHEAD_LOGGING, MODE_NO_LOCALIZED_COLLATORS, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface DatabaseMode {
    }

    public static final int MODE_PRIVATE = 0x0000;

    @Deprecated
    public static final int MODE_WORLD_READABLE = 0x0001;

    @Deprecated
    public static final int MODE_WORLD_WRITEABLE = 0x0002;

    public static final int MODE_APPEND = 0x8000;

    @Deprecated
    public static final int MODE_MULTI_PROCESS = 0x0004;

    public static final int MODE_ENABLE_WRITE_AHEAD_LOGGING = 0x0008;

    public static final int MODE_NO_LOCALIZED_COLLATORS = 0x0010;

    @IntDef(flag = true, prefix = { "BIND_" }, value = { BIND_AUTO_CREATE, BIND_DEBUG_UNBIND, BIND_NOT_FOREGROUND,
            BIND_ABOVE_CLIENT, BIND_ALLOW_OOM_MANAGEMENT, BIND_WAIVE_PRIORITY, BIND_IMPORTANT,
            BIND_ADJUST_WITH_ACTIVITY })
    @Retention(RetentionPolicy.SOURCE)
    public @interface BindServiceFlags {
    }

    public static final int BIND_AUTO_CREATE = 0x0001;

    public static final int BIND_DEBUG_UNBIND = 0x0002;

    public static final int BIND_NOT_FOREGROUND = 0x0004;

    public static final int BIND_ABOVE_CLIENT = 0x0008;

    public static final int BIND_ALLOW_OOM_MANAGEMENT = 0x0010;

    public static final int BIND_WAIVE_PRIORITY = 0x0020;

    public static final int BIND_IMPORTANT = 0x0040;

    public static final int BIND_ADJUST_WITH_ACTIVITY = 0x0080;

    public static final int BIND_ADJUST_BELOW_PERCEPTIBLE = 0x0100;

    public static final int BIND_ALLOW_INSTANT = 0x00400000;

    public static final int BIND_IMPORTANT_BACKGROUND = 0x00800000;

    public static final int BIND_ALLOW_WHITELIST_MANAGEMENT = 0x01000000;

    public static final int BIND_FOREGROUND_SERVICE_WHILE_AWAKE = 0x02000000;

    public static final int BIND_FOREGROUND_SERVICE = 0x04000000;

    public static final int BIND_TREAT_LIKE_ACTIVITY = 0x08000000;

    public static final int BIND_VISIBLE = 0x10000000;

    public static final int BIND_SHOWING_UI = 0x20000000;

    public static final int BIND_NOT_VISIBLE = 0x40000000;

    public static final int BIND_EXTERNAL_SERVICE = 0x80000000;

    @IntDef(flag = true, prefix = { "RECEIVER_VISIBLE_" }, value = { RECEIVER_VISIBLE_TO_INSTANT_APPS })
    @Retention(RetentionPolicy.SOURCE)
    public @interface RegisterReceiverFlags {
    }

    public static final int RECEIVER_VISIBLE_TO_INSTANT_APPS = 0x1;

    public abstract AssetManager getAssets();

    public abstract Resources getResources();

    public abstract PackageManager getPackageManager();

    public abstract ContentResolver getContentResolver();

    public abstract Looper getMainLooper();

    public Executor getMainExecutor() {
        throw new RuntimeException("Stub!");
    }

    public abstract Context getApplicationContext();

    public abstract boolean moveDatabaseFrom(Context sourceContext, String name);

    public abstract boolean deleteDatabase(String name);

    public abstract File getDatabasePath(String name);

    public abstract String[] databaseList();

    @Deprecated
    public abstract Drawable getWallpaper();

    @Deprecated
    public abstract Drawable peekWallpaper();

    @Deprecated
    public abstract int getWallpaperDesiredMinimumWidth();

    @Deprecated
    public abstract int getWallpaperDesiredMinimumHeight();

    @Deprecated
    public abstract void setWallpaper(Bitmap bitmap) throws IOException;

    @Deprecated
    public abstract void setWallpaper(InputStream data) throws IOException;

    @Deprecated
    public abstract void clearWallpaper() throws IOException;

    public abstract void startActivity(@RequiresPermission Intent intent);

    @SystemApi
    public void startActivityAsUser(@RequiresPermission @NonNull Intent intent, @NonNull UserHandle user) {
        throw new RuntimeException("Stub!");
    }

    public abstract void startActivity(@RequiresPermission Intent intent, @Nullable Bundle options);

    public void startActivityAsUser(@RequiresPermission Intent intent, @Nullable Bundle options, UserHandle userId) {
        throw new RuntimeException("Stub!");
    }

    public void startActivityForResult(@NonNull String who, Intent intent, int requestCode, @Nullable Bundle options) {
        throw new RuntimeException("Stub!");
    }

    public boolean canStartActivityForResult() {
        throw new RuntimeException("Stub!");
    }

    public abstract void startActivities(@RequiresPermission Intent[] intents);

    public abstract void startActivities(@RequiresPermission Intent[] intents, Bundle options);

    public int startActivitiesAsUser(Intent[] intents, Bundle options, UserHandle userHandle) {
        throw new RuntimeException("Stub!");
    }

    public abstract void sendBroadcast(@RequiresPermission Intent intent);

    public abstract void sendBroadcast(@RequiresPermission Intent intent, @Nullable String receiverPermission);

    public abstract void sendBroadcastMultiplePermissions(Intent intent, String[] receiverPermissions);

    public abstract void sendBroadcastAsUserMultiplePermissions(Intent intent, UserHandle user,
            String[] receiverPermissions);

    @SystemApi
    public abstract void sendBroadcast(Intent intent, @Nullable String receiverPermission, @Nullable Bundle options);

    public abstract void sendBroadcast(Intent intent, String receiverPermission, int appOp);

    public abstract void sendOrderedBroadcast(@RequiresPermission Intent intent, @Nullable String receiverPermission);

    public abstract void sendBroadcastAsUser(@RequiresPermission Intent intent, UserHandle user);

    public abstract void sendBroadcastAsUser(@RequiresPermission Intent intent, UserHandle user,
            @Nullable String receiverPermission);

    @SystemApi
    public abstract void sendBroadcastAsUser(@RequiresPermission Intent intent, UserHandle user,
            @Nullable String receiverPermission, @Nullable Bundle options);

    public abstract void sendBroadcastAsUser(@RequiresPermission Intent intent, UserHandle user,
            @Nullable String receiverPermission, int appOp);

    @Deprecated
    public abstract void sendStickyBroadcast(@RequiresPermission Intent intent);

    @Deprecated
    public abstract void removeStickyBroadcast(@RequiresPermission Intent intent);

    @Deprecated
    public abstract void sendStickyBroadcastAsUser(@RequiresPermission Intent intent, UserHandle user);

    @Deprecated
    public abstract void sendStickyBroadcastAsUser(@RequiresPermission Intent intent, UserHandle user, Bundle options);

    @Deprecated
    public abstract void removeStickyBroadcastAsUser(@RequiresPermission Intent intent, UserHandle user);

    @Nullable
    public abstract ComponentName startService(Intent service);

    @Nullable
    public abstract ComponentName startForegroundService(Intent service);

    @Nullable
    public abstract ComponentName startForegroundServiceAsUser(Intent service, UserHandle user);

    public abstract boolean stopService(Intent service);

    @Nullable
    public abstract ComponentName startServiceAsUser(Intent service, UserHandle user);

    public abstract boolean stopServiceAsUser(Intent service, UserHandle user);

    public abstract boolean startInstrumentation(@NonNull ComponentName className, @Nullable String profileFile,
            @Nullable Bundle arguments);

    @StringDef(suffix = { "_SERVICE" }, value = { POWER_SERVICE, WINDOW_SERVICE, LAYOUT_INFLATER_SERVICE,
            ACCOUNT_SERVICE, ACTIVITY_SERVICE, ALARM_SERVICE, NOTIFICATION_SERVICE, ACCESSIBILITY_SERVICE,
            CAPTIONING_SERVICE, KEYGUARD_SERVICE, LOCATION_SERVICE,
            // @hide: COUNTRY_DETECTOR,
            SEARCH_SERVICE, SENSOR_SERVICE, STORAGE_SERVICE, STORAGE_STATS_SERVICE, WALLPAPER_SERVICE,
            TIME_ZONE_RULES_MANAGER_SERVICE, VIBRATOR_SERVICE,
            // @hide: STATUS_BAR_SERVICE,
            CONNECTIVITY_SERVICE,
            // @hide: IP_MEMORY_STORE_SERVICE,
            IPSEC_SERVICE, TEST_NETWORK_SERVICE,
            // @hide: UPDATE_LOCK_SERVICE,
            // @hide: NETWORKMANAGEMENT_SERVICE,
            NETWORK_STATS_SERVICE,
            // @hide: NETWORK_POLICY_SERVICE,
            WIFI_SERVICE, WIFI_AWARE_SERVICE, WIFI_P2P_SERVICE, WIFI_SCANNING_SERVICE,
            // @hide: LOWPAN_SERVICE,
            // @hide: WIFI_RTT_SERVICE,
            // @hide: ETHERNET_SERVICE,
            WIFI_RTT_RANGING_SERVICE, NSD_SERVICE, AUDIO_SERVICE, FINGERPRINT_SERVICE, MEDIA_ROUTER_SERVICE,
            TELEPHONY_SERVICE, TELEPHONY_SUBSCRIPTION_SERVICE, CARRIER_CONFIG_SERVICE, TELECOM_SERVICE,
            CLIPBOARD_SERVICE, INPUT_METHOD_SERVICE, TEXT_SERVICES_MANAGER_SERVICE, TEXT_CLASSIFICATION_SERVICE,
            APPWIDGET_SERVICE,
            // @hide: VOICE_INTERACTION_MANAGER_SERVICE,
            // @hide: BACKUP_SERVICE,
            DROPBOX_SERVICE,
            // @hide: DEVICE_IDLE_CONTROLLER,
            DEVICE_POLICY_SERVICE, UI_MODE_SERVICE, DOWNLOAD_SERVICE, NFC_SERVICE, BLUETOOTH_SERVICE,
            // @hide: SIP_SERVICE,
            USB_SERVICE, LAUNCHER_APPS_SERVICE,
            // @hide: SERIAL_SERVICE,
            // @hide: HDMI_CONTROL_SERVICE,
            INPUT_SERVICE, DISPLAY_SERVICE, USER_SERVICE, RESTRICTIONS_SERVICE, APP_OPS_SERVICE, CAMERA_SERVICE,
            PRINT_SERVICE, CONSUMER_IR_SERVICE,
            // @hide: TRUST_SERVICE,
            TV_INPUT_SERVICE,
            // @hide: NETWORK_SCORE_SERVICE,
            USAGE_STATS_SERVICE, MEDIA_SESSION_SERVICE, BATTERY_SERVICE, JOB_SCHEDULER_SERVICE,
            // @hide: PERSISTENT_DATA_BLOCK_SERVICE,
            // @hide: OEM_LOCK_SERVICE,
            MEDIA_PROJECTION_SERVICE, MIDI_SERVICE, RADIO_SERVICE, HARDWARE_PROPERTIES_SERVICE,
            // @hide: SOUND_TRIGGER_SERVICE,
            SHORTCUT_SERVICE,
            // @hide: CONTEXTHUB_SERVICE,
            SYSTEM_HEALTH_SERVICE,
            // @hide: INCIDENT_SERVICE,
            // @hide: STATS_COMPANION_SERVICE,
            COMPANION_DEVICE_SERVICE, CROSS_PROFILE_APPS_SERVICE,
            // @hide: SYSTEM_UPDATE_SERVICE,
            // @hide: TIME_DETECTOR_SERVICE,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ServiceName {
    }

    public abstract @Nullable Object getSystemService(@ServiceName @NonNull String name);

    public final @Nullable <T> T getSystemService(@NonNull Class<T> serviceClass) {
        throw new RuntimeException("Stub!");
    }

    public abstract @Nullable String getSystemServiceName(@NonNull Class<?> serviceClass);

    public static final String POWER_SERVICE = "power";

    public static final String RECOVERY_SERVICE = "recovery";

    @SystemApi
    public static final String SYSTEM_UPDATE_SERVICE = "system_update";

    public static final String WINDOW_SERVICE = "window";

    public static final String LAYOUT_INFLATER_SERVICE = "layout_inflater";

    public static final String ACCOUNT_SERVICE = "account";

    public static final String ACTIVITY_SERVICE = "activity";

    public static final String ALARM_SERVICE = "alarm";

    public static final String NOTIFICATION_SERVICE = "notification";

    public static final String ACCESSIBILITY_SERVICE = "accessibility";

    public static final String CAPTIONING_SERVICE = "captioning";

    public static final String KEYGUARD_SERVICE = "keyguard";

    public static final String LOCATION_SERVICE = "location";

    public static final String COUNTRY_DETECTOR = "country_detector";

    public static final String SEARCH_SERVICE = "search";

    public static final String SENSOR_SERVICE = "sensor";

    public static final String STORAGE_SERVICE = "storage";

    public static final String STORAGE_STATS_SERVICE = "storagestats";

    public static final String WALLPAPER_SERVICE = "wallpaper";

    public static final String VIBRATOR_SERVICE = "vibrator";

    public static final String STATUS_BAR_SERVICE = "statusbar";

    public static final String CONNECTIVITY_SERVICE = "connectivity";

    @SystemApi
    public static final String NETD_SERVICE = "netd";

    public static final String NETWORK_STACK_SERVICE = "network_stack";

    public static final String IPSEC_SERVICE = "ipsec";

    @TestApi
    public static final String TEST_NETWORK_SERVICE = "test_network";

    public static final String UPDATE_LOCK_SERVICE = "updatelock";

    public static final String NETWORKMANAGEMENT_SERVICE = "network_management";

    public static final String SLICE_SERVICE = "slice";

    public static final String NETWORK_STATS_SERVICE = "netstats";
    public static final String NETWORK_POLICY_SERVICE = "netpolicy";
    public static final String NETWORK_WATCHLIST_SERVICE = "network_watchlist";

    public static final String WIFI_SERVICE = "wifi";

    public static final String WIFI_P2P_SERVICE = "wifip2p";

    public static final String WIFI_AWARE_SERVICE = "wifiaware";

    @SystemApi
    public static final String WIFI_SCANNING_SERVICE = "wifiscanner";

    @SystemApi
    @Deprecated
    public static final String WIFI_RTT_SERVICE = "rttmanager";

    public static final String WIFI_RTT_RANGING_SERVICE = "wifirtt";

    public static final String LOWPAN_SERVICE = "lowpan";

    public static final String ETHERNET_SERVICE = "ethernet";

    public static final String NSD_SERVICE = "servicediscovery";

    public static final String AUDIO_SERVICE = "audio";

    public static final String FINGERPRINT_SERVICE = "fingerprint";

    public static final String MEDIA_ROUTER_SERVICE = "media_router";

    public static final String MEDIA_SESSION_SERVICE = "media_session";

    public static final String TELEPHONY_SERVICE = "phone";

    public static final String TELEPHONY_SUBSCRIPTION_SERVICE = "telephony_subscription_service";

    public static final String TELECOM_SERVICE = "telecom";

    public static final String CARRIER_CONFIG_SERVICE = "carrier_config";

    public static final String EUICC_SERVICE = "euicc";

    @SystemApi
    public static final String EUICC_CARD_SERVICE = "euicc_card";

    public static final String CLIPBOARD_SERVICE = "clipboard";

    public static final String TEXT_CLASSIFICATION_SERVICE = "textclassification";

    public static final String INPUT_METHOD_SERVICE = "input_method";

    public static final String TEXT_SERVICES_MANAGER_SERVICE = "textservices";

    public static final String APPWIDGET_SERVICE = "appwidget";

    public static final String VOICE_INTERACTION_MANAGER_SERVICE = "voiceinteraction";

    public static final String AUTOFILL_MANAGER_SERVICE = "autofill";

    public static final String SOUND_TRIGGER_SERVICE = "soundtrigger";

    @SystemApi
    public static final String BACKUP_SERVICE = "backup";

    public static final String DROPBOX_SERVICE = "dropbox";

    public static final String DEVICE_IDLE_CONTROLLER = "deviceidle";

    public static final String DEVICE_POLICY_SERVICE = "device_policy";

    public static final String UI_MODE_SERVICE = "uimode";

    public static final String DOWNLOAD_SERVICE = "download";

    public static final String BATTERY_SERVICE = "batterymanager";

    public static final String NFC_SERVICE = "nfc";

    public static final String BLUETOOTH_SERVICE = "bluetooth";

    public static final String SIP_SERVICE = "sip";

    public static final String USB_SERVICE = "usb";

    public static final String SERIAL_SERVICE = "serial";

    @SystemApi
    public static final String HDMI_CONTROL_SERVICE = "hdmi_control";

    public static final String INPUT_SERVICE = "input";

    public static final String DISPLAY_SERVICE = "display";

    public static final String USER_SERVICE = "user";

    public static final String LAUNCHER_APPS_SERVICE = "launcherapps";

    public static final String RESTRICTIONS_SERVICE = "restrictions";

    public static final String APP_OPS_SERVICE = "appops";

    public static final String CAMERA_SERVICE = "camera";

    public static final String PRINT_SERVICE = "print";

    public static final String COMPANION_DEVICE_SERVICE = "companiondevice";

    public static final String CONSUMER_IR_SERVICE = "consumer_ir";

    public static final String TRUST_SERVICE = "trust";

    public static final String TV_INPUT_SERVICE = "tv_input";

    @SystemApi
    public static final String NETWORK_SCORE_SERVICE = "network_score";

    public static final String USAGE_STATS_SERVICE = "usagestats";

    public static final String JOB_SCHEDULER_SERVICE = "jobscheduler";

    @SystemApi
    public static final String PERSISTENT_DATA_BLOCK_SERVICE = "persistent_data_block";

    @SystemApi
    public static final String OEM_LOCK_SERVICE = "oem_lock";

    public static final String MEDIA_PROJECTION_SERVICE = "media_projection";

    public static final String MIDI_SERVICE = "midi";

    public static final String RADIO_SERVICE = "broadcastradio";

    public static final String HARDWARE_PROPERTIES_SERVICE = "hardware_properties";

    public static final String SHORTCUT_SERVICE = "shortcut";

    @SystemApi
    public static final String CONTEXTHUB_SERVICE = "contexthub";

    public static final String SYSTEM_HEALTH_SERVICE = "systemhealth";

    public static final String GATEKEEPER_SERVICE = "android.service.gatekeeper.IGateKeeperService";

    public static final String DEVICE_IDENTIFIERS_SERVICE = "device_identifiers";

    public static final String INCIDENT_SERVICE = "incident";

    public static final String STATS_COMPANION_SERVICE = "statscompanion";

    @SystemApi
    public static final String STATS_MANAGER = "stats";

    // TODO: Expose API when the implementation is more complete.
    // @SystemApi
    public static final String BUGREPORT_SERVICE = "bugreport";

    public static final String OVERLAY_SERVICE = "overlay";

    @SystemApi
    public static final String VR_SERVICE = "vrmanager";

    public static final String TIME_ZONE_RULES_MANAGER_SERVICE = "timezone";

    public static final String CROSS_PROFILE_APPS_SERVICE = "crossprofileapps";

    @SystemApi
    public static final String SECURE_ELEMENT_SERVICE = "secure_element";

    public static final String TIME_DETECTOR_SERVICE = "time_detector";

    public static final String TELEPHONY_RCS_SERVICE = "ircs";

    @SystemApi
    public static final String DYNAMIC_ANDROID_SERVICE = "dynamic_android";

    @CheckResult(suggest = "#enforcePermission(String,int,int,String)")
    @PackageManager.PermissionResult
    public abstract int checkPermission(@NonNull String permission, int pid, int uid);

    @PackageManager.PermissionResult
    public abstract int checkPermission(@NonNull String permission, int pid, int uid, IBinder callerToken);

    @CheckResult(suggest = "#enforceCallingPermission(String,String)")
    @PackageManager.PermissionResult
    public abstract int checkCallingPermission(@NonNull String permission);

    @CheckResult(suggest = "#enforceCallingOrSelfPermission(String,String)")
    @PackageManager.PermissionResult
    public abstract int checkCallingOrSelfPermission(@NonNull String permission);

    @PackageManager.PermissionResult
    public abstract int checkSelfPermission(@NonNull String permission);

    public abstract void enforcePermission(@NonNull String permission, int pid, int uid, @Nullable String message);

    public abstract void enforceCallingPermission(@NonNull String permission, @Nullable String message);

    public abstract void enforceCallingOrSelfPermission(@NonNull String permission, @Nullable String message);

    public abstract void grantUriPermission(String toPackage, Uri uri, @Intent.GrantUriMode int modeFlags);

    public abstract void revokeUriPermission(Uri uri, @Intent.AccessUriMode int modeFlags);

    public abstract void revokeUriPermission(String toPackage, Uri uri, @Intent.AccessUriMode int modeFlags);

    @CheckResult(suggest = "#enforceUriPermission(Uri,int,int,String)")
    @PackageManager.PermissionResult
    public abstract int checkUriPermission(Uri uri, int pid, int uid, @Intent.AccessUriMode int modeFlags);

    @PackageManager.PermissionResult
    public abstract int checkUriPermission(Uri uri, int pid, int uid, @Intent.AccessUriMode int modeFlags,
            IBinder callerToken);

    @CheckResult(suggest = "#enforceCallingUriPermission(Uri,int,String)")
    @PackageManager.PermissionResult
    public abstract int checkCallingUriPermission(Uri uri, @Intent.AccessUriMode int modeFlags);

    @CheckResult(suggest = "#enforceCallingOrSelfUriPermission(Uri,int,String)")
    @PackageManager.PermissionResult
    public abstract int checkCallingOrSelfUriPermission(Uri uri, @Intent.AccessUriMode int modeFlags);

    @CheckResult(suggest = "#enforceUriPermission(Uri,String,String,int,int,int,String)")
    @PackageManager.PermissionResult
    public abstract int checkUriPermission(@Nullable Uri uri, @Nullable String readPermission,
            @Nullable String writePermission, int pid, int uid, @Intent.AccessUriMode int modeFlags);

    public abstract void enforceUriPermission(Uri uri, int pid, int uid, @Intent.AccessUriMode int modeFlags,
            String message);

    public abstract void enforceCallingUriPermission(Uri uri, @Intent.AccessUriMode int modeFlags, String message);

    public abstract void enforceCallingOrSelfUriPermission(Uri uri, @Intent.AccessUriMode int modeFlags,
            String message);

    public abstract void enforceUriPermission(@Nullable Uri uri, @Nullable String readPermission,
            @Nullable String writePermission, int pid, int uid, @Intent.AccessUriMode int modeFlags,
            @Nullable String message);

    @IntDef(flag = true, prefix = { "CONTEXT_" }, value = { CONTEXT_INCLUDE_CODE, CONTEXT_IGNORE_SECURITY,
            CONTEXT_RESTRICTED, CONTEXT_DEVICE_PROTECTED_STORAGE, CONTEXT_CREDENTIAL_PROTECTED_STORAGE,
            CONTEXT_REGISTER_PACKAGE, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface CreatePackageOptions {
    }

    public static final int CONTEXT_INCLUDE_CODE = 0x00000001;

    public static final int CONTEXT_IGNORE_SECURITY = 0x00000002;

    public static final int CONTEXT_RESTRICTED = 0x00000004;

    public static final int CONTEXT_DEVICE_PROTECTED_STORAGE = 0x00000008;

    public static final int CONTEXT_CREDENTIAL_PROTECTED_STORAGE = 0x00000010;

    public static final int CONTEXT_REGISTER_PACKAGE = 0x40000000;

    public abstract Context createPackageContext(String packageName, @CreatePackageOptions int flags)
            throws PackageManager.NameNotFoundException;

    @SystemApi
    public Context createPackageContextAsUser(String packageName, @CreatePackageOptions int flags, UserHandle user)
            throws PackageManager.NameNotFoundException {

        return this;
    }

    public abstract Context createApplicationContext(ApplicationInfo application, @CreatePackageOptions int flags)
            throws PackageManager.NameNotFoundException;

    public abstract Context createContextForSplit(String splitName) throws PackageManager.NameNotFoundException;

    @TestApi
    public UserHandle getUser() {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    public @UserIdInt int getUserId() {
        throw new RuntimeException("Stub!");
    }

    public abstract Context createConfigurationContext(@NonNull Configuration overrideConfiguration);

    public abstract Context createDisplayContext(@NonNull Display display);

    public abstract Context createDeviceProtectedStorageContext();

    @SystemApi
    public abstract Context createCredentialProtectedStorageContext();

    public abstract Display getDisplay();

    public abstract void updateDisplay(int displayId);

    public boolean isRestricted() {
        throw new RuntimeException("Stub!");
    }

    public abstract boolean isDeviceProtectedStorage();

    @SystemApi
    public abstract boolean isCredentialProtectedStorage();

    public abstract boolean canLoadUnsafeResources();

    public IBinder getActivityToken() {
        throw new RuntimeException("Stub!");
    }

    public IApplicationThread getIApplicationThread() {
        throw new RuntimeException("Stub!");
    }

    public Handler getMainThreadHandler() {
        throw new RuntimeException("Stub!");
    }

    public boolean isAutofillCompatibilityEnabled() {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    public void setAutofillCompatibilityEnabled(@SuppressWarnings("unused") boolean autofillCompatEnabled) {
        throw new RuntimeException("Stub!");
    }

    public void assertRuntimeOverlayThemable() {
        throw new RuntimeException("Stub!");
    }
}
