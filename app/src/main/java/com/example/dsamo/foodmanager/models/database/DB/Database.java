package com.example.dsamo.foodmanager.models.database.DB;

import android.arch.persistence.room.RoomDatabase;

import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfaceFridge;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfaceItemOfList;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfacePMeasurement;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfacePType;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfaceProduct;
import com.example.dsamo.foodmanager.models.database.DAO.DaoInterfaceUser;
import com.example.dsamo.foodmanager.models.database.entity.Fridge;
import com.example.dsamo.foodmanager.models.database.entity.FridgeListBuying;
import com.example.dsamo.foodmanager.models.database.entity.FridgeListProducts;
import com.example.dsamo.foodmanager.models.database.entity.ItemOfList;
import com.example.dsamo.foodmanager.models.database.entity.PMeasurement;
import com.example.dsamo.foodmanager.models.database.entity.Product;
import com.example.dsamo.foodmanager.models.database.entity.PType;
import com.example.dsamo.foodmanager.models.database.entity.User;

import java.util.ArrayList;
import java.util.List;

@android.arch.persistence.room.Database(entities = {Product.class,
        Fridge.class, PType.class, PMeasurement.class, FridgeListBuying.class,
        FridgeListProducts.class, ItemOfList.class, User.class, }, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase{
    public abstract DaoInterfaceProduct daoInterfaceProduct();
    public abstract DaoInterfacePType daoInterfacePType();
    public abstract DaoInterfaceFridge daoInterfaceFridge();
    public abstract DaoInterfaceItemOfList daoInterfaceItemOfList();
    public abstract DaoInterfacePMeasurement daoInterfacePMeasurement();
    public abstract DaoInterfaceUser daoInterfaceUser();

    public void populateData(){
        List<PMeasurement> lMeasurement = new ArrayList<PMeasurement>();
        List<PType> lType = new ArrayList<PType>();
        lMeasurement.add(new PMeasurement("кг."));
        lMeasurement.add(new PMeasurement("шт."));
        lMeasurement.add(new PMeasurement("г."));
        lMeasurement.add(new PMeasurement("л."));
        lMeasurement.add(new PMeasurement("мл."));
        lMeasurement.add(new PMeasurement("п."));
        lType.add(new PType("Молочный продукт"));
        lType.add(new PType("Сок"));
        lType.add(new PType("Йогурт"));
        lType.add(new PType("Овощь"));
        lType.add(new PType("Фрукт"));
        lType.add(new PType("Выпечка"));
        daoInterfacePMeasurement().insert(lMeasurement);
        daoInterfacePType().insert(lType);
        List<PMeasurement> mNewlMeasurement;
        mNewlMeasurement = daoInterfacePMeasurement().getAll();
        mNewlMeasurement.add(new PMeasurement());
    }
}

