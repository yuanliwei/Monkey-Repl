package android.app;

import java.io.FileDescriptor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import android.Manifest;
import android.annotation.DrawableRes;
import android.annotation.IntDef;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.RequiresPermission;
import android.annotation.SystemApi;
import android.annotation.SystemService;
import android.annotation.TestApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.GraphicBuffer;
import android.graphics.Rect;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SystemService(Context.ACTIVITY_SERVICE)
public class ActivityManager {
    public static final int BUGREPORT_OPTION_FULL = 0;
    public static final int BUGREPORT_OPTION_INTERACTIVE = 1;
    public static final int BUGREPORT_OPTION_REMOTE = 2;
    public static final int BUGREPORT_OPTION_WEAR = 3;
    public static final int BUGREPORT_OPTION_TELEPHONY = 4;
    public static final int BUGREPORT_OPTION_WIFI = 5;
    public static final String META_HOME_ALTERNATE = "android.app.home.alternate";
    // NOTE: Before adding a new start result, please reference the defined ranges
    // to ensure the
    // result is properly categorized.
    private static final int FIRST_START_FATAL_ERROR_CODE = -100;
    private static final int FIRST_START_SUCCESS_CODE = 0;
    private static final int FIRST_START_NON_FATAL_ERROR_CODE = 100;
    public static final int START_VOICE_HIDDEN_SESSION = FIRST_START_FATAL_ERROR_CODE;
    public static final int START_VOICE_NOT_ACTIVE_SESSION = FIRST_START_FATAL_ERROR_CODE + 1;
    public static final int START_NOT_CURRENT_USER_ACTIVITY = FIRST_START_FATAL_ERROR_CODE + 2;
    public static final int START_NOT_VOICE_COMPATIBLE = FIRST_START_FATAL_ERROR_CODE + 3;
    public static final int START_CANCELED = FIRST_START_FATAL_ERROR_CODE + 4;
    public static final int START_NOT_ACTIVITY = FIRST_START_FATAL_ERROR_CODE + 5;
    public static final int START_PERMISSION_DENIED = FIRST_START_FATAL_ERROR_CODE + 6;
    public static final int START_FORWARD_AND_REQUEST_CONFLICT = FIRST_START_FATAL_ERROR_CODE + 7;
    public static final int START_CLASS_NOT_FOUND = FIRST_START_FATAL_ERROR_CODE + 8;
    public static final int START_INTENT_NOT_RESOLVED = FIRST_START_FATAL_ERROR_CODE + 9;
    public static final int START_ASSISTANT_HIDDEN_SESSION = FIRST_START_FATAL_ERROR_CODE + 10;
    public static final int START_ASSISTANT_NOT_ACTIVE_SESSION = FIRST_START_FATAL_ERROR_CODE + 11;
    public static final int START_SUCCESS = FIRST_START_SUCCESS_CODE;
    public static final int START_RETURN_INTENT_TO_CALLER = FIRST_START_SUCCESS_CODE + 1;
    public static final int START_TASK_TO_FRONT = FIRST_START_SUCCESS_CODE + 2;
    public static final int START_DELIVERED_TO_TOP = FIRST_START_SUCCESS_CODE + 3;
    public static final int START_SWITCHES_CANCELED = FIRST_START_NON_FATAL_ERROR_CODE;
    public static final int START_RETURN_LOCK_TASK_MODE_VIOLATION = FIRST_START_NON_FATAL_ERROR_CODE + 1;
    public static final int START_ABORTED = FIRST_START_NON_FATAL_ERROR_CODE + 2;
    public static final int START_FLAG_ONLY_IF_NEEDED = 1 << 0;
    public static final int START_FLAG_DEBUG = 1 << 1;
    public static final int START_FLAG_TRACK_ALLOCATION = 1 << 2;
    public static final int START_FLAG_NATIVE_DEBUGGING = 1 << 3;
    public static final int BROADCAST_SUCCESS = 0;
    public static final int BROADCAST_STICKY_CANT_HAVE_PERMISSION = -1;
    public static final int BROADCAST_FAILED_USER_STOPPED = -2;
    public static final int INTENT_SENDER_BROADCAST = 1;
    public static final int INTENT_SENDER_ACTIVITY = 2;
    public static final int INTENT_SENDER_ACTIVITY_RESULT = 3;
    public static final int INTENT_SENDER_SERVICE = 4;
    public static final int INTENT_SENDER_FOREGROUND_SERVICE = 5;
    public static final int USER_OP_SUCCESS = 0;
    public static final int USER_OP_UNKNOWN_USER = -1;
    public static final int USER_OP_IS_CURRENT = -2;
    public static final int USER_OP_ERROR_IS_SYSTEM = -3;
    public static final int USER_OP_ERROR_RELATED_USERS_CANNOT_STOP = -4;
    public static final int PROCESS_STATE_UNKNOWN = -1;
    public static final int PROCESS_STATE_PERSISTENT = 0;
    public static final int PROCESS_STATE_PERSISTENT_UI = 1;
    public static final int PROCESS_STATE_TOP = 2;
    public static final int PROCESS_STATE_FOREGROUND_SERVICE = 3;
    public static final int PROCESS_STATE_BOUND_FOREGROUND_SERVICE = 4;
    public static final int PROCESS_STATE_IMPORTANT_FOREGROUND = 5;
    public static final int PROCESS_STATE_IMPORTANT_BACKGROUND = 6;
    public static final int PROCESS_STATE_TRANSIENT_BACKGROUND = 7;
    public static final int PROCESS_STATE_BACKUP = 8;
    public static final int PROCESS_STATE_SERVICE = 9;
    public static final int PROCESS_STATE_RECEIVER = 10;
    public static final int PROCESS_STATE_TOP_SLEEPING = 11;
    public static final int PROCESS_STATE_HEAVY_WEIGHT = 12;
    public static final int PROCESS_STATE_HOME = 13;
    public static final int PROCESS_STATE_LAST_ACTIVITY = 14;
    public static final int PROCESS_STATE_CACHED_ACTIVITY = 15;
    public static final int PROCESS_STATE_CACHED_ACTIVITY_CLIENT = 16;
    public static final int PROCESS_STATE_CACHED_RECENT = 17;
    public static final int PROCESS_STATE_CACHED_EMPTY = 18;
    public static final int PROCESS_STATE_NONEXISTENT = 19;

