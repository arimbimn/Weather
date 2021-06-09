package com.arimbimega.weatherinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.arimbimega.weatherinfo.adapter.ForecastAdapter;
import com.arimbimega.weatherinfo.adapter.HourlyForecastAdapter;
import com.arimbimega.weatherinfo.model.Day;
import com.arimbimega.weatherinfo.model.ForecastModel;
import com.arimbimega.weatherinfo.model.Forecastday;
import com.arimbimega.weatherinfo.model.Hour;
import com.arimbimega.weatherinfo.retrofit.ApiService;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailForecastActivity extends AppCompatActivity {

    public static final String EXTRA_DATE_DETAIL = "extra_date_detai";
    public static final String EXTRA_MAX_TEMP = "extra_max_temp";
    public static final String EXTRA_MIN_TEMP = "extra_min_temp";
    public static final String EXTRA_MAX_WIND = "extra_max_wind";
    public static final String EXTRA_TOTAL_PRECIP = "extra_total_precip";
    public static final String EXTRA_CONDITION_DETAIL = "extra_condition_detail";
    public static final String EXTRA_ICON_DETAIL= "extra_icon_detail";
    public static final String EXTRA_AVGTEMP_DETAIL = "extra_avgtemp_detail";


    private TextView tvDate, tvMaxTemp, tvMinTemp, tvMaxWind, tvTotalPrecip, tvConditionDetail, tvAvgtempDetail;
    private ImageView imgDetailForecast;

    //Hourly Forecast Report
    private RecyclerView mRecyclerview;
    private HourlyForecastAdapter hourlyForecastAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public String dateDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_forecast);

        tvDate = findViewById(R.id.dateDetail);
        tvMaxTemp = findViewById(R.id.maxtemp_c_detail);
        tvMinTemp = findViewById(R.id.mintemp_c_detail);
        tvMaxWind = findViewById(R.id.maxwind_kph_detail);
        tvTotalPrecip = findViewById(R.id.totalprecip_mm_detail);
        tvConditionDetail = findViewById(R.id.condition_detail);
        tvAvgtempDetail = findViewById(R.id.average_forecast_detail);
        imgDetailForecast =findViewById(R.id.img_detail_forecast);

        dateDetail = getIntent().getStringExtra(EXTRA_DATE_DETAIL);
        String maxTempDetail = getIntent().getStringExtra(EXTRA_MAX_TEMP);
        String minTempDetail = getIntent().getStringExtra(EXTRA_MIN_TEMP);
        String maxWindDetail = getIntent().getStringExtra(EXTRA_MAX_WIND);
        String totalPrecipDetail = getIntent().getStringExtra(EXTRA_TOTAL_PRECIP);
        String averageTempDetail = getIntent().getStringExtra(EXTRA_AVGTEMP_DETAIL);
        String conditionDetail = getIntent().getStringExtra(EXTRA_CONDITION_DETAIL);
        String imgDetail = getIntent().getStringExtra(EXTRA_ICON_DETAIL);

        tvDate.setText(dateDetail);
        tvMaxTemp.setText(maxTempDetail);
        tvMinTemp.setText(minTempDetail);
        tvMaxWind.setText(maxWindDetail);
        tvTotalPrecip.setText(totalPrecipDetail);
        tvAvgtempDetail.setText(averageTempDetail);
        tvConditionDetail.setText(conditionDetail);

        Glide.with(DetailForecastActivity.this)
                .load(imgDetail)
                .into(imgDetailForecast);


        //Hourly Forecast Report
        mRecyclerview = (RecyclerView) findViewById(R.id.fcDetailRecycleview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);


        getHourlyWeather();



    }


    private void getHourlyWeather(){

        ApiService.endPoint().getForecastData()
                .enqueue(new Callback<ForecastModel>() {
                    @Override
                    public void onResponse(Call<ForecastModel> call, Response<ForecastModel> response) {

                        if (response.isSuccessful()){

                            List<Forecastday> forecastdays = response.body().getForecast().getListforecastday();
                            Log.d("Checking","Data forecast masuk" + String.valueOf(forecastdays.size()));

                            ArrayList<Hour> hourList = getIntent().getParcelableArrayListExtra("hourList");

                            hourlyForecastAdapter = new HourlyForecastAdapter(hourList);
                            hourlyForecastAdapter.notifyDataSetChanged();
                            mRecyclerview.setAdapter(hourlyForecastAdapter);


                        }

                    }

                    @Override
                    public void onFailure(Call<ForecastModel> call, Throwable t) {

                    }
                });

    }
}