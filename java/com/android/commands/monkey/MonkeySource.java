/*
 * Copyright 2009, The Android Open Source Project
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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

import com.android.commands.monkey.IOWrapper.Command;

import android.app.ActivityManager.RunningTaskInfo;
import android.app.IActivityManager;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.IClipboard;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IPowerManager;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * An Event source for getting Monkey Shell Script commands from over the shell.
 */
public class MonkeySource implements MonkeyEventSource {
    private static final String TAG = "MonkeyStub";
    /* The version of the monkey shell protocol */
    public static final int MONKEY_SHELL_VERSION = 1;

    // This maps from command names to command implementations.
    private static final Map<String, MonkeyCommand> COMMAND_MAP = new HashMap<String, MonkeyCommand>();

    static {
        // Add in all the commands we support
        COMMAND_MAP.put("flip", new FlipCommand());
        COMMAND_MAP.put("touch", new TouchCommand());
        COMMAND_MAP.put("trackball", new TrackballCommand());
        COMMAND_MAP.put("key", new KeyCommand());
        COMMAND_MAP.put("sleep", new SleepCommand());
        COMMAND_MAP.put("wake", new WakeCommand());
        COMMAND_MAP.put("tap", new TapCommand());
        COMMAND_MAP.put("press", new PressCommand());
        COMMAND_MAP.put("type", new TypeCommand());
        COMMAND_MAP.put("copy", new CopyCommand());
        COMMAND_MAP.put("slide", new SlideCommand());
        COMMAND_MAP.put("listvar", new MonkeySourceVars.ListVarCommand());
        COMMAND_MAP.put("getvar", new MonkeySourceVars.GetVarCommand());
        COMMAND_MAP.put("queryview", new MonkeySourceViews.QueryViewCommand());
        COMMAND_MAP.put("getrootview", new MonkeySourceViews.GetRootViewCommand());
        COMMAND_MAP.put("getisviewchange", new MonkeySourceViews.GetIsChangeCommand());
        COMMAND_MAP.put("getviewswithtext", new MonkeySourceViews.GetViewsWithTextCommand());
        COMMAND_MAP.put("takescreenshot", new MonkeySourceViews.TakeScreenshot());
        COMMAND_MAP.put("echo", new EchoCommand());
        COMMAND_MAP.put("gettopactivity", new GetTopActivityCommand());
        COMMAND_MAP.put("play", new PlayAudioCommand());
        COMMAND_MAP.put("help", new HelpCommand());
        COMMAND_MAP.put("download", new DownloadCommand());
    }

    /**
     * ReturnValue from the MonkeyCommand that indicates whether the command was
     * sucessful or not.
     */
    public static class MonkeyCommandReturn {
        private final boolean success;
        private final String message;

        public MonkeyCommandReturn(boolean success) {
            this.success = success;
            this.message = null;
        }

        public MonkeyCommandReturn(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        boolean hasMessage() {
            return message != null;
        }

        String getMessage() {
            return message;
        }

        boolean wasSuccessful() {
            return success;
        }
    }

    public final static MonkeyCommandReturn OK = new MonkeyCommandReturn(true);
    public final static MonkeyCommandReturn ERROR = new MonkeyCommandReturn(false);
    public final static MonkeyCommandReturn EARG = new MonkeyCommandReturn(false, "Invalid Argument");

    /**
     * Interface that MonkeyCommands must implement.
     */
    public interface MonkeyCommand {
        /**
         * Translate the command line into a sequence of MonkeyEvents.
         *
         * @param command the command line.
         * @param queue   the command queue.
         * @return MonkeyCommandReturn indicating what happened.
         */
        MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue);
    }

