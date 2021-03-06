/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.submodule;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * The lockable view pager
 *
 * @author TCSCODER
 * @version 1.0
 */
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
