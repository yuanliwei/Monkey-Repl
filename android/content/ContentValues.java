
package android.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;

public final class ContentValues implements Parcelable {
    public static final String TAG = "ContentValues";

    @Deprecated
    @UnsupportedAppUsage

    public ContentValues() {
        throw new RuntimeException("Stub!");
    }

    public ContentValues(int size) {
        throw new RuntimeException("Stub!");
    }

    public ContentValues(ContentValues from) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    @UnsupportedAppUsage
    private ContentValues(HashMap<String, Object> from) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean equals(Object object) {
        throw new RuntimeException("Stub!");
    }

    public ArrayMap<String, Object> getValues() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public void put(String key, String value) {
        throw new RuntimeException("Stub!");
    }

    public void putAll(ContentValues other) {
        throw new RuntimeException("Stub!");
    }

    public void put(String key, Byte value) {
        throw new RuntimeException("Stub!");
    }

    public void put(String key, Short value) {
        throw new RuntimeException("Stub!");
    }

    public void put(String key, Integer value) {
        throw new RuntimeException("Stub!");
    }

    public void put(String key, Long value) {
        throw new RuntimeException("Stub!");
    }

    public void put(String key, Float value) {
        throw new RuntimeException("Stub!");
    }

    public void put(String key, Double value) {
        throw new RuntimeException("Stub!");
    }

    public void put(String key, Boolean value) {
        throw new RuntimeException("Stub!");
    }

    public void put(String key, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public void putNull(String key) {
        throw new RuntimeException("Stub!");
    }

    public int size() {
        throw new RuntimeException("Stub!");
    }

    public boolean isEmpty() {
        throw new RuntimeException("Stub!");
    }

    public void remove(String key) {
        throw new RuntimeException("Stub!");
    }

    public void clear() {
        throw new RuntimeException("Stub!");
    }

    public boolean containsKey(String key) {
        throw new RuntimeException("Stub!");
    }

    public Object get(String key) {
        throw new RuntimeException("Stub!");
    }

    public String getAsString(String key) {
        throw new RuntimeException("Stub!");
    }

    public Long getAsLong(String key) {
        throw new RuntimeException("Stub!");
    }

    public Integer getAsInteger(String key) {
        throw new RuntimeException("Stub!");
    }

    public Short getAsShort(String key) {
        throw new RuntimeException("Stub!");
    }

    public Byte getAsByte(String key) {
        throw new RuntimeException("Stub!");
    }

    public Double getAsDouble(String key) {
        throw new RuntimeException("Stub!");
    }

    public Float getAsFloat(String key) {
        throw new RuntimeException("Stub!");
    }

    public Boolean getAsBoolean(String key) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getAsByteArray(String key) {
        throw new RuntimeException("Stub!");
    }

    public Set<Map.Entry<String, Object>> valueSet() {
        throw new RuntimeException("Stub!");
    }

    public Set<String> keySet() {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<ContentValues> CREATOR = new Parcelable.Creator<ContentValues>() {
        @Override
        public ContentValues createFromParcel(Parcel in) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public ContentValues[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    @UnsupportedAppUsage
    public void putStringArrayList(String key, ArrayList<String> value) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    @UnsupportedAppUsage
    public ArrayList<String> getStringArrayList(String key) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }
}
