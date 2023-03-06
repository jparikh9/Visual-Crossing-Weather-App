package com.example.visualcrossingweatherapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherViewHolder> {
    private final List<HourlyWeather> hourlyWeatherList;
    private final MainActivity main_activity;

    private static final String TAG = "HourlyWeatherAdapter";

    HourlyWeatherAdapter(List<HourlyWeather> hourWeatherList, MainActivity main_activity) {
        this.hourlyWeatherList = hourWeatherList;
        this.main_activity = main_activity;
    }
    @NonNull
    @Override
    public HourlyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW HourlyWeatherViewHolder");

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_weather_list, parent, false);

        view.setOnClickListener((View.OnClickListener) main_activity);
        //view.setOnLongClickListener(main_activity);

        return new HourlyWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyWeatherViewHolder holder, int position) {
        HourlyWeather hourlyWeather = hourlyWeatherList.get(position);
        holder.description.setText(hourlyWeather.getDescription());
        holder.temperature.setText(hourlyWeather.getTemperature());
        holder.day.setText(hourlyWeather.getDay());
        holder.time.setText(hourlyWeather.getTime());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
