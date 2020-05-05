package com.example.carsmanagment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import static com.example.carsmanagment.MainActivity.MAIN_ACTIVITY_CAR_INFO;

public class CarManagmentActivity extends AppCompatActivity {

    private Integer currentCarID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_managment);

        Intent intent = getIntent();
        currentCarID = intent.getIntExtra(MAIN_ACTIVITY_CAR_INFO, -1);
    }

    public void deleteButton(View view){
        //todo:(deleteButton) realize deletion
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openButton(View view){
        //todo:(openButton) put here response to server
    }
}
