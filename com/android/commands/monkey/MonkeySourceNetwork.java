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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

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
public class MonkeySourceNetwork implements MonkeyEventSource {
    private static final String TAG = "MonkeyStub";
    /* The version of the monkey shell protocol */
    public static final int MONKEY_SHELL_VERSION = 1;
    private static DeferredReturn deferredReturn;

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
                int keyCode = getKeyCode(command.get(2));
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
                queue.enqueueEvent(new MonkeyKeyEvent(action, keyCode));
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
                    return -1;
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
                clipboard.setPrimaryClip(clip, "com.android.shell");

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
                System.out.println("command size : " + command.size());
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
                        System.out.println("player onCompletion!");
                    }
                });
                player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        System.err.println("MediaPlayer ERROR : what:" + what + " extra:" + extra + "");
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
                int keyCode = getKeyCode(command.get(1));
                if (keyCode < 0) {
                    // Ok, you gave us something bad.
                    Log.e(TAG, "Can't find keyname: " + command.get(1));
                    return EARG;
                }

                queue.enqueueEvent(new MonkeyKeyEvent(KeyEvent.ACTION_DOWN, keyCode));
                queue.enqueueEvent(new MonkeyKeyEvent(KeyEvent.ACTION_UP, keyCode));
                return OK;

            }
            return EARG;
        }
    }

    /**
     * Command to defer the return of another command until the given event occurs.
     * deferreturn takes three arguments. It takes an event to wait for (e.g.
     * waiting for the device to display a different activity would the
     * "screenchange" event), a timeout, which is the number of microseconds to wait
     * for the event to occur, and it takes a command. The command can be any other
     * Monkey command that can be issued over the shell (e.g. press KEYCODE_HOME).
     * deferreturn will then run this command, return an OK, wait for the event to
     * occur and return the deferred return value when either the event occurs or
     * when the timeout is reached (whichever occurs first). Note that there is no
     * difference between an event occurring and the timeout being reached; the
     * client will have to verify that the change actually occured.
     *
     * Example: deferreturn screenchange 1000 press KEYCODE_HOME This command will
     * press the home key on the device and then wait for the screen to change for
     * up to one second. Either the screen will change, and the results fo the key
     * press will be returned to the client, or the timeout will be reached, and the
     * results for the key press will be returned to the client.
     */
    private static class DeferReturnCommand implements MonkeyCommand {
        // deferreturn [event] [timeout (ms)] [command]
        // deferreturn screenchange 100 tap 10 10
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            if (command.size() > 3) {
                String event = command.get(1);
                int eventId;
                if (event.equals("screenchange")) {
                    eventId = DeferredReturn.ON_WINDOW_STATE_CHANGE;
                } else {
                    return EARG;
                }
                long timeout = Long.parseLong(command.get(2));
                MonkeyCommand deferredCommand = COMMAND_MAP.get(command.get(3));
                if (deferredCommand != null) {
                    List<String> parts = command.subList(3, command.size());
                    MonkeyCommandReturn ret = deferredCommand.translateCommand(parts, queue);
                    deferredReturn = new DeferredReturn(eventId, ret, timeout);
                    return OK;
                }
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
        COMMAND_MAP.put("listvar", new MonkeySourceNetworkVars.ListVarCommand());
        COMMAND_MAP.put("getvar", new MonkeySourceNetworkVars.GetVarCommand());
        COMMAND_MAP.put("queryview", new MonkeySourceNetworkViews.QueryViewCommand());
        COMMAND_MAP.put("getrootview", new MonkeySourceNetworkViews.GetRootViewCommand());
        COMMAND_MAP.put("getisviewchange", new MonkeySourceNetworkViews.GetIsChangeCommand());
        COMMAND_MAP.put("getviewswithtext", new MonkeySourceNetworkViews.GetViewsWithTextCommand());
        COMMAND_MAP.put("deferreturn", new DeferReturnCommand());
        COMMAND_MAP.put("takescreenshot", new MonkeySourceNetworkViews.TakeScreenshot());
        COMMAND_MAP.put("echo", new EchoCommand());
        COMMAND_MAP.put("gettopactivity", new GetTopActivityCommand());
        COMMAND_MAP.put("play", new PlayAudioCommand());
    }

    // QUIT command
    private static final String QUIT = "quit";

    // command response strings
    private static final String OK_STR = "OK";
    private static final String ERROR_STR = "ERROR";

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

    // A holder class for a deferred return value. This allows us to defer returning
    // the success of
    // a call until a given event has occurred.
    private static class DeferredReturn {
        public static final int ON_WINDOW_STATE_CHANGE = 1;

        private int event;
        private MonkeyCommandReturn deferredReturn;
        private long timeout;

        public DeferredReturn(int event, MonkeyCommandReturn deferredReturn, long timeout) {
            this.event = event;
            this.deferredReturn = deferredReturn;
            this.timeout = timeout;
        }

        /**
         * Wait until the given event has occurred before returning the value.
         *
         * @return The MonkeyCommandReturn from the command that was deferred.
         */
        public MonkeyCommandReturn waitForEvent() {
            switch (event) {
                case ON_WINDOW_STATE_CHANGE:
                    try {
                        synchronized (MonkeySourceNetworkViews.class) {
                            MonkeySourceNetworkViews.class.wait(timeout);
                        }
                    } catch (InterruptedException e) {
                        Log.d(TAG, "Deferral interrupted: " + e.getMessage());
                    }
            }
            return deferredReturn;
        }
    };

    private final CommandQueueImpl commandQueue = new CommandQueueImpl();

    private BufferedReader input;
    private PrintWriter output;
    private static IActivityManager mAm;

    MonkeySourceNetwork(IActivityManager mAm, Socket socket) throws IOException {
        MonkeySourceNetwork.mAm = mAm;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // auto-flush
        output = new PrintWriter(socket.getOutputStream(), true);

        String hello = input.readLine();
        if (!"!@#$%^&*()".equals(hello.trim())) {
            throw new IllegalStateException("wrong hello msg!");
        }

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
     */
    private void translateCommand(String commandLine) {
        Log.d(TAG, "translateCommand: " + commandLine);
        List<String> parts = commandLineSplit(commandLine);
        if (parts.size() > 0) {
            MonkeyCommand command = COMMAND_MAP.get(parts.get(0));
            if (command != null) {
                MonkeyCommandReturn ret = command.translateCommand(parts, commandQueue);
                handleReturn(ret);
            }
        }
    }

    private void handleReturn(MonkeyCommandReturn ret) {
        if (ret.wasSuccessful()) {
            if (ret.hasMessage()) {
                returnOk(ret.getMessage());
            } else {
                returnOk();
            }
        } else {
            if (ret.hasMessage()) {
                returnError(ret.getMessage());
            } else {
                returnError();
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

                // Check to see if we have any returns that have been deferred. If so, now that
                // we've run the queued commands, wait for the given event to happen (or the
                // timeout
                // to be reached), and handle the deferred MonkeyCommandReturn.
                if (deferredReturn != null) {
                    Log.d(TAG, "Waiting for event");
                    MonkeyCommandReturn ret = deferredReturn.waitForEvent();
                    deferredReturn = null;
                    handleReturn(ret);
                }

                String command = input.readLine();

                if (command == null) {
                    continue;
                }

                // Do quit checking here
                if (QUIT.equals(command)) {
                    // then we're done
                    Log.d(TAG, "Quit requested");
                    // let the host know the command ran OK
                    returnOk();
                    return null;
                }

                // Do comment checking here. Comments aren't a
                // command, so we don't echo anything back to the
                // user.
                if (command.startsWith("#")) {
                    // keep going
                    continue;
                }

                // Translate the command line. This will handle returning error/ok to the user
                long time = System.currentTimeMillis();
                translateCommand(command);
                Log.i(TAG,
                        "translateCommand used time : "
                                + String.format(Locale.getDefault(), "% 6d", System.currentTimeMillis() - time)
                                + " , command : " + command);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Exception: ", e);
            return null;
        }
    }

    /**
     * Returns ERROR to the user.
     */
    private void returnError() {
        output.println(ERROR_STR);
    }

    /**
     * Returns ERROR to the user.
     *
     * @param msg the error message to include
     */
    private void returnError(String msg) {
        output.print(ERROR_STR);
        output.print(":");
        output.println(msg);
    }

    /**
     * Returns OK to the user.
     */
    private void returnOk() {
        output.println(OK_STR);
    }

    /**
     * Returns OK to the user.
     *
     * @param returnValue the value to return from this command.
     */
    private void returnOk(String returnValue) {
        output.print(OK_STR);
        output.print(":");
        output.println(returnValue);
    }

    public void setVerbose(int verbose) {
        // We're not particualy verbose
    }

    @Override
    public boolean validate() {
        return true;
    }
}
