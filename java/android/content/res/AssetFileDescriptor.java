
package android.content.res;

import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

public class AssetFileDescriptor implements Parcelable, Closeable {
    public static final long UNKNOWN_LENGTH = -1;

    public AssetFileDescriptor(ParcelFileDescriptor fd, long startOffset, long length) {
        throw new RuntimeException("Stub!");
    }

    public AssetFileDescriptor(ParcelFileDescriptor fd, long startOffset, long length, Bundle extras) {
        throw new RuntimeException("Stub!");
    }

    public ParcelFileDescriptor getParcelFileDescriptor() {
        throw new RuntimeException("Stub!");
    }

    public FileDescriptor getFileDescriptor() {
        throw new RuntimeException("Stub!");
    }

    public long getStartOffset() {
        throw new RuntimeException("Stub!");
    }

    public Bundle getExtras() {
        throw new RuntimeException("Stub!");
    }

    public long getLength() {
        throw new RuntimeException("Stub!");
    }

    public long getDeclaredLength() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void close() throws IOException {
    }

    public FileInputStream createInputStream() throws IOException {
        return null;

    }

    public FileOutputStream createOutputStream() throws IOException {
        return null;

    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public static class AutoCloseInputStream extends ParcelFileDescriptor.AutoCloseInputStream {

        public AutoCloseInputStream(AssetFileDescriptor fd) throws IOException {
            super(null);
        }

        @Override
        public int available() throws IOException {
            return 0;

        }

        @Override
        public int read() throws IOException {
            byte[] buffer = new byte[1];
            int result = read(buffer, 0, 1);
            return result == -1 ? -1 : buffer[0] & 0xff;
        }

        @Override
        public int read(byte[] buffer, int offset, int count) throws IOException {

            return super.read(buffer, offset, count);
        }

        @Override
        public int read(byte[] buffer) throws IOException {
            return read(buffer, 0, buffer.length);
        }

        @Override
        public long skip(long count) throws IOException {

            return super.skip(count);
        }

        @Override
        public void mark(int readlimit) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public boolean markSupported() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public synchronized void reset() throws IOException {

            super.reset();
        }
    }

    public static class AutoCloseOutputStream extends ParcelFileDescriptor.AutoCloseOutputStream {

        public AutoCloseOutputStream(AssetFileDescriptor fd) throws IOException {
            super(null);
        }

        @Override
        public void write(byte[] buffer, int offset, int count) throws IOException {

            super.write(buffer, offset, count);
        }

        @Override
        public void write(byte[] buffer) throws IOException {

            super.write(buffer);
        }

        @Override
        public void write(int oneByte) throws IOException {

            super.write(oneByte);
        }
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        throw new RuntimeException("Stub!");
    }

    AssetFileDescriptor(Parcel src) {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<AssetFileDescriptor> CREATOR = new Parcelable.Creator<AssetFileDescriptor>() {
        public AssetFileDescriptor createFromParcel(Parcel in) {
            throw new RuntimeException("Stub!");
        }

        public AssetFileDescriptor[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

}
