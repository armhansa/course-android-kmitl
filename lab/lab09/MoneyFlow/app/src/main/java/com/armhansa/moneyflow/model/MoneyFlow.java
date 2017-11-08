package com.armhansa.moneyflow.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "MONEY_FLOW")
public class MoneyFlow {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "TASK_NAME")
    private String task;

    @ColumnInfo(name = "TASK_VALUE")
    private int value;

    @ColumnInfo(name = "IS_INCOME")
    private boolean isIncome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }
}
