package android.media;

import java.util.Map;

import javax.sound.sampled.AudioFormat;

import android.annotation.NonNull;
import android.annotation.SystemService;
import android.content.Context;
import android.view.KeyEvent;

@SystemService(Context.AUDIO_SERVICE)
public class AudioManager {
    public static final String ACTION_AUDIO_BECOMING_NOISY = "android.media.AUDIO_BECOMING_NOISY";
    public static final String RINGER_MODE_CHANGED_ACTION = "android.media.RINGER_MODE_CHANGED";
    public static final String INTERNAL_RINGER_MODE_CHANGED_ACTION = "android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION";
    public static final String EXTRA_RINGER_MODE = "android.media.EXTRA_RINGER_MODE";
    public static final String VIBRATE_SETTING_CHANGED_ACTION = "android.media.VIBRATE_SETTING_CHANGED";
    public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    public static final String STREAM_DEVICES_CHANGED_ACTION = "android.media.STREAM_DEVICES_CHANGED_ACTION";
    public static final String STREAM_MUTE_CHANGED_ACTION = "android.media.STREAM_MUTE_CHANGED_ACTION";
    public static final String MASTER_MUTE_CHANGED_ACTION = "android.media.MASTER_MUTE_CHANGED_ACTION";
    public static final String EXTRA_VIBRATE_SETTING = "android.media.EXTRA_VIBRATE_SETTING";
    public static final String EXTRA_VIBRATE_TYPE = "android.media.EXTRA_VIBRATE_TYPE";
    public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    public static final String EXTRA_VOLUME_STREAM_TYPE_ALIAS = "android.media.EXTRA_VOLUME_STREAM_TYPE_ALIAS";
    public static final String EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";
    public static final String EXTRA_PREV_VOLUME_STREAM_VALUE = "android.media.EXTRA_PREV_VOLUME_STREAM_VALUE";
    public static final String EXTRA_VOLUME_STREAM_DEVICES = "android.media.EXTRA_VOLUME_STREAM_DEVICES";
    public static final String EXTRA_PREV_VOLUME_STREAM_DEVICES = "android.media.EXTRA_PREV_VOLUME_STREAM_DEVICES";
    public static final String EXTRA_MASTER_VOLUME_MUTED = "android.media.EXTRA_MASTER_VOLUME_MUTED";
    public static final String EXTRA_STREAM_VOLUME_MUTED = "android.media.EXTRA_STREAM_VOLUME_MUTED";
    public static final String ACTION_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";
    public static final String ACTION_HDMI_AUDIO_PLUG = "android.media.action.HDMI_AUDIO_PLUG";
    public static final String EXTRA_AUDIO_PLUG_STATE = "android.media.extra.AUDIO_PLUG_STATE";
    public static final String EXTRA_MAX_CHANNEL_COUNT = "android.media.extra.MAX_CHANNEL_COUNT";
    public static final String EXTRA_ENCODINGS = "android.media.extra.ENCODINGS";
    public static final int STREAM_VOICE_CALL = AudioSystem.STREAM_VOICE_CALL;
    public static final int STREAM_SYSTEM = AudioSystem.STREAM_SYSTEM;
    public static final int STREAM_RING = AudioSystem.STREAM_RING;
    public static final int STREAM_MUSIC = AudioSystem.STREAM_MUSIC;
    public static final int STREAM_ALARM = AudioSystem.STREAM_ALARM;
    public static final int STREAM_NOTIFICATION = AudioSystem.STREAM_NOTIFICATION;
    public static final int STREAM_BLUETOOTH_SCO = AudioSystem.STREAM_BLUETOOTH_SCO;
    public static final int STREAM_SYSTEM_ENFORCED = AudioSystem.STREAM_SYSTEM_ENFORCED;
    public static final int STREAM_DTMF = AudioSystem.STREAM_DTMF;
    public static final int STREAM_TTS = AudioSystem.STREAM_TTS;
    public static final int STREAM_ACCESSIBILITY = AudioSystem.STREAM_ACCESSIBILITY;
    public static final int NUM_STREAMS = AudioSystem.NUM_STREAMS;
    public static final int ADJUST_RAISE = 1;
    public static final int ADJUST_LOWER = -1;
    public static final int ADJUST_SAME = 0;
    public static final int ADJUST_MUTE = -100;
    public static final int ADJUST_UNMUTE = 100;
    public static final int ADJUST_TOGGLE_MUTE = 101;

