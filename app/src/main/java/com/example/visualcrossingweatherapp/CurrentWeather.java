package com.example.visualcrossingweatherapp;

public class CurrentWeather {
    private int dateTimeEpoch;
    private double temperature;
    private double feelsLike;
    private double humidity;
    private double windgust;
    private double windspeed;
    private double visibility;
    private int cloudCover;
    private double uvindex;
    private String icon;
    private String conditions;
    private int sunriseEpoch;
    private int sunsetEpoch;
    private double windDir;

    public double getWindDir() {
        return windDir;
    }

    public int getSunriseEpoch() {
        return sunriseEpoch;
    }

    public int getSunsetEpoch() {
        return sunsetEpoch;
    }

    public String getIcon() {
        return icon;
    }

    public double getUvindex() {
        return uvindex;
    }

    public String getConditions() {
        return conditions;
    }

    public int getCloudCover() {
        return cloudCover;
    }

    public double getVisibility() {
        return visibility;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public double getWindgust() {
        return windgust;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getDateTimeEpoch() {
        return dateTimeEpoch;
    }

    public void setWindDir(double windDir) {
        this.windDir = windDir;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setUvindex(double uvindex) {
        this.uvindex = uvindex;
    }

    public void setSunriseEpoch(int sunriseEpoch) {
        this.sunriseEpoch = sunriseEpoch;
    }

    public void setSunsetEpoch(int sunsetEpoch) {
        this.sunsetEpoch = sunsetEpoch;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public void setCloudCover(int cloudCover) {
        this.cloudCover = cloudCover;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindgust(double windgust) {
        this.windgust = windgust;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setDateTimeEpoch(int dateTimeEpoch) {
        this.dateTimeEpoch = dateTimeEpoch;
    }

    public CurrentWeather(){
        this.dateTimeEpoch = 0;
        this.temperature = 0;
        this.feelsLike = 0;
        this.humidity = 0;
        this.windgust = 0;
        this.windspeed = 0;
        this.cloudCover = 0;
        this.conditions = "";
        this.icon = "";
        this.sunriseEpoch = 0;
        this.sunsetEpoch = 0;
        this.uvindex = 0;
        this.visibility = 0;
    }
}
