package com.example.carsmanagment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsmanagment.recyclerview.CarAdapter;
import com.example.carsmanagment.recyclerview.DbHelper;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static final String MAIN_ACTIVITY_CAR_INFO = "com.example.carsmanagment.mainactivity";
    private ArrayList<Car> listOfCars;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        //for(int i = 0; i < listOfCars.size(); i++) dbHelper.insertCar(listOfCars.get(i));
        recyclerView = (RecyclerView) findViewById(R.id.carList);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CarAdapter(this, dbHelper.getCarsLIst()));


    }


    public void addNewCar(View view){
        Intent intent = new Intent(this, AddCarActivity.class);
        startActivity(intent);
    }

    public void goToCarManage(View view){
        Intent intent = new Intent(this, CarManagmentActivity.class);
        intent.putExtra(MAIN_ACTIVITY_CAR_INFO, view.getId());
        startActivity(intent);
    }
}
