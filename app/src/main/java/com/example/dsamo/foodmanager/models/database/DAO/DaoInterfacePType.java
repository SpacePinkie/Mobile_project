package com.example.dsamo.foodmanager.models.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.dsamo.foodmanager.models.database.entity.PType;
import java.util.List;

@Dao
public interface DaoInterfacePType {

    @Query("SELECT *FROM ptype")
    List<PType> getAll();

    @Query("SELECT *FROM ptype WHERE id = :id")
    PType getById(int id);

    @Insert
    long[] insert(List<PType> lT);

    @Insert
    long insert(PType t);

    @Update
    void update(PType t);

    @Delete
    void delete(PType t);
}
