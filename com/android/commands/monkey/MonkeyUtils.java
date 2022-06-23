/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.commands.monkey;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.util.Base64;

/**
 * Misc utilities.
 */
public abstract class MonkeyUtils {

    private static final java.util.Date DATE = new java.util.Date();
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss.SSS ");
    private static PackageFilter sFilter;

    private MonkeyUtils() {
    }

    /**
     * Return calendar time in pretty string.
     */
    public static synchronized String toCalendarTime(long time) {
        DATE.setTime(time);
        return DATE_FORMATTER.format(DATE);
    }

    public static PackageFilter getPackageFilter() {
        if (sFilter == null) {
            sFilter = new PackageFilter();
        }
        return sFilter;
    }

    public static class PackageFilter {
        private Set<String> mValidPackages = new HashSet<>();
        private Set<String> mInvalidPackages = new HashSet<>();

        private PackageFilter() {
        }

        public void addValidPackages(Set<String> validPackages) {
            mValidPackages.addAll(validPackages);
        }

        public void addInvalidPackages(Set<String> invalidPackages) {
            mInvalidPackages.addAll(invalidPackages);
        }

        public boolean hasValidPackages() {
            return mValidPackages.size() > 0;
        }

        public boolean isPackageValid(String pkg) {
            return mValidPackages.contains(pkg);
        }

        public boolean isPackageInvalid(String pkg) {
            return mInvalidPackages.contains(pkg);
        }

        /**
         * Check whether we should run against the given package.
         *
         * @param pkg The package name.
         * @return Returns true if we should run against pkg.
         */
        public boolean checkEnteringPackage(String pkg) {
            if (mInvalidPackages.size() > 0) {
                if (mInvalidPackages.contains(pkg)) {
                    return false;
                }
            } else if (mValidPackages.size() > 0) {
                if (!mValidPackages.contains(pkg)) {
                    return false;
                }
            }
            return true;
        }

        public void dump() {
            if (mValidPackages.size() > 0) {
                Iterator<String> it = mValidPackages.iterator();
                while (it.hasNext()) {
                    Logger.out.println(":AllowPackage: " + it.next());
                }
            }
            if (mInvalidPackages.size() > 0) {
                Iterator<String> it = mInvalidPackages.iterator();
                while (it.hasNext()) {
                    Logger.out.println(":DisallowPackage: " + it.next());
                }
            }
        }
    }

