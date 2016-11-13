package com.one.more.fragment.impl;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.more.R;
import com.one.more.ui.adapter.SeeTabFragmentAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by mcz on 2016/10/19
 */

public class SeeFragment extends BaseFragment {;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_see, container, false);
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
        getUserVisibleHint();
        if(!isPrepared || !getUserVisibleHint()) {
            return;
        }
        Logger.d(TAG+" 懒加载成功");
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main);
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

        TabLayout mTabLayout = (TabLayout) view.findViewById(R.id.see_tab);
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager_see);
        SeeTabFragmentAdapter myFragmentPagerAdapter = new SeeTabFragmentAdapter(
                getChildFragmentManager(),listFragment,listTittle);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void onInvisible() {

    }
}