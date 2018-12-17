package com.example.dsamo.foodmanager.models.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "product_id", onDelete =  CASCADE),
        indices = {@Index(value = {"id", "product_id"}, unique = true)})
public class ListOfBuy {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long product_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public long getProduct_id() {

        return product_id;
    }
}