    public static final String adjustToString(int adj) {
        throw new RuntimeException("Stub!");
    }

    public static final int FLAG_SHOW_UI = 1 << 0;
    public static final int FLAG_ALLOW_RINGER_MODES = 1 << 1;
    public static final int FLAG_PLAY_SOUND = 1 << 2;
    public static final int FLAG_REMOVE_SOUND_AND_VIBRATE = 1 << 3;
    public static final int FLAG_VIBRATE = 1 << 4;
    public static final int FLAG_FIXED_VOLUME = 1 << 5;
    public static final int FLAG_BLUETOOTH_ABS_VOLUME = 1 << 6;
    public static final int FLAG_SHOW_SILENT_HINT = 1 << 7;
    public static final int FLAG_HDMI_SYSTEM_AUDIO_VOLUME = 1 << 8;
    public static final int FLAG_ACTIVE_MEDIA_ONLY = 1 << 9;
    public static final int FLAG_SHOW_UI_WARNINGS = 1 << 10;
    public static final int FLAG_SHOW_VIBRATE_HINT = 1 << 11;
    public static final int FLAG_FROM_KEY = 1 << 12;

    public static final int RINGER_MODE_SILENT = 0;
    public static final int RINGER_MODE_VIBRATE = 1;
    public static final int RINGER_MODE_NORMAL = 2;
    public static final int RINGER_MODE_MAX = RINGER_MODE_NORMAL;
    public static final int VIBRATE_TYPE_RINGER = 0;
    public static final int VIBRATE_TYPE_NOTIFICATION = 1;
    public static final int VIBRATE_SETTING_OFF = 0;
    public static final int VIBRATE_SETTING_ON = 1;
    public static final int VIBRATE_SETTING_ONLY_SILENT = 2;
    public static final int USE_DEFAULT_STREAM_TYPE = Integer.MIN_VALUE;

    public AudioManager() {
        throw new RuntimeException("Stub!");
    }

    public AudioManager(Context context) {
        throw new RuntimeException("Stub!");
    }

    public void dispatchMediaKeyEvent(KeyEvent keyEvent) {
        throw new RuntimeException("Stub!");
    }

    public void preDispatchKeyEvent(KeyEvent event, int stream) {
        throw new RuntimeException("Stub!");
    }

    public boolean isVolumeFixed() {
        throw new RuntimeException("Stub!");
    }

    public void adjustStreamVolume(int streamType, int direction, int flags) {
        throw new RuntimeException("Stub!");
    }

    public void adjustVolume(int direction, int flags) {
        throw new RuntimeException("Stub!");
    }

    public void adjustSuggestedStreamVolume(int direction, int suggestedStreamType, int flags) {
        throw new RuntimeException("Stub!");
    }

    public void setMasterMute(boolean mute, int flags) {
        throw new RuntimeException("Stub!");
    }

    public int getRingerMode() {
        throw new RuntimeException("Stub!");
    }

    public static boolean isValidRingerMode(int ringerMode) {
        throw new RuntimeException("Stub!");
    }

    public int getStreamMaxVolume(int streamType) {
        throw new RuntimeException("Stub!");
    }

    public int getStreamMinVolume(int streamType) {
        throw new RuntimeException("Stub!");
    }

    public int getStreamMinVolumeInt(int streamType) {
        throw new RuntimeException("Stub!");
    }

    public int getStreamVolume(int streamType) {
        throw new RuntimeException("Stub!");
    }

    public int getLastAudibleStreamVolume(int streamType) {
        throw new RuntimeException("Stub!");
    }

    public int getUiSoundsStreamType() {
        throw new RuntimeException("Stub!");
    }

    public void setRingerMode(int ringerMode) {
        throw new RuntimeException("Stub!");
    }

    public void setStreamVolume(int streamType, int index, int flags) {
        throw new RuntimeException("Stub!");
    }

    public void setStreamSolo(int streamType, boolean state) {
        throw new RuntimeException("Stub!");
    }

    public void setStreamMute(int streamType, boolean state) {
        throw new RuntimeException("Stub!");
    }

    public boolean isStreamMute(int streamType) {
        throw new RuntimeException("Stub!");
    }

    public boolean isMasterMute() {
        throw new RuntimeException("Stub!");
    }

    public void forceVolumeControlStream(int streamType) {
        throw new RuntimeException("Stub!");
    }

    public boolean shouldVibrate(int vibrateType) {
        throw new RuntimeException("Stub!");
    }

