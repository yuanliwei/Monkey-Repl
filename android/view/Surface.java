package android.view;

import android.os.Parcel;
import android.os.Parcelable;

public class Surface implements Parcelable {
  public static final int ROTATION_0 = 0;
  public static final int ROTATION_90 = 1;
  public static final int ROTATION_180 = 2;
  public static final int ROTATION_270 = 3;

  @Override
  public int describeContents() {
    throw new RuntimeException("Stub!");
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    throw new RuntimeException("Stub!");
  }
}