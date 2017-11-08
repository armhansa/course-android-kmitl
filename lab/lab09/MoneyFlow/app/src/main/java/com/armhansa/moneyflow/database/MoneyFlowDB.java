package com.armhansa.moneyflow.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.armhansa.moneyflow.model.MoneyFlow;

@Database(entities = {MoneyFlow.class}, version = 1)
public abstract class MoneyFlowDB extends RoomDatabase {
    public abstract MoneyFlowDAO getMoneyFlowDAO();

}
