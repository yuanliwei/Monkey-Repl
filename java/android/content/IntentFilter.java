
package android.content;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import android.annotation.IntDef;
import android.annotation.SystemApi;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AndroidException;

public class IntentFilter implements Parcelable {

    public static final int SYSTEM_HIGH_PRIORITY = 1000;

    public static final int SYSTEM_LOW_PRIORITY = -1000;

    public static final int MATCH_CATEGORY_MASK = 0xfff0000;

    public static final int MATCH_ADJUSTMENT_MASK = 0x000ffff;

    public static final int MATCH_ADJUSTMENT_NORMAL = 0x8000;

    public static final int MATCH_CATEGORY_EMPTY = 0x0100000;
    public static final int MATCH_CATEGORY_SCHEME = 0x0200000;
    public static final int MATCH_CATEGORY_HOST = 0x0300000;
    public static final int MATCH_CATEGORY_PORT = 0x0400000;
    public static final int MATCH_CATEGORY_PATH = 0x0500000;
    public static final int MATCH_CATEGORY_SCHEME_SPECIFIC_PART = 0x0580000;
    public static final int MATCH_CATEGORY_TYPE = 0x0600000;

    public static final int NO_MATCH_TYPE = -1;
    public static final int NO_MATCH_DATA = -2;
    public static final int NO_MATCH_ACTION = -3;
    public static final int NO_MATCH_CATEGORY = -4;

    public static final String SCHEME_HTTP = "http";
    public static final String SCHEME_HTTPS = "https";

    public static final int VISIBILITY_NONE = 0;
    public static final int VISIBILITY_EXPLICIT = 1;
    public static final int VISIBILITY_IMPLICIT = 2;

