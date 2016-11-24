package com.one.util.presenter;

import com.one.util.view.BaseView;

import rx.Subscription;

/**
 * Created by mcz on 2016/10/11
 */

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
    void addSubscription(Subscription s);
    void unSubcrible();
}
