package com.one.more.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.one.more.R;
import com.one.more.bean.zhihu.ZhihuDaily;
import com.one.more.bean.zhihu.ZhihuStory;
import com.one.more.fragment.IZHFragment;
import com.one.more.presenter.IZHPresenter;
import com.one.more.presenter.IZHStory;
import com.one.more.presenter.IZHStoryPresenter;
import com.one.more.presenter.impl.ZHStoryPresenterImpl;
import com.one.more.util.DensityUtil;
import com.one.more.util.WebUtil;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by xinghongfei on 16/8/13
 */
public class ZHDescribeActivity extends BaseActivity implements IZHStory {
    private static final float SCRIM_ADJUSTMENT = 0.075f;

    ImageView mShot;
    Toolbar mToolbar;
    WebView wvZhihu;
    NestedScrollView mNest;
    TextView mTranslateYTextView;
    FrameLayout mDraggableFrame;

    boolean isEmpty;
    String mBody;
    String[] scc;
    String mImageUrl;


    int[] mDeviceInfo;
    int width;
    int heigh;

    private String id;
    private String title;
    private String url;
    private IZHStoryPresenter mIZhihuStoryPresenter;
    private Handler mHandler=new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zh_describe);
        mDeviceInfo = DensityUtil.getDeviceInfo(this);
        width = mDeviceInfo[0];
        heigh = width * 3 / 4;
        setSupportActionBar(mToolbar);
        initData();
        initView();
        getData();

    }

    private void initData() {


        mShot = (ImageView) findViewById(R.id.shot);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        wvZhihu = (WebView) findViewById(R.id.wv_zhihu);
        mNest = (NestedScrollView) findViewById(R.id.nest);
        mTranslateYTextView = (TextView) findViewById(R.id.title);
        mDraggableFrame = (FrameLayout) findViewById(R.id.draggable_frame);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        mImageUrl = getIntent().getStringExtra("image");
        mIZhihuStoryPresenter = new ZHStoryPresenterImpl(this);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            postponeEnterTransition();
            mShot.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mShot.getViewTreeObserver().removeOnPreDrawListener(this);
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                        startPostponedEnterTransition();
                    }
                    return true;
                }
            });
        }
    }

    public void initView() {
        mToolbar.setTitleMargin(20,20,0,10);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNest.smoothScrollTo(0,0);
            }
        });
        mShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNest.smoothScrollTo(0, 0);
            }
        });
        mTranslateYTextView.setText(title);

        WebSettings settings = wvZhihu.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        //settings.setUseWideViewPort(true);造成文字太小
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wvZhihu.setWebChromeClient(new WebChromeClient());

    }


    @Override
    protected void onResume() {
        super.onResume();

        try {
            wvZhihu.getClass().getMethod("onResume").invoke(wvZhihu, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            wvZhihu.getClass().getMethod("onPause").invoke(wvZhihu, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        //webview内存泄露
        if (wvZhihu != null) {
            ((ViewGroup) wvZhihu.getParent()).removeView(wvZhihu);
            wvZhihu.destroy();
            wvZhihu = null;
        }
        mIZhihuStoryPresenter.unSubcrible();
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getData() {
        mIZhihuStoryPresenter.getZhihuStory(id);

    }


    @Override
    public void showError(String error) {
        Snackbar.make(wvZhihu, "请稍等", Snackbar.LENGTH_INDEFINITE).setAction("重试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        }).show();
    }

    @Override
    public void showZhihuStory(ZhihuStory zhihuStory) {

            Glide.with(this)
                    .load(zhihuStory.getImage()).centerCrop()
                    .override(width,heigh)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(mShot);
        url = zhihuStory.getShareUrl();
        isEmpty=TextUtils.isEmpty(zhihuStory.getBody());
        mBody=zhihuStory.getBody();
        scc=zhihuStory.getCss();
        if (isEmpty) {
            wvZhihu.loadUrl(url);
        } else {
            String data = WebUtil.buildHtmlWithCss(mBody, scc, false);
            wvZhihu.loadDataWithBaseURL(WebUtil.BASE_URL, data, WebUtil.MIME_TYPE, WebUtil.ENCODING, WebUtil.FAIL_URL);
        }
    }
}
