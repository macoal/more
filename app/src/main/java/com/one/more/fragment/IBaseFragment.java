package com.one.more.fragment;

/**
 * Created by mcz on 2016/11/12
 */

public interface IBaseFragment {
        void showProgressDialog();

        void hidProgressDialog();

        void showError(String error);
}
