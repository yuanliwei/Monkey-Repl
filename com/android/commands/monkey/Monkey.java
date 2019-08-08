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
import android.app.ActivityThread;
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

        // TODO test code

        ActivityThread at = ActivityThread.systemMain();
        mContext = at.getSystemContext();
        

        if (!getSystemInterfaces()) {
            return -3;
        }

        mEventSource = new MonkeySourceShell(mAm);
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
        String help = "IyBtb25rZXktcmVwbAoKIyMjIyDmj4/ov7AKbW9ua2V5LXJlcGwgOiDkuIDkuKrpgJrov4cgdXNiIOaOp+WItiBBbmRyb2lkIOiuvuWkh+eahOiHquWKqOWMluW3peWFt++8jOS/ruaUueiHqiBbQW5kcm9pZCBtb25rZXldKGh0dHBzOi8vYW5kcm9pZC5nb29nbGVzb3VyY2UuY29tL3BsYXRmb3JtL2RldmVsb3BtZW50LysvcmVmcy9oZWFkcy9tYXN0ZXIvY21kcy9tb25rZXkp44CCCgojIyMjIOeJueaApwotIOS4jemcgOimgSBgcm9vdGAg5p2D6ZmQCi0g6I635Y+W5bGP5bmV5o6n5Lu25L+h5oGvCi0g5oiq5bGP44CB5Y+W6ImyCi0g5qih5ouf5oyJ6ZSuCi0g5a2X56ym6L6T5YWl44CB5Lit5paH6L6T5YWlCi0g5a6e5pe25ZON5bqU5ZG95Luk5pON5L2cCi0g5Y+v5Lul6YCa6L+H6ISa5pys6LCD55So5L2/55So5pa55L6/Ci0g5Y+v5Lul6I635Y+WIGB3ZWJ2aWV3YCDkuK3nmoTmjqfku7YKCiMjIyMg5L2/55So5pa55byPCi0g5L2/55SoIHVzYiDov57mjqXmiYvmnLoKLSDmiZPlvIDmiYvmnLrnmoQgdXNiIOiwg+ivleaooeW8jwotIOi/m+WFpSBzdGFydCDnm67lvZXvvIzov5DooYwgc3RhcnQuY21kIOi/m+WFpeS6pOS6kueql+WPowotIOi+k+WFpSBgcXVlcnl2aWV3IGdldGxvY2F0aW9uYCDmjIkgYGVudGVyYAotIOi+k+WFpSBgcXVlcnl2aWV3IGdldHRyZWUgdGV4dGAg5oyJIGBlbnRlcmAKLSDpgIDlh7ogYHF1aXRgIOaMiSBgZW50ZXJgCgojIyMjIOiEmuacrAotIOWPguiAgyBkZW1vIOebruW9lQoKIyMjIyDlip/og73muIXljZUKLSDmqKHmi5/mjInplK7kuovku7YKLSDmqKHmi5/lsY/luZXop6bmkbjkuovku7YKICAgIC0g54K55Ye7CiAgICAtIOaMieS4iwogICAgLSDnp7vliqggCiAgICAtIOaKrOi1twogICAgLSDku47kuIDngrnmu5HliqjliLDlj6bkuIDngrkKLSDkvJHnnKDmjIflrprml7bpl7QgCi0g6L6T5YWl5a2X56ym5Liy5paH5pysCi0g5aSN5Yi25paH5pys5Yiw5Ymq6LS05p2/CiAgICAtIOaZrumAmuaWh+acrAogICAgLSBiYXNlNjTnvJbnoIHnmoTmlofmnKwKICAgIC0gVVJMIOe8lueggeeahOaWh+acrAotIOi+k+WFpSoq5Lit5paH5a2X56ymKioKICAgIC0g6YCa6L+H5aSN5Yi25ZKM5qih5ouf5oyJ6ZSu5Y+v5Lul5a6e546w6L6T5YWl5Lit5paH5a2X56ym55qE5Yqf6IO9CiAgICAtIGBjb3B5IGJhc2U2NCA1TGl0NXBhSDVhMlg1NnltYAogICAgLSBgcHJlc3MgcGFzdGVgCi0g6I635Y+W5o6n5Lu25L+h5oGvCiAgICAtIOS9jee9rgogICAgLSDmlofmnKwKLSDojrflj5bmoJHlvaLnu5PmnoTnmoTnlYzpnaLmjqfku7bkv6Hmga8KICAgIC0g5paH5pys5qC85byPCiAgICAtIGpzb24g5qC85byPCiAgICAtIOiOt+WPlueVjOmdouWFqOmDqOaOp+S7tuagkeW9oue7k+aehAogICAgLSDojrflj5bmjIflrprmjqfku7bkuIvnmoTmjqfku7bmoJHlvaLnu5PmnoQKLSDmiKrlsY/lip/og70KICAgIC0g5oiq5Y+W5pW05Liq5bGP5bmVCiAgICAtIOaIquWPluaMh+WumuWMuuWfn+eahOWxj+W5lQogICAgLSDnvKnmlL7miKrlj5bnmoTlm77niYcKICAgIC0g6I635Y+W5bGP5bmV5oyH5a6a5Z2Q5qCH55qE5YOP57Sg6aKc6ImyCi0g6I635Y+W57O757uf5L+h5oGvCiAgICAtIGBidWlsZC5ib2FyZGAKICAgIC0gYGJ1aWxkLmJyYW5kYAogICAgLSBgYnVpbGQuZGV2aWNlYAogICAgLSBgYnVpbGQuZGlzcGxheWAKICAgIC0gYGJ1aWxkLmZpbmdlcnByaW50YAogICAgLSBgYnVpbGQuaG9zdGAKICAgIC0gYGJ1aWxkLmlkYAogICAgLSBgYnVpbGQubW9kZWxgCiAgICAtIGBidWlsZC5wcm9kdWN0YAogICAgLSBgYnVpbGQudGFnc2AKICAgIC0gYGJ1aWxkLmJyYW5kYAogICAgLSBgYnVpbGQudHlwZWAKICAgIC0gYGJ1aWxkLnVzZXJgCiAgICAtIGBidWlsZC5jcHVfYWJpYAogICAgLSBgYnVpbGQubWFudWZhY3R1cmVyYAotIOWbnuaYvuWtl+espuS4sgogICAgLSDnlKjkuo7lnKjohJrmnKzkuK3lkIzmraXmk43kvZwKLSDojrflj5bnlYzpnaLmmK/lkKbmnInmm7TmlrAKCiMjIyMg5Yqf6IO95L2/55So56S65L6LCi0g5qih5ouf5oyJ6ZSu5LqL5Lu2CiAgICAtIGBwcmVzcyBLRVlDT0RFX0VOVEVSYAogICAgLSBgcHJlc3MgS0VZQ09ERV9QQVNURWAKICAgIC0gYHByZXNzIEtFWUNPREVfVVBgCiAgICAtIGBwcmVzcyBLRVlDT0RFX0RPV05gCi0g5qih5ouf5bGP5bmV6Kem5pG45LqL5Lu2CiAgICAtIGB0b3VjaCBbZG93bnx1cHxtb3ZlXSBbeF0gW3ldYAogICAgLSDngrnlh7sKICAgICAgICAtIGB0YXAgeCB5YAogICAgICAgIC0gYHRhcCAzMCA1MGAKICAgIC0g5oyJ5LiLCiAgICAgICAgLSBgdG91Y2ggZG93biB4IHlgCiAgICAgICAgLSBgdG91Y2ggZG93biAzMCA1MGAKICAgIC0g56e75YqoIAogICAgICAgIC0gYHRvdWNoIG1vdmUgeCB5YAogICAgICAgIC0gYHRvdWNoIG1vdmUgNTAgNjBgCiAgICAtIOaKrOi1twogICAgICAgIC0gYHRvdWNoIHVwIHggeWAKICAgICAgICAtIGB0b3VjaCB1cCA3MCA4MGAKICAgIC0g5LuO5LiA54K55ruR5Yqo5Yiw5Y+m5LiA54K5CiAgICAgICAgLSBgc2xpZGUgeDEgeTEgeDIgeTIgdGltZSBzdGVwYAogICAgICAgIC0gYHNsaWRlIDMwMCA1MDAgNjAwIDcwMCAyMCAxNmAKLSDkvJHnnKDmjIflrprml7bpl7QgCiAgICAtIGBzbGVlcCAxMDI0YAotIOi+k+WFpeWtl+espuS4suaWh+acrAogICAgLSBgdHlwZSAxMjM0YAogICAgLSBgdHlwZSBzdHJpbmdgCiAgICAtIGB0eXBlIHVzZXJuYW1lYAotIOWkjeWItuaWh+acrOWIsOWJqui0tOadvwogICAgLSBgY29weSBbdGV4dHxiYXNlNjR8dXJsZW5jb2RlXSBzdHJpbmdgCiAgICAtIOaZrumAmuaWh+acrAogICAgICAgIC0gYGNvcHkgdGV4dCBzdHJpbmdgCiAgICAgICAgLSBgY29weSB0ZXh0ICJzdHJpbmcgc3RyaW5nIHN0cmluZyJgCiAgICAtIGJhc2U2NOe8lueggeeahOaWh+acrAogICAgICAgIC0gYGNvcHkgYmFzZTY0IDZMNlQ1WVdsNUxpdDVwYUg1YTJYNTZ5bWAKICAgIC0gVVJMIOe8lueggeeahOaWh+acrAogICAgICAgIC0gYGNvcHkgdXJsZW5jb2RlICVFOCVCRSU5MyVFNSU4NSVBNSVFNCVCOCVBRCVFNiU5NiU4NyVFNSVBRCU5NyVFNyVBQyVBNmAKLSDovpPlhaUqKuS4reaWh+Wtl+espioqCiAgICAtIOmAmui/h+WkjeWItuWSjOaooeaLn+aMiemUruWPr+S7peWunueOsOi+k+WFpeS4reaWh+Wtl+espueahOWKn+iDvQogICAgLSBgY29weSBiYXNlNjQgNUxpdDVwYUg1YTJYNTZ5bWAKICAgIC0gYHByZXNzIEtFWUNPREVfUEFTVEVgCi0g6I635Y+W5o6n5Lu25L+h5oGvCiAgICAtIGBxdWVyeXZpZXcgW2lkIHR5cGVdIFtpZChzKV0gW2NvbW1hbmRdYAogICAgICAgIC0gYGlkIHR5cGVgCiAgICAgICAgICAgIC0gYCBgCiAgICAgICAgICAgIC0gYHZpZXdpZGAKICAgICAgICAgICAgLSBgYWNjZXNzaWJpbGl0eWlkc2AKICAgIC0g6I635Y+W5bGP5bmV5aSn5bCPCiAgICAgICAgLSBgcXVlcnl2aWV3IGdldGxvY2F0aW9uYCA+IGBPSzowIDAgMTQ0MCAyODgwYAogICAgLSDkvY3nva4KICAgICAgICAtIGBxdWVyeXZpZXcgdmlld2lkIGNvbS54eHgueHh4eDppZC94eHh4eCBnZXRsb2NhdGlvbmAKICAgICAgICAtIGBxdWVyeXZpZXcgYWNjZXNzaWJpbGl0eWlkcyBbd2luZG93SWRdIFt2aWV3SWRdIGdldGxvY2F0aW9uYAogICAgICAgIC0gYHF1ZXJ5dmlldyBhY2Nlc3NpYmlsaXR5aWRzICAxMzgxICAgICAgIDg5MCAgICAgZ2V0bG9jYXRpb25gCiAgICAgICAgLSDnpLrkvosKICAgICAgICAgICAgYGBgCiAgICAgICAgICAgID4gcXVlcnl2aWV3IHZpZXdpZCBhbmRyb2lkOmlkL2J1dHRvbjEgZ2V0bG9jYXRpb24KICAgICAgICAgICAgPCBPSzoxMDgxIDE0NzkgMjI0IDE4OQogICAgICAgICAgICBgYGAKICAgIC0g5paH5pysCiAgICAgICAgLSBgcXVlcnl2aWV3IHZpZXdpZCBhbmRyb2lkOmlkL2J1dHRvbjEgZ2V0dGV4dGAKICAgICAgICBgYGAKICAgICAgICA+IHF1ZXJ5dmlldyB2aWV3aWQgYW5kcm9pZDppZC9idXR0b24xIGdldHRleHQKICAgICAgICA8IE9LOuehruWumgogICAgICAgIGBgYAotIOiOt+WPluagkeW9oue7k+aehOeahOeVjOmdouaOp+S7tuS/oeaBrwogICAgLSDmlofmnKzmoLzlvI8KICAgICAgICAtIGBxdWVyeXZpZXcgZ2V0dHJlZSB0ZXh0YAogICAgICAgIC0gYHF1ZXJ5dmlldyB2aWV3aWQgY29tLnh4eC54eHh4OmlkL3h4eHh4IGdldHRyZWUgdGV4dGAKICAgICAgICAtIGBxdWVyeXZpZXcgYWNjZXNzaWJpbGl0eWlkcyAxMzgxIDg5MCBnZXR0cmVlIHRleHRgCiAgICAtIGpzb24g5qC85byPCiAgICAgICAgLSBgcXVlcnl2aWV3IGdldHRyZWUganNvbmAKICAgICAgICAtIGBxdWVyeXZpZXcgdmlld2lkIGNvbS54eHgueHh4eDppZC94eHh4eCBnZXR0cmVlIGpzb25gCiAgICAgICAgLSBgcXVlcnl2aWV3IGFjY2Vzc2liaWxpdHlpZHMgMTM4MSA4OTAgZ2V0dHJlZSBqc29uYAogICAgLSDojrflj5bnlYzpnaLlhajpg6jmjqfku7bmoJHlvaLnu5PmnoQKICAgICAgICAtIGBxdWVyeXZpZXcgZ2V0dHJlZSB0ZXh0YAogICAgICAgIC0gYHF1ZXJ5dmlldyBnZXR0cmVlIGpzb25gCiAgICAtIOiOt+WPluaMh+WumuaOp+S7tuS4i+eahOaOp+S7tuagkeW9oue7k+aehAogICAgICAgIC0gYHF1ZXJ5dmlldyB2aWV3aWQgY29tLnh4eC54eHh4OmlkL3h4eHh4IGdldHRyZWUgdGV4dGAKICAgICAgICAtIGBxdWVyeXZpZXcgYWNjZXNzaWJpbGl0eWlkcyAxMzgxIDg5MCBnZXR0cmVlIHRleHRgCiAgICAgICAgLSBgcXVlcnl2aWV3IHZpZXdpZCBjb20ueHh4Lnh4eHg6aWQveHh4eHggZ2V0dHJlZSBqc29uYAogICAgICAgIC0gYHF1ZXJ5dmlldyBhY2Nlc3NpYmlsaXR5aWRzIDEzODEgODkwIGdldHRyZWUganNvbmAKLSDmiKrlsY/lip/og70KICAgIC0g5oiq5Y+W55qE5Zu+54mH5Li6IGpwZyDmoLzlvI/vvIznu5PmnpzpgJrov4cgYmFzZTY0IOe8lueggei/lOWbngogICAgLSBgdGFrZXNjcmVlbnNob3QgW3NjYWxlfHJlY3R8Z2V0Y29sb3J8cXVhbGl0eV1gCiAgICAtIOaIquWPluaVtOS4quWxj+W5lQogICAgICAgIC0gYHRha2VzY3JlZW5zaG90YAogICAgLSDmiKrlj5bmjIflrprljLrln5/nmoTlsY/luZUKICAgICAgICAtIGB0YWtlc2NyZWVuc2hvdCByZWN0IDMwIDMwIDUwIDUwYAogICAgLSDnvKnmlL7miKrlj5bnmoTlm77niYcKICAgICAgICAtIGB0YWtlc2NyZWVuc2hvdCBzY2FsZSAwLjNgCiAgICAtIOiOt+WPluWxj+W5leaMh+WumuWdkOagh+eahOWDj+e0oOminOiJsgogICAgICAgIC0gYHRha2VzY3JlZW5zaG90IGdldGNvbG9yIDMwMCAzMzBgCiAgICAtIOiuvue9ruWbvueJh+eahOi0qOmHjwogICAgICAgIC0gYHRha2VzY3JlZW5zaG90IHF1YWxpdHkgOTBgCiAgICAtIOe7hOWQiOWRveS7pAogICAgICAgIC0gYHRha2VzY3JlZW5zaG90IHJlY3QgMzAgMzAgNTAgNTAgc2NhbGUgMC41IHF1YWxpdHkgODBgCiAgICAgICAgLSBgdGFrZXNjcmVlbnNob3Qgc2NhbGUgMC41IHJlY3QgMzAgMzAgNTAgNTAgcXVhbGl0eSA4MGAKICAgICAgICAtIGB0YWtlc2NyZWVuc2hvdCBxdWFsaXR5IDgwIHNjYWxlIDAuNSByZWN0IDMwIDMwIDUwIDUwYAotIOiOt+WPluezu+e7n+S/oeaBrwogICAgLSDlkb3ku6TmoLzlvI8gYGdldHZhciB2YXJuYW1lYAogICAgLSBgYnVpbGQuYm9hcmRgCiAgICAgICAgLSBgZ2V0dmFyIGJ1aWxkLmJvYXJkYCA+IGBPSzpnb2xkZmlzaF94ODZgCiAgICAtIGBidWlsZC5icmFuZGAKICAgIC0gYGJ1aWxkLmRldmljZWAKICAgIC0gYGJ1aWxkLmRpc3BsYXlgCiAgICAgICAgLSBgZ2V0dmFyIGJ1aWxkLmRpc3BsYXlgID4gYE9LOnNka19ncGhvbmVfeDg2LXVzZXJkZWJ1ZyA5IFBTUjEuMTgwNzIwLjA5MyA1NDU2NDQ2IGRldi1rZXlzYAogICAgLSBgYnVpbGQuZmluZ2VycHJpbnRgCiAgICAtIGBidWlsZC5ob3N0YAogICAgLSBgYnVpbGQuaWRgCiAgICAtIGBidWlsZC5tb2RlbGAKICAgIC0gYGJ1aWxkLnByb2R1Y3RgCiAgICAtIGBidWlsZC50YWdzYAogICAgLSBgYnVpbGQuYnJhbmRgCiAgICAtIGBidWlsZC50eXBlYAogICAgLSBgYnVpbGQudXNlcmAKICAgIC0gYGJ1aWxkLmNwdV9hYmlgCiAgICAtIGBidWlsZC5tYW51ZmFjdHVyZXJgCi0g5Zue5pi+5a2X56ym5LiyCiAgICAtIOeUqOS6juWcqOiEmuacrOS4reWQjOatpeaTjeS9nAogICAgLSBgZWNobyBzdHJpbmdgCi0g6I635Y+W55WM6Z2i5piv5ZCm5pyJ5pu05pawCiAgICAtIGBnZXRpc3ZpZXdjaGFuZ2VgCi0g6I635Y+W6aG25bGCIGFjdGl2aXR5IAogICAgLSBgZ2V0dG9wYWN0aXZpdHlgID4gYE9LOmNvbS5nb29nbGUuYW5kcm9pZC5hcHBzLm5leHVzbGF1bmNoZXIvY29tLmdvb2dsZS5hbmRyb2lkLmFwcHMubmV4dXNsYXVuY2hlci5OZXh1c0xhdW5jaGVyQWN0aXZpdHlgCi0g6YCA5Ye6CiAgICAtIGBxdWl0YAoK";
        System.out.println(new String(Base64.decode(help, Base64.DEFAULT)));
    }
}
