package com.lzm.fusionnews.module;

import com.lzm.fusionnews.R;
import com.lzm.fusionnews.annotation.ActivityFragmentAnnotation;
import com.lzm.fusionnews.base.BaseActivity;
import com.lzm.fusionnews.manager.FragmentController;
import com.lzm.fusionnews.module.home.HomeContract;
import com.lzm.fusionnews.module.home.presenter.HomePresent;
import com.lzm.fusionnews.module.home.ui.HomeFragment;

@ActivityFragmentAnnotation(contentViewId = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private FragmentController mFragmentController;

    private HomePresent mHomePresent;
    //private

    @Override
    protected void initView() {
        mFragmentController = FragmentController.getInstance(this, R.id.fl_content, false);
        mHomePresent = new HomePresent((HomeContract.View) mFragmentController.getFragment(0));
        mFragmentController.showFragment(0);
    }
}
