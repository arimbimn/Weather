package com.arimbimega.weatherinfo.model;

import com.google.gson.annotations.SerializedName;

public class AirQuality {

    @SerializedName("co")
    private String co;

    @SerializedName("no2")
    private String no2;

    @SerializedName("o3")
    private String o3;

    @SerializedName("so2")
    private String so2;

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }
}
