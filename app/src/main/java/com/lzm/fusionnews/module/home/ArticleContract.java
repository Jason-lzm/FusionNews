package com.lzm.fusionnews.module.home;

import com.lzm.fusionnews.base.BasePresenter;
import com.lzm.fusionnews.base.BaseView;
import com.lzm.fusionnews.module.home.model.ArticleDetailBean;
import com.lzm.fusionnews.module.home.model.QuestionDetailBean;
import com.lzm.fusionnews.module.home.model.SerialDetailBean;

/**
 * Created by lzm on 2018/4/12.
 */

public interface ArticleContract {
    interface View extends BaseView<Presenter>{
        void setArticle(ArticleDetailBean articleDetailBean);
        void setSerial(SerialDetailBean serialDetailBean);
        void setQuestion(QuestionDetailBean questionDetailBean);
    }

    interface Presenter extends BasePresenter{
        void start(String category, String item_id);
        void loadArticleDetail(String item_id);
        void loadSerialDetail(String item_id);
        void loadQuestionDetail(String item_id);
    }
}
