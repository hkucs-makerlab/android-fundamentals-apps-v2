package com.example.whowroteit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/*
https://codelabs.developers.google.com/codelabs/android-training-asynctask-asynctaskloader/index.html
https://developer.android.com/reference/android/os/AsyncTask

    Demos implementing asynchronous task which extends class AsyncTask,
        configure it using these parameters:
    params:   The data type of the parameters in array,
                sent to the task upon executing the doInBackground() override method.
    progress: The data type of the progress units
                published using the onProgressUpdated() override method.
    result:   The data type of the result delivered
                by the onPostExecute() override method.

    e.g:
    class FetchBook extends AsyncTask<String, Void, String>{}

    Also demo to use:
    - InputMethodManager which hides the virtual keyboard
    - ConnectivityManager
    - JSON object
    - URL
    - HttpURLConnection

*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = (EditText)findViewById(R.id.bookInput);
        mTitleText = (TextView)findViewById(R.id.titleText);
        mAuthorText = (TextView)findViewById(R.id.authorText);
        Button button =findViewById(R.id.searchButton);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.searchButton) {
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (connMgr != null) {
                networkInfo = connMgr.getActiveNetworkInfo();
            }
            // Get the search string from the input field.
            String queryString = mBookInput.getText().toString();
            if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
                new FetchBookAsyncTask().execute(queryString);
                mAuthorText.setText("");
                mTitleText.setText(R.string.loading);
            } else {
                if (queryString.length() == 0) {
                    mAuthorText.setText("");
                    mTitleText.setText(R.string.no_search_term);
                } else {
                    mAuthorText.setText("");
                    mTitleText.setText(R.string.no_network);
                }
            }
        }
    }

    //
    public class FetchBookAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return NetworkUtils.getBookInfo(strings[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result ==null) {
                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray itemsArray = jsonObject.getJSONArray("items");
                int i = 0;
                String title = null;
                String authors = null;
                while (i < itemsArray.length() &&
                        (authors == null && title == null)) {
                    // Get the current item information.
                    JSONObject book = itemsArray.getJSONObject(i);
                    JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                    // Try to get the author and title from the current item,
                    // catch if either field is empty and move on.
                    try {
                        title = volumeInfo.getString("title");
                        authors = volumeInfo.getString("authors");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // Move to the next item.
                    i++;
                }
                if (title != null && authors != null) {
                    mTitleText.setText(title);
                    mAuthorText.setText(authors);
                } else {
                    mTitleText.setText(R.string.no_results);
                    mAuthorText.setText("");
                }
            } catch (JSONException e) {
                // If onPostExecute does not receive a proper JSON string,
                // update the UI to show failed results.
                mTitleText.setText(R.string.no_results);
                mAuthorText.setText("");
                e.printStackTrace();
            }
        }
    }
}
