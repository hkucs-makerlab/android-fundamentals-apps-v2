package com.example.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toast.makeText(this,"order activity started",Toast.LENGTH_SHORT).show();
        Intent intent=getIntent();
        String msg=intent.getStringExtra("msg");
        if (msg!=null) {
            TextView orderTextView=findViewById(R.id.order_textview);
            orderTextView.setText(msg);
        }
    }
}
