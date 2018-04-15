package com.lzm.fusionnews.module.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lzm on 2018/4/14.
 */

public class Data {
    public String date;
    @SerializedName("content_list")
    public List<ContentListItem> contentList;
    public Weather weather;
    public String id;
}
