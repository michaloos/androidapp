package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    private EditText slowo;
    private EditText tlumaczenie;
    private Button button_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        slowo = findViewById(R.id.slowo_edittext);
        tlumaczenie = findViewById(R.id.tlumaczenie_edittext);
        button_update = findViewById(R.id.button_update);

        slowo.setText(getIntent().getExtras().getString("slowo"));
        tlumaczenie.setText(getIntent().getExtras().getString("tlumaczenie"));
        final String id = getIntent().getExtras().getString("id");

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String slowotxt = slowo.getText().toString().trim();
                String tlumaczenietxt = tlumaczenie.getText().toString().trim();
                if(slowotxt.equals("") || tlumaczenietxt.equals("")){
                    Toast.makeText(UpdateData.this,"Zapomniałeś coś uzupełnić",Toast.LENGTH_SHORT).show();
                }else{
                    ClassDatabase.getDatabase(getApplicationContext()).getDao().updateData(slowotxt,tlumaczenietxt,Integer.parseInt(id));
                    finish();
                }
            }
        });
    }
}