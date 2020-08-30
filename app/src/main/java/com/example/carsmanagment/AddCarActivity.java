package com.example.carsmanagment;

import androidx.appcompat.app.AppCompatActivity;

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

    public void addCar(View view){
        //todo: проверка на совподение в ДБ 
        Intent intent = new Intent(this, MainActivity.class);
        EditText name = findViewById(R.id.nameEdit);
        EditText detail = findViewById(R.id.detailsEdit);
        dbHelper.insertCar(new Car(name.getText().toString(), detail.getText().toString()));
        startActivity(intent);
    }
}
