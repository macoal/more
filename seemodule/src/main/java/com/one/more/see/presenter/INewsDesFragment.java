package com.one.more.see.presenter;


import com.one.more.see.bean.news.NewsDetailBean;
import com.one.util.fragment.IBaseFragment;

/**
 * Created by xinghongfei on 16/8/17.
 */
public interface INewsDesFragment extends IBaseFragment {
       void  upListItem(NewsDetailBean newsList);
}
