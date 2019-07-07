package com.example.pickerfordate;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*

https://codelabs.developers.google.com/codelabs/android-training-menus-and-pickers/#7
    - example to use date picker dialog with DialogFragment
*/

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        Button buttonDate =findViewById(R.id.button_date);
        buttonDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_date) {
            // create date fragment
            DialogFragment newFragment = new DatePickerFragment();
            //
            newFragment.show(getSupportFragmentManager(),"datePicker");
        }
    }

    // calling from DatePickerFragment
    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string +
                "/" + year_string);

        Toast.makeText(this, "Date: " + dateMessage,
                Toast.LENGTH_SHORT).show();
    }


}
