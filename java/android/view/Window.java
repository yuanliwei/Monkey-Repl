
package android.view;

import java.util.List;

import android.annotation.ColorInt;
import android.annotation.DrawableRes;
import android.annotation.IdRes;
import android.annotation.LayoutRes;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.StyleRes;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.accessibility.AccessibilityEvent;

public abstract class Window {
    public static final int FEATURE_OPTIONS_PANEL = 0;
    public static final int FEATURE_NO_TITLE = 1;

    @Deprecated
    public static final int FEATURE_PROGRESS = 2;

    public static final int FEATURE_LEFT_ICON = 3;
    public static final int FEATURE_RIGHT_ICON = 4;

    @Deprecated
    public static final int FEATURE_INDETERMINATE_PROGRESS = 5;

    public static final int FEATURE_CONTEXT_MENU = 6;
    public static final int FEATURE_CUSTOM_TITLE = 7;
    public static final int FEATURE_ACTION_BAR = 8;
    public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;
    public static final int FEATURE_SWIPE_TO_DISMISS = 11;
    public static final int FEATURE_CONTENT_TRANSITIONS = 12;

    public static final int FEATURE_ACTIVITY_TRANSITIONS = 13;

    public static final int FEATURE_MAX = FEATURE_ACTIVITY_TRANSITIONS;

    @Deprecated
    public static final int PROGRESS_VISIBILITY_ON = -1;

    @Deprecated
    public static final int PROGRESS_VISIBILITY_OFF = -2;

    @Deprecated
    public static final int PROGRESS_INDETERMINATE_ON = -3;

    @Deprecated
    public static final int PROGRESS_INDETERMINATE_OFF = -4;

    @Deprecated
    public static final int PROGRESS_START = 0;

    @Deprecated
    public static final int PROGRESS_END = 10000;

    @Deprecated
    public static final int PROGRESS_SECONDARY_START = 20000;

    @Deprecated
    public static final int PROGRESS_SECONDARY_END = 30000;

    public static final String STATUS_BAR_BACKGROUND_TRANSITION_NAME = "android:status:background";

    public static final String NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME = "android:navigation:background";

    @Deprecated
    protected static final int DEFAULT_FEATURES = (1 << FEATURE_OPTIONS_PANEL) | (1 << FEATURE_CONTEXT_MENU);

    public static final int ID_ANDROID_CONTENT = 0;

    public static final int DECOR_CAPTION_SHADE_AUTO = 0;
    public static final int DECOR_CAPTION_SHADE_LIGHT = 1;
    public static final int DECOR_CAPTION_SHADE_DARK = 2;

    // The current window attributes.

    public interface Callback {
        public boolean dispatchKeyEvent(KeyEvent event);

        public boolean dispatchKeyShortcutEvent(KeyEvent event);

        public boolean dispatchTouchEvent(MotionEvent event);

        public boolean dispatchTrackballEvent(MotionEvent event);

        public boolean dispatchGenericMotionEvent(MotionEvent event);

        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event);

        @Nullable
        public View onCreatePanelView(int featureId);

        public boolean onCreatePanelMenu(int featureId, Menu menu);

        public boolean onPreparePanel(int featureId, View view, Menu menu);

        public boolean onMenuOpened(int featureId, Menu menu);

        public boolean onMenuItemSelected(int featureId, MenuItem item);

        public void onWindowAttributesChanged(WindowManager.LayoutParams attrs);

        public void onContentChanged();

        public void onWindowFocusChanged(boolean hasFocus);

        public void onAttachedToWindow();

        public void onDetachedFromWindow();

        public void onPanelClosed(int featureId, Menu menu);

        public boolean onSearchRequested();

        public boolean onSearchRequested(SearchEvent searchEvent);

