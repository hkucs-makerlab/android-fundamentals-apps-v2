package com.example.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordViewHolder> {
    private LinkedList<String> mWordList;
    private LayoutInflater mInflater;

    public WordListAdapter(Context activity,  LinkedList<String> wordList) {
        mWordList=wordList;
        mInflater = LayoutInflater.from(activity);

    }

    // number of WordViewHolder respective to data size
    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //mItemView is constraint layout
        View mItemView = mInflater.inflate(R.layout.wordlist_item,parent, false);

        //Log.e("WordListAdapter",mItemView.getContext().toString());
        Log.e("WordListAdapter","onCreateViewHolder");
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int position) {
        String data = mWordList.get(position);
        wordViewHolder.wordItemView.setText(data);
        Log.e("WordListAdapter","onBindViewHolder - "+String.valueOf(position));
    }


    public  LinkedList<String> getData() {

        return mWordList;
    }
}
