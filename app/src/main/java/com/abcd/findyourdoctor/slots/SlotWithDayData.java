package com.abcd.findyourdoctor.slots;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class SlotWithDayData implements Parcelable {
    private String day;
    private ArrayList<SlotData> slots;

    protected SlotWithDayData(Parcel in) {
        day = in.readString();
    }

    public static final Creator<SlotWithDayData> CREATOR = new Creator<SlotWithDayData>() {
        @Override
        public SlotWithDayData createFromParcel(Parcel in) {
            return new SlotWithDayData(in);
        }

        @Override
        public SlotWithDayData[] newArray(int size) {
            return new SlotWithDayData[size];
        }
    };

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<SlotData> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<SlotData> slots) {
        this.slots = slots;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(day);
    }
}
