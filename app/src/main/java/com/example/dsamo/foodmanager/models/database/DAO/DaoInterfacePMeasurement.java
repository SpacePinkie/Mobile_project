package com.example.dsamo.foodmanager.models.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dsamo.foodmanager.models.database.entity.PMeasurement;

import java.util.List;
@Dao
public interface DaoInterfacePMeasurement {

    @Query("SELECT *FROM pmeasurement")
    List<PMeasurement> getAll();

    @Query("SELECT *FROM pmeasurement WHERE id = :id")
    PMeasurement getById(long id);

    @Insert
    void insert(PMeasurement m);

    @Update
    void update(PMeasurement m);

    @Delete
    void delete(PMeasurement m);
}
