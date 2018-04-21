package com.lzm.fusionnews.module.home;

import com.lzm.fusionnews.base.BasePresenter;
import com.lzm.fusionnews.base.BaseView;
import com.lzm.fusionnews.module.home.model.ArticleListBean;

/**
 * Created by lzm on 2018/4/12.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter>{
        boolean isActive();
        void updateHomeList(ArticleListBean articleListBean);
    }

    interface Presenter extends BasePresenter{
        void loadData();
        void loadList(String data);
        void loadMore(int index);
    }
}
