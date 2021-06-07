package com.arimbimega.weatherinfo.retrofit;

import com.arimbimega.weatherinfo.model.Condition;
import com.arimbimega.weatherinfo.model.ForecastModel;
import com.arimbimega.weatherinfo.model.WeatherModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {

    @GET("current.json?key=2725bee3e3c74df88e6135501212305&q=Jakarta&aqi=no")
    Call<WeatherModel> getWeatherData();

    @GET("forecast.json?key=2725bee3e3c74df88e6135501212305&q=Jakarta&days=3&aqi=no&alerts=no")
    Call<ForecastModel> getForecastData();

}
