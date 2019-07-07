
package android.net;

import java.io.File;
import java.util.AbstractList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class Uri implements Parcelable, Comparable<Uri> {

    public static final Uri EMPTY = new HierarchicalUri(null, Part.NULL, PathPart.EMPTY, Part.NULL, Part.NULL);

    private Uri() {
        throw new RuntimeException("Stub!");
    }

    public abstract boolean isHierarchical();

    public boolean isOpaque() {
        throw new RuntimeException("Stub!");
    }

    public abstract boolean isRelative();

    public boolean isAbsolute() {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    public abstract String getScheme();

    public abstract String getSchemeSpecificPart();

    public abstract String getEncodedSchemeSpecificPart();

    @Nullable
    public abstract String getAuthority();

    @Nullable
    public abstract String getEncodedAuthority();

    @Nullable
    public abstract String getUserInfo();

    @Nullable
    public abstract String getEncodedUserInfo();

    @Nullable
    public abstract String getHost();

    public abstract int getPort();

    @Nullable
    public abstract String getPath();

    @Nullable
    public abstract String getEncodedPath();

    @Nullable
    public abstract String getQuery();

    @Nullable
    public abstract String getEncodedQuery();

    @Nullable
    public abstract String getFragment();

    @Nullable
    public abstract String getEncodedFragment();

    public abstract List<String> getPathSegments();

    @Nullable
    public abstract String getLastPathSegment();

    public boolean equals(Object o) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public int compareTo(Uri other) {
        throw new RuntimeException("Stub!");
    }

    public abstract String toString();

    @NonNull
    public String toSafeString() {
        throw new RuntimeException("Stub!");
    }

    public abstract Builder buildUpon();

    public static Uri parse(String uriString) {
        throw new RuntimeException("Stub!");
    }

    public static Uri fromFile(File file) {
        throw new RuntimeException("Stub!");
    }

    public static Uri fromParts(String scheme, String ssp, String fragment) {
        throw new RuntimeException("Stub!");
    }

    static class PathSegments extends AbstractList<String> implements RandomAccess {

        static final PathSegments EMPTY = new PathSegments(null, 0);

        final String[] segments;
        final int size;

        PathSegments(String[] segments, int size) {
            throw new RuntimeException("Stub!");
        }

        public String get(int index) {
            throw new RuntimeException("Stub!");
        }

        public int size() {
            throw new RuntimeException("Stub!");
        }
    }

    static class PathSegmentsBuilder {

        String[] segments;
        int size = 0;

        void add(String segment) {
            throw new RuntimeException("Stub!");
        }

        PathSegments build() {

            try {
                return new PathSegments(segments, size);
            } finally {
                // Makes sure this doesn't get reused.
                segments = null;
            }
        }
    }

    private abstract static class AbstractHierarchicalUri extends Uri {

        public String getLastPathSegment() {
            throw new RuntimeException("Stub!");
        }

        public final String getEncodedUserInfo() {
            throw new RuntimeException("Stub!");
        }

        public String getUserInfo() {
            throw new RuntimeException("Stub!");
        }

        public String getHost() {
            throw new RuntimeException("Stub!");
        }

        public int getPort() {
            throw new RuntimeException("Stub!");
        }
    }

    private static class HierarchicalUri extends AbstractHierarchicalUri {

        private HierarchicalUri(String scheme, Part authority, PathPart path, Part query, Part fragment) {
            throw new RuntimeException("Stub!");
        }

        public void writeToParcel(Parcel parcel, int flags) {
            throw new RuntimeException("Stub!");
        }

        public boolean isHierarchical() {
            throw new RuntimeException("Stub!");
        }

        public boolean isRelative() {
            throw new RuntimeException("Stub!");
        }

        public String getScheme() {
            throw new RuntimeException("Stub!");
        }

        public String getEncodedSchemeSpecificPart() {
            throw new RuntimeException("Stub!");
        }

        public String getSchemeSpecificPart() {
            throw new RuntimeException("Stub!");
        }

        public String getEncodedAuthority() {
            throw new RuntimeException("Stub!");
        }

        public String getEncodedPath() {
            throw new RuntimeException("Stub!");
        }

        public String getPath() {
            throw new RuntimeException("Stub!");
        }

        public String getQuery() {
            throw new RuntimeException("Stub!");
        }

        public String getEncodedQuery() {
            throw new RuntimeException("Stub!");
        }

        public String getFragment() {
            throw new RuntimeException("Stub!");
        }

        public String getEncodedFragment() {
            throw new RuntimeException("Stub!");
        }

        public List<String> getPathSegments() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public String toString() {
            throw new RuntimeException("Stub!");
        }

        public Builder buildUpon() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public String getAuthority() {
            throw new RuntimeException("Stub!");
        }
    }

    public static final class Builder {

        public Builder() {
            throw new RuntimeException("Stub!");
        }

        public Builder scheme(String scheme) {
            throw new RuntimeException("Stub!");
        }

        Builder opaquePart(Part opaquePart) {
            throw new RuntimeException("Stub!");
        }

        public Builder opaquePart(String opaquePart) {
            throw new RuntimeException("Stub!");
        }

        public Builder encodedOpaquePart(String opaquePart) {
            throw new RuntimeException("Stub!");
        }

        Builder authority(Part authority) {
            throw new RuntimeException("Stub!");
        }

        public Builder authority(String authority) {
            throw new RuntimeException("Stub!");
        }

        public Builder encodedAuthority(String authority) {
            throw new RuntimeException("Stub!");
        }

        Builder path(PathPart path) {
            throw new RuntimeException("Stub!");
        }

        public Builder path(String path) {
            throw new RuntimeException("Stub!");
        }

        public Builder encodedPath(String path) {
            throw new RuntimeException("Stub!");
        }

        public Builder appendPath(String newSegment) {
            throw new RuntimeException("Stub!");
        }

        public Builder appendEncodedPath(String newSegment) {
            throw new RuntimeException("Stub!");
        }

        Builder query(Part query) {
            throw new RuntimeException("Stub!");
        }

        public Builder query(String query) {
            throw new RuntimeException("Stub!");
        }

        public Builder encodedQuery(String query) {
            throw new RuntimeException("Stub!");
        }

        Builder fragment(Part fragment) {
            throw new RuntimeException("Stub!");
        }

        public Builder fragment(String fragment) {
            throw new RuntimeException("Stub!");
        }

        public Builder encodedFragment(String fragment) {
            throw new RuntimeException("Stub!");
        }

        public Builder appendQueryParameter(String key, String value) {
            throw new RuntimeException("Stub!");
        }

        public Builder clearQuery() {
            throw new RuntimeException("Stub!");
        }

        public Uri build() {
            throw new RuntimeException("Stub!");
        }

        @Override
        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    public Set<String> getQueryParameterNames() {

        String query = getEncodedQuery();

        Set<String> names = new LinkedHashSet<String>();
        int start = 0;
        do {
            int next = query.indexOf('&', start);
            int end = (next == -1) ? query.length() : next;

            int separator = query.indexOf('=', start);

            String name = query.substring(start, separator);
            names.add(decode(name));

            // Move start to end of name.
            start = end + 1;
        } while (start < query.length());

        return Collections.unmodifiableSet(names);
    }

    public List<String> getQueryParameters(String key) {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    public String getQueryParameter(String key) {
        throw new RuntimeException("Stub!");
    }

    public boolean getBooleanQueryParameter(String key, boolean defaultValue) {
        throw new RuntimeException("Stub!");
    }

    public Uri normalizeScheme() {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<Uri> CREATOR = new Parcelable.Creator<Uri>() {
        public Uri createFromParcel(Parcel in) {
            throw new RuntimeException("Stub!");
        }

        public Uri[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    public static void writeToParcel(Parcel out, Uri uri) {
        throw new RuntimeException("Stub!");
    }

    public static String encode(String s) {
        throw new RuntimeException("Stub!");
    }

    public static String encode(String s, String allow) {

        // Lazily-initialized buffers.
        StringBuilder encoded = null;

        int oldLength = s.length();

        // This loop alternates between copying over allowed characters and
        // encoding in chunks. This results in fewer method calls and
        // allocations than encoding one character at a time.
        int current = 0;
        while (current < oldLength) {
            throw new RuntimeException("Stub!");
        }

        // Encoded could still be null at this point if s is empty.
        return encoded == null ? s : encoded.toString();
    }

    public static String decode(String s) {
        throw new RuntimeException("Stub!");
    }

    static abstract class AbstractPart {

        static class Representation {
            static final int BOTH = 0;
            static final int ENCODED = 1;
            static final int DECODED = 2;
        }

        volatile String encoded;
        volatile String decoded;

        AbstractPart(String encoded, String decoded) {
            throw new RuntimeException("Stub!");
        }

        abstract String getEncoded();

        final String getDecoded() {
            throw new RuntimeException("Stub!");
        }

        final void writeTo(Parcel parcel) {
            throw new RuntimeException("Stub!");
        }
    }

    static class Part extends AbstractPart {

        static final Part NULL = new EmptyPart(null);

        static final Part EMPTY = new EmptyPart("");

        private Part(String encoded, String decoded) {
            super(encoded, decoded);
            throw new RuntimeException("Stub!");
        }

        public Part(String value) {
            super("encoded", "decoded");
            throw new RuntimeException("Stub!");
        }

        boolean isEmpty() {
            throw new RuntimeException("Stub!");
        }

        String getEncoded() {
            throw new RuntimeException("Stub!");
        }

        static Part readFrom(Parcel parcel) {
            throw new RuntimeException("Stub!");
        }

        static Part nonNull(Part part) {
            throw new RuntimeException("Stub!");
        }

        static Part fromEncoded(String encoded) {
            throw new RuntimeException("Stub!");
        }

        static Part fromDecoded(String decoded) {
            throw new RuntimeException("Stub!");
        }

        static Part from(String encoded, String decoded) {
            throw new RuntimeException("Stub!");
        }

        private static class EmptyPart extends Part {
            public EmptyPart(String value) {
                super(null);
                throw new RuntimeException("Stub!");
            }

            @Override
            boolean isEmpty() {
                throw new RuntimeException("Stub!");
            }
        }
    }

    static class PathPart extends AbstractPart {

        static final PathPart NULL = new PathPart(null, null);

        static final PathPart EMPTY = new PathPart("", "");

        private PathPart(String encoded, String decoded) {
            super(encoded, decoded);
            throw new RuntimeException("Stub!");
        }

        String getEncoded() {
            throw new RuntimeException("Stub!");
        }

        PathSegments getPathSegments() {
            throw new RuntimeException("Stub!");
        }

        static PathPart appendEncodedSegment(PathPart oldPart, String newSegment) {
            throw new RuntimeException("Stub!");
        }

        static PathPart appendDecodedSegment(PathPart oldPart, String decoded) {
            throw new RuntimeException("Stub!");
        }

        static PathPart readFrom(Parcel parcel) {
            throw new RuntimeException("Stub!");
        }

        static PathPart fromEncoded(String encoded) {
            throw new RuntimeException("Stub!");
        }

        static PathPart fromDecoded(String decoded) {
            throw new RuntimeException("Stub!");
        }

        static PathPart from(String encoded, String decoded) {
            throw new RuntimeException("Stub!");
        }

        static PathPart makeAbsolute(PathPart oldPart) {
            throw new RuntimeException("Stub!");
        }
    }

    public static Uri withAppendedPath(Uri baseUri, String pathSegment) {
        throw new RuntimeException("Stub!");
    }

    public Uri getCanonicalUri() {
        throw new RuntimeException("Stub!");
    }

    public void checkFileUriExposed(String location) {
        throw new RuntimeException("Stub!");
    }

    public void checkContentUriWithoutPermission(String location, int flags) {
        throw new RuntimeException("Stub!");
    }

    public boolean isPathPrefixMatch(Uri prefix) {
        throw new RuntimeException("Stub!");
    }
}
