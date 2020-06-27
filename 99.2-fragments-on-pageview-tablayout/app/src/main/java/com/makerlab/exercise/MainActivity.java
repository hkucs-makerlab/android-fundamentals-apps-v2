package com.makerlab.exercise;

import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.makerlab.exercise.support.TabFragmentPagerAdapter;

// example to use view pager for dynamic fragment display,
//      fragment to display is constructed in pager adapter TabFragmentPagerAdapter
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        TabLayout.OnTabSelectedListener {
    static private String LOG_TAG = MainActivity.class.getSimpleName();
    static private boolean D = BuildConfig.DEBUG;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(this); // listen to detect tab selection
        //
        //default page swiping listener for tab changing, not use in this example
        //TabLayoutOnPageChangeListener pageListener = new TabLayoutOnPageChangeListener(tabLayout);

        viewPager = findViewById(R.id.pager);
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this); //listen to detect swiping
        //viewPager.addOnPageChangeListener(pageListener);

    }

    // TabLayout.OnTabSelectedListener
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (D) {
            Log.e(LOG_TAG, "onTabSelected() :");
        }
        viewPager.setCurrentItem(tab.getPosition()); // trigger view page swiping
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
        if (D) {
            Log.e(LOG_TAG, "onPageSelected() :" + i);
        }
        // triggers tab changing
        tabLayout.setScrollPosition(i, 0, true);
//        if (tabLayout.getSelectedTabPosition()!=i && i < tabLayout.getTabCount()) {
//
//        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        if (D) {
            Log.e(LOG_TAG, "onPageScrollStateChanged() :" + i);
        }
    }

}
