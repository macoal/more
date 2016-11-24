package com.one.more.see.fragment.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.more.doit.R;
import com.one.more.see.ui.adapter.TodoAdapter;
import com.one.util.fragment.BaseFragment;

/**
 * Created by mcz on 2016/10/19
 */

public class DoitFragment extends BaseFragment {
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private View rootView;
    private RecyclerView tabDoitRecycleView;
    private TodoAdapter todoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_doit, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initDate();
        initView();

    }

    private void initDate(){
        TAG = getClass().getSimpleName();
        isPrepared = true;
        todoAdapter =new TodoAdapter(mContext);

    }

    private void initView(){

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
//        toolbar.inflateMenu(R.menu.main);
        toolbar.setTitle("More!");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabDoitRecycleView = (RecyclerView) rootView.findViewById(R.id.tab_doit_recycle);
//        tabDoitRecycleView.setLayoutManager(new GridLayoutManager(mContext,4));
        tabDoitRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        tabDoitRecycleView.setHasFixedSize(true);
        tabDoitRecycleView.setItemAnimator(new DefaultItemAnimator());
        tabDoitRecycleView.setAdapter(todoAdapter);

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
}