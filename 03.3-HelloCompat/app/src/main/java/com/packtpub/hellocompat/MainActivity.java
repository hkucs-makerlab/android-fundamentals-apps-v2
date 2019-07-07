package com.packtpub.hellocompat;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

/*
    https://codelabs.developers.google.com/codelabs/android-training-support-libraries
   demos
    - get color resource with Compat API - ContextCompat.getColor()
    - Activity's methods
        * getResource() - 1st get ID and using the ID to get value by calling ContextCompat.getColor
        * getApplicationContext()
    - saving instance state with Bundle
    - android.os.Build, see https://developer.android.com/reference/android/os/Build
 */

public class MainActivity extends AppCompatActivity {
    private TextView mHelloTextView;
    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        mHelloTextView = findViewById(R.id.hello_textview);
        if (savedInstanceState != null) {
            mHelloTextView.setTextColor(savedInstanceState.getInt("color"));
            Log.d("testme","savedInstanceState restored");
        } else {
            Log.d("testme","savedInstanceState is null");
        }
        Log.d("testme","Build version - "+Build.VERSION.SDK);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the current text color
        outState.putInt("color", mHelloTextView.getCurrentTextColor());
    }

    public void changeColor(View view) {
        Random random = new Random();
        String colorName = mColorArray[random.nextInt(20)];
        String pakcageName=getApplicationContext().getPackageName();

        //There are separate IDs for both the names and the values.
        //      get the color ID from color resource
        int colorResourceNameID = getResources().getIdentifier(colorName,"color", pakcageName);
        //
/*        int colorResourceNameID =
                getResources().getColor(colorResourceName, this.getTheme());*/

        // getResources().getColor not in SDK 14 as this build minsdk is, use ContextCompat.getColor
        int colorValue= ContextCompat.getColor(this, colorResourceNameID);
        mHelloTextView.setTextColor(colorValue);
    }
}
