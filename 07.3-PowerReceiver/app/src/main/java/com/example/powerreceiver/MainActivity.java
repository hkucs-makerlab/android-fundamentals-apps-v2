package com.example.powerreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
https://codelabs.developers.google.com/codelabs/android-training-broadcast-receivers
- Use a custom broadcast when you want your app to take an action without launching an activity
- Broadcast receivers can receive broadcasts sent by the system or by apps
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LocalBroadcastManager broadcastManager;
    private CustomReceiver mReceiver = new CustomReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //To register a dynamic system broadcast receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        registerReceiver(mReceiver, filter);

        //to register local broadcasts
        broadcastManager=LocalBroadcastManager.getInstance(this);
        broadcastManager.registerReceiver(mReceiver,
                        new IntentFilter(CustomReceiver.ACTION_CUSTOM_BROADCAST));
        //
        Button button=findViewById(R.id.sendBroadcast);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.sendBroadcast) {
            // send local broadcast.
            Intent customBroadcastIntent = new Intent(CustomReceiver.ACTION_CUSTOM_BROADCAST);
            broadcastManager.sendBroadcast(customBroadcastIntent);
        }

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        broadcastManager.unregisterReceiver(mReceiver);
        super.onDestroy();
    }

}
