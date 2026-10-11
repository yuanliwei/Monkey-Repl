package org.json;

public class JSONStringer {
    final StringBuilder out = new StringBuilder();

    enum Scope {
        EMPTY_ARRAY, NONEMPTY_ARRAY, EMPTY_OBJECT, DANGLING_KEY, NONEMPTY_OBJECT, NULL,
    }

    public JSONStringer() {
        throw new RuntimeException("Stub!");
    }

    JSONStringer(int indentSpaces) {
        throw new RuntimeException("Stub!");
    }

    public JSONStringer array() throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONStringer endArray() throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONStringer object() throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONStringer endObject() throws JSONException {
        throw new RuntimeException("Stub!");
    }

    JSONStringer open(Scope empty, String openBracket) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    JSONStringer close(Scope empty, Scope nonempty, String closeBracket) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONStringer value(Object value) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONStringer value(boolean value) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONStringer value(double value) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONStringer value(long value) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONStringer key(String name) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }
}
