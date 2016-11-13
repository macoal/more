package com.one.more.presenter.impl;

import android.content.Context;

import com.one.more.presenter.IBasePresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mcz on 2016/10/11
 */

public class BasePresenterImpl implements IBasePresenter {

    private CompositeSubscription mCompositeSubscription;

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void unSubcrible() {

        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}
