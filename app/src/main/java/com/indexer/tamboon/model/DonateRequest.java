
package com.indexer.tamboon.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class DonateRequest implements Parcelable {

  @SerializedName("amount")
  private int mAmount;
  @SerializedName("name")
  private String mName;
  @SerializedName("token")
  private String mToken;

  public int getAmount() {
    return mAmount;
  }

  public void setAmount(int amount) {
    mAmount = amount;
  }

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    mName = name;
  }

  public String getToken() {
    return mToken;
  }

  public void setToken(String token) {
    mToken = token;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.mAmount);
    dest.writeString(this.mName);
    dest.writeString(this.mToken);
  }

  public DonateRequest() {
  }

  protected DonateRequest(Parcel in) {
    this.mAmount = (int) in.readValue(Long.class.getClassLoader());
    this.mName = in.readString();
    this.mToken = in.readString();
  }

  public static final Parcelable.Creator<DonateRequest> CREATOR =
      new Parcelable.Creator<DonateRequest>() {
        @Override public DonateRequest createFromParcel(Parcel source) {
          return new DonateRequest(source);
        }

        @Override public DonateRequest[] newArray(int size) {
          return new DonateRequest[size];
        }
      };
}
