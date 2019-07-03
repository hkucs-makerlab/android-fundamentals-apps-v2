package com.example.standup;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

/*
    https://codelabs.developers.google.com/codelabs/android-training-alarm-manager/
    - demo to use alarm to send notification periodically
    -
 */
public class MainActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener {

    private AlarmManager alarmManager;
    private PendingIntent notifyPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //
        ToggleButton alarmToggle = findViewById(R.id.alarmToggle);
        alarmToggle.setOnCheckedChangeListener(this);
        // set an intent to trigger AlarmReceiver
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        notifyPendingIntent = PendingIntent.getBroadcast
                (this, AlarmReceiver.NOTIFICATION_ID, notifyIntent,
                        PendingIntent.FLAG_NO_CREATE);
        if (notifyPendingIntent == null) {
            alarmToggle.setChecked(false);
            notifyPendingIntent = PendingIntent.getBroadcast
                    (this, AlarmReceiver.NOTIFICATION_ID, notifyIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            alarmToggle.setChecked(true);
        }

        createNotificationChannel();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String toastMessage;
        if (isChecked) {
            long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES / 120;
            long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;

            //If the Toggle is turned on, set the repeating alarm with a 15 minute interval
            if (alarmManager != null) {
                // start the alarm
                alarmManager.setInexactRepeating(
                        AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        triggerTime,
                        repeatInterval,
                        notifyPendingIntent);
            }
            //Set the toast message for the "on" case.
            toastMessage = "Stand Up Alarm On!";
        } else {

            notifyPendingIntent.cancel();
            alarmManager.cancel(notifyPendingIntent);
            Log.e("test2","cancel alarm");
            //Set the toast message for the "off" case.
            toastMessage = "Stand Up Alarm Off!";
        }

        //Show a toast to say the alarm is turned on or off.
        Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT)
                .show();
    }

    public void createNotificationChannel() {

        // Create a notification manager object.
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (AlarmReceiver.PRIMARY_CHANNEL_ID,
                            "Stand up notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifies every 15 minutes to stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

}
