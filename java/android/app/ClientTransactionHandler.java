package android.app;

import android.content.res.Configuration;
import android.os.IBinder;

public abstract class ClientTransactionHandler {
        abstract void sendMessage(int what, Object obj);

        public abstract void updatePendingConfiguration(Configuration config);

        public abstract void updateProcessState(int processState, boolean fromIpc);

        public abstract void handleDestroyActivity(IBinder token, boolean finishing, int configChanges,
                        boolean getNonConfigInstance, String reason);

        public abstract void handleResumeActivity(IBinder token, boolean finalStateRequest, boolean isForward,
                        String reason);

        public abstract void performRestartActivity(IBinder token, boolean start);

        public abstract void handleActivityConfigurationChanged(IBinder activityToken, Configuration overrideConfig,
                        int displayId);

        public abstract void handleMultiWindowModeChanged(IBinder token, boolean isInMultiWindowMode,
                        Configuration overrideConfig);

        public abstract void handlePictureInPictureModeChanged(IBinder token, boolean isInPipMode,
                        Configuration overrideConfig);

        public abstract void handleWindowVisibility(IBinder token, boolean show);

        public abstract void handleConfigurationChanged(Configuration config);

        public abstract ActivityThread.ActivityClientRecord getActivityClient(IBinder token);
}