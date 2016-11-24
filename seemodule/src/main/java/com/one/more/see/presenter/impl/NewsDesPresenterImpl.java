package com.one.more.see.presenter.impl;


import com.one.more.see.bean.news.NewsDetailBean;
import com.one.more.see.presenter.INewDescriblePresenter;
import com.one.more.see.presenter.INewsDesFragment;
import com.one.more.see.util.NewsJsonUtils;
import com.one.util.presenter.impl.BasePresenterImpl;
import com.one.util.util.OkHttpUtils;
import com.one.util.util.Urls;

/**
 * Created by 蔡小木 on 2016/4/26 0026.
 */
public class NewsDesPresenterImpl extends BasePresenterImpl implements INewDescriblePresenter {

    private INewsDesFragment mITopNewsFragment;

    public NewsDesPresenterImpl(INewsDesFragment topNewsFragment) {
        if (topNewsFragment == null)
            throw new IllegalArgumentException(" must not be null");
        mITopNewsFragment = topNewsFragment;
    }
    private String getDetailUrl(String docId) {
        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docId).append(Urls.END_DETAIL_URL);
        return sb.toString();
    }

    @Override
    public void getDescrible(final String docid) {
        mITopNewsFragment.showProgressDialog();
        String url = getDetailUrl(docid);
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, docid);
               mITopNewsFragment.upListItem(newsDetailBean);
            }

            @Override
            public void onFailure(Exception e) {
                mITopNewsFragment.showError(e.toString());
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);

    }
}
