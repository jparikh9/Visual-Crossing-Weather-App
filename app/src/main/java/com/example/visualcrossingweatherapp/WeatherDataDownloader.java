package com.example.visualcrossingweatherapp;

import android.app.DownloadManager;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherDataDownloader {
    private static MainActivity mainActivity;

    // the weather api url
    private static final String crossingWeatherURL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    // api key for api call
    private static final String apiKey = "HTAK85D4UZTLEM5DBNHZ7ZWFD";
    private static RequestQueue queue;

    private static final String TAG = "WeatherDownloadRunnable";
    //private static String measurementUnit;

    public static void getCrossingWeatherData(MainActivity mainAct, String city, String measurement){
        mainActivity = mainAct;
        queue = Volley.newRequestQueue(mainActivity);
        //measurementUnit = measurement;

        Uri.Builder urlBuildObj = Uri.parse(crossingWeatherURL).buildUpon();
        urlBuildObj.appendPath(city);

        /*if(measurement.equals("us")){
            measurementUnit = "us";
        }
        if(measurement.equals("metric")){
            measurementUnit = "metric";
        }*/
        urlBuildObj.appendQueryParameter("unitGroup",measurement);
        urlBuildObj.appendQueryParameter("key", apiKey);
        String finalUrl = urlBuildObj.build().toString();
        try {

            Response.Listener<JSONObject> listener = response -> {
                //JSONObject resObject;
                ArrayList<DaysWeather> daysData = new ArrayList<>();
                CurrentWeather currentWeather = new CurrentWeather();
                JSONObject currentConditions;
                String address;
                JSONArray days;
                JSONArray hourly;

                try {
                    //resObject = new JSONObject(response.toString());
                    address = response.getString("address");
                    days = response.getJSONArray("days");
                    currentConditions = response.getJSONObject("currentConditions");
                    currentWeather.setDateTimeEpoch(currentConditions.getInt("datetimeEpoch"));
                    currentWeather.setTemperature(currentConditions.getInt("temp"));
                    currentWeather.setCloudCover(currentConditions.getInt("cloudcover"));
                    currentWeather.setConditions(currentConditions.getString("conditions"));
                    currentWeather.setUvindex(currentConditions.getDouble("uvindex"));
                    currentWeather.setFeelsLike(currentConditions.getDouble("feelslike"));
                    currentWeather.setHumidity(currentConditions.getDouble("humidity"));
                    if (currentConditions.get("windgust") != JSONObject.NULL) {
                        currentWeather.setWindgust(currentConditions.getDouble("windgust"));
                    } else {
                        currentWeather.setWindgust(-1);
                    }
                    //currentWeather.setWindgust(currentConditions.getDouble("windgust"));
                    currentWeather.setWindspeed(currentConditions.getDouble("windspeed"));
                    currentWeather.setWindDir(currentConditions.getDouble("winddir"));
                    String iconstr = currentConditions.getString("icon");
                    iconstr = iconstr.replace("-", "_");
                    currentWeather.setIcon(iconstr);
                    currentWeather.setVisibility(currentConditions.getDouble("visibility"));
                    currentWeather.setSunriseEpoch(currentConditions.getInt("sunriseEpoch"));
                    currentWeather.setSunsetEpoch(currentConditions.getInt("sunsetEpoch"));

                    for (int index = 0; index < days.length(); index++) {
                        DaysWeather daysWeather = new DaysWeather();
                        JSONObject day = days.getJSONObject(index);
                        daysWeather.setDateTimeEpoch(day.getInt("datetimeEpoch"));
                        daysWeather.setTempMin(day.getDouble("tempmin"));
                        daysWeather.setTempMax(day.getDouble("tempmax"));
                        daysWeather.setPrecipProb(day.getInt("precipprob"));
                        daysWeather.setDescription(day.getString("description"));
                        daysWeather.setUvindex(day.getInt("uvindex"));
                        String iconDayStr = day.getString("icon");
                        iconDayStr = iconDayStr.replace("-", "_");
                        daysWeather.setIcon(iconDayStr);
                        hourly = day.getJSONArray("hours");
                        ArrayList<HourlyWeather> hourlyWeatherArrayList = new ArrayList<>();

                        for (int i = 0; i < hourly.length(); i++) {
                            HourlyWeather hourlyWeather = new HourlyWeather();
                            JSONObject hour = hourly.getJSONObject(i);
                            hourlyWeather.setDateTimeEpoch(hour.getInt("datetimeEpoch"));
                            hourlyWeather.setConditions(hour.getString("conditions"));
                            String iconHourStr = hour.getString("icon");
                            iconHourStr = iconHourStr.replace("-", "_");
                            hourlyWeather.setIcon(iconHourStr);
                            hourlyWeather.setTemperature(hour.getDouble("temp"));
                            hourlyWeatherArrayList.add(hourlyWeather);
                        }
                        daysWeather.setHourlyWeatherArrayList(hourlyWeatherArrayList);
                        daysData.add(daysWeather);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mainActivity.updateData(daysData, currentWeather);
            };

            Response.ErrorListener error = error1 -> {
                error1.getMessage();
                Log.d(TAG, "downloadWeather: Error: " + error1.getMessage());
                //mainActivity.updateData();
            };
            try {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, finalUrl, null, listener, error);
                queue.add(jsonObjectRequest);
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
