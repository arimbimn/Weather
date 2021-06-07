package com.arimbimega.weatherinfo.model;

import com.google.gson.annotations.SerializedName;

public class ForecastModel {

    @SerializedName("current")
    private Current currentForecast;

    @SerializedName("location")
    private Location locationForecast;

    @SerializedName("forecast")
    private Forecast forecast;


    public Current getCurrentForecast() {
        return currentForecast;
    }

    public void setCurrentForecast(Current currentForecast) {
        this.currentForecast = currentForecast;
    }

    public Location getLocationForecast() {
        return locationForecast;
    }

    public void setLocationForecast(Location locationForecast) {
        this.locationForecast = locationForecast;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

}
