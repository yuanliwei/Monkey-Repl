
package android.content;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

import android.annotation.SystemApi;
import android.annotation.TestApi;
import android.annotation.UnsupportedAppUsage;
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

public class ContextWrapper extends Context {
    @UnsupportedAppUsage
    Context mBase;

    public ContextWrapper(Context base) {
        throw new RuntimeException("Stub!");
    }

    protected void attachBaseContext(Context base) {
        throw new RuntimeException("Stub!");
    }

    public Context getBaseContext() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public AssetManager getAssets() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Resources getResources() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public PackageManager getPackageManager() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public ContentResolver getContentResolver() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Looper getMainLooper() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Executor getMainExecutor() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Context getApplicationContext() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean moveDatabaseFrom(Context sourceContext, String name) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean deleteDatabase(String name) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public File getDatabasePath(String name) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String[] databaseList() {
        throw new RuntimeException("Stub!");
    }

    @Override
    @Deprecated
    public Drawable getWallpaper() {
        throw new RuntimeException("Stub!");
    }

    @Override
    @Deprecated
    public Drawable peekWallpaper() {
        throw new RuntimeException("Stub!");
    }

    @Override
    @Deprecated
    public int getWallpaperDesiredMinimumWidth() {
        throw new RuntimeException("Stub!");
    }

    @Override
    @Deprecated
    public int getWallpaperDesiredMinimumHeight() {
        throw new RuntimeException("Stub!");
    }

    @Override
    @Deprecated
    public void setWallpaper(Bitmap bitmap) throws IOException {
        mBase.setWallpaper(bitmap);
    }

    @Override
    @Deprecated
    public void setWallpaper(InputStream data) throws IOException {
        mBase.setWallpaper(data);
    }

    @Override
    @Deprecated
    public void clearWallpaper() throws IOException {
        mBase.clearWallpaper();
    }

    @Override
    public void startActivity(Intent intent) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void startActivityAsUser(Intent intent, UserHandle user) {
        throw new RuntimeException("Stub!");
    }

    public void startActivityForResult(String who, Intent intent, int requestCode, Bundle options) {
        throw new RuntimeException("Stub!");
    }

    public boolean canStartActivityForResult() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void startActivityAsUser(Intent intent, Bundle options, UserHandle user) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void startActivities(Intent[] intents) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void startActivities(Intent[] intents, Bundle options) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int startActivitiesAsUser(Intent[] intents, Bundle options, UserHandle userHandle) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void enforceCallingOrSelfPermission(String permission, String message) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void revokeUriPermission(Uri uri, int modeFlags) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public String getPackageName() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void revokeUriPermission(String targetPackage, Uri uri, int modeFlags) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags, IBinder callerToken) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int checkCallingUriPermission(Uri uri, int modeFlags) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int checkUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid,
            int modeFlags) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void enforceCallingUriPermission(Uri uri, int modeFlags, String message) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void enforceUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid,
            int modeFlags, String message) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
        return mBase.createPackageContext(packageName, flags);
    }

    @Override
    public Context createPackageContextAsUser(String packageName, int flags, UserHandle user)
            throws PackageManager.NameNotFoundException {
        return mBase.createPackageContextAsUser(packageName, flags, user);
    }

    @Override
    @UnsupportedAppUsage
    public Context createApplicationContext(ApplicationInfo application, int flags)
            throws PackageManager.NameNotFoundException {
        return mBase.createApplicationContext(application, flags);
    }

    @Override
    public Context createContextForSplit(String splitName) throws PackageManager.NameNotFoundException {
        return mBase.createContextForSplit(splitName);
    }

    @Override
    public int getUserId() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Context createDisplayContext(Display display) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean isRestricted() {
        throw new RuntimeException("Stub!");
    }

    @Override
    @UnsupportedAppUsage
    public Display getDisplay() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void updateDisplay(int displayId) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Context createDeviceProtectedStorageContext() {
        throw new RuntimeException("Stub!");
    }

    @SystemApi
    @Override
    public Context createCredentialProtectedStorageContext() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean isDeviceProtectedStorage() {
        throw new RuntimeException("Stub!");
    }

    @SystemApi
    @Override
    public boolean isCredentialProtectedStorage() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean canLoadUnsafeResources() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public IBinder getActivityToken() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public IApplicationThread getIApplicationThread() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Handler getMainThreadHandler() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean isAutofillCompatibilityEnabled() {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    @Override
    public void setAutofillCompatibilityEnabled(boolean autofillCompatEnabled) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Context createConfigurationContext(javax.security.auth.login.Configuration overrideConfiguration) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendBroadcast(Intent intent) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendBroadcast(Intent intent, String receiverPermission) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendBroadcastMultiplePermissions(Intent intent, String[] receiverPermissions) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendBroadcastAsUserMultiplePermissions(Intent intent, UserHandle user, String[] receiverPermissions) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendBroadcast(Intent intent, String receiverPermission, Bundle options) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendBroadcast(Intent intent, String receiverPermission, int appOp) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendOrderedBroadcast(Intent intent, String receiverPermission) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission, Bundle options) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission, int appOp) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendStickyBroadcast(Intent intent) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void removeStickyBroadcast(Intent intent) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendStickyBroadcastAsUser(Intent intent, UserHandle user) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void sendStickyBroadcastAsUser(Intent intent, UserHandle user, Bundle options) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void removeStickyBroadcastAsUser(Intent intent, UserHandle user) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public ComponentName startService(Intent service) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public ComponentName startForegroundServiceAsUser(Intent service, UserHandle user) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean stopService(Intent service) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public ComponentName startServiceAsUser(Intent service, UserHandle user) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean stopServiceAsUser(Intent service, UserHandle user) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean startInstrumentation(ComponentName className, String profileFile, Bundle arguments) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Object getSystemService(String name) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String getSystemServiceName(Class<?> serviceClass) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int checkPermission(String permission, int pid, int uid, IBinder callerToken) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int checkCallingPermission(String permission) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int checkCallingOrSelfPermission(String permission) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int checkSelfPermission(String permission) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void enforcePermission(String permission, int pid, int uid, String message) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void enforceCallingPermission(String permission, String message) {
        throw new RuntimeException("Stub!");
    }
}
