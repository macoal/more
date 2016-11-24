package com.one.more.see.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.one.more.doit.R;
import com.one.more.see.fragment.impl.DoitFragment;
import com.one.util.fragment.BaseFragment;


public class MainActivity extends AppCompatActivity {

    private BaseFragment doitFragment;

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
            doitFragment = (DoitFragment) getSupportFragmentManager().findFragmentByTag("doitFragment");

        } else {
            doitFragment = new DoitFragment();

            transaction.add(R.id.doit_tab, doitFragment, "doitFragment");
        }

        transaction.attach(doitFragment);
        transaction.commit();
    }

}
