
package android.os;

import android.annotation.NonNull;

public class HandlerThread extends Thread {
    int mPriority;
    int mTid = -1;
    Looper mLooper;

    public HandlerThread(String name) {
        throw new RuntimeException("Stub!");
    }

    public HandlerThread(String name, int priority) {
        throw new RuntimeException("Stub!");
    }

    protected void onLooperPrepared() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void run() {
        throw new RuntimeException("Stub!");
    }

    public Looper getLooper() {
        return mLooper;
    }

    @NonNull
    public Handler getThreadHandler() {
        throw new RuntimeException("Stub!");
    }

    public boolean quit() {
        throw new RuntimeException("Stub!");
    }

    public boolean quitSafely() {
        throw new RuntimeException("Stub!");
    }

    public int getThreadId() {
        throw new RuntimeException("Stub!");
    }
}
