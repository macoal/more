package com.one.more.see.presenter.impl;


import com.google.gson.Gson;
import com.one.more.see.api.ApiManage;
import com.one.more.see.bean.zhihu.ZhihuDaily;
import com.one.more.see.bean.zhihu.ZhihuDailyItem;
import com.one.more.see.fragment.IZHFragment;
import com.one.more.see.presenter.IZHPresenter;
import com.one.util.presenter.impl.BasePresenterImpl;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mcz on 2016/11/13
 */

public class ZHPresenterImpl extends BasePresenterImpl implements IZHPresenter {

    private IZHFragment mZHFragment;
    private Gson gson = new Gson();

    public ZHPresenterImpl(IZHFragment mZHFragment) {
        this.mZHFragment = mZHFragment;
    }

    @Override
    public void getLastZhihuNews() {
        mZHFragment.showProgressDialog();
        Subscription subscription = ApiManage.getInstence().getZhihuApiService().getLastDaily()
                .map(new Func1<ZhihuDaily, ZhihuDaily>() {
                    @Override
                    public ZhihuDaily call(ZhihuDaily zhihuDaily) {
                        String date = zhihuDaily.getDate();
                        for (ZhihuDailyItem zhihuDailyItem : zhihuDaily.getStories()) {
                            zhihuDailyItem.setDate(date);
                        }
                        return zhihuDaily;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuDaily>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mZHFragment.hidProgressDialog();
                        mZHFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ZhihuDaily zhihuDaily) {
                        mZHFragment.hidProgressDialog();
                        mZHFragment.updateList(zhihuDaily);
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void getTheDaily(String date) {
        Subscription subscription = ApiManage.getInstence().getZhihuApiService().getTheDaily(date)
                .map(new Func1<ZhihuDaily, ZhihuDaily>() {
                    @Override
                    public ZhihuDaily call(ZhihuDaily zhihuDaily) {
                        String date = zhihuDaily.getDate();
                        for (ZhihuDailyItem zhihuDailyItem : zhihuDaily.getStories()) {
                            zhihuDailyItem.setDate(date);
                        }
                        return zhihuDaily;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuDaily>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mZHFragment.hidProgressDialog();
                        mZHFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ZhihuDaily zhihuDaily) {
                        mZHFragment.hidProgressDialog();
                        mZHFragment.updateList(zhihuDaily);
                    }
                });
        addSubscription(subscription);
    }

}
