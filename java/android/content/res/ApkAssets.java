package android.content.res;

import java.io.FileDescriptor;
import java.io.IOException;

import android.annotation.NonNull;

public final class ApkAssets {

    public static @NonNull ApkAssets loadFromPath(@NonNull String path) throws IOException {
        return null;
    }

    public static @NonNull ApkAssets loadFromPath(@NonNull String path, boolean system) throws IOException {
        return null;
    }

    public static @NonNull ApkAssets loadFromPath(@NonNull String path, boolean system, boolean forceSharedLibrary)
            throws IOException {
        return null;
    }

    public static @NonNull ApkAssets loadFromFd(@NonNull FileDescriptor fd, @NonNull String friendlyName,
            boolean system, boolean forceSharedLibrary) throws IOException {
        return new ApkAssets(fd, friendlyName, system, forceSharedLibrary);
    }

    public static @NonNull ApkAssets loadOverlayFromPath(@NonNull String idmapPath, boolean system) throws IOException {
        return null;
    }

    private ApkAssets(@NonNull String path, boolean system, boolean forceSharedLib, boolean overlay)
            throws IOException {
    }

    private ApkAssets(@NonNull FileDescriptor fd, @NonNull String friendlyName, boolean system, boolean forceSharedLib)
            throws IOException {

    }

    public @NonNull String getAssetPath() {
        throw new RuntimeException("Stub!");
    }

    CharSequence getStringFromPool(int idx) {
        throw new RuntimeException("Stub!");
    }

    public boolean isUpToDate() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        return "ApkAssets{path=" + getAssetPath() + "}";
    }

    @Override
    protected void finalize() throws Throwable {
    }

}
