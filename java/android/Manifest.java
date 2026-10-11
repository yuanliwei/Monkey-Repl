package android;

public final class Manifest {
    public Manifest() {
        throw new RuntimeException("Stub!");
    }

    public static final class permission_group {
        public static final String CALENDAR = "android.permission-group.CALENDAR";
        public static final String CAMERA = "android.permission-group.CAMERA";
        public static final String CONTACTS = "android.permission-group.CONTACTS";
        public static final String LOCATION = "android.permission-group.LOCATION";
        public static final String MICROPHONE = "android.permission-group.MICROPHONE";
        public static final String PHONE = "android.permission-group.PHONE";
        public static final String SENSORS = "android.permission-group.SENSORS";
        public static final String SMS = "android.permission-group.SMS";
        public static final String STORAGE = "android.permission-group.STORAGE";

        public permission_group() {
            throw new RuntimeException("Stub!");
        }
    }

    public static final class permission {
        public static final String ACCESS_AMBIENT_LIGHT_STATS = "android.permission.ACCESS_AMBIENT_LIGHT_STATS";
        public static final String ACCESS_BROADCAST_RADIO = "android.permission.ACCESS_BROADCAST_RADIO";
        public static final String ACCESS_CACHE_FILESYSTEM = "android.permission.ACCESS_CACHE_FILESYSTEM";
        public static final String ACCESS_DRM_CERTIFICATES = "android.permission.ACCESS_DRM_CERTIFICATES";
        @Deprecated
        public static final String ACCESS_FM_RADIO = "android.permission.ACCESS_FM_RADIO";
        public static final String ACCESS_MOCK_LOCATION = "android.permission.ACCESS_MOCK_LOCATION";
        public static final String ACCESS_MTP = "android.permission.ACCESS_MTP";
        public static final String ACCESS_NETWORK_CONDITIONS = "android.permission.ACCESS_NETWORK_CONDITIONS";
        public static final String ACCESS_NOTIFICATIONS = "android.permission.ACCESS_NOTIFICATIONS";
        public static final String ACCESS_SHORTCUTS = "android.permission.ACCESS_SHORTCUTS";
        public static final String ACCESS_SURFACE_FLINGER = "android.permission.ACCESS_SURFACE_FLINGER";
        public static final String ACTIVITY_EMBEDDING = "android.permission.ACTIVITY_EMBEDDING";
        public static final String ALLOCATE_AGGRESSIVE = "android.permission.ALLOCATE_AGGRESSIVE";
        public static final String ALLOW_ANY_CODEC_FOR_PLAYBACK = "android.permission.ALLOW_ANY_CODEC_FOR_PLAYBACK";
        public static final String BACKUP = "android.permission.BACKUP";
        @Deprecated
        public static final String BIND_CONNECTION_SERVICE = "android.permission.BIND_CONNECTION_SERVICE";
        public static final String BIND_DIRECTORY_SEARCH = "android.permission.BIND_DIRECTORY_SEARCH";
        public static final String BIND_EUICC_SERVICE = "android.permission.BIND_EUICC_SERVICE";
        public static final String BIND_IMS_SERVICE = "android.permission.BIND_IMS_SERVICE";
        public static final String BIND_KEYGUARD_APPWIDGET = "android.permission.BIND_KEYGUARD_APPWIDGET";
        public static final String BIND_NETWORK_RECOMMENDATION_SERVICE = "android.permission.BIND_NETWORK_RECOMMENDATION_SERVICE";
        public static final String BIND_NOTIFICATION_ASSISTANT_SERVICE = "android.permission.BIND_NOTIFICATION_ASSISTANT_SERVICE";
        public static final String BIND_PHONE_ACCOUNT_SUGGESTION_SERVICE = "android.permission.BIND_PHONE_ACCOUNT_SUGGESTION_SERVICE";
        public static final String BIND_PRINT_RECOMMENDATION_SERVICE = "android.permission.BIND_PRINT_RECOMMENDATION_SERVICE";
        public static final String BIND_RESOLVER_RANKER_SERVICE = "android.permission.BIND_RESOLVER_RANKER_SERVICE";
        public static final String BIND_RUNTIME_PERMISSION_PRESENTER_SERVICE = "android.permission.BIND_RUNTIME_PERMISSION_PRESENTER_SERVICE";
        public static final String BIND_SETTINGS_SUGGESTIONS_SERVICE = "android.permission.BIND_SETTINGS_SUGGESTIONS_SERVICE";
        public static final String BIND_SOUND_TRIGGER_DETECTION_SERVICE = "android.permission.BIND_SOUND_TRIGGER_DETECTION_SERVICE";
        public static final String BIND_TELEPHONY_DATA_SERVICE = "android.permission.BIND_TELEPHONY_DATA_SERVICE";
        public static final String BIND_TELEPHONY_NETWORK_SERVICE = "android.permission.BIND_TELEPHONY_NETWORK_SERVICE";
        public static final String BIND_TEXTCLASSIFIER_SERVICE = "android.permission.BIND_TEXTCLASSIFIER_SERVICE";
        public static final String BIND_TRUST_AGENT = "android.permission.BIND_TRUST_AGENT";
        public static final String BIND_TV_REMOTE_SERVICE = "android.permission.BIND_TV_REMOTE_SERVICE";
        public static final String BRICK = "android.permission.BRICK";
        public static final String BRIGHTNESS_SLIDER_USAGE = "android.permission.BRIGHTNESS_SLIDER_USAGE";
        @Deprecated
        public static final String BROADCAST_NETWORK_PRIVILEGED = "android.permission.BROADCAST_NETWORK_PRIVILEGED";
        public static final String CAMERA_DISABLE_TRANSMIT_LED = "android.permission.CAMERA_DISABLE_TRANSMIT_LED";
        public static final String CAPTURE_AUDIO_HOTWORD = "android.permission.CAPTURE_AUDIO_HOTWORD";
        public static final String CAPTURE_TV_INPUT = "android.permission.CAPTURE_TV_INPUT";
        public static final String CHANGE_APP_IDLE_STATE = "android.permission.CHANGE_APP_IDLE_STATE";
        public static final String CHANGE_DEVICE_IDLE_TEMP_WHITELIST = "android.permission.CHANGE_DEVICE_IDLE_TEMP_WHITELIST";
        public static final String CLEAR_APP_USER_DATA = "android.permission.CLEAR_APP_USER_DATA";
        public static final String CONFIGURE_DISPLAY_BRIGHTNESS = "android.permission.CONFIGURE_DISPLAY_BRIGHTNESS";
        public static final String CONNECTIVITY_INTERNAL = "android.permission.CONNECTIVITY_INTERNAL";
        public static final String CONNECTIVITY_USE_RESTRICTED_NETWORKS = "android.permission.CONNECTIVITY_USE_RESTRICTED_NETWORKS";
        public static final String CONTROL_DISPLAY_SATURATION = "android.permission.CONTROL_DISPLAY_SATURATION";
        public static final String CONTROL_INCALL_EXPERIENCE = "android.permission.CONTROL_INCALL_EXPERIENCE";
        public static final String CONTROL_VPN = "android.permission.CONTROL_VPN";
        public static final String CRYPT_KEEPER = "android.permission.CRYPT_KEEPER";
        public static final String DEVICE_POWER = "android.permission.DEVICE_POWER";
        public static final String DISPATCH_PROVISIONING_MESSAGE = "android.permission.DISPATCH_PROVISIONING_MESSAGE";
        public static final String FORCE_BACK = "android.permission.FORCE_BACK";
        public static final String FORCE_STOP_PACKAGES = "android.permission.FORCE_STOP_PACKAGES";
        public static final String GET_APP_OPS_STATS = "android.permission.GET_APP_OPS_STATS";
        public static final String GET_PROCESS_STATE_AND_OOM_SCORE = "android.permission.GET_PROCESS_STATE_AND_OOM_SCORE";
        public static final String GET_TOP_ACTIVITY_INFO = "android.permission.GET_TOP_ACTIVITY_INFO";
        public static final String GRANT_RUNTIME_PERMISSIONS = "android.permission.GRANT_RUNTIME_PERMISSIONS";
        public static final String HARDWARE_TEST = "android.permission.HARDWARE_TEST";
        public static final String HDMI_CEC = "android.permission.HDMI_CEC";
        public static final String HIDE_NON_SYSTEM_OVERLAY_WINDOWS = "android.permission.HIDE_NON_SYSTEM_OVERLAY_WINDOWS";
        public static final String INJECT_EVENTS = "android.permission.INJECT_EVENTS";
        public static final String INSTALL_GRANT_RUNTIME_PERMISSIONS = "android.permission.INSTALL_GRANT_RUNTIME_PERMISSIONS";
        public static final String INSTALL_PACKAGE_UPDATES = "android.permission.INSTALL_PACKAGE_UPDATES";
        public static final String INSTALL_SELF_UPDATES = "android.permission.INSTALL_SELF_UPDATES";
        public static final String INTENT_FILTER_VERIFICATION_AGENT = "android.permission.INTENT_FILTER_VERIFICATION_AGENT";
        public static final String INTERACT_ACROSS_USERS = "android.permission.INTERACT_ACROSS_USERS";
        public static final String INTERACT_ACROSS_USERS_FULL = "android.permission.INTERACT_ACROSS_USERS_FULL";
        public static final String INTERNAL_SYSTEM_WINDOW = "android.permission.INTERNAL_SYSTEM_WINDOW";
        public static final String INVOKE_CARRIER_SETUP = "android.permission.INVOKE_CARRIER_SETUP";
        public static final String KILL_UID = "android.permission.KILL_UID";
        public static final String LOCAL_MAC_ADDRESS = "android.permission.LOCAL_MAC_ADDRESS";
        public static final String LOOP_RADIO = "android.permission.LOOP_RADIO";
        public static final String MANAGE_ACTIVITY_STACKS = "android.permission.MANAGE_ACTIVITY_STACKS";
        public static final String MANAGE_APP_OPS_RESTRICTIONS = "android.permission.MANAGE_APP_OPS_RESTRICTIONS";
        public static final String MANAGE_APP_TOKENS = "android.permission.MANAGE_APP_TOKENS";
        public static final String MANAGE_AUTO_FILL = "android.permission.MANAGE_AUTO_FILL";
        public static final String MANAGE_CARRIER_OEM_UNLOCK_STATE = "android.permission.MANAGE_CARRIER_OEM_UNLOCK_STATE";
        public static final String MANAGE_CA_CERTIFICATES = "android.permission.MANAGE_CA_CERTIFICATES";
        public static final String MANAGE_DEVICE_ADMINS = "android.permission.MANAGE_DEVICE_ADMINS";
        public static final String MANAGE_IPSEC_TUNNELS = "android.permission.MANAGE_IPSEC_TUNNELS";
        public static final String MANAGE_SOUND_TRIGGER = "android.permission.MANAGE_SOUND_TRIGGER";
        public static final String MANAGE_SUBSCRIPTION_PLANS = "android.permission.MANAGE_SUBSCRIPTION_PLANS";
        public static final String MANAGE_USB = "android.permission.MANAGE_USB";
        public static final String MANAGE_USERS = "android.permission.MANAGE_USERS";
        public static final String MANAGE_USER_OEM_UNLOCK_STATE = "android.permission.MANAGE_USER_OEM_UNLOCK_STATE";
        public static final String MODIFY_APPWIDGET_BIND_PERMISSIONS = "android.permission.MODIFY_APPWIDGET_BIND_PERMISSIONS";
        public static final String MODIFY_AUDIO_ROUTING = "android.permission.MODIFY_AUDIO_ROUTING";
        public static final String MODIFY_CELL_BROADCASTS = "android.permission.MODIFY_CELL_BROADCASTS";
        public static final String MODIFY_DAY_NIGHT_MODE = "android.permission.MODIFY_DAY_NIGHT_MODE";
        public static final String REORDER_TASKS = "android.permission.REORDER_TASKS";
        @Deprecated
        public static final String MODIFY_NETWORK_ACCOUNTING = "android.permission.MODIFY_NETWORK_ACCOUNTING";
        public static final String MODIFY_PARENTAL_CONTROLS = "android.permission.MODIFY_PARENTAL_CONTROLS";
        public static final String MODIFY_QUIET_MODE = "android.permission.MODIFY_QUIET_MODE";
        public static final String MOVE_PACKAGE = "android.permission.MOVE_PACKAGE";
        public static final String NETWORK_SCAN = "android.permission.NETWORK_SCAN";
        public static final String NETWORK_SETUP_WIZARD = "android.permission.NETWORK_SETUP_WIZARD";
        public static final String NETWORK_SIGNAL_STRENGTH_WAKEUP = "android.permission.NETWORK_SIGNAL_STRENGTH_WAKEUP";
        public static final String NOTIFICATION_DURING_SETUP = "android.permission.NOTIFICATION_DURING_SETUP";
        public static final String NOTIFY_TV_INPUTS = "android.permission.NOTIFY_TV_INPUTS";
        public static final String OBSERVE_APP_USAGE = "android.permission.OBSERVE_APP_USAGE";
        public static final String OVERRIDE_WIFI_CONFIG = "android.permission.OVERRIDE_WIFI_CONFIG";
        public static final String PACKAGE_VERIFICATION_AGENT = "android.permission.PACKAGE_VERIFICATION_AGENT";
        public static final String PACKET_KEEPALIVE_OFFLOAD = "android.permission.PACKET_KEEPALIVE_OFFLOAD";
        public static final String PEERS_MAC_ADDRESS = "android.permission.PEERS_MAC_ADDRESS";
        public static final String PERFORM_CDMA_PROVISIONING = "android.permission.PERFORM_CDMA_PROVISIONING";
        public static final String PERFORM_SIM_ACTIVATION = "android.permission.PERFORM_SIM_ACTIVATION";
        public static final String PROVIDE_RESOLVER_RANKER_SERVICE = "android.permission.PROVIDE_RESOLVER_RANKER_SERVICE";
        public static final String PROVIDE_TRUST_AGENT = "android.permission.PROVIDE_TRUST_AGENT";
        public static final String QUERY_TIME_ZONE_RULES = "android.permission.QUERY_TIME_ZONE_RULES";
        public static final String READ_CONTENT_RATING_SYSTEMS = "android.permission.READ_CONTENT_RATING_SYSTEMS";
        public static final String READ_DREAM_STATE = "android.permission.READ_DREAM_STATE";
        public static final String READ_INSTALL_SESSIONS = "android.permission.READ_INSTALL_SESSIONS";
        public static final String READ_NETWORK_USAGE_HISTORY = "android.permission.READ_NETWORK_USAGE_HISTORY";
        public static final String READ_OEM_UNLOCK_STATE = "android.permission.READ_OEM_UNLOCK_STATE";
        public static final String READ_PRINT_SERVICES = "android.permission.READ_PRINT_SERVICES";
        public static final String READ_PRINT_SERVICE_RECOMMENDATIONS = "android.permission.READ_PRINT_SERVICE_RECOMMENDATIONS";
        public static final String READ_PRIVILEGED_PHONE_STATE = "android.permission.READ_PRIVILEGED_PHONE_STATE";
        public static final String READ_RUNTIME_PROFILES = "android.permission.READ_RUNTIME_PROFILES";
        public static final String READ_SEARCH_INDEXABLES = "android.permission.READ_SEARCH_INDEXABLES";
        public static final String READ_SYSTEM_UPDATE_INFO = "android.permission.READ_SYSTEM_UPDATE_INFO";
        public static final String READ_WALLPAPER_INTERNAL = "android.permission.READ_WALLPAPER_INTERNAL";
        public static final String READ_WIFI_CREDENTIAL = "android.permission.READ_WIFI_CREDENTIAL";
        public static final String REAL_GET_TASKS = "android.permission.REAL_GET_TASKS";
        public static final String RECEIVE_DATA_ACTIVITY_CHANGE = "android.permission.RECEIVE_DATA_ACTIVITY_CHANGE";
        public static final String RECEIVE_EMERGENCY_BROADCAST = "android.permission.RECEIVE_EMERGENCY_BROADCAST";
        public static final String RECEIVE_WIFI_CREDENTIAL_CHANGE = "android.permission.RECEIVE_WIFI_CREDENTIAL_CHANGE";
        public static final String RECOVERY = "android.permission.RECOVERY";
        public static final String RECOVER_KEYSTORE = "android.permission.RECOVER_KEYSTORE";
        public static final String REGISTER_CALL_PROVIDER = "android.permission.REGISTER_CALL_PROVIDER";
        public static final String REGISTER_CONNECTION_MANAGER = "android.permission.REGISTER_CONNECTION_MANAGER";
        public static final String REGISTER_SIM_SUBSCRIPTION = "android.permission.REGISTER_SIM_SUBSCRIPTION";
        public static final String REMOVE_DRM_CERTIFICATES = "android.permission.REMOVE_DRM_CERTIFICATES";
        public static final String RESTRICTED_VR_ACCESS = "android.permission.RESTRICTED_VR_ACCESS";
        public static final String RETRIEVE_WINDOW_CONTENT = "android.permission.RETRIEVE_WINDOW_CONTENT";
        public static final String REVOKE_RUNTIME_PERMISSIONS = "android.permission.REVOKE_RUNTIME_PERMISSIONS";
        public static final String SCORE_NETWORKS = "android.permission.SCORE_NETWORKS";
        public static final String SEND_SHOW_SUSPENDED_APP_DETAILS = "android.permission.SEND_SHOW_SUSPENDED_APP_DETAILS";
        public static final String SEND_SMS_NO_CONFIRMATION = "android.permission.SEND_SMS_NO_CONFIRMATION";
        public static final String SERIAL_PORT = "android.permission.SERIAL_PORT";
        public static final String SET_ACTIVITY_WATCHER = "android.permission.SET_ACTIVITY_WATCHER";
        public static final String SET_HARMFUL_APP_WARNINGS = "android.permission.SET_HARMFUL_APP_WARNINGS";
        public static final String SET_MEDIA_KEY_LISTENER = "android.permission.SET_MEDIA_KEY_LISTENER";
        public static final String SET_ORIENTATION = "android.permission.SET_ORIENTATION";
        public static final String SET_POINTER_SPEED = "android.permission.SET_POINTER_SPEED";
        public static final String SET_SCREEN_COMPATIBILITY = "android.permission.SET_SCREEN_COMPATIBILITY";
        public static final String SET_VOLUME_KEY_LONG_PRESS_LISTENER = "android.permission.SET_VOLUME_KEY_LONG_PRESS_LISTENER";
        public static final String SET_WALLPAPER_COMPONENT = "android.permission.SET_WALLPAPER_COMPONENT";
        public static final String SHOW_KEYGUARD_MESSAGE = "android.permission.SHOW_KEYGUARD_MESSAGE";
        public static final String SHUTDOWN = "android.permission.SHUTDOWN";
        public static final String STOP_APP_SWITCHES = "android.permission.STOP_APP_SWITCHES";
        public static final String SUBSTITUTE_NOTIFICATION_APP_NAME = "android.permission.SUBSTITUTE_NOTIFICATION_APP_NAME";
        public static final String SUSPEND_APPS = "android.permission.SUSPEND_APPS";
        public static final String TETHER_PRIVILEGED = "android.permission.TETHER_PRIVILEGED";
        public static final String TV_INPUT_HARDWARE = "android.permission.TV_INPUT_HARDWARE";
        public static final String TV_VIRTUAL_REMOTE_CONTROLLER = "android.permission.TV_VIRTUAL_REMOTE_CONTROLLER";
        public static final String UNLIMITED_SHORTCUTS_API_CALLS = "android.permission.UNLIMITED_SHORTCUTS_API_CALLS";
        public static final String UPDATE_APP_OPS_STATS = "android.permission.UPDATE_APP_OPS_STATS";
        public static final String UPDATE_LOCK = "android.permission.UPDATE_LOCK";
        public static final String UPDATE_TIME_ZONE_RULES = "android.permission.UPDATE_TIME_ZONE_RULES";
        public static final String USER_ACTIVITY = "android.permission.USER_ACTIVITY";
        public static final String USE_RESERVED_DISK = "android.permission.USE_RESERVED_DISK";
        public static final String WRITE_DREAM_STATE = "android.permission.WRITE_DREAM_STATE";
        public static final String WRITE_EMBEDDED_SUBSCRIPTIONS = "android.permission.WRITE_EMBEDDED_SUBSCRIPTIONS";
        public static final String WRITE_MEDIA_STORAGE = "android.permission.WRITE_MEDIA_STORAGE";
		public static final String ACCESS_INSTANT_APPS = "null";
		public static final String READ_PHONE_STATE = "null";

        public permission() {
            throw new RuntimeException("Stub!");
        }
    }
}
