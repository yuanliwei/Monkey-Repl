
package android.content.pm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import android.annotation.IntDef;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.SdkConstant;
import android.annotation.SdkConstant.SdkConstantType;
import android.annotation.SystemApi;
import android.annotation.TestApi;
import android.annotation.UnsupportedAppUsage;
import android.annotation.UserIdInt;
import android.content.Intent;
import android.util.AndroidException;

public abstract class PackageManager {
        public static final boolean APPLY_DEFAULT_TO_DEVICE_PROTECTED_STORAGE = true;

        public static class NameNotFoundException extends AndroidException {

                private static final long serialVersionUID = -4527290492936194000L;

                public NameNotFoundException() {
                        throw new RuntimeException("Stub!");
                }

                public NameNotFoundException(String name) {
                        throw new RuntimeException("Stub!");
                }
        }

        @SystemApi
        public interface OnPermissionsChangedListener {

                public void onPermissionsChanged(int uid);
        }

        @IntDef(flag = true, prefix = { "GET_", "MATCH_" }, value = { GET_ACTIVITIES, GET_CONFIGURATIONS, GET_GIDS,
                        GET_INSTRUMENTATION, GET_INTENT_FILTERS, GET_META_DATA, GET_PERMISSIONS, GET_PROVIDERS,
                        GET_RECEIVERS, GET_SERVICES, GET_SHARED_LIBRARY_FILES, GET_SIGNATURES, GET_SIGNING_CERTIFICATES,
                        GET_URI_PERMISSION_PATTERNS, MATCH_UNINSTALLED_PACKAGES, MATCH_DISABLED_COMPONENTS,
                        MATCH_DISABLED_UNTIL_USED_COMPONENTS, MATCH_SYSTEM_ONLY, MATCH_FACTORY_ONLY,
                        MATCH_DEBUG_TRIAGED_MISSING, MATCH_INSTANT, MATCH_APEX, GET_DISABLED_COMPONENTS,
                        GET_DISABLED_UNTIL_USED_COMPONENTS, GET_UNINSTALLED_PACKAGES,
                        MATCH_HIDDEN_UNTIL_INSTALLED_COMPONENTS, })
        @Retention(RetentionPolicy.SOURCE)
        public @interface PackageInfoFlags {
        }

        @IntDef(flag = true, prefix = { "GET_", "MATCH_" }, value = { GET_META_DATA, GET_SHARED_LIBRARY_FILES,
                        MATCH_UNINSTALLED_PACKAGES, MATCH_SYSTEM_ONLY, MATCH_DEBUG_TRIAGED_MISSING,
                        MATCH_DISABLED_COMPONENTS, MATCH_DISABLED_UNTIL_USED_COMPONENTS, MATCH_INSTANT,
                        MATCH_STATIC_SHARED_LIBRARIES, GET_DISABLED_UNTIL_USED_COMPONENTS, GET_UNINSTALLED_PACKAGES,
                        MATCH_HIDDEN_UNTIL_INSTALLED_COMPONENTS, })
        @Retention(RetentionPolicy.SOURCE)
        public @interface ApplicationInfoFlags {
        }

        @IntDef(flag = true, prefix = { "GET_", "MATCH_" }, value = { GET_META_DATA, GET_SHARED_LIBRARY_FILES,
                        MATCH_ALL, MATCH_DEBUG_TRIAGED_MISSING, MATCH_DEFAULT_ONLY, MATCH_DISABLED_COMPONENTS,
                        MATCH_DISABLED_UNTIL_USED_COMPONENTS, MATCH_DIRECT_BOOT_AWARE, MATCH_DIRECT_BOOT_UNAWARE,
                        MATCH_SYSTEM_ONLY, MATCH_UNINSTALLED_PACKAGES, MATCH_INSTANT, MATCH_STATIC_SHARED_LIBRARIES,
                        GET_DISABLED_COMPONENTS, GET_DISABLED_UNTIL_USED_COMPONENTS, GET_UNINSTALLED_PACKAGES, })
        @Retention(RetentionPolicy.SOURCE)
        public @interface ComponentInfoFlags {
        }

        @IntDef(flag = true, prefix = { "GET_", "MATCH_" }, value = { GET_META_DATA, GET_RESOLVED_FILTER,
                        GET_SHARED_LIBRARY_FILES, MATCH_ALL, MATCH_DEBUG_TRIAGED_MISSING, MATCH_DISABLED_COMPONENTS,
                        MATCH_DISABLED_UNTIL_USED_COMPONENTS, MATCH_DEFAULT_ONLY, MATCH_DIRECT_BOOT_AWARE,
                        MATCH_DIRECT_BOOT_UNAWARE, MATCH_SYSTEM_ONLY, MATCH_UNINSTALLED_PACKAGES, MATCH_INSTANT,
                        GET_DISABLED_COMPONENTS, GET_DISABLED_UNTIL_USED_COMPONENTS, GET_UNINSTALLED_PACKAGES, })
        @Retention(RetentionPolicy.SOURCE)
        public @interface ResolveInfoFlags {
        }

