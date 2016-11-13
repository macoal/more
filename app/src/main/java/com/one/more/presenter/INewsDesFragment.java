package com.one.more.presenter;

import com.one.more.bean.news.NewsDetailBean;
import com.one.more.fragment.IBaseFragment;

/**
 * Created by xinghongfei on 16/8/17.
 */
public interface INewsDesFragment extends IBaseFragment {
       void  upListItem(NewsDetailBean newsList);
}
