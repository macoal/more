package com.one.more.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.one.more.R;
import com.one.more.easybottom.MyLinearLayout;
import com.one.more.fragment.impl.DoFragment;
import com.one.more.fragment.impl.ThinkFragment;
import com.one.util.activity.BaseActivity;
import com.one.util.fragment.BaseFragment;
import com.orhanobut.logger.Logger;

import static android.support.design.widget.BottomSheetBehavior.STATE_COLLAPSED;
import static android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED;


/**
 * Created by mcz on 2016/10/19
 */

public class MainActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private BaseFragment seeFragment;
    private BaseFragment doFragment;
    private BaseFragment thinkFragment;

    private Bundle savedInstanceState;

    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;

    private BottomSheetBehavior mBottomSheetBehavior;

    private MyLinearLayout myLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;

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

        myLinearLayout = (MyLinearLayout) findViewById(R.id.linear_view);
        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.tab_layout));
        mBottomSheetBehavior.setState(STATE_EXPANDED);

        myLinearLayout.setTouchListener(new MyLinearLayout.ViewTouchListener() {
            @Override
            public boolean onTouch(MotionEvent ev) {
                switch (ev.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x1 = ev.getX();
                        y1 = ev.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        x2 = ev.getX();
                        y2 = ev.getY();
                        if(y1 - y2 > 40) {
                            mBottomSheetBehavior.setState(STATE_COLLAPSED);
                        } else if(y2 - y1 >40) {
                            mBottomSheetBehavior.setState(STATE_EXPANDED);
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        x1 = 0;
                        x2 = 0;
                        y1 = 0;
                        y2 = 0;
                        break;
                }

                return false;
            }
        });

        initFragment(savedInstanceState);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        LinearLayout bottomBar = (LinearLayout) findViewById(R.id.tab_layout);
//        findViewById(R.id.tab_see).setOnClickListener(this);
//        findViewById(R.id.tab_do).setOnClickListener(this);
//        findViewById(R.id.tab_think).setOnClickListener(this);

        BottomNavigationView bottomBar = (BottomNavigationView) findViewById(R.id.tab_layout);
        bottomBar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        switch (item.getItemId()){
                            case R.id.tab_see:
                                Logger.d(TAG+" onTabSelected: click see");

                                transaction.attach(seeFragment);
                                transaction.detach(doFragment);
                                transaction.detach(thinkFragment);
                                transaction.commit();
                                break;
                            case R.id.tab_do:
                                Logger.d(TAG+ " onTabSelected: click do");

                                transaction.attach(doFragment);
                                transaction.detach(seeFragment);
                                transaction.detach(thinkFragment);
                                transaction.commit();
                                break;
                            case R.id.tab_think:
                                Logger.d(TAG+" onTabSelected: click think");

                                transaction.attach(thinkFragment);
                                transaction.detach(doFragment);
                                transaction.detach(seeFragment);
                                transaction.commit();
                                break;
                        }
                        return true;
                    }
                });


    }

    public void initParams(){
        super.initParams();
        Logger.d(TAG+ " initParams: 载入成功！");
    }

    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState != null) {
            seeFragment = (SeeFragment) getSupportFragmentManager().findFragmentByTag("seeFragment");
            doFragment = (DoFragment) getSupportFragmentManager().findFragmentByTag("doFragment");
            thinkFragment = (ThinkFragment) getSupportFragmentManager().findFragmentByTag("thinkFragment");
        } else {
            seeFragment = new SeeFragment();
            doFragment = new DoFragment();
            thinkFragment = new ThinkFragment();

            transaction.add(R.id.fl_body, seeFragment, "seeFragment");
            transaction.add(R.id.fl_body, doFragment, "doFragment");
            transaction.add(R.id.fl_body, thinkFragment, "thinkFragment");
        }

        transaction.attach(seeFragment);
        transaction.detach(doFragment);
        transaction.detach(thinkFragment);
        transaction.commit();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                DrawerLayout viewById = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (viewById.isDrawerOpen(GravityCompat.START)){
                    viewById.closeDrawer(GravityCompat.START);
                } else {
                    viewById.openDrawer(GravityCompat.START);
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.tab_see:
                Logger.d(TAG+" onTabSelected: click see");

                transaction.attach(seeFragment);
                transaction.detach(doFragment);
                transaction.detach(thinkFragment);
                transaction.commit();
                break;
            case R.id.tab_do:
                Logger.d(TAG+ " onTabSelected: click do");

                transaction.attach(doFragment);
                transaction.detach(seeFragment);
                transaction.detach(thinkFragment);
                transaction.commit();
                break;
            case R.id.tab_think:
                Logger.d(TAG+" onTabSelected: click think");

                transaction.attach(thinkFragment);
                transaction.detach(doFragment);
                transaction.detach(seeFragment);
                transaction.commit();
                break;
        }
    }

}
