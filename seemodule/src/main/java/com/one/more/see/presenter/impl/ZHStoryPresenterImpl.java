package com.one.more.see.presenter.impl;

import com.one.more.see.api.ApiManage;
import com.one.more.see.bean.zhihu.ZhihuStory;
import com.one.more.see.presenter.IZHStory;
import com.one.more.see.presenter.IZHStoryPresenter;
import com.one.util.presenter.impl.BasePresenterImpl;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 蔡小木 on 2016/4/26 0026.
 */
public class ZHStoryPresenterImpl extends BasePresenterImpl implements IZHStoryPresenter {

    private IZHStory mIZhihuStory;

    public ZHStoryPresenterImpl(IZHStory zhihuStory) {
        if (zhihuStory == null)
            throw new IllegalArgumentException("zhihuStory must not be null");
        mIZhihuStory = zhihuStory;
    }

    @Override
    public void getZhihuStory(String id) {
        Subscription s = ApiManage.getInstence().getZhihuApiService().getZhihuStory(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuStory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mIZhihuStory.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ZhihuStory zhihuStory) {
                        mIZhihuStory.showZhihuStory(zhihuStory);
                    }
                });
        addSubscription(s);
    }
}
