
package android.os;

import java.io.FileDescriptor;

public class Binder implements IBinder {

    @Override
    public String getInterfaceDescriptor() throws RemoteException {
        return null;
    }

    @Override
    public boolean pingBinder() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean isBinderAlive() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public IInterface queryLocalInterface(String descriptor) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void dump(FileDescriptor fd, String[] args) throws RemoteException {

    }

    @Override
    public void dumpAsync(FileDescriptor fd, String[] args) throws RemoteException {

    }

    @Override
    public void linkToDeath(DeathRecipient recipient, int flags) throws Exception {

    }

    @Override
    public boolean unlinkToDeath(DeathRecipient recipient, int flags) {
        throw new RuntimeException("Stub!");
    }

}
