package com.lzm.fusionnews.module.home.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.support.v7.widget.RecyclerView;

import com.lzm.fusionnews.R;
import com.lzm.fusionnews.base.BaseFragment;
import com.lzm.fusionnews.module.home.HomeContract;
import com.lzm.fusionnews.module.home.model.ArticleResult;
import com.lzm.fusionnews.module.home.model.ContentListItem;

import java.util.ArrayList;

/**
 * 新闻Fragment {@link BaseFragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener{

    private HomeContract.Presenter mPresenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private long mLastRefreshTime;
    private int mIndex;
    private boolean mIsRefresh;
    private HomeAdapter mAdapter;
    private ArrayList<ContentListItem> mData = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new HomeAdapter(getContext(), mData);
        recyclerView.setAdapter(mAdapter);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastPositon = layoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == mData.size() - 1) {
                    if (mIndex < 10) {
                        mPresenter.loadMore(mIndex);
                    }
                }
            }
        });
    }

    @Override
    public void setPresenter(HomeContract.Presenter present) {
        mPresenter = present;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void updateHomeList(ArticleResult articleResult) {
        if(mIsRefresh){
            mIsRefresh = false;
            refreshLayout.setRefreshing(false);
            if(mData.size() > 0){
                mData.clear();
            }
            mIndex = 1;
        } else if(mData.size() > 0) {
            mIndex += 1;
        }

        for(ContentListItem item : articleResult.data.contentList){
            if(item.category != "0") {
                mData.add(item);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        //距离上次刷新超过6分钟，重新加载数据
        if (System.currentTimeMillis()- mLastRefreshTime > 3600000) {
            mPresenter.start();
            if (mData.size() > 0) {
                mData.clear();
            }
        }
        mLastRefreshTime = System.currentTimeMillis();
    }

    @Override
    public void onRefresh() {
        if(mIsRefresh){
            mIsRefresh = true;
            mPresenter.start();
        }
    }

}
