package com.one.more.see.fragment;

import com.one.more.see.bean.news.NewsList;
import com.one.util.fragment.IBaseFragment;

/**
 * Created by mcz on 2016/11/12
 */

public interface INewsFragment extends IBaseFragment {
    void  upListItem(NewsList newsList);
}
