/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.topcoder.disasterprep.module.submodule.LockableViewPager;

/**
 * The navigation view at the bottom
 *
 * @author TCSCODER
 * @version 1.0
 */
public class NavigationView extends LinearLayout {

    /**
     * The back text view
     */
    private TextView mBack;
    private TextView mResult;
    private ProgressBar mProgress;
    private LockableViewPager mViewPager;
    private ViewPager.SimpleOnPageChangeListener mOnPageChangeListener;

    /**
     * The result listener
     */
    private ResultListener resultListener;
    private boolean mShowProgress;

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context, attrs);
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context, attrs);
    }

    /**
     * Initialization the UI
     *
     * @param context the context
     * @param attrs the attributes set
     */
    private void initUI(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.view_navigation, this);
        mBack = (TextView) findViewById(R.id.previous);
        mResult = (TextView) findViewById(R.id.result);
        mProgress = (ProgressBar) findViewById(R.id.progress);
        showProgress(false);

        TypedArray arr =context.getTheme().obtainStyledAttributes(attrs, R.styleable.NavigationView, 0, 0);
        try {
            if (arr.hasValue(R.styleable.NavigationView_prevButtonDrawable)) {
                Drawable img = arr.getDrawable(R.styleable.NavigationView_prevButtonDrawable);
                mBack.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
            }

            if (arr.hasValue(R.styleable.NavigationView_nextButtonDrawable)) {
                Drawable img = arr.getDrawable(R.styleable.NavigationView_nextButtonDrawable);
                mResult.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
            }

            if (arr.hasValue(R.styleable.NavigationView_progressBarDrawable)) {
                mProgress.setProgressDrawable(arr.getDrawable(R.styleable.NavigationView_progressBarDrawable));
            }
        } finally {
            arr.recycle();
        }
    }

    private void editNextButton(int current, int count) {
        if (mViewPager.isLocked()) {
            mResult.setText("Skip");
        } else if (current == count - 1) {
            mResult.setText(getResources().getString(R.string.result));
        } else if (current + 1 == count - 1) {
            mResult.setText(getResources().getString(R.string.next));
        } else {
            mResult.setText(getResources().getString(R.string.next));
        }
    }

    /**
     * Set pager to the navigation view
     *
     * @param viewPager the viewer page
     * @param resultListener the result listener
     * @param stepListener the step listener
     */
    public void setPager(final LockableViewPager viewPager, final ResultListener resultListener, final StepListener stepListener) {
        final int count = viewPager.getAdapter().getCount();
        mViewPager = viewPager;
        this.resultListener = resultListener;

        if (mShowProgress) {
            mProgress.setMax(count);
            mProgress.setProgress(viewPager.getCurrentItem() + 1);
        }
        // when page is selected - change progress and navigation text
        mOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                stepListener.setStep(position + 1);
                if (mShowProgress) {
                    mProgress.setProgress(position + 1);
                }
                editNextButton(position, count);
            }
        };
        viewPager.addOnPageChangeListener(mOnPageChangeListener);
        // when navigation button is clicked set page
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem();
                if (0 < current && current < count) {
                    viewPager.setCurrentItem(current - 1);
                }
            }
        });
        mResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveNext();
            }
        });
    }

    /**
     * Move to the next page (or step)
     */
    public void moveNext() {
        if (!mViewPager.isLocked()) {
            int current = mViewPager.getCurrentItem();
            int count = mViewPager.getAdapter().getCount();
            if (0 <= current && current < count - 1) {
                mViewPager.setCurrentItem(current + 1);
            } else if (current == count - 1) {
                resultListener.showResult();
            }
        } else {
            resultListener.showSkip();
        }
    }

    public void showProgress(boolean isShown) {
        mShowProgress = isShown;
        mProgress.setVisibility(isShown ? VISIBLE : INVISIBLE);
    }

    public void lock(boolean isLock) {
        mViewPager.setIsLocked(isLock);
        editNextButton(mViewPager.getCurrentItem(), mViewPager.getAdapter().getCount());
    }

    @Override
    protected void onDetachedFromWindow() {
        if (null != mViewPager) {
            mViewPager.removeOnPageChangeListener(mOnPageChangeListener);
        }
        super.onDetachedFromWindow();
    }

    public interface ResultListener {
        void showResult();

        void showSkip();
    }

    public interface StepListener {
        /**
         * @param step 1-based position
         */
        void setStep(int step);
    }
}
