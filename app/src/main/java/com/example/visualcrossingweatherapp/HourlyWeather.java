package com.example.visualcrossingweatherapp;

public class HourlyWeather {
    private String day;
    private String time;
    private String description;
    private String temperature;

    HourlyWeather(){
        this.day = "";
        this.description = "";
        this.time = "";
        this.temperature = "";
    }

    HourlyWeather(String day, String time, String description, String temperature){
        this.day = day;
        this.description = description;
        this.time = time;
        this.temperature = temperature;
    }

    public String getDay() {
        return day;
    }

    public String getDescription() {
        return description;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getTime() {
        return time;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
