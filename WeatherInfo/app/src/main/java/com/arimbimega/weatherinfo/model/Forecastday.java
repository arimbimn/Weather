package com.arimbimega.weatherinfo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Forecastday {

    @SerializedName("date")
    private String date;

    @SerializedName("day")
    private Day dayForecast;

    @SerializedName("astro")
    private Astro astro;

    @SerializedName("hour")
    private ArrayList<Hour> hourForecast;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Day getDayForecast() {
        return dayForecast;
    }

    public void setDayForecast(Day dayForecast) {
        this.dayForecast = dayForecast;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public ArrayList<Hour> getHourForecast() {
        return hourForecast;
    }

    public void setHourForecast(ArrayList<Hour> hourForecast) {
        this.hourForecast = hourForecast;
    }
}
