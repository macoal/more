package com.one.more.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.one.more.ui.fragment.BaseFragment;

import java.util.ArrayList;

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseFragment> listFragments;
    private FragmentManager mFragmentManager;

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> al) {
        super(fm);
        mFragmentManager = fm;
        listFragments = al;
        notifyDataSetChanged();
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



}
