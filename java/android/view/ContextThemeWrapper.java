
package android.view;

import android.annotation.StyleRes;
import android.annotation.UnsupportedAppUsage;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;

public class ContextThemeWrapper extends ContextWrapper {

    public ContextThemeWrapper() {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public ContextThemeWrapper(Context base, @StyleRes int themeResId) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public ContextThemeWrapper(Context base, Resources.Theme theme) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        throw new RuntimeException("Stub!");
    }

    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        throw new RuntimeException("Stub!");
    }

    public Configuration getOverrideConfiguration() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public AssetManager getAssets() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Resources getResources() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public Object getSystemService(String name) {
        throw new RuntimeException("Stub!");
    }

    protected void onApplyThemeResource(Resources.Theme theme, int resId, boolean first) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    private void initializeTheme() {
        throw new RuntimeException("Stub!");
    }
}
