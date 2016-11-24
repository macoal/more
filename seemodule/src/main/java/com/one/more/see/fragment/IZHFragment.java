package com.one.more.see.fragment;

import com.one.more.see.bean.zhihu.ZhihuDaily;
import com.one.util.fragment.IBaseFragment;

/**
 * Created by mcz on 2016/11/12
 */

public interface IZHFragment extends IBaseFragment {
    void  updateList(ZhihuDaily story);
}
