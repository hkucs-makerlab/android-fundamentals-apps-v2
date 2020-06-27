package com.makerlab.exercise.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.makerlab.exercise.ui.TabFragment1;
import com.makerlab.exercise.ui.TabFragment2;
import com.makerlab.exercise.ui.TabFragment3;

import com.makerlab.exercise.R;

// custom view pager allow disable swiping.
public class TabFragmentPager extends ViewPager {
    private boolean swipeable;

    public TabFragmentPager(@NonNull Context context) {
        super(context);
    }

    public TabFragmentPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabFragmentPager);
        try {
            swipeable = a.getBoolean(R.styleable.TabFragmentPager_swipeable, true);
        } finally {
            a.recycle();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return swipeable ? super.onInterceptTouchEvent(event) : false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return swipeable ? super.onTouchEvent(event) : false;
    }

    @Override
    public boolean executeKeyEvent(KeyEvent event) {
        return this.swipeable ? super.executeKeyEvent(event) : false;
    }

    public void setSwipeable(boolean flag) {
        this.swipeable = flag;
    }

    // add fragment adaptor
    public void setFragmentManager(FragmentManager fm) {
        setAdapter(new Adapter(fm));
    }

    // add default TabLayoutOnPageChangeListener with associate tablayout to this viewpager swiping;
    // will have no effect if swipeable is false
    public void setTabLayout(TabLayout tabLayout) {
        addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    // implments adapter for fragments
    static public class Adapter extends FragmentStatePagerAdapter {
        final int numberOfPage=3;
        public Adapter(FragmentManager fm) {
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
}
