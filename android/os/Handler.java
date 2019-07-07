
package android.os;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.UnsupportedAppUsage;

public class Handler {
    public interface Callback {
        public boolean handleMessage(Message msg);
    }

    public void handleMessage(Message msg) {
        throw new RuntimeException("Stub!");
    }

    public void dispatchMessage(Message msg) {
        throw new RuntimeException("Stub!");
    }

    public Handler() {
        throw new RuntimeException("Stub!");
    }

    public Handler(Callback callback) {
        throw new RuntimeException("Stub!");
    }

    public Handler(Looper looper) {
        throw new RuntimeException("Stub!");
    }

    public Handler(Looper looper, Callback callback) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public Handler(boolean async) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public Handler(Looper looper, Callback callback, boolean async) {
        throw new RuntimeException("Stub!");
    }

    @NonNull
    public static Handler createAsync(@NonNull Looper looper) {
        throw new RuntimeException("Stub!");
    }

    @NonNull
    public static Handler createAsync(@NonNull Looper looper, @NonNull Callback callback) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    @NonNull
    public static Handler getMain() {
        throw new RuntimeException("Stub!");
    }

    @NonNull
    public static Handler mainIfNull(@Nullable Handler handler) {
        throw new RuntimeException("Stub!");
    }

    public String getTraceName(Message message) {
        throw new RuntimeException("Stub!");
    }

    public String getMessageName(Message message) {
        throw new RuntimeException("Stub!");
    }

    public final Message obtainMessage() {
        throw new RuntimeException("Stub!");
    }

    public final Message obtainMessage(int what) {
        throw new RuntimeException("Stub!");
    }

    public final Message obtainMessage(int what, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public final Message obtainMessage(int what, int arg1, int arg2) {
        throw new RuntimeException("Stub!");
    }

    public final Message obtainMessage(int what, int arg1, int arg2, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public final boolean post(Runnable r) {
        throw new RuntimeException("Stub!");
    }

    public final boolean postAtTime(Runnable r, long uptimeMillis) {
        throw new RuntimeException("Stub!");
    }

    public final boolean postAtTime(Runnable r, Object token, long uptimeMillis) {
        throw new RuntimeException("Stub!");
    }

    public final boolean postDelayed(Runnable r, long delayMillis) {
        throw new RuntimeException("Stub!");
    }

    public final boolean postDelayed(Runnable r, Object token, long delayMillis) {
        throw new RuntimeException("Stub!");
    }

    public final boolean postAtFrontOfQueue(Runnable r) {
        throw new RuntimeException("Stub!");
    }

    public final boolean runWithScissors(final Runnable r, long timeout) {
        throw new RuntimeException("Stub!");
    }

    public final void removeCallbacks(Runnable r) {
        throw new RuntimeException("Stub!");
    }

    public final void removeCallbacks(Runnable r, Object token) {
        throw new RuntimeException("Stub!");
    }

    public final boolean sendMessage(Message msg) {
        throw new RuntimeException("Stub!");
    }

    public final boolean sendEmptyMessage(int what) {
        throw new RuntimeException("Stub!");
    }

    public final boolean sendEmptyMessageDelayed(int what, long delayMillis) {
        throw new RuntimeException("Stub!");
    }

    public final boolean sendEmptyMessageAtTime(int what, long uptimeMillis) {
        throw new RuntimeException("Stub!");
    }

    public final boolean sendMessageDelayed(Message msg, long delayMillis) {
        throw new RuntimeException("Stub!");
    }

    public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
        throw new RuntimeException("Stub!");
    }

    public final boolean sendMessageAtFrontOfQueue(Message msg) {
        throw new RuntimeException("Stub!");
    }

    public final boolean executeOrSendMessage(Message msg) {
        throw new RuntimeException("Stub!");
    }

    public final void removeMessages(int what) {
        throw new RuntimeException("Stub!");
    }

    public final void removeMessages(int what, Object object) {
        throw new RuntimeException("Stub!");
    }

    public final void removeCallbacksAndMessages(Object token) {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasMessages(int what) {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasMessagesOrCallbacks() {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasMessages(int what, Object object) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public final boolean hasCallbacks(Runnable r) {
        throw new RuntimeException("Stub!");
    }

    // if we can get rid of this method, the handler need not remember its loop
    // we could instead export a getMessageQueue() method...
    public final Looper getLooper() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    private static Message getPostMessage(Runnable r, Object token) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    final Looper mLooper;
    final MessageQueue mQueue;
    @UnsupportedAppUsage
    final Callback mCallback;
    final boolean mAsynchronous;
}
