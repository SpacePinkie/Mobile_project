package com.example.dsamo.foodmanager.models.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(indices = {@Index("list_product_id"), @Index("fridge_id")},
        primaryKeys = {"list_product_id", "fridge_id"},
        foreignKeys = {@ForeignKey(entity = Fridge.class, parentColumns = "id", childColumns = "fridge_id", onDelete =  CASCADE),
        @ForeignKey(entity = ItemOfList.class, parentColumns = "id", childColumns = "list_product_id", onDelete =  CASCADE)})
public class FridgeListBuying {
    private long list_product_id;
    private long fridge_id;

    public FridgeListBuying(){}

    public long getList_product_id() {
        return list_product_id;
    }

    public void setList_product_id(long list_product_id) {
        this.list_product_id = list_product_id;
    }

    public long getFridge_id() {
        return fridge_id;
    }

    public void setFridge_id(long fridge_id) {
        this.fridge_id = fridge_id;
    }
}
