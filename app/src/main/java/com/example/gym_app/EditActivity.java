package com.example.gym_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity implements PlanAdapter.RemovePlan {
    private static final String TAG = "EditActivity";
    private TextView txtDay;
    private RecyclerView recView;
    private Button btnAddMorePlans;
    private PlanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initViews();
        adapter = new PlanAdapter(this);
        adapter.setType("edit");
        recView.setAdapter(adapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        if(null != intent){
            String day = intent.getStringExtra("day");
            if(null != day){
                txtDay.setText(day);
                ArrayList<Plan> plans = getPlansByDay(day);
                adapter.setPlans(plans);
            }
        }

        btnAddMorePlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditActivity.this, AllTrainingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Plan> getPlansByDay(String day){
        ArrayList<Plan> allPlans = Utils.getPlans();
        ArrayList<Plan> plans = new ArrayList<>();
        for(Plan p: allPlans){
            if(p.getDay().equalsIgnoreCase(day)){
                plans.add(p);
            }
        }

        return plans;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void initViews() {
        Log.d(TAG, "initViews: started");
        txtDay = findViewById(R.id.txtDay);
        recView = findViewById(R.id.dayRecView);
        btnAddMorePlans = findViewById(R.id.addMorePlans);

    }

    @Override
    public void onRemovePlanResult(Plan plan) {
        if(Utils.removePlan(plan)){
            Toast.makeText(this, "You removed the training from your plan", Toast.LENGTH_LONG).show();
            adapter.setPlans(getPlansByDay(plan.getDay()));
        }
    }
}