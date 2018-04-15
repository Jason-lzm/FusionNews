package com.lzm.fusionnews.module.home.model;

import com.lzm.fusionnews.net.Api;
import com.lzm.fusionnews.net.RequestManager;
import com.lzm.fusionnews.net.RequestParams;

import okhttp3.Callback;

/**
 * Created by lzm on 2018/4/14.
 */

public class ArticleModel {
    //获取最新 idlist, 以获取今日或往日的 onelist 信息
    String IDLIST_URL = "onelist/idlist/?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";
    //获取 onelist,其中data替换成上面的idlist中的数据，代表最近一周的某一天
    String ONELIST_URL = "onelist/data/0?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";
    //获取故事详细信息，其中item_id替换成onelist中的item_id值
    String DETAIL_URL = "essay/item_id?channel=wdj&source=summary&source_id=9261&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";
    //获取评论信息，其中item_id替换成onelist中的item_id值
    String COMMENT_URL = "comment/praiseandtime/essay/item_id/0?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";
    //类别1 短文
    String ESSAY = "essay";
    //类别2 连载
    String SERIAL = "serialcontent";
    //类别3 问答
    String QUESTION = "question";

    private RequestManager requestManager;

    public ArticleModel() {
        requestManager = RequestManager.getInstance();
    }

    public void getIdList(Callback callback){
        String url = Api.BASE_ARTICLE_URL + IDLIST_URL;
        RequestParams requestParams = new RequestParams.Builder().setUrl(url).build();
        requestManager.getAsync(requestParams, callback);
    }

    public void getArticleList(String data, Callback callback){
        String url = Api.BASE_ARTICLE_URL + ONELIST_URL.replace("data", data);
        RequestParams requestParams = new RequestParams.Builder().setUrl(url).build();
        requestManager.getAsync(requestParams, callback);
    }
}
