package com.armhansa.moneyflow.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.armhansa.moneyflow.model.MoneyFlow;

import java.util.List;

@Dao
public interface MoneyFlowDAO {
    @Insert
    void insert(MoneyFlow moneyFlow);

    @Query("SELECT * FROM MONEY_FLOW")
    List<MoneyFlow> findAll();

    @Update
    void update(MoneyFlow moneyFlow);

    @Delete
    void delete(MoneyFlow moneyFlow);


}
