package com.one.util.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.one.util.R;
import com.one.util.presenter.BasePresenter;
import com.one.util.view.BaseView;
import com.one.util.view.MVPCallback;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by mcz on 2016/10/10
 */
public abstract class BaseActivity<V extends BaseView,P extends BasePresenter<V>> extends AppCompatActivity
        implements BaseView,MVPCallback{

    protected String TAG="";
    protected Context mContext;
    protected Handler mHandler;
    protected P mPresenter;

    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParams();
        initTheView();
    }

    public abstract P createPresenter();

    @NonNull
    public P getPresenter() {
        return mPresenter;
    }
    public V getMvpView(){
        return (V)this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public void initTheView(){
        initWindow();
    }

    public void initParams(){
        TAG=getClass().getSimpleName();
        mContext=this;

        if (createPresenter()!=null) {
            //创建presenter
            mPresenter = createPresenter();
            //内存泄露
            //关联View
            mPresenter.attachView((V) this);
        }
    }

    @TargetApi(19)
    protected void initWindow(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorPrimary));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    /**
     * 显示progressbar
     */
    public void showLoading() {
        showLoading(0, 0);
    }
    /**
     * 隐藏progressbar
     */
    public void hideLoading() {
        if (mProgressbar != null) {
            mProgressbar.setVisibility(View.GONE);
        }
    }

    /**
     * 展示progressbar
     *
     * @param topSpace
     *            progressbar与顶部的距离
     * @param bottomSpace
     *            progressbar与底部的距离
     */

    public void showLoading(int topSpace, int bottomSpace) {
        if (mProgressbar != null) {
            if (!mProgressbar.isShown()) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mProgressbar
                        .getLayoutParams();
                if (layoutParams.topMargin != topSpace
                        || layoutParams.bottomMargin != bottomSpace) {
                    layoutParams.topMargin = topSpace;
                    layoutParams.bottomMargin = bottomSpace;
                    mProgressbar.setLayoutParams(layoutParams);
                }
                mProgressbar.setVisibility(View.VISIBLE);
            }
        } else {
            mProgressbar = new ProgressBar(this);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(150, 150);
            layoutParams.topMargin = topSpace;
            layoutParams.bottomMargin = bottomSpace;
            layoutParams.gravity = Gravity.CENTER;
            FrameLayout frameLayout = (FrameLayout) getWindow().findViewById(Window.ID_ANDROID_CONTENT);
            frameLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            frameLayout.addView(mProgressbar,layoutParams);

        }
    }

}
