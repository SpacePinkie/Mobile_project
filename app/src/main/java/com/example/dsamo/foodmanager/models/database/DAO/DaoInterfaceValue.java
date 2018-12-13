package com.example.dsamo.foodmanager.models.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.dsamo.foodmanager.models.database.entity.Value;
import java.util.List;

@Dao
public interface DaoInterfaceValue {
    @Query("SELECT *FROM value")
    List<Value> getAll();

    @Query("SELECT *FROM value WHERE id = :id")
    Value getById(int id);

    @Insert
    void insert(Value v);

    @Update
    void update(Value v);

    @Delete
    void delete(Value v);
}
