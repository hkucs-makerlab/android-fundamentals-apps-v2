package com.example.testalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


public class AlarmTriggerActivity extends AppCompatActivity {

    private final static String TAG_ALARM_TRIGGER_ACTIVITY = "ALARM_TRIGGER_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_trigger);

        setTitle("dev2qa.com - Alarm Triggered Activity.");

        // Get intent that trigger this activity.
        Intent intent = getIntent();

        // Get alarm type.
        String alarmType = intent.getStringExtra(AlarmManagerActivity.ALARM_TYPE);

        // Get alarm description string.
        String alarmDescription = intent.getStringExtra(AlarmManagerActivity.ALARM_DESCRIPTION);

        TextView textView = (TextView) findViewById(R.id.alarm_trigger_activity_text_view);
        textView.setText(alarmDescription);

        Log.d(TAG_ALARM_TRIGGER_ACTIVITY, alarmDescription);

    }
}
