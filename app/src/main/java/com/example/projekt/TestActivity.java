package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.projekt.EntityClass.SlowkoModel;
import com.example.projekt.Testy.Latwy_Test;
import com.example.projekt.Testy.Sredni_Test;
import com.example.projekt.Testy.Trudny_Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    private Button start_test;
    private Switch latwy_test;
    private Switch sredni_test;
    private Switch trudny_test;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeService mShakeService;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeService = new ShakeService();
        mShakeService.setOnShakeListener(new ShakeService.OnShakeListener() {
            @Override
            public void onShake(int count) {
                losujTest();
            }
        });

        start_test = findViewById(R.id.Zacznij_test);
        latwy_test = findViewById(R.id.test_poziom1);
        sredni_test = findViewById(R.id.test_poziom2);
        trudny_test = findViewById(R.id.test_poziom3);

        latwy_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sredni_test.setChecked(false);
                trudny_test.setChecked(false);
            }
        });
        sredni_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latwy_test.setChecked(false);
                trudny_test.setChecked(false);
            }
        });
        trudny_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latwy_test.setChecked(false);
                sredni_test.setChecked(false);
            }
        });
        start_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(latwy_test.isChecked()){
                    startActivity(new Intent(TestActivity.this, Latwy_Test.class));
                }
                if(sredni_test.isChecked()){
                    startActivity(new Intent(TestActivity.this, Sredni_Test.class));
                }
                if(trudny_test.isChecked()){
                    startActivity(new Intent(TestActivity.this, Trudny_Test.class));
                }
            }
        });
    }

    private void losujTest(){
        int i = random.nextInt(3);
        switch (i){
            case 0:
                startActivity(new Intent(TestActivity.this, Latwy_Test.class));
                break;
            case 1:
                startActivity(new Intent(TestActivity.this, Sredni_Test.class));
                break;
            case 2:
                startActivity(new Intent(TestActivity.this, Trudny_Test.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //rejestracja odbiornika przy ponownym uruchomieniu aktywności
        mSensorManager.registerListener(mShakeService,mAccelerometer,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        //wyrejestrowanie czujników przy pausowaniu
        mSensorManager.unregisterListener(mShakeService);
        super.onPause();
    }
}