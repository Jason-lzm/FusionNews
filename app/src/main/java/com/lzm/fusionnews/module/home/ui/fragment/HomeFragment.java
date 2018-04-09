package com.lzm.fusionnews.module.home.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzm.fusionnews.R;
import com.lzm.fusionnews.base.BaseFragment;

/**
 * 新闻Fragment {@link BaseFragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
