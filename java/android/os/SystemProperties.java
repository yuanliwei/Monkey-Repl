
package android.os;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.SystemApi;
import android.annotation.TestApi;

@SystemApi
@TestApi
public class SystemProperties {

    public static final int PROP_NAME_MAX = Integer.MAX_VALUE;

    public static final int PROP_VALUE_MAX = 91;

    @NonNull
    @SystemApi
    public static String get(@NonNull String key) {
        throw new RuntimeException("Stub!");
    }

    @NonNull
    @SystemApi
    @TestApi
    public static String get(@NonNull String key, @Nullable String def) {
        throw new RuntimeException("Stub!");
    }

    @SystemApi
    public static int getInt(@NonNull String key, int def) {
        throw new RuntimeException("Stub!");
    }

    @SystemApi
    public static long getLong(@NonNull String key, long def) {
        throw new RuntimeException("Stub!");
    }

    @SystemApi
    public static boolean getBoolean(@NonNull String key, boolean def) {
        throw new RuntimeException("Stub!");
    }

    public static void set(@NonNull String key, @Nullable String val) {
        throw new RuntimeException("Stub!");
    }

    public static void addChangeCallback(@NonNull Runnable callback) {
        throw new RuntimeException("Stub!");
    }

    public static void reportSyspropChanged() {
        throw new RuntimeException("Stub!");
    }

    private SystemProperties() {
        throw new RuntimeException("Stub!");
    }
}
