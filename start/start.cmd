@echo off
adb push ./monkey_repl.jar /data/local/tmp/monkey_repl.jar
adb shell "export CLASSPATH=/data/local/tmp/monkey_repl.jar && exec app_process /system/bin com.android.commands.monkey.Monkey"

@REM adb shell "export CLASSPATH=/data/local/tmp/monkey_repl.jar && exec nohup app_process /system/bin com.android.commands.monkey.Monkey"
@REM adb shell "export CLASSPATH=/data/local/tmp/monkey_repl.jar && exec app_process /system/bin com.android.commands.monkey.Monkey --type repl --command_type text --name abc-repl --port 5678 --allow_ip_address 192.168.0.123 --query_view true --activity_controller true"
@REM adb shell "export CLASSPATH=/data/local/tmp/monkey_repl.jar && exec app_process /system/bin com.android.commands.monkey.Monkey --type udp --command_type json --name abc-repl --port 5678 --allow_ip_address 192.168.* --query_view true --activity_controller true"
@REM adb shell "export CLASSPATH=/data/local/tmp/monkey_repl.jar && exec app_process /system/bin com.android.commands.monkey.Monkey --type tcp --command_type json --name abc-repl --port 5678 --allow_ip_address 192.168.* --query_view true --activity_controller true"
@REM adb shell "export CLASSPATH=/data/local/tmp/monkey_repl.jar && exec app_process /system/bin com.android.commands.monkey.Monkey --type http --command_type json --name abc-repl --port 5678 --allow_ip_address 192.168.* --query_view true --activity_controller true"
