package android.content;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.annotation.IntDef;

public abstract class ContentResolver {
    @Deprecated
    public static final String SYNC_EXTRAS_ACCOUNT = "account";
    public static final String SYNC_EXTRAS_EXPEDITED = "expedited";
    public static final String SYNC_EXTRAS_REQUIRE_CHARGING = "require_charging";
    @Deprecated
    public static final String SYNC_EXTRAS_FORCE = "force";
    public static final String SYNC_EXTRAS_IGNORE_SETTINGS = "ignore_settings";
    public static final String SYNC_EXTRAS_IGNORE_BACKOFF = "ignore_backoff";
    public static final String SYNC_EXTRAS_DO_NOT_RETRY = "do_not_retry";
    public static final String SYNC_EXTRAS_MANUAL = "force";
    public static final String SYNC_EXTRAS_UPLOAD = "upload";
    public static final String SYNC_EXTRAS_OVERRIDE_TOO_MANY_DELETIONS = "deletions_override";
    public static final String SYNC_EXTRAS_DISCARD_LOCAL_DELETIONS = "discard_deletions";
    public static final String SYNC_EXTRAS_EXPECTED_UPLOAD = "expected_upload";
    public static final String SYNC_EXTRAS_EXPECTED_DOWNLOAD = "expected_download";
    public static final String SYNC_EXTRAS_PRIORITY = "sync_priority";
    public static final String SYNC_EXTRAS_DISALLOW_METERED = "allow_metered";
    public static final String SYNC_VIRTUAL_EXTRAS_EXEMPTION_FLAG = "v_exemption";
    public static final String SYNC_EXTRAS_INITIALIZE = "initialize";
    public static final Intent ACTION_SYNC_CONN_STATUS_CHANGED = new Intent(
            "com.android.sync.SYNC_CONN_STATUS_CHANGED");
    public static final String SCHEME_CONTENT = "content";
    public static final String SCHEME_ANDROID_RESOURCE = "android.resource";
    public static final String SCHEME_FILE = "file";
    public static final String EXTRA_SIZE = "android.content.extra.SIZE";
    public static final String EXTRA_REFRESH_SUPPORTED = "android.content.extra.REFRESH_SUPPORTED";
    public static final String QUERY_ARG_SQL_SELECTION = "android:query-arg-sql-selection";
    public static final String QUERY_ARG_SQL_SELECTION_ARGS = "android:query-arg-sql-selection-args";
    public static final String QUERY_ARG_SQL_SORT_ORDER = "android:query-arg-sql-sort-order";
    public static final String QUERY_ARG_SQL_GROUP_BY = "android:query-arg-sql-group-by";
    public static final String QUERY_ARG_SQL_HAVING = "android:query-arg-sql-having";
    public static final String QUERY_ARG_SQL_LIMIT = "android:query-arg-sql-limit";
    public static final String QUERY_ARG_SORT_COLUMNS = "android:query-arg-sort-columns";
    public static final String QUERY_ARG_SORT_DIRECTION = "android:query-arg-sort-direction";
    public static final String QUERY_ARG_SORT_COLLATION = "android:query-arg-sort-collation";
    public static final String EXTRA_HONORED_ARGS = "android.content.extra.HONORED_ARGS";

    @IntDef(flag = false, prefix = { "QUERY_SORT_DIRECTION_" }, value = { QUERY_SORT_DIRECTION_ASCENDING,
            QUERY_SORT_DIRECTION_DESCENDING })
    @Retention(RetentionPolicy.SOURCE)
    public @interface SortDirection {
    }

    public static final int QUERY_SORT_DIRECTION_ASCENDING = 0;
    public static final int QUERY_SORT_DIRECTION_DESCENDING = 1;

    @IntDef(flag = false, value = { java.text.Collator.PRIMARY, java.text.Collator.SECONDARY,
            java.text.Collator.TERTIARY, java.text.Collator.IDENTICAL })
    @Retention(RetentionPolicy.SOURCE)
    public @interface QueryCollator {
    }

    public static final String QUERY_ARG_OFFSET = "android:query-arg-offset";
    public static final String QUERY_ARG_LIMIT = "android:query-arg-limit";
    public static final String EXTRA_TOTAL_COUNT = "android.content.extra.TOTAL_COUNT";
    public static final String CURSOR_ITEM_BASE_TYPE = "vnd.android.cursor.item";
    public static final String CURSOR_DIR_BASE_TYPE = "vnd.android.cursor.dir";
    public static final int SYNC_ERROR_SYNC_ALREADY_IN_PROGRESS = 1;
    public static final int SYNC_ERROR_AUTHENTICATION = 2;
    public static final int SYNC_ERROR_IO = 3;
    public static final int SYNC_ERROR_PARSE = 4;
    public static final int SYNC_ERROR_CONFLICT = 5;
    public static final int SYNC_ERROR_TOO_MANY_DELETIONS = 6;
    public static final int SYNC_ERROR_TOO_MANY_RETRIES = 7;
    public static final int SYNC_ERROR_INTERNAL = 8;

    public static String syncErrorToString(int error) {
        throw new RuntimeException("Stub!");
    }

    public static int syncErrorStringToInt(String error) {
        throw new RuntimeException("Stub!");
    }

    public static final int SYNC_OBSERVER_TYPE_SETTINGS = 1 << 0;
    public static final int SYNC_OBSERVER_TYPE_PENDING = 1 << 1;
    public static final int SYNC_OBSERVER_TYPE_ACTIVE = 1 << 2;
    public static final int SYNC_OBSERVER_TYPE_STATUS = 1 << 3;
    public static final int SYNC_OBSERVER_TYPE_ALL = 0x7fffffff;

    @IntDef(flag = true, prefix = { "NOTIFY_" }, value = { NOTIFY_SYNC_TO_NETWORK, NOTIFY_SKIP_NOTIFY_FOR_DESCENDANTS })
    @Retention(RetentionPolicy.SOURCE)
    public @interface NotifyFlags {
    }

    public static final int NOTIFY_SYNC_TO_NETWORK = 1 << 0;
    public static final int NOTIFY_SKIP_NOTIFY_FOR_DESCENDANTS = 1 << 1;
    public static final int SYNC_EXEMPTION_NONE = 0;
    public static final int SYNC_EXEMPTION_PROMOTE_BUCKET = 1;
    public static final int SYNC_EXEMPTION_PROMOTE_BUCKET_WITH_TEMP = 2;

    @IntDef(flag = false, prefix = { "SYNC_EXEMPTION_" }, value = { SYNC_EXEMPTION_NONE, SYNC_EXEMPTION_PROMOTE_BUCKET,
            SYNC_EXEMPTION_PROMOTE_BUCKET_WITH_TEMP, })
    @Retention(RetentionPolicy.SOURCE)
    public @interface SyncExemption {
    }

    protected abstract IContentProvider acquireProvider(Context c, String name);

    protected IContentProvider acquireExistingProvider(Context c, String name) {
        throw new RuntimeException("Stub!");
    }

    public abstract boolean releaseProvider(IContentProvider icp);

    protected abstract IContentProvider acquireUnstableProvider(Context c, String name);

    public abstract boolean releaseUnstableProvider(IContentProvider icp);

    public abstract void unstableProviderDied(IContentProvider icp);

    public void appNotRespondingViaProvider(IContentProvider icp) {
        throw new RuntimeException("Stub!");
    }

}