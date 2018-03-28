package com.anb.screeningtestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.anb.screeningtestapp.Adapter.EventAdapter;
import com.anb.screeningtestapp.model.Event;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    ArrayList<Event> eventlist = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEvent);
        setSupportActionBar(toolbar);

        initData();

        EventAdapter eventAdapter = new EventAdapter(this,eventlist);
        listView = findViewById(R.id.event_list);
        listView.setAdapter(eventAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent returnIntent = new Intent();
                TextView txtNamaEvent = view.findViewById(R.id.txtNamaEvent);
                returnIntent.putExtra("namaevent",txtNamaEvent.getText().toString());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }


    private void initData(){
        eventlist.add(new Event(R.drawable.makan, "Makan-makan", "2018-04-01"));
        eventlist.add(new Event(R.drawable.travelling, "Wisata ke Tebing Keraton", "2018-04-02"));
        eventlist.add(new Event(R.drawable.study, "Belajar kalkulus bersama", "2018-04-03"));
        eventlist.add(new Event(R.drawable.outbound, "Outbound", "2018-04-05"));
    }
}
