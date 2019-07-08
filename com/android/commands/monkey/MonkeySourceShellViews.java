/*
 * Copyright 2011, The Android Open Source Project
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

import static com.android.commands.monkey.MonkeySourceShell.EARG;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.commands.monkey.MonkeySourceShell.CommandQueue;
import com.android.commands.monkey.MonkeySourceShell.MonkeyCommand;
import com.android.commands.monkey.MonkeySourceShell.MonkeyCommandReturn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.UiAutomation;
import android.app.UiAutomationConnection;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.display.DisplayManagerGlobal;
import android.os.HandlerThread;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.accessibility.AccessibilityInteractionClient;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

/**
 * Utility class that enables Monkey to perform view introspection when issued
 * Monkey Network Script commands over the network.
 */
public class MonkeySourceShellViews {

    protected static UiAutomation sUiTestAutomationBridge;

    private static UiAutomationConnection mUiAutomationConnection;

    private static final String HANDLER_THREAD_NAME = "UiAutomationHandlerThread";

    private static final String NO_ACCESSIBILITY_EVENT = "No accessibility event has occured yet";
    private static final String NO_NODE = "Node with given ID does not exist";
    private static final Map<String, ViewIntrospectionCommand> COMMAND_MAP = new HashMap<String, ViewIntrospectionCommand>();