        @IntDef(flag = true, prefix = { "GET_", "MATCH_" }, value = { GET_META_DATA, })
        @Retention(RetentionPolicy.SOURCE)
        public @interface PermissionInfoFlags {
        }

        @IntDef(flag = true, prefix = { "GET_", "MATCH_" }, value = { GET_META_DATA, })
        @Retention(RetentionPolicy.SOURCE)
        public @interface PermissionGroupInfoFlags {
        }

        @IntDef(flag = true, prefix = { "GET_", "MATCH_" }, value = { GET_META_DATA, })
        @Retention(RetentionPolicy.SOURCE)
        public @interface InstrumentationInfoFlags {
        }

        public static final int GET_ACTIVITIES = 0x00000001;

        public static final int GET_RECEIVERS = 0x00000002;

        public static final int GET_SERVICES = 0x00000004;

        public static final int GET_PROVIDERS = 0x00000008;

        public static final int GET_INSTRUMENTATION = 0x00000010;

        public static final int GET_INTENT_FILTERS = 0x00000020;

        @Deprecated
        public static final int GET_SIGNATURES = 0x00000040;

        public static final int GET_RESOLVED_FILTER = 0x00000040;

        public static final int GET_META_DATA = 0x00000080;

        public static final int GET_GIDS = 0x00000100;

        @Deprecated
        public static final int GET_DISABLED_COMPONENTS = 0x00000200;

        public static final int MATCH_DISABLED_COMPONENTS = 0x00000200;

        public static final int GET_SHARED_LIBRARY_FILES = 0x00000400;

        public static final int GET_URI_PERMISSION_PATTERNS = 0x00000800;
        public static final int GET_PERMISSIONS = 0x00001000;

        @Deprecated
        public static final int GET_UNINSTALLED_PACKAGES = 0x00002000;

        public static final int MATCH_UNINSTALLED_PACKAGES = 0x00002000;

        public static final int GET_CONFIGURATIONS = 0x00004000;

        @Deprecated
        public static final int GET_DISABLED_UNTIL_USED_COMPONENTS = 0x00008000;

        public static final int MATCH_DISABLED_UNTIL_USED_COMPONENTS = 0x00008000;

        public static final int MATCH_DEFAULT_ONLY = 0x00010000;

        public static final int MATCH_ALL = 0x00020000;

        public static final int MATCH_DIRECT_BOOT_UNAWARE = 0x00040000;

        public static final int MATCH_DIRECT_BOOT_AWARE = 0x00080000;

        public static final int MATCH_SYSTEM_ONLY = 0x00100000;

        @SystemApi
        @TestApi
        public static final int MATCH_FACTORY_ONLY = 0x00200000;

        @SystemApi
        public static final int MATCH_ANY_USER = 0x00400000;

        @TestApi
        public static final int MATCH_KNOWN_PACKAGES = MATCH_UNINSTALLED_PACKAGES | MATCH_ANY_USER;

        @SystemApi
        public static final int MATCH_INSTANT = 0x00800000;

        public static final int MATCH_VISIBLE_TO_INSTANT_APP_ONLY = 0x01000000;

        public static final int MATCH_EXPLICITLY_VISIBLE_ONLY = 0x02000000;

        public static final int MATCH_STATIC_SHARED_LIBRARIES = 0x04000000;

        public static final int GET_SIGNING_CERTIFICATES = 0x08000000;

        public static final int MATCH_DEBUG_TRIAGED_MISSING = 0x10000000;

        public static final int MATCH_HIDDEN_UNTIL_INSTALLED_COMPONENTS = 0x20000000;

        public static final int MATCH_APEX = 0x40000000;

        public static final int SKIP_CURRENT_PROFILE = 0x00000002;

        public static final int ONLY_IF_NO_MATCH_FOUND = 0x00000004;

        @IntDef(prefix = { "PERMISSION_" }, value = { PERMISSION_GRANTED, PERMISSION_DENIED })
        @Retention(RetentionPolicy.SOURCE)
        public @interface PermissionResult {
        }

        public static final int PERMISSION_GRANTED = 0;

        public static final int PERMISSION_DENIED = -1;

        @IntDef(prefix = { "SIGNATURE_" }, value = { SIGNATURE_MATCH, SIGNATURE_NEITHER_SIGNED,
                        SIGNATURE_FIRST_NOT_SIGNED, SIGNATURE_SECOND_NOT_SIGNED, SIGNATURE_NO_MATCH,
                        SIGNATURE_UNKNOWN_PACKAGE, })
        @Retention(RetentionPolicy.SOURCE)
        public @interface SignatureResult {
        }

