package com.anb.screeningtestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.anb.screeningtestapp.Adapter.EventAdapter;
import com.anb.screeningtestapp.model.Event;
import com.google.android.gms.maps.MapFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class EventActivity extends AppCompatActivity {

    public static ArrayList<Event> eventlist = new ArrayList<>();
    ListView listView;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEvent);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.event_list);
        btnBack = findViewById(R.id.btnBack);

        if (eventlist.size() < 4)
            initData();

        EventAdapter eventAdapter = new EventAdapter(EventActivity.this, eventlist);
        listView.setAdapter(eventAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent returnIntent = new Intent();
                TextView txtNamaEvent = view.findViewById(R.id.txttNamaEvent);
                returnIntent.putExtra("namaevent", txtNamaEvent.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_newmedia_event) {
            Intent intent = new Intent(EventActivity.this, MapActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        ArrayList<String> hashtag = new ArrayList<>(Arrays.asList("#nutricia", "#highlight f3"));
        eventlist.add(new Event(R.drawable.makan, "Makan-makan", "Mar 29 2018", hashtag, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...", -6.979860, 107.634260));
        eventlist.add(new Event(R.drawable.travelling, "Wisata ke Tebing Keraton", "Apr 12 2018", hashtag, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...", -6.982376, 107.633742));
        eventlist.add(new Event(R.drawable.study, "Belajar kalkulus bersama", "Apr 15 2018", hashtag, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...", -6.977193, 107.629114));
        eventlist.add(new Event(R.drawable.outbound, "Outbound", "Apr 17 2018", hashtag, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...", -6.972124, 107.633534));
    }
}
