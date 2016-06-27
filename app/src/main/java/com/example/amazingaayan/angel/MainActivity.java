package com.example.amazingaayan.angel;

import android.app.Activity;
import android.app.FragmentController;
import android.app.FragmentHostCallback;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    ViewPager viewPager = null;
    Toolbar toolbar;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        Fajr fajrView = Fajr.newInstance();
        Zuhr zuhrView = Zuhr.newInstance();
        Asr asrView = Asr.newInstance();
        Maghrib maghribView = Maghrib.newInstance();
        Isha ishaView = Isha.newInstance();
        Jumuah jumuahView = Jumuah.newInstance();

        MyAdapter myViewPagerAdapter = new MyAdapter(getSupportFragmentManager());

        myViewPagerAdapter.addFragment(fajrView);
        myViewPagerAdapter.addFragment(zuhrView);
        myViewPagerAdapter.addFragment(asrView);
        myViewPagerAdapter.addFragment(maghribView);
        myViewPagerAdapter.addFragment(ishaView);
        myViewPagerAdapter.addFragment(jumuahView);

        viewPager.setAdapter(myViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    class MyAdapter extends FragmentStatePagerAdapter {
        String[] tabsTitle;
        List<Fragment> mFragmentList = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
            tabsTitle = getResources().getStringArray(R.array.prayer_names);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public  void addFragment(Fragment fragment){
            mFragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabsTitle[position];
        }
    }

}
