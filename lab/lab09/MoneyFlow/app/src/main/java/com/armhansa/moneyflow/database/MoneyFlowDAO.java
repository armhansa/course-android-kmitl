package com.armhansa.moneyflow.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.armhansa.moneyflow.model.MoneyFlow;

import java.util.List;

@Dao
interface MoneyFlowDAO {
    @Insert
    void insert(MoneyFlow moneyFlow);

    @Query("SELECT * FROM MONEY_FLOW")
    List<MoneyFlow> findAll();


}
