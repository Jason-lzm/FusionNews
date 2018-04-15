package com.lzm.fusionnews.module.home.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lzm on 2018/4/14.
 */

public class Weather {
    public String date;
    @SerializedName("city_name")
    public String cityName;
    public String temperature;
    public String humidity;
    @SerializedName("wind_direction")
    public String windDirection;
    public String hurricane;
    public String climate;
}
