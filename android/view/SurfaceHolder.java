
package android.view;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface SurfaceHolder {

    @Deprecated
    public static final int SURFACE_TYPE_NORMAL = 0;
    @Deprecated
    public static final int SURFACE_TYPE_HARDWARE = 1;
    @Deprecated
    public static final int SURFACE_TYPE_GPU = 2;
    @Deprecated
    public static final int SURFACE_TYPE_PUSH_BUFFERS = 3;

    public static class BadSurfaceTypeException extends RuntimeException {

        private static final long serialVersionUID = -1260794923317674554L;

        public BadSurfaceTypeException() {
            throw new RuntimeException("Stub!");
        }

        public BadSurfaceTypeException(String name) {
            throw new RuntimeException("Stub!");
        }
    }

    public interface Callback {
        public void surfaceCreated(SurfaceHolder holder);

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height);

        public void surfaceDestroyed(SurfaceHolder holder);
    }

    public interface Callback2 extends Callback {
        void surfaceRedrawNeeded(SurfaceHolder holder);

        default void surfaceRedrawNeededAsync(SurfaceHolder holder, Runnable drawingFinished) {
            throw new RuntimeException("Stub!");
        }
    }

    public void addCallback(Callback callback);

    public void removeCallback(Callback callback);

    public boolean isCreating();

    @Deprecated
    public void setType(int type);

    public void setFixedSize(int width, int height);

    public void setSizeFromLayout();

    public void setFormat(int format);

    public void setKeepScreenOn(boolean screenOn);

    public Canvas lockCanvas();

    public Canvas lockCanvas(Rect dirty);

    default Canvas lockHardwareCanvas() {
        throw new RuntimeException("Stub!");
    }

    public void unlockCanvasAndPost(Canvas canvas);

    public Rect getSurfaceFrame();

    public Surface getSurface();
}
