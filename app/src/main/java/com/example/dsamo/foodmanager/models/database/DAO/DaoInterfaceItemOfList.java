package com.example.dsamo.foodmanager.models.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dsamo.foodmanager.models.database.entity.ItemOfList;

import java.util.List;
@Dao
public interface DaoInterfaceItemOfList {

    @Query("SELECT *FROM itemoflist")
    List<ItemOfList> getAll();

    @Query("SELECT *FROM itemoflist WHERE id = :id")
    ItemOfList getById(long id);

    @Insert
    long insert(ItemOfList i);

    @Update
    void update(ItemOfList i);

    @Delete
    void delete(ItemOfList i);
}
