package com.abcd.findyourdoctor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BookSlotAdapter extends FragmentPagerAdapter {
    int tabcount;
    /**
     * Constructor for {@link FragmentPagerAdapter} that sets the fragment manager for the adapter.
     * This is the equivalent of calling {@link #FragmentPagerAdapter(FragmentManager, int)} and
     * passing in {@link #BEHAVIOR_SET_USER_VISIBLE_HINT}.
     *
     * <p>Fragments will have {@link Fragment#setUserVisibleHint(boolean)} called whenever the
     * current Fragment changes.</p>
     *
     * @param fm fragment manager that will interact with this adapter
     * @deprecated use {@link #FragmentPagerAdapter(FragmentManager, int)} with
     * {@link #BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT}
     */
    public BookSlotAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm);
        tabcount=behavior;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new ftab1();
            case 1: return  new ftab2();
            case 2: return  new ftab3();
            default: return null;

        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return tabcount;
    }
}
