package com.example.visualcrossingweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<HourlyWeather> hourlyWeatherList = new ArrayList<>();

    private RecyclerView recycler_view_hourly;
    private static final String TAG = "MainActivity";
    private HourlyWeatherAdapter hourlyWeatherAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_view_hourly = findViewById(R.id.recyclerViewHourly_vid);
        hourlyWeatherAdapter = new HourlyWeatherAdapter(hourlyWeatherList,this);
        recycler_view_hourly.setAdapter(hourlyWeatherAdapter);
        recycler_view_hourly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
}