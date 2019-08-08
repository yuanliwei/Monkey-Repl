
package android.widget;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.annotation.IntDef;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.StringRes;
import android.annotation.UnsupportedAppUsage;
import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;

public class Toast {
    static final String TAG = "Toast";
    static final boolean localLOGV = false;

    @IntDef(prefix = { "LENGTH_" }, value = { LENGTH_SHORT, LENGTH_LONG })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public static final int LENGTH_SHORT = 0;

    public static final int LENGTH_LONG = 1;

    final Context mContext;

    View mNextView;

    public Toast(Context context) {
        throw new RuntimeException("Stub!");
    }

    public Toast(@NonNull Context context, @Nullable Looper looper) {
        throw new RuntimeException("Stub!");
    }

    public void show() {
        throw new RuntimeException("Stub!");
    }

    public void cancel() {
        throw new RuntimeException("Stub!");
    }

    public void setView(View view) {
        throw new RuntimeException("Stub!");
    }

    public View getView() {
        throw new RuntimeException("Stub!");
    }

    public void setDuration(@Duration int duration) {
        throw new RuntimeException("Stub!");
    }

    @Duration
    public int getDuration() {
        throw new RuntimeException("Stub!");
    }

    public void setMargin(float horizontalMargin, float verticalMargin) {
        throw new RuntimeException("Stub!");
    }

    public float getHorizontalMargin() {
        throw new RuntimeException("Stub!");
    }

    public float getVerticalMargin() {
        throw new RuntimeException("Stub!");
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        throw new RuntimeException("Stub!");
    }

    public int getGravity() {
        throw new RuntimeException("Stub!");
    }

    public int getXOffset() {
        throw new RuntimeException("Stub!");
    }

    public int getYOffset() {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public WindowManager.LayoutParams getWindowParams() {
        throw new RuntimeException("Stub!");
    }

    public static Toast makeText(Context context, CharSequence text, @Duration int duration) {
        throw new RuntimeException("Stub!");
    }

    public static Toast makeText(@NonNull Context context, @Nullable Looper looper, @NonNull CharSequence text,
            @Duration int duration) {
        throw new RuntimeException("Stub!");
    }

    public void setText(@StringRes int resId) {
        throw new RuntimeException("Stub!");
    }

    public void setText(CharSequence s) {
        throw new RuntimeException("Stub!");
    }

    // =======================================================================================
    // All the gunk below is the interaction with the Notification Service, which
    // handles
    // the proper ordering of these system-wide.
    // =======================================================================================

}
