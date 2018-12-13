package com.example.dsamo.foodmanager.models.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.dsamo.foodmanager.models.database.entity.List_of_buy;
import java.util.List;

@Dao
public interface DaoInterfaceList_of_buy {
    @Query("SELECT *FROM list_of_buy")
    List<List_of_buy> getAll();

    @Insert
    long insertWithId(List_of_buy l);

    @Insert
    void insert(List_of_buy l);

    @Update
    void update(List_of_buy l);

    @Delete
    void delete(List_of_buy l);
}
