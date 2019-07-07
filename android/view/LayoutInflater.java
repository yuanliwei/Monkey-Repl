
package android.view;

import android.annotation.SystemService;
import android.content.Context;
import android.util.AttributeSet;

@SystemService(Context.LAYOUT_INFLATER_SERVICE)
public abstract class LayoutInflater {

    public interface Factory {
        public View onCreateView(String name, Context context, AttributeSet attrs);
    }

    public interface Factory2 extends Factory {
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs);
    }

}
