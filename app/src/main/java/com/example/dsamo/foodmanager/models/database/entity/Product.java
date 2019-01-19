package com.example.dsamo.foodmanager.models.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(indices = {@Index("value_id"), @Index("type_id"), @Index("measurement_id")},
        foreignKeys = {@ForeignKey(entity = PType.class, parentColumns = "id", childColumns = "type_id", onDelete =  CASCADE),
        @ForeignKey(entity = PValue.class, parentColumns = "id", childColumns = "value_id", onDelete =  CASCADE),
        @ForeignKey(entity = PMeasurement.class, parentColumns = "id", childColumns = "measurement_id", onDelete =  CASCADE)})
public class Product {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    @ColumnInfo(name = "measurement_id")
    private int measurement;
    @ColumnInfo(name = "value_id")
    private int value;
    @ColumnInfo(name = "type_id")
    private int type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMeasurement() { return measurement; }

    public void setMeasurement(int measurement) { this.measurement = measurement; }
}
