package com.example.projekt.Testy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt.ClassDatabase;
import com.example.projekt.R;
import com.example.projekt.EntityClass.SlowkoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trudny_Test extends AppCompatActivity {

    private Button koniec;
    private Button nastepne;
    private TextView slowo;
    private EditText editslowo;
    private List<SlowkoModel> list;
    Random random = new Random();
    private int wynik = 0;
    private int liczba_pytan = 0;
    private int i;
    private int lastOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trudny__test);

        if(savedInstanceState == null){
            lastOrientation = getResources().getConfiguration().orientation;
        }

        koniec = findViewById(R.id.koniectesttrudny);
        nastepne = findViewById(R.id.button_nastepne_slowo_test_trudny);
        slowo = findViewById(R.id.slowo_wpisywanie);
        editslowo = findViewById(R.id.edit_slowo);

        list = new ArrayList<>();
        list = ClassDatabase.getDatabase(getApplicationContext()).getDao().getAllData();

        i = random.nextInt(list.size());
        String slowko = list.get(i).getSlowo();
        slowo.setText(slowko);

        nastepne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(liczba_pytan <= 19){
                    String s1 = editslowo.getText().toString().trim();
                    if(s1.equals(list.get(i).getTlumaczenie())){
                        wynik++;
                    }
                    liczba_pytan++;
                    i = wylosujslowo();
                }else{
                    Toast.makeText(Trudny_Test.this,"To było już ostatnie pytanie",Toast.LENGTH_LONG).show();
                }
            }
        });
        koniec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Trudny_Test.this);
                builder.setMessage(R.string.czy_pewny_koniec_testu).setTitle(R.string.koniec_testu);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Trudny_Test.this,Wynik_Testu.class).putExtra("punkty",wynik));
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Trudny_Test.this,"Test nie został zakończony",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() { }

    public int wylosujslowo(){
        i = random.nextInt(list.size());
        slowo.setText(list.get(i).getSlowo());
        editslowo.setText("");
        return i;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("wynik",wynik);
        savedInstanceState.putInt("liczba_pytan",liczba_pytan);
        savedInstanceState.putInt("i",i);
        savedInstanceState.putString("slowo",slowo.getText().toString());
        savedInstanceState.putInt("orientation",lastOrientation);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        i = savedInstanceState.getInt("i");
        wynik = savedInstanceState.getInt("wynik");
        liczba_pytan = savedInstanceState.getInt("liczba_pytan");
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