        public static final int SIGNATURE_MATCH = 0;

        public static final int SIGNATURE_NEITHER_SIGNED = 1;

        public static final int SIGNATURE_FIRST_NOT_SIGNED = -1;

        public static final int SIGNATURE_SECOND_NOT_SIGNED = -2;

        public static final int SIGNATURE_NO_MATCH = -3;

        public static final int SIGNATURE_UNKNOWN_PACKAGE = -4;

        @IntDef(prefix = { "COMPONENT_ENABLED_STATE_" }, value = { COMPONENT_ENABLED_STATE_DEFAULT,
                        COMPONENT_ENABLED_STATE_ENABLED, COMPONENT_ENABLED_STATE_DISABLED,
                        COMPONENT_ENABLED_STATE_DISABLED_USER, COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED, })
        @Retention(RetentionPolicy.SOURCE)
        public @interface EnabledState {
        }

        public static final int COMPONENT_ENABLED_STATE_DEFAULT = 0;

        public static final int COMPONENT_ENABLED_STATE_ENABLED = 1;

        public static final int COMPONENT_ENABLED_STATE_DISABLED = 2;

        public static final int COMPONENT_ENABLED_STATE_DISABLED_USER = 3;

        public static final int COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED = 4;

        @IntDef(flag = true, prefix = { "INSTALL_" }, value = { INSTALL_FORWARD_LOCK, INSTALL_REPLACE_EXISTING,
                        INSTALL_ALLOW_TEST, INSTALL_EXTERNAL, INSTALL_INTERNAL, INSTALL_FROM_ADB, INSTALL_ALL_USERS,
                        INSTALL_ALLOW_DOWNGRADE, INSTALL_GRANT_RUNTIME_PERMISSIONS, INSTALL_FORCE_VOLUME_UUID,
                        INSTALL_FORCE_PERMISSION_PROMPT, INSTALL_INSTANT_APP, INSTALL_DONT_KILL_APP, INSTALL_FORCE_SDK,
                        INSTALL_FULL_APP, INSTALL_ALLOCATE_AGGRESSIVE, })
        @Retention(RetentionPolicy.SOURCE)
        public @interface InstallFlags {
        }

        @Deprecated
        public static final int INSTALL_FORWARD_LOCK = 0x00000001;

        @UnsupportedAppUsage
        public static final int INSTALL_REPLACE_EXISTING = 0x00000002;

        public static final int INSTALL_ALLOW_TEST = 0x00000004;

        @Deprecated
        public static final int INSTALL_EXTERNAL = 0x00000008;

        public static final int INSTALL_INTERNAL = 0x00000010;

        public static final int INSTALL_FROM_ADB = 0x00000020;

        public static final int INSTALL_ALL_USERS = 0x00000040;

        public static final int INSTALL_ALLOW_DOWNGRADE = 0x00000080;

        public static final int INSTALL_GRANT_RUNTIME_PERMISSIONS = 0x00000100;

        public static final int INSTALL_FORCE_VOLUME_UUID = 0x00000200;

        public static final int INSTALL_FORCE_PERMISSION_PROMPT = 0x00000400;

        public static final int INSTALL_INSTANT_APP = 0x00000800;

        public static final int INSTALL_DONT_KILL_APP = 0x00001000;

        public static final int INSTALL_FORCE_SDK = 0x00002000;

        public static final int INSTALL_FULL_APP = 0x00004000;

        public static final int INSTALL_ALLOCATE_AGGRESSIVE = 0x00008000;

        public static final int INSTALL_VIRTUAL_PRELOAD = 0x00010000;

        public static final int INSTALL_APEX = 0x00020000;

        @IntDef(flag = true, prefix = { "DONT_KILL_APP" }, value = { DONT_KILL_APP })
        @Retention(RetentionPolicy.SOURCE)
        public @interface EnabledFlags {
        }

        public static final int DONT_KILL_APP = 0x00000001;

        @IntDef(prefix = { "INSTALL_REASON_" }, value = { INSTALL_REASON_UNKNOWN, INSTALL_REASON_POLICY,
                        INSTALL_REASON_DEVICE_RESTORE, INSTALL_REASON_DEVICE_SETUP, INSTALL_REASON_USER })
        @Retention(RetentionPolicy.SOURCE)
        public @interface InstallReason {
        }

        public static final int INSTALL_REASON_UNKNOWN = 0;

        public static final int INSTALL_REASON_POLICY = 1;

        public static final int INSTALL_REASON_DEVICE_RESTORE = 2;

