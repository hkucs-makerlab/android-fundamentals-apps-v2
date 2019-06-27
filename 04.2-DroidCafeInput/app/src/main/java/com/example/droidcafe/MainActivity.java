package com.example.droidcafe;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
/*
https://codelabs.developers.google.com/codelabs/android-training-input-controls

examples of using spinner and radioButton views

 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView donut;
    private ImageView iceCream;
    private ImageView froyo;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(this);
        donut=findViewById(R.id.donut);
        iceCream=findViewById(R.id.ice_cream);
        froyo=findViewById(R.id.froyo);
        donut.setOnClickListener(this);
        iceCream.setOnClickListener(this);
        froyo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String msg=null;
        Intent intent = new Intent(this, OrderActivity.class);
        if (v.getId()==R.id.donut) {
           msg=getString(R.string.donut_order_message);
        } else if (v==iceCream) {
            msg=getString(R.string.ice_cream_order_message);
        } else if (v==froyo) {
            msg=getString(R.string.froyo_order_message);
        } else if (v==fab){
            msg="you tapped FAB button";
        }
        if (msg!=null) {
            intent.putExtra("msg", msg);
            startActivity(intent);
        }
    }
}
