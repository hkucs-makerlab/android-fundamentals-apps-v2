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

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the sports data.
 */
class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder>  {
    // Member variables.
    private ArrayList<Sport> mSportsData;
    private MainActivity mContext; //required to inflate views in list_item.xml

    /**
     * Constructor that passes in the sports data and the context.

     * @param context Context of the application.
     */
    SportsAdapter(Context context) {
        this.mContext = (MainActivity)context;
        // Initialize the ArrayList that will contain the data.
        initializeData();
    }
    public ArrayList<Sport> getSportsData() {
        return mSportsData;
    }
    /**
     * Initialize the sports data from resources.
     */
    public void initializeData() {
        // Get the resources from the XML file.
        String[] sportsList = mContext.getResources()
                .getStringArray(R.array.sports_titles);
        String[] sportsInfo = mContext.getResources()
                .getStringArray(R.array.sports_info);
        //
        TypedArray sportsImageResourcesTypedArray =
                mContext.getResources().obtainTypedArray(R.array.sports_images);

        mSportsData = new ArrayList<>();
        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<sportsList.length;i++){
            mSportsData.add(new Sport(
                    sportsList[i],
                    sportsInfo[i],
                    sportsImageResourcesTypedArray.getResourceId(i,0))
            );
            Log.e("tat",String.valueOf( sportsImageResourcesTypedArray.getResourceId(i,0)));
        }

        // Notify the adapter of the change.
        notifyDataSetChanged();
    }
    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View viewContainer=layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(viewContainer);
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(SportsAdapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        Sport currentSport = mSportsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentSport);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mSportsData.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mSportsImage;
        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param ViewContainer The rootview of the list_item.xml layout file.
         */
        ViewHolder(View ViewContainer) {
            super(ViewContainer);

            ViewContainer.setOnClickListener(this);
            // Initialize the views.
            mTitleText = ViewContainer.findViewById(R.id.title);
            mInfoText = ViewContainer.findViewById(R.id.subTitle);
            mSportsImage = itemView.findViewById(R.id.sportsImage);
        }

        void bindTo(Sport currentSport){
            // Populate the textviews with data.
            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());
            RequestBuilder<Drawable> builder=Glide.with(mContext).load(currentSport.getImageResource());
            builder.into(mSportsImage);
        }

        @Override
        public void onClick(View v) {
            Sport currentSport = mSportsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentSport.getTitle());
            detailIntent.putExtra("image_resource",
                    currentSport.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }
}
