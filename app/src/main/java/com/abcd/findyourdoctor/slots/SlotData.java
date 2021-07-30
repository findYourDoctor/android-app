package com.abcd.findyourdoctor.slots;

public class SlotData {
    private String slot_time;
    private boolean is_available;

    public String getSlotTime() {
        return slot_time;
    }

    public void setSlot_time(String slot_time) {
        this.slot_time = slot_time;
    }

    public boolean isAvailable() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }
}