    // NOTE: If PROCESS_STATEs are added, then new fields must be added
    // to frameworks/base/core/proto/android/app/enums.proto and the following
    // method must
    // be updated to correctly map between them.
    // However, if the current ActivityManager values are merely modified, no update
    // should be made
    // to enums.proto, to which values can only be added but never modified. Note
    // that the proto
    // versions do NOT have the ordering restrictions of the ActivityManager process
    // state.
    public static final int processStateAmToProto(int amInt) {
        throw new RuntimeException("Stub!");
    }

    public static final int MIN_PROCESS_STATE = PROCESS_STATE_PERSISTENT;
    public static final int MAX_PROCESS_STATE = PROCESS_STATE_NONEXISTENT;

    public static final boolean isProcStateBackground(int procState) {
        throw new RuntimeException("Stub!");
    }

    public static final int ASSIST_CONTEXT_BASIC = 0;
    public static final int ASSIST_CONTEXT_FULL = 1;
    public static final int ASSIST_CONTEXT_AUTOFILL = 2;
    public static final int UID_OBSERVER_PROCSTATE = 1 << 0;
    public static final int UID_OBSERVER_GONE = 1 << 1;
    public static final int UID_OBSERVER_IDLE = 1 << 2;
    public static final int UID_OBSERVER_ACTIVE = 1 << 3;
    public static final int UID_OBSERVER_CACHED = 1 << 4;
    public static final int APP_START_MODE_NORMAL = 0;
    public static final int APP_START_MODE_DELAYED = 1;
    public static final int APP_START_MODE_DELAYED_RIGID = 2;
    public static final int APP_START_MODE_DISABLED = 3;
    public static final int LOCK_TASK_MODE_NONE = 0;
    public static final int LOCK_TASK_MODE_LOCKED = 1;
    public static final int LOCK_TASK_MODE_PINNED = 2;

    public static final boolean isStartResultSuccessful(int result) {
        throw new RuntimeException("Stub!");
    }

    public static final boolean isStartResultFatalError(int result) {
        throw new RuntimeException("Stub!");
    }

    public static final int COMPAT_MODE_ALWAYS = -1;
    public static final int COMPAT_MODE_NEVER = -2;
    public static final int COMPAT_MODE_UNKNOWN = -3;
    public static final int COMPAT_MODE_DISABLED = 0;
    public static final int COMPAT_MODE_ENABLED = 1;
    public static final int COMPAT_MODE_TOGGLE = 2;

