package com.armhansa.moneyflow.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.armhansa.moneyflow.R;
import com.armhansa.moneyflow.model.MoneyFlow;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<Holder> {

    private List<MoneyFlow> moneyFlow;

    public void setMoneyFlow(List<MoneyFlow> moneyFlow) {
        this.moneyFlow = moneyFlow;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.task_item, null, false);
        Holder holder = new Holder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        TextView type = holder.type;
        TextView task = holder.task;
        TextView value = holder.value;

        if(moneyFlow.get(position).isIncome()) type.setText("+");
        else type.setText("-");
        task.setText(moneyFlow.get(position).getTask());
        value.setText(String.valueOf(moneyFlow.get(position).getValue()));

    }

    @Override
    public int getItemCount() {
        return moneyFlow.size();
    }
}

class Holder extends RecyclerView.ViewHolder {
    public TextView type;
    public TextView task;
    public TextView value;

    public Holder(View itemView) {
        super(itemView);
        type = itemView.findViewById(R.id.type);
        task = itemView.findViewById(R.id.task);
        value = itemView.findViewById(R.id.value);

    }
}
