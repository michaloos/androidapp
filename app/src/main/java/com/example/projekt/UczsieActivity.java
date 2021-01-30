package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.projekt.ActivityUczenieSie.PoziomTrudnosci1;
import com.example.projekt.ActivityUczenieSie.PoziomTrudnosci2;
import com.example.projekt.ActivityUczenieSie.PoziomTrudnosci3;
import com.example.projekt.ActivityUczenieSie.PoziomTrudnosci4;
import com.example.projekt.ActivityUczenieSie.WpisywanieOdpowiedzi;

public class UczsieActivity extends AppCompatActivity {

    private Button zacznij;
    private Switch poziom1;
    private Switch poziom2;
    private Switch poziom3;
    private Switch poziom4;
    private Switch wlasne_odp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uczsie);

        zacznij = findViewById(R.id.zaczynajmy_uczenie);
        poziom1 = findViewById(R.id.poziom_trudnosci_1);
        poziom2 = findViewById(R.id.poziom_trudnosci_2);
        poziom3 = findViewById(R.id.poziom_trudnosci_3);
        poziom4 = findViewById(R.id.poziom_trudnosci_4);
        wlasne_odp = findViewById(R.id.wlasne_odpowiedzi);

        poziom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poziom2.setChecked(false);
                poziom3.setChecked(false);
                poziom4.setChecked(false);
                wlasne_odp.setChecked(false);
            }
        });
        poziom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poziom1.setChecked(false);
                poziom3.setChecked(false);
                poziom4.setChecked(false);
                wlasne_odp.setChecked(false);
            }
        });
        poziom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poziom1.setChecked(false);
                poziom2.setChecked(false);
                poziom4.setChecked(false);
                wlasne_odp.setChecked(false);
            }
        });
        poziom4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poziom1.setChecked(false);
                poziom2.setChecked(false);
                poziom3.setChecked(false);
                wlasne_odp.setChecked(false);
            }
        });
        wlasne_odp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poziom1.setChecked(false);
                poziom2.setChecked(false);
                poziom3.setChecked(false);
                poziom4.setChecked(false);
            }
        });

        zacznij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(poziom1.isChecked()){
                    startActivity(new Intent(UczsieActivity.this, PoziomTrudnosci1.class));
                }
                if(poziom2.isChecked()){
                    startActivity(new Intent(UczsieActivity.this, PoziomTrudnosci2.class));
                }
                if(poziom3.isChecked()){
                    startActivity(new Intent(UczsieActivity.this, PoziomTrudnosci3.class));
                }
                if(poziom4.isChecked()){
                    startActivity(new Intent(UczsieActivity.this, PoziomTrudnosci4.class));
                }
                if(wlasne_odp.isChecked()){
                    startActivity(new Intent(UczsieActivity.this, WpisywanieOdpowiedzi.class));
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        poziom1.setChecked(false);
        poziom2.setChecked(false);
        poziom3.setChecked(false);
        poziom4.setChecked(false);
        wlasne_odp.setChecked(false);
    }
}