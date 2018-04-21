package com.lzm.fusionnews.module.home.presenter;

import com.lzm.fusionnews.common.Constants;
import com.lzm.fusionnews.module.home.ArticleContract;
import com.lzm.fusionnews.module.home.model.ArticleDetailBean;
import com.lzm.fusionnews.module.home.model.ArticleModel;
import com.lzm.fusionnews.module.home.model.QuestionDetailBean;
import com.lzm.fusionnews.module.home.model.SerialDetailBean;
import com.lzm.fusionnews.net.NetCallback;

import java.io.IOException;

import timber.log.Timber;

/**
 * Created by lzm on 2018/4/18.
 */

public class ArticleDetailPresenter implements ArticleContract.Presenter {

    private ArticleContract.View mView;
    private ArticleModel mModel;

    public ArticleDetailPresenter(ArticleContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mModel = new ArticleModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void start(String category, String item_id) {
        Timber.d("category:" + category);
        switch (category){
            case Constants.CATEGORY_PHOTO:
                break;
            case Constants.CATEGORY_ESSAY:
                loadArticleDetail(item_id);
                break;
            case Constants.CATEGORY_SERIAL:
                loadSerialDetail(item_id);
                break;
            case Constants.CATEGORY_QUESTION:
                loadQuestionDetail(item_id);
                break;
        }
    }

    @Override
    public void loadArticleDetail(String item_id) {
        mModel.getArticleDetail(item_id, new NetCallback<ArticleDetailBean>(ArticleDetailBean.class) {
            @Override
            public void onSuccess(ArticleDetailBean result) {
                mView.setArticle(result);
            }

            @Override
            public void onFailed(IOException e) {
                Timber.e("getArticleDetail Failed, exception:" + e);
            }
        });
    }

    @Override
    public void loadSerialDetail(String item_id) {
        mModel.getSerialDetail(item_id, new NetCallback<SerialDetailBean>(SerialDetailBean.class) {

            @Override
            public void onSuccess(SerialDetailBean result) {
                mView.setSerial(result);
            }

            @Override
            public void onFailed(IOException e) {
                Timber.e("getArticleSerialDetail Failed, exception:" + e);
            }
        });
    }

    @Override
    public void loadQuestionDetail(String item_id) {
        mModel.getQuestionDetail(item_id, new NetCallback<QuestionDetailBean>(QuestionDetailBean.class) {
            @Override
            public void onSuccess(QuestionDetailBean result) {
                mView.setQuestion(result);
            }

            @Override
            public void onFailed(IOException e) {
                Timber.e("getQuestionDetail Failed, exception:" + e);
            }
        });
    }

}
