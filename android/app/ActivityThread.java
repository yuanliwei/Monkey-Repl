package android.app;

import java.util.ArrayList;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.IPackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.ArrayMap;
import android.view.Window;
import android.view.WindowManager;

public final class ActivityThread {
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
    final Looper mLooper = Looper.myLooper();
    final ArrayMap<IBinder, ActivityClientRecord> mActivities = new ArrayMap<>();
    ActivityClientRecord mNewActivities = null;
    int mNumVisibleActivities = 0;
    final ArrayMap<IBinder, Service> mServices = new ArrayMap<>();
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
    final ArrayList<ActivityClientRecord> mRelaunchingActivities = new ArrayList<>();
    Configuration mPendingConfiguration = null;
    boolean mPurgeIdlerScheduled = false;
    boolean mGcIdlerScheduled = false;
    Bundle mCoreSettings = null;

    public static final class ActivityClientRecord {
        public IBinder token;
        int ident;
        Intent intent;
        String referrer;
        Activity activity;
        Window window;
        Activity parent;
        String embeddedID;
        boolean paused;
        boolean stopped;
        boolean hideForNow;
        Configuration newConfig;
        Configuration createdConfig;
        ActivityClientRecord nextIdle;
        ProfilerInfo profilerInfo;
        ActivityInfo activityInfo;
        boolean startsNotResumed;
        int pendingConfigChanges;
        Window mPendingRemoveWindow;
        WindowManager mPendingRemoveWindowManager;
    }
}