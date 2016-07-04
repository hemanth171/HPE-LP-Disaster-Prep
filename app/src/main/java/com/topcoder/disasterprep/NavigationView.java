package com.topcoder.disasterprep;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.topcoder.disasterprep.module.bc.LockableViewPager;

public class NavigationView extends LinearLayout {

    private View mBack;
    private TextView mResult;
    private ProgressBar mProgress;
    private LockableViewPager mViewPager;
    private ViewPager.SimpleOnPageChangeListener mOnPageChangeListener;
    private boolean mShowProgress;

    public NavigationView(Context context) {
        super(context);
        initUI();
    }

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    private void initUI() {
        inflate(getContext(), R.layout.view_navigation, this);
        mBack = findViewById(R.id.previous);
        mResult = (TextView) findViewById(R.id.result);
        mProgress = (ProgressBar) findViewById(R.id.progress);
        showProgress(false);
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

    public void setPager(final LockableViewPager viewPager, final ResultListener resultListener, final StepListener stepListener) {
        final int count = viewPager.getAdapter().getCount();
        mViewPager = viewPager;
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
                if (!mViewPager.isLocked()) {
                    int current = viewPager.getCurrentItem();
                    if (0 < current && current < count) {
                        viewPager.setCurrentItem(current - 1);
                    }
                }
            }
        });
        mResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mViewPager.isLocked()) {
                    int current = viewPager.getCurrentItem();
                    int count = viewPager.getAdapter().getCount();
                    if (0 <= current && current < count - 1) {
                        viewPager.setCurrentItem(current + 1);
                    } else if (current == count - 1) {
                        resultListener.showResult();
                    }
                } else {
                    resultListener.showSkip();
                }
            }
        });
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
