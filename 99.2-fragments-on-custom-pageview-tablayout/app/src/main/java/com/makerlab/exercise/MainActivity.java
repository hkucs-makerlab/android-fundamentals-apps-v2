package com.makerlab.exercise;

import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.makerlab.exercise.support.TabFragmentPager;

public class MainActivity extends AppCompatActivity implements
        TabLayout.OnTabSelectedListener {
    static private String LOG_TAG = MainActivity.class.getSimpleName();
    TabFragmentPager viewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(this);
        //
        viewPager = findViewById(R.id.pager);
        viewPager.setFragmentManager(getSupportFragmentManager());
        viewPager.setTabLayout(tabLayout);
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
        Log.e(LOG_TAG, "onTabUnselected() :");
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Log.e(LOG_TAG, "onTabReselected() :");
    }


}
