package android.content;

public interface IClipboard extends android.os.IInterface {

    public static abstract class Stub extends android.os.Binder implements IClipboard {

        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IClipboard asInterface(android.os.IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public android.os.IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    void setPrimaryClip(ClipData clip, String callingPackage);

    void clearPrimaryClip(String callingPackage);

    ClipData getPrimaryClip(String pkg);

    ClipDescription getPrimaryClipDescription(String callingPackage);

    boolean hasPrimaryClip(String callingPackage);

    void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener listener, String callingPackage);

    void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener listener);

    /**
     * Returns true if the clipboard contains text; false otherwise.
     */
    boolean hasClipboardText(String callingPackage);

}
