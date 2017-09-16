
package com.indexer.tamboon.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Charity implements Parcelable {

  @SerializedName("id")
  private Long mId;
  @SerializedName("logo_url")
  private String mLogoUrl;
  @SerializedName("name")
  private String mName;

  public Long getId() {
    return mId;
  }

  public void setId(Long id) {
    mId = id;
  }

  public String getLogoUrl() {
    return mLogoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    mLogoUrl = logoUrl;
  }

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    mName = name;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.mId);
    dest.writeString(this.mLogoUrl);
    dest.writeString(this.mName);
  }

  public Charity() {
  }

  protected Charity(Parcel in) {
    this.mId = (Long) in.readValue(Long.class.getClassLoader());
    this.mLogoUrl = in.readString();
    this.mName = in.readString();
  }

  public static final Parcelable.Creator<Charity> CREATOR = new Parcelable.Creator<Charity>() {
    @Override public Charity createFromParcel(Parcel source) {
      return new Charity(source);
    }

    @Override public Charity[] newArray(int size) {
      return new Charity[size];
    }
  };
}