    @IntDef(prefix = { "VISIBILITY_" }, value = { VISIBILITY_NONE, VISIBILITY_EXPLICIT, VISIBILITY_IMPLICIT, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface InstantAppVisibility {
    }

    public static class MalformedMimeTypeException extends AndroidException {

        private static final long serialVersionUID = 8037358025590894859L;

        public MalformedMimeTypeException() {
            throw new RuntimeException("Stub!");
        }

        public MalformedMimeTypeException(String name) {
            throw new RuntimeException("Stub!");
        }
    }

    public static IntentFilter create(String action, String dataType) {
        throw new RuntimeException("Stub!");
    }

    public IntentFilter() {
        throw new RuntimeException("Stub!");
    }

    public IntentFilter(String action) {
        throw new RuntimeException("Stub!");
    }

    public IntentFilter(String action, String dataType) throws MalformedMimeTypeException {

    }

    public IntentFilter(IntentFilter o) {
        throw new RuntimeException("Stub!");
    }

    public final void setPriority(int priority) {
        throw new RuntimeException("Stub!");
    }

    public final int getPriority() {
        throw new RuntimeException("Stub!");
    }

    @SystemApi
    public final void setOrder(int order) {
        throw new RuntimeException("Stub!");
    }

    @SystemApi
    public final int getOrder() {
        throw new RuntimeException("Stub!");
    }

    public final void setAutoVerify(boolean autoVerify) {
        throw new RuntimeException("Stub!");
    }

    public final boolean getAutoVerify() {
        throw new RuntimeException("Stub!");
    }

    public final boolean handleAllWebDataURI() {
        throw new RuntimeException("Stub!");
    }

    public final boolean handlesWebUris(boolean onlyWebSchemes) {
        throw new RuntimeException("Stub!");
    }

    public final boolean needsVerification() {
        throw new RuntimeException("Stub!");
    }

    public final boolean isVerified() {
        throw new RuntimeException("Stub!");
    }

    public void setVerified(boolean verified) {
        throw new RuntimeException("Stub!");
    }

    public void setVisibilityToInstantApp(@InstantAppVisibility int visibility) {
        throw new RuntimeException("Stub!");
    }

    public @InstantAppVisibility int getVisibilityToInstantApp() {
        throw new RuntimeException("Stub!");
    }

    public boolean isVisibleToInstantApp() {
        throw new RuntimeException("Stub!");
    }

    public boolean isExplicitlyVisibleToInstantApp() {
        throw new RuntimeException("Stub!");
    }

    public boolean isImplicitlyVisibleToInstantApp() {
        throw new RuntimeException("Stub!");
    }

    public final void addAction(String action) {
        throw new RuntimeException("Stub!");
    }

    public final int countActions() {
        throw new RuntimeException("Stub!");
    }

    public final String getAction(int index) {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasAction(String action) {
        throw new RuntimeException("Stub!");
    }

    public final boolean matchAction(String action) {
        throw new RuntimeException("Stub!");
    }

    public final Iterator<String> actionsIterator() {
        throw new RuntimeException("Stub!");
    }

    public final void addDataType(String type) throws MalformedMimeTypeException {
        throw new MalformedMimeTypeException(type);
    }

    public final boolean hasDataType(String type) {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasExactDataType(String type) {
        throw new RuntimeException("Stub!");
    }

    public final int countDataTypes() {
        throw new RuntimeException("Stub!");
    }

    public final String getDataType(int index) {
        throw new RuntimeException("Stub!");
    }

    public final Iterator<String> typesIterator() {
        throw new RuntimeException("Stub!");
    }

    public final void addDataScheme(String scheme) {
        throw new RuntimeException("Stub!");
    }

    public final int countDataSchemes() {
        throw new RuntimeException("Stub!");
    }

    public final String getDataScheme(int index) {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasDataScheme(String scheme) {
        throw new RuntimeException("Stub!");
    }

    public final Iterator<String> schemesIterator() {
        throw new RuntimeException("Stub!");
    }

    public final static class AuthorityEntry {

        public AuthorityEntry(String host, String port) {
            throw new RuntimeException("Stub!");
        }

        AuthorityEntry(Parcel src) {
            throw new RuntimeException("Stub!");
        }

        void writeToParcel(Parcel dest) {
            throw new RuntimeException("Stub!");
        }

        public String getHost() {
            throw new RuntimeException("Stub!");
        }

        public int getPort() {
            throw new RuntimeException("Stub!");
        }

        public boolean match(AuthorityEntry other) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public boolean equals(Object obj) {
            throw new RuntimeException("Stub!");
        }

        public int match(Uri data) {
            throw new RuntimeException("Stub!");
        }
    }

    public final void addDataSchemeSpecificPart(String ssp, int type) {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasDataSchemeSpecificPart(String data) {
        throw new RuntimeException("Stub!");
    }

    public final void addDataAuthority(String host, String port) {
        throw new RuntimeException("Stub!");
    }

    public final void addDataAuthority(AuthorityEntry ent) {
        throw new RuntimeException("Stub!");
    }

    public final int countDataAuthorities() {
        throw new RuntimeException("Stub!");
    }

    public final AuthorityEntry getDataAuthority(int index) {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasDataAuthority(Uri data) {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasDataAuthority(AuthorityEntry auth) {
        throw new RuntimeException("Stub!");
    }

    public final Iterator<AuthorityEntry> authoritiesIterator() {
        throw new RuntimeException("Stub!");
    }

    public final void addDataPath(String path, int type) {
        throw new RuntimeException("Stub!");
    }

    public final int countDataPaths() {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasDataPath(String data) {
        throw new RuntimeException("Stub!");
    }

    public final int matchDataAuthority(Uri data) {
        throw new RuntimeException("Stub!");
    }

    public final int matchData(String type, String scheme, Uri data) {
        throw new RuntimeException("Stub!");
    }

    public final void addCategory(String category) {
        throw new RuntimeException("Stub!");
    }

    public final int countCategories() {
        throw new RuntimeException("Stub!");
    }

    public final String getCategory(int index) {
        throw new RuntimeException("Stub!");
    }

    public final boolean hasCategory(String category) {
        throw new RuntimeException("Stub!");
    }

    public final Iterator<String> categoriesIterator() {
        throw new RuntimeException("Stub!");
    }

    public final String matchCategories(Set<String> categories) {
        throw new RuntimeException("Stub!");
    }

    public final int match(String action, String type, String scheme, Uri data, Set<String> categories, String logTag) {
        throw new RuntimeException("Stub!");
    }

    public boolean debugCheck() {
        throw new RuntimeException("Stub!");
    }

    public IntentFilter(Parcel source) {
        throw new RuntimeException("Stub!");
    }

    public ArrayList<String> getHostsList() {
        throw new RuntimeException("Stub!");
    }

    public String[] getHosts() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }
}
