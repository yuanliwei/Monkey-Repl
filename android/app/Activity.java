
package android.app;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.Window.WindowControllerCallback;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;

public class Activity extends ContextThemeWrapper implements LayoutInflater.Factory2, Window.Callback,
        KeyEvent.Callback, ComponentCallbacks2, Window.OnWindowDismissedCallback, WindowControllerCallback {

    public static final int RESULT_CANCELED = 0;
    public static final int RESULT_OK = -1;
    public static final int RESULT_FIRST_USER = 1;

    public static final int DONT_FINISH_TASK_WITH_ACTIVITY = 0;
    public static final int FINISH_TASK_WITH_ROOT_ACTIVITY = 1;
    public static final int FINISH_TASK_WITH_ACTIVITY = 2;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void onLowMemory() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void exitFreeformMode() throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void enterPictureInPictureModeIfPossible() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean isTaskRoot() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void onWindowDismissed(boolean finishTask, boolean suppressWindowTransition) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void onTrimMemory(int level) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public View onCreatePanelView(int featureId) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void onWindowAttributesChanged(LayoutParams attrs) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void onContentChanged() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void onAttachedToWindow() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void onDetachedFromWindow() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onSearchRequested() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean onSearchRequested(SearchEvent searchEvent) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public ActionMode onWindowStartingActionMode(Callback callback) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public ActionMode onWindowStartingActionMode(Callback callback, int type) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Context createConfigurationContext(javax.security.auth.login.Configuration overrideConfiguration) {
        throw new RuntimeException("Stub!");
    }

}
