package com.example.visualcrossingweatherapp;

import java.io.Serializable;
import java.util.ArrayList;

public class DaysWeather implements Serializable {
    private int dateTimeEpoch;
    private double tempMax;
    private double tempMin;
    private int precipProb;
    private int uvindex;
    private String icon;
    private String description;
    private ArrayList<HourlyWeather> hourlyWeatherArrayList;

    public DaysWeather(){
        this.dateTimeEpoch = 0;
        this.tempMax = 0;
        this.tempMin  =0;
        this.description = "";
        this.icon = "";
        this.precipProb = 0;
        this.uvindex = 0;
        this.hourlyWeatherArrayList = new ArrayList<>();
    }

    public DaysWeather(int dateTimeEpoch, double tempMax, double tempMin, String description, String icon, int precipProb, int uvindex, ArrayList<HourlyWeather> hourlyWeatherArrayList){
        this.dateTimeEpoch = dateTimeEpoch;
        this.tempMax = tempMax;
        this.tempMin  =tempMin;
        this.description = description;
        this.icon = icon;
        this.precipProb = precipProb;
        this.uvindex = uvindex;
        this.hourlyWeatherArrayList = hourlyWeatherArrayList;
    }

    public ArrayList<HourlyWeather> getHourlyWeatherArrayList() {
        return hourlyWeatherArrayList;
    }

    public String getDescription() {
        return description;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public int getDateTimeEpoch() {
        return dateTimeEpoch;
    }

    public int getPrecipProb() {
        return precipProb;
    }

    public int getUvindex() {
        return uvindex;
    }

    public String getIcon() {
        return icon;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTimeEpoch(int dateTimeEpoch) {
        this.dateTimeEpoch = dateTimeEpoch;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setPrecipProb(int precipProb) {
        this.precipProb = precipProb;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public void setUvindex(int uvindex) {
        this.uvindex = uvindex;
    }

    public void setHourlyWeatherArrayList(ArrayList<HourlyWeather> hourlyWeatherArrayList) {
        this.hourlyWeatherArrayList = hourlyWeatherArrayList;
    }
}
