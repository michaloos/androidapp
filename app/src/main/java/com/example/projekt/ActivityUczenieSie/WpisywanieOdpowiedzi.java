package com.example.projekt.ActivityUczenieSie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class WpisywanieOdpowiedzi extends AppCompatActivity {

    private Button nastepne;
    private Button sprawdz;
    private TextView slowo;
    private EditText editslowo;
    private List<SlowkoModel> list;
    Random random = new Random();
    private int i;
    private ImageButton speaker;
    TextToSpeech textToSpeech;
    private int lastOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wpisywanie_odpowiedzi);

        if(savedInstanceState == null){
            lastOrientation = getResources().getConfiguration().orientation;
        }

        nastepne = findViewById(R.id.button_nastepne_slowo_wypisywanie);
        sprawdz = findViewById(R.id.sprawdz_wypisywanie);
        slowo = findViewById(R.id.slowo_wpisywanie);
        editslowo = findViewById(R.id.edit_slowo);
        speaker = findViewById(R.id.speaker);

        list = new ArrayList<>();
        list = ClassDatabase.getDatabase(getApplicationContext()).getDao().getAllData();

        i = random.nextInt(list.size());
        String slowko = list.get(i).getSlowo();
        slowo.setText(slowko);
        sprawdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = editslowo.getText().toString().trim();
                if(s1.equals(list.get(i).getTlumaczenie())){
                    Toast.makeText(WpisywanieOdpowiedzi.this,"Dobra odpowiedź",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(WpisywanieOdpowiedzi.this,"Zła odpowiedź",Toast.LENGTH_SHORT).show();
                }
            }
        });

        nastepne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = wylosujslowo();
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

    public int wylosujslowo(){
        i = random.nextInt(list.size());
        slowo.setText(list.get(i).getSlowo());
        editslowo.setText("");
        return i;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("i",i);
        savedInstanceState.putString("slowo",slowo.getText().toString());
        savedInstanceState.putInt("orientation",lastOrientation);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        i = savedInstanceState.getInt("i");
        slowo.setText(savedInstanceState.getString("slowo"));
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
            i = wylosujslowo();
        }
    }

}