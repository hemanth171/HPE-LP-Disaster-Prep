package com.topcoder.disasterprep.module.bc;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class LockableViewPager extends ViewPager {

    private boolean mIsLocked;

    public LockableViewPager(Context context) {
        super(context);
    }

    public LockableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIsLocked(boolean isLocked) {
        this.mIsLocked = isLocked;
    }

    public boolean isLocked() {
        return mIsLocked;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !mIsLocked && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return !mIsLocked && super.onTouchEvent(ev);
    }
}
