package com.makerlab.exercise.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makerlab.exercise.MainActivity;
import com.makerlab.exercise.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment1 extends Fragment {
    static private String LOG_TAG = TabFragment1.class.getSimpleName();

    public TabFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(LOG_TAG, "onCreateView() :");
        return inflater.inflate(R.layout.fragment_tab_fragment1, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.e(LOG_TAG, "onStart() :");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(LOG_TAG, "onStop() :");
    }
}
