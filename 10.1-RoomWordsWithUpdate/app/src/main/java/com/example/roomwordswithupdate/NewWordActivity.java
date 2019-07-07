package com.example.roomwordswithupdate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_REPLY_WORD ="com.example.android.roomwordswithupdate.REPLY";
    public static final String EXTRA_REPLY_ID = "com.android.example.roomwordswithupdate.REPLY_ID";

    private EditText mEditWordView;
    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(this);

         extras = getIntent().getExtras();

        // If we are passed content, fill it in for the user to edit.
        if (extras != null) {
            String word = extras.getString(MainActivity.EXTRA_DATA_UPDATE_WORD, "");
            if (!word.isEmpty()) {
                mEditWordView.setText(word);
                mEditWordView.setSelection(word.length());
                mEditWordView.requestFocus();
            }
        } // Otherwise, start with empty fields.

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button_save) {
            // Create a new Intent for the reply.
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditWordView.getText())) {
                // No word was entered, set the result accordingly.
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                // Get the new word that the user entered.
                String word = mEditWordView.getText().toString();
                // Put the new word in the extras for the reply Intent.
                replyIntent.putExtra(EXTRA_REPLY_WORD, word);
                if (extras != null && extras.containsKey(MainActivity.EXTRA_DATA_ID)) {
                    int id = extras.getInt(MainActivity.EXTRA_DATA_ID, -1);
                    if (id != -1) {
                        replyIntent.putExtra(EXTRA_REPLY_ID, id);
                    }
                }
                // Set the result status to indicate success.
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        }
    }
}
