package com.example.carsmanagment.Request;

import android.os.Bundle;

import com.example.carsmanagment.R;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class httpCarRequest extends Thread{

    private final URL mUrl;// request URL
    private final int mType;// request Type (assigned automatically depends on constructor)
    private final String mCid; // cid
    private final Listener mListener;// class that must be implemented in order to create a request

    private Bundle reqTime;
    private String response = "";

    //request Type
    //used for checks and code generalising
    private interface Types{
        int getReq = 0;
        int addTime = 1;
    }

    //class that must be implemented in order to create a request
    //onRespond methods calls when request get answer
    public interface Listener{
        void onRespond(String respond);
    }

    // get request constructor
    public httpCarRequest(URL url, String cid, Listener listener){
        mUrl = url;
        mCid = cid;
        mListener = listener;
        mType = Types.getReq;
    }
    //add request constructor
    public httpCarRequest(URL url, String cid, Bundle time, Listener listener){
        mUrl = url;
        mCid = cid;
        mListener = listener;
        reqTime = time;
        mType = Types.addTime;
    }

    @Override
    public void start(){ super.start(); }

    @Override
    public void run() {
        HttpURLConnection urlCon = null;
        try {
            //region connection setup
            urlCon = (HttpURLConnection) mUrl.openConnection();
            urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            urlCon.setRequestMethod("POST");
            //endregion

            //region sending data
            OutputStream writer = new BufferedOutputStream(urlCon.getOutputStream());
            // send data for get time
            if(mType == Types.getReq) writer.write(("cid=" + mCid).getBytes());
            // send data for add Time
            else if(mType == Types.addTime) {
                writer.write((
                        "cid=" + mCid + "&" +
                        TIME_FIELDS_NAMES.YEARS + "=" + reqTime.getString(TIME_FIELDS_NAMES.YEARS) + "&" +
                        TIME_FIELDS_NAMES.MONTHS + "=" + reqTime.getString(TIME_FIELDS_NAMES.MONTHS) + "&" +
                        TIME_FIELDS_NAMES.DAYS + "=" + reqTime.getString(TIME_FIELDS_NAMES.DAYS) + "&" +
                        TIME_FIELDS_NAMES.HOURS + "=" + reqTime.getString(TIME_FIELDS_NAMES.HOURS)
                ).getBytes());
            }
            writer.flush();
            writer.close();
            //endregion

            //region getting response data
            // code from stackoverflow so i don't know whether it fully and correctly works
            int responseCode = urlCon.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
            //endregion

            //region call a user def function that should be defined
            // code repeats some Volley request code but without some thread guard
            //todo: mb add some guard for response
            Listener listener;
            listener = mListener;
            if(listener != null) listener.onRespond(response);
            //endregion

        }
        // if some error has occurred return error massage
        catch (IOException e) {
            Listener listener;
            listener = mListener;
            if(listener != null) listener.onRespond(e.getMessage());
        } catch (Exception e) {
            Listener listener;
            listener = mListener;
            if(listener != null) listener.onRespond(e.getMessage());
        } finally {
            //close our connection and exit the thread
            urlCon.disconnect();
            return;
        }
    }
}
