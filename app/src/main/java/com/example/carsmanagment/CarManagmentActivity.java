package com.example.carsmanagment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.carsmanagment.Request.TIME_FIELDS_NAMES;
import com.example.carsmanagment.Request.httpCarRequest;
import com.example.carsmanagment.recyclerview.DbHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class CarManagmentActivity extends AppCompatActivity {

    private Car currentCar;
    private final int TIME_ADDITION_ID = 10; // id for time addition intent
    private URL url; // url whether we well send req
    private TextView paidTime;

    //Я ебан по этому все ощибки ваыводятся в paidTime view
    //лучше в будующем это заменить на всплывающие окта или просто скрыть и отсылать кудуто хз


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // region initialisation processes
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_managment);
        Intent intent = getIntent();
        currentCar = new Car(intent.getExtras());
        TextView carName = findViewById(R.id.carName);
        TextView cardID = findViewById(R.id.cardID);
        paidTime = findViewById(R.id.paidTill);
        carName.setText(currentCar.name);
        cardID.setText(getString(R.string.card) + " " + currentCar.detail);
        // endregion
        // trying to set url to url
        try{
            url = new URL("http://31.210.210.172/Parking/mobile_app_get_time_left.php");
        }catch(MalformedURLException e){
            paidTime.setText(e.getMessage());
        }
        // making http req
        httpCarRequest req = new httpCarRequest(
                url,
                currentCar.detail,
                new httpCarRequest.Listener() {
                    @Override
                    public void onRespond(String respond) {
                        // write here actions that mast happen after respond was gotten
                        try {
                            JSONObject obj = new JSONObject(respond.substring(1, respond.length() - 1));
                            paidTime.setText(getString(R.string.paid_till) +
                                    obj.getString(TIME_FIELDS_NAMES.HOURS) + "H " +
                                    obj.getString(TIME_FIELDS_NAMES.DAYS) + "." +
                                    obj.getString(TIME_FIELDS_NAMES.MONTHS) + "." +
                                    obj.getString(TIME_FIELDS_NAMES.YEARS)
                            );
                        }catch (JSONException e){
                            paidTime.setText("JSON fucked up: " + e.getMessage());
                        }
                    }
                });
        //httpCarRequest actually thread so we need to start it
        //btw there is no any defense from thread abuse so we need to add it in a future
        req.start();


    }
    // starting TimeAddition activity
    public void payButton(View view){
        Intent intent = new Intent(this, TimeAdition.class);
        startActivityForResult(intent, TIME_ADDITION_ID);
    }

    @Override
    // reaction on activity result
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TIME_ADDITION_ID){
            if(resultCode == RESULT_OK){
                // send a req with addition time
                try{
                    url = new URL("http://31.210.210.172/Parking/mobile_app_add_time.php");
                }catch(MalformedURLException e){
                    paidTime.setText(e.getMessage());
                    url = null;
                }
                if(url != null && data != null){
                    httpCarRequest req = new httpCarRequest(
                            url,
                            currentCar.detail,
                            data.getExtras(),
                            new httpCarRequest.Listener() {
                                @Override
                                public void onRespond(String respond) {
                                }
                            });
                    req.start();
                }
            }
        }
    }
}
