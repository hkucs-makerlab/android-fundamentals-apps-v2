package com.example.droidcafe;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
/*
 * https://codelabs.developers.google.com/codelabs/android-training-menus-and-pickers
 *
 * This app demonstrates
 * - app bar with option menu with icon menu item
 * - user navigation between activities - set parent and child Activity in manifest.
 * - images used as buttons to make a choice
 * - a Toast message showing the user’s choice
 * - floating action button to use an intent to launch a second activity.
 * - sends the choice as data with an intent to launch the second activity.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    // Tag for the intent extra.
    public static final String EXTRA_MESSAGE =
            "com.example.android.droidcafeinput.extra.MESSAGE";

    // The order message, displayed in the Toast and sent to the new Activity.
    private String msg=null;
    private ImageView iceCream,froyo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(this);
        ImageView donut=findViewById(R.id.donut);
        donut.setOnClickListener(this);
        iceCream=findViewById(R.id.ice_cream);
        iceCream.setOnClickListener(this);
        froyo=findViewById(R.id.froyo);
        froyo.setOnClickListener(this);

        // enable action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        msg=null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // This comment suppresses the Android Studio warning about simplifying
        // the return statements.
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_order:
                if (msg != null) {
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, msg);
                    startActivity(intent);
                    return true;
                }
                displayToast("Please select an item!");
                return true;
            case R.id.action_status:
                displayToast(getString(R.string.action_status_message));
                return true;
            case R.id.action_favorites:
                displayToast(getString(R.string.action_favorites_message));
                return true;
            case R.id.action_contact:
                displayToast(getString(R.string.action_contact_message));
                return true;
            default:
                // Do nothing
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.donut) {    //this way to compare with ID
           msg=getString(R.string.donut_select_message);
        } else if (v==iceCream) {   // this way to compare with object instant
            msg=getString(R.string.ice_cream_select_message);
        } else if (v==froyo) {
            msg=getString(R.string.froyo_select_message);
        } else if (v.getId()==R.id.fab){
            if (msg==null) {
                msg="please select an item!";
            } else {
                Intent intent = new Intent(this, OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, msg);
                startActivity(intent);
            }
        }
        displayToast(msg);
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}
