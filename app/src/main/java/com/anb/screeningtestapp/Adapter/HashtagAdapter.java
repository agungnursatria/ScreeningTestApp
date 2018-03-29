package com.anb.screeningtestapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.anb.screeningtestapp.R;

import java.util.ArrayList;

/**
 * Created by Agung Nursatria on 3/29/2018.
 */

public class HashtagAdapter extends RecyclerView.Adapter<HashtagAdapter.HashtagViewHolder> {

    public Context context;
    public ArrayList<String> hashtagList;

    public HashtagAdapter(Context context, ArrayList<String> hashtagList) {
        this.context = context;
        this.hashtagList = hashtagList;
    }

    @Override
    public HashtagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.hashtag_item, parent, false);
        return new HashtagViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HashtagViewHolder holder, int position) {
        holder.hashtag.setText(hashtagList.get(position));
    }

    @Override
    public int getItemCount() {
        return hashtagList.size();
    }

    class HashtagViewHolder extends RecyclerView.ViewHolder {
        public TextView hashtag;

        public HashtagViewHolder(View itemView) {
            super(itemView);
            hashtag = itemView.findViewById(R.id.hashtag);
        }
    }


}
