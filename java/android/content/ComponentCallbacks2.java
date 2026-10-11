
package android.content;

import android.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ComponentCallbacks2 extends ComponentCallbacks {

    @IntDef(prefix = { "TRIM_MEMORY_" }, value = { TRIM_MEMORY_COMPLETE, TRIM_MEMORY_MODERATE, TRIM_MEMORY_BACKGROUND,
            TRIM_MEMORY_UI_HIDDEN, TRIM_MEMORY_RUNNING_CRITICAL, TRIM_MEMORY_RUNNING_LOW,
            TRIM_MEMORY_RUNNING_MODERATE, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TrimMemoryLevel {
    }

    static final int TRIM_MEMORY_COMPLETE = 80;

    static final int TRIM_MEMORY_MODERATE = 60;

    static final int TRIM_MEMORY_BACKGROUND = 40;

    static final int TRIM_MEMORY_UI_HIDDEN = 20;

    static final int TRIM_MEMORY_RUNNING_CRITICAL = 15;

    static final int TRIM_MEMORY_RUNNING_LOW = 10;

    static final int TRIM_MEMORY_RUNNING_MODERATE = 5;

    void onTrimMemory(@TrimMemoryLevel int level);
}
