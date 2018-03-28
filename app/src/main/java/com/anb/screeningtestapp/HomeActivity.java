package com.anb.screeningtestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final EditText edtNama = findViewById(R.id.edtNama);
        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtNama.getText().toString().equals("")){
                    Intent intent = new Intent(HomeActivity.this,SelectingActivity.class);
                    intent.putExtra("nama", edtNama.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
