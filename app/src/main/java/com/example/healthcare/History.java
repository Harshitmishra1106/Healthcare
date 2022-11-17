package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class History extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ImageView imageview= findViewById(R.id.back);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Dashboard.class);
                startActivity(i);

            }
        });

        HistModel[] histModel = new HistModel[] {
                new HistModel("12/10/22","2:40","96.4°C","76","96"),
                new HistModel("13/10/22","8:15","97.1°C","79","95.2"),
                new HistModel("16/10/22","10:20","95.3°C","89","99"),
                new HistModel("19/10/22","9:45","98.7°C","93","98"),
                new HistModel("21/10/22","7:12","106.2°C","85","97"),
                new HistModel("24/10/22","11:13","99.5°C","94","97"),
                new HistModel("25/10/22","13:04","98.3°C","78","98"),
                new HistModel("28/10/22","17:15","100.2°C","83","96"),
                new HistModel("29/10/22","6:41","101.8°C","68","94"),
                new HistModel("31/10/22","19:18","97.6°C","72","97"),
                new HistModel("05/11/22","12:40","98.3°C","91","95"),
                new HistModel("07/11/22","21:35","104.7°C","98","96")
        };
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        HistAdapter adapter = new HistAdapter(histModel);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }




}