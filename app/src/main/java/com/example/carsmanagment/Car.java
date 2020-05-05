package com.example.carsmanagment;

import android.os.Bundle;

public class Car {
    public String name = "";
    public String detail = "";

    public Car(String name, String detail){
        this.name = name;
        this.detail = detail;
    }

    //region Bundle
    private static String BUNDLE_NAME = "name";
    private static String BUNDLE_DETAIL = "detail";

    public Car(Bundle bundle){
        this.name = bundle.getString(BUNDLE_NAME);
        this.name = bundle.getString(BUNDLE_NAME);
    }

    public Bundle get_bundle(){
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_NAME, this.name);
        bundle.putString(BUNDLE_DETAIL, this.detail);

        return bundle;
    }

    //endregion Bundle
}
