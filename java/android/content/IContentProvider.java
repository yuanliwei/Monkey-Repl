
package android.content;

import android.annotation.UnsupportedAppUsage;
import android.os.IBinder;
import android.os.IInterface;

public interface IContentProvider extends IInterface {

    @UnsupportedAppUsage
    static final int QUERY_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION;
    static final int GET_TYPE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 1;
    static final int INSERT_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 2;
    static final int DELETE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 3;
    static final int UPDATE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 9;
    static final int BULK_INSERT_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 12;
    static final int OPEN_FILE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 13;
    static final int OPEN_ASSET_FILE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 14;
    static final int APPLY_BATCH_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 19;
    static final int CALL_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 20;
    static final int GET_STREAM_TYPES_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 21;
    static final int OPEN_TYPED_ASSET_FILE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 22;
    static final int CREATE_CANCELATION_SIGNAL_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 23;
    static final int CANONICALIZE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 24;
    static final int UNCANONICALIZE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 25;
    static final int REFRESH_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 26;
}
