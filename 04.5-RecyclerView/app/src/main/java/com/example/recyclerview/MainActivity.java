package com.example.recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;
/*

https://codelabs.developers.google.com/codelabs/android-training-create-recycler-view/index.html

demo to use RecycleView to scroll a list of data in views -
    * need a adaptor (RecyclerView.Adapter) to inflate views from layout xml;
    * then return a view holder (RecyclerView.ViewHolder) that contains a single view;
    * in this example, returned view is a constraint layout covering other views e.g TextView;
    * the adapter is also used to notify the change of data in view holder.

 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinkedList<String> mWordList;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this);
        mWordList=mAdapter.getData();
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            mAdapter.clearData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            int wordListSize=mAdapter.addData();
            // Scroll to the bottom.
            mRecyclerView.smoothScrollToPosition(wordListSize);
            Snackbar.make(view, "a word added", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
