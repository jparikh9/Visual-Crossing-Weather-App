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

import org.json.JSONObject;

public class WeatherDataDownloader {
    private static MainActivity mainActivity;

    // the weather api url
    private static final String crossingWeatherURL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    // api key for api call
    private static final String apiKey = "HTAK85D4UZTLEM5DBNHZ7ZWFD";
    private static RequestQueue queue;

    private static final String TAG = "WeatherDownloadRunnable";

    public static void getCrossingWeatherData(MainActivity mainAct, String city){
        mainActivity = mainAct;
        queue = Volley.newRequestQueue(mainActivity);
        Uri.Builder urlBuildObj = Uri.parse(crossingWeatherURL).buildUpon();
        urlBuildObj.appendPath(city);
        urlBuildObj.appendQueryParameter("unitGroup","us");
        urlBuildObj.appendQueryParameter("key", apiKey);
        String finalUrl = urlBuildObj.build().toString();

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject resObject;
                try{
                    resObject = new JSONObject(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener error = error1 -> {
            Log.d(TAG, "downloadWeather: Error: " + error1.getMessage());
            mainActivity.updateData();
        };

        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, finalUrl, null, listener, error);
        queue.add(jsonObjectRequest);

    }
}