    static String getHelp() {
        String help = "IyBtb25rZXktcmVwbAoKIyMjIyDmj4/ov7AKbW9ua2V5LXJlcGwgOiDkuIDkuKrpgJrov4cgdXNiIOaOp+WItiBBbmRyb2lkIOiuvuWkh+eahOiHquWKqOWMluW3peWFt++8jOS/ruaUueiHqiBbQW5kcm9pZCBtb25rZXldKGh0dHBzOi8vYW5kcm9pZC5nb29nbGVzb3VyY2UuY29tL3BsYXRmb3JtL2RldmVsb3BtZW50LysvcmVmcy9oZWFkcy9tYXN0ZXIvY21kcy9tb25rZXkp44CCCgojIyMjIOeJueaApwotIOS4jemcgOimgSBgcm9vdGAg5p2D6ZmQCi0g6I635Y+W5bGP5bmV5o6n5Lu25L+h5oGvCi0g5oiq5bGP44CB5Y+W6ImyCi0g5qih5ouf5oyJ6ZSuCi0g5a2X56ym6L6T5YWl44CB5Lit5paH6L6T5YWlCi0g5a6e5pe25ZON5bqU5ZG95Luk5pON5L2cCi0g5Y+v5Lul6YCa6L+H6ISa5pys6LCD55So5L2/55So5pa55L6/Ci0g5Y+v5Lul6I635Y+WIGB3ZWJ2aWV3YCDkuK3nmoTmjqfku7YKLSDmkq3mlL7pn7PpopEKCiMjIyMg5L2/55So5pa55byPCi0g5L2/55SoIHVzYiDov57mjqXmiYvmnLoKLSDmiZPlvIDmiYvmnLrnmoQgdXNiIOiwg+ivleaooeW8jwotIOi/m+WFpSBzdGFydCDnm67lvZXvvIzov5DooYwgc3RhcnQuY21kIOi/m+WFpeS6pOS6kueql+WPowotIOi+k+WFpSBgcXVlcnl2aWV3IGdldGxvY2F0aW9uYCDmjIkgYGVudGVyYAotIOi+k+WFpSBgcXVlcnl2aWV3IGdldHRyZWUgdGV4dGAg5oyJIGBlbnRlcmAKLSDpgIDlh7ogYHF1aXRgIOaMiSBgZW50ZXJgCgoqKuWQr+WKqOWPguaVsCoqCmBgYAptb25rZXktcmVwbCAtLXR5cGUgcmVwbCAtLWNvbW1hbmRfdHlwZSB0ZXh0IC0tbmFtZSBhYmMtcmVwbCAtLXBvcnQgNTY3OCAtLWFsbG93X2lwX2FkZHJlc3MgMTkyLjE2OC4wLjEyMyAtLXF1ZXJ5X3ZpZXcgdHJ1ZSAtLWFjdGl2aXR5X2NvbnRyb2xsZXIgdHJ1ZQogICAgLS10eXBlICAgICAgICAgICAgICAgIDogW3JlcGx8bmV0d29ya10gIGRlZmF1bHQgOiByZXBsCiAgICAtLWNvbW1hbmRfdHlwZSAgICAgICAgOiBbdGV4dHxqc29uXSAgICAgZGVmYXVsdCA6IHRleHQKICAgIC0tbmFtZSAgICAgICAgICAgICAgICA6IHN0cmluZyAgICAgICAgICBkZWZhdWx0IDogbW9ua2V5LXJlcGwKICAgIC0tcG9ydCAgICAgICAgICAgICAgICA6IG51bWJlciAgICAgICAgICBkZWZhdWx0IDogNTY3OAogICAgLS1hbGxvd19pcF9hZGRyZXNzICAgIDogc3RyaW5nICAgICAgICAgIGRlZmF1bHQgOiBhbGwKICAgIC0tcXVlcnlfdmlldyAgICAgICAgICA6IGJvb2xlYW4gICAgICAgICBkZWZhdWx0IDogdHJ1ZQogICAgLS1hY3Rpdml0eV9jb250cm9sbGVyIDogYm9vbGVhbiAgICAgICAgIGRlZmF1bHQgOiB0cnVlCmBgYAoKIyMjIyDohJrmnKwKLSDlj4LogIMgZGVtbyDnm67lvZUKCiMjIyMg5Yqf6IO95riF5Y2VCi0g5qih5ouf5oyJ6ZSu5LqL5Lu2Ci0g5qih5ouf5bGP5bmV6Kem5pG45LqL5Lu2CiAgICAtIOeCueWHuwogICAgLSDmjInkuIsKICAgIC0g56e75YqoCiAgICAtIOaKrOi1twogICAgLSDku47kuIDngrnmu5HliqjliLDlj6bkuIDngrkKLSDkvJHnnKDmjIflrprml7bpl7QKLSDovpPlhaXlrZfnrKbkuLLmlofmnKwKLSDlpI3liLbmlofmnKzliLDliarotLTmnb8KICAgIC0g5pmu6YCa5paH5pysCiAgICAtIGJhc2U2NOe8lueggeeahOaWh+acrAogICAgLSBVUkwg57yW56CB55qE5paH5pysCi0g6L6T5YWlKirkuK3mloflrZfnrKYqKgogICAgLSDpgJrov4flpI3liLblkozmqKHmi5/mjInplK7lj6/ku6Xlrp7njrDovpPlhaXkuK3mloflrZfnrKbnmoTlip/og70KICAgIC0gYGNvcHkgYmFzZTY0IDVMaXQ1cGFINWEyWDU2eW1gCiAgICAtIGBwcmVzcyBwYXN0ZWAKLSDojrflj5bmjqfku7bkv6Hmga8KICAgIC0g5L2N572uCiAgICAtIOaWh+acrAotIOiOt+WPluagkeW9oue7k+aehOeahOeVjOmdouaOp+S7tuS/oeaBrwogICAgLSDmlofmnKzmoLzlvI8KICAgIC0ganNvbiDmoLzlvI8KICAgIC0g6I635Y+W55WM6Z2i5YWo6YOo5o6n5Lu25qCR5b2i57uT5p6ECiAgICAtIOiOt+WPluaMh+WumuaOp+S7tuS4i+eahOaOp+S7tuagkeW9oue7k+aehAotIOaIquWxj+WKn+iDvQogICAgLSDmiKrlj5bmlbTkuKrlsY/luZUKICAgIC0g5oiq5Y+W5oyH5a6a5Yy65Z+f55qE5bGP5bmVCiAgICAtIOe8qeaUvuaIquWPlueahOWbvueJhwogICAgLSDojrflj5blsY/luZXmjIflrprlnZDmoIfnmoTlg4/ntKDpopzoibIKLSDojrflj5bns7vnu5/kv6Hmga8KICAgIC0gYGJ1aWxkLmJvYXJkYAogICAgLSBgYnVpbGQuYnJhbmRgCiAgICAtIGBidWlsZC5kZXZpY2VgCiAgICAtIGBidWlsZC5kaXNwbGF5YAogICAgLSBgYnVpbGQuZmluZ2VycHJpbnRgCiAgICAtIGBidWlsZC5ob3N0YAogICAgLSBgYnVpbGQuaWRgCiAgICAtIGBidWlsZC5tb2RlbGAKICAgIC0gYGJ1aWxkLnByb2R1Y3RgCiAgICAtIGBidWlsZC50YWdzYAogICAgLSBgYnVpbGQuYnJhbmRgCiAgICAtIGBidWlsZC50eXBlYAogICAgLSBgYnVpbGQudXNlcmAKICAgIC0gYGJ1aWxkLmNwdV9hYmlgCiAgICAtIGBidWlsZC5tYW51ZmFjdHVyZXJgCi0g5Zue5pi+5a2X56ym5LiyCiAgICAtIOeUqOS6juWcqOiEmuacrOS4reWQjOatpeaTjeS9nAotIOiOt+WPlueVjOmdouaYr+WQpuacieabtOaWsAoKIyMjIyDlip/og73kvb/nlKjnpLrkvosKLSDmqKHmi5/mjInplK7kuovku7YgW0tFWUNPREVdKGh0dHBzOi8vZ2l0aHViLmNvbS9hb3NwLW1pcnJvci9wbGF0Zm9ybV9mcmFtZXdvcmtzX2Jhc2UvYmxvYi9tYXN0ZXIvY29yZS9qYXZhL2FuZHJvaWQvdmlldy9LZXlFdmVudC5qYXZhKQogICAgLSBgcHJlc3MgS0VZQ09ERV9FTlRFUmAKICAgIC0gYHByZXNzIEtFWUNPREVfUEFTVEVgCiAgICAtIGBwcmVzcyBLRVlDT0RFX1VQYAogICAgLSBgcHJlc3MgS0VZQ09ERV9ET1dOYAogICAgLSBgcHJlc3MgQ1RSTCtBYAogICAgLSBgcHJlc3MgQ1RSTCtTSElGVCtYYAogICAgLSBgcHJlc3MgQ1RSTCtTSElGVCtBTFQrVmAKICAgIC0gYGtleSBkb3duIFBPV0VSYAogICAgLSBga2V5IHVwIFBPV0VSYAotIOaooeaLn+Wxj+W5leinpuaRuOS6i+S7tgogICAgLSBgdG91Y2ggW2Rvd258dXB8bW92ZV0gW3hdIFt5XWAKICAgIC0g54K55Ye7CiAgICAgICAgLSBgdGFwIHggeWAKICAgICAgICAtIGB0YXAgMzAgNTBgCiAgICAtIOaMieS4iwogICAgICAgIC0gYHRvdWNoIGRvd24geCB5YAogICAgICAgIC0gYHRvdWNoIGRvd24gMzAgNTBgCiAgICAtIOenu+WKqAogICAgICAgIC0gYHRvdWNoIG1vdmUgeCB5YAogICAgICAgIC0gYHRvdWNoIG1vdmUgNTAgNjBgCiAgICAtIOaKrOi1twogICAgICAgIC0gYHRvdWNoIHVwIHggeWAKICAgICAgICAtIGB0b3VjaCB1cCA3MCA4MGAKICAgIC0g5LuO5LiA54K55ruR5Yqo5Yiw5Y+m5LiA54K5CiAgICAgICAgLSBgc2xpZGUgeDEgeTEgeDIgeTIgdGltZSBzdGVwYAogICAgICAgIC0gYHNsaWRlIDMwMCA1MDAgNjAwIDcwMCAyMCAxNmAKLSDkvJHnnKDmjIflrprml7bpl7QKICAgIC0gYHNsZWVwIDEwMjRgCi0g6L6T5YWl5a2X56ym5Liy5paH5pysCiAgICAtIGB0eXBlIDEyMzRgCiAgICAtIGB0eXBlIHN0cmluZ2AKICAgIC0gYHR5cGUgdXNlcm5hbWVgCi0g5aSN5Yi25paH5pys5Yiw5Ymq6LS05p2/CiAgICAtIGBjb3B5IFt0ZXh0fGJhc2U2NHx1cmxlbmNvZGVdIHN0cmluZ2AKICAgIC0g5pmu6YCa5paH5pysCiAgICAgICAgLSBgY29weSB0ZXh0IHN0cmluZ2AKICAgICAgICAtIGBjb3B5IHRleHQgInN0cmluZyBzdHJpbmcgc3RyaW5nImAKICAgIC0gYmFzZTY057yW56CB55qE5paH5pysCiAgICAgICAgLSBgY29weSBiYXNlNjQgNkw2VDVZV2w1TGl0NXBhSDVhMlg1NnltYAogICAgLSBVUkwg57yW56CB55qE5paH5pysCiAgICAgICAgLSBgY29weSB1cmxlbmNvZGUgJUU4JUJFJTkzJUU1JTg1JUE1JUU0JUI4JUFEJUU2JTk2JTg3JUU1JUFEJTk3JUU3JUFDJUE2YAotIOi+k+WFpSoq5Lit5paH5a2X56ymKioKICAgIC0g6YCa6L+H5aSN5Yi25ZKM5qih5ouf5oyJ6ZSu5Y+v5Lul5a6e546w6L6T5YWl5Lit5paH5a2X56ym55qE5Yqf6IO9CiAgICAtIGBjb3B5IGJhc2U2NCA1TGl0NXBhSDVhMlg1NnltYAogICAgLSBgcHJlc3MgS0VZQ09ERV9QQVNURWAKLSDmkq3mlL7pn7PpopEKICAgIC0gYHBsYXkgL21udC9zZGNhcmQvdHRzLm1wM2AKLSDojrflj5bmjqfku7bkv6Hmga8KICAgIC0gYHF1ZXJ5dmlldyBbaWQgdHlwZV0gW2lkKHMpXSBbY29tbWFuZF1gCiAgICAgICAgLSBgaWQgdHlwZWAKICAgICAgICAgICAgLSBgIGAKICAgICAgICAgICAgLSBgdmlld2lkYAogICAgICAgICAgICAtIGBhY2Nlc3NpYmlsaXR5aWRzYAogICAgLSDojrflj5blsY/luZXlpKflsI8KICAgICAgICAtIGBxdWVyeXZpZXcgZ2V0bG9jYXRpb25gID4gYE9LOjAgMCAxNDQwIDI4ODBgCiAgICAtIOS9jee9rgogICAgICAgIC0gYHF1ZXJ5dmlldyB2aWV3aWQgY29tLnh4eC54eHh4OmlkL3h4eHh4IGdldGxvY2F0aW9uYAogICAgICAgIC0gYHF1ZXJ5dmlldyBhY2Nlc3NpYmlsaXR5aWRzIFt3aW5kb3dJZF0gW3ZpZXdJZF0gZ2V0bG9jYXRpb25gCiAgICAgICAgLSBgcXVlcnl2aWV3IGFjY2Vzc2liaWxpdHlpZHMgIDEzODEgICAgICAgODkwICAgICBnZXRsb2NhdGlvbmAKICAgICAgICAtIOekuuS+iwogICAgICAgICAgICBgYGAKICAgICAgICAgICAgPiBxdWVyeXZpZXcgdmlld2lkIGFuZHJvaWQ6aWQvYnV0dG9uMSBnZXRsb2NhdGlvbgogICAgICAgICAgICA8IE9LOjEwODEgMTQ3OSAyMjQgMTg5CiAgICAgICAgICAgIGBgYAogICAgLSDmlofmnKwKICAgICAgICAtIGBxdWVyeXZpZXcgdmlld2lkIGFuZHJvaWQ6aWQvYnV0dG9uMSBnZXR0ZXh0YAogICAgICAgIGBgYAogICAgICAgID4gcXVlcnl2aWV3IHZpZXdpZCBhbmRyb2lkOmlkL2J1dHRvbjEgZ2V0dGV4dAogICAgICAgIDwgT0s656Gu5a6aCiAgICAgICAgYGBgCi0g6I635Y+W5qCR5b2i57uT5p6E55qE55WM6Z2i5o6n5Lu25L+h5oGvCiAgICAtIOaWh+acrOagvOW8jwogICAgICAgIC0gYHF1ZXJ5dmlldyBnZXR0cmVlIHRleHRgCiAgICAgICAgLSBgcXVlcnl2aWV3IHZpZXdpZCBjb20ueHh4Lnh4eHg6aWQveHh4eHggZ2V0dHJlZSB0ZXh0YAogICAgICAgIC0gYHF1ZXJ5dmlldyBhY2Nlc3NpYmlsaXR5aWRzIDEzODEgODkwIGdldHRyZWUgdGV4dGAKICAgIC0ganNvbiDmoLzlvI8KICAgICAgICAtIGBxdWVyeXZpZXcgZ2V0dHJlZSBqc29uYAogICAgICAgIC0gYHF1ZXJ5dmlldyB2aWV3aWQgY29tLnh4eC54eHh4OmlkL3h4eHh4IGdldHRyZWUganNvbmAKICAgICAgICAtIGBxdWVyeXZpZXcgYWNjZXNzaWJpbGl0eWlkcyAxMzgxIDg5MCBnZXR0cmVlIGpzb25gCiAgICAtIOiOt+WPlueVjOmdouWFqOmDqOaOp+S7tuagkeW9oue7k+aehAogICAgICAgIC0gYHF1ZXJ5dmlldyBnZXR0cmVlIHRleHRgCiAgICAgICAgLSBgcXVlcnl2aWV3IGdldHRyZWUganNvbmAKICAgIC0g6I635Y+W5oyH5a6a5o6n5Lu25LiL55qE5o6n5Lu25qCR5b2i57uT5p6ECiAgICAgICAgLSBgcXVlcnl2aWV3IHZpZXdpZCBjb20ueHh4Lnh4eHg6aWQveHh4eHggZ2V0dHJlZSB0ZXh0YAogICAgICAgIC0gYHF1ZXJ5dmlldyBhY2Nlc3NpYmlsaXR5aWRzIDEzODEgODkwIGdldHRyZWUgdGV4dGAKICAgICAgICAtIGBxdWVyeXZpZXcgdmlld2lkIGNvbS54eHgueHh4eDppZC94eHh4eCBnZXR0cmVlIGpzb25gCiAgICAgICAgLSBgcXVlcnl2aWV3IGFjY2Vzc2liaWxpdHlpZHMgMTM4MSA4OTAgZ2V0dHJlZSBqc29uYAotIOaIquWxj+WKn+iDvQogICAgLSDmiKrlj5bnmoTlm77niYfkuLoganBnIOagvOW8j++8jOe7k+aenOmAmui/hyBiYXNlNjQg57yW56CB6L+U5ZueCiAgICAtIGB0YWtlc2NyZWVuc2hvdCBbc2NhbGV8cmVjdHxnZXRjb2xvcnxxdWFsaXR5XWAKICAgIC0g5oiq5Y+W5pW05Liq5bGP5bmVCiAgICAgICAgLSBgdGFrZXNjcmVlbnNob3RgCiAgICAtIOaIquWPluaMh+WumuWMuuWfn+eahOWxj+W5lQogICAgICAgIC0gYHRha2VzY3JlZW5zaG90IHJlY3QgMzAgMzAgNTAgNTBgCiAgICAtIOe8qeaUvuaIquWPlueahOWbvueJhwogICAgICAgIC0gYHRha2VzY3JlZW5zaG90IHNjYWxlIDAuM2AKICAgIC0g6I635Y+W5bGP5bmV5oyH5a6a5Z2Q5qCH55qE5YOP57Sg6aKc6ImyCiAgICAgICAgLSBgdGFrZXNjcmVlbnNob3QgZ2V0Y29sb3IgMzAwIDMzMGAKICAgIC0g6K6+572u5Zu+54mH55qE6LSo6YePCiAgICAgICAgLSBgdGFrZXNjcmVlbnNob3QgcXVhbGl0eSA5MGAKICAgIC0g57uE5ZCI5ZG95LukCiAgICAgICAgLSBgdGFrZXNjcmVlbnNob3QgcmVjdCAzMCAzMCA1MCA1MCBzY2FsZSAwLjUgcXVhbGl0eSA4MGAKICAgICAgICAtIGB0YWtlc2NyZWVuc2hvdCBzY2FsZSAwLjUgcmVjdCAzMCAzMCA1MCA1MCBxdWFsaXR5IDgwYAogICAgICAgIC0gYHRha2VzY3JlZW5zaG90IHF1YWxpdHkgODAgc2NhbGUgMC41IHJlY3QgMzAgMzAgNTAgNTBgCi0g6I635Y+W57O757uf5L+h5oGvCiAgICAtIOWRveS7pOagvOW8jyBgZ2V0dmFyIHZhcm5hbWVgCiAgICAtIGBidWlsZC5ib2FyZGAKICAgICAgICAtIGBnZXR2YXIgYnVpbGQuYm9hcmRgID4gYE9LOmdvbGRmaXNoX3g4NmAKICAgIC0gYGJ1aWxkLmJyYW5kYAogICAgLSBgYnVpbGQuZGV2aWNlYAogICAgLSBgYnVpbGQuZGlzcGxheWAKICAgICAgICAtIGBnZXR2YXIgYnVpbGQuZGlzcGxheWAgPiBgT0s6c2RrX2dwaG9uZV94ODYtdXNlcmRlYnVnIDkgUFNSMS4xODA3MjAuMDkzIDU0NTY0NDYgZGV2LWtleXNgCiAgICAtIGBidWlsZC5maW5nZXJwcmludGAKICAgIC0gYGJ1aWxkLmhvc3RgCiAgICAtIGBidWlsZC5pZGAKICAgIC0gYGJ1aWxkLm1vZGVsYAogICAgLSBgYnVpbGQucHJvZHVjdGAKICAgIC0gYGJ1aWxkLnRhZ3NgCiAgICAtIGBidWlsZC5icmFuZGAKICAgIC0gYGJ1aWxkLnR5cGVgCiAgICAtIGBidWlsZC51c2VyYAogICAgLSBgYnVpbGQuY3B1X2FiaWAKICAgIC0gYGJ1aWxkLm1hbnVmYWN0dXJlcmAKLSDlm57mmL7lrZfnrKbkuLIKICAgIC0g55So5LqO5Zyo6ISa5pys5Lit5ZCM5q2l5pON5L2cCiAgICAtIGBlY2hvIHN0cmluZ2AKLSDojrflj5bnlYzpnaLmmK/lkKbmnInmm7TmlrAKICAgIC0gYGdldGlzdmlld2NoYW5nZWAKLSDojrflj5bpobblsYIgYWN0aXZpdHkKICAgIC0gYGdldHRvcGFjdGl2aXR5YCA+IGBPSzpjb20uZ29vZ2xlLmFuZHJvaWQuYXBwcy5uZXh1c2xhdW5jaGVyL2NvbS5nb29nbGUuYW5kcm9pZC5hcHBzLm5leHVzbGF1bmNoZXIuTmV4dXNMYXVuY2hlckFjdGl2aXR5YAotIOmAgOWHugogICAgLSBgcXVpdGAKCg==";
        return new String(Base64.decode(help, Base64.DEFAULT));
    }
}
