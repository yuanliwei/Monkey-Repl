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
import android.app.IActivityController;
import android.app.IActivityManager;
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
        mAm = ActivityManager.getService();
        if (mAm == null) {
            Logger.err.println("** Error: Unable to connect to activity manager; is the system " + "running?");
            return false;
        }

        mWm = IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
        if (mWm == null) {
            Logger.err.println("** Error: Unable to connect to window manager; is the system " + "running?");
            return false;
        }

        mPm = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
        if (mPm == null) {
            Logger.err.println("** Error: Unable to connect to package manager; is the system " + "running?");
            return false;
        }

        mAm.setActivityController(new ActivityController(), true);

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
        String help = "IyBtb25rZXktcmVwbAoKIyMjIyDmj4/ov7AKbW9ua2V5LXJlcGwgOiDkuIDkuKrpgJrov4cgdXNiIOaOp+WItiBBbmRyb2lkIOiuvuWkh+eahOiHquWKqOWMluW3peWFt++8jOS/ruaUueiHqiBbQW5kcm9pZCBtb25rZXldKGh0dHBzOi8vYW5kcm9pZC5nb29nbGVzb3VyY2UuY29tL3BsYXRmb3JtL2RldmVsb3BtZW50LysvcmVmcy9oZWFkcy9tYXN0ZXIvY21kcy9tb25rZXkp44CCCgojIyMjIOeJueaApwotIOS4jemcgOimgSBgcm9vdGAg5p2D6ZmQCi0g6I635Y+W5bGP5bmV5o6n5Lu25L+h5oGvCi0g5oiq5bGP44CB5Y+W6ImyCi0g5qih5ouf5oyJ6ZSuCi0g5a2X56ym6L6T5YWl44CB5Lit5paH6L6T5YWlCi0g5a6e5pe25ZON5bqU5ZG95Luk5pON5L2cCi0g5Y+v5Lul6YCa6L+H6ISa5pys6LCD55So5L2/55So5pa55L6/Ci0g5Y+v5Lul6I635Y+WIGB3ZWJ2aWV3YCDkuK3nmoTmjqfku7YKCiMjIyMg5L2/55So5pa55byPCi0g5L2/55SoIHVzYiDov57mjqXmiYvmnLoKLSDmiZPlvIDmiYvmnLrnmoQgdXNiIOiwg+ivleaooeW8jwotIOi/m+WFpSBzdGFydCDnm67lvZXvvIzov5DooYwgc3RhcnQuY21kIOi/m+WFpeS6pOS6kueql+WPowotIOi+k+WFpSBgcXVlcnl2aWV3IGdldGxvY2F0aW9uYCDmjIkgYGVudGVyYAotIOi+k+WFpSBgcXVlcnl2aWV3IGdldHRyZWUgdGV4dGAg5oyJIGBlbnRlcmAKLSDpgIDlh7ogYHF1aXRgIOaMiSBgZW50ZXJgCgojIyMjIOiEmuacrAotIOWPguiAgyBkZW1vIOebruW9lQoKIyMjIyDlip/og73muIXljZUKLSDmqKHmi5/mjInplK7kuovku7YKLSDmqKHmi5/lsY/luZXop6bmkbjkuovku7YKICAgIC0g54K55Ye7CiAgICAtIOaMieS4iwogICAgLSDnp7vliqggCiAgICAtIOaKrOi1twotIOS8keecoOaMh+WumuaXtumXtCAKLSDovpPlhaXlrZfnrKbkuLLmlofmnKwKLSDlpI3liLbmlofmnKzliLDliarotLTmnb8KICAgIC0g5pmu6YCa5paH5pysCiAgICAtIGJhc2U2NOe8lueggeeahOaWh+acrAogICAgLSBVUkwg57yW56CB55qE5paH5pysCi0g6L6T5YWlKirkuK3mloflrZfnrKYqKgogICAgLSDpgJrov4flpI3liLblkozmqKHmi5/mjInplK7lj6/ku6Xlrp7njrDovpPlhaXkuK3mloflrZfnrKbnmoTlip/og70KICAgIC0gYGNvcHkgYmFzZTY0IDVMaXQ1cGFINWEyWDU2eW1gCiAgICAtIGBwcmVzcyBwYXN0ZWAKLSDojrflj5bmjqfku7bkv6Hmga8KICAgIC0g5L2N572uCiAgICAtIOaWh+acrAotIOiOt+WPluagkeW9oue7k+aehOeahOeVjOmdouaOp+S7tuS/oeaBrwogICAgLSDmlofmnKzmoLzlvI8KICAgIC0ganNvbiDmoLzlvI8KICAgIC0g6I635Y+W55WM6Z2i5YWo6YOo5o6n5Lu25qCR5b2i57uT5p6ECiAgICAtIOiOt+WPluaMh+WumuaOp+S7tuS4i+eahOaOp+S7tuagkeW9oue7k+aehAotIOaIquWxj+WKn+iDvQogICAgLSDmiKrlj5bmlbTkuKrlsY/luZUKICAgIC0g5oiq5Y+W5oyH5a6a5Yy65Z+f55qE5bGP5bmVCiAgICAtIOe8qeaUvuaIquWPlueahOWbvueJhwogICAgLSDojrflj5blsY/luZXmjIflrprlnZDmoIfnmoTlg4/ntKDpopzoibIKLSDojrflj5bns7vnu5/kv6Hmga8KICAgIC0gYGJ1aWxkLmJvYXJkYAogICAgLSBgYnVpbGQuYnJhbmRgCiAgICAtIGBidWlsZC5kZXZpY2VgCiAgICAtIGBidWlsZC5kaXNwbGF5YAogICAgLSBgYnVpbGQuZmluZ2VycHJpbnRgCiAgICAtIGBidWlsZC5ob3N0YAogICAgLSBgYnVpbGQuaWRgCiAgICAtIGBidWlsZC5tb2RlbGAKICAgIC0gYGJ1aWxkLnByb2R1Y3RgCiAgICAtIGBidWlsZC50YWdzYAogICAgLSBgYnVpbGQuYnJhbmRgCiAgICAtIGBidWlsZC50eXBlYAogICAgLSBgYnVpbGQudXNlcmAKICAgIC0gYGJ1aWxkLmNwdV9hYmlgCiAgICAtIGBidWlsZC5tYW51ZmFjdHVyZXJgCgojIyMjIOWKn+iDveS9v+eUqOekuuS+iwotIOaooeaLn+aMiemUruS6i+S7tgogICAgLSBgcHJlc3MgS0VZQ09ERV9FTlRFUmAKICAgIC0gYHByZXNzIEtFWUNPREVfUEFTVEVgCiAgICAtIGBwcmVzcyBLRVlDT0RFX1VQYAogICAgLSBgcHJlc3MgS0VZQ09ERV9ET1dOYAotIOaooeaLn+Wxj+W5leinpuaRuOS6i+S7tgogICAgLSBgdG91Y2ggW2Rvd258dXB8bW92ZV0gW3hdIFt5XWAKICAgIC0g54K55Ye7CiAgICAgICAgLSBgdGFwIHggeWAKICAgICAgICAtIGB0YXAgMzAgNTBgCiAgICAtIOaMieS4iwogICAgICAgIC0gYHRvdWNoIGRvd24geCB5YAogICAgICAgIC0gYHRvdWNoIGRvd24gMzAgNTBgCiAgICAtIOenu+WKqCAKICAgICAgICAtIGB0b3VjaCBtb3ZlIHggeWAKICAgICAgICAtIGB0b3VjaCBtb3ZlIDUwIDYwYAogICAgLSDmiqzotbcKICAgICAgICAtIGB0b3VjaCB1cCB4IHlgCiAgICAgICAgLSBgdG91Y2ggdXAgNzAgODBgCi0g5LyR55yg5oyH5a6a5pe26Ze0IAogICAgLSBgc2xlZXAgMTAyNGAKLSDovpPlhaXlrZfnrKbkuLLmlofmnKwKICAgIC0gYHR5cGUgMTIzNGAKICAgIC0gYHR5cGUgc3RyaW5nYAogICAgLSBgdHlwZSB1c2VybmFtZWAKLSDlpI3liLbmlofmnKzliLDliarotLTmnb8KICAgIC0gYGNvcHkgW3RleHR8YmFzZTY0fHVybGVuY29kZV0gc3RyaW5nYAogICAgLSDmma7pgJrmlofmnKwKICAgICAgICAtIGBjb3B5IHRleHQgc3RyaW5nYAogICAgICAgIC0gYGNvcHkgdGV4dCAic3RyaW5nIHN0cmluZyBzdHJpbmciYAogICAgLSBiYXNlNjTnvJbnoIHnmoTmlofmnKwKICAgICAgICAtIGBjb3B5IGJhc2U2NCA2TDZUNVlXbDVMaXQ1cGFINWEyWDU2eW1gCiAgICAtIFVSTCDnvJbnoIHnmoTmlofmnKwKICAgICAgICAtIGBjb3B5IHVybGVuY29kZSAlRTglQkUlOTMlRTUlODUlQTUlRTQlQjglQUQlRTYlOTYlODclRTUlQUQlOTclRTclQUMlQTZgCi0g6L6T5YWlKirkuK3mloflrZfnrKYqKgogICAgLSDpgJrov4flpI3liLblkozmqKHmi5/mjInplK7lj6/ku6Xlrp7njrDovpPlhaXkuK3mloflrZfnrKbnmoTlip/og70KICAgIC0gYGNvcHkgYmFzZTY0IDVMaXQ1cGFINWEyWDU2eW1gCiAgICAtIGBwcmVzcyBLRVlDT0RFX1BBU1RFYAotIOiOt+WPluaOp+S7tuS/oeaBrwogICAgLSBgcXVlcnl2aWV3IFtpZCB0eXBlXSBbaWQocyldIFtjb21tYW5kXWAKICAgICAgICAtIGBpZCB0eXBlYAogICAgICAgICAgICAtIGAgYAogICAgICAgICAgICAtIGB2aWV3aWRgCiAgICAgICAgICAgIC0gYGFjY2Vzc2liaWxpdHlpZHNgCiAgICAtIOiOt+WPluWxj+W5leWkp+WwjwogICAgICAgIC0gYHF1ZXJ5dmlldyBnZXRsb2NhdGlvbmAgPiBgT0s6MCAwIDE0NDAgMjg4MGAKICAgIC0g5L2N572uCiAgICAgICAgLSBgcXVlcnl2aWV3IHZpZXdpZCBjb20ueHh4Lnh4eHg6aWQveHh4eHggZ2V0bG9jYXRpb25gCiAgICAgICAgLSBgcXVlcnl2aWV3IGFjY2Vzc2liaWxpdHlpZHMgW3dpbmRvd0lkXSBbdmlld0lkXSBnZXRsb2NhdGlvbmAKICAgICAgICAtIGBxdWVyeXZpZXcgYWNjZXNzaWJpbGl0eWlkcyAgMTM4MSAgICAgICA4OTAgICAgIGdldGxvY2F0aW9uYAogICAgICAgIC0g56S65L6LCiAgICAgICAgICAgIGBgYAogICAgICAgICAgICA+IHF1ZXJ5dmlldyB2aWV3aWQgYW5kcm9pZDppZC9idXR0b24xIGdldGxvY2F0aW9uCiAgICAgICAgICAgIDwgT0s6MTA4MSAxNDc5IDIyNCAxODkKICAgICAgICAgICAgYGBgCiAgICAtIOaWh+acrAogICAgICAgIC0gYHF1ZXJ5dmlldyB2aWV3aWQgYW5kcm9pZDppZC9idXR0b24xIGdldHRleHRgCiAgICAgICAgYGBgCiAgICAgICAgPiBxdWVyeXZpZXcgdmlld2lkIGFuZHJvaWQ6aWQvYnV0dG9uMSBnZXR0ZXh0CiAgICAgICAgPCBPSzrnoa7lrpoKICAgICAgICBgYGAKLSDojrflj5bmoJHlvaLnu5PmnoTnmoTnlYzpnaLmjqfku7bkv6Hmga8KICAgIC0g5paH5pys5qC85byPCiAgICAgICAgLSBgcXVlcnl2aWV3IGdldHRyZWUgdGV4dGAKICAgICAgICAtIGBxdWVyeXZpZXcgdmlld2lkIGNvbS54eHgueHh4eDppZC94eHh4eCBnZXR0cmVlIHRleHRgCiAgICAgICAgLSBgcXVlcnl2aWV3IGFjY2Vzc2liaWxpdHlpZHMgMTM4MSA4OTAgZ2V0dHJlZSB0ZXh0YAogICAgLSBqc29uIOagvOW8jwogICAgICAgIC0gYHF1ZXJ5dmlldyBnZXR0cmVlIGpzb25gCiAgICAgICAgLSBgcXVlcnl2aWV3IHZpZXdpZCBjb20ueHh4Lnh4eHg6aWQveHh4eHggZ2V0dHJlZSBqc29uYAogICAgICAgIC0gYHF1ZXJ5dmlldyBhY2Nlc3NpYmlsaXR5aWRzIDEzODEgODkwIGdldHRyZWUganNvbmAKICAgIC0g6I635Y+W55WM6Z2i5YWo6YOo5o6n5Lu25qCR5b2i57uT5p6ECiAgICAgICAgLSBgcXVlcnl2aWV3IGdldHRyZWUgdGV4dGAKICAgICAgICAtIGBxdWVyeXZpZXcgZ2V0dHJlZSBqc29uYAogICAgLSDojrflj5bmjIflrprmjqfku7bkuIvnmoTmjqfku7bmoJHlvaLnu5PmnoQKICAgICAgICAtIGBxdWVyeXZpZXcgdmlld2lkIGNvbS54eHgueHh4eDppZC94eHh4eCBnZXR0cmVlIHRleHRgCiAgICAgICAgLSBgcXVlcnl2aWV3IGFjY2Vzc2liaWxpdHlpZHMgMTM4MSA4OTAgZ2V0dHJlZSB0ZXh0YAogICAgICAgIC0gYHF1ZXJ5dmlldyB2aWV3aWQgY29tLnh4eC54eHh4OmlkL3h4eHh4IGdldHRyZWUganNvbmAKICAgICAgICAtIGBxdWVyeXZpZXcgYWNjZXNzaWJpbGl0eWlkcyAxMzgxIDg5MCBnZXR0cmVlIGpzb25gCi0g5oiq5bGP5Yqf6IO9CiAgICAtIOaIquWPlueahOWbvueJh+S4uiBqcGcg5qC85byP77yM57uT5p6c6YCa6L+HIGJhc2U2NCDnvJbnoIHov5Tlm54KICAgIC0gYHRha2VzY3JlZW5zaG90IFtzY2FsZXxyZWN0fGdldGNvbG9yfHF1YWxpdHldYAogICAgLSDmiKrlj5bmlbTkuKrlsY/luZUKICAgICAgICAtIGB0YWtlc2NyZWVuc2hvdGAKICAgIC0g5oiq5Y+W5oyH5a6a5Yy65Z+f55qE5bGP5bmVCiAgICAgICAgLSBgdGFrZXNjcmVlbnNob3QgcmVjdCAzMCAzMCA1MCA1MGAKICAgIC0g57yp5pS+5oiq5Y+W55qE5Zu+54mHCiAgICAgICAgLSBgdGFrZXNjcmVlbnNob3Qgc2NhbGUgMC4zYAogICAgLSDojrflj5blsY/luZXmjIflrprlnZDmoIfnmoTlg4/ntKDpopzoibIKICAgICAgICAtIGB0YWtlc2NyZWVuc2hvdCBnZXRjb2xvciAzMDAgMzMwYAogICAgLSDorr7nva7lm77niYfnmoTotKjph48KICAgICAgICAtIGB0YWtlc2NyZWVuc2hvdCBxdWFsaXR5IDkwYAogICAgLSDnu4TlkIjlkb3ku6QKICAgICAgICAtIGB0YWtlc2NyZWVuc2hvdCByZWN0IDMwIDMwIDUwIDUwIHNjYWxlIDAuNSBxdWFsaXR5IDgwYAogICAgICAgIC0gYHRha2VzY3JlZW5zaG90IHNjYWxlIDAuNSByZWN0IDMwIDMwIDUwIDUwIHF1YWxpdHkgODBgCiAgICAgICAgLSBgdGFrZXNjcmVlbnNob3QgcXVhbGl0eSA4MCBzY2FsZSAwLjUgcmVjdCAzMCAzMCA1MCA1MGAKLSDojrflj5bns7vnu5/kv6Hmga8KICAgIC0g5ZG95Luk5qC85byPIGBnZXR2YXIgdmFybmFtZWAKICAgIC0gYGJ1aWxkLmJvYXJkYAogICAgICAgIC0gYGdldHZhciBidWlsZC5ib2FyZGAgPiBgT0s6Z29sZGZpc2hfeDg2YAogICAgLSBgYnVpbGQuYnJhbmRgCiAgICAtIGBidWlsZC5kZXZpY2VgCiAgICAtIGBidWlsZC5kaXNwbGF5YAogICAgICAgIC0gYGdldHZhciBidWlsZC5kaXNwbGF5YCA+IGBPSzpzZGtfZ3Bob25lX3g4Ni11c2VyZGVidWcgOSBQU1IxLjE4MDcyMC4wOTMgNTQ1NjQ0NiBkZXYta2V5c2AKICAgIC0gYGJ1aWxkLmZpbmdlcnByaW50YAogICAgLSBgYnVpbGQuaG9zdGAKICAgIC0gYGJ1aWxkLmlkYAogICAgLSBgYnVpbGQubW9kZWxgCiAgICAtIGBidWlsZC5wcm9kdWN0YAogICAgLSBgYnVpbGQudGFnc2AKICAgIC0gYGJ1aWxkLmJyYW5kYAogICAgLSBgYnVpbGQudHlwZWAKICAgIC0gYGJ1aWxkLnVzZXJgCiAgICAtIGBidWlsZC5jcHVfYWJpYAogICAgLSBgYnVpbGQubWFudWZhY3R1cmVyYAotIOmAgOWHugogICAgLSBgcXVpdGA=";
        System.out.println(new String(Base64.decode(help, Base64.DEFAULT)));
    }
}