    @TestApi
    public static class StackId {
        private StackId() {
            throw new RuntimeException("Stub!");
        }

        public static final int INVALID_STACK_ID = -1;
    }

    @TestApi
    public static final int SPLIT_SCREEN_CREATE_MODE_TOP_OR_LEFT = 0;
    @TestApi
    public static final int SPLIT_SCREEN_CREATE_MODE_BOTTOM_OR_RIGHT = 1;
    public static final int RESIZE_MODE_SYSTEM = 0;
    public static final int RESIZE_MODE_PRESERVE_WINDOW = (0x1 << 0);
    public static final int RESIZE_MODE_FORCED = (0x1 << 1);
    public static final int RESIZE_MODE_SYSTEM_SCREEN_ROTATION = RESIZE_MODE_PRESERVE_WINDOW;
    public static final int RESIZE_MODE_USER = RESIZE_MODE_PRESERVE_WINDOW;
    public static final int RESIZE_MODE_USER_FORCED = RESIZE_MODE_PRESERVE_WINDOW | RESIZE_MODE_FORCED;

    public int getFrontActivityScreenCompatMode() {
        throw new RuntimeException("Stub!");
    }

    public void setFrontActivityScreenCompatMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getPackageScreenCompatMode(String packageName) {
        throw new RuntimeException("Stub!");
    }

    public void setPackageScreenCompatMode(String packageName, int mode) {
        throw new RuntimeException("Stub!");
    }

    public boolean getPackageAskScreenCompat(String packageName) {
        throw new RuntimeException("Stub!");
    }

    public void setPackageAskScreenCompat(String packageName, boolean ask) {
        throw new RuntimeException("Stub!");
    }

    public int getMemoryClass() {
        throw new RuntimeException("Stub!");
    }

    static public int staticGetMemoryClass() {
        throw new RuntimeException("Stub!");
    }

    public int getLargeMemoryClass() {
        throw new RuntimeException("Stub!");
    }

    static public int staticGetLargeMemoryClass() {
        throw new RuntimeException("Stub!");
    }

    public boolean isLowRamDevice() {
        throw new RuntimeException("Stub!");
    }

    public static boolean isLowRamDeviceStatic() {
        throw new RuntimeException("Stub!");
    }

    public static boolean isSmallBatteryDevice() {
        throw new RuntimeException("Stub!");
    }

