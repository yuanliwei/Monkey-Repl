package android.content.res;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.AnyRes;
import android.annotation.ArrayRes;
import android.annotation.AttrRes;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.StringRes;
import android.annotation.StyleRes;

public final class AssetManager implements AutoCloseable {
    public static final int ACCESS_RANDOM = 1;
    public static final int ACCESS_STREAMING = 2;
    public static final int ACCESS_BUFFER = 3;

    public static class Builder {
        public Builder addApkAssets(ApkAssets apkAssets) {
            throw new RuntimeException("Stub!");
        }

        public AssetManager build() {
            throw new RuntimeException("Stub!");
        }
    }

    public AssetManager() {
        throw new RuntimeException("Stub!");
    }

    public static AssetManager getSystem() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void close() {
        throw new RuntimeException("Stub!");
    }

    public void setApkAssets(@NonNull ApkAssets[] apkAssets, boolean invalidateCaches) {
        throw new RuntimeException("Stub!");
    }

    private void invalidateCachesLocked(int diff) {
        throw new RuntimeException("Stub!");
    }

    public @NonNull ApkAssets[] getApkAssets() {
        throw new RuntimeException("Stub!");
    }

    public int findCookieForPath(@NonNull String path) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public int addAssetPath(String path) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public int addAssetPathAsSharedLibrary(String path) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public int addOverlayPath(String path) {
        throw new RuntimeException("Stub!");
    }

    private int addAssetPathInternal(String path, boolean overlay, boolean appAsLib) {
        throw new RuntimeException("Stub!");
    }

    private void ensureValidLocked() {
        throw new RuntimeException("Stub!");
    }

    private void ensureOpenLocked() {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    CharSequence getResourceText(@StringRes int resId) {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    CharSequence getResourceBagText(@StringRes int resId, int bagEntryId) {
        throw new RuntimeException("Stub!");
    }

    int getResourceArraySize(@ArrayRes int resId) {
        throw new RuntimeException("Stub!");
    }

    int getResourceArray(@ArrayRes int resId, @NonNull int[] outData) {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    String[] getResourceStringArray(@ArrayRes int resId) {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    CharSequence[] getResourceTextArray(@ArrayRes int resId) {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    int[] getResourceIntArray(@ArrayRes int resId) {
        throw new RuntimeException("Stub!");
    }

    @AttrRes
    int[] getStyleAttributes(@StyleRes int resId) {
        throw new RuntimeException("Stub!");
    }

    void dumpTheme(long theme, int priority, String tag, String prefix) {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    String getResourceName(@AnyRes int resId) {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    String getResourcePackageName(@AnyRes int resId) {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    String getResourceTypeName(@AnyRes int resId) {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    String getResourceEntryName(@AnyRes int resId) {
        throw new RuntimeException("Stub!");
    }

    @AnyRes
    int getResourceIdentifier(@NonNull String name, @Nullable String defType, @Nullable String defPackage) {
        throw new RuntimeException("Stub!");
    }

    CharSequence getPooledStringForCookie(int cookie, int id) {
        throw new RuntimeException("Stub!");
    }

    public @NonNull InputStream open(@NonNull String fileName) throws IOException {
        return open(fileName, ACCESS_STREAMING);
    }

    public @NonNull InputStream open(@NonNull String fileName, int accessMode) throws IOException {
        return null;
    }

    public @NonNull AssetFileDescriptor openFd(@NonNull String fileName) throws IOException {
        return null;
    }

    public @Nullable String[] list(@NonNull String path) throws IOException {
        return null;
    }

    public @NonNull InputStream openNonAsset(@NonNull String fileName) throws IOException {
        return openNonAsset(0, fileName, ACCESS_STREAMING);
    }

    public @NonNull InputStream openNonAsset(@NonNull String fileName, int accessMode) throws IOException {
        return openNonAsset(0, fileName, accessMode);
    }

    public @NonNull InputStream openNonAsset(int cookie, @NonNull String fileName) throws IOException {
        return openNonAsset(cookie, fileName, ACCESS_STREAMING);
    }

    public @NonNull InputStream openNonAsset(int cookie, @NonNull String fileName, int accessMode) throws IOException {
        return null;
    }

    public @NonNull AssetFileDescriptor openNonAssetFd(@NonNull String fileName) throws IOException {
        return openNonAssetFd(0, fileName);
    }

    public @NonNull AssetFileDescriptor openNonAssetFd(int cookie, @NonNull String fileName) throws IOException {
        return null;
    }

    void xmlBlockGone(int id) {
        throw new RuntimeException("Stub!");
    }

    long createTheme() {
        throw new RuntimeException("Stub!");
    }

    void releaseTheme(long themePtr) {
        throw new RuntimeException("Stub!");
    }

    void applyStyleToTheme(long themePtr, @StyleRes int resId, boolean force) {
        throw new RuntimeException("Stub!");
    }

    @Override
    protected void finalize() throws Throwable {
        throw new IllegalStateException("not implemented! ");
    }

    public final class AssetInputStream extends InputStream {
        public final int getAssetInt() {
            throw new RuntimeException("Stub!");
        }

        public final long getNativeAsset() {
            throw new RuntimeException("Stub!");
        }

        private AssetInputStream(long assetNativePtr) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public final int read() throws IOException {
            ensureOpen();
            return 0;
        }

        @Override
        public final int read(@NonNull byte[] b) throws IOException {
            ensureOpen();
            return 0;
        }

        @Override
        public final int read(@NonNull byte[] b, int off, int len) throws IOException {
            ensureOpen();
            return len;
        }

        @Override
        public final long skip(long n) throws IOException {
            ensureOpen();
            return n;
        }

        @Override
        public final int available() throws IOException {
            return 0;
        }

        @Override
        public final boolean markSupported() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public final void mark(int readlimit) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public final void reset() throws IOException {
        }

        @Override
        public final void close() throws IOException {
        }

        @Override
        protected void finalize() throws Throwable {
            close();
        }

        private void ensureOpen() {
            throw new RuntimeException("Stub!");
        }
    }

    public boolean isUpToDate() {
        throw new RuntimeException("Stub!");
    }

    public String[] getLocales() {
        throw new RuntimeException("Stub!");
    }

    public String[] getNonSystemLocales() {
        throw new RuntimeException("Stub!");
    }

    public void setConfiguration(int mcc, int mnc, @Nullable String locale, int orientation, int touchscreen,
            int density, int keyboard, int keyboardHidden, int navigation, int screenWidth, int screenHeight,
            int smallestScreenWidthDp, int screenWidthDp, int screenHeightDp, int screenLayout, int uiMode,
            int colorMode, int majorVersion) {
        throw new RuntimeException("Stub!");
    }

    private void decRefsLocked(long id) {
        throw new RuntimeException("Stub!");
    }

    static native void nativeThemeCopy(long destThemePtr, long sourceThemePtr);

    static native void nativeThemeClear(long themePtr);

    public static native int getGlobalAssetCount();

    public static native String getAssetAllocations();

    public static native int getGlobalAssetManagerCount();
}