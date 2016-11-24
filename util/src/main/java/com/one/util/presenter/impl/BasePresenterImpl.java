package com.one.util.presenter.impl;

import com.one.util.presenter.BasePresenter;
import com.one.util.view.BaseView;

import java.lang.ref.WeakReference;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mcz on 2016/10/11
 */

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    //当内存不足，释放内存
    private WeakReference<V> mViewReference;
    private CompositeSubscription mCompositeSubscription = null;

    //进行关联
    public void attachView(V view) {
        mViewReference = new WeakReference<V>(view);
    }
    //解除关联
    public void detachView() {
        if(mViewReference != null){
            mViewReference.clear();
            mViewReference = null;
        }
    }

    public V getView() {
        return mViewReference == null ? null : mViewReference.get();
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    public void unSubcrible() {

        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

}
