package com.example.visualcrossingweatherapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HourlyWeatherViewHolder extends RecyclerView.ViewHolder {
    TextView day;
    TextView time;
    TextView temperature;
    TextView description;
    ImageView icon;

    HourlyWeatherViewHolder(View view){
        super(view);
        day = view.findViewById(R.id.day_hw_vid);
        time = view.findViewById(R.id.time_hw_vid);
        temperature = view.findViewById(R.id.temperature_hw_vid);
        description = view.findViewById(R.id.description_hw_vid);
        icon = view.findViewById(R.id.weatherIcon_hw_vid);
    }
}
