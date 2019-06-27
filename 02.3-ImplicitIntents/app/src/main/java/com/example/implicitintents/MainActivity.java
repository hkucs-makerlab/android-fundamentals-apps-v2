package com.example.implicitintents;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// https://codelabs.developers.google.com/codelabs/android-training-activity-with-implicit-intent
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button openWebButton;
    private Button openLocButton;
    private Button shareButton;
    private EditText websitEditText;
    private EditText locEditText;
    private EditText shareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openLocButton = findViewById(R.id.open_location_button);
        openLocButton.setOnClickListener(this);
        shareButton = findViewById(R.id.share_text_button);
        shareButton.setOnClickListener(this);
        openWebButton = findViewById(R.id.open_website_button);
        openWebButton.setOnClickListener(this);
        websitEditText = findViewById(R.id.website_edittext);
        locEditText = findViewById(R.id.location_edittext);
        shareEditText = findViewById(R.id.share_edittext);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == openWebButton) {
            String url = websitEditText.getText().toString();
            Uri webpage = Uri.parse(url);
            intent = new Intent(Intent.ACTION_VIEW, webpage);
        } else if (v == openLocButton) {
            String loc = locEditText.getText().toString();
            Uri locUri = Uri.parse("geo:0,0?q=" + loc);
            intent = new Intent(Intent.ACTION_VIEW, locUri);
        } else if (v == shareButton) {
            String text = shareEditText.getText().toString();
            String mimeType = "text/plain";
//            intent = ShareCompat.IntentBuilder
//                    .from(this).setType(mimeType)
//                    .setChooserTitle("Share this text with: ")
//                    .setText(text)
//                    .getIntent();
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Share this text with: ")
                    .setText(text)
                    .startChooser();
            return;

        }
        if (intent == null) return;

        //Use the resolveActivity() method and the Android package manager to
        // find an Activity that can handle your implicit Intent.
        PackageManager pkMgr = getPackageManager();
        ComponentName c = intent.resolveActivity(pkMgr);
        if (c == null) {
            Log.d(this.getClass().getSimpleName(), "Can't handle implicit intent!");
            return;
        }
        Log.d(this.getClass().getSimpleName(), c.toString());
        startActivity(intent);
    }
}
