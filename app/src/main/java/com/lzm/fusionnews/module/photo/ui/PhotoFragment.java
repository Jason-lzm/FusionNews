package com.lzm.fusionnews.module.photo.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzm.fusionnews.R;
import com.lzm.fusionnews.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends BaseFragment {


    public PhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

}
