package com.makerlab.app3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*
    examples of two activity back/fore and result is returned by back key or up action button
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final int REQUEST_CODE=1111;
    private Button button;
    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =findViewById(R.id.button);
        button.setOnClickListener(this);
        textViewResult=findViewById(R.id.textViewResult);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        Log.e("onActivityResult","called");
        if (requestCode == REQUEST_CODE) {
            Log.e("onActivityResult","got result");
            if (resultCode == RESULT_OK) {
                Log.e("onActivityResult","RESULT_OK");
                textViewResult.setText("returned result is "+result.getStringExtra("key").toUpperCase());
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

}
