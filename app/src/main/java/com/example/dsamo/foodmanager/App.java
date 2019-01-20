package com.example.dsamo.foodmanager;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.example.dsamo.foodmanager.models.database.DB.Database;
import com.example.dsamo.foodmanager.models.database.entity.PMeasurement;

import java.util.concurrent.Executors;

public class App extends Application {
    public static App instance;

    private Database db;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RoomDatabase.Callback setStartPopulate = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        getDatabase().populateData();
                    }
                });
            }
        };
        db = Room.databaseBuilder(this, Database.class, "product_manager").addCallback(setStartPopulate).allowMainThreadQueries().build();
        db.daoInterfacePMeasurement().getAll();
    }

    public static App getInstance(){return instance;}
    public Database getDatabase(){return db;}


}
