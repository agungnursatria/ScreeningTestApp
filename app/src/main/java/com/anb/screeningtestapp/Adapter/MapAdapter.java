package com.anb.screeningtestapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anb.screeningtestapp.R;
import com.anb.screeningtestapp.model.Event;

import java.util.ArrayList;

/**
 * Created by Agung Nursatria on 3/29/2018.
 */

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.MapViewHolder> {

    public Context context;
    public ArrayList<Event> eventlist;

    public MapAdapter(Context context, ArrayList<Event> eventlist) {
        this.context = context;
        this.eventlist = eventlist;
    }

    @Override
    public MapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.maps_item, parent, false);
        return new MapViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MapViewHolder holder, int position) {
        holder.txtMapsName.setText(eventlist.get(position).nama);
        holder.imgMap.setImageResource(eventlist.get(position).image);
    }

    @Override
    public int getItemCount() {
        return eventlist.size();
    }


    class MapViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgMap;
        public TextView txtMapsName;

        public MapViewHolder(View itemView) {
            super(itemView);
            imgMap = itemView.findViewById(R.id.imgMaps);
            txtMapsName = itemView.findViewById(R.id.txtMapsName);
        }
    }

}
