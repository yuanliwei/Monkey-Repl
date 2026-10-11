
package android.content.pm;

import java.util.List;

import android.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

abstract class BaseParceledListSlice<T> implements Parcelable {

    public BaseParceledListSlice(List<T> list) {
        throw new RuntimeException("Stub!");
    }

    BaseParceledListSlice(Parcel p, ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public List<T> getList() {
        throw new RuntimeException("Stub!");
    }

    public void setInlineCountLimit(int maxCount) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    protected abstract void writeElement(T parcelable, Parcel reply, int callFlags);

    @UnsupportedAppUsage
    protected abstract void writeParcelableCreator(T parcelable, Parcel dest);

    protected abstract Parcelable.Creator<?> readParcelableCreator(Parcel from, ClassLoader loader);
}
