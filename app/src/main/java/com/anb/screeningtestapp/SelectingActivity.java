package com.anb.screeningtestapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectingActivity extends AppCompatActivity {

    TextView txtNama;
    Button btnPilihEvent,btnPilihGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecting);

        txtNama = findViewById(R.id.txtNama);
        btnPilihEvent = findViewById(R.id.btnPilihEvent);
        btnPilihGuest = findViewById(R.id.btnPilihGuest);

        Bundle extras = getIntent().getExtras();
        txtNama.setText(extras.getString("nama"));

        btnPilihEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectingActivity.this, EventActivity.class);
                startActivityForResult(intent,1);
            }
        });

        btnPilihGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectingActivity.this, GuestActivity.class);
                startActivityForResult(intent,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                btnPilihEvent.setText(data.getStringExtra("namaevent"));
            }
        }
        else if (requestCode == 2){
            if (resultCode == Activity.RESULT_OK){
                btnPilihGuest.setText(data.getStringExtra("namaguest"));

                String dateString = data.getStringExtra("birthdayguest");
                jenisOS(dateString);

                isMonthPrime(dateString);
            }
        }
    }

    public void jenisOS(String dateString){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            int startDate = (int) (date.getTime()/1000);

            String os;
            if (startDate % 2 == 0  && startDate % 3 == 0 ){
                os = "iOS";
            }
            else if (startDate % 3 == 0){
                os = "android";
            }
            else if (startDate % 2 == 0){
                os = "blackberry";
            }
            else
            {
                os = "feature phone";
            }

            Toast.makeText(this, os, Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void isMonthPrime(String dateString){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            int month = Integer.valueOf((String) DateFormat.format("MM",date));

            int counter = 0;
            for (int i=2;i<=month;i++){
                if (month % i == 0)
                    counter++;
            }
            String prime = (counter == 1)? "Bulan "+ month +" merupakan bilangan prima" : "Bulan "+ month +" bukan bilangan prima";
            Toast.makeText(this, prime, Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
