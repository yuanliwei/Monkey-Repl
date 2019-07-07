
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class ParceledListSlice<T extends Parcelable> extends BaseParceledListSlice<T> {

    ParceledListSlice(Parcel p, ClassLoader loader) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    protected void writeElement(T parcelable, Parcel reply, int callFlags) {
        throw new RuntimeException("Stub!");
    }

    @Override
    protected void writeParcelableCreator(T parcelable, Parcel dest) {
        throw new RuntimeException("Stub!");
    }

    @Override
    protected Creator<?> readParcelableCreator(Parcel from, ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

}
