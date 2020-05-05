package com.example.carsmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
    }

    public void addCar(View view){
        //todo realise addition to list of cars
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
