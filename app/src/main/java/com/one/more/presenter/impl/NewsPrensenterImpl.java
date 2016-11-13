package com.one.more.presenter.impl;

import com.one.more.api.ApiManage;
import com.one.more.bean.news.NewsList;
import com.one.more.presenter.INewsPresenter;
import com.one.more.fragment.INewsFragment;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mcz on 16/11/12
 */
public class NewsPrensenterImpl extends BasePresenterImpl implements INewsPresenter {

    INewsFragment mINewsFragment;
    public NewsPrensenterImpl(INewsFragment iNewsFragment){
        mINewsFragment=iNewsFragment;
    }
    @Override
    public void getNewsList(int t) {
        mINewsFragment.showProgressDialog();
        Subscription subscription= ApiManage.getInstence().getTopNewsService().getNews(t)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        mINewsFragment.hidProgressDialog();
                        mINewsFragment.showError(e.toString());
                    }

                    @Override
                    public void onNext(NewsList newsList) {
                        mINewsFragment.hidProgressDialog();
                        mINewsFragment.upListItem(newsList);

                    }
                });
        addSubscription(subscription);
    }
}
