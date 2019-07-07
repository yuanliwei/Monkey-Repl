
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class ResolveInfo implements Parcelable {

    public ActivityInfo activityInfo;

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }
}
