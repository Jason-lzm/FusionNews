package com.lzm.fusionnews.module.home.ui;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.lzm.fusionnews.R;
import com.lzm.fusionnews.annotation.ActivityFragmentAnnotation;
import com.lzm.fusionnews.base.BaseActivity;
import com.lzm.fusionnews.common.Constants;
import com.lzm.fusionnews.module.home.ArticleContract;
import com.lzm.fusionnews.module.home.model.ArticleDetailBean;
import com.lzm.fusionnews.module.home.model.QuestionDetailBean;
import com.lzm.fusionnews.module.home.model.SerialDetailBean;
import com.lzm.fusionnews.module.home.presenter.ArticleDetailPresenter;


@ActivityFragmentAnnotation(contentViewId = R.layout.activity_article_detail)
public class ArticleDetailActivity extends BaseActivity<ArticleDetailPresenter> implements ArticleContract.View{

    TextView tv_article_title;
    TextView tv_content_title;
    TextView tv_content_author;
    TextView tv_content;
    TextView tv_question_content;
    TextView tv_answer_author;
    View divider;
    int itemId;
    String category;

    @Override
    protected void initView() {
        itemId = getIntent().getIntExtra("item_id", 0);
        category = getIntent().getStringExtra("category");

        tv_article_title = (TextView) findViewById(R.id.tv_article_title);
        tv_content_title = (TextView) findViewById(R.id.tv_content_title);
        tv_content_author = (TextView) findViewById(R.id.tv_content_author);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_question_content = (TextView) findViewById(R.id.tv_question_content);
        tv_answer_author = (TextView) findViewById(R.id.tv_answer_author);
        divider = findViewById(R.id.divider);

        switch (category){
            case Constants.CATEGORY_ESSAY:
                tv_article_title.setText(Constants.ARTICLE_TITLE_ESSAY);
                break;
            case Constants.CATEGORY_SERIAL:
                tv_article_title.setText(Constants.ARTICLE_TITLE_SERIAL);
                break;
            default:
                tv_article_title.setText(Constants.ARTICLE_TITLE_QUESTION);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter = new ArticleDetailPresenter(this);
        mPresenter.start(category, itemId+"");
    }

    @Override
    public void setPresenter(ArticleContract.Presenter present) {

    }

    @Override
    public void setArticle(ArticleDetailBean articleDetailBean) {
        ArticleDetailBean.Data data = articleDetailBean.data;
        tv_content_title.setText(data.hpTitle);
        tv_content_author.setText("作者：" + data.author.get(0).userName);
        tv_content.setText(Html.fromHtml(data.hpContent));
    }

    @Override
    public void setSerial(SerialDetailBean serialDetailBean) {
        SerialDetailBean.Data data = serialDetailBean.data;
        tv_content_title.setText(data.title);
        tv_content_author.setText("作者：" + data.author.userName);
        tv_content.setText(Html.fromHtml(data.content));
    }

    @Override
    public void setQuestion(QuestionDetailBean questionDetailBean) {
        QuestionDetailBean.Data data = questionDetailBean.data;
        tv_content_title.setText(data.questionTitle);
        tv_question_content.setVisibility(View.VISIBLE);
        divider.setVisibility(View.VISIBLE);
        tv_question_content.setText(data.questionContent);
        tv_answer_author.setText(data.answerer.userName + "：");
        tv_content.setText(Html.fromHtml(data.answerContent));
    }
}
