package android.app;

import android.graphics.Bitmap;

public interface IThumbnailReceiver {
    void newThumbnail(int id, Bitmap thumbnail, CharSequence description);

    void finished();
}
