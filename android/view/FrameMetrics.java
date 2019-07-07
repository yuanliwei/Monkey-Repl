
package android.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.annotation.IntDef;

public final class FrameMetrics {

    public static final int UNKNOWN_DELAY_DURATION = 0;

    public static final int INPUT_HANDLING_DURATION = 1;

    public static final int ANIMATION_DURATION = 2;

    public static final int LAYOUT_MEASURE_DURATION = 3;
    public static final int DRAW_DURATION = 4;

    public static final int SYNC_DURATION = 5;

    public static final int COMMAND_ISSUE_DURATION = 6;

    public static final int SWAP_BUFFERS_DURATION = 7;

    public static final int TOTAL_DURATION = 8;

    public static final int FIRST_DRAW_FRAME = 9;

    public static final int INTENDED_VSYNC_TIMESTAMP = 10;

    public static final int VSYNC_TIMESTAMP = 11;

    @IntDef({ UNKNOWN_DELAY_DURATION, INPUT_HANDLING_DURATION, ANIMATION_DURATION, LAYOUT_MEASURE_DURATION,
            DRAW_DURATION, SYNC_DURATION, COMMAND_ISSUE_DURATION, SWAP_BUFFERS_DURATION, TOTAL_DURATION,
            FIRST_DRAW_FRAME, INTENDED_VSYNC_TIMESTAMP, VSYNC_TIMESTAMP, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Metric {
    }

    @IntDef({ Index.FLAGS, Index.INTENDED_VSYNC, Index.VSYNC, Index.OLDEST_INPUT_EVENT, Index.NEWEST_INPUT_EVENT,
            Index.HANDLE_INPUT_START, Index.ANIMATION_START, Index.PERFORM_TRAVERSALS_START, Index.DRAW_START,
            Index.SYNC_QUEUED, Index.SYNC_START, Index.ISSUE_DRAW_COMMANDS_START, Index.SWAP_BUFFERS,
            Index.FRAME_COMPLETED, })
    @Retention(RetentionPolicy.SOURCE)
    private @interface Index {
        int FLAGS = 0;
        int INTENDED_VSYNC = 1;
        int VSYNC = 2;
        int OLDEST_INPUT_EVENT = 3;
        int NEWEST_INPUT_EVENT = 4;
        int HANDLE_INPUT_START = 5;
        int ANIMATION_START = 6;
        int PERFORM_TRAVERSALS_START = 7;
        int DRAW_START = 8;
        int SYNC_QUEUED = 9;
        int SYNC_START = 10;
        int ISSUE_DRAW_COMMANDS_START = 11;
        int SWAP_BUFFERS = 12;
        int FRAME_COMPLETED = 13;
    }

    public FrameMetrics(FrameMetrics other) {
        throw new RuntimeException("Stub!");
    }

    FrameMetrics() {
        throw new RuntimeException("Stub!");
    }

    public long getMetric(@Metric int id) {
        throw new RuntimeException("Stub!");
    }
}
