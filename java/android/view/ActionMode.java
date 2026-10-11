
package android.view;

import android.annotation.StringRes;
import android.annotation.TestApi;
import android.graphics.Rect;

public abstract class ActionMode {

    public static final int TYPE_PRIMARY = 0;
    public static final int TYPE_FLOATING = 1;

    public static final int DEFAULT_HIDE_DURATION = -1;

    public void setTag(Object tag) {
        throw new RuntimeException("Stub!");
    }

    public Object getTag() {
        throw new RuntimeException("Stub!");
    }

    public abstract void setTitle(CharSequence title);

    public abstract void setTitle(@StringRes int resId);

    public abstract void setSubtitle(CharSequence subtitle);

    public abstract void setSubtitle(@StringRes int resId);

    public void setTitleOptionalHint(boolean titleOptional) {
        throw new RuntimeException("Stub!");
    }

    public boolean getTitleOptionalHint() {
        throw new RuntimeException("Stub!");
    }

    public boolean isTitleOptional() {
        throw new RuntimeException("Stub!");
    }

    public abstract void setCustomView(View view);

    public void setType(int type) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public abstract void invalidate();

    public void invalidateContentRect() {
        throw new RuntimeException("Stub!");
    }

    public void hide(long duration) {
        throw new RuntimeException("Stub!");
    }

    public abstract void finish();

    public abstract Menu getMenu();

    public abstract CharSequence getTitle();

    public abstract CharSequence getSubtitle();

    public abstract View getCustomView();

    public abstract MenuInflater getMenuInflater();

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        throw new RuntimeException("Stub!");
    }

    @TestApi
    public boolean isUiFocusable() {
        throw new RuntimeException("Stub!");
    }

    public interface Callback {
        public boolean onCreateActionMode(ActionMode mode, Menu menu);

        public boolean onPrepareActionMode(ActionMode mode, Menu menu);

        public boolean onActionItemClicked(ActionMode mode, MenuItem item);

        public void onDestroyActionMode(ActionMode mode);
    }

    public static abstract class Callback2 implements ActionMode.Callback {

        public void onGetContentRect(ActionMode mode, View view, Rect outRect) {
            throw new RuntimeException("Stub!");
        }

    }
}
