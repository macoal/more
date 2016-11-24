package com.one.more.see.presenter;


import com.one.util.presenter.BasePresenter;
import com.one.util.view.BaseView;

/**
 * Created by mcz on 2016/11/13
 */

public interface IZHPresenter<V extends BaseView> extends BasePresenter<V> {

    void getLastZhihuNews();

    void getTheDaily(String date);

}
