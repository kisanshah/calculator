package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView EquationView, ResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history2);

        recyclerView = findViewById(R.id.RecyclerView);
        EquationView = findViewById(R.id.Equation);
        ResultView = findViewById(R.id.Result);
        ArrayList<String> Equation;
        ArrayList<String> Result;
        String CurrentEquation = getIntent().getStringExtra("CurrentEquation");
        String CurrentResult = getIntent().getStringExtra("CurrentResult");

        Equation = getIntent().getStringArrayListExtra("Equation");
        Result = getIntent().getStringArrayListExtra("Result");

        EquationView.setText(CurrentEquation);
        ResultView.setText(CurrentResult);
        System.out.println(CurrentResult);
        System.out.println(Result);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HistoryAdapter(Equation, Result));
    }
}
