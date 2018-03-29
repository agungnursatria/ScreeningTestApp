package com.anb.screeningtestapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anb.screeningtestapp.R;
import com.anb.screeningtestapp.model.Guest;

import java.util.ArrayList;

/**
 * Created by Agung Nursatria on 3/28/2018.
 */

public class GuestAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Guest> guestList;

    public GuestAdapter(Context context, ArrayList<Guest> guestList) {
        this.context = context;
        this.guestList = guestList;
    }

    @Override
    public int getCount() {
        return guestList.size();
    }

    @Override
    public Object getItem(int position) {
        return guestList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.guest_item, parent, false);

        TextView txtGuestName = v.findViewById(R.id.txtGuestName);
        TextView txtGuestBirthday = v.findViewById(R.id.txtGuestBirthday);

        Guest guest = guestList.get(position);

        txtGuestName.setText(guest.name);
        txtGuestBirthday.setText(guest.birthday);

        return v;
    }
}
