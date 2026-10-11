package android.media;

import android.content.Context;
import android.net.Uri;

public class MediaPlayer {
    public static MediaPlayer create(Context context, Uri uri) {
        return null;
    }

    public void setDataSource(String path) {
    }

    public native void prepareAsync() throws IllegalStateException;

    public void start() throws IllegalStateException {
    }

    public void prepare() {
    }

    public void release() {
    }

    public void reset() {
    }

    public native int getDuration();

    public void setAudioStreamType(int streamtype) {
    }

    public interface OnCompletionListener {
        void onCompletion(MediaPlayer mp);
    }

    public void setOnCompletionListener(OnCompletionListener listener) {
    }

    public interface OnErrorListener {
        boolean onError(MediaPlayer mp, int what, int extra);
    }

    public void setOnErrorListener(OnErrorListener listener) {
    }

}
