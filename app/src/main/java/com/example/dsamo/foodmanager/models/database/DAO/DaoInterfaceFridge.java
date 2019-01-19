package com.example.dsamo.foodmanager.models.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dsamo.foodmanager.models.database.entity.Fridge;

import java.util.List;
@Dao
public interface DaoInterfaceFridge {

    @Query("SELECT *FROM fridge")
    List<Fridge> getAll();

    @Query("SELECT *FROM fridge WHERE id = :id")
    Fridge getById(long id);

    @Insert
    void insert(Fridge f);

    @Update
    void update(Fridge f);

    @Delete
    void delete(Fridge f);
}
