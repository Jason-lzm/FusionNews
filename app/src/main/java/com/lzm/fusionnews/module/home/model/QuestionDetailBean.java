package com.lzm.fusionnews.module.home.model;

import com.google.gson.annotations.SerializedName;
import com.lzm.fusionnews.base.BaseBean;

/**
 * Created by lzm on 2018/4/21.
 */

public class QuestionDetailBean extends BaseBean<QuestionDetailBean.Data> {
    public class Data{
        @SerializedName("question_title")
        public String questionTitle;
        @SerializedName("question_content")
        public String questionContent;
        public Answerer answerer;
        @SerializedName("answer_content")
        public String answerContent;
    }

    public class Answerer{
        @SerializedName("user_name")
        public String  userName;
    }
}
