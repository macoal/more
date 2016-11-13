package com.one.more.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.one.more.fragment.impl.BaseFragment;

import java.util.ArrayList;

public class SeeTabFragmentAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> listTitle;                                //tab名的列表
    private ArrayList<BaseFragment> listFragments;                  //fragment列表


    public SeeTabFragmentAdapter(FragmentManager fm, ArrayList<BaseFragment> al, ArrayList<String> listTitle) {
        super(fm);
        this.listFragments = al;
        this.listTitle = listTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position % listTitle.size());
    }

}
