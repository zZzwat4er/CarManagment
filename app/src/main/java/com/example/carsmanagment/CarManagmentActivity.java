package com.example.carsmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.carsmanagment.recyclerview.DbHelper;

import static com.example.carsmanagment.MainActivity.MAIN_ACTIVITY_CAR_INFO;

public class CarManagmentActivity extends AppCompatActivity {

    private Car currentCar;
    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_managment);

        Intent intent = getIntent();
        currentCar = new Car(intent.getExtras());
        dbHelper = new DbHelper(this);
    }

    public void deleteButton(View view){
        //todo: исправить возможную ошибку
        Intent intent = new Intent(this, MainActivity.class);
        dbHelper.deleteCar(currentCar);
        startActivity(intent);
    }

    public void openButton(View view){
        //todo:(openButton) put here response to server
    }
}
