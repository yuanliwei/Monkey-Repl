package android.content.pm;

public class ComponentInfo extends PackageItemInfo {
  public ComponentInfo(ServiceInfo orig) {
    throw new RuntimeException("Stub!");
  }

  public ApplicationInfo applicationInfo;
  public String processName;
  public String splitName;
  public int descriptionRes;
  public boolean enabled = true;
  public boolean exported = false;
  public boolean directBootAware = false;
  public boolean encryptionAware = false;
}