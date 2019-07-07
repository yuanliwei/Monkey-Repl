
package android.os;

import java.io.FileDescriptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.Nullable;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

public final class Parcel {

    static native void nativeWriteString(long nativePtr, String val);

    static native String nativeReadString(long nativePtr);

    public final static Parcelable.Creator<String> STRING_CREATOR = new Parcelable.Creator<String>() {
        public String createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public String[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    public static class ReadWriteHelper {
        public static final ReadWriteHelper DEFAULT = new ReadWriteHelper();

        public void writeString(Parcel p, String s) {
            throw new RuntimeException("Stub!");
        }

        public String readString(Parcel p) {
            throw new RuntimeException("Stub!");
        }
    }

    public static Parcel obtain() {
        throw new RuntimeException("Stub!");
    }

    public final void recycle() {
        throw new RuntimeException("Stub!");
    }

    public void setReadWriteHelper(ReadWriteHelper helper) {
        throw new RuntimeException("Stub!");
    }

    public boolean hasReadWriteHelper() {
        throw new RuntimeException("Stub!");
    }

    public static native long getGlobalAllocSize();

    public static native long getGlobalAllocCount();

    public final int dataSize() {
        throw new RuntimeException("Stub!");
    }

    public final int dataAvail() {
        throw new RuntimeException("Stub!");
    }

    public final int dataPosition() {
        throw new RuntimeException("Stub!");
    }

    public final int dataCapacity() {
        throw new RuntimeException("Stub!");
    }

    public final void setDataSize(int size) {
        throw new RuntimeException("Stub!");
    }

    public final void setDataPosition(int pos) {
        throw new RuntimeException("Stub!");
    }

    public final void setDataCapacity(int size) {
        throw new RuntimeException("Stub!");
    }

    public final boolean pushAllowFds(boolean allowFds) {
        throw new RuntimeException("Stub!");
    }

    public final void restoreAllowFds(boolean lastValue) {
        throw new RuntimeException("Stub!");
    }

    public final byte[] marshall() {
        throw new RuntimeException("Stub!");
    }

    public final void unmarshall(byte[] data, int offset, int length) {
        throw new RuntimeException("Stub!");
    }

    public final void appendFrom(Parcel parcel, int offset, int length) {
        throw new RuntimeException("Stub!");
    }

    public final int compareData(Parcel other) {
        throw new RuntimeException("Stub!");
    }

    public final void setClassCookie(Class clz, Object cookie) {
        throw new RuntimeException("Stub!");
    }

    public final Object getClassCookie(Class clz) {
        throw new RuntimeException("Stub!");
    }

    public final void adoptClassCookies(Parcel from) {
        throw new RuntimeException("Stub!");
    }

    public Map<Class, Object> copyClassCookies() {
        throw new RuntimeException("Stub!");
    }

    public void putClassCookies(Map<Class, Object> cookies) {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasFileDescriptors() {
        throw new RuntimeException("Stub!");
    }

    public final void writeInterfaceToken(String interfaceName) {
        throw new RuntimeException("Stub!");
    }

    public final void enforceInterface(String interfaceName) {
        throw new RuntimeException("Stub!");
    }

    public final void writeByteArray(byte[] b) {
        throw new RuntimeException("Stub!");
    }

    public final void writeByteArray(byte[] b, int offset, int len) {
        throw new RuntimeException("Stub!");
    }

    public final void writeBlob(byte[] b) {
        throw new RuntimeException("Stub!");
    }

    public final void writeBlob(byte[] b, int offset, int len) {
        throw new RuntimeException("Stub!");
    }

    public final void writeInt(int val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeLong(long val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeFloat(float val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeDouble(double val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeString(String val) {
        throw new RuntimeException("Stub!");
    }

    public void writeStringNoHelper(String val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeBoolean(boolean val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeCharSequence(CharSequence val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeStrongBinder(IBinder val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeStrongInterface(IInterface val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeFileDescriptor(FileDescriptor val) {
        throw new RuntimeException("Stub!");
    }

    private void updateNativeSize(long newNativeSize) {
        throw new RuntimeException("Stub!");
    }

    public final void writeRawFileDescriptor(FileDescriptor val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeRawFileDescriptorArray(FileDescriptor[] value) {
        throw new RuntimeException("Stub!");
    }

    public final void writeByte(byte val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeMap(Map val) {
        throw new RuntimeException("Stub!");
    }

    public void writeArrayMap(ArrayMap<String, Object> val) {
        throw new RuntimeException("Stub!");
    }

    public void writeArraySet(@Nullable ArraySet<? extends Object> val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeBundle(Bundle val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeList(List val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeArray(Object[] val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeSparseArray(SparseArray<Object> val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeSparseBooleanArray(SparseBooleanArray val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeSparseIntArray(SparseIntArray val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeBooleanArray(boolean[] val) {
        throw new RuntimeException("Stub!");
    }

    public final boolean[] createBooleanArray() {
        throw new RuntimeException("Stub!");
    }

    public final void readBooleanArray(boolean[] val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeCharArray(char[] val) {
        throw new RuntimeException("Stub!");
    }

    public final char[] createCharArray() {
        throw new RuntimeException("Stub!");
    }

    public final void readCharArray(char[] val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeIntArray(int[] val) {
        throw new RuntimeException("Stub!");
    }

    public final int[] createIntArray() {
        throw new RuntimeException("Stub!");
    }

    public final void readIntArray(int[] val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeLongArray(long[] val) {
        throw new RuntimeException("Stub!");
    }

    public final long[] createLongArray() {
        throw new RuntimeException("Stub!");
    }

    public final void readLongArray(long[] val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeFloatArray(float[] val) {
        throw new RuntimeException("Stub!");
    }

    public final float[] createFloatArray() {
        throw new RuntimeException("Stub!");
    }

    public final void readFloatArray(float[] val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeDoubleArray(double[] val) {
        throw new RuntimeException("Stub!");
    }

    public final double[] createDoubleArray() {
        throw new RuntimeException("Stub!");
    }

    public final void readDoubleArray(double[] val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeStringArray(String[] val) {
        throw new RuntimeException("Stub!");
    }

    public final String[] createStringArray() {
        throw new RuntimeException("Stub!");
    }

    public final void readStringArray(String[] val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeBinderArray(IBinder[] val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeCharSequenceArray(CharSequence[] val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeCharSequenceList(ArrayList<CharSequence> val) {
        throw new RuntimeException("Stub!");
    }

    public final IBinder[] createBinderArray() {
        throw new RuntimeException("Stub!");
    }

    public final void readBinderArray(IBinder[] val) {
        throw new RuntimeException("Stub!");
    }

    public final <T extends Parcelable> void writeTypedList(List<T> val) {
        throw new RuntimeException("Stub!");
    }

    public <T extends Parcelable> void writeTypedList(List<T> val, int parcelableFlags) {
        throw new RuntimeException("Stub!");
    }

    public final void writeStringList(List<String> val) {
        throw new RuntimeException("Stub!");
    }

    public final void writeBinderList(List<IBinder> val) {
        throw new RuntimeException("Stub!");
    }

    public final <T extends Parcelable> void writeParcelableList(List<T> val, int flags) {
        throw new RuntimeException("Stub!");
    }

    public final <T extends Parcelable> void writeTypedArray(T[] val, int parcelableFlags) {
        throw new RuntimeException("Stub!");
    }

    public final <T extends Parcelable> void writeTypedObject(T val, int parcelableFlags) {
        throw new RuntimeException("Stub!");
    }

    public final void writeValue(Object v) {
        throw new RuntimeException("Stub!");
    }

    public final void writeParcelable(Parcelable p, int parcelableFlags) {
        throw new RuntimeException("Stub!");
    }

    public final void writeParcelableCreator(Parcelable p) {
        throw new RuntimeException("Stub!");
    }

    public final void writeSerializable(Serializable s) {
        throw new RuntimeException("Stub!");
    }

    public static void setStackTraceParceling(boolean enabled) {
        throw new RuntimeException("Stub!");
    }

    public final void writeException(Exception e) {
        throw new RuntimeException("Stub!");
    }

    public final void writeNoException() {
        throw new RuntimeException("Stub!");
    }

    public final void readException() {
        throw new RuntimeException("Stub!");
    }

    public final int readExceptionCode() {
        throw new RuntimeException("Stub!");
    }

    public final void readException(int code, String msg) {
        throw new RuntimeException("Stub!");
    }

    private Exception createException(int code, String msg) {
        throw new RuntimeException("Stub!");
    }

    public final int readInt() {
        throw new RuntimeException("Stub!");
    }

    public final long readLong() {
        throw new RuntimeException("Stub!");
    }

    public final float readFloat() {
        throw new RuntimeException("Stub!");
    }

    public final double readDouble() {
        throw new RuntimeException("Stub!");
    }

    public final String readString() {
        throw new RuntimeException("Stub!");
    }

    public String readStringNoHelper() {
        throw new RuntimeException("Stub!");
    }

    public final boolean readBoolean() {
        throw new RuntimeException("Stub!");
    }

    public final CharSequence readCharSequence() {
        throw new RuntimeException("Stub!");
    }

    public final IBinder readStrongBinder() {
        throw new RuntimeException("Stub!");
    }

    public final ParcelFileDescriptor readFileDescriptor() {
        throw new RuntimeException("Stub!");
    }

    public final FileDescriptor readRawFileDescriptor() {
        throw new RuntimeException("Stub!");
    }

    public final FileDescriptor[] createRawFileDescriptorArray() {
        throw new RuntimeException("Stub!");
    }

    public final void readRawFileDescriptorArray(FileDescriptor[] val) {
        throw new RuntimeException("Stub!");
    }

    public final byte readByte() {
        throw new RuntimeException("Stub!");
    }

    public final void readMap(Map outVal, ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public final void readList(List outVal, ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public final HashMap readHashMap(ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public final Bundle readBundle() {
        throw new RuntimeException("Stub!");
    }

    public final Bundle readBundle(ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public final byte[] createByteArray() {
        throw new RuntimeException("Stub!");
    }

    public final void readByteArray(byte[] val) {
        throw new RuntimeException("Stub!");
    }

    public final byte[] readBlob() {
        throw new RuntimeException("Stub!");
    }

    public final String[] readStringArray() {
        throw new RuntimeException("Stub!");
    }

    public final CharSequence[] readCharSequenceArray() {
        throw new RuntimeException("Stub!");
    }

    public final ArrayList<CharSequence> readCharSequenceList() {
        throw new RuntimeException("Stub!");
    }

    public final ArrayList readArrayList(ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public final Object[] readArray(ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public final SparseArray readSparseArray(ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public final SparseBooleanArray readSparseBooleanArray() {
        throw new RuntimeException("Stub!");
    }

    public final SparseIntArray readSparseIntArray() {
        throw new RuntimeException("Stub!");
    }

    public final <T> ArrayList<T> createTypedArrayList(Parcelable.Creator<T> c) {
        throw new RuntimeException("Stub!");
    }

    public final <T> void readTypedList(List<T> list, Parcelable.Creator<T> c) {
        throw new RuntimeException("Stub!");
    }

    public final ArrayList<String> createStringArrayList() {
        throw new RuntimeException("Stub!");
    }

    public final ArrayList<IBinder> createBinderArrayList() {
        throw new RuntimeException("Stub!");
    }

    public final void readStringList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public final void readBinderList(List<IBinder> list) {
        throw new RuntimeException("Stub!");
    }

    public final <T extends Parcelable> List<T> readParcelableList(List<T> list, ClassLoader cl) {
        throw new RuntimeException("Stub!");
    }

    public final <T> T[] createTypedArray(Parcelable.Creator<T> c) {
        throw new RuntimeException("Stub!");
    }

    public final <T> void readTypedArray(T[] val, Parcelable.Creator<T> c) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public final <T> T[] readTypedArray(Parcelable.Creator<T> c) {
        throw new RuntimeException("Stub!");
    }

    public final <T> T readTypedObject(Parcelable.Creator<T> c) {
        throw new RuntimeException("Stub!");
    }

    public final <T extends Parcelable> void writeParcelableArray(T[] value, int parcelableFlags) {
        throw new RuntimeException("Stub!");
    }

    public final Object readValue(ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public final <T extends Parcelable> T readParcelable(ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public final <T extends Parcelable> T readCreator(Parcelable.Creator<?> creator, ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public final Parcelable.Creator<?> readParcelableCreator(ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public final Parcelable[] readParcelableArray(ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public final <T extends Parcelable> T[] readParcelableArray(ClassLoader loader, Class<T> clazz) {
        throw new RuntimeException("Stub!");
    }

    public final Serializable readSerializable() {
        throw new RuntimeException("Stub!");
    }

    private final Serializable readSerializable(final ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    static protected final Parcel obtain(int obj) {
        throw new RuntimeException("Stub!");
    }

    static protected final Parcel obtain(long obj) {
        throw new RuntimeException("Stub!");
    }

    private Parcel(long nativePtr) {
        throw new RuntimeException("Stub!");
    }

    private void init(long nativePtr) {
        throw new RuntimeException("Stub!");
    }

    private void freeBuffer() {
        throw new RuntimeException("Stub!");
    }

    private void destroy() {
        throw new RuntimeException("Stub!");
    }

    @Override
    protected void finalize() throws Throwable {
        throw new RuntimeException("Stub!");
    }

    public void readArrayMap(ArrayMap outVal, ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    public @Nullable ArraySet<? extends Object> readArraySet(ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    private void readListInternal(List outVal, int N, ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    private void readArrayInternal(Object[] outVal, int N, ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    private void readSparseArrayInternal(SparseArray outVal, int N, ClassLoader loader) {
        throw new RuntimeException("Stub!");
    }

    private void readSparseBooleanArrayInternal(SparseBooleanArray outVal, int N) {
        throw new RuntimeException("Stub!");
    }

    private void readSparseIntArrayInternal(SparseIntArray outVal, int N) {
        throw new RuntimeException("Stub!");
    }

    public long getBlobAshmemSize() {
        throw new RuntimeException("Stub!");
    }
}
