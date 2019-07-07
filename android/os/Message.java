
package android.os;

public final class Message implements Parcelable {

    public Object callback;
    public Object what;

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static Message obtain(Handler handler, int what2) {
        throw new RuntimeException("Stub!");
    }

    public static Message obtain(Handler handler, int what2, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public static Message obtain(Handler handler, int what2, int arg1, int arg2) {
        throw new RuntimeException("Stub!");
    }

    public static Message obtain(Handler handler, int what2, int arg1, int arg2, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public static Message obtain() {
        throw new RuntimeException("Stub!");
    }
}
