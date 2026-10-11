package android.net;

public class IpConfiguration {
    public enum IpAssignment { STATIC, DHCP, UNASSIGNED }
    public enum ProxySettings { NONE, STATIC, PAC, UNASSIGNED }

    public IpConfiguration() {
        throw new RuntimeException("Stub!");
    }

    public IpAssignment getIpAssignment() {
        throw new RuntimeException("Stub!");
    }

    public void setIpAssignment(IpAssignment ipAssignment) {
        throw new RuntimeException("Stub!");
    }

    public StaticIpConfiguration getStaticIpConfiguration() {
        throw new RuntimeException("Stub!");
    }

    public void setStaticIpConfiguration(StaticIpConfiguration staticIpConfiguration) {
        throw new RuntimeException("Stub!");
    }

    public ProxySettings getProxySettings() {
        throw new RuntimeException("Stub!");
    }

    public void setProxySettings(ProxySettings proxySettings) {
        throw new RuntimeException("Stub!");
    }
}
