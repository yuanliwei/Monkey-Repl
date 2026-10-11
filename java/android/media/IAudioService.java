package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/** IAudioService stub（编译期占位，运行时用真机类） */
public interface IAudioService extends IInterface {

    public static abstract class Stub extends Binder implements IAudioService {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IAudioService asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    int getStreamVolume(int streamType) throws RemoteException;

    int getStreamMaxVolume(int streamType) throws RemoteException;

    int getStreamMinVolume(int streamType) throws RemoteException;

    boolean isStreamMute(int streamType) throws RemoteException;

    void setStreamVolume(int streamType, int index, int flags, String callingPackage) throws RemoteException;

    void adjustStreamVolume(int streamType, int direction, int flags, String callingPackage) throws RemoteException;

    void setRingerModeExternal(int ringerMode, String callingPackage) throws RemoteException;

    void setRingerModeInternal(int ringerMode, String callingPackage) throws RemoteException;

    int getRingerModeExternal() throws RemoteException;

    int getRingerModeInternal() throws RemoteException;

    boolean isMasterMute() throws RemoteException;

    void setMasterMute(boolean mute, int flags, String callingPackage, int userId, String attributionTag) throws RemoteException;

    boolean isMusicActive(boolean isOnTwos) throws RemoteException;
}
