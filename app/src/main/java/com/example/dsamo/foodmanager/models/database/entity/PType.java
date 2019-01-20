package com.example.dsamo.foodmanager.models.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PType {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public PType(){}
    @Ignore
    public PType(String name){this.name = name;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
