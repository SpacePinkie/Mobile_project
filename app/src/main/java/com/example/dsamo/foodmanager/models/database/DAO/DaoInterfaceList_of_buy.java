package com.example.dsamo.foodmanager.models.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dsamo.foodmanager.models.database.entity.ListOfBuy;

import java.util.List;

@Dao
public interface DaoInterfaceList_of_buy {
    @Query("SELECT *FROM ListOfBuy")
    List<ListOfBuy> getAll();

    @Insert
    long insertWithId(ListOfBuy l);

    @Insert
    void insert(ListOfBuy l);

    @Update
    void update(ListOfBuy l);

    @Delete
    void delete(ListOfBuy l);
}
