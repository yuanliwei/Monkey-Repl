
# clean build translate push run
javac -encoding UTF-8 -d build/ "@source_list"
jar -cfm ./java_build.jar MANIFEST.MF -C build/ com/
dx --dex --output=classes.dex ./java_build.jar
aapt add monkey_repl.jar classes.dex
adb push ./monkey_repl.jar /data/local/tmp/monkey_repl.jar
pause
# remove temp files
rm monkey_repl.jar
rm java_build.jar
rm classes.dex
adb shell "export CLASSPATH=/data/local/tmp/monkey_repl.jar && exec app_process /system/bin com.android.commands.monkey.Monkey"

