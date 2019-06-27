package com.example.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //Toast.makeText(this,"order activity started",Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if (msg != null) {
            TextView orderTextView = findViewById(R.id.order_textview);
            orderTextView.setText(msg);
        }
        RadioButton radioButton = findViewById(R.id.sameday);
        radioButton.setOnClickListener(this);
        radioButton = findViewById(R.id.pickup);
        radioButton.setOnClickListener(this);
        radioButton = findViewById(R.id.nextday);
        radioButton.setOnClickListener(this);
        //
        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
            /* below can be done in layout editor */
            // Create ArrayAdapter using the string array and default spinner layout.
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                    R.array.spinner_labels_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears.
//            adapter.setDropDownViewResource
//                    (android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner.
         //   spinner.setAdapter(adapter);
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        switch (view.getId()) {
            case R.id.sameday:
                if (checked)
                    // Same day service
                    displayToast(getString(R.string.same_day_messenger_service));
                break;
            case R.id.nextday:
                if (checked)
                    // Next day delivery
                    displayToast(getString(R.string.next_day_ground_delivery));
                break;
            case R.id.pickup:
                if (checked)
                    // Pick up
                    displayToast(getString(R.string.pick_up));
                break;
            default:
                // Do nothing.
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> spinner, View view, int position, long id) {
        String spinnerLabel = spinner.getItemAtPosition(position).toString();
        //displayToast(spinnerLabel);
        // or using below code
//        TextView t =(TextView)view;
//        displayToast(t.getText().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
