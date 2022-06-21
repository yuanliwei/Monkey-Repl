/*
 * Copyright 2007, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.commands.monkey;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.app.ActivityManager;
import android.app.ActivityManagerNative;
import android.app.IActivityController;
import android.app.IActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageManager;
import android.os.Build;
import android.os.Process;
import android.os.ServiceManager;
import android.os.StrictMode;
import android.view.IWindowManager;
import android.view.Surface;

/**
 * Application that injects random key events and other actions into the system.
 */
public class Monkey {

    private IActivityManager mAm;

    private IWindowManager mWm;

    private IPackageManager mPm;

    // information on the current activity.
    public static Intent currentIntent;

    public static String currentPackage;

    /**
     * Monitor operations happening in the system.
     */
    private class ActivityController extends IActivityController.Stub {

        public boolean activityStarting(Intent intent, String pkg) {
            Logger.out.println("    // activityStarting(" + pkg + ")");
            currentPackage = pkg;
            currentIntent = intent;
            return true;
        }

        public boolean activityResuming(String pkg) {
            StrictMode.ThreadPolicy savedPolicy = StrictMode.allowThreadDiskWrites();
            Logger.out.println("    // activityResuming(" + pkg + ")");
            currentPackage = pkg;
            StrictMode.setThreadPolicy(savedPolicy);
            return true;
        }

        public boolean appCrashed(String processName, int pid, String shortMsg, String longMsg, long timeMillis,
                String stackTrace) {
            StrictMode.ThreadPolicy savedPolicy = StrictMode.allowThreadDiskWrites();
            Logger.err.println("// CRASH: " + processName + " (pid " + pid + ")");
            Logger.err.println("// Short Msg: " + shortMsg);
            Logger.err.println("// Long Msg: " + longMsg);
            Logger.err.println("// Build Label: " + Build.FINGERPRINT);
            Logger.err.println("// Build Changelist: " + Build.VERSION.INCREMENTAL);
            Logger.err.println("// Build Time: " + Build.TIME);
            Logger.err.println("// " + stackTrace.replace("\n", "\n// "));
            StrictMode.setThreadPolicy(savedPolicy);
            return false;
        }

        public int appEarlyNotResponding(String processName, int pid, String annotation) {
            StrictMode.ThreadPolicy savedPolicy = StrictMode.allowThreadDiskWrites();
            Logger.out.println("    // appEarlyNotResponding(" + processName + ")");
            StrictMode.setThreadPolicy(savedPolicy);
            return 0;
        }

        public int appNotResponding(String processName, int pid, String processStats) {
            StrictMode.ThreadPolicy savedPolicy = StrictMode.allowThreadDiskWrites();
            Logger.err.println("// NOT RESPONDING: " + processName + " (pid " + pid + ")");
            Logger.err.println(processStats);
            StrictMode.setThreadPolicy(savedPolicy);
            return -1;
        }

        public int systemNotResponding(String message) {
            StrictMode.ThreadPolicy savedPolicy = StrictMode.allowThreadDiskWrites();
            Logger.err.println("// WATCHDOG: " + message);
            StrictMode.setThreadPolicy(savedPolicy);
            return -1;
        }
    }

    public static void main(String[] args_) {
        // Set the process name showing in "ps" or "top"
        Argument args = new Argument(args_);
        Process.setArgV0(args.name());
        int resultCode = new Monkey().run(args);
        System.exit(resultCode);
    }