        public static final int INSTALL_REASON_DEVICE_SETUP = 3;

        public static final int INSTALL_REASON_USER = 4;

        @SystemApi
        public static final int INSTALL_SUCCEEDED = 1;

        @SystemApi
        public static final int INSTALL_FAILED_ALREADY_EXISTS = -1;

        @SystemApi
        public static final int INSTALL_FAILED_INVALID_APK = -2;

        @SystemApi
        public static final int INSTALL_FAILED_INVALID_URI = -3;

        @SystemApi
        public static final int INSTALL_FAILED_INSUFFICIENT_STORAGE = -4;

        @SystemApi
        public static final int INSTALL_FAILED_DUPLICATE_PACKAGE = -5;

        @SystemApi
        public static final int INSTALL_FAILED_NO_SHARED_USER = -6;

        @SystemApi
        public static final int INSTALL_FAILED_UPDATE_INCOMPATIBLE = -7;

        @SystemApi
        public static final int INSTALL_FAILED_SHARED_USER_INCOMPATIBLE = -8;

        @SystemApi
        public static final int INSTALL_FAILED_MISSING_SHARED_LIBRARY = -9;

        @SystemApi
        public static final int INSTALL_FAILED_REPLACE_COULDNT_DELETE = -10;

        @SystemApi
        public static final int INSTALL_FAILED_DEXOPT = -11;

        @SystemApi
        public static final int INSTALL_FAILED_OLDER_SDK = -12;

        @SystemApi
        public static final int INSTALL_FAILED_CONFLICTING_PROVIDER = -13;

        @SystemApi
        public static final int INSTALL_FAILED_NEWER_SDK = -14;

        @SystemApi
        public static final int INSTALL_FAILED_TEST_ONLY = -15;

        @SystemApi
        public static final int INSTALL_FAILED_CPU_ABI_INCOMPATIBLE = -16;

        @SystemApi
        public static final int INSTALL_FAILED_MISSING_FEATURE = -17;

        // ------ Errors related to sdcard
        @SystemApi
        public static final int INSTALL_FAILED_CONTAINER_ERROR = -18;

        @SystemApi
        public static final int INSTALL_FAILED_INVALID_INSTALL_LOCATION = -19;

        @SystemApi
        public static final int INSTALL_FAILED_MEDIA_UNAVAILABLE = -20;

        @SystemApi
        public static final int INSTALL_FAILED_VERIFICATION_TIMEOUT = -21;

        @SystemApi
        public static final int INSTALL_FAILED_VERIFICATION_FAILURE = -22;

        @SystemApi
        public static final int INSTALL_FAILED_PACKAGE_CHANGED = -23;

        public static final int INSTALL_FAILED_UID_CHANGED = -24;

        public static final int INSTALL_FAILED_VERSION_DOWNGRADE = -25;

        @SystemApi
        public static final int INSTALL_FAILED_PERMISSION_MODEL_DOWNGRADE = -26;

        @SystemApi
        public static final int INSTALL_FAILED_SANDBOX_VERSION_DOWNGRADE = -27;

        @SystemApi
        public static final int INSTALL_PARSE_FAILED_NOT_APK = -100;

        @SystemApi
        public static final int INSTALL_PARSE_FAILED_BAD_MANIFEST = -101;

        @SystemApi
        public static final int INSTALL_PARSE_FAILED_UNEXPECTED_EXCEPTION = -102;

        @SystemApi
        public static final int INSTALL_PARSE_FAILED_NO_CERTIFICATES = -103;

        @SystemApi
        public static final int INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES = -104;

        @SystemApi
        public static final int INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING = -105;

        @SystemApi
        public static final int INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME = -106;

        @SystemApi
        public static final int INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID = -107;

        @SystemApi
        public static final int INSTALL_PARSE_FAILED_MANIFEST_MALFORMED = -108;

        @SystemApi
        public static final int INSTALL_PARSE_FAILED_MANIFEST_EMPTY = -109;

        @SystemApi
        public static final int INSTALL_FAILED_INTERNAL_ERROR = -110;

        public static final int INSTALL_FAILED_USER_RESTRICTED = -111;

        public static final int INSTALL_FAILED_DUPLICATE_PERMISSION = -112;

        public static final int INSTALL_FAILED_NO_MATCHING_ABIS = -113;

        @UnsupportedAppUsage
        public static final int NO_NATIVE_LIBRARIES = -114;

        public static final int INSTALL_FAILED_ABORTED = -115;

        public static final int INSTALL_FAILED_INSTANT_APP_INVALID = -116;

        public static final int INSTALL_FAILED_BAD_DEX_METADATA = -117;

