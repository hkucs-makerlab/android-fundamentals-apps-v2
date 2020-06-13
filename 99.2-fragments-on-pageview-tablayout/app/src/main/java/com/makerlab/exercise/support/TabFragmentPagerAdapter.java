package com.makerlab.exercise.support;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.makerlab.exercise.ui.TabFragment1;
import com.makerlab.exercise.ui.TabFragment2;
import com.makerlab.exercise.ui.TabFragment3;

public class TabFragmentPagerAdapter extends FragmentStatePagerAdapter {
    final int numberOfPage=3;
    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int pageIndex) {
        switch (pageIndex) {
            case 0: return new TabFragment1();
            case 1: return new TabFragment2();
            case 2: return new TabFragment3();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfPage;
    }
}
