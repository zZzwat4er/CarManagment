package com.example.carsmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carsmanagment.recyclerview.CarAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static final String MAIN_ACTIVITY_CAR_INFO = "com.example.carsmanagment.mainactivity";

    private String[] listOfCars = {"1234567", "asd as"};
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            //todo Make Recycler View WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            recyclerView = (RecyclerView) findViewById(R.id.carList);
            recyclerView.setHasFixedSize(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            //recyclerView.setAdapter(new CarAdapter(listOfCars));
        }
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
