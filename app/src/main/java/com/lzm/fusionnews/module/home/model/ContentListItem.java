package com.lzm.fusionnews.module.home.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lzm on 2018/4/12.
 */

public class ContentListItem {
    public String contentBgcolor;
    public int adType;
    public String startVideo;
    public int hasReading;
    public String title;
    public String picInfo;
    public int number;
    public String videoUrl;
    public String contentType;
    public int serialId;
    public String id;
    public String adClosetime;
    public String lastUpdateDate;
    public String orientation;
    @SerializedName("like_count")
    public int likeCount;
    @SerializedName("item_id")
    public int itemId;
    public String adPvurlVendor;
    @SerializedName("content_id")
    public String contentId;
    public String forward;
    public String wordsInfo;
    public int audioPlatform;
    public String volume;
    public int adId;
    public String adPvurl;
    public String adShareCnt;
    public String adLinkurl;
    public String imgUrl;
    @SerializedName("post_date")
    public String postDate;
    public String shareUrl;
    public String subtitle;
    public String audioUrl;
    public int movieStoryId;
    public String category;
    public String displayCategory;
    public String adMakettime;
}
