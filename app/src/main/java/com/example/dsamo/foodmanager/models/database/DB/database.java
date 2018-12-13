package com.example.dsamo.foodmanager.models.database.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfaceList_of_buy;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfacePType;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfaceProduct;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfaceValue;
import com.example.dsamo.foodmanager.models.database.entity.List_of_buy;
import com.example.dsamo.foodmanager.models.database.entity.Product;
import com.example.dsamo.foodmanager.models.database.entity.Value;
import com.example.dsamo.foodmanager.models.database.entity.PType;

@Database(entities = {Product.class,
        List_of_buy.class, PType.class, Value.class}, version = 1)
public abstract class database extends RoomDatabase{
    public abstract DaoInterfaceProduct daoInterfaceProduct();
    public abstract DaoInterfaceList_of_buy daoInterfaceList_of_buy();
    public abstract DaoInterfacePType daoInterfacePType();
    public abstract DaoInterfaceValue daoInterfaceValue();
}
