package com.lzm.fusionnews.module.home.model;

import com.google.gson.annotations.SerializedName;
import com.lzm.fusionnews.base.BaseBean;

import java.util.List;

/**
 * Created by lzm on 2018/4/14.
 */

public class ArticleListBean extends BaseBean<ArticleListBean.Data> {
    public class Data {
        public String date;
        @SerializedName("content_list")
        public List<ContentListItem> contentList;
        public Weather weather;
        public String id;
    }
}
