package com.example.projekt.DaoClass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projekt.EntityClass.SlowkoModel;

import java.util.List;

//data access objects metody które modyfikują baze danych
@Dao
public interface Daoclass {

    @Insert
    void insertAllData(SlowkoModel slowkoModel);

    //select
    @Query("SELECT * FROM slowka")
    List<SlowkoModel> getAllData();

    //delete
    @Query("DELETE FROM slowka WHERE `id` = :id")
    void deleteData(int id);

    //update
    @Query("UPDATE slowka SET slowo = :slowo, tlumaczenie = :tlumaczenie WHERE `id` =:id")
    void updateData(String slowo,String tlumaczenie,int id);
}
