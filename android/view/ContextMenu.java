
package android.view;

import android.annotation.DrawableRes;
import android.annotation.StringRes;
import android.graphics.drawable.Drawable;

public interface ContextMenu extends Menu {
    public ContextMenu setHeaderTitle(@StringRes int titleRes);

    public ContextMenu setHeaderTitle(CharSequence title);

    public ContextMenu setHeaderIcon(@DrawableRes int iconRes);

    public ContextMenu setHeaderIcon(Drawable icon);

    public ContextMenu setHeaderView(View view);

    public void clearHeader();

    public interface ContextMenuInfo {
    }
}
