package com.example.dsamo.foodmanager.models.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.dsamo.foodmanager.models.database.entity.PValue;
import java.util.List;

@Dao
public interface DaoInterfacePValue {
    @Query("SELECT *FROM pvalue")
    List<PValue> getAll();

    @Query("SELECT *FROM pvalue WHERE id = :id")
    PValue getById(int id);

    @Insert
    void insert(PValue v);

    @Update
    void update(PValue v);

    @Delete
    void delete(PValue v);
}
