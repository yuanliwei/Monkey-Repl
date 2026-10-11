package android.util.proto;

import java.io.FileDescriptor;
import java.io.OutputStream;

import android.annotation.TestApi;

@TestApi
public final class ProtoOutputStream {
    public ProtoOutputStream() {
        throw new RuntimeException("Stub!");
    }

    public ProtoOutputStream(int chunkSize) {
        throw new RuntimeException("Stub!");
    }

    public ProtoOutputStream(OutputStream stream) {
        throw new RuntimeException("Stub!");
    }

    public ProtoOutputStream(FileDescriptor fd) {
        throw new RuntimeException("Stub!");
    }

    public void write(long fieldId, double val) {
        throw new RuntimeException("Stub!");
    }

    public void write(long fieldId, float val) {
        throw new RuntimeException("Stub!");
    }

    public void write(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    public void write(long fieldId, long val) {
        throw new RuntimeException("Stub!");
    }

    public void write(long fieldId, boolean val) {
        throw new RuntimeException("Stub!");
    }

    public void write(long fieldId, String val) {
        throw new RuntimeException("Stub!");
    }

    public void write(long fieldId, byte[] val) {
        throw new RuntimeException("Stub!");
    }

    public long start(long fieldId) {
        throw new RuntimeException("Stub!");
    }

    public void end(long token) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeDouble(long fieldId, double val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedDouble(long fieldId, double val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedDouble(long fieldId, double[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeFloat(long fieldId, float val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedFloat(long fieldId, float val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedFloat(long fieldId, float[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeInt32(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedInt32(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedInt32(long fieldId, int[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeInt64(long fieldId, long val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedInt64(long fieldId, long val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedInt64(long fieldId, long[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeUInt32(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedUInt32(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedUInt32(long fieldId, int[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeUInt64(long fieldId, long val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedUInt64(long fieldId, long val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedUInt64(long fieldId, long[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeSInt32(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedSInt32(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedSInt32(long fieldId, int[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeSInt64(long fieldId, long val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedSInt64(long fieldId, long val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedSInt64(long fieldId, long[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeFixed32(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedFixed32(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedFixed32(long fieldId, int[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeFixed64(long fieldId, long val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedFixed64(long fieldId, long val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedFixed64(long fieldId, long[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeSFixed32(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedSFixed32(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedSFixed32(long fieldId, int[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeSFixed64(long fieldId, long val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedSFixed64(long fieldId, long val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedSFixed64(long fieldId, long[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeBool(long fieldId, boolean val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedBool(long fieldId, boolean val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedBool(long fieldId, boolean[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeString(long fieldId, String val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedString(long fieldId, String val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeBytes(long fieldId, byte[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedBytes(long fieldId, byte[] val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeEnum(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedEnum(long fieldId, int val) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writePackedEnum(long fieldId, int[] val) {
        throw new RuntimeException("Stub!");
    }

    public static long makeToken(int tagSize, boolean repeated, int depth, int objectId, int sizePos) {
        throw new RuntimeException("Stub!");
    }

    public static int getTagSizeFromToken(long token) {
        throw new RuntimeException("Stub!");
    }

    public static boolean getRepeatedFromToken(long token) {
        throw new RuntimeException("Stub!");
    }

    public static int getDepthFromToken(long token) {
        throw new RuntimeException("Stub!");
    }

    public static int getObjectIdFromToken(long token) {
        throw new RuntimeException("Stub!");
    }

    public static int getSizePosFromToken(long token) {
        throw new RuntimeException("Stub!");
    }

    public static int convertObjectIdToOrdinal(int objectId) {
        throw new RuntimeException("Stub!");
    }

    public static String token2String(long token) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public long startObject(long fieldId) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void endObject(long token) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public long startRepeatedObject(long fieldId) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void endRepeatedObject(long token) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeObject(long fieldId, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    void writeObjectImpl(int id, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void writeRepeatedObject(long fieldId, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    void writeRepeatedObjectImpl(int id, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public static long makeFieldId(int id, long fieldFlags) {
        throw new RuntimeException("Stub!");
    }

    public static int checkFieldId(long fieldId, long expectedFlags) {
        throw new RuntimeException("Stub!");
    }

    public void writeTag(int id, int wireType) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getBytes() {
        throw new RuntimeException("Stub!");
    }

    public void flush() {
        throw new RuntimeException("Stub!");
    }

    public void dump(String tag) {
        throw new RuntimeException("Stub!");
    }
}