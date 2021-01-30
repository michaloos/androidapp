package com.example.projekt.Testy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt.ClassDatabase;
import com.example.projekt.R;
import com.example.projekt.EntityClass.SlowkoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Latwy_Test extends AppCompatActivity {

    public int wynik = 0;
    private TextView slowo;
    private Button odp1;
    private Button odp2;
    private Button zakoncz;
    private List<SlowkoModel> list;
    Random random = new Random();
    public int liczba_pytan = 0;
    private int lastOrientation;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latwy__test);

        if(savedInstanceState == null){
            lastOrientation = getResources().getConfiguration().orientation;
        }

        slowo = findViewById(R.id.slowo_test_latwy);
        odp1 = findViewById(R.id.odpowiedz1);
        odp2 = findViewById(R.id.odpowiedz2);
        zakoncz = findViewById(R.id.koniectestlatwy);

        list = new ArrayList<>();
        list = ClassDatabase.getDatabase(getApplicationContext()).getDao().getAllData();

        odp1.setOnClickListener(v -> {
            if(liczba_pytan <= 9) {
                if(odp1.getText().toString().toLowerCase().equals(list.get(i).getTlumaczenie().toLowerCase())){
                    wynik++;
                    i = ustawSlowo(liczba_pytan);
                }else{
                    i = ustawSlowo(liczba_pytan);
                }
                liczba_pytan++;
            }else{
                Toast.makeText(Latwy_Test.this,"To było już ostatnie pytanie",Toast.LENGTH_LONG).show();
            }
        });
        odp2.setOnClickListener(v -> {
            if(liczba_pytan <= 9){
                if(odp2.getText().toString().toLowerCase().equals(list.get(i).getTlumaczenie().toLowerCase())){
                    wynik++;
                    i = ustawSlowo(liczba_pytan);
                }else{
                    i = ustawSlowo(liczba_pytan);
                }
                liczba_pytan++;
            }else{
                Toast.makeText(Latwy_Test.this,"To było już ostatnie pytanie",Toast.LENGTH_LONG).show();
            }
        });


        zakoncz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Latwy_Test.this);
                builder.setMessage(R.string.czy_pewny_koniec_testu).setTitle(R.string.koniec_testu);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Latwy_Test.this,Wynik_Testu.class).putExtra("punkty",wynik));
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Latwy_Test.this,"Test nie został zakończony",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() { }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("wynik",wynik);
        savedInstanceState.putInt("liczba_pytan",liczba_pytan);
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
        wynik = savedInstanceState.getInt("wynik");
        liczba_pytan = savedInstanceState.getInt("liczba_pytan");
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
            i = ustawSlowo(liczba_pytan);
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

    public int ustawSlowo(int liczba_pytan){
        int i = losujSlowo();
        if(liczba_pytan < 9){
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
        }
        return i;
    }
}