package com.one.util.fragment;

import com.one.util.view.BaseView;

/**
 * Created by mcz on 2016/11/12
 */

public interface IBaseFragment extends BaseView{
        void showProgressDialog();

        void hidProgressDialog();

        void showError(String error);
}
