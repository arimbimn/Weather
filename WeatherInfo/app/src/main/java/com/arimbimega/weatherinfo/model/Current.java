package com.arimbimega.weatherinfo.model;

import com.google.gson.annotations.SerializedName;

public class Current {

    @SerializedName("wind_kph")
    private String wind_kph;

    @SerializedName("pressure_mb")
    private String pressure_mb;

    @SerializedName("precip_mm")
    private String precip_mm;

    @SerializedName("humidity")
    private String humidity;

    @SerializedName("cloud")
    private String cloud;

    @SerializedName("gust_kph")
    private String gust_kph;

    @SerializedName("temp_c")
    private String temp_c;

    @SerializedName("last_updated")
    private String last_updated;

    @SerializedName("condition")
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(String wind_kph) {
        this.wind_kph = wind_kph;
    }

    public String getPressure_mb() {
        return pressure_mb;
    }

    public void setPressure_mb(String pressure_mb) {
        this.pressure_mb = pressure_mb;
    }

    public String getPrecip_mm() {
        return precip_mm;
    }

    public void setPrecip_mm(String precip_mm) {
        this.precip_mm = precip_mm;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getGust_kph() {
        return gust_kph;
    }

    public void setGust_kph(String gust_kph) {
        this.gust_kph = gust_kph;
    }

    public String getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }


}
