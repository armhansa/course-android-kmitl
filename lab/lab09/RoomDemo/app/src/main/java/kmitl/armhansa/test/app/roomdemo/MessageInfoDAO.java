package kmitl.armhansa.test.app.roomdemo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
interface MessageInfoDAO {
    @Insert
    void insert(MessageInfo message);

    @Query("SELECT * FROM MESSAGE_INFO")
    List<MessageInfo> findAll();
}