    public int getVibrateSetting(int vibrateType) {
        throw new RuntimeException("Stub!");
    }

    public void setVibrateSetting(int vibrateType, int vibrateSetting) {
        throw new RuntimeException("Stub!");
    }

    public void setSpeakerphoneOn(boolean on) {
        throw new RuntimeException("Stub!");
    }

    public boolean isSpeakerphoneOn() {
        throw new RuntimeException("Stub!");
    }

    public boolean isOffloadedPlaybackSupported(@NonNull AudioFormat format) {
        throw new RuntimeException("Stub!");
    }

    public static final String ACTION_SCO_AUDIO_STATE_CHANGED = "android.media.SCO_AUDIO_STATE_CHANGED";
    public static final String ACTION_SCO_AUDIO_STATE_UPDATED = "android.media.ACTION_SCO_AUDIO_STATE_UPDATED";
    public static final String EXTRA_SCO_AUDIO_STATE = "android.media.extra.SCO_AUDIO_STATE";
    public static final String EXTRA_SCO_AUDIO_PREVIOUS_STATE = "android.media.extra.SCO_AUDIO_PREVIOUS_STATE";
    public static final int SCO_AUDIO_STATE_DISCONNECTED = 0;
    public static final int SCO_AUDIO_STATE_CONNECTED = 1;
    public static final int SCO_AUDIO_STATE_CONNECTING = 2;
    public static final int SCO_AUDIO_STATE_ERROR = -1;

    public boolean isBluetoothScoAvailableOffCall() {
        throw new RuntimeException("Stub!");
    }

    public void startBluetoothSco() {
        throw new RuntimeException("Stub!");
    }

    public void startBluetoothScoVirtualCall() {
        throw new RuntimeException("Stub!");
    }

    public void stopBluetoothSco() {
        throw new RuntimeException("Stub!");
    }

    public void setBluetoothScoOn(boolean on) {
        throw new RuntimeException("Stub!");
    }

    public boolean isBluetoothScoOn() {
        throw new RuntimeException("Stub!");
    }

    public void setBluetoothA2dpOn(boolean on) {
        throw new RuntimeException("Stub!");
    }

    public boolean isBluetoothA2dpOn() {
        throw new RuntimeException("Stub!");
    }

    public void setWiredHeadsetOn(boolean on) {
        throw new RuntimeException("Stub!");
    }

    public boolean isWiredHeadsetOn() {
        throw new RuntimeException("Stub!");
    }

    public void setMicrophoneMute(boolean on) {
        throw new RuntimeException("Stub!");
    }

    public boolean isMicrophoneMute() {
        throw new RuntimeException("Stub!");
    }

    public static final String ACTION_MICROPHONE_MUTE_CHANGED = "android.media.action.MICROPHONE_MUTE_CHANGED";

    public void setMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getMode() {
        throw new RuntimeException("Stub!");
    }

    public static final int MODE_INVALID = AudioSystem.MODE_INVALID;
    public static final int MODE_CURRENT = AudioSystem.MODE_CURRENT;
    public static final int MODE_NORMAL = AudioSystem.MODE_NORMAL;
    public static final int MODE_RINGTONE = AudioSystem.MODE_RINGTONE;
    public static final int MODE_IN_CALL = AudioSystem.MODE_IN_CALL;
    public static final int MODE_IN_COMMUNICATION = AudioSystem.MODE_IN_COMMUNICATION;
    public static final int ROUTE_EARPIECE = AudioSystem.ROUTE_EARPIECE;
    public static final int ROUTE_SPEAKER = AudioSystem.ROUTE_SPEAKER;
    public static final int ROUTE_BLUETOOTH = AudioSystem.ROUTE_BLUETOOTH_SCO;
    public static final int ROUTE_BLUETOOTH_SCO = AudioSystem.ROUTE_BLUETOOTH_SCO;
    public static final int ROUTE_HEADSET = AudioSystem.ROUTE_HEADSET;
    public static final int ROUTE_BLUETOOTH_A2DP = AudioSystem.ROUTE_BLUETOOTH_A2DP;
    public static final int ROUTE_ALL = AudioSystem.ROUTE_ALL;

    public void setRouting(int mode, int routes, int mask) {
        throw new RuntimeException("Stub!");
    }

    public int getRouting(int mode) {
        throw new RuntimeException("Stub!");
    }

    public boolean isMusicActive() {
        throw new RuntimeException("Stub!");
    }

