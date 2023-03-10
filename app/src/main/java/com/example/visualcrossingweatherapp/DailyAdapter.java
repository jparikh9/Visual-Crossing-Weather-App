package com.example.visualcrossingweatherapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DailyAdapter extends RecyclerView.Adapter<DailyViewHolder> {
    private final ArrayList<DaysWeather> daysWeathers;
    private final DailyWeatherDataActivity main_activity;

    private static final String TAG = "DailyAdapter";
    private String temperatureUnit;

    DailyAdapter(ArrayList<DaysWeather> daysWeatherArrayList, DailyWeatherDataActivity main_activity, String temperatureUnit) {
        this.daysWeathers = daysWeatherArrayList;
        this.main_activity = main_activity;
        this.temperatureUnit = temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }
    @NonNull
    @Override
    public DailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW DailyViewHolder");

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_list_layout, parent, false);

        //view.setOnClickListener((View.OnClickListener) main_activity);
        //view.setOnLongClickListener(main_activity);

        return new DailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolder holder, int position) {
        DaysWeather day = daysWeathers.get(position);
        holder.description.setText(day.getDescription());
        holder.icon.setImageResource(this.weatherIcon(day.getIcon()));
        long milli = (long) day.getDateTimeEpoch() * 1000;
        Date cd = new Date(milli);
        SimpleDateFormat fullDate = new SimpleDateFormat("EEEE MM/dd", Locale.getDefault());
        String daytime = fullDate.format(cd);
        holder.day.setText(daytime);

        String temp = String.format("%.0f/%.0f %s", day.getTempMax(),day.getTempMin(), temperatureUnit);
        holder.temperature.setText(temp);

        ArrayList<HourlyWeather> tempArray = day.getHourlyWeatherArrayList();

        double morningTemp = tempArray.get(8).getTemperature();
        String morningStr = String.format("%.0f %s\nMorning", morningTemp, temperatureUnit);
        holder.morning.setText(morningStr);

        double afternoonTemp = tempArray.get(13).getTemperature();
        String afternoonStr = String.format("%.0f %s\nAfternoon", afternoonTemp, temperatureUnit);
        holder.afternoon.setText(afternoonStr);

        double eveningTemp = tempArray.get(17).getTemperature();
        String eveningStr = String.format("%.0f %s\nEvening", eveningTemp, temperatureUnit);
        holder.evening.setText(eveningStr);

        double nightTemp = tempArray.get(tempArray.size() - 1).getTemperature();
        String nightStr = String.format("%.0f %s\nNight", nightTemp, temperatureUnit);
        holder.night.setText(nightStr);

        String perciprob = "(" + day.getPrecipProb() + "% percip.)";
        holder.perci.setText(perciprob);

        String uv = "UV Index: " + day.getUvindex();
        holder.uvindex.setText(uv);
    }

    @Override
    public int getItemCount() {
        return daysWeathers.size();
    }

    public int weatherIcon(String iconText){
        int icon = main_activity.getResources().getIdentifier(iconText, "drawable", main_activity.getPackageName());
        if(icon!=0){
            return icon;
        }
        else
            return 0;
    }
}
