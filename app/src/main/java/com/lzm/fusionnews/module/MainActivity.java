package com.lzm.fusionnews.module;

import com.lzm.fusionnews.R;
import com.lzm.fusionnews.annotation.ActivityFragmentAnnotation;
import com.lzm.fusionnews.base.BaseActivity;
import com.lzm.fusionnews.manager.FragmentController;

@ActivityFragmentAnnotation(contentViewId = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private FragmentController mFragmentController;

    //private

    @Override
    protected void initView() {
        mFragmentController = FragmentController.getInstance(this, R.id.fl_content, false);
        mFragmentController.showFragment(0);
    }
}
