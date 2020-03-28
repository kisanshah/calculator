package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

    private final ArrayList<String> data;
    private final ArrayList<String> data2;

    public HistoryAdapter(ArrayList<String> data, ArrayList<String> data2) {
        this.data = data;
        this.data2 = data2;
    }

    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.history, viewGroup, false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryHolder historyHolder, int i) {
        String Equation = data.get(i);
        String Result = data2.get(i);
        historyHolder.EquationHistory.setText(Equation);
        historyHolder.ResultHistory.setText(Result);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView EquationHistory, ResultHistory;
        public HistoryHolder(View itemView) {
            super(itemView);
            EquationHistory = itemView.findViewById(R.id.EquationHistory);
            ResultHistory = itemView.findViewById(R.id.ResultHistory);
        }
    }
}
