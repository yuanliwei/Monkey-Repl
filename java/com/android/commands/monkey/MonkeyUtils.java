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
        String help = "# monkey-repl\r\n" + //
                "\r\n" + //
                "#### 描述\r\n" + //
                "monkey-repl : 一个通过 usb 控制 Android 设备的自动化工具，修改自 [Android monkey](https://android.googlesource.com/platform/development/+/refs/heads/master/cmds/monkey)。\r\n"
                + //
                "\r\n" + //
                "#### 特性\r\n" + //
                "- 不需要 `root` 权限\r\n" + //
                "- 获取屏幕控件信息\r\n" + //
                "- 截屏、取色\r\n" + //
                "- 模拟按键\r\n" + //
                "- 字符输入、中文输入\r\n" + //
                "- 实时响应命令操作\r\n" + //
                "- 可以通过脚本调用使用方便\r\n" + //
                "- 可以获取 `webview` 中的控件\r\n" + //
                "- 播放音频\r\n" + //
                "\r\n" + //
                "#### 使用方式\r\n" + //
                "- 使用 usb 连接手机\r\n" + //
                "- 打开手机的 usb 调试模式\r\n" + //
                "- 进入 start 目录，运行 start.cmd 进入交互窗口\r\n" + //
                "- 输入 `queryview getlocation` 按 `enter`\r\n" + //
                "- 输入 `queryview gettree text` 按 `enter`\r\n" + //
                "- 退出 `quit` 按 `enter`\r\n" + //
                "\r\n" + //
                "**启动参数**\r\n" + //
                "```\r\n" + //
                "monkey-repl --type repl --command_type text --name abc-repl --port 5678 --allow_ip_address 192.168.0.123 --query_view true --activity_controller true\r\n"
                + //
                "    --type                : [repl|network]  default : repl\r\n" + //
                "    --command_type        : [text|json]     default : text\r\n" + //
                "    --name                : string          default : monkey-repl\r\n" + //
                "    --port                : number          default : 5678\r\n" + //
                "    --allow_ip_address    : string          default : all\r\n" + //
                "    --query_view          : boolean         default : true\r\n" + //
                "    --activity_controller : boolean         default : true\r\n" + //
                "```\r\n" + //
                "\r\n" + //
                "#### 脚本\r\n" + //
                "- 参考 demo 目录\r\n" + //
                "\r\n" + //
                "#### 功能清单\r\n" + //
                "- 模拟按键事件\r\n" + //
                "- 模拟屏幕触摸事件\r\n" + //
                "    - 点击\r\n" + //
                "    - 按下\r\n" + //
                "    - 移动\r\n" + //
                "    - 抬起\r\n" + //
                "    - 从一点滑动到另一点\r\n" + //
                "- 休眠指定时间\r\n" + //
                "- 输入字符串文本\r\n" + //
                "- 复制文本到剪贴板\r\n" + //
                "    - 普通文本\r\n" + //
                "    - base64编码的文本\r\n" + //
                "    - URL 编码的文本\r\n" + //
                "- 输入**中文字符**\r\n" + //
                "    - 通过复制和模拟按键可以实现输入中文字符的功能\r\n" + //
                "    - `copy base64 5Lit5paH5a2X56ym`\r\n" + //
                "    - `press paste`\r\n" + //
                "- 获取控件信息\r\n" + //
                "    - 位置\r\n" + //
                "    - 文本\r\n" + //
                "- 获取树形结构的界面控件信息\r\n" + //
                "    - 文本格式\r\n" + //
                "    - json 格式\r\n" + //
                "    - 获取界面全部控件树形结构\r\n" + //
                "    - 获取指定控件下的控件树形结构\r\n" + //
                "- 截屏功能\r\n" + //
                "    - 截取整个屏幕\r\n" + //
                "    - 截取指定区域的屏幕\r\n" + //
                "    - 缩放截取的图片\r\n" + //
                "    - 获取屏幕指定坐标的像素颜色\r\n" + //
                "- 获取系统信息\r\n" + //
                "    - `build.board`\r\n" + //
                "    - `build.brand`\r\n" + //
                "    - `build.device`\r\n" + //
                "    - `build.display`\r\n" + //
                "    - `build.fingerprint`\r\n" + //
                "    - `build.host`\r\n" + //
                "    - `build.id`\r\n" + //
                "    - `build.model`\r\n" + //
                "    - `build.product`\r\n" + //
                "    - `build.tags`\r\n" + //
                "    - `build.brand`\r\n" + //
                "    - `build.type`\r\n" + //
                "    - `build.user`\r\n" + //
                "    - `build.cpu_abi`\r\n" + //
                "    - `build.manufacturer`\r\n" + //
                "- 回显字符串\r\n" + //
                "    - 用于在脚本中同步操作\r\n" + //
                "- 获取界面是否有更新\r\n" + //
                "- 下载文件\r\n" + //
                "\r\n" + //
                "#### 功能使用示例\r\n" + //
                "- 模拟按键事件 [KEYCODE](https://github.com/aosp-mirror/platform_frameworks_base/blob/master/core/java/android/view/KeyEvent.java)\r\n"
                + //
                "    - `press KEYCODE_ENTER`\r\n" + //
                "    - `press KEYCODE_PASTE`\r\n" + //
                "    - `press KEYCODE_UP`\r\n" + //
                "    - `press KEYCODE_DOWN`\r\n" + //
                "    - `press CTRL+A`\r\n" + //
                "    - `press CTRL+SHIFT+X`\r\n" + //
                "    - `press CTRL+SHIFT+ALT+V`\r\n" + //
                "    - `key down POWER`\r\n" + //
                "    - `key up POWER`\r\n" + //
                "- 模拟屏幕触摸事件\r\n" + //
                "    - `touch [down|up|move] [x] [y]`\r\n" + //
                "    - 点击\r\n" + //
                "        - `tap x y`\r\n" + //
                "        - `tap 30 50`\r\n" + //
                "    - 按下\r\n" + //
                "        - `touch down x y`\r\n" + //
                "        - `touch down 30 50`\r\n" + //
                "    - 移动\r\n" + //
                "        - `touch move x y`\r\n" + //
                "        - `touch move 50 60`\r\n" + //
                "    - 抬起\r\n" + //
                "        - `touch up x y`\r\n" + //
                "        - `touch up 70 80`\r\n" + //
                "    - 从一点滑动到另一点\r\n" + //
                "        - `slide x1 y1 x2 y2 time step`\r\n" + //
                "        - `slide 300 500 600 700 20 16`\r\n" + //
                "- 休眠指定时间\r\n" + //
                "    - `sleep 1024`\r\n" + //
                "- 输入字符串文本\r\n" + //
                "    - `type 1234`\r\n" + //
                "    - `type string`\r\n" + //
                "    - `type username`\r\n" + //
                "- 复制文本到剪贴板\r\n" + //
                "    - `copy [text|base64|urlencode] string`\r\n" + //
                "    - 普通文本\r\n" + //
                "        - `copy text string`\r\n" + //
                "        - `copy text \"string string string\"`\r\n" + //
                "    - base64编码的文本\r\n" + //
                "        - `copy base64 6L6T5YWl5Lit5paH5a2X56ym`\r\n" + //
                "    - URL 编码的文本\r\n" + //
                "        - `copy urlencode %E8%BE%93%E5%85%A5%E4%B8%AD%E6%96%87%E5%AD%97%E7%AC%A6`\r\n" + //
                "- 输入**中文字符**\r\n" + //
                "    - 通过复制和模拟按键可以实现输入中文字符的功能\r\n" + //
                "    - `copy base64 5Lit5paH5a2X56ym`\r\n" + //
                "    - `press KEYCODE_PASTE`\r\n" + //
                "- 播放音频\r\n" + //
                "    - `play /mnt/sdcard/tts.mp3`\r\n" + //
                "- 获取控件信息\r\n" + //
                "    - `queryview [id type] [id(s)] [command]`\r\n" + //
                "        - `id type`\r\n" + //
                "            - ` `\r\n" + //
                "            - `viewid`\r\n" + //
                "            - `accessibilityids`\r\n" + //
                "    - 获取屏幕大小\r\n" + //
                "        - `queryview getlocation` > `OK:0 0 1440 2880`\r\n" + //
                "    - 位置\r\n" + //
                "        - `queryview viewid com.xxx.xxxx:id/xxxxx getlocation`\r\n" + //
                "        - `queryview accessibilityids [windowId] [viewId] getlocation`\r\n" + //
                "        - `queryview accessibilityids  1381       890     getlocation`\r\n" + //
                "        - 示例\r\n" + //
                "            ```\r\n" + //
                "            > queryview viewid android:id/button1 getlocation\r\n" + //
                "            < OK:1081 1479 224 189\r\n" + //
                "            ```\r\n" + //
                "    - 文本\r\n" + //
                "        - `queryview viewid android:id/button1 gettext`\r\n" + //
                "        ```\r\n" + //
                "        > queryview viewid android:id/button1 gettext\r\n" + //
                "        < OK:确定\r\n" + //
                "        ```\r\n" + //
                "- 获取树形结构的界面控件信息\r\n" + //
                "    - 文本格式\r\n" + //
                "        - `queryview gettree text`\r\n" + //
                "        - `queryview viewid com.xxx.xxxx:id/xxxxx gettree text`\r\n" + //
                "        - `queryview accessibilityids 1381 890 gettree text`\r\n" + //
                "    - json 格式\r\n" + //
                "        - `queryview gettree json`\r\n" + //
                "        - `queryview viewid com.xxx.xxxx:id/xxxxx gettree json`\r\n" + //
                "        - `queryview accessibilityids 1381 890 gettree json`\r\n" + //
                "    - 获取界面全部控件树形结构\r\n" + //
                "        - `queryview gettree text`\r\n" + //
                "        - `queryview gettree json`\r\n" + //
                "    - 获取指定控件下的控件树形结构\r\n" + //
                "        - `queryview viewid com.xxx.xxxx:id/xxxxx gettree text`\r\n" + //
                "        - `queryview accessibilityids 1381 890 gettree text`\r\n" + //
                "        - `queryview viewid com.xxx.xxxx:id/xxxxx gettree json`\r\n" + //
                "        - `queryview accessibilityids 1381 890 gettree json`\r\n" + //
                "- 截屏功能\r\n" + //
                "    - 截取的图片为 jpg 格式，结果通过 base64 编码返回\r\n" + //
                "    - `takescreenshot [scale|rect|getcolor|quality]`\r\n" + //
                "    - 截取整个屏幕\r\n" + //
                "        - `takescreenshot`\r\n" + //
                "    - 截取指定区域的屏幕\r\n" + //
                "        - `takescreenshot rect 30 30 50 50`\r\n" + //
                "    - 缩放截取的图片\r\n" + //
                "        - `takescreenshot scale 0.3`\r\n" + //
                "    - 获取屏幕指定坐标的像素颜色\r\n" + //
                "        - `takescreenshot getcolor 300 330`\r\n" + //
                "    - 设置图片的质量\r\n" + //
                "        - `takescreenshot quality 90`\r\n" + //
                "    - 组合命令\r\n" + //
                "        - `takescreenshot rect 30 30 50 50 scale 0.5 quality 80`\r\n" + //
                "        - `takescreenshot scale 0.5 rect 30 30 50 50 quality 80`\r\n" + //
                "        - `takescreenshot quality 80 scale 0.5 rect 30 30 50 50`\r\n" + //
                "- 获取系统信息\r\n" + //
                "    - 命令格式 `getvar varname`\r\n" + //
                "    - `build.board`\r\n" + //
                "        - `getvar build.board` > `OK:goldfish_x86`\r\n" + //
                "    - `build.brand`\r\n" + //
                "    - `build.device`\r\n" + //
                "    - `build.display`\r\n" + //
                "        - `getvar build.display` > `OK:sdk_gphone_x86-userdebug 9 PSR1.180720.093 5456446 dev-keys`\r\n"
                + //
                "    - `build.fingerprint`\r\n" + //
                "    - `build.host`\r\n" + //
                "    - `build.id`\r\n" + //
                "    - `build.model`\r\n" + //
                "    - `build.product`\r\n" + //
                "    - `build.tags`\r\n" + //
                "    - `build.brand`\r\n" + //
                "    - `build.type`\r\n" + //
                "    - `build.user`\r\n" + //
                "    - `build.cpu_abi`\r\n" + //
                "    - `build.manufacturer`\r\n" + //
                "- 回显字符串\r\n" + //
                "    - 用于在脚本中同步操作\r\n" + //
                "    - `echo string`\r\n" + //
                "- 获取界面是否有更新\r\n" + //
                "    - `getisviewchange`\r\n" + //
                "- 获取顶层 activity\r\n" + //
                "    - `gettopactivity` > `OK:com.google.android.apps.nexuslauncher/com.google.android.apps.nexuslauncher.NexusLauncherActivity`\r\n"
                + //
                "- 下载文件\r\n" + //
                "    - `download http://example.com/a.txt a.txt`\r\n" + //
                "- 退出\r\n" + //
                "    - `quit`\r\n" + //
                "\r\n" + //
                "";
        return help;
    }
}
