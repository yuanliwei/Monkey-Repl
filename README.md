# monkey-repl

#### 描述
monkey-repl : 一个通过 usb 控制 Android 设备的自动化工具，修改自 [Android monkey](https://android.googlesource.com/platform/development/+/refs/heads/master/cmds/monkey)。

#### 特性
- 不需要 `root` 权限
- 获取屏幕控件信息
- 截屏、取色
- 模拟按键
- 字符输入、中文输入
- 实时响应命令操作
- 可以通过脚本调用使用方便
- 可以获取 `webview` 中的控件
- 播放音频


#### 构建
- `npm install` 安装依赖
- `npm run build` 编译打包，产物 `resource/monkey_repl.jar`
- 依赖 JDK 21 + Android SDK build-tools（默认 36.1.0，可用 `BUILD_TOOLS_DIR` 环境变量指定）

#### 目录结构
- `java/` Java 源码（`com/` 为核心，`android/`/`dalvik/`/`org/` 为 stub）
- `node/` Node 脚本（`build.js` 编译、`lib.js` 客户端、`lib.test.js` 测试）
- `resource/` 资源（`monkey_repl.jar`、`MANIFEST.MF`、`source_list`）
- `dist/` 构建产物

#### 使用方式
- 使用 usb 连接手机
- 打开手机的 usb 调试模式
- 通过 `node/lib.js` 的 `Controller` 连接设备（自动推送 jar 到 `/data/local/tmp/monkey_repl.jar`）
- 输入 `queryview getlocation` 按 `enter`
- 输入 `queryview gettree text` 按 `enter`
- 退出 `quit` 按 `enter`

**启动参数**
```
monkey-repl --type repl --command_type text --name abc-repl --port 5678 --allow_ip_address 192.168.0.123 --query_view true --activity_controller true
    --type                : [repl|network]  default : repl
    --command_type        : [text|json]     default : text
    --name                : string          default : monkey-repl
    --port                : number          default : 5678
    --allow_ip_address    : string          default : all
    --query_view          : boolean         default : true
    --activity_controller : boolean         default : true
```

#### 脚本
- 参考 `node/lib.test.js`

#### 功能清单
- 模拟按键事件
- 模拟屏幕触摸事件
    - 点击
    - 按下
    - 移动
    - 抬起
    - 从一点滑动到另一点
- 休眠指定时间
- 输入字符串文本
- 复制文本到剪贴板
    - 普通文本
    - base64编码的文本
    - URL 编码的文本
- 输入**中文字符**
    - 通过复制和模拟按键可以实现输入中文字符的功能
    - `copy base64 5Lit5paH5a2X56ym`
    - `press paste`
- 获取控件信息
    - 位置
    - 文本
- 获取树形结构的界面控件信息
    - 文本格式
    - json 格式
    - 获取界面全部控件树形结构
    - 获取指定控件下的控件树形结构
- 截屏功能
    - 截取整个屏幕
    - 截取指定区域的屏幕
    - 缩放截取的图片
    - 获取屏幕指定坐标的像素颜色
- 获取系统信息
    - `build.board`
    - `build.brand`
    - `build.device`
    - `build.display`
    - `build.fingerprint`
    - `build.host`
    - `build.id`
    - `build.model`
    - `build.product`
    - `build.tags`
    - `build.brand`
    - `build.type`
    - `build.user`
    - `build.cpu_abi`
    - `build.manufacturer`
- 回显字符串
    - 用于在脚本中同步操作
- 获取界面是否有更新
- 下载文件
- 文件操作
    - 读文件（base64）
    - 写文件（base64）
    - 删除文件
    - 列目录
    - 创建目录
    - 判断路径存在
- wifi 网络控制（JSON 参数）
    - 开关 WiFi
    - 查看当前网络信息
    - 列出已配置网络
    - 设置静态 IP / DHCP
    - 连接 / 断开 / 忘记网络
- 蓝牙控制（JSON 参数）
    - 开关蓝牙
    - 查看状态/信息
    - 扫描设备
    - 已配对设备列表
    - 设备操作（查询状态/连接/取消配对/删除配对）


#### 功能使用示例
- 模拟按键事件 [KEYCODE](https://github.com/aosp-mirror/platform_frameworks_base/blob/master/core/java/android/view/KeyEvent.java)
    - `press KEYCODE_ENTER`
    - `press KEYCODE_PASTE`
    - `press KEYCODE_UP`
    - `press KEYCODE_DOWN`
    - `press CTRL+A`
    - `press CTRL+SHIFT+X`
    - `press CTRL+SHIFT+ALT+V`
    - `key down POWER`
    - `key up POWER`
- 模拟屏幕触摸事件
    - `touch [down|up|move] [x] [y]`
    - 点击
        - `tap x y`
        - `tap 30 50`
    - 按下
        - `touch down x y`
        - `touch down 30 50`
    - 移动
        - `touch move x y`
        - `touch move 50 60`
    - 抬起
        - `touch up x y`
        - `touch up 70 80`
    - 从一点滑动到另一点
        - `slide x1 y1 x2 y2 time step`
        - `slide 300 500 600 700 20 16`
- 休眠指定时间
    - `sleep 1024`
- 输入字符串文本
    - `type 1234`
    - `type string`
    - `type username`
- 复制文本到剪贴板
    - `copy [text|base64|urlencode] string`
    - 普通文本
        - `copy text string`
        - `copy text "string string string"`
    - base64编码的文本
        - `copy base64 6L6T5YWl5Lit5paH5a2X56ym`
    - URL 编码的文本
        - `copy urlencode %E8%BE%93%E5%85%A5%E4%B8%AD%E6%96%87%E5%AD%97%E7%AC%A6`
- 输入**中文字符**
    - 通过复制和模拟按键可以实现输入中文字符的功能
    - `copy base64 5Lit5paH5a2X56ym`
    - `press KEYCODE_PASTE`
- 播放音频
    - `play /mnt/sdcard/tts.mp3`
- 获取控件信息
    - `queryview [id type] [id(s)] [command]`
        - `id type`
            - ` `
            - `viewid`
            - `accessibilityids`
    - 获取屏幕大小
        - `queryview getlocation` > `OK:0 0 1440 2880`
    - 位置
        - `queryview viewid com.xxx.xxxx:id/xxxxx getlocation`
        - `queryview accessibilityids [windowId] [viewId] getlocation`
        - `queryview accessibilityids  1381       890     getlocation`
        - 示例
            ```
            > queryview viewid android:id/button1 getlocation
            < OK:1081 1479 224 189
            ```
    - 文本
        - `queryview viewid android:id/button1 gettext`
        ```
        > queryview viewid android:id/button1 gettext
        < OK:确定
        ```
- 获取树形结构的界面控件信息
    - 文本格式
        - `queryview gettree text`
        - `queryview viewid com.xxx.xxxx:id/xxxxx gettree text`
        - `queryview accessibilityids 1381 890 gettree text`
    - json 格式
        - `queryview gettree json`
        - `queryview viewid com.xxx.xxxx:id/xxxxx gettree json`
        - `queryview accessibilityids 1381 890 gettree json`
    - 获取界面全部控件树形结构
        - `queryview gettree text`
        - `queryview gettree json`
    - 获取指定控件下的控件树形结构
        - `queryview viewid com.xxx.xxxx:id/xxxxx gettree text`
        - `queryview accessibilityids 1381 890 gettree text`
        - `queryview viewid com.xxx.xxxx:id/xxxxx gettree json`
        - `queryview accessibilityids 1381 890 gettree json`
- 截屏功能
    - 截取的图片为 jpg 格式，结果通过 base64 编码返回
    - `takescreenshot [scale|rect|getcolor|quality]`
    - 截取整个屏幕
        - `takescreenshot`
    - 截取指定区域的屏幕
        - `takescreenshot rect 30 30 50 50`
    - 缩放截取的图片
        - `takescreenshot scale 0.3`
    - 获取屏幕指定坐标的像素颜色
        - `takescreenshot getcolor 300 330`
    - 设置图片的质量
        - `takescreenshot quality 90`
    - 组合命令
        - `takescreenshot rect 30 30 50 50 scale 0.5 quality 80`
        - `takescreenshot scale 0.5 rect 30 30 50 50 quality 80`
        - `takescreenshot quality 80 scale 0.5 rect 30 30 50 50`
- 获取系统信息
    - 命令格式 `getvar varname`
    - `build.board`
        - `getvar build.board` > `OK:goldfish_x86`
    - `build.brand`
    - `build.device`
    - `build.display`
        - `getvar build.display` > `OK:sdk_gphone_x86-userdebug 9 PSR1.180720.093 5456446 dev-keys`
    - `build.fingerprint`
    - `build.host`
    - `build.id`
    - `build.model`
    - `build.product`
    - `build.tags`
    - `build.brand`
    - `build.type`
    - `build.user`
    - `build.cpu_abi`
    - `build.manufacturer`
- 回显字符串
    - 用于在脚本中同步操作
    - `echo string`
- 获取界面是否有更新
    - `getisviewchange`
- 获取顶层 activity
    - `gettopactivity` > `OK:com.google.android.apps.nexuslauncher/com.google.android.apps.nexuslauncher.NexusLauncherActivity`
- 下载文件
    - `download http://example.com/a.txt a.txt`
- 退出
    - `quit`
- 文件操作
    - 读文件（返回 base64）：`readfile /data/local/tmp/a.txt`
    - 写文件（内容 base64）：`writefile /data/local/tmp/a.txt aGVsbG8=`
    - 删除文件：`deletefile /data/local/tmp/a.txt`
    - 列目录（返回 JSON 数组）：`listdir /data/local/tmp`
    - 创建目录：`mkdir /data/local/tmp/mydir`
    - 判断存在：`exists /data/local/tmp/a.txt` > `OK:true`
- wifi 网络控制（JSON 参数，调 framework API）
    - 开/关 WiFi：`wifi {"action":"on"}` / `wifi {"action":"off"}`
    - 当前网络信息：`wifi {"action":"info"}` > `{"ssid":..,"ip":..,"gateway":..,"dns1":..}`
    - 已配置网络列表：`wifi {"action":"list"}`
    - 设置静态 IP：`wifi {"action":"setStatic","ssid":"MyWifi","ip":"192.168.1.100","prefix":24,"gateway":"192.168.1.1","dns":["8.8.8.8"]}`
    - 切换 DHCP：`wifi {"action":"setDhcp","ssid":"MyWifi"}`
    - 连接网络：`wifi {"action":"connect","ssid":"MyWifi"}`
    - 断开：`wifi {"action":"disconnect"}`
    - 忘记网络：`wifi {"action":"forget","networkId":13}`
    - 启用/禁用：`wifi {"action":"enable","networkId":13}` / `wifi {"action":"disable","networkId":13}`
    - 新建/保存：`wifi {"action":"save","ssid":"MyWifi","password":"12345678"}`

- 蓝牙控制（JSON 参数，调 framework API）
    - 开关：`bluetooth {"action":"on"}` / `bluetooth {"action":"off"}`
    - 状态：`bluetooth {"action":"state"}` > `ON`
    - 信息：`bluetooth {"action":"info"}` > `{"state":..,"address":..,"name":..}`
    - 开始/停止扫描：`bluetooth {"action":"scan","enable":true}` / `bluetooth {"action":"scan","enable":false}`
    - 是否扫描中：`bluetooth {"action":"scanning"}` > `true`
    - 已配对设备列表：`bluetooth {"action":"devices"}`
    - 设备操作：`bluetooth {"action":"bond","op":"state","address":"AA:BB:CC:DD:EE:FF"}`（op: state/connect/remove/cancel）
    - 注：配对新设备（op=create）在 app_process 下不可用（BluetoothDevice 构造为 hidden API）



#### 系统服务命令（framework API）

基于 `ServiceManager` + `IXxx.Stub.asInterface` 的 Binder 底座（无需 Context）：

- **wifi** 网络（开关/信息/列表/静态IP/DHCP/连接）
- **bluetooth** 蓝牙（开关/信息/扫描/已配对设备/配对状态）
- **power** 电源（重启/锁屏/唤醒/亮屏状态/省电模式）
- **audio** 音量（各 stream 获取/设置/调节/静音/铃声模式）
- **net** 网络（连接状态/类型/计量/飞行模式）
- **clipboard** 剪贴板（读取/是否有/清除）
- **notification** 通知（是否启用/清除；列表受权限限制，用 shell+dumpsys）
- **display** 显示（亮度/分辨率/DPI/旋转）
- **wait** 等待条件（Activity/控件文本/控件id 出现或消失，带超时）

#### 通用执行命令

- **shell** 执行 shell 命令：`shell {"cmd":"..."}` > `{exitCode, output}`
- **cmd** 执行 `cmd <service>`：`cmd {"service":"vibrator_manager","args":"list"}`
- **app** 应用管理：`app {"action":"start|stop|current|list","package":"..."}`

#### 用 shell / cmd 覆盖的功能（无需专门命令）

- **媒体控制**：`shell {"cmd":"input keyevent 85"}`（播放/暂停 85，下一首 87，上一首 88）
- **包管理**：`shell {"cmd":"pm list packages"}` / `pm path <pkg>` / `pm dump <pkg>`
- **电池**：`shell {"cmd":"dumpsys battery"}`
- **温度**：`shell {"cmd":"dumpsys thermalservice"}`
- **传感器**：`shell {"cmd":"dumpsys sensorservice"}`
- **定位**：`cmd {"service":"location","args":"is-location-enabled"}`
- **电话/SIM**：`shell {"cmd":"dumpsys telephony.registry"}`
- **输入法**：`shell {"cmd":"ime list -s"}` / `ime enable <id>` / `ime set <id>`
- **震动**：`cmd {"service":"vibrator_manager","args":"feedback LONG_PRESS"}`
- **HTTP**：`shell {"cmd":"curl -s http://..."}`（POST: `curl -X POST -d '...' url`）
- **录屏**：`shell {"cmd":"screenrecord --time-limit 10 /sdcard/a.mp4"}`
- **安装/卸载 APK**：`shell {"cmd":"pm install /data/local/tmp/a.apk"}` / `pm uninstall <pkg>`

#### 架构考虑

系统服务命令增多后，可评估是否统一为 `service {"name":"power","action":...}` 形式（当前每服务独立命令）。
