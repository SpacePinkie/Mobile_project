package com.example.dsamo.foodmanager.models.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(indices = {@Index("fridge_id")},
        foreignKeys = {@ForeignKey(entity = Fridge.class, parentColumns = "id", childColumns = "fridge_id", onDelete =  CASCADE)})
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "fridge_id")
    private int fridge;
    private String user_password;
    private String user_name;

    public User(){}
    @Ignore
    public User(int fridge, String name, String password){
        this.fridge = fridge;
        this.user_name = name;
        this.user_password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFridge(){
        return this.fridge;
    }

    public void setFridge(int fridge) {
        this.fridge = fridge;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
