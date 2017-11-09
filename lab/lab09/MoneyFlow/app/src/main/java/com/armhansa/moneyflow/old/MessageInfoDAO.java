package com.armhansa.moneyflow.old;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MessageInfoDAO {

    @Insert
    void insert(MessageInfo messageInfo);

    @Query("SELECT * FROM MESSAGE_INFO")
    List<MessageInfo> findAll();

}
