
package android.view;

public final class InputQueue {

    public static interface Callback {
        void onInputQueueCreated(InputQueue queue);

        void onInputQueueDestroyed(InputQueue queue);
    }

    public static interface FinishedInputEventCallback {
        void onFinishedInputEvent(Object token, boolean handled);
    }

}
