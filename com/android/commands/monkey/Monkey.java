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
import android.util.Base64;
import android.view.IWindowManager;
import android.view.Surface;

/**
 * Application that injects random key events and other actions into the system.
 */
public class Monkey {

    private IActivityManager mAm;

    private IWindowManager mWm;

    private IPackageManager mPm;

    MonkeyEventSource mEventSource;

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

    public static void main(String[] args) {
        // Set the process name showing in "ps" or "top"
        Process.setArgV0("com.android.commands.monkey");
        int resultCode = (new Monkey()).run(args);
        System.exit(resultCode);
    }

    /**
     * Run the command!
     *
     * @param args The command-line arguments
     * @return Returns a posix-style result code. 0 for no error.
     */
    private int run(String[] args) {
        if (!getSystemInterfaces()) {
            return -3;
        }

        mEventSource = new MonkeySourceShell();
        printHelp();

        try {
            runMonkeyCycles();
        } finally {
            // Release the rotation lock if it's still held and restore the
            // original orientation.
            new MonkeyRotationEvent(Surface.ROTATION_0, false).injectEvent(mWm, mAm, 0);
        }

        mAm.setActivityController(null, true);

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

        try {
            mAm.setActivityController(new ActivityController(), true);
        } catch (Throwable e) {
            mAm.setActivityController(new ActivityController());
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
    private int runMonkeyCycles() {
        try {
            while (true) {
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
            }
        } catch (RuntimeException e) {
            Logger.error("** Error: A RuntimeException occurred:", e);
        }
        return 0; // eventCounter;
    }

    void printHelp() {
        String help = "IyBtb25rZXktcmVwbAoKICAgICAgICAjIyMjIOaPj+i/sAogICAgICAgIG1vbmtleS1yZXBsIDog5LiA5Liq6YCa6L+HIHVzYiDmjqfliLYgQW5kcm9pZCDorr7lpIfnmoToh6rliqjljJblt6XlhbfvvIzkv67mlLnoh6ogW0FuZHJvaWQgbW9ua2V5XShodHRwczovL2FuZHJvaWQuZ29vZ2xlc291cmNlLmNvbS9wbGF0Zm9ybS9kZXZlbG9wbWVudC8rL3JlZnMvaGVhZHMvbWFzdGVyL2NtZHMvbW9ua2V5KeOAggogICAgICAgIAogICAgICAgICMjIyMg54m55oCnCiAgICAgICAgLSDkuI3pnIDopoEgYHJvb3RgIOadg+mZkAogICAgICAgIC0g6I635Y+W5bGP5bmV5o6n5Lu25L+h5oGvCiAgICAgICAgLSDmiKrlsY/jgIHlj5boibIKICAgICAgICAtIOaooeaLn+aMiemUrgogICAgICAgIC0g5a2X56ym6L6T5YWl44CB5Lit5paH6L6T5YWlCiAgICAgICAgLSDlrp7ml7blk43lupTlkb3ku6Tmk43kvZwKICAgICAgICAtIOWPr+S7pemAmui/h+iEmuacrOiwg+eUqOS9v+eUqOaWueS+vwogICAgICAgIC0g5Y+v5Lul6I635Y+WIGB3ZWJ2aWV3YCDkuK3nmoTmjqfku7YKICAgICAgICAKICAgICAgICAjIyMjIOS9v+eUqOaWueW8jwogICAgICAgIC0g5L2/55SoIHVzYiDov57mjqXmiYvmnLoKICAgICAgICAtIOaJk+W8gOaJi+acuueahCB1c2Ig6LCD6K+V5qih5byPCiAgICAgICAgLSDov5vlhaUgc3RhcnQg55uu5b2V77yM6L+Q6KGMIHN0YXJ0LmNtZCDov5vlhaXkuqTkupLnqpflj6MKICAgICAgICAtIOi+k+WFpSBgcXVlcnl2aWV3IGdldGxvY2F0aW9uYCDmjIkgYGVudGVyYAogICAgICAgIC0g6L6T5YWlIGBxdWVyeXZpZXcgZ2V0dHJlZSB0ZXh0YCDmjIkgYGVudGVyYAogICAgICAgIC0g6YCA5Ye6IGBxdWl0YCDmjIkgYGVudGVyYAogICAgICAgIAogICAgICAgICMjIyMg6ISa5pysCiAgICAgICAgLSDlj4LogIMgZGVtbyDnm67lvZUKICAgICAgICAKICAgICAgICAjIyMjIOWKn+iDvea4heWNlQogICAgICAgIC0g5qih5ouf5oyJ6ZSu5LqL5Lu2CiAgICAgICAgLSDmqKHmi5/lsY/luZXop6bmkbjkuovku7YKICAgICAgICAgICAgLSDngrnlh7sKICAgICAgICAgICAgLSDmjInkuIsKICAgICAgICAgICAgLSDnp7vliqggCiAgICAgICAgICAgIC0g5oqs6LW3CiAgICAgICAgLSDkvJHnnKDmjIflrprml7bpl7QgCiAgICAgICAgLSDovpPlhaXlrZfnrKbkuLLmlofmnKwKICAgICAgICAtIOWkjeWItuaWh+acrOWIsOWJqui0tOadvwogICAgICAgICAgICAtIOaZrumAmuaWh+acrAogICAgICAgICAgICAtIGJhc2U2NOe8lueggeeahOaWh+acrAogICAgICAgICAgICAtIFVSTCDnvJbnoIHnmoTmlofmnKwKICAgICAgICAtIOi+k+WFpSoq5Lit5paH5a2X56ymKioKICAgICAgICAgICAgLSDpgJrov4flpI3liLblkozmqKHmi5/mjInplK7lj6/ku6Xlrp7njrDovpPlhaXkuK3mloflrZfnrKbnmoTlip/og70KICAgICAgICAgICAgLSBgY29weSBiYXNlNjQgNUxpdDVwYUg1YTJYNTZ5bWAKICAgICAgICAgICAgLSBgcHJlc3MgcGFzdGVgCiAgICAgICAgLSDojrflj5bmjqfku7bkv6Hmga8KICAgICAgICAgICAgLSDkvY3nva4KICAgICAgICAgICAgLSDmlofmnKwKICAgICAgICAtIOiOt+WPluagkeW9oue7k+aehOeahOeVjOmdouaOp+S7tuS/oeaBrwogICAgICAgICAgICAtIOaWh+acrOagvOW8jwogICAgICAgICAgICAtIGpzb24g5qC85byPCiAgICAgICAgICAgIC0g6I635Y+W55WM6Z2i5YWo6YOo5o6n5Lu25qCR5b2i57uT5p6ECiAgICAgICAgICAgIC0g6I635Y+W5oyH5a6a5o6n5Lu25LiL55qE5o6n5Lu25qCR5b2i57uT5p6ECiAgICAgICAgLSDmiKrlsY/lip/og70KICAgICAgICAgICAgLSDmiKrlj5bmlbTkuKrlsY/luZUKICAgICAgICAgICAgLSDmiKrlj5bmjIflrprljLrln5/nmoTlsY/luZUKICAgICAgICAgICAgLSDnvKnmlL7miKrlj5bnmoTlm77niYcKICAgICAgICAgICAgLSDojrflj5blsY/luZXmjIflrprlnZDmoIfnmoTlg4/ntKDpopzoibIKICAgICAgICAtIOiOt+WPluezu+e7n+S/oeaBrwogICAgICAgICAgICAtIGBidWlsZC5ib2FyZGAKICAgICAgICAgICAgLSBgYnVpbGQuYnJhbmRgCiAgICAgICAgICAgIC0gYGJ1aWxkLmRldmljZWAKICAgICAgICAgICAgLSBgYnVpbGQuZGlzcGxheWAKICAgICAgICAgICAgLSBgYnVpbGQuZmluZ2VycHJpbnRgCiAgICAgICAgICAgIC0gYGJ1aWxkLmhvc3RgCiAgICAgICAgICAgIC0gYGJ1aWxkLmlkYAogICAgICAgICAgICAtIGBidWlsZC5tb2RlbGAKICAgICAgICAgICAgLSBgYnVpbGQucHJvZHVjdGAKICAgICAgICAgICAgLSBgYnVpbGQudGFnc2AKICAgICAgICAgICAgLSBgYnVpbGQuYnJhbmRgCiAgICAgICAgICAgIC0gYGJ1aWxkLnR5cGVgCiAgICAgICAgICAgIC0gYGJ1aWxkLnVzZXJgCiAgICAgICAgICAgIC0gYGJ1aWxkLmNwdV9hYmlgCiAgICAgICAgICAgIC0gYGJ1aWxkLm1hbnVmYWN0dXJlcmAKICAgICAgICAtIOWbnuaYvuWtl+espuS4sgogICAgICAgICAgICAtIOeUqOS6juWcqOiEmuacrOS4reWQjOatpeaTjeS9nAogICAgICAgIC0g6I635Y+W55WM6Z2i5piv5ZCm5pyJ5pu05pawCiAgICAgICAgCiAgICAgICAgIyMjIyDlip/og73kvb/nlKjnpLrkvosKICAgICAgICAtIOaooeaLn+aMiemUruS6i+S7tgogICAgICAgICAgICAtIGBwcmVzcyBLRVlDT0RFX0VOVEVSYAogICAgICAgICAgICAtIGBwcmVzcyBLRVlDT0RFX1BBU1RFYAogICAgICAgICAgICAtIGBwcmVzcyBLRVlDT0RFX1VQYAogICAgICAgICAgICAtIGBwcmVzcyBLRVlDT0RFX0RPV05gCiAgICAgICAgLSDmqKHmi5/lsY/luZXop6bmkbjkuovku7YKICAgICAgICAgICAgLSBgdG91Y2ggW2Rvd258dXB8bW92ZV0gW3hdIFt5XWAKICAgICAgICAgICAgLSDngrnlh7sKICAgICAgICAgICAgICAgIC0gYHRhcCB4IHlgCiAgICAgICAgICAgICAgICAtIGB0YXAgMzAgNTBgCiAgICAgICAgICAgIC0g5oyJ5LiLCiAgICAgICAgICAgICAgICAtIGB0b3VjaCBkb3duIHggeWAKICAgICAgICAgICAgICAgIC0gYHRvdWNoIGRvd24gMzAgNTBgCiAgICAgICAgICAgIC0g56e75YqoIAogICAgICAgICAgICAgICAgLSBgdG91Y2ggbW92ZSB4IHlgCiAgICAgICAgICAgICAgICAtIGB0b3VjaCBtb3ZlIDUwIDYwYAogICAgICAgICAgICAtIOaKrOi1twogICAgICAgICAgICAgICAgLSBgdG91Y2ggdXAgeCB5YAogICAgICAgICAgICAgICAgLSBgdG91Y2ggdXAgNzAgODBgCiAgICAgICAgLSDkvJHnnKDmjIflrprml7bpl7QgCiAgICAgICAgICAgIC0gYHNsZWVwIDEwMjRgCiAgICAgICAgLSDovpPlhaXlrZfnrKbkuLLmlofmnKwKICAgICAgICAgICAgLSBgdHlwZSAxMjM0YAogICAgICAgICAgICAtIGB0eXBlIHN0cmluZ2AKICAgICAgICAgICAgLSBgdHlwZSB1c2VybmFtZWAKICAgICAgICAtIOWkjeWItuaWh+acrOWIsOWJqui0tOadvwogICAgICAgICAgICAtIGBjb3B5IFt0ZXh0fGJhc2U2NHx1cmxlbmNvZGVdIHN0cmluZ2AKICAgICAgICAgICAgLSDmma7pgJrmlofmnKwKICAgICAgICAgICAgICAgIC0gYGNvcHkgdGV4dCBzdHJpbmdgCiAgICAgICAgICAgICAgICAtIGBjb3B5IHRleHQgInN0cmluZyBzdHJpbmcgc3RyaW5nImAKICAgICAgICAgICAgLSBiYXNlNjTnvJbnoIHnmoTmlofmnKwKICAgICAgICAgICAgICAgIC0gYGNvcHkgYmFzZTY0IDZMNlQ1WVdsNUxpdDVwYUg1YTJYNTZ5bWAKICAgICAgICAgICAgLSBVUkwg57yW56CB55qE5paH5pysCiAgICAgICAgICAgICAgICAtIGBjb3B5IHVybGVuY29kZSAlRTglQkUlOTMlRTUlODUlQTUlRTQlQjglQUQlRTYlOTYlODclRTUlQUQlOTclRTclQUMlQTZgCiAgICAgICAgLSDovpPlhaUqKuS4reaWh+Wtl+espioqCiAgICAgICAgICAgIC0g6YCa6L+H5aSN5Yi25ZKM5qih5ouf5oyJ6ZSu5Y+v5Lul5a6e546w6L6T5YWl5Lit5paH5a2X56ym55qE5Yqf6IO9CiAgICAgICAgICAgIC0gYGNvcHkgYmFzZTY0IDVMaXQ1cGFINWEyWDU2eW1gCiAgICAgICAgICAgIC0gYHByZXNzIEtFWUNPREVfUEFTVEVgCiAgICAgICAgLSDojrflj5bmjqfku7bkv6Hmga8KICAgICAgICAgICAgLSBgcXVlcnl2aWV3IFtpZCB0eXBlXSBbaWQocyldIFtjb21tYW5kXWAKICAgICAgICAgICAgICAgIC0gYGlkIHR5cGVgCiAgICAgICAgICAgICAgICAgICAgLSBgIGAKICAgICAgICAgICAgICAgICAgICAtIGB2aWV3aWRgCiAgICAgICAgICAgICAgICAgICAgLSBgYWNjZXNzaWJpbGl0eWlkc2AKICAgICAgICAgICAgLSDojrflj5blsY/luZXlpKflsI8KICAgICAgICAgICAgICAgIC0gYHF1ZXJ5dmlldyBnZXRsb2NhdGlvbmAgPiBgT0s6MCAwIDE0NDAgMjg4MGAKICAgICAgICAgICAgLSDkvY3nva4KICAgICAgICAgICAgICAgIC0gYHF1ZXJ5dmlldyB2aWV3aWQgY29tLnh4eC54eHh4OmlkL3h4eHh4IGdldGxvY2F0aW9uYAogICAgICAgICAgICAgICAgLSBgcXVlcnl2aWV3IGFjY2Vzc2liaWxpdHlpZHMgW3dpbmRvd0lkXSBbdmlld0lkXSBnZXRsb2NhdGlvbmAKICAgICAgICAgICAgICAgIC0gYHF1ZXJ5dmlldyBhY2Nlc3NpYmlsaXR5aWRzICAxMzgxICAgICAgIDg5MCAgICAgZ2V0bG9jYXRpb25gCiAgICAgICAgICAgICAgICAtIOekuuS+iwogICAgICAgICAgICAgICAgICAgIGBgYAogICAgICAgICAgICAgICAgICAgID4gcXVlcnl2aWV3IHZpZXdpZCBhbmRyb2lkOmlkL2J1dHRvbjEgZ2V0bG9jYXRpb24KICAgICAgICAgICAgICAgICAgICA8IE9LOjEwODEgMTQ3OSAyMjQgMTg5CiAgICAgICAgICAgICAgICAgICAgYGBgCiAgICAgICAgICAgIC0g5paH5pysCiAgICAgICAgICAgICAgICAtIGBxdWVyeXZpZXcgdmlld2lkIGFuZHJvaWQ6aWQvYnV0dG9uMSBnZXR0ZXh0YAogICAgICAgICAgICAgICAgYGBgCiAgICAgICAgICAgICAgICA+IHF1ZXJ5dmlldyB2aWV3aWQgYW5kcm9pZDppZC9idXR0b24xIGdldHRleHQKICAgICAgICAgICAgICAgIDwgT0s656Gu5a6aCiAgICAgICAgICAgICAgICBgYGAKICAgICAgICAtIOiOt+WPluagkeW9oue7k+aehOeahOeVjOmdouaOp+S7tuS/oeaBrwogICAgICAgICAgICAtIOaWh+acrOagvOW8jwogICAgICAgICAgICAgICAgLSBgcXVlcnl2aWV3IGdldHRyZWUgdGV4dGAKICAgICAgICAgICAgICAgIC0gYHF1ZXJ5dmlldyB2aWV3aWQgY29tLnh4eC54eHh4OmlkL3h4eHh4IGdldHRyZWUgdGV4dGAKICAgICAgICAgICAgICAgIC0gYHF1ZXJ5dmlldyBhY2Nlc3NpYmlsaXR5aWRzIDEzODEgODkwIGdldHRyZWUgdGV4dGAKICAgICAgICAgICAgLSBqc29uIOagvOW8jwogICAgICAgICAgICAgICAgLSBgcXVlcnl2aWV3IGdldHRyZWUganNvbmAKICAgICAgICAgICAgICAgIC0gYHF1ZXJ5dmlldyB2aWV3aWQgY29tLnh4eC54eHh4OmlkL3h4eHh4IGdldHRyZWUganNvbmAKICAgICAgICAgICAgICAgIC0gYHF1ZXJ5dmlldyBhY2Nlc3NpYmlsaXR5aWRzIDEzODEgODkwIGdldHRyZWUganNvbmAKICAgICAgICAgICAgLSDojrflj5bnlYzpnaLlhajpg6jmjqfku7bmoJHlvaLnu5PmnoQKICAgICAgICAgICAgICAgIC0gYHF1ZXJ5dmlldyBnZXR0cmVlIHRleHRgCiAgICAgICAgICAgICAgICAtIGBxdWVyeXZpZXcgZ2V0dHJlZSBqc29uYAogICAgICAgICAgICAtIOiOt+WPluaMh+WumuaOp+S7tuS4i+eahOaOp+S7tuagkeW9oue7k+aehAogICAgICAgICAgICAgICAgLSBgcXVlcnl2aWV3IHZpZXdpZCBjb20ueHh4Lnh4eHg6aWQveHh4eHggZ2V0dHJlZSB0ZXh0YAogICAgICAgICAgICAgICAgLSBgcXVlcnl2aWV3IGFjY2Vzc2liaWxpdHlpZHMgMTM4MSA4OTAgZ2V0dHJlZSB0ZXh0YAogICAgICAgICAgICAgICAgLSBgcXVlcnl2aWV3IHZpZXdpZCBjb20ueHh4Lnh4eHg6aWQveHh4eHggZ2V0dHJlZSBqc29uYAogICAgICAgICAgICAgICAgLSBgcXVlcnl2aWV3IGFjY2Vzc2liaWxpdHlpZHMgMTM4MSA4OTAgZ2V0dHJlZSBqc29uYAogICAgICAgIC0g5oiq5bGP5Yqf6IO9CiAgICAgICAgICAgIC0g5oiq5Y+W55qE5Zu+54mH5Li6IGpwZyDmoLzlvI/vvIznu5PmnpzpgJrov4cgYmFzZTY0IOe8lueggei/lOWbngogICAgICAgICAgICAtIGB0YWtlc2NyZWVuc2hvdCBbc2NhbGV8cmVjdHxnZXRjb2xvcnxxdWFsaXR5XWAKICAgICAgICAgICAgLSDmiKrlj5bmlbTkuKrlsY/luZUKICAgICAgICAgICAgICAgIC0gYHRha2VzY3JlZW5zaG90YAogICAgICAgICAgICAtIOaIquWPluaMh+WumuWMuuWfn+eahOWxj+W5lQogICAgICAgICAgICAgICAgLSBgdGFrZXNjcmVlbnNob3QgcmVjdCAzMCAzMCA1MCA1MGAKICAgICAgICAgICAgLSDnvKnmlL7miKrlj5bnmoTlm77niYcKICAgICAgICAgICAgICAgIC0gYHRha2VzY3JlZW5zaG90IHNjYWxlIDAuM2AKICAgICAgICAgICAgLSDojrflj5blsY/luZXmjIflrprlnZDmoIfnmoTlg4/ntKDpopzoibIKICAgICAgICAgICAgICAgIC0gYHRha2VzY3JlZW5zaG90IGdldGNvbG9yIDMwMCAzMzBgCiAgICAgICAgICAgIC0g6K6+572u5Zu+54mH55qE6LSo6YePCiAgICAgICAgICAgICAgICAtIGB0YWtlc2NyZWVuc2hvdCBxdWFsaXR5IDkwYAogICAgICAgICAgICAtIOe7hOWQiOWRveS7pAogICAgICAgICAgICAgICAgLSBgdGFrZXNjcmVlbnNob3QgcmVjdCAzMCAzMCA1MCA1MCBzY2FsZSAwLjUgcXVhbGl0eSA4MGAKICAgICAgICAgICAgICAgIC0gYHRha2VzY3JlZW5zaG90IHNjYWxlIDAuNSByZWN0IDMwIDMwIDUwIDUwIHF1YWxpdHkgODBgCiAgICAgICAgICAgICAgICAtIGB0YWtlc2NyZWVuc2hvdCBxdWFsaXR5IDgwIHNjYWxlIDAuNSByZWN0IDMwIDMwIDUwIDUwYAogICAgICAgIC0g6I635Y+W57O757uf5L+h5oGvCiAgICAgICAgICAgIC0g5ZG95Luk5qC85byPIGBnZXR2YXIgdmFybmFtZWAKICAgICAgICAgICAgLSBgYnVpbGQuYm9hcmRgCiAgICAgICAgICAgICAgICAtIGBnZXR2YXIgYnVpbGQuYm9hcmRgID4gYE9LOmdvbGRmaXNoX3g4NmAKICAgICAgICAgICAgLSBgYnVpbGQuYnJhbmRgCiAgICAgICAgICAgIC0gYGJ1aWxkLmRldmljZWAKICAgICAgICAgICAgLSBgYnVpbGQuZGlzcGxheWAKICAgICAgICAgICAgICAgIC0gYGdldHZhciBidWlsZC5kaXNwbGF5YCA+IGBPSzpzZGtfZ3Bob25lX3g4Ni11c2VyZGVidWcgOSBQU1IxLjE4MDcyMC4wOTMgNTQ1NjQ0NiBkZXYta2V5c2AKICAgICAgICAgICAgLSBgYnVpbGQuZmluZ2VycHJpbnRgCiAgICAgICAgICAgIC0gYGJ1aWxkLmhvc3RgCiAgICAgICAgICAgIC0gYGJ1aWxkLmlkYAogICAgICAgICAgICAtIGBidWlsZC5tb2RlbGAKICAgICAgICAgICAgLSBgYnVpbGQucHJvZHVjdGAKICAgICAgICAgICAgLSBgYnVpbGQudGFnc2AKICAgICAgICAgICAgLSBgYnVpbGQuYnJhbmRgCiAgICAgICAgICAgIC0gYGJ1aWxkLnR5cGVgCiAgICAgICAgICAgIC0gYGJ1aWxkLnVzZXJgCiAgICAgICAgICAgIC0gYGJ1aWxkLmNwdV9hYmlgCiAgICAgICAgICAgIC0gYGJ1aWxkLm1hbnVmYWN0dXJlcmAKICAgICAgICAtIOWbnuaYvuWtl+espuS4sgogICAgICAgICAgICAtIOeUqOS6juWcqOiEmuacrOS4reWQjOatpeaTjeS9nAogICAgICAgICAgICAtIGBlY2hvIHN0cmluZ2AKICAgICAgICAtIOiOt+WPlueVjOmdouaYr+WQpuacieabtOaWsAogICAgICAgICAgICAtIGBnZXRpc3ZpZXdjaGFuZ2VgCiAgICAgICAgLSDpgIDlh7oKICAgICAgICAgICAgLSBgcXVpdGAKICAgICAgICAKICAgICAgICA=";
        System.out.println(new String(Base64.decode(help, Base64.DEFAULT)));
    }
}
