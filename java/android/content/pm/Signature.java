
package android.content.pm;

import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;

import android.annotation.UnsupportedAppUsage;
import android.os.Parcel;
import android.os.Parcelable;

public class Signature implements Parcelable {
    public static final Parcelable.Creator<Signature> CREATOR = new Parcelable.Creator<Signature>() {
        public Signature createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public Signature[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    public Signature(byte[] signature) {
        throw new RuntimeException("Stub!");
    }

    public Signature(Certificate[] certificateChain) throws CertificateEncodingException {
        throw new RuntimeException("Stub!");
    }

    public Signature(String text) {
        throw new RuntimeException("Stub!");
    }

    public char[] toChars() {
        throw new RuntimeException("Stub!");
    }

    public char[] toChars(char[] existingArray, int[] outLen) {
        throw new RuntimeException("Stub!");
    }

    public String toCharsString() {
        throw new RuntimeException("Stub!");
    }

    public byte[] toByteArray() {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public PublicKey getPublicKey() throws CertificateException {
        throw new RuntimeException("Stub!");
    }

    public Signature[] getChainSignatures() throws CertificateEncodingException {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

}
