package com.one.more.see.fragment.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.more.see.ui.adapter.SeeTabFragmentAdapter;
import com.one.more.see.R;
import com.one.util.fragment.BaseFragment;
import com.one.util.presenter.BasePresenter;
import com.one.util.presenter.impl.BasePresenterImpl;

import java.util.ArrayList;

/**
 * Created by mcz on 2016/10/19
 */

public class SeeFragment extends BaseFragment {;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_see, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initParams();
        initView();

    }

    public void initParams(){
        isPrepared = true;
    }

    protected void initView(){

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
//        toolbar.inflateMenu(R.menu.main);
        toolbar.setTitle("More!");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BaseFragment seeZH = new SeeZHFragment();
        BaseFragment seeTop = new SeeNewFragment();
        ArrayList<BaseFragment> listFragment = new ArrayList<>();
        ArrayList<String> listTittle = new ArrayList<>();
        listTittle.add("TOP");
        listTittle.add("ZH");
        listFragment.add(seeTop);
        listFragment.add(seeZH);

        TabLayout mTabLayout = (TabLayout) rootView.findViewById(R.id.see_tab);
        ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.viewPager_see);
        SeeTabFragmentAdapter myFragmentPagerAdapter = new SeeTabFragmentAdapter(
                getChildFragmentManager(),listFragment,listTittle);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }

    @Override
    public BasePresenterImpl createPresenter() {
        return null ;
    }

    @Override
    protected void lazyLoad(){
        getUserVisibleHint();
        if(!isPrepared || !getUserVisibleHint()) {
            return;
        }

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }

    @Override
    public void showError(String error) {

    }
}