package com.lzm.fusionnews.module.home.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lzm on 2018/4/18.
 */

public class AuthorItem {
    public String summary;
    @SerializedName("wb_name")
    public String wbName;
    @SerializedName("web_url")
    public String webUrl;
    @SerializedName("user_id")
    public String userId;
    @SerializedName("user_name")
    public String userName;
    @SerializedName("fans_total")
    public String fansTotal;
    public String desc;
}