    public boolean isMusicActiveRemotely() {
        throw new RuntimeException("Stub!");
    }

    public boolean isAudioFocusExclusive() {
        throw new RuntimeException("Stub!");
    }

    public int generateAudioSessionId() {
        throw new RuntimeException("Stub!");
    }

    public static final int AUDIO_SESSION_ID_GENERATE = AudioSystem.AUDIO_SESSION_ALLOCATE;

    public void setParameter(String key, String value) {
        throw new RuntimeException("Stub!");
    }

    public void setParameters(String keyValuePairs) {
        throw new RuntimeException("Stub!");
    }

    public String getParameters(String keys) {
        throw new RuntimeException("Stub!");
    }

    public static final int FX_KEY_CLICK = 0;
    public static final int FX_FOCUS_NAVIGATION_UP = 1;
    public static final int FX_FOCUS_NAVIGATION_DOWN = 2;
    public static final int FX_FOCUS_NAVIGATION_LEFT = 3;
    public static final int FX_FOCUS_NAVIGATION_RIGHT = 4;
    public static final int FX_KEYPRESS_STANDARD = 5;
    public static final int FX_KEYPRESS_SPACEBAR = 6;
    public static final int FX_KEYPRESS_DELETE = 7;
    public static final int FX_KEYPRESS_RETURN = 8;
    public static final int FX_KEYPRESS_INVALID = 9;
    public static final int NUM_SOUND_EFFECTS = 10;

    public void playSoundEffect(int effectType) {
        throw new RuntimeException("Stub!");
    }

    public void playSoundEffect(int effectType, int userId) {
        throw new RuntimeException("Stub!");
    }

    public void playSoundEffect(int effectType, float volume) {
        throw new RuntimeException("Stub!");
    }

    public void loadSoundEffects() {
        throw new RuntimeException("Stub!");
    }

    public void unloadSoundEffects() {
        throw new RuntimeException("Stub!");
    }

    public static final int AUDIOFOCUS_NONE = 0;
    public static final int AUDIOFOCUS_GAIN = 1;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE = 4;
    public static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = 5;

    public interface OnAudioFocusChangeListener {
        public void onAudioFocusChange(int focusChange);
    }

    public static final int AUDIOFOCUS_REQUEST_FAILED = 0;
    public static final int AUDIOFOCUS_REQUEST_GRANTED = 1;
    public static final int AUDIOFOCUS_REQUEST_DELAYED = 2;

    public static final int AUDIOFOCUS_REQUEST_WAITING_FOR_EXT_POLICY = 100;

    public int requestAudioFocus(OnAudioFocusChangeListener l, int streamType, int durationHint) {
        throw new RuntimeException("Stub!");
    }

    public static final int AUDIOFOCUS_FLAG_DELAY_OK = 0x1 << 0;
    public static final int AUDIOFOCUS_FLAG_PAUSES_ON_DUCKABLE_LOSS = 0x1 << 1;
    public static final int AUDIOFOCUS_FLAG_LOCK = 0x1 << 2;
    public static final int AUDIOFOCUS_FLAGS_APPS = AUDIOFOCUS_FLAG_DELAY_OK | AUDIOFOCUS_FLAG_PAUSES_ON_DUCKABLE_LOSS;
    public static final int AUDIOFOCUS_FLAGS_SYSTEM = AUDIOFOCUS_FLAG_DELAY_OK | AUDIOFOCUS_FLAG_PAUSES_ON_DUCKABLE_LOSS
            | AUDIOFOCUS_FLAG_LOCK;

    public void safeWait(long millis) throws InterruptedException {

    }

    public int abandonAudioFocus(OnAudioFocusChangeListener l) {
        throw new RuntimeException("Stub!");
    }

    public final static int RECORD_CONFIG_EVENT_START = 1;
    public final static int RECORD_CONFIG_EVENT_STOP = 0;

    public void reloadAudioSettings() {
        throw new RuntimeException("Stub!");
    }

    public void avrcpSupportsAbsoluteVolume(String address, boolean support) {
        throw new RuntimeException("Stub!");
    }

    public boolean isSilentMode() {
        throw new RuntimeException("Stub!");
    }

