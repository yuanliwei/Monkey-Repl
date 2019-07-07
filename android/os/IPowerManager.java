package android.os;

public interface IPowerManager extends android.os.IInterface {

    public static abstract class Stub extends android.os.Binder implements IPowerManager {

        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IPowerManager asInterface(android.os.IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public android.os.IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    void wakeUp(long time, String reason, String opPackageName) throws RemoteException;
}
