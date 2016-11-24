package com.one.more.see.fragment.impl;

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

import com.one.more.see.bean.news.NewsList;
import com.one.more.see.fragment.INewsFragment;
import com.one.more.see.presenter.INewsPresenter;
import com.one.more.see.presenter.impl.NewsPrensenterImpl;
import com.one.more.see.ui.adapter.NewsAdapter;
import com.one.more.see.R;
import com.one.util.fragment.BaseFragment;
import com.one.util.presenter.BasePresenter;

/**
 * Created by mcz on 2016/10/19
 */
public class SeeNewFragment extends BaseFragment<INewsFragment,INewsPresenter<INewsFragment>>implements INewsFragment {
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private int currentIndex;
    private NewsAdapter mNewsAdapter;
    private INewsPresenter<INewsFragment> mINewsPresenter;

    private View rootView;
    private ProgressBar mProgress;
    private RecyclerView mRecyclerView;

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
        mNewsAdapter =new NewsAdapter(mContext);

    }

    @Nullable
    @Override
    public INewsPresenter<INewsFragment> createPresenter() {
//        return new NewsPrensenterImpl();
        mINewsPresenter = new NewsPrensenterImpl(this);
        return mINewsPresenter;
    }

    protected void initView(){

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_see_rescycleview);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mNewsAdapter);

        mProgress = (ProgressBar) rootView.findViewById(R.id.prograss);
        if (isNetworkConnected()){
            lazyLoad();
        }

    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }

    @Override
    protected void lazyLoad(){
        if(!isPrepared || !isVisible) {
            return;
        }
        mINewsPresenter.getNewsList(currentIndex);
    }

    private void loadMoreDate() {
        mNewsAdapter.loadingStart();
        currentIndex+=20;
    }

    @Override
    protected void onInvisible() {

    }

    public void upListItem(NewsList newsList) {
        hidProgressDialog();
        mNewsAdapter.addItems(newsList.getNewsList());
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
                    mINewsPresenter.getNewsList(currentIndex);
                }
            }).show();

        }
    }
}