    /**
     * Run the command!
     *
     * @param args The command-line arguments
     * @return Returns a posix-style result code. 0 for no error.
     */
    private int run(Argument args) {

        if ("repl".equals(args.type())) {
            Logger.out.println(MonkeyUtils.getHelp());
        }

        if (!getSystemInterfaces()) {
            Logger.err.println("getSystemInterfaces error!");
            return -3;
        }

        if (args.queryView()) {
            MonkeySourceViews.setup();
        }

        if (args.activityController()) {
            try {
                mAm.setActivityController(new ActivityController(), false);
            } catch (Throwable e) {
                mAm.setActivityController(new ActivityController());
            }
        }

        IOWrapper ioWrapper = null;
        if ("udp".equals(args.type())) {
            ioWrapper = IOWrapper.IOWrapperUDP.build(args);
        } else if ("tcp".equals(args.type())) {
            ioWrapper = IOWrapper.IOWrapperTCP.build(args);
        } else if ("http".equals(args.type())) {
            ioWrapper = IOWrapper.IOWrapperHTTP.build(args);
        } else { // repl
            ioWrapper = IOWrapper.IOWrapperStdio.build(args);
        }

        if (ioWrapper == null) {
            return -4;
        }

        MonkeyEventSource mEventSource = new MonkeySource(mAm, ioWrapper);
        try {
            runMonkeyCycles(mEventSource);
        } finally {
            // Release the rotation lock if it's still held and restore the
            // original orientation.
            new MonkeyRotationEvent(Surface.ROTATION_0, false).injectEvent(mWm, mAm, 0);
        }

        return 0;
    }

    /**
     * Attach to the required system interfaces.
     *
     * @return Returns true if all system interfaces were available.
     */
    private boolean getSystemInterfaces() {

        try {
            mAm = ActivityManager.getService();
        } catch (Throwable e) {
            mAm = ActivityManagerNative.getDefault();
        }

        if (mAm == null) {
            Logger.err.println("** Error: Unable to connect to activity manager; is the system " + "running?");
            return false;
        }

        mWm = IWindowManager.Stub.asInterface(ServiceManager.getService(Context.WINDOW_SERVICE));
        if (mWm == null) {
            Logger.err.println("** Error: Unable to connect to window manager; is the system " + "running?");
            return false;
        }

        mPm = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
        if (mPm == null) {
            Logger.err.println("** Error: Unable to connect to package manager; is the system " + "running?");
            return false;
        }

        return true;
    }

    /**
     * Run mCount cycles and see if we hit any crashers.
     * <p>
     *
     * @return Returns the last cycle which executed. If the value == mCount, no
     *         errors detected.
     */
    private int runMonkeyCycles(MonkeyEventSource mEventSource) {
        while (true) {
            try {
                MonkeyEvent ev = mEventSource.getNextEvent();
                if (ev != null) {
                    int injectCode = ev.injectEvent(mWm, mAm, 0);
                    if (injectCode == MonkeyEvent.INJECT_FAIL) {
                        Logger.out.println("    // Injection Failed");
                    } else if (injectCode == MonkeyEvent.INJECT_ERROR_REMOTE_EXCEPTION) {
                        Logger.err.println("** Error: RemoteException while injecting event.");
                    } else if (injectCode == MonkeyEvent.INJECT_ERROR_SECURITY_EXCEPTION) {
                        Logger.err.println("** Error: SecurityException while injecting event.");
                    }
                } else {
                    break;
                }
            } catch (RuntimeException e) {
                Logger.error("** Error: A RuntimeException occurred:", e);
            }
        }
        return 0; // eventCounter;
    }

    public static class Argument {
        Map<String, String> map = new HashMap<>();

        public Argument(String[] args) {
            System.out.println(args);
            System.out.println(Arrays.toString(args));
            String key = null;
            for (int i = 0; i < args.length; i++) {
                String item = args[i];
                if (key == null) {
                    key = item;
                    continue;
                } else {
                    map.put(key, item);
                    key = null;
                }
            }
        }

        @SuppressWarnings("unchecked")
        private <T> T getValue(String key, T defaultValue) {
            String v = map.get(key);
            if (v == null) {
                return defaultValue;
            } else {
                if (defaultValue instanceof Boolean) {
                    return (T) Boolean.valueOf(v);
                }
                if (defaultValue instanceof Integer) {
                    return (T) Integer.valueOf(v);
                }
                return (T) v;
            }
        }

        public String type() {
            return getValue("--type", "repl");
        }

        public String commandType() {
            return getValue("--command_type", "text");
        }

        public String name() {
            return getValue("--name", "monkey-repl");
        }

        public int port() {
            return getValue("--port", 5678);
        }

        public String allowIpAddress() {
            return getValue("--allow_ip_address", "all");
        }

        public boolean queryView() {
            return getValue("--query_view", true);
        }

        public boolean activityController() {
            return getValue("--activity_controller", true);
        }
    }
}
