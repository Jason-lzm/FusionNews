package com.lzm.fusionnews.module.home.presenter;

import com.lzm.fusionnews.module.home.HomeContract;
import com.lzm.fusionnews.module.home.model.ArticleModel;
import com.lzm.fusionnews.module.home.model.ArticleListBean;
import com.lzm.fusionnews.module.home.model.IdListBean;
import com.lzm.fusionnews.net.NetCallback;

import java.io.IOException;
import java.util.List;

import timber.log.Timber;

/**
 * Created by lzm on 2018/4/14.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private ArticleModel model;
    private List<String> mData;
    public HomePresenter(HomeContract.View view) {
        mView = view;
        view.setPresenter(this);
        model = new ArticleModel();
    }

    @Override
    public void start() {
        if(mView.isActive()){
            loadData();
        }
    }

    @Override
    public void detachView() {

    }

    @Override
    public void loadData() {
        model.getIdList(new NetCallback<IdListBean>(IdListBean.class) {
            @Override
            public void onSuccess(IdListBean result) {
                if(result.data.size() > 0){
                    mData = result.data;
                    loadList(mData.get(0));
                }
            }

            @Override
            public void onFailed(IOException e) {
                Timber.e("getIdList Failed, exception:" +e);
            }
        });
    }

    @Override
    public void loadList(String data) {
        model.getArticleList(data, new NetCallback<ArticleListBean>(ArticleListBean.class) {
            @Override
            public void onSuccess(ArticleListBean result) {
                mView.updateHomeList(result);
            }

            @Override
            public void onFailed(IOException e) {
                Timber.e("getArticleList Failed, exception:" +e);
            }
        });
    }

    @Override
    public void loadMore(int index) {
        if(mData != null){
            loadList(mData.get(index));
        }
    }
}
