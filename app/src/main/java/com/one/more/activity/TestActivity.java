package com.one.more.activity;

import android.os.Bundle;
import android.util.Log;

import com.one.more.R;

/**
 * Created by mcz on 2016/10/10
 */

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initParams();
        initView();

    }

    public void initView(){
        setContentView(R.layout.activity_main);
//        View v = findViewById(R.id.first);
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setY(v.getY()+10);
//            }
//        });
    }
    public void initParams(){
        super.initParams();
        showLoading();
        Log.d(TAG, "initParams: 载入成功！");
        // TODO: 2016/10/11 showLoading()没有执行
    }

}
