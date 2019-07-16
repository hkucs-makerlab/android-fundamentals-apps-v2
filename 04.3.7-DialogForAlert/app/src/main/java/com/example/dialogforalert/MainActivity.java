package com.example.dialogforalert;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*

https://codelabs.developers.google.com/codelabs/android-training-menus-and-pickers/#6

an example how to use alertDialog, the implement of this code is slightly different to
proposed answer.
- implements dialog onClick() to handle positive and negative button
- creates dialog builder object
- builder sets dialog parameters
- creates dialog object
- shows dialog

 */

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        DialogInterface.OnClickListener {

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        Button bt = findViewById(R.id.button_alert);
        bt.setOnClickListener(this);
        //
        AlertDialog.Builder myAlertBuilder = new
                AlertDialog.Builder(MainActivity.this);
        myAlertBuilder.setTitle("Alert");
        myAlertBuilder.setMessage("Click OK to continue, or Cancel to stop:");
        myAlertBuilder.setPositiveButton("OK", this);
        //cancel can be optional
        myAlertBuilder.setNegativeButton("Cancel", this);
        alertDialog = myAlertBuilder.create();

    }

    @Override
    public void onClick(View v) {
        alertDialog.show();
    }

    // positive/negative button listener of alert dialog
    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (this.alertDialog == dialog) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                Toast.makeText(getApplicationContext(), "Pressed OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Pressed Cancel",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }
}
