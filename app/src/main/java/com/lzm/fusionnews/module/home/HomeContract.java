package com.lzm.fusionnews.module.home;

import android.content.Context;

import com.lzm.fusionnews.base.BasePresenter;
import com.lzm.fusionnews.base.BaseView;
import com.lzm.fusionnews.module.home.model.ArticleResult;
import com.lzm.fusionnews.module.home.model.ContentListItem;

/**
 * Created by lzm on 2018/4/12.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter>{
        boolean isActive();
        void updateHomeList(ArticleResult articleResult);
    }

    interface Presenter extends BasePresenter{
        void loadData();
        void loadList(String data);
        void loadMore(int index);
    }
}
