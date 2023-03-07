package com.example.visualcrossingweatherapp;

import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherViewHolder> {
    private final ArrayList<HourlyWeather> hourlyWeatherList;
    private final MainActivity main_activity;

    private static final String TAG = "HourlyWeatherAdapter";
    private String temperatureUnit;

    HourlyWeatherAdapter(ArrayList<HourlyWeather> hourWeatherList, MainActivity main_activity, String temperatureUnit) {
        this.hourlyWeatherList = hourWeatherList;
        this.main_activity = main_activity;
        this.temperatureUnit = temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    @NonNull
    @Override
    public HourlyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW HourlyWeatherViewHolder");

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_weather_list, parent, false);

        //view.setOnClickListener((View.OnClickListener) main_activity);
        //view.setOnLongClickListener(main_activity);

        return new HourlyWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyWeatherViewHolder holder, int position) {
        HourlyWeather hourlyWeather = hourlyWeatherList.get(position);
        holder.description.setText(hourlyWeather.getConditions());
        String temp = String.format("%.0f %s", hourlyWeather.getTemperature(), this.temperatureUnit);
        holder.temperature.setText(temp);
        String dayName = "";
        long milli = (long) hourlyWeather.getDateTimeEpoch() * 1000;
        Date cd = new Date(milli);
        SimpleDateFormat fullDate = new SimpleDateFormat("EEE MMM dd h:mm a, yyyy", Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat ("h:mm a");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        String fullDateStr = fullDate.format(cd);
        if(DateUtils.isToday(cd.getTime())){
            dayName = "Today";
            holder.day.setText(dayName);
        }
        else{
            dayName = dayFormat.format(cd);
            holder.day.setText(dayName);
        }
        holder.time.setText(dateFormat.format(cd));
        //holder.time.setText(hourlyWeather.getDateTimeEpoch());
        holder.icon.setImageResource(main_activity.weatherIcon(hourlyWeather.getIcon()));
    }

    @Override
    public int getItemCount() {
        return hourlyWeatherList.size();
    }
}
