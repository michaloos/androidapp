package com.example.projekt;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projekt.DaoClass.Daoclass;
import com.example.projekt.EntityClass.SlowkoModel;

//dla każdej klasy DAO należy stworzyć klasę abstrakcyjną bez arumentów która zwraca instancję klasy getDao
@Database(entities = {SlowkoModel.class}, version = 1)
public abstract class ClassDatabase extends RoomDatabase {

    public abstract Daoclass getDao();

    private static ClassDatabase instance;

    public static ClassDatabase getDatabase(final Context context){
        if(instance == null){
            synchronized (ClassDatabase.class){
                instance = Room.databaseBuilder(context, ClassDatabase.class, "DATABASE").allowMainThreadQueries().build();
            }
        }
        return instance;
    }
}
