package com.one.more.ui.activity;

import android.os.Message;
import android.util.Log;

import com.one.more.R;

/**
 * Created by mcz on 2016/10/10
 */

public class TestActivity extends BaseActivity {

    public void initView(){
        setContentView(R.layout.activity_main);
    }
    public void initParams(){
        super.initParams();
        Log.d(TAG, "initParams: 载入成功！");
        // TODO: 2016/10/11 showLoading()没有执行
        showLoading();
        mHandler.sendEmptyMessageDelayed(0,5000);
    }
    public boolean mHandler(Message msg){
        if (msg.what==0)
            hideLoading();
        return false;
    }
}
