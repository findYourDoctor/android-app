package com.abcd.findyourdoctor.doctor.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class DoctorData implements Parcelable {
    private String name;
    private String speciality;
    private boolean isOnline;
    private String ratings;
    private String id;

    public DoctorData() {

    }
    protected DoctorData(Parcel in) {
        name = in.readString();
        speciality = in.readString();
        isOnline = in.readByte() != 0;
        ratings = in.readString();
        id = in.readString();
    }

    public static final Creator<DoctorData> CREATOR = new Creator<DoctorData>() {
        @Override
        public DoctorData createFromParcel(Parcel in) {
            return new DoctorData(in);
        }

        @Override
        public DoctorData[] newArray(int size) {
            return new DoctorData[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(speciality);
        dest.writeByte((byte) (isOnline ? 1 : 0));
        dest.writeString(ratings);
        dest.writeString(id);
    }
}
