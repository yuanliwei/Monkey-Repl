
package android.view.accessibility;

public interface AccessibilityEventSource {

    public void sendAccessibilityEvent(int eventType);

    public void sendAccessibilityEventUnchecked(AccessibilityEvent event);
}
