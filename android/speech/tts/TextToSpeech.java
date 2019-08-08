package android.speech.tts;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

import android.content.Context;

public class TextToSpeech {

    public static final int SUCCESS = 0;
    public static final int ERROR = -1;
    public static final int STOPPED = -2;
    public static final int ERROR_SYNTHESIS = -3;
    public static final int ERROR_SERVICE = -4;
    public static final int ERROR_OUTPUT = -5;
    public static final int ERROR_NETWORK = -6;
    public static final int ERROR_NETWORK_TIMEOUT = -7;
    public static final int ERROR_INVALID_REQUEST = -8;
    public static final int ERROR_NOT_INSTALLED_YET = -9;
    public static final int QUEUE_FLUSH = 0;
    public static final int QUEUE_ADD = 1;
    static final int QUEUE_DESTROY = 2;
    public static final int LANG_COUNTRY_VAR_AVAILABLE = 2;
    public static final int LANG_COUNTRY_AVAILABLE = 1;
    public static final int LANG_AVAILABLE = 0;
    public static final int LANG_MISSING_DATA = -1;
    public static final int LANG_NOT_SUPPORTED = -2;

    public interface OnInitListener {
        void onInit(int status);
    }

    public interface OnUtteranceCompletedListener {
    }

    public class Engine {
    }

    public TextToSpeech(Context context, OnInitListener listener) {
        throw new RuntimeException("Stub!");
    }

    public TextToSpeech(Context context, OnInitListener listener, String engine) {
        throw new RuntimeException("Stub!");
    }

    public void shutdown() {
        throw new RuntimeException("Stub!");
    }

    public int addSpeech(String text, String filename) {
        throw new RuntimeException("Stub!");
    }

    public int addSpeech(CharSequence text, File file) {
        throw new RuntimeException("Stub!");
    }

    public int addEarcon(String earcon, String filename) {
        throw new RuntimeException("Stub!");
    }

    public int addEarcon(String earcon, File file) {
        throw new RuntimeException("Stub!");
    }

    public Set<String> getFeatures(final Locale locale) {
        throw new RuntimeException("Stub!");
    }

    public boolean isSpeaking() {
        throw new RuntimeException("Stub!");
    }

    public int stop() {
        throw new RuntimeException("Stub!");
    }

    public int setSpeechRate(float speechRate) {
        throw new RuntimeException("Stub!");
    }

    public int setPitch(float pitch) {
        throw new RuntimeException("Stub!");
    }

    public String getCurrentEngine() {
        throw new RuntimeException("Stub!");
    }

    public Locale getDefaultLanguage() {
        throw new RuntimeException("Stub!");
    }

    public int setLanguage(final Locale loc) {
        throw new RuntimeException("Stub!");
    }

    public Locale getLanguage() {
        throw new RuntimeException("Stub!");
    }

    public Set<Locale> getAvailableLanguages() {
        throw new RuntimeException("Stub!");
    }

    public int isLanguageAvailable(final Locale loc) {
        throw new RuntimeException("Stub!");
    }

    public static class EngineInfo {
    }

    public static int getMaxSpeechInputLength() {
        throw new RuntimeException("Stub!");
    }

    public int speak(final String text, final int queueMode, final HashMap<String, String> params) {
        return 0;
    }
}