package com.example.pickerdialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
/*

https://codelabs.developers.google.com/codelabs/android-training-menus-and-pickers/#7
example to use time/date picker dialog, differently of this code is not using DialogFragment

 */
public class PickerDialogActivity extends AppCompatActivity implements
        View.OnClickListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener
{

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker_dialog);
        //
        Button button =findViewById(R.id.button_date);
        button.setOnClickListener(this);
        button=findViewById(R.id.button_time);
        button.setOnClickListener(this);
        //
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        datePickerDialog= new DatePickerDialog(this, this, year, month, day);
        //
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(this,this,hour,minute,true);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_date) {
            datePickerDialog.show();
        } else if (v.getId()==R.id.button_time) {
            timePickerDialog.show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(dayOfMonth);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string +
                "/" + year_string);

        Toast.makeText(this, "Date: " + dateMessage,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Convert time elements into strings.
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        // Assign the concatenated strings to timeMessage.
        String timeMessage = (hour_string + ":" + minute_string);

        Toast.makeText(this,"Time: "+ timeMessage, Toast.LENGTH_SHORT).show();

    }
}
