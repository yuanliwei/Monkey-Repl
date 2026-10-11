package org.json;

public class JSONTokener {
    public JSONTokener(String in) {
        throw new RuntimeException("Stub!");
    }

    public Object nextValue() throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public String nextString(char quote) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONException syntaxError(String message) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public boolean more() {
        throw new RuntimeException("Stub!");
    }

    public char next() {
        throw new RuntimeException("Stub!");
    }

    public char next(char c) throws JSONException {
        char result = next();
        return result;
    }

    public char nextClean() throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public String next(int length) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public String nextTo(String excluded) {
        throw new RuntimeException("Stub!");
    }

    public String nextTo(char excluded) {
        throw new RuntimeException("Stub!");
    }

    public void skipPast(String thru) {
        throw new RuntimeException("Stub!");
    }

    public char skipTo(char to) {
        throw new RuntimeException("Stub!");
    }

    public void back() {
        throw new RuntimeException("Stub!");
    }

    public static int dehexchar(char hex) {
        throw new RuntimeException("Stub!");
    }
}
