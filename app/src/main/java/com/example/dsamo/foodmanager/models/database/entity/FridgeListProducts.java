package com.example.dsamo.foodmanager.models.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(indices = {@Index("fridge_id"), @Index("product_id")},
        primaryKeys = {"fridge_id", "product_id"},
        foreignKeys = {@ForeignKey(entity = Fridge.class, parentColumns = "id", childColumns = "fridge_id", onDelete =  CASCADE),
        @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "product_id", onDelete =  CASCADE)})
public class FridgeListProducts {
    private long fridge_id;
    private long product_id;

    public FridgeListProducts(){}

    public long getFridge_id() {
        return fridge_id;
    }

    public void setFridge_id(long fridge_id) {
        this.fridge_id = fridge_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }
}
