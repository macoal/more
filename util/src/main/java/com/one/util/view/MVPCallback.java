package com.one.util.view;

import com.one.util.presenter.BasePresenter;

/**
 * Created by mcz on 2016/11/21
 */

public interface MVPCallback<V extends BaseView, P extends BasePresenter<V>> {
    public V getMvpView();
    public P createPresenter();
    public P getPresenter();
    public void setPresenter(P presenter);
}
