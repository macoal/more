package com.one.more.ui.fragment;


import android.support.v4.app.Fragment;

/**
 * Created by mcz on 2016/10/19
 */

public abstract class BaseFragment extends Fragment {

    protected String TAG = "";
    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    protected void onVisible(){
        lazyLoad();
    }
    protected abstract void lazyLoad();
    protected abstract void onInvisible();

}