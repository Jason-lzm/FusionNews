package com.lzm.fusionnews.module.home.model;

import com.google.gson.annotations.SerializedName;
import com.lzm.fusionnews.base.BaseBean;

import java.util.List;

/**
 * Created by lzm on 2018/4/18.
 */

public class ArticleDetailBean extends BaseBean<ArticleDetailBean.Data> {

    public class Data {
        @SerializedName("hp_makettime")
        public String hpMakettime;
        public String copyright;
        @SerializedName("audio_duration")
        public String audioDuration;
        public String maketime;
        @SerializedName("guide_word")
        public String guideWord;
        @SerializedName("hp_title")
        public String hpTitle;
        @SerializedName("start_video")
        public String startVideo;
        @SerializedName("hp_content")
        public String hpContent;
        public List<AuthorItem> author;
    }
}
