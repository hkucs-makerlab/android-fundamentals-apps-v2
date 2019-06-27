package com.example.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;


public class WordViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    public TextView wordItemView = null;
    private WordListAdapter mAdapter = null;


    public WordViewHolder(View containerView, WordListAdapter adapter) {
        super(containerView);
        wordItemView = containerView.findViewById(R.id.word);
        wordItemView.setOnClickListener(this);
        this.mAdapter = adapter;
        // from any view we get the context which is the activity class,
        // thus to access any public definition
        MainActivity activity=(MainActivity)wordItemView.getContext();

    }

    @Override
    public void onClick(View v) {
        LinkedList<String> mWordList=mAdapter.getData();
        // Get the position of the item that was clicked.
        int mPosition = getLayoutPosition();
        // Use that to access the affected item in mWordList.
        String data = mWordList.get(mPosition);
        // Change the word in the mWordList.
        mWordList.set(mPosition, "Clicked! " + data);
        // Notify the adapter, that the data has changed so it can
        // update the RecyclerView to display the data.
        mAdapter.notifyItemChanged(mPosition);
        //mAdapter.notifyDataSetChanged();
    }
}
