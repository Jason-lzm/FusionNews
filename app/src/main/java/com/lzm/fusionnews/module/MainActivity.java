package com.lzm.fusionnews.module;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzm.fusionnews.R;
import com.lzm.fusionnews.annotation.ActivityFragmentAnnotation;
import com.lzm.fusionnews.base.BaseActivity;
import com.lzm.fusionnews.manager.FragmentController;
import com.lzm.fusionnews.module.home.HomeContract;
import com.lzm.fusionnews.module.home.presenter.HomePresenter;

@ActivityFragmentAnnotation(contentViewId = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private FragmentController mFragmentController;

    private HomePresenter mHomePresent;
    //private
    private LinearLayout llBottom;
    private ImageView ivIconHome;
    private TextView tvTextHome;
    private View lastSelectedIcon;
    private View lastSelectedText;

    @Override
    protected void initView() {
        llBottom = (LinearLayout) findViewById(R.id.llBottom);
        ivIconHome = (ImageView) findViewById(R.id.ivIconHome);
        tvTextHome = (TextView) findViewById(R.id.tvTextHome);
        setListener();

        mFragmentController = FragmentController.getInstance(this, R.id.fl_content, false);
        mHomePresent = new HomePresenter((HomeContract.View) mFragmentController.getFragment(0));
        mFragmentController.showFragment(0);
    }

    protected void setListener() {
        for (int i = 0; i < llBottom.getChildCount(); i++) {
            if (i == 0) {
                //默认选中首页
                setSelectIcon(ivIconHome, tvTextHome);
            }
            final int position = i;
            llBottom.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastSelectedIcon != null) lastSelectedIcon.setSelected(false);
                    if (lastSelectedText != null) lastSelectedText.setSelected(false);

                    RelativeLayout rl = (RelativeLayout) v;
                    ImageView icon = (ImageView) rl.getChildAt(0);
                    TextView text = (TextView) rl.getChildAt(1);
                    mFragmentController.showFragment(position);
                    setSelectIcon(icon, text);
                }
            });
        }
    }

    private void setSelectIcon(ImageView iv, TextView tv) {
        iv.setSelected(true);
        tv.setSelected(true);
        lastSelectedIcon = iv;
        lastSelectedText = tv;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragmentController.onDestroy();
    }
}
