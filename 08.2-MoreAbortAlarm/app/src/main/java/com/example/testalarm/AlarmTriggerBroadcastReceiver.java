package com.example.testalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmTriggerBroadcastReceiver extends BroadcastReceiver {

    private final static String TAG_ALARM_TRIGGER_BROADCAST = "ALARM_TRIGGER_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        String alarmType = intent.getStringExtra(AlarmManagerActivity.ALARM_TYPE);

        String alarmDescription = intent.getStringExtra(AlarmManagerActivity.ALARM_DESCRIPTION);

        Log.d(TAG_ALARM_TRIGGER_BROADCAST, alarmDescription);

        Toast.makeText(context, alarmDescription, Toast.LENGTH_LONG).show();

    }
}
