package com.example.carsmanagment;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class TimeAdition extends AppCompatActivity {

    //LocalDate
    TextView TimeButtons[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_adition);
        TimeButtons = new TextView[4];
        //todo: сделать блять нормально
        /*TimeButtons[0].setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(additionalTime != null){

                }
            }
        });*/
        //todo: сделать блять нормально
    }

    public void pay(View view){
        //todo: получить данные для RETURN
        Intent intent = new Intent();
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