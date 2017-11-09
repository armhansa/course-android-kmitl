package com.armhansa.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.armhansa.moneyflow.adapter.PostAdapter;
import com.armhansa.moneyflow.database.MoneyFlowDB;
import com.armhansa.moneyflow.model.MoneyFlow;

public class FlowAdditionActivity extends AppCompatActivity {

    MoneyFlowDB moneyFlowDB;

    Button selectIncome;
    Button selectOutcome;

    Button addBtn;
    Button deleteBtn;

    EditText taskInput;
    EditText valueInput;

    private boolean isAddPage;
    private boolean isIncome;

    int color[]
            = {Color.rgb(85,85,204)
            , Color.rgb(240,240, 240)
            , Color.rgb(0,0,0)
            , Color.rgb(255,255,255)};

    MoneyFlow moneyFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_addition);

        configureAttibute();
        switchType(null);
        if(moneyFlow != null) {
            isAddPage = false;
            setValue();
        } else {
            isAddPage = true;
        }
    }

    private void configureAttibute() {
        moneyFlowDB = Room.databaseBuilder(this
                , MoneyFlowDB.class, "MoneyFlow")
                .build();

        isIncome = false;
        selectIncome = findViewById(R.id.incomeBtn);
        selectOutcome = findViewById(R.id.outcomeBtn);

        addBtn = findViewById(R.id.addBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        taskInput = findViewById(R.id.task);
        valueInput = findViewById(R.id.value);

        moneyFlow = getIntent().getParcelableExtra("moneyFlow");

    }

    public void setValue() {
        deleteBtn.setVisibility(View.VISIBLE);
        addBtn.setText("แก้ไข");
        isIncome = !moneyFlow.isIncome();
        switchType(null);
        taskInput.setText(moneyFlow.getTask());
        valueInput.setText(String.valueOf(moneyFlow.getValue()));
    }

    public void addFlow(View view) {
        if("".equals(String.valueOf(taskInput.getText())))
            Toast.makeText(this, "Please enter task name.", Toast.LENGTH_LONG).show();
        else if("".equals(String.valueOf(valueInput.getText())))
            Toast.makeText(this, "Please enter value.", Toast.LENGTH_LONG).show();
        else {
            try {
                String tmpString = String.valueOf(valueInput.getText());
                final int tmpValue = Integer.parseInt(tmpString);

                new AsyncTask<Void, Void, MoneyFlow>() {

                    @Override
                    protected MoneyFlow doInBackground(Void... voids) {
                        if(isAddPage) {
                            MoneyFlow moneyFlow = new MoneyFlow();
                            moneyFlow.setTask(String.valueOf(taskInput.getText()));
                            moneyFlow.setValue(tmpValue);
                            moneyFlow.setIncome(isIncome);
                            moneyFlowDB.getMoneyFlowDAO().insert(moneyFlow);
                        } else {
                            moneyFlow.setTask(String.valueOf(taskInput.getText()));
                            moneyFlow.setValue(tmpValue);
                            moneyFlow.setIncome(isIncome);
                            moneyFlowDB.getMoneyFlowDAO().update(moneyFlow);
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(MoneyFlow moneyFlow) {
                        super.onPostExecute(moneyFlow);
                        Intent intent = new Intent(FlowAdditionActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }.execute();

            } catch (Exception ex) {
                Toast.makeText(this, "Please enter numeric.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void switchType(View view) {
        isIncome = !isIncome;

        selectIncome.setBackgroundColor(color[isIncome ? 0: 1]);
        selectOutcome.setBackgroundColor(color[isIncome ? 1: 0]);
        selectIncome.setTextColor(color[isIncome ? 3: 2]);
        selectOutcome.setTextColor(color[isIncome ? 2: 3]);
        selectIncome.setEnabled(!isIncome);
        selectOutcome.setEnabled(isIncome);

    }

    public void deleteFlow(View view) {
        new AsyncTask<Void, Void, MoneyFlow>() {

            @Override
            protected MoneyFlow doInBackground(Void... voids) {
                moneyFlowDB.getMoneyFlowDAO().delete(moneyFlow);
                return null;
            }

            @Override
            protected void onPostExecute(MoneyFlow moneyFlow) {
                super.onPostExecute(moneyFlow);
                Intent intent = new Intent(FlowAdditionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.execute();

    }
}
