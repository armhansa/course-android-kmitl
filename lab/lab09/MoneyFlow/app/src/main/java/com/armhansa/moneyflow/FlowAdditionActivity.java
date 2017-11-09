package com.armhansa.moneyflow;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.armhansa.moneyflow.database.MoneyFlowDB;
import com.armhansa.moneyflow.model.MoneyFlow;

public class FlowAdditionActivity extends AppCompatActivity {

    MoneyFlowDB moneyFlowDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_addition);

        moneyFlowDB = getIntent().getParcelableExtra("moneyFlowDB");

    }

    public void addFlow(View view) {
        new AsyncTask<Void, Void, MoneyFlow>() {

            @Override
            protected MoneyFlow doInBackground(Void... voids) {
                MoneyFlow moneyFlow = new MoneyFlow();

                moneyFlowDB.getMoneyFlowDAO().insert(moneyFlow);
                return null;
            }

            @Override
            protected void onPostExecute(MoneyFlow moneyFlow) {
                super.onPostExecute(moneyFlow);
                finish();
            }
        }.execute();
    }
}