        @IntDef(flag = true, prefix = { "DELETE_" }, value = { DELETE_KEEP_DATA, DELETE_ALL_USERS, DELETE_SYSTEM_APP,
                        DELETE_DONT_KILL_APP, DELETE_CHATTY, })
        @Retention(RetentionPolicy.SOURCE)
        public @interface DeleteFlags {
        }

        public static final int DELETE_KEEP_DATA = 0x00000001;

        public static final int DELETE_ALL_USERS = 0x00000002;

        public static final int DELETE_SYSTEM_APP = 0x00000004;

        public static final int DELETE_DONT_KILL_APP = 0x00000008;

        public static final int DELETE_CHATTY = 0x80000000;

        public static final int DELETE_SUCCEEDED = 1;

        public static final int DELETE_FAILED_INTERNAL_ERROR = -1;

        public static final int DELETE_FAILED_DEVICE_POLICY_MANAGER = -2;

        public static final int DELETE_FAILED_USER_RESTRICTED = -3;

        public static final int DELETE_FAILED_OWNER_BLOCKED = -4;

        public static final int DELETE_FAILED_ABORTED = -5;

        public static final int DELETE_FAILED_USED_SHARED_LIBRARY = -6;

        public static final int MOVE_SUCCEEDED = -100;

        public static final int MOVE_FAILED_INSUFFICIENT_STORAGE = -1;

        public static final int MOVE_FAILED_DOESNT_EXIST = -2;

        public static final int MOVE_FAILED_SYSTEM_PACKAGE = -3;

        public static final int MOVE_FAILED_FORWARD_LOCKED = -4;

        public static final int MOVE_FAILED_INVALID_LOCATION = -5;

        public static final int MOVE_FAILED_INTERNAL_ERROR = -6;

        public static final int MOVE_FAILED_OPERATION_PENDING = -7;

        public static final int MOVE_FAILED_DEVICE_ADMIN = -8;

        public static final int MOVE_FAILED_3RD_PARTY_NOT_ALLOWED_ON_INTERNAL = -9;

        public static final int MOVE_FAILED_LOCKED_USER = -10;

        @Deprecated
        @UnsupportedAppUsage
        public static final int MOVE_INTERNAL = 0x00000001;

        @Deprecated
        @UnsupportedAppUsage
        public static final int MOVE_EXTERNAL_MEDIA = 0x00000002;

        public static final String EXTRA_MOVE_ID = "android.content.pm.extra.MOVE_ID";

        public static final int VERIFICATION_ALLOW_WITHOUT_SUFFICIENT = 2;

        public static final int VERIFICATION_ALLOW = 1;

        public static final int VERIFICATION_REJECT = -1;

        @SystemApi
        public static final int INTENT_FILTER_VERIFICATION_SUCCESS = 1;

        @SystemApi
        public static final int INTENT_FILTER_VERIFICATION_FAILURE = -1;

        @SystemApi
        public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_UNDEFINED = 0;

        @SystemApi
        public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_ASK = 1;

        @SystemApi
        public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_ALWAYS = 2;

        @SystemApi
        public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_NEVER = 3;

        @SystemApi
        public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_ALWAYS_ASK = 4;

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_AUDIO_LOW_LATENCY = "android.hardware.audio.low_latency";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_AUDIO_OUTPUT = "android.hardware.audio.output";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_AUDIO_PRO = "android.hardware.audio.pro";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_BLUETOOTH = "android.hardware.bluetooth";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_BLUETOOTH_LE = "android.hardware.bluetooth_le";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CAMERA = "android.hardware.camera";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CAMERA_AUTOFOCUS = "android.hardware.camera.autofocus";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CAMERA_ANY = "android.hardware.camera.any";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CAMERA_EXTERNAL = "android.hardware.camera.external";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CAMERA_FLASH = "android.hardware.camera.flash";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CAMERA_FRONT = "android.hardware.camera.front";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CAMERA_LEVEL_FULL = "android.hardware.camera.level.full";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CAMERA_CAPABILITY_MANUAL_SENSOR = "android.hardware.camera.capability.manual_sensor";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CAMERA_CAPABILITY_MANUAL_POST_PROCESSING = "android.hardware.camera.capability.manual_post_processing";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CAMERA_CAPABILITY_RAW = "android.hardware.camera.capability.raw";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CAMERA_AR = "android.hardware.camera.ar";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CONSUMER_IR = "android.hardware.consumerir";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CTS = "android.software.cts";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_LOCATION = "android.hardware.location";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_LOCATION_GPS = "android.hardware.location.gps";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_LOCATION_NETWORK = "android.hardware.location.network";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_RAM_LOW = "android.hardware.ram.low";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_RAM_NORMAL = "android.hardware.ram.normal";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_MICROPHONE = "android.hardware.microphone";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_NFC = "android.hardware.nfc";

