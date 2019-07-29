package com.example.testalarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AlarmTriggerService extends Service {

    private final static String TAG_ALARM_TRIGGER_SERVICE = "ALARM_TRIGGER_SERVICE";

    public AlarmTriggerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        String message = "Alarm trigger this service. This is service onCreate() method.";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        Log.d(TAG_ALARM_TRIGGER_SERVICE, message);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String alarmType = intent.getStringExtra(AlarmManagerActivity.ALARM_TYPE);
        String alarmDescription = intent.getStringExtra(AlarmManagerActivity.ALARM_DESCRIPTION);
        alarmDescription = alarmDescription + ", this is service onStartCommand() method/";

        Toast.makeText(getApplicationContext(), alarmDescription, Toast.LENGTH_LONG).show();

        Log.d(TAG_ALARM_TRIGGER_SERVICE, alarmDescription);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
