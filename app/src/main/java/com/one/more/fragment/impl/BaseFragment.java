package com.one.more.fragment.impl;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by mcz on 2016/10/19
 */

public abstract class BaseFragment extends Fragment {

    protected String TAG = "";
    //生命周期不对，弃用
    protected boolean isVisible;
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext=getContext();
        TAG=getClass().getSimpleName();
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