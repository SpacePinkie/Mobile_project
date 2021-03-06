package com.example.dsamo.foodmanager.models.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(indices = {@Index("type_id"), @Index("measurement_id")},
        foreignKeys = {@ForeignKey(entity = PType.class, parentColumns = "id", childColumns = "type_id", onDelete =  CASCADE),
        @ForeignKey(entity = PMeasurement.class, parentColumns = "id", childColumns = "measurement_id", onDelete =  CASCADE)})
public class Product {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    @ColumnInfo(name = "measurement_id")
    private long measurement;
    private int value;
    @ColumnInfo(name = "type_id")
    private long type;
    private String image;

    public Product(){
    }
    @Ignore
    public Product(String name, int type, int value, int measurement, String image){
        this.name = name;
        this.measurement = measurement;
        this.type = type;
        this.value = value;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage(){return image;}

    public void setImage(String image){this.image = image;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getMeasurement() { return measurement; }

    public void setMeasurement(long measurement) { this.measurement = measurement; }
}
