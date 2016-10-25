package com.one.more.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.more.R;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Created by mcz on 2016/10/19
 */

public class DoFragment extends BaseFragment {

    private boolean isPrepared;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        Log.d(TAG, " :onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_see, container, false);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected void lazyLoad(){
        if(!isPrepared || !isVisible) {
            return;
        }
    }

    @Override
    protected void onInvisible() {

    }

}