package com.one.more.see.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.one.more.see.bean.news.NewsDetailBean;
import com.one.more.see.presenter.INewsDesFragment;
import com.one.more.see.presenter.impl.NewsDesPresenterImpl;
import com.one.more.see.R;
import com.one.util.activity.BaseActivity;
import com.one.util.presenter.BasePresenter;
import com.one.util.util.DensityUtil;

import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by mcz on 16/11、13
 */
public class NewsDescribeActivity extends BaseActivity implements INewsDesFragment {
    private static final float SCRIM_ADJUSTMENT = 0.075f;
    int[] mDeviceInfo;
    int width;
    int heigh;
    ProgressBar mProgress;
    HtmlTextView mHtNewsContent;
    ImageView mShot;
    Toolbar mToolbar;
    FrameLayout mDraggableFrame;
    NestedScrollView mNest;
    TextView mTextView;

    private String id;
    private String title;
    private String mImageUrl;
    private NewsDesPresenterImpl mTopNewsDesPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_describe);
        setSupportActionBar(mToolbar);
        mDeviceInfo = DensityUtil.getDeviceInfo(this);
        width = mDeviceInfo[0];
        heigh = width * 3 / 4;
        initData();
        initView();
        getData();

    }

    @NonNull
    @Override
    public BasePresenter createPresenter() {
        mTopNewsDesPresenter = new NewsDesPresenterImpl(this);
        return mTopNewsDesPresenter;
    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }


    private void initData() {

        mProgress = (ProgressBar) findViewById(R.id.progress);
        mHtNewsContent = (HtmlTextView) findViewById(R.id.htNewsContent);
        mShot = (ImageView) findViewById(R.id.shot);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDraggableFrame = (FrameLayout) findViewById(R.id.draggable_frame);
        mNest = (NestedScrollView) findViewById(R.id.nest);
        mTextView = (TextView) findViewById(R.id.title);

        id = getIntent().getStringExtra("docid");
        title = getIntent().getStringExtra("title");
        mTextView.setText(title);
        mImageUrl = getIntent().getStringExtra("image");

        Glide.with(this)
                .load(mImageUrl)
                .override(width, heigh)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mShot);

    }

    public void initView() {
        mNest.setAlpha(0.5f);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNest.smoothScrollTo(0, 0);
            }
        });

        mShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNest.smoothScrollTo(0, 0);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        mTopNewsDesPresenter.unSubcrible();
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getData() {
        mTopNewsDesPresenter.getDescrible(id);

    }


    @Override
    public void showProgressDialog() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidProgressDialog() {
        mProgress.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showError(String error) {
        Snackbar.make(mDraggableFrame, "请稍后", Snackbar.LENGTH_INDEFINITE).setAction("重试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        }).show();
    }

    @Override
    public void upListItem(NewsDetailBean newsList) {
        mProgress.setVisibility(View.INVISIBLE);
        mHtNewsContent.setHtmlFromString(newsList.getBody(), new HtmlTextView.LocalImageGetter());

    }
}
