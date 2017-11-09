package com.armhansa.moneyflow.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.os.Parcel;
import android.os.Parcelable;

import com.armhansa.moneyflow.model.MoneyFlow;

@Database(entities = {MoneyFlow.class}, version = 1)
public abstract class MoneyFlowDB extends RoomDatabase implements Parcelable{
    public abstract MoneyFlowDAO getMoneyFlowDAO();

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
