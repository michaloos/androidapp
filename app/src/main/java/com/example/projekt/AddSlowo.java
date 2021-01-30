package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projekt.EntityClass.SlowkoModel;

public class AddSlowo extends AppCompatActivity {

    private EditText slowo_text;
    private EditText tlumaczenie_text;
    private Button button_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_slowo);

        slowo_text = findViewById(R.id.slowo_edittext);
        tlumaczenie_text = findViewById(R.id.tlumaczenie_edittext);
        button_save = findViewById(R.id.button_save);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String slowo = slowo_text.getText().toString().trim();
                String tlumaczenie = tlumaczenie_text.getText().toString().trim();

                SlowkoModel slowkoModel = new SlowkoModel();
                slowkoModel.setSlowo(slowo);
                slowkoModel.setTlumaczenie(tlumaczenie);

                ClassDatabase.getDatabase(getApplicationContext()).getDao().insertAllData(slowkoModel);

                SlowkoModel slowkoModel1 = new SlowkoModel();
                slowkoModel1.setSlowo(tlumaczenie);
                slowkoModel1.setTlumaczenie(slowo);

                ClassDatabase.getDatabase((getApplicationContext())).getDao().insertAllData(slowkoModel1);

                slowo_text.setText("");
                tlumaczenie_text.setText("");
                Toast.makeText(AddSlowo.this,"Slowo pomyślnie dodane", Toast.LENGTH_SHORT).show();
            }
        });

    }
}