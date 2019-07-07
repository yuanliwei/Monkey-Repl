@echo off
adb push ./monkey_repl.jar /data/local/tmp/monkey_repl.jar
adb shell "export CLASSPATH=/data/local/tmp/monkey_repl.jar && exec app_process /system/bin com.android.commands.monkey.Monkey"

