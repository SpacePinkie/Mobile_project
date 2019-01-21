package com.example.dsamo.foodmanager.models.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PMeasurement {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;

    public PMeasurement(){}
    @Ignore
    public PMeasurement(String name){this.name = name;}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
