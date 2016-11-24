package com.one.more.see.fragment;


import com.one.more.see.bean.todo.TodoBean;
import com.one.util.fragment.IBaseFragment;

/**
 * Created by mcz on 2016/11/12
 */

public interface IDoitFragment extends IBaseFragment {
    void  upListItem(TodoBean todoList);
}
