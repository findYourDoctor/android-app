package com.app.chatmodule.messaging.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class SecondUserData implements Parcelable {

    private String name;
    private String id;
    private String imageUrl;
    private Long timestamp;

    public SecondUserData() {}
    protected SecondUserData(Parcel in) {
        name = in.readString();
        id = in.readString();
        imageUrl = in.readString();
        if (in.readByte() == 0) {
            timestamp = null;
        } else {
            timestamp = in.readLong();
        }
    }

    public static final Creator<SecondUserData> CREATOR = new Creator<SecondUserData>() {
        @Override
        public SecondUserData createFromParcel(Parcel in) {
            return new SecondUserData(in);
        }

        @Override
        public SecondUserData[] newArray(int size) {
            return new SecondUserData[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(id);
        parcel.writeString(imageUrl);
        if (timestamp == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(timestamp);
        }
    }
}
