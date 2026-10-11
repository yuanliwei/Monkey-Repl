package android.net;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class StaticIpConfiguration {
    public LinkAddress ipAddress;
    public InetAddress gateway;
    public ArrayList<InetAddress> dnsServers;
    public String domains;

    public StaticIpConfiguration() {
        throw new RuntimeException("Stub!");
    }

    public LinkAddress getIpAddress() {
        throw new RuntimeException("Stub!");
    }

    public InetAddress getGateway() {
        throw new RuntimeException("Stub!");
    }

    public List<InetAddress> getDnsServers() {
        throw new RuntimeException("Stub!");
    }
}
