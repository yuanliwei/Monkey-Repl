
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.PatternMatcher;

public class PathPermission extends PatternMatcher {

    public PathPermission(String pattern, int type, String readPermission, String writePermission) {
        super(pattern, type);
        throw new RuntimeException("Stub!");
    }

    public String getReadPermission() {
        throw new RuntimeException("Stub!");
    }

    public String getWritePermission() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    public PathPermission(Parcel src) {
        super("pattern", 0);
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<PathPermission> CREATOR = new Parcelable.Creator<PathPermission>() {
        public PathPermission createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public PathPermission[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}