package com.example.carsmanagment.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.carsmanagment.Car;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Cars";
    private static final int DB_VER = 1;
    private static final String DB_TABLE = "Car";
    public static final String DB_COLUMN1 = "CarName";
    public static final String DB_COLUMN2 = "CarDetail";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL);",
                DB_TABLE, DB_COLUMN1, DB_COLUMN2);
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format("DELETE TABLE IF EXISTS %s", DB_TABLE);
        db.execSQL(query);
        onCreate(db);
    }

    public void insertCar(Car car){
        //todo: проверка уникальности detail
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(DB_COLUMN1, car.name);
        value.put(DB_COLUMN2, car.detail);
        db.insertWithOnConflict(DB_TABLE, null, value, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteCar(Car car){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE, DB_COLUMN2 + " = ?", new String[]{car.detail});
        db.close();
    }

    public ArrayList<Car> getCarsLIst(){
        ArrayList<Car> cars = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN1, DB_COLUMN2},null,null,null,null,null);
        while(cursor.moveToNext()){
            int nameIndex = cursor.getColumnIndex(DB_COLUMN1);
            int detailIndex = cursor.getColumnIndex(DB_COLUMN2);
            String carName = cursor.getString(nameIndex);
            String carDeatail = cursor.getString(detailIndex);
            cars.add(new Car(carName, carDeatail));
        }
        cursor.close();
        db.close();
        return cars;
    }
}