    public static final int DEVICE_NONE = AudioSystem.DEVICE_NONE;
    public static final int DEVICE_OUT_EARPIECE = AudioSystem.DEVICE_OUT_EARPIECE;
    public static final int DEVICE_OUT_SPEAKER = AudioSystem.DEVICE_OUT_SPEAKER;
    public static final int DEVICE_OUT_WIRED_HEADSET = AudioSystem.DEVICE_OUT_WIRED_HEADSET;
    public static final int DEVICE_OUT_WIRED_HEADPHONE = AudioSystem.DEVICE_OUT_WIRED_HEADPHONE;
    public static final int DEVICE_OUT_USB_HEADSET = AudioSystem.DEVICE_OUT_USB_HEADSET;
    public static final int DEVICE_OUT_BLUETOOTH_SCO = AudioSystem.DEVICE_OUT_BLUETOOTH_SCO;
    public static final int DEVICE_OUT_BLUETOOTH_SCO_HEADSET = AudioSystem.DEVICE_OUT_BLUETOOTH_SCO_HEADSET;
    public static final int DEVICE_OUT_BLUETOOTH_SCO_CARKIT = AudioSystem.DEVICE_OUT_BLUETOOTH_SCO_CARKIT;
    public static final int DEVICE_OUT_BLUETOOTH_A2DP = AudioSystem.DEVICE_OUT_BLUETOOTH_A2DP;
    public static final int DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES = AudioSystem.DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES;
    public static final int DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER = AudioSystem.DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER;
    public static final int DEVICE_OUT_AUX_DIGITAL = AudioSystem.DEVICE_OUT_AUX_DIGITAL;
    public static final int DEVICE_OUT_HDMI = AudioSystem.DEVICE_OUT_HDMI;
    public static final int DEVICE_OUT_ANLG_DOCK_HEADSET = AudioSystem.DEVICE_OUT_ANLG_DOCK_HEADSET;
    public static final int DEVICE_OUT_DGTL_DOCK_HEADSET = AudioSystem.DEVICE_OUT_DGTL_DOCK_HEADSET;
    public static final int DEVICE_OUT_USB_ACCESSORY = AudioSystem.DEVICE_OUT_USB_ACCESSORY;
    public static final int DEVICE_OUT_USB_DEVICE = AudioSystem.DEVICE_OUT_USB_DEVICE;
    public static final int DEVICE_OUT_REMOTE_SUBMIX = AudioSystem.DEVICE_OUT_REMOTE_SUBMIX;
    public static final int DEVICE_OUT_TELEPHONY_TX = AudioSystem.DEVICE_OUT_TELEPHONY_TX;
    public static final int DEVICE_OUT_LINE = AudioSystem.DEVICE_OUT_LINE;
    public static final int DEVICE_OUT_HDMI_ARC = AudioSystem.DEVICE_OUT_HDMI_ARC;
    public static final int DEVICE_OUT_SPDIF = AudioSystem.DEVICE_OUT_SPDIF;
    public static final int DEVICE_OUT_FM = AudioSystem.DEVICE_OUT_FM;
    public static final int DEVICE_OUT_DEFAULT = AudioSystem.DEVICE_OUT_DEFAULT;
    public static final int DEVICE_IN_BUILTIN_MIC = AudioSystem.DEVICE_IN_BUILTIN_MIC;
    public static final int DEVICE_IN_BLUETOOTH_SCO_HEADSET = AudioSystem.DEVICE_IN_BLUETOOTH_SCO_HEADSET;
    public static final int DEVICE_IN_WIRED_HEADSET = AudioSystem.DEVICE_IN_WIRED_HEADSET;
    public static final int DEVICE_IN_HDMI = AudioSystem.DEVICE_IN_HDMI;
    public static final int DEVICE_IN_TELEPHONY_RX = AudioSystem.DEVICE_IN_TELEPHONY_RX;
    public static final int DEVICE_IN_BACK_MIC = AudioSystem.DEVICE_IN_BACK_MIC;
    public static final int DEVICE_IN_ANLG_DOCK_HEADSET = AudioSystem.DEVICE_IN_ANLG_DOCK_HEADSET;
    public static final int DEVICE_IN_DGTL_DOCK_HEADSET = AudioSystem.DEVICE_IN_DGTL_DOCK_HEADSET;
    public static final int DEVICE_IN_USB_ACCESSORY = AudioSystem.DEVICE_IN_USB_ACCESSORY;
    public static final int DEVICE_IN_USB_DEVICE = AudioSystem.DEVICE_IN_USB_DEVICE;
    public static final int DEVICE_IN_FM_TUNER = AudioSystem.DEVICE_IN_FM_TUNER;
    public static final int DEVICE_IN_TV_TUNER = AudioSystem.DEVICE_IN_TV_TUNER;
    public static final int DEVICE_IN_LINE = AudioSystem.DEVICE_IN_LINE;
    public static final int DEVICE_IN_SPDIF = AudioSystem.DEVICE_IN_SPDIF;
    public static final int DEVICE_IN_LOOPBACK = AudioSystem.DEVICE_IN_LOOPBACK;

