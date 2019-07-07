
package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.proto.ProtoOutputStream;

public class ClipDescription implements Parcelable {

    public static final String EXTRA_TARGET_COMPONENT_NAME = "android.content.extra.TARGET_COMPONENT_NAME";

    public static final String EXTRA_USER_SERIAL_NUMBER = "android.content.extra.USER_SERIAL_NUMBER";

    final CharSequence mLabel;

    public ClipDescription(CharSequence label, String[] mimeTypes) {
        throw new RuntimeException("Stub!");
    }

    public ClipDescription(ClipDescription o) {
        throw new RuntimeException("Stub!");
    }

    public static boolean compareMimeTypes(String concreteType, String desiredType) {
        throw new RuntimeException("Stub!");

    }

    public void setTimestamp(long timeStamp) {
        throw new RuntimeException("Stub!");
    }

    public long getTimestamp() {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getLabel() {
        throw new RuntimeException("Stub!");
    }

    public boolean hasMimeType(String mimeType) {
        throw new RuntimeException("Stub!");
    }

    public int getMimeTypeCount() {
        throw new RuntimeException("Stub!");
    }

    public String getMimeType(int index) {
        throw new RuntimeException("Stub!");
    }

    void addMimeTypes(String[] mimeTypes) {
        throw new RuntimeException("Stub!");
    }

    public void validate() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");

    }

    public boolean toShortString(StringBuilder b) {
        throw new RuntimeException("Stub!");

    }

    public boolean toShortStringTypesOnly(StringBuilder b) {
        throw new RuntimeException("Stub!");
    }

    public void writeToProto(ProtoOutputStream proto, long fieldId) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    ClipDescription(Parcel in) {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<ClipDescription> CREATOR = new Parcelable.Creator<ClipDescription>() {

        public ClipDescription createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public ClipDescription[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}
