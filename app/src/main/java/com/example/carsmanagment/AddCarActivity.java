package com.example.carsmanagment;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.carsmanagment.recyclerview.DbHelper;

public class AddCarActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        dbHelper = new DbHelper(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void addCar(View view){
        //todo: проверка на совподение в ДБ
        EditText name = findViewById(R.id.name);
        EditText cardID = findViewById(R.id.cardID);
        EditText cardIDRepeat = findViewById(R.id.cardIDRepeat);
        if (!cardID.getText().toString().isEmpty() && !cardIDRepeat.getText().toString().isEmpty()) {
            if (Integer.parseInt(cardID.getText().toString()) == Integer.parseInt(cardIDRepeat.getText().toString())) {
                Car car = new Car(name.getText().toString(), cardID.getText().toString());
                Intent intent = new Intent();
                intent.putExtras(car.get_bundle());
                setResult(RESULT_OK, intent);
                finish();
            }
            else{
                //todo: всплывающее окно о том что cardID и cardIDRepeat рaзные
            }
        }
        else{
            //todo: всплывающее окно при отсутствии cardID
        }
    }
}