    public static boolean isOutputDevice(int device) {
        throw new RuntimeException("Stub!");
    }

    public static boolean isInputDevice(int device) {
        throw new RuntimeException("Stub!");
    }

    public int getDevicesForStream(int streamType) {
        throw new RuntimeException("Stub!");
    }

    public void setWiredDeviceConnectionState(int type, int state, String address, String name) {
        throw new RuntimeException("Stub!");
    }

    public static final String PROPERTY_OUTPUT_SAMPLE_RATE = "android.media.property.OUTPUT_SAMPLE_RATE";
    public static final String PROPERTY_OUTPUT_FRAMES_PER_BUFFER = "android.media.property.OUTPUT_FRAMES_PER_BUFFER";
    public static final String PROPERTY_SUPPORT_MIC_NEAR_ULTRASOUND = "android.media.property.SUPPORT_MIC_NEAR_ULTRASOUND";
    public static final String PROPERTY_SUPPORT_SPEAKER_NEAR_ULTRASOUND = "android.media.property.SUPPORT_SPEAKER_NEAR_ULTRASOUND";
    public static final String PROPERTY_SUPPORT_AUDIO_SOURCE_UNPROCESSED = "android.media.property.SUPPORT_AUDIO_SOURCE_UNPROCESSED";

    public String getProperty(String key) {
        throw new RuntimeException("Stub!");
    }

    public int getOutputLatency(int streamType) {
        throw new RuntimeException("Stub!");
    }

    public boolean isStreamAffectedByRingerMode(int streamType) {
        throw new RuntimeException("Stub!");
    }

    public boolean isStreamAffectedByMute(int streamType) {
        throw new RuntimeException("Stub!");
    }

    public void disableSafeMediaVolume() {
        throw new RuntimeException("Stub!");
    }

    public void setRingerModeInternal(int ringerMode) {
        throw new RuntimeException("Stub!");
    }

    public int getRingerModeInternal() {
        throw new RuntimeException("Stub!");
    }

    public int setHdmiSystemAudioSupported(boolean on) {
        throw new RuntimeException("Stub!");
    }

    public boolean isHdmiSystemAudioSupported() {
        throw new RuntimeException("Stub!");
    }

    public static final int SUCCESS = AudioSystem.SUCCESS;
    public static final int ERROR = AudioSystem.ERROR;
    public static final int ERROR_BAD_VALUE = AudioSystem.BAD_VALUE;
    public static final int ERROR_INVALID_OPERATION = AudioSystem.INVALID_OPERATION;
    public static final int ERROR_PERMISSION_DENIED = AudioSystem.PERMISSION_DENIED;
    public static final int ERROR_NO_INIT = AudioSystem.NO_INIT;
    public static final int ERROR_DEAD_OBJECT = AudioSystem.DEAD_OBJECT;

    public interface OnAudioPortUpdateListener {

        public void onServiceDied();
    }

    public void registerAudioPortUpdateListener(OnAudioPortUpdateListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterAudioPortUpdateListener(OnAudioPortUpdateListener l) {
        throw new RuntimeException("Stub!");
    }

    static int resetAudioPortGeneration() {
        throw new RuntimeException("Stub!");
    }

    public static final int GET_DEVICES_INPUTS = 0x0001;
    public static final int GET_DEVICES_OUTPUTS = 0x0002;
    public static final int GET_DEVICES_ALL = GET_DEVICES_OUTPUTS | GET_DEVICES_INPUTS;

    public abstract static class AudioServerStateCallback {
        public void onAudioServerDown() {
            throw new RuntimeException("Stub!");
        }

        public void onAudioServerUp() {
            throw new RuntimeException("Stub!");
        }
    }

    public void clearAudioServerStateCallback() {
        throw new RuntimeException("Stub!");
    }

    public boolean isAudioServerRunning() {
        throw new RuntimeException("Stub!");
    }

    public Map<Integer, Boolean> getSurroundFormats() {
        throw new RuntimeException("Stub!");
    }

    public Map<Integer, Boolean> getReportedSurroundFormats() {
        throw new RuntimeException("Stub!");
    }

}