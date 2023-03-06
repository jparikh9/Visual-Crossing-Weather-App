package com.example.visualcrossingweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<HourlyWeather> hourlyWeatherList = new ArrayList<>();

    private RecyclerView recycler_view_hourly;
    private static final String TAG = "MainActivity";
    private HourlyWeatherAdapter hourlyWeatherAdapter;
    private boolean networkFlag;

    private TextView dateTimeMain;
    private TextView temperatureMain;
    private ImageView weatherIconMain;
    private TextView feelsLikeMain;
    private TextView descriptionMain;
    private TextView windsMain;
    private TextView humidityMain;
    private TextView uvindexMain;
    private TextView visibilityMain;
    private TextView morningMain;
    private TextView afternoonMain;
    private TextView eveningMain;
    private TextView nightMain;
    private TextView sunriseMain;
    private TextView sunsetMain;
    //private WeatherDataDownloader weatherDataDownloader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateTimeMain = findViewById(R.id.currentDateTime_vid);
        temperatureMain = findViewById(R.id.temperature_vid);
        weatherIconMain = findViewById(R.id.weatherIcon_vid);
        feelsLikeMain = findViewById(R.id.feelsLike_vid);
        descriptionMain = findViewById(R.id.description_vid);
        windsMain = findViewById(R.id.winds_vid);
        humidityMain = findViewById(R.id.humidity_vid);
        uvindexMain = findViewById(R.id.uvindex_vid);
        visibilityMain = findViewById(R.id.visibility_vid);
        morningMain = findViewById(R.id.morning_vid);
        afternoonMain = findViewById(R.id.afternoon_vid);
        eveningMain = findViewById(R.id.evening_vid);
        nightMain = findViewById(R.id.night_vid);
        sunriseMain = findViewById(R.id.sunrise_vid);
        sunsetMain = findViewById(R.id.sunset_vid);
        recycler_view_hourly = findViewById(R.id.recyclerViewHourly_vid);
        hourlyWeatherAdapter = new HourlyWeatherAdapter(hourlyWeatherList,this);
        recycler_view_hourly.setAdapter(hourlyWeatherAdapter);
        recycler_view_hourly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //weatherDataDownloader = new WeatherDataDownloader();
        networkFlag = hasNetworkConnection();
        if(networkFlag){
            WeatherDataDownloader.getCrossingWeatherData(this,"Chicago,IL");
        }
        else{
            setNoInternetMainLayout();
        }
    }

    public void updateData(){
    }

    private boolean hasNetworkConnection() {
        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    private void setNoInternetMainLayout(){
        String noInternetMsg = "No Internet connection";
        dateTimeMain.setText(noInternetMsg);
        temperatureMain.setVisibility(View.INVISIBLE);
        weatherIconMain.setVisibility(View.INVISIBLE);
        recycler_view_hourly.setVisibility(View.INVISIBLE);
        feelsLikeMain.setVisibility(View.INVISIBLE);
        descriptionMain.setVisibility(View.INVISIBLE);
        windsMain.setVisibility(View.INVISIBLE);
        humidityMain.setVisibility(View.INVISIBLE);
        uvindexMain.setVisibility(View.INVISIBLE);
        visibilityMain.setVisibility(View.INVISIBLE);
        morningMain.setVisibility(View.INVISIBLE);
        afternoonMain.setVisibility(View.INVISIBLE);
        eveningMain.setVisibility(View.INVISIBLE);
        nightMain.setVisibility(View.INVISIBLE);
        sunriseMain.setVisibility(View.INVISIBLE);
        sunsetMain.setVisibility(View.INVISIBLE);
    }
}