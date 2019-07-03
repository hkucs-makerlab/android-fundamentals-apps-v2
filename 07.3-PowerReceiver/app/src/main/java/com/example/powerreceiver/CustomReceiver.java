package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    public static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        if (intentAction == null) {
            return;
        }
        String toastMessage = "unknown intent action";
        switch (intentAction){
            case Intent.ACTION_POWER_CONNECTED:
                toastMessage = "Power connected!";
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                toastMessage = "Power disconnected!";
                break;
            case CustomReceiver.ACTION_CUSTOM_BROADCAST:
                toastMessage = "Custom Broadcast Received";
                break;
        }

        //Display the toast.
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();

    }
}
