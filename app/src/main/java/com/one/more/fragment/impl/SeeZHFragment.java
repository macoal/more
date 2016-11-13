package com.one.more.fragment.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.one.more.R;
import com.one.more.bean.zhihu.ZhihuDaily;
import com.one.more.fragment.IZHFragment;
import com.one.more.presenter.IZHPresenter;
import com.one.more.presenter.impl.ZHPresenterImpl;
import com.one.more.ui.adapter.ZhihuAdapter;

/**
 * Created by mcz on 2016/10/19
 */

public class SeeZHFragment extends BaseFragment implements IZHFragment {;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private int currentIndex;
    private ZhihuAdapter mZhihuAdapter;
    private IZHPresenter mZHPresenter;

    private View rootView;
    private ProgressBar mProgress;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_see_top, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initDate();
        initView();

    }

    private void initDate(){
        isPrepared = true;
        mZHPresenter = new ZHPresenterImpl(this);
        mZhihuAdapter =new ZhihuAdapter(mContext);

    }

    private void initView(){

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_see_rescycleview);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mZhihuAdapter);

        mProgress = (ProgressBar) rootView.findViewById(R.id.prograss);

        if (isNetworkConnected()){
            lazyLoad();
        }
    }

    @Override
    protected void lazyLoad(){
        if(!isPrepared || !isVisible) {
            return;
        }
        mZHPresenter.getLastZhihuNews();
    }

    private void loadMoreDate() {
        mZhihuAdapter.loadingStart();
        currentIndex+=20;
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mZHPresenter.unSubcrible();
    }

    @Override
    public void showProgressDialog() {
        if (currentIndex ==0){
            mProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hidProgressDialog() {
        mProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String error) {
        if (mRecyclerView != null) {
            Snackbar.make(mRecyclerView, "请稍后", Snackbar.LENGTH_SHORT).setAction("重试", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mZHPresenter.getLastZhihuNews();
                }
            }).show();

        }
    }

    @Override
    public void updateList(ZhihuDaily story) {
        hidProgressDialog();
        mZhihuAdapter.addItems(story.getStories());
    }
}