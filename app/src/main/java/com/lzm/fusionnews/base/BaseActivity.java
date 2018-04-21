package com.lzm.fusionnews.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.lzm.fusionnews.R;
import com.lzm.fusionnews.annotation.ActivityFragmentAnnotation;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    /**
     * 将代理类通用行为抽出来
     */
    protected T mPresenter;

    /**
     * 布局资源id
     */
    protected int mContentViewId;

    /**
     * 菜单id
     */
    private int mMenuId;

    /**
     * toolbar的标题id
     *
     * @return
     */
    private int mToolbarTitle;
    /**
     * Toolbar左侧按钮的样式
     */
    private int mToolbarIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getClass().isAnnotationPresent(ActivityFragmentAnnotation.class)){
            ActivityFragmentAnnotation annotation = getClass().getAnnotation(ActivityFragmentAnnotation.class);
            mContentViewId = annotation.contentViewId();
            mMenuId = annotation.menuId();
            mToolbarTitle = annotation.toolbarTitle();
            mToolbarIndicator = annotation.toolbarIndicator();
        } else {
            throw new RuntimeException("Class must add annotations of ActivityFragmentInitParams.class");
        }

        setContentView(mContentViewId);
        initView();
        //initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(mMenuId,menu);
        return true;
    }

    protected abstract void initView();

    /*
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mToolbarTitle);
        toolbar.setContentInsetStartWithNavigation(0);
        setSupportActionBar(toolbar);
        if (mToolbarIndicator != -1) {
            toolbar.setNavigationIcon(mToolbarIndicator);
        }
    }
    */
}
