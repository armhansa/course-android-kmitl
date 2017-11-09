package com.armhansa.moneyflow.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "MONEY_FLOW")
public class MoneyFlow implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "TASK_NAME")
    private String task;

    @ColumnInfo(name = "TASK_VALUE")
    private int value;

    @ColumnInfo(name = "IS_INCOME")
    private boolean isIncome;

    public MoneyFlow() {

    }

    protected MoneyFlow(Parcel in) {
        id = in.readInt();
        task = in.readString();
        value = in.readInt();
        isIncome = in.readByte() != 0;
    }

    public static final Creator<MoneyFlow> CREATOR = new Creator<MoneyFlow>() {
        @Override
        public MoneyFlow createFromParcel(Parcel in) {
            return new MoneyFlow(in);
        }

        @Override
        public MoneyFlow[] newArray(int size) {
            return new MoneyFlow[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(task);
        parcel.writeInt(value);
        parcel.writeByte((byte) (isIncome ? 1 : 0));
    }
}
