package com.abcd.findyourdoctor.slots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.abcd.findyourdoctor.BaseActivity;
import com.abcd.findyourdoctor.R;
import com.abcd.findyourdoctor.serverrequest.MockRepository;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class BookSlotActivity extends BaseActivity {

    TabLayout tabLayout;
    TabItem tabItem1, tabItem2, tabItem3;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot2);

        BaseSlotData slotData = MockRepository.getInstance().getData(this, BaseSlotData.class, "slots.json");
        tabLayout = findViewById(R.id.tabSlots);
        viewPager = findViewById(R.id.viewPagerSlots);


        pagerAdapter = new BookSlotAdapter(getSupportFragmentManager(), slotData.getSlot());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//
//                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2)
//                    pagerAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
        //listen for scroll or pg chng
//           viewPager.addOnPageChangeListener(new  TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}