        @Nullable
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback);

        @Nullable
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type);

        public void onActionModeStarted(ActionMode mode);

        public void onActionModeFinished(ActionMode mode);

        default public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu,
                int deviceId) {
            throw new RuntimeException("Stub!");
        };

        default public void onPointerCaptureChanged(boolean hasCapture) {
            throw new RuntimeException("Stub!");
        };
    }

    public interface OnWindowDismissedCallback {
        void onWindowDismissed(boolean finishTask, boolean suppressWindowTransition);
    }

    public interface OnWindowSwipeDismissedCallback {
        void onWindowSwipeDismissed();
    }

    public interface WindowControllerCallback {
        void exitFreeformMode() throws RemoteException;

        void enterPictureInPictureModeIfPossible();

        boolean isTaskRoot();
    }

    public interface OnRestrictedCaptionAreaChangedListener {
        void onRestrictedCaptionAreaChanged(Rect rect);
    }

    public interface OnFrameMetricsAvailableListener {
        void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int dropCountSinceLastInvocation);
    }

    public Window(Context context) {
        throw new RuntimeException("Stub!");
    }

    public final Context getContext() {
        throw new RuntimeException("Stub!");
    }

    public void setContainer(Window container) {
        throw new RuntimeException("Stub!");
    }

    public final Window getContainer() {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasChildren() {
        throw new RuntimeException("Stub!");
    }

    public final void destroy() {
        throw new RuntimeException("Stub!");
    }

    public final boolean isDestroyed() {
        throw new RuntimeException("Stub!");
    }

    public void setWindowManager(WindowManager wm, IBinder appToken, String appName) {
        throw new RuntimeException("Stub!");
    }

    public void setWindowManager(WindowManager wm, IBinder appToken, String appName, boolean hardwareAccelerated) {
        throw new RuntimeException("Stub!");
    }

    void adjustLayoutParamsForSubWindow(WindowManager.LayoutParams wp) {
        CharSequence curTitle = wp.getTitle();
        if (wp.type >= WindowManager.LayoutParams.FIRST_SUB_WINDOW
                && wp.type <= WindowManager.LayoutParams.LAST_SUB_WINDOW) {
            if (wp.token == null) {
                throw new RuntimeException("Stub!");
            }
            if (curTitle == null || curTitle.length() == 0) {
                throw new RuntimeException("Stub!");
            }
        } else if (wp.type >= WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW
                && wp.type <= WindowManager.LayoutParams.LAST_SYSTEM_WINDOW) {
            throw new RuntimeException("Stub!");
        }

    }

    public WindowManager getWindowManager() {
        throw new RuntimeException("Stub!");
    }

    public void setCallback(Callback callback) {
        throw new RuntimeException("Stub!");
    }

    public final Callback getCallback() {
        throw new RuntimeException("Stub!");
    }

    public final void addOnFrameMetricsAvailableListener(@NonNull OnFrameMetricsAvailableListener listener,
            Handler handler) {
        throw new RuntimeException("Stub!");
    }

    public final void removeOnFrameMetricsAvailableListener(OnFrameMetricsAvailableListener listener) {
        throw new RuntimeException("Stub!");
    }

    public final void setOnWindowDismissedCallback(OnWindowDismissedCallback dcb) {
        throw new RuntimeException("Stub!");
    }

    public final void dispatchOnWindowDismissed(boolean finishTask, boolean suppressWindowTransition) {
        throw new RuntimeException("Stub!");
    }

    public final void setOnWindowSwipeDismissedCallback(OnWindowSwipeDismissedCallback sdcb) {
        throw new RuntimeException("Stub!");
    }

    public final void dispatchOnWindowSwipeDismissed() {
        throw new RuntimeException("Stub!");
    }

    public final void setWindowControllerCallback(WindowControllerCallback wccb) {
        throw new RuntimeException("Stub!");
    }

    public final WindowControllerCallback getWindowControllerCallback() {
        throw new RuntimeException("Stub!");
    }

    public final void setRestrictedCaptionAreaListener(OnRestrictedCaptionAreaChangedListener listener) {
        throw new RuntimeException("Stub!");
    }

    public abstract void takeSurface(SurfaceHolder.Callback2 callback);

    public abstract void takeInputQueue(InputQueue.Callback callback);

    public abstract boolean isFloating();

    public void setLayout(int width, int height) {
        throw new RuntimeException("Stub!");
    }

    public void setGravity(int gravity) {
        throw new RuntimeException("Stub!");
    }

    public void setType(int type) {
        throw new RuntimeException("Stub!");
    }

    public void setFormat(int format) {
        throw new RuntimeException("Stub!");
    }

    public void setWindowAnimations(@StyleRes int resId) {
        throw new RuntimeException("Stub!");
    }

    public void setSoftInputMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public void addFlags(int flags) {
        throw new RuntimeException("Stub!");
    }

    public void addPrivateFlags(int flags) {
        throw new RuntimeException("Stub!");
    }

    public void clearFlags(int flags) {
        throw new RuntimeException("Stub!");
    }

    public void setFlags(int flags, int mask) {
        throw new RuntimeException("Stub!");
    }

    final WindowManager.LayoutParams attrs = getAttributes();

    protected void setNeedsMenuKey(int value) {
        throw new RuntimeException("Stub!");
    }

    protected void dispatchWindowAttributesChanged(WindowManager.LayoutParams attrs) {
        throw new RuntimeException("Stub!");
    }

    public void setColorMode(@ActivityInfo.ColorMode int colorMode) {
        throw new RuntimeException("Stub!");
    }

    @ActivityInfo.ColorMode
    public int getColorMode() {
        throw new RuntimeException("Stub!");
    }

    public boolean isWideColorGamut() {
        throw new RuntimeException("Stub!");
    }

    public void setDimAmount(float amount) {
        throw new RuntimeException("Stub!");
    }

    public void setAttributes(WindowManager.LayoutParams a) {
        throw new RuntimeException("Stub!");
    }

    public final WindowManager.LayoutParams getAttributes() {
        throw new RuntimeException("Stub!");
    }

    protected final int getForcedWindowFlags() {
        throw new RuntimeException("Stub!");
    }

    protected final boolean hasSoftInputMode() {
        throw new RuntimeException("Stub!");
    }

    public void setCloseOnTouchOutside(boolean close) {
        throw new RuntimeException("Stub!");
    }

    public void setCloseOnTouchOutsideIfNotSet(boolean close) {
        throw new RuntimeException("Stub!");
    }

    public abstract void alwaysReadCloseOnTouchAttr();

    public boolean shouldCloseOnTouch(Context context, MotionEvent event) {
        throw new RuntimeException("Stub!");
    }

    public void setSustainedPerformanceMode(boolean enable) {
        throw new RuntimeException("Stub!");
    }

    public boolean requestFeature(int featureId) {
        throw new RuntimeException("Stub!");
    }

    protected void removeFeature(int featureId) {
        throw new RuntimeException("Stub!");
    }

    public final void makeActive() {
        throw new RuntimeException("Stub!");
    }

    public final boolean isActive() {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    public <T extends View> T findViewById(@IdRes int id) {
        throw new RuntimeException("Stub!");
    }

    @NonNull
    public final <T extends View> T requireViewById(@IdRes int id) {
        throw new RuntimeException("Stub!");
    }

    public abstract void setContentView(@LayoutRes int layoutResID);

    public abstract void setContentView(View view);

    public abstract void setContentView(View view, ViewGroup.LayoutParams params);

    public abstract void addContentView(View view, ViewGroup.LayoutParams params);

    public abstract void clearContentView();

    @Nullable
    public abstract View getCurrentFocus();

    @NonNull
    public abstract LayoutInflater getLayoutInflater();

    public abstract void setTitle(CharSequence title);

    @Deprecated
    public abstract void setTitleColor(@ColorInt int textColor);

    public abstract void openPanel(int featureId, KeyEvent event);

    public abstract void closePanel(int featureId);

    public abstract void togglePanel(int featureId, KeyEvent event);

    public abstract void invalidatePanelMenu(int featureId);

    public abstract boolean performPanelShortcut(int featureId, int keyCode, KeyEvent event, int flags);

    public abstract boolean performPanelIdentifierAction(int featureId, int id, int flags);

    public abstract void closeAllPanels();

    public abstract boolean performContextMenuIdentifierAction(int id, int flags);

    public abstract void onConfigurationChanged(Configuration newConfig);

    public void setElevation(float elevation) {
        throw new RuntimeException("Stub!");
    }

    public float getElevation() {
        throw new RuntimeException("Stub!");
    }

    public void setClipToOutline(boolean clipToOutline) {
        throw new RuntimeException("Stub!");
    }

    public void setBackgroundDrawableResource(@DrawableRes int resId) {
        throw new RuntimeException("Stub!");
    }

    public abstract void setBackgroundDrawable(Drawable drawable);

    public abstract void setFeatureDrawableResource(int featureId, @DrawableRes int resId);

    public abstract void setFeatureDrawableUri(int featureId, Uri uri);

    public abstract void setFeatureDrawable(int featureId, Drawable drawable);

    public abstract void setFeatureDrawableAlpha(int featureId, int alpha);

    public abstract void setFeatureInt(int featureId, int value);

    public abstract void takeKeyEvents(boolean get);

    public abstract boolean superDispatchKeyEvent(KeyEvent event);

    public abstract boolean superDispatchKeyShortcutEvent(KeyEvent event);

    public abstract boolean superDispatchTouchEvent(MotionEvent event);

    public abstract boolean superDispatchTrackballEvent(MotionEvent event);

    public abstract boolean superDispatchGenericMotionEvent(MotionEvent event);

    public abstract View getDecorView();

    public abstract View peekDecorView();

    public abstract Bundle saveHierarchyState();

    public abstract void restoreHierarchyState(Bundle savedInstanceState);

    protected abstract void onActive();

    protected final int getFeatures() {
        throw new RuntimeException("Stub!");
    }

    public static int getDefaultFeatures(Context context) {
        throw new RuntimeException("Stub!");
    }

    public boolean hasFeature(int feature) {
        throw new RuntimeException("Stub!");
    }

    protected final int getLocalFeatures() {
        throw new RuntimeException("Stub!");
    }

    protected void setDefaultWindowFormat(int format) {
        throw new RuntimeException("Stub!");
    }

    protected boolean haveDimAmount() {
        throw new RuntimeException("Stub!");
    }

    public abstract void setChildDrawable(int featureId, Drawable drawable);

    public abstract void setChildInt(int featureId, int value);

    public abstract boolean isShortcutKey(int keyCode, KeyEvent event);

    public abstract void setVolumeControlStream(int streamType);

    public abstract int getVolumeControlStream();

    public void setUiOptions(int uiOptions) {
        throw new RuntimeException("Stub!");
    }

    public void setUiOptions(int uiOptions, int mask) {
        throw new RuntimeException("Stub!");
    }

    public void setIcon(@DrawableRes int resId) {
        throw new RuntimeException("Stub!");
    }

    public void setDefaultIcon(@DrawableRes int resId) {
        throw new RuntimeException("Stub!");
    }

    public void setLogo(@DrawableRes int resId) {
        throw new RuntimeException("Stub!");
    }

    public void setDefaultLogo(@DrawableRes int resId) {
        throw new RuntimeException("Stub!");
    }

    public void setLocalFocus(boolean hasFocus, boolean inTouchMode) {
        throw new RuntimeException("Stub!");
    }

    public void injectInputEvent(InputEvent event) {
        throw new RuntimeException("Stub!");
    }

    public void setAllowEnterTransitionOverlap(boolean allow) {
        throw new RuntimeException("Stub!");
    }

    public boolean getAllowEnterTransitionOverlap() {
        throw new RuntimeException("Stub!");
    }

    public void setAllowReturnTransitionOverlap(boolean allow) {
        throw new RuntimeException("Stub!");
    }

    public boolean getAllowReturnTransitionOverlap() {
        throw new RuntimeException("Stub!");
    }

    public long getTransitionBackgroundFadeDuration() {
        throw new RuntimeException("Stub!");
    }

    public void setTransitionBackgroundFadeDuration(long fadeDurationMillis) {
        throw new RuntimeException("Stub!");
    }

    public boolean getSharedElementsUseOverlay() {
        throw new RuntimeException("Stub!");
    }

    public void setSharedElementsUseOverlay(boolean sharedElementsUseOverlay) {
        throw new RuntimeException("Stub!");
    }

    @ColorInt
    public abstract int getStatusBarColor();

    public abstract void setStatusBarColor(@ColorInt int color);

    @ColorInt
    public abstract int getNavigationBarColor();

    public abstract void setNavigationBarColor(@ColorInt int color);

    public void setNavigationBarDividerColor(@ColorInt int dividerColor) {
        throw new RuntimeException("Stub!");
    }

    public @ColorInt int getNavigationBarDividerColor() {
        throw new RuntimeException("Stub!");
    }

    public void setTheme(int resId) {
        throw new RuntimeException("Stub!");
    }

    public abstract void setDecorCaptionShade(int decorCaptionShade);

    public abstract void setResizingCaptionDrawable(Drawable drawable);

    public abstract void onMultiWindowModeChanged();

    public abstract void onPictureInPictureModeChanged(boolean isInPictureInPictureMode);

    public abstract void reportActivityRelaunched();

    public void setCloseOnSwipeEnabled(boolean closeOnSwipeEnabled) {
        throw new RuntimeException("Stub!");
    }

    public boolean isCloseOnSwipeEnabled() {
        throw new RuntimeException("Stub!");
    }

}
