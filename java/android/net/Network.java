
package android.net;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

import android.annotation.NonNull;
import android.annotation.SystemApi;
import android.annotation.TestApi;
import android.os.Parcel;
import android.os.Parcelable;

public class Network implements Parcelable {

    public final int netId;

    public Network(int netId) {
        throw new RuntimeException("Stub!");
    }

    public Network(int netId, boolean privateDnsBypass) {
        throw new RuntimeException("Stub!");
    }

    @SystemApi
    @TestApi
    public Network(@NonNull Network that) {
        throw new RuntimeException("Stub!");
    }

    public InetAddress[] getAllByName(String host) throws UnknownHostException {
        return null;
    }

    public InetAddress getByName(String host) throws UnknownHostException {
        return null;
    }

    @TestApi
    @SystemApi
    public @NonNull Network getPrivateDnsBypassingCopy() {
        throw new RuntimeException("Stub!");
    }

    public int getNetIdForResolv() {
        throw new RuntimeException("Stub!");
    }

    public SocketFactory getSocketFactory() {
        throw new RuntimeException("Stub!");
    }

    public URLConnection openConnection(URL url) throws IOException {

        return null;

    }

    public URLConnection openConnection(URL url, java.net.Proxy proxy) throws IOException {
        return null;

    }

    public void bindSocket(DatagramSocket socket) throws IOException {
        // Query a property of the underlying socket to ensure that the socket's file
        // descriptor
        // exists, is available to bind to a network and is not closed.
        socket.getReuseAddress();

    }

    public void bindSocket(Socket socket) throws IOException {
        // Query a property of the underlying socket to ensure that the socket's file
        // descriptor
        // exists, is available to bind to a network and is not closed.
        socket.getReuseAddress();
    }

    public void bindSocket(FileDescriptor fd) throws IOException {

        throw new IllegalStateException("not implemented! ");

    }

    public static Network fromNetworkHandle(long networkHandle) {
        throw new RuntimeException("Stub!");
    }

    public long getNetworkHandle() {
        throw new RuntimeException("Stub!");
    }

    // implement the Parcelable interface
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static final Creator<Network> CREATOR = new Creator<Network>() {
        public Network createFromParcel(Parcel in) {
            throw new RuntimeException("Stub!");
        }

        public Network[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

}
