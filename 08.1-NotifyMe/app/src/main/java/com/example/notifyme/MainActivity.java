package com.example.notifyme;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
    https://codelabs.developers.google.com/codelabs/android-training-notifications
    notification is a message
    steps:
    - gets NotificationManager -> mNotifyManager
    - creates channel if the SDK version is 26 or higher to mNotifyManager
    - creates NotificationCompat.Builder -> notifyBuilder
    - creates notification object by notifyBuilder.build()
    - calls mNotifyManager.notify() to trigger
    - call mNotifyManager.cancel() to cancel
    - update is more complex, see updateNotification()
    - use boardcast reveiver for users to responding in notification
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final int NOTIFICATION_ID = 0;
    private NotificationManager mNotifyManager;
    private Button buttonNotfiy;
    private Button buttonCancel;
    private Button buttonUpdate;
    private NotificationReceiver mReceiver = new NotificationReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        buttonNotfiy = findViewById(R.id.button_notify);
        buttonNotfiy.setOnClickListener(this);
        buttonCancel=findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(this);
        buttonUpdate=findViewById(R.id.button_update);
        buttonUpdate.setOnClickListener(this);
        //
        createNotificationChannel();
        //
        setNotificationButtonState(true, false, false);
        // register a broadcast receive to call update notification
        registerReceiver(mReceiver,new IntentFilter(NotificationReceiver.ACTION_UPDATE_NOTIFICATION));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //
        if (android.os.Build.VERSION.SDK_INT >=android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder(){
        // create an explicit intent method to launch the MainActivity
        //     when user tap the notification
        Intent notificationIntent = new Intent(this, MainActivity.class);
        // intent wrapped in a PendingIntent
        PendingIntent notificationPendingIntent =
                PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder =
                new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID);
        notifyBuilder.setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_android)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(notificationPendingIntent)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        return notifyBuilder;

    }
    public void sendNotification() {
        // Sets up the pending intent to update the notification.
        // Corresponds to a press of the Update Me! button.
        Intent updateIntent = new Intent(NotificationReceiver.ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);
        //
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyBuilder.addAction(R.drawable.ic_update,
                "Update Notification",
                updatePendingIntent);
        //
        Notification notification=notifyBuilder.build();
        mNotifyManager.notify(NOTIFICATION_ID,notification);
        setNotificationButtonState(false, true, true);
    }

    public void updateNotification() {
        Bitmap androidImage = BitmapFactory
                .decodeResource(getResources(),R.drawable.mascot_1);
        //
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle("Notification Updated!"));
        //
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        setNotificationButtonState(false, false, true);
    }


    void setNotificationButtonState(Boolean isNotifyEnabled,
                                    Boolean isUpdateEnabled,
                                    Boolean isCancelEnabled) {
        buttonNotfiy.setEnabled(isNotifyEnabled);
        buttonUpdate.setEnabled(isUpdateEnabled);
        buttonCancel.setEnabled(isCancelEnabled);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_notify) {
            sendNotification();

        } else if (v.getId()==R.id.button_cancel) {
            mNotifyManager.cancel(NOTIFICATION_ID);
            setNotificationButtonState(true, false, false);
        } else if (v.getId() ==R.id.button_update) {
            updateNotification();

        }

    }

    //a broadcast receiver that calls updateNotification
    public class NotificationReceiver extends BroadcastReceiver {
        public static final String ACTION_UPDATE_NOTIFICATION =
                "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";
        public NotificationReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            updateNotification();
        }
    }
}
