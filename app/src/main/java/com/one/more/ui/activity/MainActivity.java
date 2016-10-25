package com.one.more.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.one.more.R;
import com.one.more.ui.adapter.MyFragmentPagerAdapter;
import com.one.more.ui.fragment.BaseFragment;
import com.one.more.ui.fragment.DoFragment;
import com.one.more.ui.fragment.SeeFragment;
import com.one.more.ui.fragment.ThinkFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

/**
 * Created by mcz on 2016/10/19.
 */

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private BaseFragment seeFragment;
    private BaseFragment doFragment;
    private BaseFragment thinkFragment;
    private MyFragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initParams();
        initView();

    }

    public void initView(){
        setContentView(R.layout.activity_main);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        hideLoading();
                        break;
                    case 1:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        super.handleMessage(msg);
                }
            }
        };

        showLoading();
        mHandler.sendEmptyMessageDelayed(0,5000);


        ArrayList<BaseFragment> listFragment = new ArrayList<>();
        seeFragment = new SeeFragment();
        doFragment = new DoFragment();
        thinkFragment = new ThinkFragment();

        listFragment.add(seeFragment);
        listFragment.add(doFragment);
        listFragment.add(thinkFragment);
        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), listFragment);

        mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPager.setCurrentItem(0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.bottomBarSee) {
                    Log.d(TAG, "onTabSelected: click see");
                    findViewById(R.id.toolbar_main_normal).setVisibility(View.VISIBLE);
                    findViewById(R.id.toolbar_main_image).setVisibility(View.GONE);
                    mViewPager.setCurrentItem(0);
                }else if (tabId == R.id.bottomBarDo){
                    Log.d(TAG, "onTabSelected: click do");
                    findViewById(R.id.toolbar_main_normal).setVisibility(View.GONE);
                    findViewById(R.id.toolbar_main_image).setVisibility(View.VISIBLE);
//                    CollapsingToolbarLayout mCollapsingToolbarLayout =
//                            (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
//                    mCollapsingToolbarLayout.setTitle("HHXX");
//                    //通过CollapsingToolbarLayout修改字体颜色
//                    mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
//                    mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
                    mViewPager.setCurrentItem(1);
                }else if (tabId == R.id.bottomBarThink){
                    Log.d(TAG, "onTabSelected: click think");
                    findViewById(R.id.toolbar_main_normal).setVisibility(View.VISIBLE);
                    findViewById(R.id.toolbar_main_image).setVisibility(View.GONE);
                    mViewPager.setCurrentItem(2);
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void initParams(){
        super.initParams();
        Log.d(TAG, "initParams: 载入成功！");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
