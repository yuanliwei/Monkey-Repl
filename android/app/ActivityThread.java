package android.app;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Context;
import android.content.IContentProvider;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.util.AndroidRuntimeException;
import android.util.ArrayMap;
import android.view.Window;
import android.view.WindowManager;

final class RemoteServiceException extends AndroidRuntimeException {

    private static final long serialVersionUID = 1L;

    public RemoteServiceException(String msg) {
        throw new RuntimeException("Stub!");
    }
}

public final class ActivityThread extends ClientTransactionHandler {
    public static final String TAG = "ActivityThread";
    static final boolean localLOGV = false;
    static final boolean DEBUG_MESSAGES = false;
    public static final boolean DEBUG_BROADCAST = false;
    public static final boolean DEBUG_CONFIGURATION = false;
    public static final boolean DEBUG_MEMORY_TRIM = false;
    public static final boolean DEBUG_ORDER = false;
    public static final int SERVICE_DONE_EXECUTING_ANON = 0;
    public static final int SERVICE_DONE_EXECUTING_START = 1;
    public static final int SERVICE_DONE_EXECUTING_STOP = 2;
    public static final long INVALID_PROC_STATE_SEQ = -1;
    public static final String PROC_START_SEQ_IDENT = "seq=";
    static volatile IPackageManager sPackageManager;
    final ApplicationThread mAppThread = new ApplicationThread();
    final Looper mLooper = Looper.myLooper();
    final H mH = new H();
    final ArrayMap<IBinder, ActivityClientRecord> mActivities = new ArrayMap<>();
    ActivityClientRecord mNewActivities = null;
    int mNumVisibleActivities = 0;
    final ArrayMap<IBinder, Service> mServices = new ArrayMap<>();
    AppBindData mBoundApplication;
    Profiler mProfiler;
    int mCurDefaultDisplayDpi;
    boolean mDensityCompatMode;
    Configuration mConfiguration;
    Configuration mCompatConfiguration;
    Application mInitialApplication;
    final ArrayList<Application> mAllApplications = new ArrayList<Application>();
    String mInstrumentationPackageName = null;
    String mInstrumentationAppDir = null;
    String[] mInstrumentationSplitAppDirs = null;
    String mInstrumentationLibDir = null;
    String mInstrumentedAppDir = null;
    String[] mInstrumentedSplitAppDirs = null;
    String mInstrumentedLibDir = null;
    boolean mSystemThread = false;
    boolean mSomeActivitiesChanged = false;
    boolean mUpdatingSystemConfig = false;
    boolean mHiddenApiWarningShown = false;
    final ArrayList<ActivityClientRecord> mRelaunchingActivities = new ArrayList<>();
    Configuration mPendingConfiguration = null;

    private static final class ProviderKey {

        public boolean equals(Object o) {
            throw new RuntimeException("Stub!");
        }

        public int hashCode() {
            throw new RuntimeException("Stub!");
        }
    }

    final ArrayMap<ProviderKey, ProviderClientRecord> mProviderMap = new ArrayMap<ProviderKey, ProviderClientRecord>();
    final ArrayMap<IBinder, ProviderClientRecord> mLocalProviders = new ArrayMap<IBinder, ProviderClientRecord>();
    final ArrayMap<ComponentName, ProviderClientRecord> mLocalProvidersByName = new ArrayMap<ComponentName, ProviderClientRecord>();
    final ArrayMap<ProviderKey, Object> mGetProviderLocks = new ArrayMap<>();
    boolean mPurgeIdlerScheduled = false;
    boolean mGcIdlerScheduled = false;
    static volatile Handler sMainThreadHandler; // set once in main()
    Bundle mCoreSettings = null;

    public static final class ActivityClientRecord {
        public IBinder token;
        int ident;
        Intent intent;
        String referrer;
        Bundle state;
        Activity activity;
        Window window;
        Activity parent;
        String embeddedID;
        boolean paused;
        boolean stopped;
        boolean hideForNow;
        Configuration newConfig;
        Configuration createdConfig;
        Configuration overrideConfig;
        ActivityClientRecord nextIdle;
        ProfilerInfo profilerInfo;
        ActivityInfo activityInfo;
        boolean startsNotResumed;
        public final boolean isForward;
        int pendingConfigChanges;
        Window mPendingRemoveWindow;
        WindowManager mPendingRemoveWindowManager;
        boolean mPreserveWindow;

        public ActivityClientRecord() {
            throw new RuntimeException("Stub!");
        }

        public int getLifecycleState() {
            throw new RuntimeException("Stub!");
        }

