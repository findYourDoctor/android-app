package com.abcd.findyourdoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class BookSlot extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tabItem1, tabItem2, tabItem3;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot2);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout_Date);
        tabItem1 = (TabItem) findViewById(R.id.tabItem1);
        tabItem2 = (TabItem) findViewById(R.id.tabItem2);
        tabItem3 = (TabItem) findViewById(R.id.tabItem3);
        viewPager = (ViewPager) findViewById(R.id.Viewpagersitem);


        pagerAdapter = new BookSlotAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2)
                    pagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //listen for scroll or pg chng
           viewPager.addOnPageChangeListener(new  TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}