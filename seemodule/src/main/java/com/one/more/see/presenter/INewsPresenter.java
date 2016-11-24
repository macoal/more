package com.one.more.see.presenter;


import com.one.util.presenter.BasePresenter;
import com.one.util.view.BaseView;

/**
 * Created by mcz on 2016/11/12
 */

public interface INewsPresenter<V extends BaseView> extends BasePresenter<V> {
    void getNewsList(int t);
}
