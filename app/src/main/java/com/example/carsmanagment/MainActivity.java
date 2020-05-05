package com.example.carsmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carsmanagment.recyclerview.CarAdapter;
import com.example.carsmanagment.recyclerview.DbHelper;



public class MainActivity extends AppCompatActivity {

    public static final String MAIN_ACTIVITY_CAR_INFO = "com.example.carsmanagment.mainactivity";
    private String[] listOfCars = {"1234", "123456t"};
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //todo Make Recycler View WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        dbHelper = new DbHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.carList);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CarAdapter(this, listOfCars));

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
