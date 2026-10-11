package android.app;

public interface IApplicationThread extends android.os.IInterface {

    public static abstract class Stub extends android.os.Binder implements IApplicationThread {

        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IApplicationThread asInterface(android.os.IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public android.os.IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }
}
