package org.json;

import java.util.Collection;

public class JSONArray {
    public JSONArray() {
        throw new RuntimeException("Stub!");
    }

    public JSONArray(Collection copyFrom) {
        throw new RuntimeException("Stub!");
    }

    public JSONArray(JSONTokener readFrom) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONArray(String json) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONArray(Object array) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public int length() {
        throw new RuntimeException("Stub!");
    }

    public JSONArray put(boolean value) {
        throw new RuntimeException("Stub!");
    }

    public JSONArray put(double value) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONArray put(int value) {
        throw new RuntimeException("Stub!");
    }

    public JSONArray put(long value) {
        throw new RuntimeException("Stub!");
    }

    public JSONArray put(Object value) {
        throw new RuntimeException("Stub!");
    }

    public JSONArray put(int index, boolean value) throws JSONException {
        return put(index, (Boolean) value);
    }

    public JSONArray put(int index, double value) throws JSONException {
        return put(index, (Double) value);
    }

    public JSONArray put(int index, int value) throws JSONException {
        return put(index, (Integer) value);
    }

    public JSONArray put(int index, long value) throws JSONException {
        return put(index, (Long) value);
    }

    public JSONArray put(int index, Object value) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public boolean isNull(int index) {
        throw new RuntimeException("Stub!");
    }

    public Object get(int index) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public Object opt(int index) {
        throw new RuntimeException("Stub!");
    }

    public Object remove(int index) {
        throw new RuntimeException("Stub!");
    }

    public boolean getBoolean(int index) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public boolean optBoolean(int index) {
        throw new RuntimeException("Stub!");
    }

    public boolean optBoolean(int index, boolean fallback) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int index) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public double optDouble(int index) {
        throw new RuntimeException("Stub!");
    }

    public double optDouble(int index, double fallback) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int index) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public int optInt(int index) {
        throw new RuntimeException("Stub!");
    }

    public int optInt(int index, int fallback) {
        throw new RuntimeException("Stub!");
    }

    public long getLong(int index) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public long optLong(int index) {
        throw new RuntimeException("Stub!");
    }

    public long optLong(int index, long fallback) {
        throw new RuntimeException("Stub!");
    }

    public String getString(int index) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public String optString(int index) {
        throw new RuntimeException("Stub!");
    }

    public String optString(int index, String fallback) {
        throw new RuntimeException("Stub!");
    }

    public JSONArray getJSONArray(int index) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONArray optJSONArray(int index) {
        throw new RuntimeException("Stub!");
    }

    public JSONObject getJSONObject(int index) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public JSONObject optJSONObject(int index) {
        throw new RuntimeException("Stub!");
    }

    public JSONObject toJSONObject(JSONArray names) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    public String join(String separator) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public String toString(int indentSpaces) throws JSONException {
        throw new RuntimeException("Stub!");
    }

    @Override
    public boolean equals(Object o) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Stub!");
    }
}