    /* Interface for view queries */
    private static interface ViewIntrospectionCommand {
        /**
         * Get the response to the query
         * 
         * @return the response to the query
         */
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args);
    }

    static {
        COMMAND_MAP.put("getlocation", new GetLocation());
        COMMAND_MAP.put("gettext", new GetText());
        COMMAND_MAP.put("getclass", new GetClass());
        COMMAND_MAP.put("getchecked", new GetChecked());
        COMMAND_MAP.put("getenabled", new GetEnabled());
        COMMAND_MAP.put("getselected", new GetSelected());
        COMMAND_MAP.put("setselected", new SetSelected());
        COMMAND_MAP.put("getfocused", new GetFocused());
        COMMAND_MAP.put("setfocused", new SetFocused());
        COMMAND_MAP.put("getparent", new GetParent());
        COMMAND_MAP.put("getchildren", new GetChildren());
        COMMAND_MAP.put("getaccessibilityids", new GetAccessibilityIds());
        COMMAND_MAP.put("gettree", new GetTree());
    }

    private static final HandlerThread sHandlerThread = new HandlerThread(HANDLER_THREAD_NAME);

    /**
     * Registers the event listener for AccessibilityEvents. Also sets up a
     * communication connection so we can query the accessibility service.
     */
    public static void setup() {
        sHandlerThread.setDaemon(true);
        sHandlerThread.start();
        mUiAutomationConnection = new UiAutomationConnection();
        sUiTestAutomationBridge = new UiAutomation(sHandlerThread.getLooper(), mUiAutomationConnection);
        sUiTestAutomationBridge.connect();
    }

    public static void reconnect() {
        sUiTestAutomationBridge.disconnect();
        sUiTestAutomationBridge.connect();
    }

    private static AccessibilityNodeInfo getNodeByAccessibilityIds(String windowString, String viewString) {
        int windowId = Integer.parseInt(windowString);
        int viewId = Integer.parseInt(viewString);
        int connectionId = sUiTestAutomationBridge.getConnectionId();
        AccessibilityInteractionClient client = AccessibilityInteractionClient.getInstance();
        return client.findAccessibilityNodeInfoByAccessibilityId(connectionId, windowId, viewId, false, 0, null);
    }

    private static AccessibilityNodeInfo getNodeByViewId(String viewId) throws MonkeyViewException {
        int connectionId = sUiTestAutomationBridge.getConnectionId();
        AccessibilityInteractionClient client = AccessibilityInteractionClient.getInstance();
        List<AccessibilityNodeInfo> infos = client.findAccessibilityNodeInfosByViewId(connectionId,
                AccessibilityWindowInfo.ACTIVE_WINDOW_ID, AccessibilityNodeInfo.ROOT_NODE_ID, viewId);
        return (!infos.isEmpty()) ? infos.get(0) : null;
    }

    /**
     * Command to list view as tree under given node or root node
     */
    public static class GetTree implements ViewIntrospectionCommand {
        // queryview gettree [text|json]
        // queryview gettree text
        // queryview gettree json
        // queryview [id type] [id] gettree json
        // queryview accessibilityids 1381 890 gettree text
        // queryview viewid com.xxx.xxxx:id/xxxxx gettree json
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            String type = "text";
            for (int i = 0; i < args.size(); i++) {
                String arg = args.get(i);
                if ("text".equals(arg)) {
                    type = "text";
                } else if ("json".equals(arg)) {
                    type = "json";
                }
            }
            /*
             * Occasionally the API will generate an event with no source, which is
             * essentially the same as it generating no event at all
             */
            if (node == null) {
                return new MonkeyCommandReturn(false, NO_ACCESSIBILITY_EVENT);
            }

            try {
                if ("text".equals(type)) {
                    StringBuilder fieldBuilder = new StringBuilder();
                    loopViews(node, 0, 0, fieldBuilder);
                    return new MonkeyCommandReturn(true, fieldBuilder.toString());
                } else if ("json".equals(type)) {
                    JSONObject jsObj = new JSONObject();
                    loopViews(node, 0, 0, jsObj);
                    return new MonkeyCommandReturn(true, jsObj.toString());
                } else {
                    return EARG;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return new MonkeyCommandReturn(false, e.getMessage());
            }
        }

        // text
        void loopViews(AccessibilityNodeInfo node, int deep, int index, StringBuilder sb) {
            if (node == null) {
                return;
            }
            Rect bounds = new Rect();
            node.getBoundsInScreen(bounds);

            for (int i = 0; i < deep; i++) {
                sb.append("  ");
            }

            sb.append("index=").append(index).append(" text=").append(node.getText()).append(" resource-id:")
                    .append(node.getViewIdResourceName());
            sb.append(" bounds=").append(bounds.toShortString());
            sb.append(" class=").append(node.getClassName()).append(" type=").append(node.getInputType());
            int viewId = AccessibilityNodeInfo.getAccessibilityViewId(node.getSourceNodeId());
            String ids = node.getWindowId() + " " + viewId;
            sb.append(" accessibilityids=[").append(ids).append("]");
            sb.append("\n");
            int childCount = node.getChildCount();
            for (int i = 0; i < childCount; i++) {
                AccessibilityNodeInfo child = node.getChild(i);
                loopViews(child, deep + 1, i, sb);
            }
        }

        // json
        void loopViews(AccessibilityNodeInfo node, int deep, int index, JSONObject jsObj) throws JSONException {
            if (node == null) {
                return;
            }
            Rect bounds = new Rect();
            node.getBoundsInScreen(bounds);
            jsObj.put("deep", deep);
            jsObj.put("index", index);
            jsObj.put("text", node.getText());
            jsObj.put("resource-id", node.getSourceNodeId());
            jsObj.put("resource-id-name", node.getViewIdResourceName());
            jsObj.put("bounds", bounds.toShortString());
            jsObj.put("class", node.getClassName());
            jsObj.put("inputType", node.getInputType());
            jsObj.put("inputType", node.getMaxTextLength());
            jsObj.put("isEditable", node.isEditable());
            jsObj.put("isClickable", node.isClickable());
            jsObj.put("isCheckable", node.isCheckable());
            jsObj.put("isChecked", node.isChecked());
            int viewId = AccessibilityNodeInfo.getAccessibilityViewId(node.getSourceNodeId());
            jsObj.put("windowId", node.getWindowId());
            jsObj.put("viewId", viewId);
            JSONArray jsArr = new JSONArray();
            jsObj.put("childrens", jsArr);

            int childCount = node.getChildCount();
            for (int i = 0; i < childCount; i++) {
                AccessibilityNodeInfo child = node.getChild(i);
                JSONObject jsObjChild = new JSONObject();
                loopViews(child, deep + 1, i, jsObjChild);
                jsArr.put(jsObjChild);
            }
        }
    }

    /**
     * A command that allows for querying of views. It takes an id type, the
     * requisite ids, and the command for querying the view.
     */
    public static class QueryViewCommand implements MonkeyCommand {

        private static final String TAG = "MonkeyStub";

        // queryview [id type] [id(s)] [command]
        // queryview viewid button1 gettext
        // queryview accessibilityids 12 5 getparent
        // queryview accessibilityids [windowId] [viewId] getchildren
        // queryview accessibilityids 1381 890 gettree text
        // queryview viewid com.xxx.xxxx:id/xxxxx gettree json
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            if (command.size() >= 2) {
                String idType = command.get(1);
                AccessibilityNodeInfo node = null;
                String viewQuery;
                List<String> args;
                int tryCount = 0;
                do {
                    if ("viewid".equals(idType)) {
                        try {
                            node = getNodeByViewId(command.get(2));
                            viewQuery = command.get(3);
                            args = command.subList(4, command.size());
                        } catch (MonkeyViewException e) {
                            return new MonkeyCommandReturn(false, e.getMessage());
                        }
                    } else if ("accessibilityids".equals(idType)) {
                        try {
                            node = getNodeByAccessibilityIds(command.get(2), command.get(3));
                            viewQuery = command.get(4);
                            args = command.subList(5, command.size());
                        } catch (NumberFormatException e) {
                            return EARG;
                        }
                    } else {
                        node = sUiTestAutomationBridge.getRootInActiveWindow();
                        viewQuery = command.get(1);
                        args = command.subList(2, command.size());
                    }
                    if (node != null) {
                        break;
                    }
                    Log.i(TAG, "reconnect : ......... " + tryCount++);
                    reconnect();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (tryCount < 10);
                if (node == null) {
                    return new MonkeyCommandReturn(false, NO_NODE);
                }
                ViewIntrospectionCommand getter = COMMAND_MAP.get(viewQuery);
                if (getter != null) {
                    return getter.query(node, args);
                } else {
                    return EARG;
                }
            }
            return EARG;
        }
    }

    /**
     * A command that returns the accessibility ids of the root view.
     */
    public static class GetRootViewCommand implements MonkeyCommand {
        // getrootview
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            AccessibilityNodeInfo node = sUiTestAutomationBridge.getRootInActiveWindow();
            return (new GetAccessibilityIds()).query(node, new ArrayList<String>());
        }
    }

    /**
     * A command that returns the accessibility ids of the views that contain the
     * given text. It takes a string of text and returns the accessibility ids of
     * the nodes that contain the text as a list of integers separated by spaces.
     */
    public static class GetViewsWithTextCommand implements MonkeyCommand {
        // getviewswithtext [text]
        // getviewswithtext "some text here"
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            if (command.size() == 2) {
                String text = command.get(1);
                int connectionId = sUiTestAutomationBridge.getConnectionId();
                List<AccessibilityNodeInfo> nodes = AccessibilityInteractionClient.getInstance()
                        .findAccessibilityNodeInfosByText(connectionId, AccessibilityWindowInfo.ACTIVE_WINDOW_ID,
                                AccessibilityNodeInfo.ROOT_NODE_ID, text);
                ViewIntrospectionCommand idGetter = new GetAccessibilityIds();
                List<String> emptyArgs = new ArrayList<String>();
                StringBuilder ids = new StringBuilder();
                for (AccessibilityNodeInfo node : nodes) {
                    MonkeyCommandReturn result = idGetter.query(node, emptyArgs);
                    if (!result.wasSuccessful()) {
                        return result;
                    }
                    ids.append(result.getMessage()).append(" ");
                }
                return new MonkeyCommandReturn(true, ids.toString());
            }
            return EARG;
        }
    }

    public static class TakeScreenshot implements MonkeyCommand {
        // takescreenshot [scale|rect|getcolor|quality]
        // takescreenshot scale 0.3
        // takescreenshot rect 30 30 50 50
        // takescreenshot getcolor 300 330
        // takescreenshot getcolor 300 330 quality 90
        public MonkeyCommandReturn translateCommand(List<String> command, CommandQueue queue) {
            Rect rect = new Rect();
            float scale = 1;
            int quality = 80;
            int x = -1;
            int y = -1;
            try {
                for (int i = 0; i < command.size(); i++) {
                    String arg = command.get(i);
                    if ("scale".equals(arg)) {
                        scale = Float.parseFloat(command.get(++i));
                    } else if ("rect".equals(arg)) {
                        rect.set(Integer.parseInt(command.get(++i)), Integer.parseInt(command.get(++i)),
                                Integer.parseInt(command.get(++i)), Integer.parseInt(command.get(++i)));
                    } else if ("getcolor".equals(arg)) {
                        x = Integer.parseInt(command.get(++i));
                        y = Integer.parseInt(command.get(++i));
                    } else if ("quality".equals(arg)) {
                        quality = Integer.parseInt(command.get(++i));
                    }
                }

                if (x > -1 && y > -1) {
                    rect.set(x, y, x + 1, y + 1);
                }
                Bitmap bitmap = null;
                if (rect.width() > 0 && rect.height() > 0) {
                    // takescreenshot rect 30 30 50 50
                    Display display = DisplayManagerGlobal.getInstance().getRealDisplay(Display.DEFAULT_DISPLAY);
                    int rotation = display.getRotation();
                    try {
                        bitmap = mUiAutomationConnection.takeScreenshot(rect, rotation);
                    } catch (Throwable e) {
                        // bitmap = mUiAutomationConnection.takeScreenshot(rect.width(), rect.height());
                        bitmap = sUiTestAutomationBridge.takeScreenshot();
                        bitmap = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height(), null, false);
                    }
                } else {
                    // takescreenshot
                    bitmap = sUiTestAutomationBridge.takeScreenshot();
                }
                // takescreenshot getcolor 300 330
                if (x > -1 && y > -1) {
                    bitmap = bitmap.copy(Config.ARGB_8888, true);
                    int pixel = bitmap.getPixel(0, 0);
                    Color color = Color.valueOf(pixel);
                    return new MonkeyCommandReturn(true, Integer.toHexString(color.toArgb()));
                }

                // takescreenshot scale 0.3
                if (scale != 1) {
                    bitmap = scaleBitmap(bitmap, scale);
                }
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
                String base64 = Base64.encodeToString(out.toByteArray(), Base64.DEFAULT);

                return new MonkeyCommandReturn(true, base64);
            } catch (Exception e) {
                e.printStackTrace();
                return new MonkeyCommandReturn(false, e.getMessage());
            }
        }

        /**
         * 按比例缩放图片
         *
         * @param origin 原图
         * @param ratio  比例
         * @return 新的bitmap
         */
        private Bitmap scaleBitmap(Bitmap origin, float ratio) {
            if (origin == null) {
                return null;
            }
            int width = origin.getWidth();
            int height = origin.getHeight();
            Matrix matrix = new Matrix();
            matrix.preScale(ratio, ratio);
            Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
            if (newBM.equals(origin)) {
                return newBM;
            }
            origin.recycle();
            return newBM;
        }
    }

    /**
     * Command to retrieve the location of the given node. Returns the x, y, width
     * and height of the view, separated by spaces.
     */
    public static class GetLocation implements ViewIntrospectionCommand {
        // queryview [id type] [id] getlocation
        // queryview viewid button1 getlocation
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            if (args.size() == 0) {
                Rect nodePosition = new Rect();
                node.getBoundsInScreen(nodePosition);
                StringBuilder positions = new StringBuilder();
                positions.append(nodePosition.left).append(" ").append(nodePosition.top);
                positions.append(" ").append(nodePosition.right - nodePosition.left).append(" ");
                positions.append(nodePosition.bottom - nodePosition.top);
                return new MonkeyCommandReturn(true, positions.toString());
            }
            return EARG;
        }
    }

    /**
     * Command to retrieve the text of the given node
     */
    public static class GetText implements ViewIntrospectionCommand {
        // queryview [id type] [id] gettext
        // queryview viewid button1 gettext
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            if (args.size() == 0) {
                if (node.isPassword()) {
                    return new MonkeyCommandReturn(false, "Node contains a password");
                }
                /*
                 * Occasionally we get a null from the accessibility API, rather than an empty
                 * string
                 */
                if (node.getText() == null) {
                    return new MonkeyCommandReturn(true, "");
                }
                return new MonkeyCommandReturn(true, node.getText().toString());
            }
            return EARG;
        }
    }

    /**
     * Command to retrieve the class name of the given node
     */
    public static class GetClass implements ViewIntrospectionCommand {
        // queryview [id type] [id] getclass
        // queryview viewid button1 getclass
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            if (args.size() == 0) {
                return new MonkeyCommandReturn(true, node.getClassName().toString());
            }
            return EARG;
        }
    }

    /**
     * Command to retrieve the checked status of the given node
     */
    public static class GetChecked implements ViewIntrospectionCommand {
        // queryview [id type] [id] getchecked
        // queryview viewid button1 getchecked
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            if (args.size() == 0) {
                return new MonkeyCommandReturn(true, Boolean.toString(node.isChecked()));
            }
            return EARG;
        }
    }

    /**
     * Command to retrieve whether the given node is enabled
     */
    public static class GetEnabled implements ViewIntrospectionCommand {
        // queryview [id type] [id] getenabled
        // queryview viewid button1 getenabled
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            if (args.size() == 0) {
                return new MonkeyCommandReturn(true, Boolean.toString(node.isEnabled()));
            }
            return EARG;
        }
    }

    /**
     * Command to retrieve whether the given node is selected
     */
    public static class GetSelected implements ViewIntrospectionCommand {
        // queryview [id type] [id] getselected
        // queryview viewid button1 getselected
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            if (args.size() == 0) {
                return new MonkeyCommandReturn(true, Boolean.toString(node.isSelected()));
            }
            return EARG;
        }
    }

    /**
     * Command to set the selected status of the given node. Takes a boolean value
     * as its only argument.
     */
    public static class SetSelected implements ViewIntrospectionCommand {
        // queryview [id type] [id] setselected [boolean]
        // queryview viewid button1 setselected true
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            if (args.size() == 1) {
                boolean actionPerformed;
                if (Boolean.valueOf(args.get(0))) {
                    actionPerformed = node.performAction(AccessibilityNodeInfo.ACTION_SELECT);
                } else if (!Boolean.valueOf(args.get(0))) {
                    actionPerformed = node.performAction(AccessibilityNodeInfo.ACTION_CLEAR_SELECTION);
                } else {
                    return EARG;
                }
                return new MonkeyCommandReturn(actionPerformed);
            }
            return EARG;
        }
    }

    /**
     * Command to get whether the given node is focused.
     */
    public static class GetFocused implements ViewIntrospectionCommand {
        // queryview [id type] [id] getfocused
        // queryview viewid button1 getfocused
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            if (args.size() == 0) {
                return new MonkeyCommandReturn(true, Boolean.toString(node.isFocused()));
            }
            return EARG;
        }
    }

    /**
     * Command to set the focus status of the given node. Takes a boolean value as
     * its only argument.
     */
    public static class SetFocused implements ViewIntrospectionCommand {
        // queryview [id type] [id] setfocused [boolean]
        // queryview viewid button1 setfocused false
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            if (args.size() == 1) {
                boolean actionPerformed;
                if (Boolean.valueOf(args.get(0))) {
                    actionPerformed = node.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
                } else if (!Boolean.valueOf(args.get(0))) {
                    actionPerformed = node.performAction(AccessibilityNodeInfo.ACTION_CLEAR_FOCUS);
                } else {
                    return EARG;
                }
                return new MonkeyCommandReturn(actionPerformed);
            }
            return EARG;
        }
    }

    /**
     * Command to get the accessibility ids of the given node. Returns the
     * accessibility ids as a space separated pair of integers with window id coming
     * first, followed by the accessibility view id.
     */
    public static class GetAccessibilityIds implements ViewIntrospectionCommand {
        // queryview [id type] [id] getaccessibilityids
        // queryview viewid button1 getaccessibilityids
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            if (args.size() == 0) {
                int viewId = AccessibilityNodeInfo.getAccessibilityViewId(node.getSourceNodeId());
                String ids = node.getWindowId() + " " + viewId;
                return new MonkeyCommandReturn(true, ids);
            }
            return EARG;
        }
    }

    /**
     * Command to get the accessibility ids of the parent of the given node. Returns
     * the accessibility ids as a space separated pair of integers with window id
     * coming first followed by the accessibility view id.
     */
    public static class GetParent implements ViewIntrospectionCommand {
        // queryview [id type] [id] getparent
        // queryview viewid button1 getparent
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            if (args.size() == 0) {
                AccessibilityNodeInfo parent = node.getParent();
                if (parent == null) {
                    return new MonkeyCommandReturn(false, "Given node has no parent");
                }
                return (new GetAccessibilityIds()).query(parent, new ArrayList<String>());
            }
            return EARG;
        }
    }

    /**
     * Command to get the accessibility ids of the children of the given node.
     * Returns the children's ids as a space separated list of integer pairs. Each
     * of the pairs consists of the window id, followed by the accessibility id.
     */
    public static class GetChildren implements ViewIntrospectionCommand {
        // queryview [id type] [id] getchildren
        // queryview viewid button1 getchildren
        public MonkeyCommandReturn query(AccessibilityNodeInfo node, List<String> args) {
            if (args.size() == 0) {
                ViewIntrospectionCommand idGetter = new GetAccessibilityIds();
                List<String> emptyArgs = new ArrayList<String>();
                StringBuilder ids = new StringBuilder();
                int totalChildren = node.getChildCount();
                for (int i = 0; i < totalChildren; i++) {
                    MonkeyCommandReturn result = idGetter.query(node.getChild(i), emptyArgs);
                    if (!result.wasSuccessful()) {
                        return result;
                    } else {
                        ids.append(result.getMessage()).append(" ");
                    }
                }
                return new MonkeyCommandReturn(true, ids.toString());
            }
            return EARG;
        }
    }
}
