package com.example.visualcrossingweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class DailyWeatherDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DailyAdapter dailyAdapter;
    ArrayList<DaysWeather> daysWeatherArrayList;
    String tempUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather_data);
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.dailyRecycler_vid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        if(intent.hasExtra("dailyArray")){
            daysWeatherArrayList = (ArrayList<DaysWeather>) getIntent().getSerializableExtra("dailyArray");
        }
        else{
            daysWeatherArrayList = new ArrayList<>();
        }

        if(intent.hasExtra("tempunit")){
            tempUnit = (String) getIntent().getSerializableExtra("tempunit");
        }
        else{
            tempUnit = "";
        }
        dailyAdapter = new DailyAdapter(daysWeatherArrayList, this, tempUnit);
        recyclerView.setAdapter(dailyAdapter);
        dailyAdapter.notifyItemChanged(0,daysWeatherArrayList.size());


    }

}