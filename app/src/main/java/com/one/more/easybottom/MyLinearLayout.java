package com.one.more.easybottom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by mcz on 2016/11/4
 */

public class MyLinearLayout extends LinearLayout {

    private ViewTouchListener onTouch = null;

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTouchListener(ViewTouchListener onTouch) {
        this.onTouch = onTouch;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (onTouch != null){
            onTouch.onTouch(ev);
        }
        return super.onInterceptTouchEvent(ev);
    }

    public interface ViewTouchListener {
        boolean onTouch(MotionEvent ev);

    }
}
