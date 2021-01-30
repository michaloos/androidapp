package com.example.projekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projekt.ActivityUczenieSie.PoziomTrudnosci1;
import com.google.android.material.badge.BadgeUtils;

public class MenuActivity extends AppCompatActivity {

    private Button button_test;
    private Button button_ucz_sie;
    private Button button_slowa;
    private Button button_dodaj_slowo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button_test = findViewById(R.id.button_test);
        button_ucz_sie = findViewById(R.id.button_ucz_sie);
        button_slowa = findViewById(R.id.button_slowa);
        button_dodaj_slowo = findViewById(R.id.button_dodaj_slowo);

        button_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, TestActivity.class));
            }
        });

        button_ucz_sie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, UczsieActivity.class));
            }
        });

        button_slowa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),GetData.class));
            }
        });

        button_dodaj_slowo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, AddSlowo.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.slownik_menu:
                String urlslownik = "https://www.diki.pl";
                try{
                    Intent slownikIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(urlslownik));
                    startActivity(slownikIntent);
                }catch (ActivityNotFoundException e){}
                return true;
            case R.id.youtube_menu:
                String urlYoutube = "https://www.youtube.com/watch?v=ToRnkFlsK9s";
                Intent youtubeIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(urlYoutube));
                try{
                    MenuActivity.this.startActivity(youtubeIntent);
                }catch (ActivityNotFoundException e){}
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }
}