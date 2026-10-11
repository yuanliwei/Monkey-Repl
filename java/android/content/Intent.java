
package android.content;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

import android.annotation.BroadcastBehavior;
import android.annotation.IntDef;
import android.annotation.SdkConstant;
import android.annotation.SdkConstant.SdkConstantType;
import android.annotation.SystemApi;
import android.annotation.UnsupportedAppUsage;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Intent implements Parcelable, Cloneable {

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_MAIN = "android.intent.action.MAIN";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_VIEW = "android.intent.action.VIEW";

	public static final String EXTRA_FROM_STORAGE = "android.intent.extra.FROM_STORAGE";

	public static final String ACTION_DEFAULT = ACTION_VIEW;

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_QUICK_VIEW = "android.intent.action.QUICK_VIEW";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_ATTACH_DATA = "android.intent.action.ATTACH_DATA";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_EDIT = "android.intent.action.EDIT";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_INSERT_OR_EDIT = "android.intent.action.INSERT_OR_EDIT";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_PICK = "android.intent.action.PICK";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_CREATE_SHORTCUT = "android.intent.action.CREATE_SHORTCUT";

	@Deprecated
	public static final String EXTRA_SHORTCUT_INTENT = "android.intent.extra.shortcut.INTENT";
	@Deprecated
	public static final String EXTRA_SHORTCUT_NAME = "android.intent.extra.shortcut.NAME";
	@Deprecated
	public static final String EXTRA_SHORTCUT_ICON = "android.intent.extra.shortcut.ICON";
	@Deprecated
	public static final String EXTRA_SHORTCUT_ICON_RESOURCE = "android.intent.extra.shortcut.ICON_RESOURCE";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_APPLICATION_PREFERENCES = "android.intent.action.APPLICATION_PREFERENCES";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_SHOW_APP_INFO = "android.intent.action.SHOW_APP_INFO";

	public static class ShortcutIconResource implements Parcelable {
		public String packageName;

		public String resourceName;

		public static final Parcelable.Creator<ShortcutIconResource> CREATOR = new Parcelable.Creator<ShortcutIconResource>() {

			public ShortcutIconResource createFromParcel(Parcel source) {
				throw new RuntimeException("Stub!");
			}

			public ShortcutIconResource[] newArray(int size) {
				throw new RuntimeException("Stub!");
			}
		};

		public int describeContents() {
			throw new RuntimeException("Stub!");
		}

		public void writeToParcel(Parcel dest, int flags) {
			throw new RuntimeException("Stub!");
		}

		@Override
		public String toString() {
			throw new RuntimeException("Stub!");
		}
	}

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_CHOOSER = "android.intent.action.CHOOSER";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_DIAL = "android.intent.action.DIAL";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_CALL = "android.intent.action.CALL";
	@SystemApi
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_CALL_EMERGENCY = "android.intent.action.CALL_EMERGENCY";
	@SystemApi
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_CALL_PRIVILEGED = "android.intent.action.CALL_PRIVILEGED";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_CARRIER_SETUP = "android.intent.action.CARRIER_SETUP";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_SENDTO = "android.intent.action.SENDTO";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_SEND = "android.intent.action.SEND";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_SEND_MULTIPLE = "android.intent.action.SEND_MULTIPLE";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_ANSWER = "android.intent.action.ANSWER";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_INSERT = "android.intent.action.INSERT";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_PASTE = "android.intent.action.PASTE";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_DELETE = "android.intent.action.DELETE";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_RUN = "android.intent.action.RUN";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_SYNC = "android.intent.action.SYNC";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_PICK_ACTIVITY = "android.intent.action.PICK_ACTIVITY";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_SEARCH = "android.intent.action.SEARCH";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_SYSTEM_TUTORIAL = "android.intent.action.SYSTEM_TUTORIAL";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_WEB_SEARCH = "android.intent.action.WEB_SEARCH";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_ASSIST = "android.intent.action.ASSIST";

	@SystemApi
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_VOICE_ASSIST = "android.intent.action.VOICE_ASSIST";

	public static final String EXTRA_ASSIST_PACKAGE = "android.intent.extra.ASSIST_PACKAGE";

	public static final String EXTRA_ASSIST_UID = "android.intent.extra.ASSIST_UID";

	public static final String EXTRA_ASSIST_CONTEXT = "android.intent.extra.ASSIST_CONTEXT";

	public static final String EXTRA_ASSIST_INPUT_HINT_KEYBOARD = "android.intent.extra.ASSIST_INPUT_HINT_KEYBOARD";

	public static final String EXTRA_ASSIST_INPUT_DEVICE_ID = "android.intent.extra.ASSIST_INPUT_DEVICE_ID";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_ALL_APPS = "android.intent.action.ALL_APPS";
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_SET_WALLPAPER = "android.intent.action.SET_WALLPAPER";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_BUG_REPORT = "android.intent.action.BUG_REPORT";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_FACTORY_TEST = "android.intent.action.FACTORY_TEST";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_CALL_BUTTON = "android.intent.action.CALL_BUTTON";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_VOICE_COMMAND = "android.intent.action.VOICE_COMMAND";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_SEARCH_LONG_PRESS = "android.intent.action.SEARCH_LONG_PRESS";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_APP_ERROR = "android.intent.action.APP_ERROR";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_POWER_USAGE_SUMMARY = "android.intent.action.POWER_USAGE_SUMMARY";

	@Deprecated
	@SystemApi
	public static final String ACTION_DEVICE_INITIALIZATION_WIZARD = "android.intent.action.DEVICE_INITIALIZATION_WIZARD";

	@SystemApi
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_UPGRADE_SETUP = "android.intent.action.UPGRADE_SETUP";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_SHOW_KEYBOARD_SHORTCUTS = "com.android.intent.action.SHOW_KEYBOARD_SHORTCUTS";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_DISMISS_KEYBOARD_SHORTCUTS = "com.android.intent.action.DISMISS_KEYBOARD_SHORTCUTS";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_MANAGE_NETWORK_USAGE = "android.intent.action.MANAGE_NETWORK_USAGE";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_INSTALL_PACKAGE = "android.intent.action.INSTALL_PACKAGE";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_INSTALL_FAILURE = "android.intent.action.INSTALL_FAILURE";

	@SystemApi
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_INSTALL_INSTANT_APP_PACKAGE = "android.intent.action.INSTALL_INSTANT_APP_PACKAGE";

	@SystemApi
	@SdkConstant(SdkConstantType.SERVICE_ACTION)
	public static final String ACTION_RESOLVE_INSTANT_APP_PACKAGE = "android.intent.action.RESOLVE_INSTANT_APP_PACKAGE";

	@SystemApi
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_INSTANT_APP_RESOLVER_SETTINGS = "android.intent.action.INSTANT_APP_RESOLVER_SETTINGS";

	public static final String EXTRA_INSTALLER_PACKAGE_NAME = "android.intent.extra.INSTALLER_PACKAGE_NAME";

	public static final String EXTRA_NOT_UNKNOWN_SOURCE = "android.intent.extra.NOT_UNKNOWN_SOURCE";

	public static final String EXTRA_ORIGINATING_URI = "android.intent.extra.ORIGINATING_URI";

	public static final String EXTRA_REFERRER = "android.intent.extra.REFERRER";

	public static final String EXTRA_REFERRER_NAME = "android.intent.extra.REFERRER_NAME";

	@SystemApi
	public static final String EXTRA_ORIGINATING_UID = "android.intent.extra.ORIGINATING_UID";

	@Deprecated
	public static final String EXTRA_ALLOW_REPLACE = "android.intent.extra.ALLOW_REPLACE";

	public static final String EXTRA_RETURN_RESULT = "android.intent.extra.RETURN_RESULT";

	public static final String EXTRA_INSTALL_RESULT = "android.intent.extra.INSTALL_RESULT";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_UNINSTALL_PACKAGE = "android.intent.action.UNINSTALL_PACKAGE";

	public static final String EXTRA_UNINSTALL_ALL_USERS = "android.intent.extra.UNINSTALL_ALL_USERS";

	public static final String METADATA_SETUP_VERSION = "android.SETUP_VERSION";

	@SystemApi
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_MANAGE_APP_PERMISSIONS = "android.intent.action.MANAGE_APP_PERMISSIONS";

	@SystemApi
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_MANAGE_PERMISSIONS = "android.intent.action.MANAGE_PERMISSIONS";

	@SystemApi
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_REVIEW_PERMISSIONS = "android.intent.action.REVIEW_PERMISSIONS";

	@SystemApi
	public static final String EXTRA_REMOTE_CALLBACK = "android.intent.extra.REMOTE_CALLBACK";

	public static final String EXTRA_PACKAGE_NAME = "android.intent.extra.PACKAGE_NAME";

	public static final String EXTRA_SUSPENDED_PACKAGE_EXTRAS = "android.intent.extra.SUSPENDED_PACKAGE_EXTRAS";

	public static final String EXTRA_SPLIT_NAME = "android.intent.extra.SPLIT_NAME";

	public static final String EXTRA_COMPONENT_NAME = "android.intent.extra.COMPONENT_NAME";

	@SystemApi
	public static final String EXTRA_RESULT_NEEDED = "android.intent.extra.RESULT_NEEDED";

	public static final String EXTRA_LAUNCHER_EXTRAS = "android.intent.extra.LAUNCHER_EXTRAS";

	@SystemApi
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_MANAGE_PERMISSION_APPS = "android.intent.action.MANAGE_PERMISSION_APPS";

	@SystemApi
	public static final String EXTRA_PERMISSION_NAME = "android.intent.extra.PERMISSION_NAME";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_SCREEN_OFF = "android.intent.action.SCREEN_OFF";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_SCREEN_ON = "android.intent.action.SCREEN_ON";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_DREAMING_STOPPED = "android.intent.action.DREAMING_STOPPED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_DREAMING_STARTED = "android.intent.action.DREAMING_STARTED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_USER_PRESENT = "android.intent.action.USER_PRESENT";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_TIME_TICK = "android.intent.action.TIME_TICK";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_TIME_CHANGED = "android.intent.action.TIME_SET";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_DATE_CHANGED = "android.intent.action.DATE_CHANGED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_TIMEZONE_CHANGED = "android.intent.action.TIMEZONE_CHANGED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_CLEAR_DNS_CACHE = "android.intent.action.CLEAR_DNS_CACHE";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	@UnsupportedAppUsage
	public static final String ACTION_ALARM_CHANGED = "android.intent.action.ALARM_CHANGED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_LOCKED_BOOT_COMPLETED = "android.intent.action.LOCKED_BOOT_COMPLETED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	@BroadcastBehavior(includeBackground = true)
	public static final String ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_CLOSE_SYSTEM_DIALOGS = "android.intent.action.CLOSE_SYSTEM_DIALOGS";
	@Deprecated
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGE_INSTALL = "android.intent.action.PACKAGE_INSTALL";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGE_ADDED = "android.intent.action.PACKAGE_ADDED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGE_REPLACED = "android.intent.action.PACKAGE_REPLACED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MY_PACKAGE_REPLACED = "android.intent.action.MY_PACKAGE_REPLACED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGE_REMOVED = "android.intent.action.PACKAGE_REMOVED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGE_FULLY_REMOVED = "android.intent.action.PACKAGE_FULLY_REMOVED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGE_CHANGED = "android.intent.action.PACKAGE_CHANGED";
	@SystemApi
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_QUERY_PACKAGE_RESTART = "android.intent.action.QUERY_PACKAGE_RESTART";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGE_RESTARTED = "android.intent.action.PACKAGE_RESTARTED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGE_DATA_CLEARED = "android.intent.action.PACKAGE_DATA_CLEARED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGES_SUSPENDED = "android.intent.action.PACKAGES_SUSPENDED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGES_UNSUSPENDED = "android.intent.action.PACKAGES_UNSUSPENDED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MY_PACKAGE_SUSPENDED = "android.intent.action.MY_PACKAGE_SUSPENDED";

	@SystemApi
	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_SHOW_SUSPENDED_APP_DETAILS = "android.intent.action.SHOW_SUSPENDED_APP_DETAILS";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MY_PACKAGE_UNSUSPENDED = "android.intent.action.MY_PACKAGE_UNSUSPENDED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_UID_REMOVED = "android.intent.action.UID_REMOVED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGE_FIRST_LAUNCH = "android.intent.action.PACKAGE_FIRST_LAUNCH";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGE_NEEDS_VERIFICATION = "android.intent.action.PACKAGE_NEEDS_VERIFICATION";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PACKAGE_VERIFIED = "android.intent.action.PACKAGE_VERIFIED";

	@SystemApi
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_INTENT_FILTER_NEEDS_VERIFICATION = "android.intent.action.INTENT_FILTER_NEEDS_VERIFICATION";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PREFERRED_ACTIVITY_CHANGED = "android.intent.action.ACTION_PREFERRED_ACTIVITY_CHANGED";

	@Deprecated
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_WALLPAPER_CHANGED = "android.intent.action.WALLPAPER_CHANGED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_CONFIGURATION_CHANGED = "android.intent.action.CONFIGURATION_CHANGED";

	@SystemApi
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_SPLIT_CONFIGURATION_CHANGED = "android.intent.action.SPLIT_CONFIGURATION_CHANGED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_LOCALE_CHANGED = "android.intent.action.LOCALE_CHANGED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";

	@SystemApi
	public static final String ACTION_BATTERY_LEVEL_CHANGED = "android.intent.action.BATTERY_LEVEL_CHANGED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_BATTERY_LOW = "android.intent.action.BATTERY_LOW";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_BATTERY_OKAY = "android.intent.action.BATTERY_OKAY";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";
	public static final String ACTION_REQUEST_SHUTDOWN = "com.android.internal.intent.action.REQUEST_SHUTDOWN";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	@Deprecated
	public static final String ACTION_DEVICE_STORAGE_LOW = "android.intent.action.DEVICE_STORAGE_LOW";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	@Deprecated
	public static final String ACTION_DEVICE_STORAGE_OK = "android.intent.action.DEVICE_STORAGE_OK";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	@Deprecated
	public static final String ACTION_DEVICE_STORAGE_FULL = "android.intent.action.DEVICE_STORAGE_FULL";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	@Deprecated
	public static final String ACTION_DEVICE_STORAGE_NOT_FULL = "android.intent.action.DEVICE_STORAGE_NOT_FULL";
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MANAGE_PACKAGE_STORAGE = "android.intent.action.MANAGE_PACKAGE_STORAGE";
	@Deprecated
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_UMS_CONNECTED = "android.intent.action.UMS_CONNECTED";

	@Deprecated
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_UMS_DISCONNECTED = "android.intent.action.UMS_DISCONNECTED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_REMOVED = "android.intent.action.MEDIA_REMOVED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_UNMOUNTED = "android.intent.action.MEDIA_UNMOUNTED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_CHECKING = "android.intent.action.MEDIA_CHECKING";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_NOFS = "android.intent.action.MEDIA_NOFS";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_MOUNTED = "android.intent.action.MEDIA_MOUNTED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_SHARED = "android.intent.action.MEDIA_SHARED";

	public static final String ACTION_MEDIA_UNSHARED = "android.intent.action.MEDIA_UNSHARED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_BAD_REMOVAL = "android.intent.action.MEDIA_BAD_REMOVAL";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_UNMOUNTABLE = "android.intent.action.MEDIA_UNMOUNTABLE";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_EJECT = "android.intent.action.MEDIA_EJECT";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_SCANNER_STARTED = "android.intent.action.MEDIA_SCANNER_STARTED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_SCANNER_FINISHED = "android.intent.action.MEDIA_SCANNER_FINISHED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_SCANNER_SCAN_FILE = "android.intent.action.MEDIA_SCANNER_SCAN_FILE";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MEDIA_BUTTON = "android.intent.action.MEDIA_BUTTON";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_CAMERA_BUTTON = "android.intent.action.CAMERA_BUTTON";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_GTALK_SERVICE_CONNECTED = "android.intent.action.GTALK_CONNECTED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_GTALK_SERVICE_DISCONNECTED = "android.intent.action.GTALK_DISCONNECTED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_INPUT_METHOD_CHANGED = "android.intent.action.INPUT_METHOD_CHANGED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_AIRPLANE_MODE_CHANGED = "android.intent.action.AIRPLANE_MODE";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_PROVIDER_CHANGED = "android.intent.action.PROVIDER_CHANGED";

	public static final String ACTION_ADVANCED_SETTINGS_CHANGED = "android.intent.action.ADVANCED_SETTINGS";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_APPLICATION_RESTRICTIONS_CHANGED = "android.intent.action.APPLICATION_RESTRICTIONS_CHANGED";

	@Deprecated
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_NEW_OUTGOING_CALL = "android.intent.action.NEW_OUTGOING_CALL";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_REBOOT = "android.intent.action.REBOOT";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_DOCK_EVENT = "android.intent.action.DOCK_EVENT";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_IDLE_MAINTENANCE_START = "android.intent.action.ACTION_IDLE_MAINTENANCE_START";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_IDLE_MAINTENANCE_END = "android.intent.action.ACTION_IDLE_MAINTENANCE_END";

	public static final String ACTION_REMOTE_INTENT = "com.google.android.c2dm.intent.RECEIVE";

	@SystemApi
	public static final String ACTION_PRE_BOOT_COMPLETED = "android.intent.action.PRE_BOOT_COMPLETED";

	public static final String ACTION_GET_RESTRICTION_ENTRIES = "android.intent.action.GET_RESTRICTION_ENTRIES";

	public static final String ACTION_USER_INITIALIZE = "android.intent.action.USER_INITIALIZE";

	public static final String ACTION_USER_FOREGROUND = "android.intent.action.USER_FOREGROUND";

	public static final String ACTION_USER_BACKGROUND = "android.intent.action.USER_BACKGROUND";

	public static final String ACTION_USER_ADDED = "android.intent.action.USER_ADDED";

	public static final String ACTION_USER_STARTED = "android.intent.action.USER_STARTED";

	public static final String ACTION_USER_STARTING = "android.intent.action.USER_STARTING";

	public static final String ACTION_USER_STOPPING = "android.intent.action.USER_STOPPING";

	public static final String ACTION_USER_STOPPED = "android.intent.action.USER_STOPPED";

	@SystemApi
	public static final String ACTION_USER_REMOVED = "android.intent.action.USER_REMOVED";

	@UnsupportedAppUsage
	public static final String ACTION_USER_SWITCHED = "android.intent.action.USER_SWITCHED";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_USER_UNLOCKED = "android.intent.action.USER_UNLOCKED";

	public static final String ACTION_USER_INFO_CHANGED = "android.intent.action.USER_INFO_CHANGED";

	public static final String ACTION_MANAGED_PROFILE_ADDED = "android.intent.action.MANAGED_PROFILE_ADDED";

	public static final String ACTION_MANAGED_PROFILE_REMOVED = "android.intent.action.MANAGED_PROFILE_REMOVED";

	public static final String ACTION_MANAGED_PROFILE_UNLOCKED = "android.intent.action.MANAGED_PROFILE_UNLOCKED";

	public static final String ACTION_MANAGED_PROFILE_AVAILABLE = "android.intent.action.MANAGED_PROFILE_AVAILABLE";

	public static final String ACTION_MANAGED_PROFILE_UNAVAILABLE = "android.intent.action.MANAGED_PROFILE_UNAVAILABLE";

	public static final String ACTION_DEVICE_LOCKED_CHANGED = "android.intent.action.DEVICE_LOCKED_CHANGED";

	public static final String ACTION_QUICK_CLOCK = "android.intent.action.QUICK_CLOCK";

	public static final String ACTION_SHOW_BRIGHTNESS_DIALOG = "com.android.intent.action.SHOW_BRIGHTNESS_DIALOG";

	@SystemApi
	public static final String ACTION_GLOBAL_BUTTON = "android.intent.action.GLOBAL_BUTTON";

	public static final String ACTION_MEDIA_RESOURCE_GRANTED = "android.intent.action.MEDIA_RESOURCE_GRANTED";

	public static final String ACTION_OVERLAY_CHANGED = "android.intent.action.OVERLAY_CHANGED";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_OPEN_DOCUMENT = "android.intent.action.OPEN_DOCUMENT";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_CREATE_DOCUMENT = "android.intent.action.CREATE_DOCUMENT";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_OPEN_DOCUMENT_TREE = "android.intent.action.OPEN_DOCUMENT_TREE";

	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_DYNAMIC_SENSOR_CHANGED = "android.intent.action.DYNAMIC_SENSOR_CHANGED";

	@Deprecated
	@SystemApi
	public static final String ACTION_MASTER_CLEAR = "android.intent.action.MASTER_CLEAR";

	@SystemApi
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_MASTER_CLEAR_NOTIFICATION = "android.intent.action.MASTER_CLEAR_NOTIFICATION";

	@Deprecated
	public static final String EXTRA_FORCE_MASTER_CLEAR = "android.intent.extra.FORCE_MASTER_CLEAR";

	@SystemApi
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_FACTORY_RESET = "android.intent.action.FACTORY_RESET";

	@SystemApi
	public static final String EXTRA_FORCE_FACTORY_RESET = "android.intent.extra.FORCE_FACTORY_RESET";

	public static final String ACTION_SETTING_RESTORED = "android.os.action.SETTING_RESTORED";

	public static final String EXTRA_SETTING_NAME = "setting_name";
	public static final String EXTRA_SETTING_PREVIOUS_VALUE = "previous_value";
	public static final String EXTRA_SETTING_NEW_VALUE = "new_value";
	public static final String EXTRA_SETTING_RESTORED_FROM_SDK_INT = "restored_from_sdk_int";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_PROCESS_TEXT = "android.intent.action.PROCESS_TEXT";

	@Deprecated
	@SystemApi
	@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";

	@Deprecated
	@SystemApi
	@SdkConstant(SdkConstant.SdkConstantType.BROADCAST_INTENT_ACTION)
	public static final String ACTION_SERVICE_STATE = "android.intent.action.SERVICE_STATE";

	@Deprecated
	@SystemApi
	public static final String EXTRA_VOICE_REG_STATE = "voiceRegState";

	@Deprecated
	@SystemApi
	public static final String EXTRA_DATA_REG_STATE = "dataRegState";

	@Deprecated
	@SystemApi
	public static final String EXTRA_VOICE_ROAMING_TYPE = "voiceRoamingType";

	@Deprecated
	@SystemApi
	public static final String EXTRA_DATA_ROAMING_TYPE = "dataRoamingType";

	@Deprecated
	@SystemApi
	public static final String EXTRA_OPERATOR_ALPHA_LONG = "operator-alpha-long";

	@Deprecated
	@SystemApi
	public static final String EXTRA_OPERATOR_ALPHA_SHORT = "operator-alpha-short";

	@Deprecated
	@SystemApi
	public static final String EXTRA_OPERATOR_NUMERIC = "operator-numeric";

	@Deprecated
	@SystemApi
	public static final String EXTRA_DATA_OPERATOR_ALPHA_LONG = "data-operator-alpha-long";

	@Deprecated
	@SystemApi
	public static final String EXTRA_DATA_OPERATOR_ALPHA_SHORT = "data-operator-alpha-short";

	@Deprecated
	@SystemApi
	public static final String EXTRA_DATA_OPERATOR_NUMERIC = "data-operator-numeric";

	@Deprecated
	@SystemApi
	public static final String EXTRA_MANUAL = "manual";

	@Deprecated
	@SystemApi
	public static final String EXTRA_VOICE_RADIO_TECH = "radioTechnology";

	@Deprecated
	@SystemApi
	public static final String EXTRA_DATA_RADIO_TECH = "dataRadioTechnology";

	@Deprecated
	@SystemApi
	public static final String EXTRA_CSS_INDICATOR = "cssIndicator";

	@Deprecated
	@SystemApi
	public static final String EXTRA_NETWORK_ID = "networkId";

	@Deprecated
	@SystemApi
	public static final String EXTRA_SYSTEM_ID = "systemId";

	@Deprecated
	@SystemApi
	public static final String EXTRA_CDMA_ROAMING_INDICATOR = "cdmaRoamingIndicator";

	@Deprecated
	@SystemApi
	public static final String EXTRA_CDMA_DEFAULT_ROAMING_INDICATOR = "cdmaDefaultRoamingIndicator";

	@Deprecated
	@SystemApi
	public static final String EXTRA_EMERGENCY_ONLY = "emergencyOnly";

	@Deprecated
	@SystemApi
	public static final String EXTRA_IS_DATA_ROAMING_FROM_REGISTRATION = "isDataRoamingFromRegistration";

	@Deprecated
	@SystemApi
	public static final String EXTRA_IS_USING_CARRIER_AGGREGATION = "isUsingCarrierAggregation";

	@Deprecated
	@SystemApi
	public static final String EXTRA_LTE_EARFCN_RSRP_BOOST = "LteEarfcnRsrpBoost";

	public static final String EXTRA_SERVICE_STATE = "android.intent.extra.SERVICE_STATE";

	public static final String EXTRA_PROCESS_TEXT = "android.intent.extra.PROCESS_TEXT";
	public static final String EXTRA_PROCESS_TEXT_READONLY = "android.intent.extra.PROCESS_TEXT_READONLY";

	@SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
	public static final String ACTION_THERMAL_EVENT = "android.intent.action.THERMAL_EVENT";

	public static final String EXTRA_THERMAL_STATE = "android.intent.extra.THERMAL_STATE";

	public static final int EXTRA_THERMAL_STATE_NORMAL = 0;

	public static final int EXTRA_THERMAL_STATE_WARNING = 1;

	public static final int EXTRA_THERMAL_STATE_EXCEEDED = 2;

	public static final String ACTION_DOCK_IDLE = "android.intent.action.DOCK_IDLE";

	public static final String ACTION_DOCK_ACTIVE = "android.intent.action.DOCK_ACTIVE";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_DEFAULT = "android.intent.category.DEFAULT";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_BROWSABLE = "android.intent.category.BROWSABLE";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_VOICE = "android.intent.category.VOICE";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_ALTERNATIVE = "android.intent.category.ALTERNATIVE";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_SELECTED_ALTERNATIVE = "android.intent.category.SELECTED_ALTERNATIVE";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_TAB = "android.intent.category.TAB";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_LAUNCHER = "android.intent.category.LAUNCHER";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_CAR_LAUNCHER = "android.intent.category.CAR_LAUNCHER";
	@SystemApi
	public static final String CATEGORY_LEANBACK_SETTINGS = "android.intent.category.LEANBACK_SETTINGS";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_INFO = "android.intent.category.INFO";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_HOME = "android.intent.category.HOME";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_HOME_MAIN = "android.intent.category.HOME_MAIN";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_SETUP_WIZARD = "android.intent.category.SETUP_WIZARD";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_LAUNCHER_APP = "android.intent.category.LAUNCHER_APP";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_PREFERENCE = "android.intent.category.PREFERENCE";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_DEVELOPMENT_PREFERENCE = "android.intent.category.DEVELOPMENT_PREFERENCE";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_EMBED = "android.intent.category.EMBED";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_APP_MARKET = "android.intent.category.APP_MARKET";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_MONKEY = "android.intent.category.MONKEY";
	public static final String CATEGORY_TEST = "android.intent.category.TEST";
	public static final String CATEGORY_UNIT_TEST = "android.intent.category.UNIT_TEST";
	public static final String CATEGORY_SAMPLE_CODE = "android.intent.category.SAMPLE_CODE";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_OPENABLE = "android.intent.category.OPENABLE";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_TYPED_OPENABLE = "android.intent.category.TYPED_OPENABLE";

	public static final String CATEGORY_FRAMEWORK_INSTRUMENTATION_TEST = "android.intent.category.FRAMEWORK_INSTRUMENTATION_TEST";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_CAR_DOCK = "android.intent.category.CAR_DOCK";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_DESK_DOCK = "android.intent.category.DESK_DOCK";
	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_LE_DESK_DOCK = "android.intent.category.LE_DESK_DOCK";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_HE_DESK_DOCK = "android.intent.category.HE_DESK_DOCK";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_CAR_MODE = "android.intent.category.CAR_MODE";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_VR_HOME = "android.intent.category.VR_HOME";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_APP_BROWSER = "android.intent.category.APP_BROWSER";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_APP_CALCULATOR = "android.intent.category.APP_CALCULATOR";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_APP_CALENDAR = "android.intent.category.APP_CALENDAR";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_APP_CONTACTS = "android.intent.category.APP_CONTACTS";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_APP_EMAIL = "android.intent.category.APP_EMAIL";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_APP_GALLERY = "android.intent.category.APP_GALLERY";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_APP_MAPS = "android.intent.category.APP_MAPS";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_APP_MESSAGING = "android.intent.category.APP_MESSAGING";

	@SdkConstant(SdkConstantType.INTENT_CATEGORY)
	public static final String CATEGORY_APP_MUSIC = "android.intent.category.APP_MUSIC";

	public static final String EXTRA_TEMPLATE = "android.intent.extra.TEMPLATE";

	public static final String EXTRA_TEXT = "android.intent.extra.TEXT";

	public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";

	public static final String EXTRA_STREAM = "android.intent.extra.STREAM";

	public static final String EXTRA_EMAIL = "android.intent.extra.EMAIL";

	public static final String EXTRA_CC = "android.intent.extra.CC";

	public static final String EXTRA_BCC = "android.intent.extra.BCC";

	public static final String EXTRA_SUBJECT = "android.intent.extra.SUBJECT";

	public static final String EXTRA_INTENT = "android.intent.extra.INTENT";

	public static final String EXTRA_USER_ID = "android.intent.extra.USER_ID";

	public static final String EXTRA_TASK_ID = "android.intent.extra.TASK_ID";

	public static final String EXTRA_ALTERNATE_INTENTS = "android.intent.extra.ALTERNATE_INTENTS";

	public static final String EXTRA_EXCLUDE_COMPONENTS = "android.intent.extra.EXCLUDE_COMPONENTS";

	public static final String EXTRA_CHOOSER_TARGETS = "android.intent.extra.CHOOSER_TARGETS";

	public static final String EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER = "android.intent.extra.CHOOSER_REFINEMENT_INTENT_SENDER";

	public static final String EXTRA_CONTENT_ANNOTATIONS = "android.intent.extra.CONTENT_ANNOTATIONS";

	public static final String EXTRA_RESULT_RECEIVER = "android.intent.extra.RESULT_RECEIVER";

	public static final String EXTRA_TITLE = "android.intent.extra.TITLE";

	public static final String EXTRA_INITIAL_INTENTS = "android.intent.extra.INITIAL_INTENTS";

	@Deprecated
	public static final String EXTRA_EPHEMERAL_SUCCESS = "android.intent.extra.EPHEMERAL_SUCCESS";

	@SystemApi
	public static final String EXTRA_INSTANT_APP_SUCCESS = "android.intent.extra.INSTANT_APP_SUCCESS";

	@Deprecated
	public static final String EXTRA_EPHEMERAL_FAILURE = "android.intent.extra.EPHEMERAL_FAILURE";

	@SystemApi
	public static final String EXTRA_INSTANT_APP_FAILURE = "android.intent.extra.INSTANT_APP_FAILURE";

	@Deprecated
	public static final String EXTRA_EPHEMERAL_HOSTNAME = "android.intent.extra.EPHEMERAL_HOSTNAME";

	@SystemApi
	public static final String EXTRA_INSTANT_APP_HOSTNAME = "android.intent.extra.INSTANT_APP_HOSTNAME";

	@Deprecated
	public static final String EXTRA_EPHEMERAL_TOKEN = "android.intent.extra.EPHEMERAL_TOKEN";

	@SystemApi
	public static final String EXTRA_INSTANT_APP_TOKEN = "android.intent.extra.INSTANT_APP_TOKEN";

	@SystemApi
	public static final String EXTRA_INSTANT_APP_ACTION = "android.intent.extra.INSTANT_APP_ACTION";

	@SystemApi
	public static final String EXTRA_INSTANT_APP_BUNDLES = "android.intent.extra.INSTANT_APP_BUNDLES";

	@SystemApi
	public static final String EXTRA_INSTANT_APP_EXTRAS = "android.intent.extra.INSTANT_APP_EXTRAS";

	@SystemApi
	public static final String EXTRA_UNKNOWN_INSTANT_APP = "android.intent.extra.UNKNOWN_INSTANT_APP";

	@Deprecated
	public static final String EXTRA_VERSION_CODE = "android.intent.extra.VERSION_CODE";

	@SystemApi
	public static final String EXTRA_LONG_VERSION_CODE = "android.intent.extra.LONG_VERSION_CODE";

	@SystemApi
	public static final String EXTRA_CALLING_PACKAGE = "android.intent.extra.CALLING_PACKAGE";

	@SystemApi
	public static final String EXTRA_VERIFICATION_BUNDLE = "android.intent.extra.VERIFICATION_BUNDLE";

	public static final String EXTRA_REPLACEMENT_EXTRAS = "android.intent.extra.REPLACEMENT_EXTRAS";

	public static final String EXTRA_CHOSEN_COMPONENT_INTENT_SENDER = "android.intent.extra.CHOSEN_COMPONENT_INTENT_SENDER";

	public static final String EXTRA_CHOSEN_COMPONENT = "android.intent.extra.CHOSEN_COMPONENT";

	public static final String EXTRA_KEY_EVENT = "android.intent.extra.KEY_EVENT";

	public static final String EXTRA_KEY_CONFIRM = "android.intent.extra.KEY_CONFIRM";

	public static final String EXTRA_USER_REQUESTED_SHUTDOWN = "android.intent.extra.USER_REQUESTED_SHUTDOWN";

	public static final String EXTRA_DONT_KILL_APP = "android.intent.extra.DONT_KILL_APP";

	public static final String EXTRA_PHONE_NUMBER = "android.intent.extra.PHONE_NUMBER";

	public static final String EXTRA_UID = "android.intent.extra.UID";

	@SystemApi
	public static final String EXTRA_PACKAGES = "android.intent.extra.PACKAGES";

	public static final String EXTRA_DATA_REMOVED = "android.intent.extra.DATA_REMOVED";

	public static final String EXTRA_REMOVED_FOR_ALL_USERS = "android.intent.extra.REMOVED_FOR_ALL_USERS";

	public static final String EXTRA_REPLACING = "android.intent.extra.REPLACING";

	public static final String EXTRA_ALARM_COUNT = "android.intent.extra.ALARM_COUNT";

	public static final String EXTRA_DOCK_STATE = "android.intent.extra.DOCK_STATE";

	public static final int EXTRA_DOCK_STATE_UNDOCKED = 0;

	public static final int EXTRA_DOCK_STATE_DESK = 1;

	public static final int EXTRA_DOCK_STATE_CAR = 2;

	public static final int EXTRA_DOCK_STATE_LE_DESK = 3;

	public static final int EXTRA_DOCK_STATE_HE_DESK = 4;

	public static final String METADATA_DOCK_HOME = "android.dock_home";

	public static final String EXTRA_BUG_REPORT = "android.intent.extra.BUG_REPORT";

	public static final String EXTRA_REMOTE_INTENT_TOKEN = "android.intent.extra.remote_intent_token";

	@Deprecated
	public static final String EXTRA_CHANGED_COMPONENT_NAME = "android.intent.extra.changed_component_name";

	public static final String EXTRA_CHANGED_COMPONENT_NAME_LIST = "android.intent.extra.changed_component_name_list";

	public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";

	public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";

	public static final String EXTRA_CLIENT_LABEL = "android.intent.extra.client_label";

	public static final String EXTRA_CLIENT_INTENT = "android.intent.extra.client_intent";

	public static final String EXTRA_LOCAL_ONLY = "android.intent.extra.LOCAL_ONLY";

	public static final String EXTRA_ALLOW_MULTIPLE = "android.intent.extra.ALLOW_MULTIPLE";

	public static final String EXTRA_USER_HANDLE = "android.intent.extra.user_handle";

	public static final String EXTRA_USER = "android.intent.extra.USER";

	public static final String EXTRA_RESTRICTIONS_LIST = "android.intent.extra.restrictions_list";

	public static final String EXTRA_RESTRICTIONS_BUNDLE = "android.intent.extra.restrictions_bundle";

	public static final String EXTRA_RESTRICTIONS_INTENT = "android.intent.extra.restrictions_intent";

	public static final String EXTRA_MIME_TYPES = "android.intent.extra.MIME_TYPES";

	public static final String EXTRA_SHUTDOWN_USERSPACE_ONLY = "android.intent.extra.SHUTDOWN_USERSPACE_ONLY";

	public static final String EXTRA_TIME_PREF_24_HOUR_FORMAT = "android.intent.extra.TIME_PREF_24_HOUR_FORMAT";
	public static final int EXTRA_TIME_PREF_VALUE_USE_12_HOUR = 0;
	public static final int EXTRA_TIME_PREF_VALUE_USE_24_HOUR = 1;
	public static final int EXTRA_TIME_PREF_VALUE_USE_LOCALE_DEFAULT = 2;

	@SystemApi
	public static final String EXTRA_REASON = "android.intent.extra.REASON";

	public static final String EXTRA_WIPE_EXTERNAL_STORAGE = "android.intent.extra.WIPE_EXTERNAL_STORAGE";

	public static final String EXTRA_WIPE_ESIMS = "com.android.internal.intent.extra.WIPE_ESIMS";

	public static final String EXTRA_SIM_ACTIVATION_RESPONSE = "android.intent.extra.SIM_ACTIVATION_RESPONSE";

	public static final String EXTRA_INDEX = "android.intent.extra.INDEX";

	@Deprecated
	public static final String EXTRA_QUICK_VIEW_ADVANCED = "android.intent.extra.QUICK_VIEW_ADVANCED";

	public static final String EXTRA_QUICK_VIEW_FEATURES = "android.intent.extra.QUICK_VIEW_FEATURES";

	public static final String EXTRA_QUIET_MODE = "android.intent.extra.QUIET_MODE";

	public static final String EXTRA_MEDIA_RESOURCE_TYPE = "android.intent.extra.MEDIA_RESOURCE_TYPE";

	public static final String EXTRA_AUTO_LAUNCH_SINGLE_CHOICE = "android.intent.extra.AUTO_LAUNCH_SINGLE_CHOICE";

	public static final int EXTRA_MEDIA_RESOURCE_TYPE_VIDEO_CODEC = 0;

	public static final int EXTRA_MEDIA_RESOURCE_TYPE_AUDIO_CODEC = 1;

	@IntDef(flag = true, prefix = { "FLAG_GRANT_" }, value = { FLAG_GRANT_READ_URI_PERMISSION,
			FLAG_GRANT_WRITE_URI_PERMISSION, FLAG_GRANT_PERSISTABLE_URI_PERMISSION, FLAG_GRANT_PREFIX_URI_PERMISSION })
	@Retention(RetentionPolicy.SOURCE)
	public @interface GrantUriMode {
	}

	@IntDef(flag = true, prefix = { "FLAG_GRANT_" }, value = { FLAG_GRANT_READ_URI_PERMISSION,
			FLAG_GRANT_WRITE_URI_PERMISSION })
	@Retention(RetentionPolicy.SOURCE)
	public @interface AccessUriMode {
	}

	public Intent(String actionMain) {
		throw new RuntimeException("Stub!");
	}

	public static boolean isAccessUriMode(int modeFlags) {
		throw new RuntimeException("Stub!");
	}

	@IntDef(flag = true, prefix = { "FLAG_" }, value = { FLAG_GRANT_READ_URI_PERMISSION,
			FLAG_GRANT_WRITE_URI_PERMISSION, FLAG_FROM_BACKGROUND, FLAG_DEBUG_LOG_RESOLUTION,
			FLAG_EXCLUDE_STOPPED_PACKAGES, FLAG_INCLUDE_STOPPED_PACKAGES, FLAG_GRANT_PERSISTABLE_URI_PERMISSION,
			FLAG_GRANT_PREFIX_URI_PERMISSION, FLAG_DEBUG_TRIAGED_MISSING, FLAG_IGNORE_EPHEMERAL,
			FLAG_ACTIVITY_MATCH_EXTERNAL, FLAG_ACTIVITY_NO_HISTORY, FLAG_ACTIVITY_SINGLE_TOP, FLAG_ACTIVITY_NEW_TASK,
			FLAG_ACTIVITY_MULTIPLE_TASK, FLAG_ACTIVITY_CLEAR_TOP, FLAG_ACTIVITY_FORWARD_RESULT,
			FLAG_ACTIVITY_PREVIOUS_IS_TOP, FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS, FLAG_ACTIVITY_BROUGHT_TO_FRONT,
			FLAG_ACTIVITY_RESET_TASK_IF_NEEDED, FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY,
			FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, FLAG_ACTIVITY_NEW_DOCUMENT, FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET,
			FLAG_ACTIVITY_NO_USER_ACTION, FLAG_ACTIVITY_REORDER_TO_FRONT, FLAG_ACTIVITY_NO_ANIMATION,
			FLAG_ACTIVITY_CLEAR_TASK, FLAG_ACTIVITY_TASK_ON_HOME, FLAG_ACTIVITY_RETAIN_IN_RECENTS,
			FLAG_ACTIVITY_LAUNCH_ADJACENT, FLAG_RECEIVER_REGISTERED_ONLY, FLAG_RECEIVER_REPLACE_PENDING,
			FLAG_RECEIVER_FOREGROUND, FLAG_RECEIVER_NO_ABORT, FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT,
			FLAG_RECEIVER_BOOT_UPGRADE, FLAG_RECEIVER_INCLUDE_BACKGROUND, FLAG_RECEIVER_EXCLUDE_BACKGROUND,
			FLAG_RECEIVER_FROM_SHELL, FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS, })
	@Retention(RetentionPolicy.SOURCE)
	public @interface Flags {
	}

	@IntDef(flag = true, prefix = { "FLAG_" }, value = { FLAG_FROM_BACKGROUND, FLAG_DEBUG_LOG_RESOLUTION,
			FLAG_EXCLUDE_STOPPED_PACKAGES, FLAG_INCLUDE_STOPPED_PACKAGES, FLAG_DEBUG_TRIAGED_MISSING,
			FLAG_IGNORE_EPHEMERAL, FLAG_ACTIVITY_MATCH_EXTERNAL, FLAG_ACTIVITY_NO_HISTORY, FLAG_ACTIVITY_SINGLE_TOP,
			FLAG_ACTIVITY_NEW_TASK, FLAG_ACTIVITY_MULTIPLE_TASK, FLAG_ACTIVITY_CLEAR_TOP, FLAG_ACTIVITY_FORWARD_RESULT,
			FLAG_ACTIVITY_PREVIOUS_IS_TOP, FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS, FLAG_ACTIVITY_BROUGHT_TO_FRONT,
			FLAG_ACTIVITY_RESET_TASK_IF_NEEDED, FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY,
			FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, FLAG_ACTIVITY_NEW_DOCUMENT, FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET,
			FLAG_ACTIVITY_NO_USER_ACTION, FLAG_ACTIVITY_REORDER_TO_FRONT, FLAG_ACTIVITY_NO_ANIMATION,
			FLAG_ACTIVITY_CLEAR_TASK, FLAG_ACTIVITY_TASK_ON_HOME, FLAG_ACTIVITY_RETAIN_IN_RECENTS,
			FLAG_ACTIVITY_LAUNCH_ADJACENT, FLAG_RECEIVER_REGISTERED_ONLY, FLAG_RECEIVER_REPLACE_PENDING,
			FLAG_RECEIVER_FOREGROUND, FLAG_RECEIVER_NO_ABORT, FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT,
			FLAG_RECEIVER_BOOT_UPGRADE, FLAG_RECEIVER_INCLUDE_BACKGROUND, FLAG_RECEIVER_EXCLUDE_BACKGROUND,
			FLAG_RECEIVER_FROM_SHELL, FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS, })
	@Retention(RetentionPolicy.SOURCE)
	public @interface MutableFlags {
	}

	public static final int FLAG_GRANT_READ_URI_PERMISSION = 0x00000001;
	public static final int FLAG_GRANT_WRITE_URI_PERMISSION = 0x00000002;
	public static final int FLAG_FROM_BACKGROUND = 0x00000004;
	public static final int FLAG_DEBUG_LOG_RESOLUTION = 0x00000008;
	public static final int FLAG_EXCLUDE_STOPPED_PACKAGES = 0x00000010;
	public static final int FLAG_INCLUDE_STOPPED_PACKAGES = 0x00000020;

	public static final int FLAG_GRANT_PERSISTABLE_URI_PERMISSION = 0x00000040;

	public static final int FLAG_GRANT_PREFIX_URI_PERMISSION = 0x00000080;

	public static final int FLAG_DEBUG_TRIAGED_MISSING = 0x00000100;

	public static final int FLAG_IGNORE_EPHEMERAL = 0x00000200;

	public static final int FLAG_ACTIVITY_NO_HISTORY = 0x40000000;
	public static final int FLAG_ACTIVITY_SINGLE_TOP = 0x20000000;
	public static final int FLAG_ACTIVITY_NEW_TASK = 0x10000000;
	public static final int FLAG_ACTIVITY_MULTIPLE_TASK = 0x08000000;
	public static final int FLAG_ACTIVITY_CLEAR_TOP = 0x04000000;
	public static final int FLAG_ACTIVITY_FORWARD_RESULT = 0x02000000;
	public static final int FLAG_ACTIVITY_PREVIOUS_IS_TOP = 0x01000000;
	public static final int FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS = 0x00800000;
	public static final int FLAG_ACTIVITY_BROUGHT_TO_FRONT = 0x00400000;
	public static final int FLAG_ACTIVITY_RESET_TASK_IF_NEEDED = 0x00200000;
	public static final int FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY = 0x00100000;
	@Deprecated
	public static final int FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET = 0x00080000;
	public static final int FLAG_ACTIVITY_NEW_DOCUMENT = FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
	public static final int FLAG_ACTIVITY_NO_USER_ACTION = 0x00040000;
	public static final int FLAG_ACTIVITY_REORDER_TO_FRONT = 0X00020000;
	public static final int FLAG_ACTIVITY_NO_ANIMATION = 0X00010000;
	public static final int FLAG_ACTIVITY_CLEAR_TASK = 0X00008000;
	public static final int FLAG_ACTIVITY_TASK_ON_HOME = 0X00004000;
	public static final int FLAG_ACTIVITY_RETAIN_IN_RECENTS = 0x00002000;

	public static final int FLAG_ACTIVITY_LAUNCH_ADJACENT = 0x00001000;

	public static final int FLAG_ACTIVITY_MATCH_EXTERNAL = 0x00000800;

	public static final int FLAG_RECEIVER_REGISTERED_ONLY = 0x40000000;
	public static final int FLAG_RECEIVER_REPLACE_PENDING = 0x20000000;
	public static final int FLAG_RECEIVER_FOREGROUND = 0x10000000;
	public static final int FLAG_RECEIVER_NO_ABORT = 0x08000000;
	@UnsupportedAppUsage
	public static final int FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT = 0x04000000;
	public static final int FLAG_RECEIVER_BOOT_UPGRADE = 0x02000000;
	public static final int FLAG_RECEIVER_INCLUDE_BACKGROUND = 0x01000000;
	public static final int FLAG_RECEIVER_EXCLUDE_BACKGROUND = 0x00800000;
	public static final int FLAG_RECEIVER_FROM_SHELL = 0x00400000;

	public static final int FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS = 0x00200000;

	public static final int IMMUTABLE_FLAGS = FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION
			| FLAG_GRANT_PERSISTABLE_URI_PERMISSION | FLAG_GRANT_PREFIX_URI_PERMISSION;

	@Override
	public int describeContents() {
		throw new RuntimeException("Stub!");
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		throw new RuntimeException("Stub!");
	}

	public Set<String> getCategories() {
		throw new RuntimeException("Stub!");
	}

	public String getAction() {
		throw new RuntimeException("Stub!");
	}

	public String getType() {
		throw new RuntimeException("Stub!");
	}

	public Intent addCategory(String category) {
		throw new RuntimeException("Stub!");
	}

	public <T extends Parcelable> T getParcelableExtra(String name) {
		throw new RuntimeException("Stub!");
	}

	public ComponentName getComponent() {
		throw new RuntimeException("Stub!");
	}

	public String toUri(int i) {
		throw new RuntimeException("Stub!");
	}

	public void putExtras(Bundle args) {
		throw new RuntimeException("Stub!");
	}

	public void setComponent(ComponentName mApp) {
		throw new RuntimeException("Stub!");
	}

	public void addFlags(int i) {
		throw new RuntimeException("Stub!");
	}

	public String getDataString() {
		throw new RuntimeException("Stub!");
	}

}
