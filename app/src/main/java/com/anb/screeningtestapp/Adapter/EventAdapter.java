package com.anb.screeningtestapp.Adapter;

import android.content.Context;
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
 * Created by Agung Nursatria on 3/28/2018.
 */

public class EventAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Event> eventList;

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

        ImageView imageEvent = v.findViewById(R.id.imageEvent);
        TextView txtNamaEvent = v.findViewById(R.id.txtNamaEvent);
        TextView txtTglEvent = v.findViewById(R.id.txtTglEvent);

        Event event = eventList.get(position);

        imageEvent.setImageResource(event.image);
        txtNamaEvent.setText(event.nama);
        txtTglEvent.setText(event.tgl);

        return v;
    }
}
