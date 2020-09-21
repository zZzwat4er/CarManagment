package com.example.carsmanagment;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carsmanagment.recyclerview.DbHelper;

import java.util.Calendar;
import java.util.Date;

import static com.example.carsmanagment.MainActivity.MAIN_ACTIVITY_CAR_INFO;

public class CarManagmentActivity extends AppCompatActivity {

    private Car currentCar;
    private DbHelper dbHelper;
    private final int TIME_ADDITION_ID = 10;
    private Calendar paidTill = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_managment);
        Intent intent = getIntent();
        currentCar = new Car(intent.getExtras());
        dbHelper = new DbHelper(this);
        TextView carName = findViewById(R.id.carName);
        TextView cardID = findViewById(R.id.cardID);
        TextView paidTime = findViewById(R.id.paidTill);
        carName.setText(currentCar.name);

        //todo: POST запрос на получение данных

        cardID.setText(getString(R.string.card) + " " + currentCar.detail);
        paidTime.setText(getString(R.string.paid_till) + " " + paidTill.get(Calendar.HOUR_OF_DAY) +
                ":" + paidTill.get(Calendar.MINUTE) + " " + paidTill.get(Calendar.DATE) + "." +
                paidTill.get(Calendar.MONTH) + "." + paidTill.get(Calendar.YEAR));
    }

    public void payButton(View view){
        Intent intent = new Intent(this, TimeAdition.class);
        startActivityForResult(intent, TIME_ADDITION_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TIME_ADDITION_ID){
            if(resultCode == RESULT_OK){
                //todo: POST запрос на добовленние данных + апдейт страници
            }
        }
    }
}
