package com.lzm.fusionnews.module.home.model;

import com.lzm.fusionnews.base.BaseBean;

/**
 * Created by lzm on 2018/4/21.
 */

public class SerialDetailBean extends BaseBean<SerialDetailBean.Data>{

    public class Data{
        public String title;
        public String content;
        public AuthorItem author;
    }

}
