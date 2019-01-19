package com.example.dsamo.foodmanager.models.database.DB;

import android.app.Application;
import android.arch.persistence.room.Room;

public class App extends Application {
    public static App instance;

    private Database db;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(this, Database.class, "Database").build();
    }

    public static App getInstance(){return instance;}
    public Database getDatabase(){return db;}
}
