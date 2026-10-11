package android.content;

import java.util.ArrayList;
import java.util.List;

import android.annotation.UnsupportedAppUsage;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.proto.ProtoOutputStream;

public class ClipData implements Parcelable {
    public static class Item {
        final CharSequence mText;
        final String mHtmlText;
        final Intent mIntent;
        @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.P, trackingBug = 115609023)
        Uri mUri;

        public Item(Item other) {
            throw new RuntimeException("Stub!");
        }

        public Item(CharSequence text) {
            throw new RuntimeException("Stub!");
        }

        public Item(CharSequence text, String htmlText) {
            throw new RuntimeException("Stub!");
        }

        public Item(Intent intent) {
            throw new RuntimeException("Stub!");
        }

        public Item(Uri uri) {
            throw new RuntimeException("Stub!");
        }

        public Item(CharSequence text, Intent intent, Uri uri) {
            throw new RuntimeException("Stub!");
        }

        public Item(CharSequence text, String htmlText, Intent intent, Uri uri) {
            throw new RuntimeException("Stub!");
        }

        public CharSequence getText() {
            throw new RuntimeException("Stub!");
        }

        public String getHtmlText() {
            throw new RuntimeException("Stub!");
        }

        public Intent getIntent() {
            throw new RuntimeException("Stub!");
        }

        public Uri getUri() {
            throw new RuntimeException("Stub!");
        }

        public CharSequence coerceToText(Context context) {
            throw new RuntimeException("Stub!");
        }

        public CharSequence coerceToStyledText(Context context) {
            throw new RuntimeException("Stub!");
        }

        public String coerceToHtmlText(Context context) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public String toString() {
            throw new RuntimeException("Stub!");
        }

        public void toShortString(StringBuilder b) {
            throw new RuntimeException("Stub!");
        }

        public void toShortSummaryString(StringBuilder b) {
            throw new RuntimeException("Stub!");
        }
    }

    public ClipData(CharSequence label, String[] mimeTypes, Item item) {
        throw new RuntimeException("Stub!");
    }

    public ClipData(ClipDescription description, Item item) {
        throw new RuntimeException("Stub!");
    }

    public ClipData(ClipDescription description, ArrayList<Item> items) {
        throw new RuntimeException("Stub!");
    }

    public ClipData(ClipData other) {
        throw new RuntimeException("Stub!");
    }

    static public ClipData newPlainText(CharSequence label, CharSequence text) {
        throw new RuntimeException("Stub!");
    }

    static public ClipData newHtmlText(CharSequence label, CharSequence text, String htmlText) {
        throw new RuntimeException("Stub!");
    }

    static public ClipData newIntent(CharSequence label, Intent intent) {
        throw new RuntimeException("Stub!");
    }

    static public ClipData newUri(ContentResolver resolver, CharSequence label, Uri uri) {
        throw new RuntimeException("Stub!");
    }

    static public ClipData newRawUri(CharSequence label, Uri uri) {
        throw new RuntimeException("Stub!");
    }

    public ClipDescription getDescription() {
        throw new RuntimeException("Stub!");
    }

    public void addItem(Item item) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void addItem(Item item, ContentResolver resolver) {
        throw new RuntimeException("Stub!");
    }

    public void addItem(ContentResolver resolver, Item item) {
        throw new RuntimeException("Stub!");
    }

    @UnsupportedAppUsage
    public Bitmap getIcon() {
        throw new RuntimeException("Stub!");
    }

    public int getItemCount() {
        throw new RuntimeException("Stub!");
    }

    public Item getItemAt(int index) {
        throw new RuntimeException("Stub!");
    }

    public void setItemAt(int index, Item item) {
        throw new RuntimeException("Stub!");
    }

    public void prepareToLeaveProcess(boolean leavingPackage) {
        throw new RuntimeException("Stub!");
    }

    public void prepareToLeaveProcess(boolean leavingPackage, int intentFlags) {
        throw new RuntimeException("Stub!");
    }

    public void prepareToEnterProcess() {
        throw new RuntimeException("Stub!");
    }

    public void fixUris(int contentUserHint) {
        throw new RuntimeException("Stub!");
    }

    public void fixUrisLight(int contentUserHint) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public void toShortString(StringBuilder b) {
        throw new RuntimeException("Stub!");
    }

    public void toShortStringShortItems(StringBuilder b, boolean first) {
        throw new RuntimeException("Stub!");
    }

    public void writeToProto(ProtoOutputStream proto, long fieldId) {
        throw new RuntimeException("Stub!");
    }

    public void collectUris(List<Uri> out) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    ClipData(Parcel in) {
        throw new RuntimeException("Stub!");
    }

    public static final Parcelable.Creator<ClipData> CREATOR = new Parcelable.Creator<ClipData>() {
        @Override
        public ClipData createFromParcel(Parcel source) {
            throw new RuntimeException("Stub!");
        }

        @Override
        public ClipData[] newArray(int size) {
            throw new RuntimeException("Stub!");
        }
    };
}