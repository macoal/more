package com.one.more.presenter;

import rx.Subscription;

/**
 * Created by mcz on 2016/10/11
 */

public interface IBasePresenter {
    void addSubscription(Subscription s);
    void unSubcrible();
}
