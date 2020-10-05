package com.example.carsmanagment;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.carsmanagment.Request.TIME_FIELDS_NAMES;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class TimeAdition extends AppCompatActivity {

    //LocalDate
    TextView TimeButtons[];

    private Integer [] time = {0, 0, 0, 0};
    /*
    * 0 - years
    * 1 - months
    * 2 - days
    * 3 - hours
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_adition);
        TimeButtons = new TextView[4];
        TimeButtons[0] = findViewById(R.id.Time2Y);
        TimeButtons[1] = findViewById(R.id.Time3M);
        TimeButtons[2] = findViewById(R.id.Time7D);
        TimeButtons[3] = findViewById(R.id.Time5H);
        //todo: сделать блять нормально
        TimeButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time[0] += 2;
            }
        });
        TimeButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time[1] += 3;
            }
        });
        TimeButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time[2] += 7;
            }
        });
        TimeButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time[3] += 5;
            }
        });
        //todo: сделать блять нормально
    }

    public void pay(View view){
        //todo: получить данные для RETURN
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(TIME_FIELDS_NAMES.YEARS, time[0].toString());
        bundle.putString(TIME_FIELDS_NAMES.MONTHS, time[1].toString());
        bundle.putString(TIME_FIELDS_NAMES.DAYS, time[2].toString());
        bundle.putString(TIME_FIELDS_NAMES.HOURS, time[3].toString());
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

}
/*
* Intent intent = new Intent(currentActivity, CarManagmentActivity.class);
                    Bundle bundle = car.get_bundle();
                    intent.putExtras(bundle);
                    currentActivity.startActivity(intent);
* */