package android.app;

public interface IInstrumentationWatcher extends android.os.IInterface {

    public static abstract class Stub extends android.os.Binder implements IInstrumentationWatcher {

        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IInstrumentationWatcher asInterface(android.os.IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public android.os.IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

}
