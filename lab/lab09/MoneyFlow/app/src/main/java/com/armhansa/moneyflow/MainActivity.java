package com.armhansa.moneyflow;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.armhansa.moneyflow.database.MoneyFlowDB;
import com.armhansa.moneyflow.model.MoneyFlow;
import com.armhansa.moneyflow.old.MessageDB;
import com.armhansa.moneyflow.old.MessageInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MoneyFlowDB moneyFlowDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyFlowDB = Room.databaseBuilder(this
                , MoneyFlowDB.class, "MoneyFlow")
                .build();



    }


}
