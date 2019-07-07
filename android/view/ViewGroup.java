package android.view;

import java.util.ArrayList;

import android.annotation.UiThread;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

@UiThread
public abstract class ViewGroup extends View implements ViewParent, ViewManager {
    protected ArrayList<View> mDisappearingChildren;
    View mFocusedInCluster;
    RectF mInvalidateRegion;
    protected int mGroupFlags;
    static final int FLAG_CLIP_CHILDREN = 0x1;
    static final int FLAG_INVALIDATE_REQUIRED = 0x4;
    static final int FLAG_ANIMATION_DONE = 0x10;
    static final int FLAG_OPTIMIZE_INVALIDATE = 0x80;
    static final int FLAG_CLEAR_TRANSFORMATION = 0x100;
    protected static final int FLAG_USE_CHILD_DRAWING_ORDER = 0x400;
    protected static final int FLAG_SUPPORT_STATIC_TRANSFORMATIONS = 0x800;
    public static final int FOCUS_BEFORE_DESCENDANTS = 0x20000;
    public static final int FOCUS_AFTER_DESCENDANTS = 0x40000;
    public static final int FOCUS_BLOCK_DESCENDANTS = 0x60000;
    protected static final int FLAG_DISALLOW_INTERCEPT = 0x80000;
    static final int FLAG_IS_TRANSITION_GROUP = 0x1000000;
    static final int FLAG_IS_TRANSITION_GROUP_SET = 0x2000000;
    static final int FLAG_TOUCHSCREEN_BLOCKS_FOCUS = 0x4000000;
    protected int mPersistentDrawingCache;
    public static final int PERSISTENT_NO_CACHE = 0x0;
    public static final int PERSISTENT_ANIMATION_CACHE = 0x1;
    public static final int PERSISTENT_SCROLLING_CACHE = 0x2;
    public static final int PERSISTENT_ALL_CACHES = 0x3;
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;
    public static int LAYOUT_MODE_DEFAULT = LAYOUT_MODE_CLIP_BOUNDS;

    public static class LayoutParams {
        public static final int FILL_PARENT = -1;

        public static final int MATCH_PARENT = -1;

        public static final int WRAP_CONTENT = -2;

        public int width;

        public int height;

        public LayoutParams(Context c, AttributeSet attrs) {
            throw new RuntimeException("Stub!");
        }

        public LayoutParams(int width, int height) {
            throw new RuntimeException("Stub!");
        }

        public LayoutParams(LayoutParams source) {
            throw new RuntimeException("Stub!");
        }

        LayoutParams() {
            throw new RuntimeException("Stub!");
        }

        protected void setBaseAttributes(TypedArray a, int widthAttr, int heightAttr) {
            throw new RuntimeException("Stub!");
        }

        public void resolveLayoutDirection(int layoutDirection) {
            throw new RuntimeException("Stub!");
        }

        public String debug(String output) {
            throw new RuntimeException("Stub!");
        }

        public void onDebugDraw(View view, Canvas canvas, Paint paint) {
            throw new RuntimeException("Stub!");
        }

        protected static String sizeToString(int size) {
            throw new RuntimeException("Stub!");
        }

    }
}