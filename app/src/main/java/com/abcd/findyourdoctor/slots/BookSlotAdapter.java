package com.abcd.findyourdoctor.slots;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class BookSlotAdapter extends FragmentStatePagerAdapter {
    ArrayList<SlotWithDayData> slotDataList;

    public BookSlotAdapter(@NonNull FragmentManager fm, ArrayList<SlotWithDayData> slotDataList) {
        super(fm);
        this.slotDataList = slotDataList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return SlotFragment.newInstance(slotDataList.get(position));
    }

    @Override
    public int getCount() {
        return slotDataList.size();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return slotDataList.get(position).getDay();
    }
}
