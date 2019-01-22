package com.mdc.cpfit.util.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Maity on 5/3/2017 AD.
 */

public class CustomViewPager extends ViewPager implements View.OnTouchListener{

    private boolean enabled;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(
                event.getAction() == MotionEvent.ACTION_MOVE &&
                        v instanceof ViewGroup
                ) {
            ((ViewGroup) v).requestDisallowInterceptTouchEvent(true);
        }
        return false;
    }
}
