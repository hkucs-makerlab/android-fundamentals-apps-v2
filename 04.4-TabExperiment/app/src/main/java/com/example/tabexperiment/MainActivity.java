package com.example.tabexperiment;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/*
https://codelabs.developers.google.com/codelabs/android-training-provide-user-navigation/index.html#3
    demo using tab layout, ViewPager layout manager , page adaptor for content implemented as a fragment

    view page can swipes left/right, needs an adaptor which extends the FragmentStatePagerAdapter
            for content/views
    view page is added TabLayout.TabLayoutOnPageChangeListener for page changed
    tab layout is added TabLayout.OnTabSelectedListener for tab changed
        --onTabSelected(), onTabUnSelected(), onTabReselected()
 */
public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        android.support.v7.widget.Toolbar toolbar =
                findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        // Create an instance of the tab layout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(this);  // trap tab events
/*
        //tab labels are done in layout editor
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label3));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL); // Set the tabs to fill the entire layout.
*/
        // Use PagerAdapter to manage page views in fragments.
        // Each page is represented by its own fragment.
        PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager = findViewById(R.id.pager);
        // traps page changed events to switch the tab label
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(adapter);

    }

    // tab selection listeners for the tab layout
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        // calls viewPage to switch context/views
        //  then viewPage calls pageAdapter to fill the contents
        viewPager.setCurrentItem(tab.getPosition());

        Log.d("msg","selelcted");
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

        Log.d("msg","unselelcted");
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Log.d("msg","re-selelcted");
    }
}
