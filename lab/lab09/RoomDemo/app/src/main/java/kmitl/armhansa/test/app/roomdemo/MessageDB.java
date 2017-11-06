package kmitl.armhansa.test.app.roomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MessageInfo.class}, version = 1)
public abstract class MessageDB extends RoomDatabase {
    public abstract MessageInfoDAO getMessageInfoDAO();
}