package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class ApplicationInfo extends PackageItemInfo implements Parcelable {
    public String taskAffinity;
    public String permission;
    public String processName;
    public String className;
    public int descriptionRes;
    public int theme;
    public String manageSpaceActivityName;
    public String backupAgentName;
    public int fullBackupContent = 0;
    public int uiOptions = 0;
    public static final int FLAG_SYSTEM = 1 << 0;
    public static final int FLAG_DEBUGGABLE = 1 << 1;
    public static final int FLAG_HAS_CODE = 1 << 2;
    public static final int FLAG_PERSISTENT = 1 << 3;
    public static final int FLAG_FACTORY_TEST = 1 << 4;
    public static final int FLAG_ALLOW_TASK_REPARENTING = 1 << 5;
    public static final int FLAG_ALLOW_CLEAR_USER_DATA = 1 << 6;
    public static final int FLAG_UPDATED_SYSTEM_APP = 1 << 7;
    public static final int FLAG_TEST_ONLY = 1 << 8;
    public static final int FLAG_SUPPORTS_SMALL_SCREENS = 1 << 9;
    public static final int FLAG_SUPPORTS_NORMAL_SCREENS = 1 << 10;
    public static final int FLAG_SUPPORTS_LARGE_SCREENS = 1 << 11;
    public static final int FLAG_RESIZEABLE_FOR_SCREENS = 1 << 12;
    public static final int FLAG_SUPPORTS_SCREEN_DENSITIES = 1 << 13;
    public static final int FLAG_VM_SAFE_MODE = 1 << 14;
    public static final int FLAG_ALLOW_BACKUP = 1 << 15;
    public static final int FLAG_KILL_AFTER_RESTORE = 1 << 16;
    public static final int FLAG_RESTORE_ANY_VERSION = 1 << 17;
    public static final int FLAG_EXTERNAL_STORAGE = 1 << 18;
    public static final int FLAG_SUPPORTS_XLARGE_SCREENS = 1 << 19;
    public static final int FLAG_LARGE_HEAP = 1 << 20;
    public static final int FLAG_STOPPED = 1 << 21;
    public static final int FLAG_SUPPORTS_RTL = 1 << 22;
    public static final int FLAG_INSTALLED = 1 << 23;
    public static final int FLAG_IS_DATA_ONLY = 1 << 24;
    @Deprecated
    public static final int FLAG_IS_GAME = 1 << 25;
    public static final int FLAG_FULL_BACKUP_ONLY = 1 << 26;
    public static final int FLAG_USES_CLEARTEXT_TRAFFIC = 1 << 27;
    public static final int FLAG_EXTRACT_NATIVE_LIBS = 1 << 28;
    public static final int FLAG_HARDWARE_ACCELERATED = 1 << 29;
    public static final int FLAG_SUSPENDED = 1 << 30;
    public static final int FLAG_MULTIARCH = 1 << 31;
    public int flags = 0;
    public static final int PRIVATE_FLAG_HIDDEN = 1 << 0;
    public static final int PRIVATE_FLAG_CANT_SAVE_STATE = 1 << 1;
    public static final int PRIVATE_FLAG_FORWARD_LOCK = 1 << 2;
    public static final int PRIVATE_FLAG_PRIVILEGED = 1 << 3;
    public static final int PRIVATE_FLAG_HAS_DOMAIN_URLS = 1 << 4;
    public static final int PRIVATE_FLAG_DEFAULT_TO_DEVICE_PROTECTED_STORAGE = 1 << 5;
    public static final int PRIVATE_FLAG_DIRECT_BOOT_AWARE = 1 << 6;
    public static final int PRIVATE_FLAG_INSTANT = 1 << 7;
    public static final int PRIVATE_FLAG_PARTIALLY_DIRECT_BOOT_AWARE = 1 << 8;
    public static final int PRIVATE_FLAG_REQUIRED_FOR_SYSTEM_USER = 1 << 9;
    public static final int PRIVATE_FLAG_ACTIVITIES_RESIZE_MODE_RESIZEABLE = 1 << 10;
    public static final int PRIVATE_FLAG_ACTIVITIES_RESIZE_MODE_UNRESIZEABLE = 1 << 11;
    public static final int PRIVATE_FLAG_ACTIVITIES_RESIZE_MODE_RESIZEABLE_VIA_SDK_VERSION = 1 << 12;
    public static final int PRIVATE_FLAG_BACKUP_IN_FOREGROUND = 1 << 13;
    public static final int PRIVATE_FLAG_STATIC_SHARED_LIBRARY = 1 << 14;
    public static final int PRIVATE_FLAG_ISOLATED_SPLIT_LOADING = 1 << 15;
    public static final int PRIVATE_FLAG_VIRTUAL_PRELOAD = 1 << 16;
    public static final int PRIVATE_FLAG_OEM = 1 << 17;
    public static final int PRIVATE_FLAG_VENDOR = 1 << 18;
    public static final int PRIVATE_FLAG_PRODUCT = 1 << 19;
    public static final int PRIVATE_FLAG_SIGNED_WITH_PLATFORM_KEY = 1 << 20;
    public static final int PRIVATE_FLAG_USES_NON_SDK_API = 1 << 22;

    public @interface ApplicationInfoPrivateFlags {
    }

    public @ApplicationInfoPrivateFlags int privateFlags;
    public static final String METADATA_PRELOADED_FONTS = "preloaded_fonts";
    public int requiresSmallestWidthDp = 0;
    public int compatibleWidthLimitDp = 0;
    public int largestWidthLimitDp = 0;
    public float maxAspectRatio;
    @Deprecated
    public String volumeUuid;
    public String scanSourceDir;
    public String scanPublicSourceDir;
    public String sourceDir;
    public String publicSourceDir;
    public String[] splitNames;
    public String[] splitSourceDirs;
    public String[] splitPublicSourceDirs;
    public String[] resourceDirs;
    public String seInfo;
    public String seInfoUser;
    public String[] sharedLibraryFiles;
    public String dataDir;
    public String deviceProtectedDataDir;
    public String credentialProtectedDataDir;
    public String nativeLibraryDir;
    public String secondaryNativeLibraryDir;
    public String nativeLibraryRootDir;
    public boolean nativeLibraryRootRequiresIsa;
    public String primaryCpuAbi;
    public String secondaryCpuAbi;
    public int uid;
    public int minSdkVersion;
    public int targetSdkVersion;
    public long longVersionCode;
    @Deprecated
    public int versionCode;
    public int compileSdkVersion;
    public String compileSdkVersionCodename;
    public boolean enabled = true;
    public int enabledSetting = PackageManager.COMPONENT_ENABLED_STATE_DEFAULT;
    public int installLocation = PackageInfo.INSTALL_LOCATION_UNSPECIFIED;
    public int networkSecurityConfigRes;
    public int targetSandboxVersion;
    public String appComponentFactory;
    public @Category int category = CATEGORY_UNDEFINED;

    public @interface Category {
    }

    public static final int CATEGORY_UNDEFINED = -1;
    public static final int CATEGORY_GAME = 0;
    public static final int CATEGORY_AUDIO = 1;
    public static final int CATEGORY_VIDEO = 2;
    public static final int CATEGORY_IMAGE = 3;
    public static final int CATEGORY_SOCIAL = 4;
    public static final int CATEGORY_NEWS = 5;
    public static final int CATEGORY_MAPS = 6;
    public static final int CATEGORY_PRODUCTIVITY = 7;

    public String classLoaderName;
    public String[] splitClassLoaderNames;
    public boolean hiddenUntilInstalled;
    public static final int HIDDEN_API_ENFORCEMENT_DEFAULT = -1;
    public static final int HIDDEN_API_ENFORCEMENT_DISABLED = 0;
    public static final int HIDDEN_API_ENFORCEMENT_JUST_WARN = 1;
    public static final int HIDDEN_API_ENFORCEMENT_ENABLED = 2;
    public static final Parcelable.Creator<ApplicationInfo> CREATOR = new Parcelable.Creator<ApplicationInfo>() {
        public ApplicationInfo createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public ApplicationInfo[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }
}