package com.arimbimega.weatherinfo.model;

import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("avgtemp_c")
    private Double avgtemp_c;

    @SerializedName("condition")
    private Condition conditionForecast;

    @SerializedName("maxtemp_c")
    private String maxtemp_c;

    @SerializedName("mintemp_c")
    private String mintemp_c;

    @SerializedName("maxwind_kph")
    private String maxwind_kph;

    @SerializedName("totalprecip_mm")
    private String totalprecip_mm;


    public Double getAvgtemp_c() {
        return avgtemp_c;
    }

    public void setAvgtemp_c(Double avgtemp_c) {
        this.avgtemp_c = avgtemp_c;
    }

    public Condition getConditionForecast() {
        return conditionForecast;
    }

    public void setConditionForecast(Condition conditionForecast) {
        this.conditionForecast = conditionForecast;
    }

    public String getMaxtemp_c() {
        return maxtemp_c;
    }

    public void setMaxtemp_c(String maxtemp_c) {
        this.maxtemp_c = maxtemp_c;
    }

    public String getMintemp_c() {
        return mintemp_c;
    }

    public void setMintemp_c(String mintemp_c) {
        this.mintemp_c = mintemp_c;
    }

    public String getMaxwind_kph() {
        return maxwind_kph;
    }

    public void setMaxwind_kph(String maxwind_kph) {
        this.maxwind_kph = maxwind_kph;
    }

    public String getTotalprecip_mm() {
        return totalprecip_mm;
    }

    public void setTotalprecip_mm(String totalprecip_mm) {
        this.totalprecip_mm = totalprecip_mm;
    }
}
