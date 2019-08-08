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

#### 使用方式
- 使用 usb 连接手机
- 打开手机的 usb 调试模式
- 进入 start 目录，运行 start.cmd 进入交互窗口
- 输入 `queryview getlocation` 按 `enter`
- 输入 `queryview gettree text` 按 `enter`
- 退出 `quit` 按 `enter`

#### 脚本
- 参考 demo 目录

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

#### 功能使用示例
- 模拟按键事件
    - `press KEYCODE_ENTER`
    - `press KEYCODE_PASTE`
    - `press KEYCODE_UP`
    - `press KEYCODE_DOWN`
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
- 退出
    - `quit`