        @Deprecated
        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_NFC_HCE = "android.hardware.nfc.hce";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_NFC_HOST_CARD_EMULATION = "android.hardware.nfc.hce";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_NFC_HOST_CARD_EMULATION_NFCF = "android.hardware.nfc.hcef";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_NFC_OFF_HOST_CARD_EMULATION_UICC = "android.hardware.nfc.uicc";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_NFC_OFF_HOST_CARD_EMULATION_ESE = "android.hardware.nfc.ese";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_NFC_BEAM = "android.sofware.nfc.beam";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_NFC_ANY = "android.hardware.nfc.any";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_OPENGLES_EXTENSION_PACK = "android.hardware.opengles.aep";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_VULKAN_HARDWARE_LEVEL = "android.hardware.vulkan.level";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_VULKAN_HARDWARE_COMPUTE = "android.hardware.vulkan.compute";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_VULKAN_HARDWARE_VERSION = "android.hardware.vulkan.version";

        @SystemApi
        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_BROADCAST_RADIO = "android.hardware.broadcastradio";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SENSOR_ACCELEROMETER = "android.hardware.sensor.accelerometer";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SENSOR_BAROMETER = "android.hardware.sensor.barometer";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SENSOR_COMPASS = "android.hardware.sensor.compass";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SENSOR_GYROSCOPE = "android.hardware.sensor.gyroscope";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SENSOR_LIGHT = "android.hardware.sensor.light";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SENSOR_PROXIMITY = "android.hardware.sensor.proximity";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SENSOR_STEP_COUNTER = "android.hardware.sensor.stepcounter";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SENSOR_STEP_DETECTOR = "android.hardware.sensor.stepdetector";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SENSOR_HEART_RATE = "android.hardware.sensor.heartrate";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SENSOR_HEART_RATE_ECG = "android.hardware.sensor.heartrate.ecg";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SENSOR_RELATIVE_HUMIDITY = "android.hardware.sensor.relative_humidity";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SENSOR_AMBIENT_TEMPERATURE = "android.hardware.sensor.ambient_temperature";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_HIFI_SENSORS = "android.hardware.sensor.hifi_sensors";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_ASSIST_GESTURE = "android.hardware.sensor.assist";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_TELEPHONY = "android.hardware.telephony";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_TELEPHONY_CDMA = "android.hardware.telephony.cdma";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_TELEPHONY_GSM = "android.hardware.telephony.gsm";

        @SystemApi
        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_TELEPHONY_CARRIERLOCK = "android.hardware.telephony.carrierlock";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_TELEPHONY_EUICC = "android.hardware.telephony.euicc";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_TELEPHONY_MBMS = "android.hardware.telephony.mbms";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_TELEPHONY_IMS = "android.hardware.telephony.ims";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_USB_HOST = "android.hardware.usb.host";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_USB_ACCESSORY = "android.hardware.usb.accessory";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SIP = "android.software.sip";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SIP_VOIP = "android.software.sip.voip";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CONNECTION_SERVICE = "android.software.connectionservice";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_TOUCHSCREEN = "android.hardware.touchscreen";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_TOUCHSCREEN_MULTITOUCH = "android.hardware.touchscreen.multitouch";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT = "android.hardware.touchscreen.multitouch.distinct";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND = "android.hardware.touchscreen.multitouch.jazzhand";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_FAKETOUCH = "android.hardware.faketouch";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_FAKETOUCH_MULTITOUCH_DISTINCT = "android.hardware.faketouch.multitouch.distinct";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_FAKETOUCH_MULTITOUCH_JAZZHAND = "android.hardware.faketouch.multitouch.jazzhand";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_FINGERPRINT = "android.hardware.fingerprint";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SCREEN_PORTRAIT = "android.hardware.screen.portrait";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SCREEN_LANDSCAPE = "android.hardware.screen.landscape";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_LIVE_WALLPAPER = "android.software.live_wallpaper";
        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_APP_WIDGETS = "android.software.app_widgets";
        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_CANT_SAVE_STATE = "android.software.cant_save_state";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_VOICE_RECOGNIZERS = "android.software.voice_recognizers";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_HOME_SCREEN = "android.software.home_screen";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_INPUT_METHODS = "android.software.input_methods";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_DEVICE_ADMIN = "android.software.device_admin";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_LEANBACK = "android.software.leanback";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_LEANBACK_ONLY = "android.software.leanback_only";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_LIVE_TV = "android.software.live_tv";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_WIFI = "android.hardware.wifi";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_WIFI_DIRECT = "android.hardware.wifi.direct";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_WIFI_AWARE = "android.hardware.wifi.aware";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_WIFI_PASSPOINT = "android.hardware.wifi.passpoint";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_WIFI_RTT = "android.hardware.wifi.rtt";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_LOWPAN = "android.hardware.lowpan";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_AUTOMOTIVE = "android.hardware.type.automotive";

