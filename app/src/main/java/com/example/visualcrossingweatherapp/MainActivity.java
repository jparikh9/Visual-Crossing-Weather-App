package com.example.visualcrossingweatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity{

    //private final ArrayList<HourlyWeather> hourlyWeatherList = new ArrayList<>();

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
    private boolean unitToggle;
    private String temperatureUnit;
    private String measurement;
    private String windMeasurement;
    private ArrayList<DaysWeather> daysWeathers;
    private CurrentWeather currentWeather;
    private WeatherDataDownloader weatherDataDownloader;
    private String city;
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
        //hourlyWeatherAdapter = new HourlyWeatherAdapter(this);
        //recycler_view_hourly.setAdapter(hourlyWeatherAdapter);
        recycler_view_hourly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        weatherDataDownloader = new WeatherDataDownloader(this);
        networkFlag = hasNetworkConnection();
        unitToggle = true;
        temperatureUnit = "°F";
        //this.updateData(this.daysWeathers, this.currentWeather);
        measurement = "us";
        windMeasurement = "mph";
        city = "Chicago,IL";

        if(networkFlag){
            weatherDataDownloader.getCrossingWeatherData("Chicago,IL",measurement);
            Objects.requireNonNull(this.getSupportActionBar()).setTitle("Chicago,IL");
        }
        else{
            setNoInternetMainLayout();
            Objects.requireNonNull(this.getSupportActionBar()).setTitle("Weather App");
        }

    }

    /*public void setData(ArrayList<DaysWeather> daysWeather, CurrentWeather currentWeather){
        if(daysWeather == null || currentWeather == null){
            Toast.makeText(this, "No data to show.", Toast.LENGTH_SHORT).show();
            return;
        }
        this.currentWeather = currentWeather;
        this.daysWeathers = daysWeather;

    }*/
    public void downloadWeatherForCity(String city){
        //this.city = city;
        //wd.getCrossingWeatherData(city,measurement);

        download(city);
        //Objects.requireNonNull(this.getSupportActionBar()).setTitle(city);
    }
    public void download(String city){
        this.weatherDataDownloader.getCrossingWeatherData(city, this.measurement);
    }
    public void updateData(ArrayList<DaysWeather> daysWeather, CurrentWeather currentWeather, String city){
        //for(int i = 0;i < days)
        if(daysWeather == null || currentWeather == null){
            Toast.makeText(this, "No data to show. Maybe Entered wrong city.", Toast.LENGTH_LONG).show();
            return;
        }
        this.city = city;
        Objects.requireNonNull(this.getSupportActionBar()).setTitle(this.city);
        this.currentWeather = currentWeather;
        this.daysWeathers = daysWeather;

        Date cd = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat ("E MMM dd',' hh:mm a ");
        dateTimeMain.setText(dateFormat.format(cd));
        //String tempstr = Double.toString(currentWeather.getTemperature());

        String tempstr = String.format("%.0f %s", currentWeather.getTemperature(), temperatureUnit);
        temperatureMain.setText(tempstr);

        String feelsLike = String.format("%.0f %s", currentWeather.getFeelsLike(), temperatureUnit);
        feelsLike = "Feels Like " + feelsLike;
        feelsLikeMain.setText(feelsLike);

        String description = currentWeather.getConditions() + " (" + currentWeather.getCloudCover() + "%clouds)";
        descriptionMain.setText(description);

        String humidity = "Humidity: " + currentWeather.getHumidity() + "%";
        humidityMain.setText(humidity);

        String uv = "UV Index: " + currentWeather.getUvindex();
        uvindexMain.setText(uv);

        String vi = "";
        if(measurement.equals("metric")){
            vi = "Visibility: " + currentWeather.getVisibility() + " km";
        }
        else if(measurement.equals("us")) {
            vi = "Visibility: " + currentWeather.getVisibility() + " mi";
        }
        visibilityMain.setText(vi);

        int icon = weatherIcon(currentWeather.getIcon());
        if(icon != 0){
            weatherIconMain.setImageResource(icon);
        }
        else{
            Log.d(TAG, "parseCurrentRecord: CANNOT FIND ICON " + icon);
        }

        long milliSunrise = (long) currentWeather.getSunriseEpoch() * 1000;
        long milliSunset = (long) currentWeather.getSunsetEpoch() * 1000;
        Date sr = new Date(milliSunrise);
        Date ss = new Date(milliSunset);
        SimpleDateFormat timeOnly = new SimpleDateFormat("h:mm a", Locale.getDefault());
        String sunriseStr = "Sunrise: " + timeOnly.format(sr);
        String sunsetStr = "Sunset: " + timeOnly.format(ss);
        sunriseMain.setText(sunriseStr);
        sunsetMain.setText(sunsetStr);


        ArrayList<HourlyWeather> tempArray = daysWeather.get(0).getHourlyWeatherArrayList();
        tempArray.addAll(tempArray.size(), daysWeather.get(1).getHourlyWeatherArrayList());

        double morningTemp = tempArray.get(8).getTemperature();
        String morningStr = String.format("%.0f %s\nMorning", morningTemp, temperatureUnit);
        morningMain.setText(morningStr);

        double afternoonTemp = tempArray.get(13).getTemperature();
        String afternoonStr = String.format("%.0f %s\nAfternoon", afternoonTemp, temperatureUnit);
        afternoonMain.setText(afternoonStr);

        double eveningTemp = tempArray.get(17).getTemperature();
        String eveningStr = String.format("%.0f %s\nEvening", eveningTemp, temperatureUnit);
        eveningMain.setText(eveningStr);


        double nightTemp = tempArray.get(tempArray.size() - 1).getTemperature();
        String nightStr = String.format("%.0f %s\nNight", nightTemp, temperatureUnit);
        nightMain.setText(nightStr);

        //Wind
        double wdir = currentWeather.getWindDir();
        String sdir = getDirection(wdir);
        double wgust = currentWeather.getWindgust();
        String sgust = "";
        if(wgust == -1){
            sgust = ", No wind gust";
        }
        else{
            sgust = String.format("gusting to %.1f %s", wgust, windMeasurement);
        }
        double wspeed = currentWeather.getWindspeed();
        String sspeed = String.format("at %.1f %s", wspeed, windMeasurement);
        String wind = String.format("Winds: %s at %s %s", sdir, sspeed, sgust);
        windsMain.setText(wind);
        //ArrayList<HourlyWeather> hourlyWeatherArrayList = new ArrayList<>();
        /*for(int i = 0 ;i<tempArray.size();i++){
            HourlyWeather hw = new HourlyWeather();
            hw.setTemperature(tempArray.get(i).getTemperature());
            hw.setIcon(tempArray.get(i).getIcon());
            hw.setDateTimeEpoch(tempArray.get(i).getDateTimeEpoch());
            hw.setConditions(tempArray.get(i).getConditions());
            hourlyWeatherArrayList.add(hw);
        }*/
        hourlyWeatherAdapter = new HourlyWeatherAdapter(tempArray, this, this.temperatureUnit);
        recycler_view_hourly.setAdapter(hourlyWeatherAdapter);

        //hourlyWeatherList.addAll(daysWeather.get(0).getHourlyWeatherArrayList());
        //hourlyWeatherAdapter = new HourlyWeatherAdapter(hourlyWeatherList, this);
        //recycler_view_hourly.setAdapter(hourlyWeatherAdapter);
        //hourlyWeatherAdapter = new HourlyWeatherAdapter()
        hourlyWeatherAdapter.notifyItemRangeChanged(0,tempArray.size());

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

    private void setMainLayoutVisible(){
        temperatureMain.setVisibility(View.VISIBLE);
        weatherIconMain.setVisibility(View.VISIBLE);
        recycler_view_hourly.setVisibility(View.VISIBLE);
        feelsLikeMain.setVisibility(View.VISIBLE);
        descriptionMain.setVisibility(View.VISIBLE);
        windsMain.setVisibility(View.VISIBLE);
        humidityMain.setVisibility(View.VISIBLE);
        uvindexMain.setVisibility(View.VISIBLE);
        visibilityMain.setVisibility(View.VISIBLE);
        morningMain.setVisibility(View.VISIBLE);
        afternoonMain.setVisibility(View.VISIBLE);
        eveningMain.setVisibility(View.VISIBLE);
        nightMain.setVisibility(View.VISIBLE);
        sunriseMain.setVisibility(View.VISIBLE);
        sunsetMain.setVisibility(View.VISIBLE);
    }

    private String getDirection(double degrees) {
        if (degrees >= 337.5 || degrees < 22.5)
            return "N";
        if (degrees >= 22.5 && degrees < 67.5)
            return "NE";
        if (degrees >= 67.5 && degrees < 112.5)
            return "E";
        if (degrees >= 112.5 && degrees < 157.5)
            return "SE";
        if (degrees >= 157.5 && degrees < 202.5)
            return "S";
        if (degrees >= 202.5 && degrees < 247.5)
            return "SW";
        if (degrees >= 247.5 && degrees < 292.5)
            return "W";
        if (degrees >= 292.5 && degrees < 337.5)
            return "NW";
        return "X"; // We'll use 'X' as the default if we get a bad value
    }

    public int weatherIcon(String iconText){
        int icon = this.getResources().getIdentifier(iconText, "drawable", this.getPackageName());
        if(icon!=0){
            return icon;
        }
        else
            return 0;
    }

    protected void onPause() {
        // notes list will get stored in local file
        if(!hasNetworkConnection()){
            setNoInternetMainLayout();
        }
        else{
            updateData(this.daysWeathers, this.currentWeather, this.city);
        }

        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // select layout for menu items to show
        getMenuInflater().inflate(R.menu.weather_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(!hasNetworkConnection()){
            setNoInternetMainLayout();
            Toast.makeText(this, "Cannot use menu items when no Internet", Toast.LENGTH_SHORT).show();
            return true;
        }
        setMainLayoutVisible();
        int selected = item.getItemId();
        if(selected == R.id.unitToggle){
            if (unitToggle){
                item.setIcon(R.drawable.units_c);
                temperatureUnit = "°C";
                unitToggle = false;
                measurement = "metric";
                windMeasurement = "kmh";
                downloadWeatherForCity(this.city);
                //weatherDataDownloader.getCrossingWeatherData("Chicago,IL",measurement);
                //Objects.requireNonNull(this.getSupportActionBar()).setTitle("Chicago,IL");
                //updateData(this.daysWeathers, this.currentWeather);
            }
            else {
                item.setIcon(R.drawable.units_f);
                temperatureUnit = "°F";
                unitToggle = true;
                measurement = "us";
                windMeasurement = "mph";
                downloadWeatherForCity(this.city);
                //weatherDataDownloader.getCrossingWeatherData("Chicago,IL",measurement);
                //Objects.requireNonNull(this.getSupportActionBar()).setTitle("Chicago,IL");
                //updateData(this.daysWeathers, this.currentWeather);
            }
            return true;
        }
        else if(selected == R.id.dailyToggle){
            Intent intent = new Intent(this,DailyWeatherDataActivity.class);
            intent.putExtra("dailyArray", this.daysWeathers);
            intent.putExtra("tempunit", this.temperatureUnit);
            startActivity(intent);
            return true;
        }
        else if(selected == R.id.location){
            //WeatherDataDownloader wd = new WeatherDataDownloader(this);
            EditText location = new EditText(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(location);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String text = location.getText().toString();
                    if(!text.isEmpty()){
                        text = text.trim().replaceAll(", ", ",");
                        //wd.getCrossingWeatherData();
                        downloadWeatherForCity(text);
                        //Objects.requireNonNull(this.getSupportActionBar()).setTitle("Chicago,IL");
                    }
                    else {
                        showMessageLocationNull();
                        //Toast.makeText(this, "Cannot use menu items when no Internet", Toast.LENGTH_SHORT).show();
                    }
                    //finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            });


            builder.setTitle("Enter Location");
            builder.setMessage("Enter city");

            AlertDialog dialog = builder.create();
            dialog.show();
            //download();
            //return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
        //download();
        return true;
    }

    public void showMessageLocationNull(){
        Toast.makeText(this, "Enter city name", Toast.LENGTH_SHORT).show();
    }
}