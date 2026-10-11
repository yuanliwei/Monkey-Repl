
package android.os;

import java.util.Map;

import android.annotation.UnsupportedAppUsage;

public final class ServiceManager {

    interface Stats {
        int GET_SERVICE = 0;

        int COUNT = GET_SERVICE + 1;
    }

    @UnsupportedAppUsage
    public static IBinder getService(String name) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public static void addService(String name, IBinder service) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public static void addService(String name, IBinder service, boolean allowIsolated) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public static void addService(String name, IBinder service, boolean allowIsolated, int dumpPriority) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public static IBinder checkService(String name) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public static String[] listServices() {
        throw new RuntimeException("Stub!");
    }

    public static void initServiceCache(Map<String, IBinder> cache) {
        throw new RuntimeException("Stub!");
    }

    public static class ServiceNotFoundException extends Exception {
        private static final long serialVersionUID = 1L;

        public ServiceNotFoundException(String name) {
            throw new RuntimeException("Stub!");
        }
    }

}