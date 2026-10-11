
package android.os;

public class PatternMatcher implements Parcelable {
    public static final int PATTERN_LITERAL = 0;

    public static final int PATTERN_PREFIX = 1;

    public static final int PATTERN_SIMPLE_GLOB = 2;

    public static final int PATTERN_ADVANCED_GLOB = 3;

    public PatternMatcher(String pattern, int type) {
        throw new RuntimeException("Stub!");
    }

    public final String getPath() {
        throw new RuntimeException("Stub!");
    }

    public final int getType() {
        throw new RuntimeException("Stub!");
    }

    public boolean match(String str) {
        throw new RuntimeException("Stub!");
    }

    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<PatternMatcher> CREATOR = new Parcelable.Creator<PatternMatcher>() {
        public PatternMatcher createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        public PatternMatcher[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };

    static boolean matchPattern(String match, String pattern, int[] parsedPattern, int type) {
        throw new RuntimeException("Stub!");
    }

    static boolean matchGlobPattern(String pattern, String match) {
        throw new RuntimeException("Stub!");
    }

    synchronized static int[] parseAndVerifyAdvancedPattern(String pattern) {
        throw new RuntimeException("Stub!");
    }

    static boolean matchAdvancedPattern(int[] parsedPattern, String match) {
        throw new RuntimeException("Stub!");
    }
}