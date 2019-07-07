
package android.os;

import android.util.AndroidException;

public class RemoteException extends AndroidException {

    private static final long serialVersionUID = -8954933872724433428L;

    public RemoteException() {
        throw new RuntimeException("Stub!");
    }

    public RemoteException(String message) {
        throw new RuntimeException("Stub!");
    }

    public RemoteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        throw new RuntimeException("Stub!");
    }

    public RuntimeException rethrowAsRuntimeException() {
        throw new RuntimeException("Stub!");
    }

    public RuntimeException rethrowFromSystemServer() {
        throw new RuntimeException("Stub!");
    }
}
