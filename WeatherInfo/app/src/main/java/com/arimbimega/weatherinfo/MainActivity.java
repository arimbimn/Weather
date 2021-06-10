package com.arimbimega.weatherinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.arimbimega.weatherinfo.adapter.ForecastAdapter;
import com.arimbimega.weatherinfo.model.Current;
import com.arimbimega.weatherinfo.model.Forecast;
import com.arimbimega.weatherinfo.model.ForecastModel;
import com.arimbimega.weatherinfo.model.Forecastday;
import com.arimbimega.weatherinfo.model.Hour;
import com.arimbimega.weatherinfo.model.WeatherModel;
import com.arimbimega.weatherinfo.retrofit.ApiEndPoint;
import com.arimbimega.weatherinfo.retrofit.ApiService;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    TextView wind, pressure, precip, humidity, cloud, gust, condition, temp, update, loc, co, no2, o3, so2, ystDate, ystTemp, ystCondition;
    ImageView imgCondition, ystConditionImg;

    private RecyclerView mrecyclerView;
    private ForecastAdapter forecastAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static MainActivity ma;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Current Weather (HomeScreen)
        wind = findViewById(R.id.wind_kph);
        pressure = findViewById(R.id.pressure_mb);
        precip = findViewById(R.id.precip_mb);
        humidity = findViewById(R.id.humidity);
        cloud = findViewById(R.id.cloud);
        gust = findViewById(R.id.gust_kph);
        condition = findViewById(R.id.condition);
        temp = findViewById(R.id.temp_c);
        update = findViewById(R.id.last_update);
        loc = findViewById(R.id.loc);
        imgCondition = findViewById(R.id.condition_img);
        co = findViewById(R.id.co_current);
        no2 = findViewById(R.id.no2_current);
        o3 = findViewById(R.id.o3_current);
        so2 = findViewById(R.id.so2_current);

        ystDate = findViewById(R.id.yesterday_date);
        ystTemp = findViewById(R.id.yesterday_temp);
        ystCondition = findViewById(R.id.yesterday_condition);
        ystConditionImg = findViewById(R.id.condition_img_yesterday);


        mrecyclerView = (RecyclerView)findViewById(R.id.fcRecycleview);

        mLayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mLayoutManager);

        ma = this;


        getCurrentWeather();
        getForecastWeather();
        getYesterdayData();



    }




    private void getCurrentWeather(){

        ApiService.endPoint().getWeatherData()
                .enqueue(new Callback<WeatherModel>() {
                    @Override
                    public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {

                        if (response.isSuccessful()) {

                            Glide.with(MainActivity.this)
                                    .load("http:" + response.body().getCurrent().getCondition().getIcon() )
                                    .into(imgCondition);

                            loc.setText(response.body().getLocation().getName());
                            wind.setText(response.body().getCurrent().getWind_kph());
                            pressure.setText(response.body().getCurrent().getPressure_mb());
                            precip.setText(response.body().getCurrent().getPrecip_mm());
                            humidity.setText(response.body().getCurrent().getHumidity());
                            cloud.setText(response.body().getCurrent().getCloud());
                            gust.setText(response.body().getCurrent().getGust_kph());
                            condition.setText(response.body().getCurrent().getCondition().getText());
                            temp.setText(response.body().getCurrent().getTemp_c());
                            update.setText(response.body().getCurrent().getLast_updated());
                            co.setText("CO :" + " " + response.body().getCurrent().getAirQuality().getCo());
                            no2.setText("NO2 :" + " " + response.body().getCurrent().getAirQuality().getNo2());
                            o3.setText("O3 :" + " " + response.body().getCurrent().getAirQuality().getO3());
                            so2.setText("SO2 :" + " " + response.body().getCurrent().getAirQuality().getSo2());


                        }



                    }

                    @Override
                    public void onFailure(Call<WeatherModel> call, Throwable t) {

                        Log.d(TAG, t.toString());

                    }
                });

    }

    private void showSelectedForecast(Forecastday forecastday) {

        //Kirim data dari main activity yang sudah disimpan di ForecastAdapter ke detail activity
        Intent kirimData = new Intent(MainActivity.this, DetailForecastActivity.class);
        kirimData.putExtra(DetailForecastActivity.EXTRA_AVGTEMP_DETAIL,Double.toString(forecastday.getDayForecast().getAvgtemp_c()));
        kirimData.putExtra(DetailForecastActivity.EXTRA_DATE_DETAIL, forecastday.getDate());
        kirimData.putExtra(DetailForecastActivity.EXTRA_MAX_TEMP, forecastday.getDayForecast().getMaxtemp_c());
        kirimData.putExtra(DetailForecastActivity.EXTRA_MIN_TEMP, forecastday.getDayForecast().getMintemp_c());
        kirimData.putExtra(DetailForecastActivity.EXTRA_MAX_WIND, forecastday.getDayForecast().getMaxwind_kph());
        kirimData.putExtra(DetailForecastActivity.EXTRA_TOTAL_PRECIP, forecastday.getDayForecast().getTotalprecip_mm());
        kirimData.putExtra(DetailForecastActivity.EXTRA_CONDITION_DETAIL, forecastday.getDayForecast().getConditionForecast().getText());
        kirimData.putExtra(DetailForecastActivity.EXTRA_ICON_DETAIL, "http:" + forecastday.getDayForecast().getConditionForecast().getIcon());

        //kirim data dari main activity yang sudah disimpan di HourlyForecastdapter ke detail activity
        kirimData.putExtra("hourList", forecastday.getHourForecast());

        startActivity(kirimData);
    }

    private void getForecastWeather(){

        ApiService.endPoint().getForecastData()
                .enqueue(new Callback<ForecastModel>() {
                    @Override
                    public void onResponse(Call<ForecastModel> call, Response<ForecastModel> response) {

                        if(response.isSuccessful()){

                            //fcDay.setText(response.code());


                            List<Forecastday> forecastdays = response.body().getForecast().getListforecastday();
                            Log.d("Checking","Data forecast masuk" + String.valueOf(forecastdays.size()));

                            forecastAdapter = new ForecastAdapter(forecastdays);
                            forecastAdapter.notifyDataSetChanged();
                            mrecyclerView.setAdapter(forecastAdapter);

                            //Onclick
                            forecastAdapter.setOnItemClickCallback(new ForecastAdapter.OnItemClickCallback() {
                                @Override
                                public void onItemClicked(Forecastday data) {
                                    showSelectedForecast(data);
                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastModel> call, Throwable t) {

                        Log.e(TAG, t.toString());

                    }
                });

    }

    private void getYesterdayData(){

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = dateFormat.format(cal.getTime());

        Log.d(TAG, String.valueOf(yesterday));

        ApiService.endPoint().getYesterdayData("history.json?key=2725bee3e3c74df88e6135501212305&q=Jakarta&dt=" + yesterday)
                .enqueue(new Callback<ForecastModel>() {
                    @Override
                    public void onResponse(Call<ForecastModel> call, Response<ForecastModel> response) {

                        ystDate.setText(response.body().getForecast().getListforecastday().get(0).getDate());
                        ystTemp.setText(Double.toString(response.body().getForecast().getListforecastday().get(0).getDayForecast().getAvgtemp_c()));
                        ystCondition.setText(response.body().getForecast().getListforecastday().get(0).getDayForecast().getConditionForecast().getText());

                        Glide.with(MainActivity.this)
                                .load("http:" + response.body().getForecast().getListforecastday().get(0).getDayForecast().getConditionForecast().getIcon())
                                .into(ystConditionImg);

                    }

                    @Override
                    public void onFailure(Call<ForecastModel> call, Throwable t) {


                    }
                });
    }
}