        @Deprecated
        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_TELEVISION = "android.hardware.type.television";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_WATCH = "android.hardware.type.watch";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_EMBEDDED = "android.hardware.type.embedded";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_PC = "android.hardware.type.pc";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_PRINTING = "android.software.print";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_COMPANION_DEVICE_SETUP = "android.software.companion_device_setup";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_BACKUP = "android.software.backup";

        // If this feature is present, you also need to set
        // com.android.internal.R.config_freeformWindowManagement to true in your
        // configuration overlay.
        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_FREEFORM_WINDOW_MANAGEMENT = "android.software.freeform_window_management";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_PICTURE_IN_PICTURE = "android.software.picture_in_picture";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_ACTIVITIES_ON_SECONDARY_DISPLAYS = "android.software.activities_on_secondary_displays";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_MANAGED_USERS = "android.software.managed_users";

        public static final String FEATURE_MANAGED_PROFILES = "android.software.managed_users";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_VERIFIED_BOOT = "android.software.verified_boot";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_SECURELY_REMOVES_USERS = "android.software.securely_removes_users";

        @TestApi
        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_FILE_BASED_ENCRYPTION = "android.software.file_based_encryption";

        @TestApi
        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_ADOPTABLE_STORAGE = "android.software.adoptable_storage";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_WEBVIEW = "android.software.webview";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_ETHERNET = "android.hardware.ethernet";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_HDMI_CEC = "android.hardware.hdmi.cec";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_GAMEPAD = "android.hardware.gamepad";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_MIDI = "android.software.midi";

        @Deprecated
        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_VR_MODE = "android.software.vr.mode";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_VR_MODE_HIGH_PERFORMANCE = "android.hardware.vr.high_performance";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_AUTOFILL = "android.software.autofill";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_VR_HEADTRACKING = "android.hardware.vr.headtracking";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_STRONGBOX_KEYSTORE = "android.hardware.strongbox_keystore";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_DEVICE_ID_ATTESTATION = "android.software.device_id_attestation";

        public static final String ACTION_CLEAN_EXTERNAL_STORAGE = "android.content.pm.CLEAN_EXTERNAL_STORAGE";

        @SdkConstant(SdkConstantType.FEATURE)
        public static final String FEATURE_IPSEC_TUNNELS = "android.software.ipsec_tunnels";

        public static final String EXTRA_VERIFICATION_URI = "android.content.pm.extra.VERIFICATION_URI";

        public static final String EXTRA_VERIFICATION_ID = "android.content.pm.extra.VERIFICATION_ID";

        public static final String EXTRA_VERIFICATION_INSTALLER_PACKAGE = "android.content.pm.extra.VERIFICATION_INSTALLER_PACKAGE";

        public static final String EXTRA_VERIFICATION_INSTALL_FLAGS = "android.content.pm.extra.VERIFICATION_INSTALL_FLAGS";

        public static final String EXTRA_VERIFICATION_INSTALLER_UID = "android.content.pm.extra.VERIFICATION_INSTALLER_UID";

        public static final String EXTRA_VERIFICATION_PACKAGE_NAME = "android.content.pm.extra.VERIFICATION_PACKAGE_NAME";
        public static final String EXTRA_VERIFICATION_RESULT = "android.content.pm.extra.VERIFICATION_RESULT";

        @Deprecated
        public static final String EXTRA_VERIFICATION_VERSION_CODE = "android.content.pm.extra.VERIFICATION_VERSION_CODE";

        public static final String EXTRA_VERIFICATION_LONG_VERSION_CODE = "android.content.pm.extra.VERIFICATION_LONG_VERSION_CODE";

        public static final String EXTRA_INTENT_FILTER_VERIFICATION_ID = "android.content.pm.extra.INTENT_FILTER_VERIFICATION_ID";

        public static final String EXTRA_INTENT_FILTER_VERIFICATION_URI_SCHEME = "android.content.pm.extra.INTENT_FILTER_VERIFICATION_URI_SCHEME";

        public static final String EXTRA_INTENT_FILTER_VERIFICATION_HOSTS = "android.content.pm.extra.INTENT_FILTER_VERIFICATION_HOSTS";

        public static final String EXTRA_INTENT_FILTER_VERIFICATION_PACKAGE_NAME = "android.content.pm.extra.INTENT_FILTER_VERIFICATION_PACKAGE_NAME";

        @SystemApi
        public static final String ACTION_REQUEST_PERMISSIONS = "android.content.pm.action.REQUEST_PERMISSIONS";

