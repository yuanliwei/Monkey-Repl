
package android.os;

import android.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Parcelable {
    @IntDef(flag = true, prefix = { "PARCELABLE_" }, value = { PARCELABLE_WRITE_RETURN_VALUE,
            PARCELABLE_ELIDE_DUPLICATES, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface WriteFlags {
    }

    public static final int PARCELABLE_WRITE_RETURN_VALUE = 0x0001;

    public static final int PARCELABLE_ELIDE_DUPLICATES = 0x0002;

    @IntDef(flag = true, prefix = { "CONTENTS_" }, value = { CONTENTS_FILE_DESCRIPTOR, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentsFlags {
    }

    public static final int CONTENTS_FILE_DESCRIPTOR = 0x0001;

    public @ContentsFlags int describeContents();

    public void writeToParcel(Parcel dest, @WriteFlags int flags);

    public interface Creator<T> {
        public T createFromParcel(Parcel source);

        public T[] newArray(int size);
    }

    public interface ClassLoaderCreator<T> extends Creator<T> {
        public T createFromParcel(Parcel source, ClassLoader loader);
    }
}
