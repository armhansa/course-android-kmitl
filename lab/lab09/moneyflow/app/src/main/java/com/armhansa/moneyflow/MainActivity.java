package com.armhansa.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.armhansa.moneyflow.adapter.PostAdapter;
import com.armhansa.moneyflow.database.MoneyFlowDB;
import com.armhansa.moneyflow.model.MoneyFlow;

import java.util.List;


public class MainActivity extends AppCompatActivity
implements PostAdapter.MoneyFlowListener {

    private MoneyFlowDB moneyFlowDB;
    private PostAdapter postAdapter;
    RecyclerView recyclerView;

    private List<MoneyFlow> moneyFlows;

    private TextView totalTxt;

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

        totalTxt = findViewById(R.id.totalTxt);

        postAdapter = new PostAdapter();
        postAdapter.setListener(this);

        recyclerView = MainActivity.this.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    public void addFlow(View view) {
        Intent toAddPage = new Intent(MainActivity.this, FlowAdditionActivity.class);
        startActivity(toAddPage);
        toDisplayList();
    }

    @Override
    public void onClickInItem(int position) {
        Intent toEditPage = new Intent(MainActivity.this, FlowAdditionActivity.class);
        toEditPage.putExtra("moneyFlow", moneyFlows.get(position));
        startActivity(toEditPage);
        finish();
        toDisplayList();

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
                MainActivity.this.moneyFlows = moneyFlows;
                setTotal(moneyFlows);
                postAdapter.setMoneyFlow(moneyFlows);
                recyclerView.setAdapter(postAdapter);
            }

        }.execute();
    }

    private void setTotal(List<MoneyFlow> moneyFlows) {
        int total = 0;
        int sumOfIncome = 0;
        for(MoneyFlow i: moneyFlows) {
            if(i.isIncome()) {
                total += i.getValue();
                sumOfIncome += i.getValue();
            }
            else total -= i.getValue();
        }
        totalTxt.setText(String.valueOf(total));
        if(total > sumOfIncome/2)
            totalTxt.setTextColor(Color.rgb(106, 168, 79));
        else if(total >= sumOfIncome/4)
            totalTxt.setTextColor(Color.rgb(241, 194, 50));
        else
            totalTxt.setTextColor(Color.rgb(204, 0, 0));

    }

}
