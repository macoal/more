package com.one.more.see.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.one.more.see.fragment.impl.SeeFragment;
import com.one.more.see.R;
import com.one.util.fragment.BaseFragment;


public class MainActivity extends AppCompatActivity {

    private BaseFragment seeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initFragment(savedInstanceState);
    }

    protected void initView() {
        setContentView(R.layout.activity_main);
        Log.e("ZZZXXXYYY", "initView: ");

    }
    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState != null) {
            seeFragment = (SeeFragment) getSupportFragmentManager().findFragmentByTag("seeFragment");

        } else {
            seeFragment = new SeeFragment();

            transaction.add(R.id.see_tab, seeFragment, "seeFragment");
        }

        transaction.attach(seeFragment);
        transaction.commit();
    }

}
