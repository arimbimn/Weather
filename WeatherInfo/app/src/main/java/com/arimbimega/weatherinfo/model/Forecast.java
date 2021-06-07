package com.arimbimega.weatherinfo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {

    @SerializedName("forecastday")
    private List<Forecastday> listforecastday;

    public List<Forecastday> getListforecastday() {
        return listforecastday;
    }

    public void setListforecastday(List<Forecastday> listforecastday) {
        this.listforecastday = listforecastday;
    }
}
