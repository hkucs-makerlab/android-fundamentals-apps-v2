package com.makerlab.exercise;

import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.makerlab.exercise.support.TabFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        TabLayout.OnTabSelectedListener{
    static private String LOG_TAG = MainActivity.class.getSimpleName();
    ViewPager viewPager;
    TabLayout tabLayout;
    TabLayoutOnPageChangeListener pageListener ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        tabLayout = findViewById(R.id.tab_layout);
       tabLayout.addOnTabSelectedListener(this);
        //
        viewPager = findViewById(R.id.pager);
        pageListener = new TabLayoutOnPageChangeListener(tabLayout);
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        //
    }

    // TabLayout.OnTabSelectedListener
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.e(LOG_TAG, "onTabSelected() :");
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    //
    // ViewPager.OnPageChangeListener
    @Override
    public void onPageScrolled(int i, float v, int i1) {
        //Log.e(LOG_TAG,"onPageScrolled() :"+i+" "+i1);
    }

    @Override
    public void onPageSelected(int i) {
        Log.e(LOG_TAG, "onPageSelected() :" + i);
        tabLayout.setScrollPosition(i,0,true);
//        if (tabLayout.getSelectedTabPosition()!=i && i < tabLayout.getTabCount()) {
//
//        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        // Log.e(LOG_TAG,"onPageScrollStateChanged() :"+i);
    }

}
