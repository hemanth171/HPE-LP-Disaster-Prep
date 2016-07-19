/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.assessment_intro;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.topcoder.disasterprep.module.submodule.LockableViewPager;

/**
 * The custom view pager
 *
 * @author TCSCODER
 * @version 1.0
 */
public class MeasuredViewPager extends LockableViewPager {

    public MeasuredViewPager(Context context) {
        super(context);
    }

    public MeasuredViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for(int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if(h > height) height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
