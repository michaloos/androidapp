package com.example.projekt.Testy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projekt.MenuActivity;
import com.example.projekt.R;

public class Wynik_Testu extends AppCompatActivity {

    private TextView wyniktestu;
    private Button zakoncz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynik__testu);
        Intent intent = getIntent();
        int wynik = intent.getIntExtra("punkty",0);
        zakoncz = findViewById(R.id.wroc_do_testow);
        wyniktestu = findViewById(R.id.wynik_testu);

        wyniktestu.setText(String.valueOf(wynik));
        zakoncz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Wynik_Testu.this, MenuActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }

    @Override
    public void onBackPressed() { }
}