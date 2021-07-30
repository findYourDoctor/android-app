package com.abcd.findyourdoctor.slots;

import java.util.ArrayList;

public class BaseSlotData {
    private ArrayList<SlotWithDayData> slotList;

    public ArrayList<SlotWithDayData> getSlot() {
        return slotList;
    }

    public void setSlot(ArrayList<SlotWithDayData> slot) {
        this.slotList = slot;
    }
}
