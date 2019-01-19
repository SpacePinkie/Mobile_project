package com.example.dsamo.foodmanager.models.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dsamo.foodmanager.models.database.entity.User;

import java.util.List;
@Dao
public interface DaoInterfaceUser {

    @Query("SELECT *FROM user")
    List<User> getAll();

    @Query("SELECT *FROM user WHERE id = :id")
    User getById(int id);

    @Insert
    void insert(User u);

    @Update
    void update(User u);

    @Delete
    void delete(User u);
}
