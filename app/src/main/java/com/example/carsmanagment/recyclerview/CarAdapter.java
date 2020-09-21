package com.example.carsmanagment.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsmanagment.Car;
import com.example.carsmanagment.CarManagmentActivity;
import com.example.carsmanagment.MainActivity;
import com.example.carsmanagment.R;
import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    private static Context currentActivity;
    private ArrayList<Car> cars;

    public CarAdapter(Context currentActivity, ArrayList<Car> cars){
        this.currentActivity = currentActivity;
        this.cars = cars;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView carName;
        private TextView carDetail;
        private Car car;
        public ViewHolder(@NonNull LinearLayout itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(currentActivity, CarManagmentActivity.class);
                    Bundle bundle = car.get_bundle();
                    intent.putExtras(bundle);
                    currentActivity.startActivity(intent);
                }
            });
            carName = (TextView) itemView.getChildAt(0);
            carDetail = (TextView) itemView.getChildAt(1);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.car = cars.get(position);
        holder.carName.setText(cars.get(position).name);
        holder.carDetail.setText(cars.get(position).detail);
    }

    @Override
    public int getItemCount() { return cars.size(); }

}
