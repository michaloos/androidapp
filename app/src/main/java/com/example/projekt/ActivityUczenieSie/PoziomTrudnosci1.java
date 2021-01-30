package com.example.projekt.ActivityUczenieSie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt.ClassDatabase;
import com.example.projekt.R;
import com.example.projekt.EntityClass.SlowkoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class PoziomTrudnosci1 extends AppCompatActivity {

    private TextView slowo;
    private Button odp1;
    private Button odp2;
    private List<SlowkoModel> list;
    Random random = new Random();
    private int i;
    private ImageButton speaker;
    private int lastOrientation;

    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poziom_trudnosci1);

        if(savedInstanceState == null){
            lastOrientation = getResources().getConfiguration().orientation;
        }

        slowo = findViewById(R.id.slowo_poz1);
        odp1 = findViewById(R.id.odpowiedz1);
        odp2 = findViewById(R.id.odpowiedz2);
        speaker = findViewById(R.id.speaker);

        list = new ArrayList<>();
        list = ClassDatabase.getDatabase(getApplicationContext()).getDao().getAllData();

        odp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(odp1.getText().toString().toLowerCase().equals(list.get(i).getTlumaczenie().toLowerCase())){
                    Toast.makeText(PoziomTrudnosci1.this,"Dobra odpowiedź!", Toast.LENGTH_LONG).show();
                    i = ustawSlowo();
                }else{
                    Toast.makeText(PoziomTrudnosci1.this,"Zła odpowiedź!", Toast.LENGTH_LONG).show();
                }
            }
        });
        odp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(odp2.getText().toString().toLowerCase().equals(list.get(i).getTlumaczenie().toLowerCase())){
                    Toast.makeText(PoziomTrudnosci1.this,"Dobra odpowiedź!", Toast.LENGTH_LONG).show();
                    i = ustawSlowo();
                }else{
                    Toast.makeText(PoziomTrudnosci1.this,"Zła odpowiedź!", Toast.LENGTH_LONG).show();
                }
            }
        });

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(i == TextToSpeech.SUCCESS){
                    int language = textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = slowo.getText().toString().trim();
                int speech = textToSpeech.speak(word,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("i",i);
        savedInstanceState.putString("slowo",slowo.getText().toString());
        savedInstanceState.putString("odp1",odp1.getText().toString());
        savedInstanceState.putString("odp2",odp2.getText().toString());
        savedInstanceState.putInt("orientation",lastOrientation);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        i = savedInstanceState.getInt("i");
        slowo.setText(savedInstanceState.getString("slowo"));
        odp1.setText(savedInstanceState.getString("odp1"));
        odp2.setText(savedInstanceState.getString("odp2"));
        lastOrientation = savedInstanceState.getInt("orientation");
    }

    @Override
    protected void onStart(){
        super.onStart();
        checkOrientationChanged();
    }

    private void checkOrientationChanged(){
        int currentOrientation = getResources().getConfiguration().orientation;
        if(currentOrientation != lastOrientation){
            lastOrientation = currentOrientation;
        }else{
            i = ustawSlowo();
        }
    }

    public int innaliczba(int i){
        int x = random.nextInt(3);
        while(i == x){
            x = random.nextInt(3);
        }
        return x;
    }

    public int losujSlowo(){
        int i;
        i = random.nextInt(list.size());
        return i;
    }

    public int ustawSlowo(){
        int i = losujSlowo();
        slowo.setText(list.get(i).getSlowo());
        boolean odp;
        int x;
        x = random.nextInt(list.size());
        odp = random.nextBoolean();
        if(odp){
            odp1.setText(list.get(i).getTlumaczenie());
            if(x == i){
                x = innaliczba(i);
            }
            odp2.setText(list.get(x).getTlumaczenie());
        }else{
            odp2.setText(list.get(i).getTlumaczenie());
            if(x == i){
                x = innaliczba(i);
            }
            odp1.setText(list.get(x).getTlumaczenie());
        }
        return i;
    }
}