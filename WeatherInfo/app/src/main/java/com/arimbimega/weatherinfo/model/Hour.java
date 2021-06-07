package com.arimbimega.weatherinfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Hour implements Parcelable {

    @SerializedName("time")
    private String time;

    @SerializedName("temp_c")
    private String temp_c;

    @SerializedName("condition")
    private Condition ConditionHour;

    protected Hour(Parcel in) {
        time = in.readString();
        temp_c = in.readString();
        ConditionHour =in.readParcelable(Condition.class.getClassLoader());
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }

    public Condition getConditionHour() {
        return ConditionHour;
    }

    public void setConditionHour(Condition conditionHour) {
        ConditionHour = conditionHour;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeString(temp_c);
        dest.writeParcelable(ConditionHour, flags);
    }
}
