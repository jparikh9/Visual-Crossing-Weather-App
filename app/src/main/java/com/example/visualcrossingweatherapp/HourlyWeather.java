package com.example.visualcrossingweatherapp;

import java.io.Serializable;

public class HourlyWeather implements Serializable {
    private int dateTimeEpoch;
    private String conditions;
    private String icon;
    private double temperature;
    private String day;

    HourlyWeather(){
        this.dateTimeEpoch = 0;
        this.icon = "";
        this.conditions = "";
        this.temperature = 0;
        this.day = "";
    }

    HourlyWeather(int dateTimeEpoch, String conditions, String icon, double temperature, String day){
        this.dateTimeEpoch = dateTimeEpoch;
        this.icon = icon;
        this.conditions = conditions;
        this.day = "";
        this.temperature = temperature;
    }

    public int getDateTimeEpoch() {
        return dateTimeEpoch;
    }

    public String getIcon() {
        return icon;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getConditions() {
        return conditions;
    }

    public String getDay() {
        return day;
    }

    public void setDateTimeEpoch(int dateTimeEpoch) {
        this.dateTimeEpoch = dateTimeEpoch;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
