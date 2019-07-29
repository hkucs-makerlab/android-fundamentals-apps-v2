package com.example.testalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// https://www.dev2qa.com/android-one-time-repeat-alarm-example/

public class AlarmManagerActivity extends AppCompatActivity {

    public static final String ALARM_TYPE = "ALARM_TYPE";

    public static final String ALARM_TYPE_ONE_TIME = "ALARM_TYPE_ONE_TIME";

    public static final String ALARM_TYPE_REPEAT = "ALARM_TYPE_REPEAT";

    public static final String ALARM_DESCRIPTION = "ALARM_DESCRIPTION";

    private AlarmManager alarmManager = null;

    private PendingIntent pendingIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);

        setTitle("dev2qa.com - Android Alarm Manager Example.");

        // Get system built-in alarm manager object.
        alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);

        // Click this button to start a one time alarm after 5 seconds from now.
        Button startOneTimeAlarmButton = (Button) findViewById(R.id.alarm_start_one_time_button);
        startOneTimeAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create an intent that will open an activity.
                Intent intent = new Intent(getApplicationContext(), AlarmTriggerActivity.class);
                // Add alarm type.
                intent.putExtra(ALARM_TYPE, ALARM_TYPE_ONE_TIME);
                // Add extra description string.
                intent.putExtra(ALARM_DESCRIPTION, "One time alarm start this activity.");
                // Wrap the intent object for later use to start the AlarmTriggerActivity.
                pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                // Trigger alarm 5 seconds after now.
                long alarmTriggerTime = System.currentTimeMillis() + 5000;
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTriggerTime, pendingIntent);

                Toast.makeText(getApplicationContext(), "A one time alarm has been created, it will be triggered after 5 seconds. This alarm will open another activity.", Toast.LENGTH_LONG).show();
            }
        });

        // Click this button to start a repeated alarm, it will execute every interval seconds to invoke a service.
        Button startRepeatServiceAlarmButton = (Button) findViewById(R.id.alarm_start_repeat_service_button);
        startRepeatServiceAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlarmTriggerService.class);
                intent.putExtra(ALARM_TYPE, ALARM_TYPE_REPEAT);
                intent.putExtra(ALARM_DESCRIPTION, "Repeat alarm start this service.");
                pendingIntent = PendingIntent.getService(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                long alarmStartTime = System.currentTimeMillis();
                long alarmExecuteInterval = 90 * 1000;

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmExecuteInterval, pendingIntent);

                Toast.makeText(getApplicationContext(), "A repeat alarm has been created. This alarm will open a service.", Toast.LENGTH_LONG).show();
            }
        });

        // Click this button to start a repeated alarm, it will execute every interval seconds to send a broadcast.
        Button startRepeatBroadcastAlarmButton = (Button) findViewById(R.id.alarm_start_repeat_broadcast_button);
        startRepeatBroadcastAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlarmTriggerBroadcastReceiver.class);
                intent.putExtra(ALARM_TYPE, ALARM_TYPE_REPEAT);
                intent.putExtra(ALARM_DESCRIPTION, "Repeat alarm start this broadcast.");
                pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                long alarmStartTime = System.currentTimeMillis();
                // This is too short, it will be expanded by android os to 60 seconds by default.
                long alarmExecuteInterval = 10 * 1000;
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmExecuteInterval, pendingIntent);

                Toast.makeText(getApplicationContext(), "A repeat alarm has been created. This alarm will send to a broadcast receiver.", Toast.LENGTH_LONG).show();
            }
        });

        // Click this button to cancel current pendingIntent related alarm.
        Button cancelRepeatAlarmButton = (Button) findViewById(R.id.alarm_cancel_repeat_button);
        cancelRepeatAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmManager.cancel(pendingIntent);
                Toast.makeText(AlarmManagerActivity.this, "Cancel current alarm.", Toast.LENGTH_LONG).show();
            }
        });

    }
}