        public void setState(int newLifecycleState) {
            throw new RuntimeException("Stub!");
        }

        public boolean isPersistable() {
            throw new RuntimeException("Stub!");
        }

        public boolean isVisibleFromServer() {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }

        public String getStateString() {
            throw new RuntimeException("Stub!");
        }
    }

    final class ProviderClientRecord {
        String[] mNames;
        IContentProvider mProvider;
    }

    static final class CreateBackupAgentData {
        ApplicationInfo appInfo;
        int backupMode;

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    static final class CreateServiceData {
        IBinder token;
        ServiceInfo info;
        Intent intent;

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    static final class BindServiceData {
        IBinder token;
        Intent intent;
        boolean rebind;

        public String toString() {
            return "BindServiceData{token=" + token + " intent=" + intent + "}";
        }
    }

    static final class ServiceArgsData {
        IBinder token;
        boolean taskRemoved;
        int startId;
        int flags;
        Intent args;

        public String toString() {
            return "ServiceArgsData{token=" + token + " startId=" + startId + " args=" + args + "}";
        }
    }

    static final class AppBindData {
        String processName;
        ApplicationInfo appInfo;
        List<ProviderInfo> providers;
        ComponentName instrumentationName;
        Bundle instrumentationArgs;
        IInstrumentationWatcher instrumentationWatcher;
        IUiAutomationConnection instrumentationUiAutomationConnection;
        int debugMode;
        boolean enableBinderTracking;
        boolean trackAllocation;
        boolean restrictedBackupMode;
        boolean persistent;
        Configuration config;
        String buildSerial;
        ProfilerInfo initProfilerInfo;
        boolean autofillCompatibilityEnabled;

        public String toString() {
            return "AppBindData{appInfo=" + appInfo + "}";
        }
    }

    static final class Profiler {
        String profileFile;
        ParcelFileDescriptor profileFd;
        int samplingInterval;
        boolean autoStopProfiler;
        boolean streamingOutput;
        boolean profiling;
        boolean handlingProfiling;

        public void setProfiler(ProfilerInfo profilerInfo) {
            throw new RuntimeException("Stub!");
        }

        public void startProfiling() {
            throw new RuntimeException("Stub!");
        }

        public void stopProfiling() {
            throw new RuntimeException("Stub!");
        }
    }

    static final class DumpComponentInfo {
        ParcelFileDescriptor fd;
        IBinder token;
        String prefix;
        String[] args;
    }

    static final class ContextCleanupInfo {
        String what;
        String who;
    }

    static final class DumpHeapData {
        public boolean managed;
        public boolean mallocInfo;
        public boolean runGc;
        String path;
        ParcelFileDescriptor fd;
    }

    static final class UpdateCompatibilityData {
        String pkg;
    }

    static final class RequestAssistContextExtras {

    }

    private class ApplicationThread extends IApplicationThread.Stub {

    }

    public void updatePendingConfiguration(Configuration config) {
        throw new RuntimeException("Stub!");
    }

    public void updateProcessState(int processState, boolean fromIpc) {
        throw new RuntimeException("Stub!");
    }

    class H extends Handler {
        public static final int BIND_APPLICATION = 110;
        public static final int EXIT_APPLICATION = 111;
        public static final int RECEIVER = 113;
        public static final int CREATE_SERVICE = 114;
        public static final int SERVICE_ARGS = 115;
        public static final int STOP_SERVICE = 116;
        public static final int CONFIGURATION_CHANGED = 118;
        public static final int CLEAN_UP_CONTEXT = 119;
        public static final int GC_WHEN_IDLE = 120;
        public static final int BIND_SERVICE = 121;
        public static final int UNBIND_SERVICE = 122;
        public static final int DUMP_SERVICE = 123;
        public static final int LOW_MEMORY = 124;
        public static final int PROFILER_CONTROL = 127;
        public static final int CREATE_BACKUP_AGENT = 128;
        public static final int DESTROY_BACKUP_AGENT = 129;
        public static final int SUICIDE = 130;
        public static final int REMOVE_PROVIDER = 131;
        public static final int DISPATCH_PACKAGE_BROADCAST = 133;
        public static final int SCHEDULE_CRASH = 134;
        public static final int DUMP_HEAP = 135;
        public static final int DUMP_ACTIVITY = 136;
        public static final int SLEEPING = 137;
        public static final int SET_CORE_SETTINGS = 138;
        public static final int UPDATE_PACKAGE_COMPATIBILITY_INFO = 139;
        public static final int DUMP_PROVIDER = 141;
        public static final int UNSTABLE_PROVIDER_DIED = 142;
        public static final int REQUEST_ASSIST_CONTEXT_EXTRAS = 143;
        public static final int TRANSLUCENT_CONVERSION_COMPLETE = 144;
        public static final int INSTALL_PROVIDER = 145;
        public static final int ON_NEW_ACTIVITY_OPTIONS = 146;
        public static final int ENTER_ANIMATION_COMPLETE = 149;
        public static final int START_BINDER_TRACKING = 150;
        public static final int STOP_BINDER_TRACKING_AND_DUMP = 151;
        public static final int LOCAL_VOICE_INTERACTION_STARTED = 154;
        public static final int ATTACH_AGENT = 155;
        public static final int APPLICATION_INFO_CHANGED = 156;
        public static final int RUN_ISOLATED_ENTRY_POINT = 158;
        public static final int EXECUTE_TRANSACTION = 159;
        public static final int RELAUNCH_ACTIVITY = 160;
        public static final int PURGE_RESOURCES = 161;

        String codeToString(int code) {
            throw new RuntimeException("Stub!");
        }

        public void handleMessage(Message msg) {
            throw new RuntimeException("Stub!");
        }
    }

    public static ActivityThread currentActivityThread() {
        throw new RuntimeException("Stub!");
    }

    public static boolean isSystem() {
        throw new RuntimeException("Stub!");
    }

    public static String currentOpPackageName() {
        throw new RuntimeException("Stub!");
    }

    public static String currentPackageName() {
        throw new RuntimeException("Stub!");
    }

    public static String currentProcessName() {
        throw new RuntimeException("Stub!");
    }

    public static Application currentApplication() {
        throw new RuntimeException("Stub!");
    }

    public static IPackageManager getPackageManager() {
        throw new RuntimeException("Stub!");
    }

    final Handler getHandler() {
        throw new RuntimeException("Stub!");
    }

    ActivityThread() {
        throw new RuntimeException("Stub!");
    }

    public ApplicationThread getApplicationThread() {
        throw new RuntimeException("Stub!");
    }

    public boolean isProfiling() {
        throw new RuntimeException("Stub!");
    }

    public String getProfileFilePath() {
        throw new RuntimeException("Stub!");
    }

    public Looper getLooper() {
        throw new RuntimeException("Stub!");
    }

    public Executor getExecutor() {
        throw new RuntimeException("Stub!");
    }

    public Application getApplication() {
        throw new RuntimeException("Stub!");
    }

    public String getProcessName() {
        throw new RuntimeException("Stub!");
    }

    public ContextImpl getSystemContext() {
        throw new RuntimeException("Stub!");
    }

    public ContextImpl getSystemUiContext() {
        throw new RuntimeException("Stub!");
    }

    public void installSystemApplicationInfo(ApplicationInfo info, ClassLoader classLoader) {
        throw new RuntimeException("Stub!");
    }

    void scheduleGcIdler() {
        throw new RuntimeException("Stub!");
    }

    void unscheduleGcIdler() {
        throw new RuntimeException("Stub!");
    }

    void schedulePurgeIdler() {
        throw new RuntimeException("Stub!");
    }

    void unschedulePurgeIdler() {
        throw new RuntimeException("Stub!");
    }

    void doGcIfNeeded() {
        throw new RuntimeException("Stub!");
    }

    static void printRow(PrintWriter pw, String format, Object... objs) {
        throw new RuntimeException("Stub!");
    }

    public final ActivityInfo resolveActivityInfo(Intent intent) {
        throw new RuntimeException("Stub!");
    }

    public final Activity getActivity(IBinder token) {
        throw new RuntimeException("Stub!");
    }

    public ActivityClientRecord getActivityClient(IBinder token) {
        throw new RuntimeException("Stub!");
    }

    public final void sendActivityResult(IBinder token, String id, int requestCode, int resultCode, Intent data) {
        throw new RuntimeException("Stub!");
    }

    void sendMessage(int what, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void handleRequestAssistContextExtras(RequestAssistContextExtras cmd) {
        throw new RuntimeException("Stub!");
    }

    public void handleTranslucentConversionComplete(IBinder token, boolean drawComplete) {
        throw new RuntimeException("Stub!");
    }

    public void handleInstallProvider(ProviderInfo info) {
        throw new RuntimeException("Stub!");
    }

    public void handleMultiWindowModeChanged(IBinder token, boolean isInMultiWindowMode, Configuration overrideConfig) {
        throw new RuntimeException("Stub!");
    }

    public void handlePictureInPictureModeChanged(IBinder token, boolean isInPipMode, Configuration overrideConfig) {
        throw new RuntimeException("Stub!");
    }

    public static Intent getIntentBeingBroadcast() {
        throw new RuntimeException("Stub!");
    }

    public ActivityClientRecord performResumeActivity(IBinder token, boolean finalStateRequest, String reason) {
        throw new RuntimeException("Stub!");
    }

    static final void cleanUpPendingRemoveWindows(ActivityClientRecord r, boolean force) {
        throw new RuntimeException("Stub!");
    }

    public void handleResumeActivity(IBinder token, boolean finalStateRequest, boolean isForward, String reason) {
        throw new RuntimeException("Stub!");
    }

    final void performUserLeavingActivity(ActivityClientRecord r) {
        throw new RuntimeException("Stub!");
    }

    final void performStopActivity(IBinder token, boolean saveState, String reason) {
        throw new RuntimeException("Stub!");
    }

    public void performRestartActivity(IBinder token, boolean start) {
        throw new RuntimeException("Stub!");
    }

    public void handleWindowVisibility(IBinder token, boolean show) {
        throw new RuntimeException("Stub!");
    }

    ActivityClientRecord performDestroyActivity(IBinder token, boolean finishing, int configChanges,
            boolean getNonConfigInstance, String reason) {
        throw new RuntimeException("Stub!");
    }

    public void handleDestroyActivity(IBinder token, boolean finishing, int configChanges, boolean getNonConfigInstance,
            String reason) {
        throw new RuntimeException("Stub!");
    }

    void scheduleRelaunchActivity(IBinder token) {
        throw new RuntimeException("Stub!");
    }

    ArrayList<ComponentCallbacks2> collectComponentCallbacks(boolean allActivities, Configuration newConfig) {
        throw new RuntimeException("Stub!");
    }

    public final void applyConfigurationToResources(Configuration config) {
        throw new RuntimeException("Stub!");
    }

    final Configuration applyCompatConfiguration(int displayDensity) {
        throw new RuntimeException("Stub!");
    }

    public void handleConfigurationChanged(Configuration config) {
        throw new RuntimeException("Stub!");
    }

    public void handleSystemApplicationInfoChanged(ApplicationInfo ai) {
        throw new RuntimeException("Stub!");
    }

    void handleApplicationInfoChanged(final ApplicationInfo ai) {
        throw new RuntimeException("Stub!");
    }

    static void freeTextLayoutCachesIfNeeded(int configDiff) {
        throw new RuntimeException("Stub!");
    }

    public void handleActivityConfigurationChanged(IBinder activityToken, Configuration overrideConfig, int displayId) {
        throw new RuntimeException("Stub!");
    }

    final void handleProfilerControl(boolean start, ProfilerInfo profilerInfo, int profileType) {
        throw new RuntimeException("Stub!");
    }

    public void stopProfiling() {
        throw new RuntimeException("Stub!");
    }

    static void handleDumpHeap(DumpHeapData dhd) {
        throw new RuntimeException("Stub!");
    }

    final void handleDispatchPackageBroadcast(int cmd, String[] packages) {
        throw new RuntimeException("Stub!");
    }

    final void handleLowMemory() {
        throw new RuntimeException("Stub!");
    }

    public final IContentProvider acquireProvider(Context c, String auth, int userId, boolean stable) {
        throw new RuntimeException("Stub!");
    }

    public final IContentProvider acquireExistingProvider(Context c, String auth, int userId, boolean stable) {
        throw new RuntimeException("Stub!");
    }

    public final boolean releaseProvider(IContentProvider provider, boolean stable) {
        throw new RuntimeException("Stub!");
    }

    final void handleUnstableProviderDied(IBinder provider, boolean fromClient) {
        throw new RuntimeException("Stub!");
    }

    final void handleUnstableProviderDiedLocked(IBinder provider, boolean fromClient) {
        throw new RuntimeException("Stub!");
    }

    final void appNotRespondingViaProvider(IBinder provider) {
        throw new RuntimeException("Stub!");
    }

    public static ActivityThread systemMain() {
        throw new RuntimeException("Stub!");
    }

    public static void updateHttpProxy(Context context) {
        throw new RuntimeException("Stub!");
    }

    public final void installSystemProviders(List<ProviderInfo> providers) {
        throw new RuntimeException("Stub!");
    }

    public int getIntCoreSetting(String key, int defaultValue) {
        throw new RuntimeException("Stub!");
    }

    public static void main(String[] args) {
        throw new RuntimeException("Stub!");
    }
}