        @SystemApi
        public static final String EXTRA_REQUEST_PERMISSIONS_NAMES = "android.content.pm.extra.REQUEST_PERMISSIONS_NAMES";

        @SystemApi
        public static final String EXTRA_REQUEST_PERMISSIONS_RESULTS = "android.content.pm.extra.REQUEST_PERMISSIONS_RESULTS";

        public static final String EXTRA_FAILURE_EXISTING_PACKAGE = "android.content.pm.extra.FAILURE_EXISTING_PACKAGE";

        public static final String EXTRA_FAILURE_EXISTING_PERMISSION = "android.content.pm.extra.FAILURE_EXISTING_PERMISSION";

        @SystemApi
        public static final int FLAG_PERMISSION_USER_SET = 1 << 0;

        @SystemApi
        public static final int FLAG_PERMISSION_USER_FIXED = 1 << 1;

        @SystemApi
        public static final int FLAG_PERMISSION_POLICY_FIXED = 1 << 2;

        @SystemApi
        public static final int FLAG_PERMISSION_REVOKE_ON_UPGRADE = 1 << 3;

        @SystemApi
        public static final int FLAG_PERMISSION_SYSTEM_FIXED = 1 << 4;

        @SystemApi
        public static final int FLAG_PERMISSION_GRANTED_BY_DEFAULT = 1 << 5;

        @SystemApi
        public static final int FLAG_PERMISSION_REVIEW_REQUIRED = 1 << 6;

        @SystemApi
        public static final int MASK_PERMISSION_FLAGS = 0xFF;

        public static final String SYSTEM_SHARED_LIBRARY_SERVICES = "android.ext.services";

        public static final String SYSTEM_SHARED_LIBRARY_SHARED = "android.ext.shared";

        public static final int NOTIFY_PACKAGE_USE_ACTIVITY = 0;

        public static final int NOTIFY_PACKAGE_USE_SERVICE = 1;

        public static final int NOTIFY_PACKAGE_USE_FOREGROUND_SERVICE = 2;

        public static final int NOTIFY_PACKAGE_USE_BROADCAST_RECEIVER = 3;

        public static final int NOTIFY_PACKAGE_USE_CONTENT_PROVIDER = 4;

        public static final int NOTIFY_PACKAGE_USE_BACKUP = 5;

        public static final int NOTIFY_PACKAGE_USE_CROSS_PACKAGE = 6;

        public static final int NOTIFY_PACKAGE_USE_INSTRUMENTATION = 7;

        public static final int NOTIFY_PACKAGE_USE_REASONS_COUNT = 8;

        public static final int VERSION_CODE_HIGHEST = -1;

        public int getUserId() {
                throw new RuntimeException("Stub!");
        }

        public abstract PackageInfo getPackageInfo(String packageName, @PackageInfoFlags int flags)
                        throws NameNotFoundException;

        public abstract PackageInfo getPackageInfo(VersionedPackage versionedPackage, @PackageInfoFlags int flags)
                        throws NameNotFoundException;

        @UnsupportedAppUsage
        public abstract PackageInfo getPackageInfoAsUser(String packageName, @PackageInfoFlags int flags,
                        @UserIdInt int userId) throws NameNotFoundException;

        public abstract String[] currentToCanonicalPackageNames(String[] names);

        public abstract String[] canonicalToCurrentPackageNames(String[] names);

        public abstract @Nullable Intent getLaunchIntentForPackage(@NonNull String packageName);

        public abstract @Nullable Intent getLeanbackLaunchIntentForPackage(@NonNull String packageName);

        public abstract @Nullable Intent getCarLaunchIntentForPackage(@NonNull String packageName);

        public abstract int[] getPackageGids(@NonNull String packageName) throws NameNotFoundException;

        public abstract int[] getPackageGids(String packageName, @PackageInfoFlags int flags)
                        throws NameNotFoundException;

        public abstract int getPackageUid(String packageName, @PackageInfoFlags int flags) throws NameNotFoundException;

        @UnsupportedAppUsage
        public abstract int getPackageUidAsUser(String packageName, @UserIdInt int userId) throws NameNotFoundException;

        @UnsupportedAppUsage
        public abstract int getPackageUidAsUser(String packageName, @PackageInfoFlags int flags, @UserIdInt int userId)
                        throws NameNotFoundException;

        public abstract PermissionInfo getPermissionInfo(String name, @PermissionInfoFlags int flags)
                        throws NameNotFoundException;

        public abstract List<PermissionInfo> queryPermissionsByGroup(String group, @PermissionInfoFlags int flags)
                        throws NameNotFoundException;

        @TestApi
        public abstract boolean isPermissionReviewModeEnabled();

        public abstract CharSequence getText(String packageName, int resid, ApplicationInfo appInfo);

}
