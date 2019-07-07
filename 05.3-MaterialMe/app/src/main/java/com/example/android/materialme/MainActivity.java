/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.materialme;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/*
https://codelabs.developers.google.com/codelabs/android-training-adaptive-layouts/index.html
    use alternate resources xml to change the appearance of UI
    summaries:
     - cardView in recyclerView
     - drag-n-drap, swipe in cardView, needed ItemTouchHelper to do that.
     - onclick event of ViewHolder sends the content detail of a cardView with intent
           to another Activity
     - how to use drawable resource in cardView
     - resource qualifiers which providing alternative resources for changing of app
           appearance (e.g landscape/portrait/tablet), demos using GridLayoutManager here,
               values/integers.xml variant resource file for specifying grid column
                        respect to screen size
               values/styles.xml variants for different screen size on view styles
               values/strings.xml for different text respect to language locales

*/

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<Sport> mSportsData;
    private SportsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        //
        int gridColumnCount =
                getResources().getInteger(R.integer.grid_column_count);
        //
        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new SportsAdapter(this);
        // Get the data.
        mSportsData = mAdapter.getSportsData();
        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);
        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));
        //
        mRecyclerView.setAdapter(mAdapter);
        //
        int swipeDirs;
        if(gridColumnCount > 1){
            swipeDirs = 0;
        } else {
            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, swipeDirs) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {

                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mSportsData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                
                mSportsData.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }

    @Override
    public void onClick(View v) {
        mAdapter.initializeData();
        mSportsData = mAdapter.getSportsData();
        Toast.makeText(this,"Data reset!",Toast.LENGTH_SHORT).show();
    }

}
