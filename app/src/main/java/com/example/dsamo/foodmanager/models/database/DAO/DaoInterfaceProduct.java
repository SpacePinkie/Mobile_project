package com.example.dsamo.foodmanager.models.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.dsamo.foodmanager.models.database.entity.Product;
import java.util.List;

@Dao
public interface DaoInterfaceProduct {

    @Query("SELECT *FROM product")
    List<Product> getAll();

    @Query("SELECT *FROM product WHERE id = :id")
    Product getById(long id);

    @Insert
    long insertWithId(Product p);

    @Insert
    void insert(Product p);

    @Update
    void update(Product p);

    @Delete
    void delete(Product p);
}
