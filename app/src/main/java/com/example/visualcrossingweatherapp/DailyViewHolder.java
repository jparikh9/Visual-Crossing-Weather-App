package com.example.visualcrossingweatherapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class DailyViewHolder extends RecyclerView.ViewHolder {
    TextView day;
    TextView uvindex;
    TextView temperature;
    TextView description;
    ImageView icon;
    TextView morning;
    TextView afternoon;
    TextView evening;
    TextView night;
    TextView perci;

    DailyViewHolder(View view){
        super(view);
        day = view.findViewById(R.id.day_d_vid);
        uvindex = view.findViewById(R.id.uv_d_vid);
        temperature = view.findViewById(R.id.temp_d_vid);
        description = view.findViewById(R.id.description_d_vid);
        icon = view.findViewById(R.id.icon_d_vid);
        morning = view.findViewById(R.id.morning_d_vid);
        afternoon = view.findViewById(R.id.noon_d_vid);
        evening = view.findViewById(R.id.evening_d_vid);
        night = view.findViewById(R.id.night_d_vid);
        perci = view.findViewById(R.id.perci_d_vid);
    }
}
