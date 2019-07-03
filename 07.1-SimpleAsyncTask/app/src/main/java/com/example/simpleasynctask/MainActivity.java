package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Random;
/*
https://codelabs.developers.google.com/codelabs/android-training-create-asynctask
demo to use AsyncTask
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;

    public class SimpleAsyncTask extends AsyncTask <Void, Void, String>{
        private WeakReference<TextView> mTextView;

        public SimpleAsyncTask(TextView tv) {
            mTextView = new WeakReference<>(tv);
        }

        @Override
        protected String doInBackground(Void... voids) {
            // Generate a random number between 0 and 10
            Random r = new Random();
            int n = r.nextInt(11);

            // Make the task take long enough that we have
            // time to rotate the phone while it is running
            int s = n * 200;

            // Sleep for the random amount of time
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a String result
            return "Awake at last after sleeping for " + s + " milliseconds!";
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView=mTextView.get();
            textView.setText(result);
        }
    }

    public class SimpleAsyncTask2 extends AsyncTask <Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            // Generate a random number between 0 and 10
            Random r = new Random();
            int n = r.nextInt(11);
            // Make the task take long enough that we have
            // time to rotate the phone while it is running
            int s = n * 200;
            // Sleep for the random amount of time
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a String result
            return "Awake at last after sleeping for " + s + " milliseconds!";
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView=findViewById(R.id.textView);
            textView.setText(result);
        }
    }

    public class SimpleAsyncTask3 extends AsyncTask <Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            // Generate a random number between 0 and 10
            Random r = new Random();
            int n = r.nextInt(11);
            // Make the task take long enough that we have
            // time to rotate the phone while it is running
            int s = n * 200;
            // Sleep for the random amount of time
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a String result
            return "Awake at last after sleeping for " + s + " milliseconds!";
        }

        @Override
        protected void onPostExecute(String result) {
            textView.setText(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        Button button=findViewById(R.id.button);
        button.setOnClickListener(this);
        //
        textView=findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button) {
            textView.setText("Napping…");
            // Start the AsyncTask.
            //new SimpleAsyncTask(textView).execute();
            new SimpleAsyncTask3().execute();
        }
    }
}
