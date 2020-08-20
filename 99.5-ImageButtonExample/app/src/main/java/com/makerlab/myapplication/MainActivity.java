package com.makerlab.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener, View.OnClickListener, View.OnTouchListener {
    static final String LOG_TAG=MainActivity.class.getSimpleName();
    Switch mSwitchEnableButton;
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwitchEnableButton =findViewById(R.id.switch_enable_button);
        mSwitchEnableButton.setOnCheckedChangeListener(this);
        mButton=findViewById(R.id.fire_button);
        mButton.setOnClickListener(this);
        mButton.setOnTouchListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mButton.setEnabled(isChecked);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.fire_button) {
            Log.d(LOG_TAG,"onClick() - clicked");
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId()==R.id.fire_button) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mButton.setPressed(true);
                Log.d(LOG_TAG,"onTouch() - pressed");
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                mButton.setPressed(false);
                Log.d(LOG_TAG,"onTouch() - released");
                mButton.performClick(); // calls the onClick()
            }
            return true;
        }
        return false;
    }
}
