package android.os;

import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;

public class ParcelFileDescriptor implements Parcelable, Closeable {
    @Deprecated
    public static final int MODE_WORLD_READABLE = 0x00000001;
    @Deprecated
    public static final int MODE_WORLD_WRITEABLE = 0x00000002;
    public static final int MODE_READ_ONLY = 0x10000000;
    public static final int MODE_WRITE_ONLY = 0x20000000;
    public static final int MODE_READ_WRITE = 0x30000000;
    public static final int MODE_CREATE = 0x08000000;
    public static final int MODE_TRUNCATE = 0x04000000;
    public static final int MODE_APPEND = 0x02000000;

    public ParcelFileDescriptor(ParcelFileDescriptor wrapped) {
        throw new RuntimeException("Stub!");
    }

    public ParcelFileDescriptor(FileDescriptor fd) {
        throw new RuntimeException("Stub!");
    }

    public ParcelFileDescriptor(FileDescriptor fd, FileDescriptor commChannel) {
        throw new RuntimeException("Stub!");
    }

    public static ParcelFileDescriptor open(File file, int mode) throws FileNotFoundException {
        throw new RuntimeException("Stub!");
    }

    public static ParcelFileDescriptor adoptFd(int fd) {
        throw new RuntimeException("Stub!");
    }

    public static ParcelFileDescriptor fromSocket(Socket socket) {
        throw new RuntimeException("Stub!");
    }

    public static ParcelFileDescriptor fromDatagramSocket(DatagramSocket datagramSocket) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static class AutoCloseInputStream extends FileInputStream {

        public AutoCloseInputStream(File file) throws FileNotFoundException {
            super(file);
        }

    }

    public static class AutoCloseOutputStream extends FileOutputStream {

        public AutoCloseOutputStream(File file) throws FileNotFoundException {
            super(file);
        }

    }
}