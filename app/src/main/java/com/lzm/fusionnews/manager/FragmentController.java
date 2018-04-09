package com.lzm.fusionnews.manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.lzm.fusionnews.module.home.ui.fragment.HomeFragment;
import com.lzm.fusionnews.module.me.ui.MeFragment;
import com.lzm.fusionnews.module.photo.ui.PhotoFragment;
import com.lzm.fusionnews.module.video.ui.VideoFragment;

import java.util.ArrayList;

/**
 * Created by lzm on 2018/1/28.
 */

public class FragmentController {

    private int containerId;
    private FragmentManager mFragmentManager;
    private ArrayList<Fragment> mFragments;

    private static FragmentController instance;
    private static boolean isReload;

    public static FragmentController getInstance(FragmentActivity activity, int containerId, boolean isReload) {
        FragmentController.isReload = isReload;
        if (instance == null) {
            instance = new FragmentController(activity, containerId);
        }
        return instance;
    }

    private FragmentController(FragmentActivity activity, int containerId) {
        this.containerId = containerId;
        mFragmentManager = activity.getSupportFragmentManager();
        initFragment();
    }

    private void initFragment(){
        if(!isReload){
            mFragments = new ArrayList<>();
            mFragments.add(new HomeFragment());
            mFragments.add(new VideoFragment());
            mFragments.add(new PhotoFragment());
            mFragments.add(new MeFragment());

            FragmentTransaction ft = mFragmentManager.beginTransaction();
            for (int i = 0; i < mFragments.size(); i++) {
                ft.add(containerId, mFragments.get(i), "" + i);
            }
            ft.commit();
        } else {
            for (int i = 0; i < 5; i++) {
                mFragments.add( mFragmentManager.findFragmentByTag(i+""));
            }
        }
    }

    public void showFragment(int position) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        for (Fragment fragment : mFragments) {
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
        Fragment fragment = mFragments.get(position);
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }

    public Fragment getFragment(int position) {
        return mFragments.get(position);
    }

    public static void onDestroy() {
        instance = null;
    }
}
