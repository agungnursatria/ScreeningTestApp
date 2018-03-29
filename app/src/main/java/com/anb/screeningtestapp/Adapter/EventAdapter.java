package com.anb.screeningtestapp.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.anb.screeningtestapp.R;
import com.anb.screeningtestapp.model.Event;

import java.util.ArrayList;

/**
 * Created by Agung Nursatria on 3/28/2018.
 */

public class EventAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Event> eventList;

    public EventAdapter(Context context, ArrayList<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);

        ImageView imageEvent = v.findViewById(R.id.imgEvent);
        TextView txtNamaEvent = v.findViewById(R.id.txttNamaEvent);
        TextView txtTglEvent = v.findViewById(R.id.txttTglEvent);
        RecyclerView listhashtag = v.findViewById(R.id.listHashtag);
        TextView txtDescEvent = v.findViewById(R.id.txtDescEvent);

        Event event = eventList.get(position);

        imageEvent.setImageResource(event.image);
        txtNamaEvent.setText(event.nama);
        txtTglEvent.setText(event.tgl);
        txtDescEvent.setText(event.desc);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        HashtagAdapter hashtagAdapter = new HashtagAdapter(context, event.hastag);
        listhashtag.setHasFixedSize(true);
        listhashtag.setLayoutManager(mLayoutManager);
        listhashtag.setAdapter(hashtagAdapter);

        return v;
    }
}