    static public boolean isHighEndGfx() {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    public long getTotalRam() {
        throw new RuntimeException("Stub!");
    }

    static public int getMaxRecentTasksStatic() {
        throw new RuntimeException("Stub!");
    }

    static public int getDefaultAppRecentsLimitStatic() {
        throw new RuntimeException("Stub!");
    }

    static public int getMaxAppRecentsLimitStatic() {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    static public boolean supportsMultiWindow(Context context) {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    static public boolean supportsSplitScreenMultiWindow(Context context) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public static int getMaxNumPictureInPictureActions() {
        throw new RuntimeException("Stub!");
    }

    public static class TaskDescription implements Parcelable {
        public static final String ATTR_TASKDESCRIPTION_PREFIX = "task_description_";

        @Deprecated
        public TaskDescription(String label, Bitmap icon, int colorPrimary) {
            throw new RuntimeException("Stub!");
        }

        public TaskDescription(String label, @DrawableRes int iconRes, int colorPrimary) {
            throw new RuntimeException("Stub!");
        }

        @Deprecated
        public TaskDescription(String label, Bitmap icon) {
            throw new RuntimeException("Stub!");
        }

        public TaskDescription(String label, @DrawableRes int iconRes) {
            throw new RuntimeException("Stub!");
        }

        public TaskDescription(String label) {
            throw new RuntimeException("Stub!");
        }

        public TaskDescription() {
            throw new RuntimeException("Stub!");
        }

        public TaskDescription(String label, Bitmap bitmap, int iconRes, String iconFilename, int colorPrimary,
                int colorBackground, int statusBarColor, int navigationBarColor) {
            throw new RuntimeException("Stub!");
        }

        public TaskDescription(TaskDescription td) {
            throw new RuntimeException("Stub!");
        }

        public void copyFrom(TaskDescription other) {
            throw new RuntimeException("Stub!");
        }

        public void copyFromPreserveHiddenFields(TaskDescription other) {
            throw new RuntimeException("Stub!");
        }

        public void setLabel(String label) {
            throw new RuntimeException("Stub!");
        }

        public void setPrimaryColor(int primaryColor) {
            throw new RuntimeException("Stub!");
        }

        public void setBackgroundColor(int backgroundColor) {
            throw new RuntimeException("Stub!");
        }

        public void setStatusBarColor(int statusBarColor) {
            throw new RuntimeException("Stub!");
        }

        public void setNavigationBarColor(int navigationBarColor) {
            throw new RuntimeException("Stub!");
        }

        public void setIcon(Bitmap icon) {
            throw new RuntimeException("Stub!");
        }

        public void setIcon(int iconRes) {
            throw new RuntimeException("Stub!");
        }

        public void setIconFilename(String iconFilename) {
            throw new RuntimeException("Stub!");
        }

        public String getLabel() {
            throw new RuntimeException("Stub!");
        }

        public Bitmap getIcon() {
            throw new RuntimeException("Stub!");
        }

        @TestApi
        public int getIconResource() {
            throw new RuntimeException("Stub!");
        }

        @TestApi
        public String getIconFilename() {
            throw new RuntimeException("Stub!");
        }

        public Bitmap getInMemoryIcon() {
            throw new RuntimeException("Stub!");
        }

        public static Bitmap loadTaskDescriptionIcon(String iconFilename, int userId) {
            if (iconFilename != null) {
                throw new RuntimeException("Stub!");
            }
            return null;
        }

        public int getPrimaryColor() {
            throw new RuntimeException("Stub!");
        }

        public int getBackgroundColor() {
            throw new RuntimeException("Stub!");
        }

        public int getStatusBarColor() {
            throw new RuntimeException("Stub!");
        }

        public int getNavigationBarColor() {
            throw new RuntimeException("Stub!");
        }

        public void restoreFromXml(String attrName, String attrValue) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            throw new RuntimeException("Stub!");
        }

        public void readFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public static final Creator<TaskDescription> CREATOR = new Creator<TaskDescription>() {
            public TaskDescription createFromParcel(Parcel source) {
                throw new RuntimeException("Stub!");
            }

            public TaskDescription[] newArray(int size) {
                throw new RuntimeException("Stub!");
            }
        };

        @Override
        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    public static class RecentTaskInfo implements Parcelable {
        public int id;
        public int persistentId;
        public Intent baseIntent;
        public ComponentName origActivity;
        @Nullable
        public ComponentName realActivity;
        public CharSequence description;
        public int stackId;
        public int userId;
        public long firstActiveTime;
        public long lastActiveTime;
        public TaskDescription taskDescription;
        public int affiliatedTaskId;
        public int affiliatedTaskColor;
        public ComponentName baseActivity;
        public ComponentName topActivity;
        public int numActivities;
        public Rect bounds;
        public boolean supportsSplitScreenMultiWindow;
        public int resizeMode;
        final public Configuration configuration = new Configuration();

        public RecentTaskInfo() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            throw new RuntimeException("Stub!");
        }

        public void readFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public static final Creator<RecentTaskInfo> CREATOR = new Creator<RecentTaskInfo>() {
            public RecentTaskInfo createFromParcel(Parcel source) {
                throw new RuntimeException("Stub!");
            }

            public RecentTaskInfo[] newArray(int size) {
                throw new RuntimeException("Stub!");
            }
        };
    }

    public static final int RECENT_WITH_EXCLUDED = 0x0001;
    public static final int RECENT_IGNORE_UNAVAILABLE = 0x0002;

    @Deprecated
    public List<RecentTaskInfo> getRecentTasks(int maxNum, int flags) throws SecurityException {
        throw new RuntimeException("Stub!");
    }

    public static class RunningTaskInfo implements Parcelable {
        public int id;
        public int stackId;
        public ComponentName baseActivity;
        public ComponentName topActivity;
        public Bitmap thumbnail;
        public CharSequence description;
        public int numActivities;
        public int numRunning;
        public long lastActiveTime;
        public boolean supportsSplitScreenMultiWindow;
        public int resizeMode;
        final public Configuration configuration = new Configuration();

        public RunningTaskInfo() {
            throw new RuntimeException("Stub!");
        }

        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        public void writeToParcel(Parcel dest, int flags) {
            throw new RuntimeException("Stub!");
        }

        public void readFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public static final Creator<RunningTaskInfo> CREATOR = new Creator<RunningTaskInfo>() {
            public RunningTaskInfo createFromParcel(Parcel source) {
                throw new RuntimeException("Stub!");
            }

            public RunningTaskInfo[] newArray(int size) {
                throw new RuntimeException("Stub!");
            }
        };
    }

    public List<ActivityManager.AppTask> getAppTasks() {
        throw new RuntimeException("Stub!");
    }

    public int addAppTask(@NonNull Activity activity, @NonNull Intent intent, @Nullable TaskDescription description,
            @NonNull Bitmap thumbnail) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public List<RunningTaskInfo> getRunningTasks(int maxNum) throws SecurityException {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    @RequiresPermission(android.Manifest.permission.MANAGE_ACTIVITY_STACKS)
    public void setTaskWindowingMode(int taskId, int windowingMode, boolean toTop) throws SecurityException {
    }

    @TestApi
    @RequiresPermission(android.Manifest.permission.MANAGE_ACTIVITY_STACKS)
    public void setTaskWindowingModeSplitScreenPrimary(int taskId, int createMode, boolean toTop, boolean animate,
            Rect initialBounds, boolean showRecents) throws SecurityException {
    }

    @TestApi
    @RequiresPermission(android.Manifest.permission.MANAGE_ACTIVITY_STACKS)
    public void resizeStack(int stackId, Rect bounds) throws SecurityException {
    }

    @TestApi
    @RequiresPermission(android.Manifest.permission.MANAGE_ACTIVITY_STACKS)
    public void removeStacksInWindowingModes(int[] windowingModes) throws SecurityException {
    }

    @TestApi
    @RequiresPermission(android.Manifest.permission.MANAGE_ACTIVITY_STACKS)
    public void removeStacksWithActivityTypes(int[] activityTypes) throws SecurityException {
    }

    public static class TaskSnapshot implements Parcelable {
        // Whether this snapshot is a down-sampled version of the full resolution, used
        // mainly for
        // low-ram devices
        // Whether or not the snapshot is a real snapshot or an app-theme generated
        // snapshot due to
        // the task having a secure window or having previews disabled
        public TaskSnapshot(GraphicBuffer snapshot, int orientation, Rect contentInsets, boolean reducedResolution,
                float scale, boolean isRealSnapshot, int windowingMode, int systemUiVisibility, boolean isTranslucent) {
            throw new RuntimeException("Stub!");
        }

        public GraphicBuffer getSnapshot() {
            throw new RuntimeException("Stub!");
        }

        public int getOrientation() {
            throw new RuntimeException("Stub!");
        }

        public Rect getContentInsets() {
            throw new RuntimeException("Stub!");
        }

        public boolean isReducedResolution() {
            throw new RuntimeException("Stub!");
        }

        public boolean isRealSnapshot() {
            throw new RuntimeException("Stub!");
        }

        public boolean isTranslucent() {
            throw new RuntimeException("Stub!");
        }

        public int getWindowingMode() {
            throw new RuntimeException("Stub!");
        }

        public int getSystemUiVisibility() {
            throw new RuntimeException("Stub!");
        }

        public float getScale() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public String toString() {
            throw new RuntimeException("Stub!");
        }

        public static final Creator<TaskSnapshot> CREATOR = new Creator<TaskSnapshot>() {
            public TaskSnapshot createFromParcel(Parcel source) {
                throw new RuntimeException("Stub!");
            }

            public TaskSnapshot[] newArray(int size) {
                throw new RuntimeException("Stub!");
            }
        };
    }

    @IntDef(flag = true, prefix = { "MOVE_TASK_" }, value = { MOVE_TASK_WITH_HOME, MOVE_TASK_NO_USER_ACTION, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface MoveTaskFlags {
    }

    public static final int MOVE_TASK_WITH_HOME = 0x00000001;
    public static final int MOVE_TASK_NO_USER_ACTION = 0x00000002;

    @RequiresPermission(android.Manifest.permission.REORDER_TASKS)
    public void moveTaskToFront(int taskId, @MoveTaskFlags int flags) {
        throw new RuntimeException("Stub!");
    }

    @RequiresPermission(android.Manifest.permission.REORDER_TASKS)
    public void moveTaskToFront(int taskId, @MoveTaskFlags int flags, Bundle options) {
        throw new RuntimeException("Stub!");
    }

    public static class RunningServiceInfo implements Parcelable {
        public ComponentName service;
        public int pid;
        public int uid;
        public String process;
        public boolean foreground;
        public long activeSince;
        public boolean started;
        public int clientCount;
        public int crashCount;
        public long lastActivityTime;
        public long restarting;
        public static final int FLAG_STARTED = 1 << 0;
        public static final int FLAG_FOREGROUND = 1 << 1;
        public static final int FLAG_SYSTEM_PROCESS = 1 << 2;
        public static final int FLAG_PERSISTENT_PROCESS = 1 << 3;
        public int flags;
        public String clientPackage;
        public int clientLabel;

        public RunningServiceInfo() {
            throw new RuntimeException("Stub!");
        }

        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        public void writeToParcel(Parcel dest, int flags) {
            throw new RuntimeException("Stub!");
        }

        public void readFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public static final Creator<RunningServiceInfo> CREATOR = new Creator<RunningServiceInfo>() {
            public RunningServiceInfo createFromParcel(Parcel source) {
                throw new RuntimeException("Stub!");
            }

            public RunningServiceInfo[] newArray(int size) {
                throw new RuntimeException("Stub!");
            }
        };
    }

    public static class MemoryInfo implements Parcelable {
        public long availMem;
        public long totalMem;
        public long threshold;
        public boolean lowMemory;
        public long hiddenAppThreshold;
        public long secondaryServerThreshold;
        public long visibleAppThreshold;
        public long foregroundAppThreshold;

        public MemoryInfo() {
            throw new RuntimeException("Stub!");
        }

        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        public void writeToParcel(Parcel dest, int flags) {
            throw new RuntimeException("Stub!");
        }

        public void readFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public static final Creator<MemoryInfo> CREATOR = new Creator<MemoryInfo>() {
            public MemoryInfo createFromParcel(Parcel source) {
                throw new RuntimeException("Stub!");
            }

            public MemoryInfo[] newArray(int size) {
                throw new RuntimeException("Stub!");
            }
        };
    }

    public void getMemoryInfo(MemoryInfo outInfo) {
        throw new RuntimeException("Stub!");
    }

    public static class StackInfo implements Parcelable {
        public int stackId;
        public Rect bounds = new Rect();
        public int[] taskIds;
        public String[] taskNames;
        public Rect[] taskBounds;
        public int[] taskUserIds;
        public ComponentName topActivity;
        public int displayId;
        public int userId;
        public boolean visible;
        // Index of the stack in the display's stack list, can be used for comparison of
        // stack order
        public int position;
        final public Configuration configuration = new Configuration();

        @Override
        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            throw new RuntimeException("Stub!");
        }

        public void readFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public static final Creator<StackInfo> CREATOR = new Creator<StackInfo>() {
            @Override
            public StackInfo createFromParcel(Parcel source) {
                throw new RuntimeException("Stub!");
            }

            @Override
            public StackInfo[] newArray(int size) {
                throw new RuntimeException("Stub!");
            }
        };

        public StackInfo() {
            throw new RuntimeException("Stub!");
        }

        public String toString(String prefix) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    public boolean clearApplicationUserData() {
        throw new RuntimeException("Stub!");
    }

    public void clearGrantedUriPermissions(String packageName) {
        throw new RuntimeException("Stub!");
    }

    public static class ProcessErrorStateInfo implements Parcelable {
        public static final int NO_ERROR = 0;
        public static final int CRASHED = 1;
        public static final int NOT_RESPONDING = 2;
        public int condition;
        public String processName;
        public int pid;
        public int uid;
        public String tag;
        public String shortMsg;
        public String longMsg;
        public String stackTrace;
        public byte[] crashData = null;

        public ProcessErrorStateInfo() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            throw new RuntimeException("Stub!");
        }

        public void readFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public static final Creator<ProcessErrorStateInfo> CREATOR = new Creator<ProcessErrorStateInfo>() {
            public ProcessErrorStateInfo createFromParcel(Parcel source) {
                throw new RuntimeException("Stub!");
            }

            public ProcessErrorStateInfo[] newArray(int size) {
                throw new RuntimeException("Stub!");
            }
        };
    }

    public List<ProcessErrorStateInfo> getProcessesInErrorState() {
        throw new RuntimeException("Stub!");
    }

    public static class RunningAppProcessInfo implements Parcelable {
        public String processName;
        public int pid;
        public int uid;
        public String pkgList[];
        public static final int FLAG_CANT_SAVE_STATE = 1 << 0;
        public static final int FLAG_PERSISTENT = 1 << 1;
        public static final int FLAG_HAS_ACTIVITIES = 1 << 2;
        public int flags;
        public int lastTrimLevel;

        @IntDef(prefix = { "IMPORTANCE_" }, value = { IMPORTANCE_FOREGROUND, IMPORTANCE_FOREGROUND_SERVICE,
                IMPORTANCE_TOP_SLEEPING, IMPORTANCE_VISIBLE, IMPORTANCE_PERCEPTIBLE, IMPORTANCE_CANT_SAVE_STATE,
                IMPORTANCE_SERVICE, IMPORTANCE_CACHED, IMPORTANCE_GONE, })
        @Retention(RetentionPolicy.SOURCE)
        public @interface Importance {
        }

        public static final int IMPORTANCE_FOREGROUND = 100;
        public static final int IMPORTANCE_FOREGROUND_SERVICE = 125;
        @Deprecated
        public static final int IMPORTANCE_TOP_SLEEPING_PRE_28 = 150;
        public static final int IMPORTANCE_VISIBLE = 200;
        public static final int IMPORTANCE_PERCEPTIBLE_PRE_26 = 130;
        public static final int IMPORTANCE_PERCEPTIBLE = 230;
        public static final int IMPORTANCE_CANT_SAVE_STATE_PRE_26 = 170;
        public static final int IMPORTANCE_SERVICE = 300;
        public static final int IMPORTANCE_TOP_SLEEPING = 325;
        public static final int IMPORTANCE_CANT_SAVE_STATE = 350;
        public static final int IMPORTANCE_CACHED = 400;
        public static final int IMPORTANCE_BACKGROUND = IMPORTANCE_CACHED;
        @Deprecated
        public static final int IMPORTANCE_EMPTY = 500;
        public static final int IMPORTANCE_GONE = 1000;

        public static @Importance int procStateToImportance(int procState) {
            throw new RuntimeException("Stub!");
        }

        public static @Importance int procStateToImportanceForClient(int procState, Context clientContext) {
            throw new RuntimeException("Stub!");
        }

        public static @Importance int procStateToImportanceForTargetSdk(int procState, int targetSdkVersion) {
            final int importance = procStateToImportance(procState);
            // For pre O apps, convert to the old, wrong values.
            if (targetSdkVersion < VERSION_CODES.O) {
                throw new RuntimeException("Stub!");
            }
            return importance;
        }

        public static int importanceToProcState(@Importance int importance) {
            throw new RuntimeException("Stub!");
        }

        public @Importance int importance;
        public int lru;
        public static final int REASON_UNKNOWN = 0;
        public static final int REASON_PROVIDER_IN_USE = 1;
        public static final int REASON_SERVICE_IN_USE = 2;
        public int importanceReasonCode;
        public int importanceReasonPid;
        public ComponentName importanceReasonComponent;
        public int importanceReasonImportance;
        public int processState;

        public RunningAppProcessInfo() {
            throw new RuntimeException("Stub!");
        }

        public RunningAppProcessInfo(String pProcessName, int pPid, String pArr[]) {
            throw new RuntimeException("Stub!");
        }

        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        public void writeToParcel(Parcel dest, int flags) {
            throw new RuntimeException("Stub!");
        }

        public void readFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public static final Creator<RunningAppProcessInfo> CREATOR = new Creator<RunningAppProcessInfo>() {
            public RunningAppProcessInfo createFromParcel(Parcel source) {
                throw new RuntimeException("Stub!");
            }

            public RunningAppProcessInfo[] newArray(int size) {
                throw new RuntimeException("Stub!");
            }
        };
    }

    public List<ApplicationInfo> getRunningExternalApplications() {
        throw new RuntimeException("Stub!");
    }

    public boolean isBackgroundRestricted() {
        throw new RuntimeException("Stub!");
    }

    public boolean setProcessMemoryTrimLevel(String process, int userId, int level) {
        throw new RuntimeException("Stub!");
    }

    public List<RunningAppProcessInfo> getRunningAppProcesses() {
        throw new RuntimeException("Stub!");
    }

    @SystemApi
    @TestApi
    public interface OnUidImportanceListener {
        void onUidImportance(int uid, @RunningAppProcessInfo.Importance int importance);
    }

    @Deprecated
    public void restartPackage(String packageName) {
        throw new RuntimeException("Stub!");
    }

    @SystemApi
    @RequiresPermission(Manifest.permission.KILL_UID)
    public void killUid(int uid, String reason) {
        throw new RuntimeException("Stub!");
    }

    public void forceStopPackageAsUser(String packageName, int userId) {
        throw new RuntimeException("Stub!");
    }

    @SystemApi
    @RequiresPermission(Manifest.permission.FORCE_STOP_PACKAGES)
    public void forceStopPackage(String packageName) {
        throw new RuntimeException("Stub!");
    }

    public ConfigurationInfo getDeviceConfigurationInfo() {
        throw new RuntimeException("Stub!");
    }

    public int getLauncherLargeIconDensity() {
        throw new RuntimeException("Stub!");
    }

    public int getLauncherLargeIconSize() {
        throw new RuntimeException("Stub!");
    }

    static int getLauncherLargeIconSizeInner(Context context) {
        throw new RuntimeException("Stub!");
    }

    public static boolean isUserAMonkey() {
        throw new RuntimeException("Stub!");
    }

    public static boolean isRunningInTestHarness() {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    public void alwaysShowUnsupportedCompileSdkWarning(ComponentName activity) {
        throw new RuntimeException("Stub!");
    }

    public static int checkComponentPermission(String permission, int uid, int owningUid, boolean exported) {
        throw new RuntimeException("Stub!");
    }

    public static int checkUidPermission(String permission, int uid) {
        throw new RuntimeException("Stub!");
    }

    public static int handleIncomingUser(int callingPid, int callingUid, int userId, boolean allowAll,
            boolean requireFull, String name, String callerPackage) {
        throw new RuntimeException("Stub!");
    }

    @SystemApi
    @RequiresPermission(anyOf = { "android.permission.INTERACT_ACROSS_USERS",
            "android.permission.INTERACT_ACROSS_USERS_FULL" })
    public static int getCurrentUser() {
        throw new RuntimeException("Stub!");
    }

    public boolean switchUser(int userid) {
        throw new RuntimeException("Stub!");
    }

    public static final int FLAG_OR_STOPPED = 1 << 0;
    public static final int FLAG_AND_LOCKED = 1 << 1;
    public static final int FLAG_AND_UNLOCKED = 1 << 2;
    public static final int FLAG_AND_UNLOCKING_OR_UNLOCKED = 1 << 3;

    public boolean isUserRunning(int userId) {
        throw new RuntimeException("Stub!");
    }

    public boolean isVrModePackageEnabled(ComponentName component) {
        throw new RuntimeException("Stub!");
    }

    public static void dumpPackageStateStatic(FileDescriptor fd, String packageName) {
        throw new RuntimeException("Stub!");
    }

    public static boolean isSystemReady() {
        throw new RuntimeException("Stub!");
    }

    public static void broadcastStickyIntent(Intent intent, int userId) {
        throw new RuntimeException("Stub!");
    }

    public static void broadcastStickyIntent(Intent intent, int appOp, int userId) {
        throw new RuntimeException("Stub!");
    }

    public static IActivityManager getService() {
        throw new RuntimeException("Stub!");
    }

    public void setWatchHeapLimit(long pssSize) {
        throw new RuntimeException("Stub!");
    }

    public static final String ACTION_REPORT_HEAP_LIMIT = "android.app.action.REPORT_HEAP_LIMIT";

    public void clearWatchHeapLimit() {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public boolean isInLockTaskMode() {
        throw new RuntimeException("Stub!");
    }

    public int getLockTaskModeState() {
        throw new RuntimeException("Stub!");
    }

    public static void setVrThread(int tid) {
        throw new RuntimeException("Stub!");
    }

    @RequiresPermission(Manifest.permission.RESTRICTED_VR_ACCESS)
    public static void setPersistentVrThread(int tid) {
        throw new RuntimeException("Stub!");
    }

    public static class AppTask {
        public void finishAndRemoveTask() {
            throw new RuntimeException("Stub!");
        }

        public RecentTaskInfo getTaskInfo() {
            throw new RuntimeException("Stub!");
        }

        public void moveToFront() {
            throw new RuntimeException("Stub!");
        }

        public void startActivity(Context context, Intent intent, Bundle options) {
            throw new RuntimeException("Stub!");
        }

        public void setExcludeFromRecents(boolean exclude) {
            throw new RuntimeException("Stub!");
        }
    }
}