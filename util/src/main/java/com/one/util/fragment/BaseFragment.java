package com.one.util.fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.one.util.presenter.BasePresenter;
import com.one.util.view.BaseView;
import com.one.util.view.MVPCallback;


/**
 * Created by mcz on 2016/10/19
 */

public abstract class BaseFragment<V extends BaseView,P extends BasePresenter<V>> extends Fragment
        implements MVPCallback,IBaseFragment {

    protected String TAG = "";
    //生命周期不对，弃用
    protected boolean isVisible;
    protected Context mContext;
    protected P mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initParams();
        initView();
    }

    public abstract P createPresenter();

    @NonNull
    public P getPresenter() {
        return mPresenter;
    }
    public V getMvpView(){
        return (V)this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract void initView();

    public void initParams(){
        TAG=getClass().getSimpleName();
        mContext=getContext();
        if (createPresenter()!=null) {
            //创建presenter
            mPresenter = createPresenter();
            //内存泄露
            //关联View
            mPresenter.attachView((V) this);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    protected abstract void lazyLoad();
    protected abstract void onInvisible();

}