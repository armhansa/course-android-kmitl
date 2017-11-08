package com.armhansa.moneyflow.old;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.armhansa.moneyflow.MainActivity;
import com.armhansa.moneyflow.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class tmp {
    private MessageDB messageDB;
    private int count;
    private ListView list;
    private Button addList;
    private ArrayAdapter<MessageInfo> adapter;

    /*
    public void main() {
        messageDB = Room.databaseBuilder(this
                , MessageDB.class, "Message")
                .build();

        addList = findViewById(R.id.addBtn);
        list = findViewById(R.id.list);
        addList.setOnClickListener(this);
        adapter =
                new ArrayAdapter<MessageInfo>(MainActivity.this
                        , android.R.layout.simple_list_item_1);
        count = 0;

        new AsyncTask<Void, Void, List<MessageInfo>>() {

            @Override
            protected List<MessageInfo> doInBackground(Void... voids) {
                List<MessageInfo> messageInfos = messageDB.getMessageInfoDAO().findAll();
                return messageInfos;
            }

            @Override
            protected void onPostExecute(List<MessageInfo> messageInfos) {
                super.onPostExecute(messageInfos);
                adapter.addAll(messageInfos);
                ListView listOfText = findViewById(R.id.list);
                listOfText.setAdapter(adapter);
            }
        }.execute();
    }

    //R.id.addBtn
    @Override
    public void onClick(View view) {
        new AsyncTask<Void, Void, List<MessageInfo>>() {

            @Override
            protected List<MessageInfo> doInBackground(Void... voids) {
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setText(String.valueOf(count++));
                messageInfo.setTime(new Date().toString());
                messageDB.getMessageInfoDAO().insert(messageInfo);

                List<MessageInfo> messageInfos = messageDB.getMessageInfoDAO().findAll();
                ArrayList<MessageInfo> inverseMessageInfos = new ArrayList<>();
                for(int i=messageInfos.size()-1; i>=0; i--) {
                    inverseMessageInfos.add(messageInfos.get(i));
                }

                return inverseMessageInfos;
            }

            @Override
            protected void onPostExecute(List<MessageInfo> messageInfos) {
                super.onPostExecute(messageInfos);

                adapter.clear();
                adapter.addAll(messageInfos);
                list.setAdapter(adapter);
            }
        }.execute();
    } */
}
