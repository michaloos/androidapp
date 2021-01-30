package com.example.projekt.EntityClass;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//definiujemy tabele w bazie danych
@Entity(tableName = "slowka")
public class SlowkoModel {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "slowo")
    private String slowo;

    @ColumnInfo(name = "tlumaczenie")
    private String tlumaczenie;

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSlowo(){
        return slowo;
    }
    public void setSlowo(String slowo){
        this.slowo = slowo;
    }
    public String getTlumaczenie() {
        return tlumaczenie;
    }
    public void setTlumaczenie(String tlumaczenie) {
        this.tlumaczenie = tlumaczenie;
    }
}
