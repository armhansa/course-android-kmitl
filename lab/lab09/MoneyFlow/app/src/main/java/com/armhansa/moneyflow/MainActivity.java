package com.armhansa.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.armhansa.moneyflow.adapter.PostAdapter;
import com.armhansa.moneyflow.database.MoneyFlowDB;
import com.armhansa.moneyflow.model.MoneyFlow;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MoneyFlowDB moneyFlowDB;
    private PostAdapter postAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAttibute();
        toDisplayList();
    }

    private void configureAttibute() {
        moneyFlowDB = Room.databaseBuilder(this
                , MoneyFlowDB.class, "MoneyFlow")
                .build();

        postAdapter = new PostAdapter();
        recyclerView = MainActivity.this.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    public void addFlow(View view) {
//        Intent toAddPage = new Intent(MainActivity.this, FlowAdditionActivity.class);
//        toAddPage.putExtra("moneyFlowDB", moneyFlowDB);
//        startActivity(toAddPage);

        new AsyncTask<Void, Void, List<MoneyFlow>>() {

            @Override
            protected List<MoneyFlow> doInBackground(Void... voids) {
                MoneyFlow moneyFlow = new MoneyFlow();
                moneyFlow.setIncome(true);
                moneyFlow.setTask("เงินเดือน");
                moneyFlow.setValue(15000);
                moneyFlowDB.getMoneyFlowDAO().insert(moneyFlow);

                List<MoneyFlow> moneyFlows = moneyFlowDB.getMoneyFlowDAO().findAll();
                return moneyFlows;
            }

            @Override
            protected void onPostExecute(List<MoneyFlow> moneyFlows) {
                super.onPostExecute(moneyFlows);
                postAdapter.setMoneyFlow(moneyFlows);
                recyclerView.setAdapter(postAdapter);
            }
        }.execute();
    }

    public void toDisplayList() {
        new AsyncTask<Void, Void, List<MoneyFlow>>() {
            @Override
            protected List<MoneyFlow> doInBackground(Void... voids) {
                List<MoneyFlow> moneyFlows = moneyFlowDB.getMoneyFlowDAO().findAll();
                return moneyFlows;
            }

            @Override
            protected void onPostExecute(List<MoneyFlow> moneyFlows) {
                super.onPostExecute(moneyFlows);
                postAdapter.setMoneyFlow(moneyFlows);
                recyclerView.setAdapter(postAdapter);
            }
        }.execute();
    }


}
