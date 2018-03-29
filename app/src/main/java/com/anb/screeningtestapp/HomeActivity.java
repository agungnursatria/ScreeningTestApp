package com.anb.screeningtestapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final EditText edtNama = findViewById(R.id.edtNama);
        FrameLayout btnSelesai = findViewById(R.id.btnSelesai);

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nama = edtNama.getText().toString();
                if (!nama.equals("")) {
                    boolean palindromCheck = isPalindrom(nama);
                    String palindromWord = palindromCheck ? "isPalindrom" : "not palindrome";

                    AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                    builder.setTitle(String.valueOf(palindromCheck).toUpperCase())
                            .setMessage(nama + " " + palindromWord)
                            .setCancelable(false)
                            .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    Intent intent = new Intent(HomeActivity.this, SelectingActivity.class);
                                    intent.putExtra("nama", nama);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                    Button buttonPositive = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                    buttonPositive.setTextColor(Color.parseColor("#000000"));
                }
            }
        });
    }

    private boolean isPalindrom(String sentence) {
        sentence = sentence.replaceAll("\\s", "");
        Log.d("Palindrom Check", sentence);
        int top = sentence.length() - 1;
        int bottom = 0;

        while (top >= bottom && sentence.charAt(bottom) == sentence.charAt(top)) {
            Log.d("Palindrom Check", "Senternce[top]: " + sentence.charAt(top) + "\n"
                    + "Senternce[bottom]: " + sentence.charAt(bottom) + "\n"
                    + "top: " + top + "\n"
                    + "bottom: " + bottom + "\n"
                    + "==================================================== \n \n"
            );
            top--;
            bottom++;
        }
        return (top < bottom);
    }
}
