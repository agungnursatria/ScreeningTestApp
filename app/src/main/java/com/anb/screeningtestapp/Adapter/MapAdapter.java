package com.anb.screeningtestapp.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anb.screeningtestapp.R;
import com.anb.screeningtestapp.model.Event;

import java.util.ArrayList;

/**
 * Created by Agung Nursatria on 3/29/2018.
 */

public class MapAdapter extends PagerAdapter {

    public ArrayList<Event> eventlist;
    LayoutInflater mLayoutInflater;

    public MapAdapter(Context context, ArrayList<Event> eventlist) {
        this.eventlist = eventlist;
        mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return eventlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = mLayoutInflater.inflate(R.layout.map_item, container, false);

        ImageView imgMaps = v.findViewById(R.id.imgMaps);
        TextView txtMapsName = v.findViewById(R.id.txtMapsName);

        imgMaps.setImageResource(eventlist.get(position).image);
        txtMapsName.setText(eventlist.get(position).nama);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
