package com.example.carsmanagment.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsmanagment.CarManagmentActivity;
import com.example.carsmanagment.MainActivity;
import com.example.carsmanagment.R;
import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    private static Context currentActivity;
    String[] cars;

    public CarAdapter(Context currentActivity, String[] cars){
        this.currentActivity = currentActivity;
        this.cars = cars;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(@NonNull LinearLayout itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(currentActivity, CarManagmentActivity.class);
                    currentActivity.startActivity(intent);

                }
            });
            textView = (TextView) itemView.getChildAt(0);
        }
    }

    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { holder.textView.setText(cars[position]); }

    @Override
    public int getItemCount() { return cars.length; }

}