    /**
     * Command to simulate closing and opening the keyboard.
     */
    private static class FlipCommand implements MonkeyCommand {
        // flip open
        // flip closed
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            if (command.size() > 1) {
                String direction = command.get(1);
                if ("open".equals(direction)) {
                    queue.enqueueEvent(new MonkeyFlipEvent(true));
                    return OK;
                } else if ("close".equals(direction)) {
                    queue.enqueueEvent(new MonkeyFlipEvent(false));
                    return OK;
                }
            }
            return EARG;
        }
    }

    /**
     * Command to send touch events to the input system.
     */
    private static class TouchCommand implements MonkeyCommand {
        // touch [down|up|move] [x] [y]
        // touch down 120 120
        // touch move 140 140
        // touch up 140 140
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            if (command.size() == 4) {
                String actionName = command.get(1);
                int x = 0;
                int y = 0;
                try {
                    x = Integer.parseInt(command.get(2));
                    y = Integer.parseInt(command.get(3));
                } catch (NumberFormatException e) {
                    // Ok, it wasn't a number
                    Log.e(TAG, "Got something that wasn't a number", e);
                    return EARG;
                }

                // figure out the action
                int action = -1;
                long downTime = 0;
                if ("down".equals(actionName)) {
                    action = MotionEvent.ACTION_DOWN;
                    downTime = SystemClock.uptimeMillis();
                } else if ("up".equals(actionName)) {
                    action = MotionEvent.ACTION_UP;
                } else if ("move".equals(actionName)) {
                    action = MotionEvent.ACTION_MOVE;
                }
                if (action == -1) {
                    Log.e(TAG, "Got a bad action: " + actionName);
                    return EARG;
                }

                if (downTime > 0) {
                    queue.enqueueEvent(new MonkeyTouchEvent(action).addPointer(0, x, y).setDownTime(downTime));
                } else {
                    queue.enqueueEvent(new MonkeyTouchEvent(action).addPointer(0, x, y));
                }
                return OK;
            }
            return EARG;
        }
    }

    /**
     * Command to send Trackball events to the input system.
     */
    private static class TrackballCommand implements MonkeyCommand {
        // trackball [dx] [dy]
        // trackball 1 0 -- move right
        // trackball -1 0 -- move left
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            if (command.size() == 3) {
                int dx = 0;
                int dy = 0;
                try {
                    dx = Integer.parseInt(command.get(1));
                    dy = Integer.parseInt(command.get(2));
                } catch (NumberFormatException e) {
                    // Ok, it wasn't a number
                    Log.e(TAG, "Got something that wasn't a number", e);
                    return EARG;
                }
                queue.enqueueEvent(new MonkeyTrackballEvent(MotionEvent.ACTION_MOVE).addPointer(0, dx, dy));
                return OK;

            }
            return EARG;
        }
    }

    /**
     * Command to send Key events to the input system.
     */
    private static class KeyCommand implements MonkeyCommand {
        // key [down|up] [keycode]
        // key down 82
        // key up 82
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            if (command.size() == 3) {
                // CTRL SHIFT ALT META
                String keyString = command.get(2);
                int metaState = 0;
                if (keyString.length() > 3 && keyString.contains("+")) {
                    String[] arr = keyString.split("\\+");
                    for (String item : arr) {
                        if ("CTRL".equals(item.toUpperCase())) {
                            metaState |= KeyEvent.META_CTRL_ON;
                        } else if ("SHIFT".equals(item.toUpperCase())) {
                            metaState |= KeyEvent.META_SHIFT_ON;
                        } else if ("ALT".equals(item.toUpperCase())) {
                            metaState |= KeyEvent.META_ALT_ON;
                        } else if ("META".equals(item.toUpperCase())) {
                            metaState |= KeyEvent.META_META_ON;
                        } else {
                            keyString = item;
                        }
                    }
                }
                int keyCode = getKeyCode(keyString);
                if (keyCode < 0) {
                    // Ok, you gave us something bad.
                    Log.e(TAG, "Can't find keyname: " + command.get(2));
                    return EARG;
                }
                Log.d(TAG, "keycode: " + keyCode);
                int action = -1;
                if ("down".equals(command.get(1))) {
                    action = KeyEvent.ACTION_DOWN;
                } else if ("up".equals(command.get(1))) {
                    action = KeyEvent.ACTION_UP;
                }
                if (action == -1) {
                    Log.e(TAG, "got unknown action.");
                    return EARG;
                }
                queue.enqueueEvent(
                        new MonkeyKeyEvent(-1, -1, action, keyCode, 0, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD, 0));
                return OK;
            }
            return EARG;
        }
    }

    /**
     * Get an integer keycode value from a given keyname.
     *
     * @param keyName the key name to get the code for
     * @return the integer keycode value, or -1 on error.
     */
    private static int getKeyCode(String keyName) {
        int keyCode = -1;
        try {
            keyCode = Integer.parseInt(keyName);
        } catch (NumberFormatException e) {
            // Ok, it wasn't a number, see if we have a
            // keycode name for it
            keyCode = KeyEvent.keyCodeFromString(keyName);
            if (keyCode == KeyEvent.KEYCODE_UNKNOWN) {
                // OK, one last ditch effort to find a match.
                // Build the KEYCODE_STRING from the string
                // we've been given and see if that key
                // exists. This would allow you to do "key
                // down menu", for example.
                keyCode = KeyEvent.keyCodeFromString("KEYCODE_" + keyName.toUpperCase());
                if (keyCode == KeyEvent.KEYCODE_UNKNOWN) {
                    // Still unknown
                    keyCode = KeyEvent.keyCodeFromString("KEYCODE_DPAD_" + keyName.toUpperCase());
                    if (keyCode == KeyEvent.KEYCODE_UNKNOWN) {
                        // Still unknown
                        keyCode = KeyEvent.keyCodeFromString("KEYCODE_MEDIA_" + keyName.toUpperCase());
                        if (keyCode == KeyEvent.KEYCODE_UNKNOWN) {
                            // Still unknown
                            return -1;
                        }
                    }
                }
            }
        }
        return keyCode;
    }

    /**
     * Command to put the Monkey to sleep.
     */
    private static class SleepCommand implements MonkeyCommand {
        // sleep 2000
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            if (command.size() == 2) {
                int sleep = -1;
                String sleepStr = command.get(1);
                try {
                    sleep = Integer.parseInt(sleepStr);
                } catch (NumberFormatException e) {
                    Log.e(TAG, "Not a number: " + sleepStr, e);
                    return EARG;
                }
                queue.enqueueEvent(new MonkeyThrottleEvent(sleep));
                return OK;
            }
            return EARG;
        }
    }

    /**
     * Command to type a string
     */
    private static class TypeCommand implements MonkeyCommand {
        // wake
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            if (command.size() == 2) {
                String str = command.get(1);

                char[] chars = str.toString().toCharArray();

                // Convert the string to an array of KeyEvent's for
                // the built in keymap.
                KeyCharacterMap keyCharacterMap = KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD);
                KeyEvent[] events = keyCharacterMap.getEvents(chars);

                // enqueue all the events we just got.
                for (KeyEvent event : events) {
                    queue.enqueueEvent(new MonkeyKeyEvent(event));
                }
                return OK;
            }
            return EARG;
        }
    }

    /**
     * Command to copy a string
     */
    private static class CopyCommand implements MonkeyCommand {
        // copy [text|base64|urlencode] string
        // copy text string
        // copy base64 base64string
        // copy urlencode urlencodestring
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {

            try {
                String type = "text";
                String payload = "";
                for (int i = 0; i < command.size(); i++) {
                    String arg = command.get(i);
                    if ("text".equals(arg)) {
                        type = "text";
                        payload = command.get(++i);
                    } else if ("base64".equals(arg)) {
                        type = "base64";
                        payload = command.get(++i);
                    } else if ("urlencode".equals(arg)) {
                        type = "urlencode";
                        payload = command.get(++i);
                    }
                }

                if ("text".equals(type)) {
                } else if ("base64".equals(type)) {
                    payload = new String(Base64.decode(payload, Base64.DEFAULT));
                } else if ("urlencode".equals(type)) {
                    payload = URLDecoder.decode(payload, "UTF-8");
                } else {
                    return new MonkeyCommandReturn(false, "error type : " + type);
                }

                IClipboard clipboard = IClipboard.Stub
                        .asInterface(ServiceManager.getService(Context.CLIPBOARD_SERVICE));
                ClipData clip = ClipData.newPlainText("label", payload);
                try {
                    clipboard.setPrimaryClip(clip, "com.android.shell", 0);
                } catch (Exception e) {
                    clipboard.setPrimaryClip(clip, "com.android.shell");
                }

                return OK;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return new MonkeyCommandReturn(false, e.getMessage());
            }
        }
    }

    /**
     * Command slide from point to point on time with step
     */
    private static class SlideCommand implements MonkeyCommand {
        // slide x1 y1 x2 y2 time step
        // slide 300 500 600 700 20 16
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {

            try {
                int x1, y1, x2, y2, time, step;
                Logger.out.println("command size : " + command.size());
                if (command.size() != 7) {
                    return EARG;
                }
                int i = 1;
                x1 = Integer.parseInt(command.get(i++));
                y1 = Integer.parseInt(command.get(i++));
                x2 = Integer.parseInt(command.get(i++));
                y2 = Integer.parseInt(command.get(i++));
                time = Integer.parseInt(command.get(i++));
                step = Integer.parseInt(command.get(i++));

                int sleep = time / step;
                if (sleep == 0) {
                    sleep = 1;
                }
                int xRange = (x2 - x1) / step;
                int yRange = (y2 - y1) / step;

                i = 0;
                queue.enqueueEvent(new MonkeyTouchEvent(MotionEvent.ACTION_DOWN).addPointer(0, x1, y1));
                queue.enqueueEvent(new MonkeyThrottleEvent(sleep));
                while (i++ < step) {
                    queue.enqueueEvent(new MonkeyTouchEvent(MotionEvent.ACTION_MOVE).addPointer(0,
                            (int) (x1 + i * xRange), (int) (y1 + i * yRange)));
                    queue.enqueueEvent(new MonkeyThrottleEvent(sleep));
                }
                queue.enqueueEvent(new MonkeyTouchEvent(MotionEvent.ACTION_UP).addPointer(0, x2, y2));

                return OK;
            } catch (Exception e) {
                e.printStackTrace();
                return new MonkeyCommandReturn(false, e.getMessage());
            }
        }
    }

    /**
     * Command to echo a string
     */
    private static class EchoCommand implements MonkeyCommand {
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            String arg = command.get(1);
            return new MonkeyCommandReturn(true, arg);
        }
    }

    /**
     * Command to get top activity class
     */
    private static class GetTopActivityCommand implements MonkeyCommand {
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            String className = "";
            List<RunningTaskInfo> tasks = new ArrayList<>();
            try {
                tasks = mAm.getTasks(1);
            } catch (Throwable e) {
                try {
                    tasks = mAm.getTasks(1, 0);
                } catch (Throwable e1) {
                    try {
                        tasks = mAm.getTasks(1, 0, null);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (tasks.size() > 0) {
                ComponentName componentName = tasks.get(0).topActivity;
                className = componentName.getPackageName() + "/" + componentName.getClassName();
            }
            return new MonkeyCommandReturn(true, className);
        }
    }

    /**
     * Command to play audio
     */
    private static class PlayAudioCommand implements MonkeyCommand {

        MediaPlayer player = null;

        // play /mnt/sdcard/tts.mp3

        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            int duration = 0;
            try {
                if (player != null) {
                    try {
                        player.reset();
                        player.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    player = null;
                }
                player = new MediaPlayer();
                String audio = command.get(1);
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    player.setDataSource(audio);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                player.prepare();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Logger.out.println("player onCompletion!");
                    }
                });
                player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        Logger.err.println("MediaPlayer ERROR : what:" + what + " extra:" + extra + "");
                        return false;
                    }
                });
                player.start();
                duration = player.getDuration();
            } catch (Exception e) {
                e.printStackTrace();
                return EARG;
            }
            return new MonkeyCommandReturn(true, String.valueOf(duration));
        }
    }

    /**
     * Command help
     */
    private static class HelpCommand implements MonkeyCommand {

        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            return new MonkeyCommandReturn(true, MonkeyUtils.getHelp());
        }
    }

    /**
     * Command Download
     */
    private static class DownloadCommand implements MonkeyCommand {

        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            try {

                String url = command.get(1);
                String name = command.get(2);

                File outFile = new File(name);
                if (!name.startsWith("/")) {
                    outFile = new File("/data/local/tmp/" + name);
                }
                Log.d(TAG, "outfile:" + outFile.getAbsolutePath());
                if (outFile.exists()) {
                    outFile.delete();
                }
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outFile, false));
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();

                byte[] buffer = new byte[4096];
                int len;
                long size = 0;
                long time = System.currentTimeMillis();
                while ((len = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                    size += len;
                    if (System.currentTimeMillis() - time > 1000) {
                        time = System.currentTimeMillis();
                        System.out.println("download size:" + formatSize(size));
                    }
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();

                return new MonkeyCommandReturn(true, outFile.getAbsolutePath());
            } catch (Exception e) {
                return new MonkeyCommandReturn(false, e.getMessage() + "\n" + Log.getStackTraceString(e));
            }
        }

        String formatSize(double size) {
            String[] companys = "B KB MB GB TB".split(" ");
            double cur = size;
            int index = 0;
            while (cur > 1024) {
                index++;
                cur /= 1024;
            }
            return ((long) (cur * 100)) / 100f + companys[index];
        }
    }

    /**
     * Command to wake the device up
     */
    private static class WakeCommand implements MonkeyCommand {
        // wake
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            if (!wake()) {
                return ERROR;
            }
            return OK;
        }
    }

    /**
     * Command to "tap" at a location (Sends a down and up touch event).
     */
    private static class TapCommand implements MonkeyCommand {
        // tap x y
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            if (command.size() == 3) {
                int x = 0;
                int y = 0;
                try {
                    x = Integer.parseInt(command.get(1));
                    y = Integer.parseInt(command.get(2));
                } catch (NumberFormatException e) {
                    // Ok, it wasn't a number
                    Log.e(TAG, "Got something that wasn't a number", e);
                    return EARG;
                }
                long downTime = SystemClock.uptimeMillis();
                queue.enqueueEvent(
                        new MonkeyTouchEvent(MotionEvent.ACTION_DOWN).addPointer(0, x, y).setDownTime(downTime));
                queue.enqueueEvent(new MonkeyThrottleEvent(100));
                queue.enqueueEvent(
                        new MonkeyTouchEvent(MotionEvent.ACTION_UP).addPointer(0, x, y).setDownTime(downTime));
                return OK;
            }
            return EARG;
        }
    }

    /**
     * Command to "press" a buttons (Sends an up and down key event.)
     */
    private static class PressCommand implements MonkeyCommand {
        // press keycode
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            if (command.size() == 2) {

                String keyString = command.get(1);
                int metaState = 0;
                if (keyString.length() > 3 && keyString.contains("+")) {
                    String[] arr = keyString.split("\\+");
                    for (String item : arr) {
                        if ("CTRL".equals(item.toUpperCase())) {
                            metaState |= KeyEvent.META_CTRL_ON;
                        } else if ("SHIFT".equals(item.toUpperCase())) {
                            metaState |= KeyEvent.META_SHIFT_ON;
                        } else if ("ALT".equals(item.toUpperCase())) {
                            metaState |= KeyEvent.META_ALT_ON;
                        } else if ("META".equals(item.toUpperCase())) {
                            metaState |= KeyEvent.META_META_ON;
                        } else {
                            keyString = item;
                        }
                    }
                }
                int keyCode = getKeyCode(keyString);

                if (keyCode < 0) {
                    // Ok, you gave us something bad.
                    Log.e(TAG, "Can't find keyname: " + command.get(1));
                    return EARG;
                }

                queue.enqueueEvent(new MonkeyKeyEvent(-1, -1, KeyEvent.ACTION_DOWN, keyCode, 0, metaState,
                        KeyCharacterMap.VIRTUAL_KEYBOARD, 0));
                queue.enqueueEvent(new MonkeyKeyEvent(-1, -1, KeyEvent.ACTION_UP, keyCode, 0, metaState,
                        KeyCharacterMap.VIRTUAL_KEYBOARD, 0));
                return OK;

            }
            return EARG;
        }
    }

    /**
     * Force the device to wake up.
     *
     * @return true if woken up OK.
     */
    private static final boolean wake() {
        IPowerManager pm = IPowerManager.Stub.asInterface(ServiceManager.getService(Context.POWER_SERVICE));
        try {
            pm.wakeUp(SystemClock.uptimeMillis(), PowerManager.WAKE_REASON_UNKNOWN, "Monkey", null);
        } catch (Throwable e) {
            try {
                pm.wakeUp(SystemClock.uptimeMillis(), "Monkey", null);
            } catch (Throwable e1) {
                try {
                    pm.wakeUp(SystemClock.uptimeMillis());
                } catch (Throwable e2) {
                    Log.e(TAG, "Got remote exception", e2);
                    e2.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    // QUIT command
    private static final String QUIT = "quit";

    public static interface CommandQueue {
        /**
         * Enqueue an event to be returned later. This allows a command to return
         * multiple events. Commands using the command queue still have to return a
         * valid event from their translateCommand method. The returned command will be
         * executed before anything put into the queue.
         *
         * @param e the event to be enqueued.
         */
        public void enqueueEvent(MonkeyEvent e);
    };

    // Queue of Events to be processed. This allows commands to push
    // multiple events into the queue to be processed.
    private static class CommandQueueImpl implements CommandQueue {
        private final Queue<MonkeyEvent> queuedEvents = new LinkedList<MonkeyEvent>();

        public void enqueueEvent(MonkeyEvent e) {
            queuedEvents.offer(e);
        }

        /**
         * Get the next queued event to excecute.
         *
         * @return the next event, or null if there aren't any more.
         */
        public MonkeyEvent getNextQueuedEvent() {
            return queuedEvents.poll();
        }
    };

    private final CommandQueueImpl commandQueue = new CommandQueueImpl();

    final IOWrapper ioWrapper;
    private static IActivityManager mAm;

    MonkeySource(IActivityManager mAm, IOWrapper ioWrapper) {
        MonkeySource.mAm = mAm;
        this.ioWrapper = ioWrapper;

        // Wake the device up in preparation for doing some commands.
        wake();

    }

    /**
     * Helper function for commandLineSplit that replaces quoted charaters with
     * their real values.
     *
     * @param input the string to do replacement on.
     * @return the results with the characters replaced.
     */
    private static String replaceQuotedChars(String input) {
        return input.replace("\\\"", "\"");
    }

    /**
     * This function splits the given line into String parts. It obey's quoted
     * strings and returns them as a single part.
     *
     * "This is a test" -> returns only one element This is a test -> returns four
     * elements
     *
     * @param line the line to parse
     * @return the List of elements
     */
    private static List<String> commandLineSplit(String line) {
        ArrayList<String> result = new ArrayList<String>();
        StringTokenizer tok = new StringTokenizer(line);

        boolean insideQuote = false;
        StringBuffer quotedWord = new StringBuffer();
        while (tok.hasMoreTokens()) {
            String cur = tok.nextToken();
            if (!insideQuote && cur.startsWith("\"")) {
                // begin quote
                quotedWord.append(replaceQuotedChars(cur));
                insideQuote = true;
            } else if (insideQuote) {
                // end quote
                if (cur.endsWith("\"")) {
                    insideQuote = false;
                    quotedWord.append(" ").append(replaceQuotedChars(cur));
                    String word = quotedWord.toString();

                    // trim off the quotes
                    result.add(word.substring(1, word.length() - 1));
                } else {
                    quotedWord.append(" ").append(replaceQuotedChars(cur));
                }
            } else {
                result.add(replaceQuotedChars(cur));
            }
        }
        return result;
    }

    /**
     * Translate the given command line into a MonkeyEvent.
     *
     * @param commandLine the full command line given.
     * @param command
     */
    private void translateCommand(Command command) {
        String commandLine = command.data;
        Log.d(TAG, "translateCommand: " + commandLine);
        List<String> parts = commandLineSplit(commandLine);
        if (parts.size() > 0) {
            MonkeyCommand monkeyCommand = COMMAND_MAP.get(parts.get(0));
            if (monkeyCommand != null) {
                MonkeyCommandReturn ret = monkeyCommand.translateCommand(parts, commandQueue);
                handleReturn(command, ret);
            } else {
                handleReturn(command, null);
            }
        }
    }

    private void handleReturn(Command command, MonkeyCommandReturn ret) {
        if (ret == null) {
            ioWrapper.sendResult(command, false, "not exists command!");
            return;
        }
        if (ret.wasSuccessful()) {
            if (ret.hasMessage()) {
                ioWrapper.sendResult(command, true, ret.getMessage());
            } else {
                ioWrapper.sendResult(command, true, null);
            }
        } else {
            if (ret.hasMessage()) {
                ioWrapper.sendResult(command, false, ret.getMessage());
            } else {
                ioWrapper.sendResult(command, false, null);
            }
        }
    }

    public MonkeyEvent getNextEvent() {
        // Now, get the next command. This call may block, but that's OK
        try {
            while (true) {
                // Check to see if we have any events queued up. If
                // we do, use those until we have no more. Then get
                // more input from the user.
                MonkeyEvent queuedEvent = commandQueue.getNextQueuedEvent();
                if (queuedEvent != null) {
                    // dispatch the event
                    return queuedEvent;
                }

                Command command = ioWrapper.readCommand();

                if (command == null) {
                    continue;
                }

                // Do quit checking here
                if (QUIT.equals(command.data)) {
                    // then we're done
                    Log.d(TAG, "Quit requested");
                    // let the host know the command ran OK
                    handleReturn(command, OK);
                    return null;
                }

                // Translate the command line. This will handle returning error/ok to the user
                long time = System.currentTimeMillis();
                translateCommand(command);
                Log.i(TAG,
                        "translateCommand used time : "
                                + String.format(Locale.getDefault(), "% 6d", System.currentTimeMillis() - time)
                                + " , command : " + command.data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Exception: ", e);
            return null;
        }
    }

    public void setVerbose(int verbose) {
        // We're not particualy verbose
    }

    @Override
    public boolean validate() {
        return true;
    }
}
