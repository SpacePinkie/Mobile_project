package com.example.dsamo.foodmanager.models.database.DB;

import android.arch.persistence.room.RoomDatabase;

import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfaceFridge;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfaceItemOfList;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfacePMeasurement;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfacePType;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfacePValue;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfaceProduct;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfaceUser;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfacePValue;
import com.example.dsamo.foodmanager.models.database.entity.Fridge;
import com.example.dsamo.foodmanager.models.database.entity.FridgeListBuying;
import com.example.dsamo.foodmanager.models.database.entity.FridgeListProducts;
import com.example.dsamo.foodmanager.models.database.entity.ItemOfList;
import com.example.dsamo.foodmanager.models.database.entity.PMeasurement;
import com.example.dsamo.foodmanager.models.database.entity.Product;
import com.example.dsamo.foodmanager.models.database.entity.PValue;
import com.example.dsamo.foodmanager.models.database.entity.PType;
import com.example.dsamo.foodmanager.models.database.entity.User;

@android.arch.persistence.room.Database(entities = {Product.class,
        Fridge.class, PType.class, PValue.class, PMeasurement.class, FridgeListBuying.class,
        FridgeListProducts.class, ItemOfList.class, User.class, }, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase{
    public abstract DaoInterfaceProduct daoInterfaceProduct();
    public abstract DaoInterfacePType daoInterfacePType();
    public abstract DaoInterfaceFridge daoInterfaceFridge();
    public abstract DaoInterfaceItemOfList daoInterfaceItemOfList();
    public abstract DaoInterfacePMeasurement daoInterfacePMeasurement();
    public abstract DaoInterfacePValue daoInterfacePValue();
    public abstract DaoInterfaceUser daoInterfaceUser();
}
