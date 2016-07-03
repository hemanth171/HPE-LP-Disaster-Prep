package com.topcoder.disasterprep.assessment_intro;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.topcoder.disasterprep.R;

public class IndicatorProgressView extends LinearLayout {

    public IndicatorProgressView(Context context) {
        super(context);
        initUI(context);
    }

    public IndicatorProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public IndicatorProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    private void initUI(Context context) {
        setOrientation(HORIZONTAL);
    }

    void setChildren(int count) {
        for (int i = 0; i < count; i++) {
            IndicatorView indicator = new IndicatorView(getContext());
            indicator.setText(String.valueOf(i + 1));
            if (i == 0) {
                indicator.hideLeft();
            }
            if (i == count - 1) {
                indicator.hideRight();
            }
            addView(indicator);
        }
    }
    
    void setStep(int step) {
        int count = getChildCount();
        if (1 <= step && step <= count) {
            for (int i = 0; i < count; i++) {
                IndicatorView indicator = (IndicatorView) getChildAt(i);
                if (i == step - 1) {
                    indicator.setActive();
                } else if (i < step - 1) {
                    indicator.setComplete();
                } else if (i > step - 1) {
                    indicator.setInactive();
                }
            }
        }
    }

    private static class IndicatorView extends FrameLayout {

        private ColorModel active;
        private ColorModel inactive;
        private ColorModel complete;
        private View mLeft;
        private View mRight;
        private TextView mCenter;

        public IndicatorView(Context context) {
            super(context);
            initUI(context);
        }

        public IndicatorView(Context context, AttributeSet attrs) {
            super(context, attrs);
            initUI(context);
        }

        public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initUI(context);
        }

        private void initUI(Context context) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rootView = layoutInflater.inflate(R.layout.view_indicator, this, true);
            mLeft = rootView.findViewById(R.id.indicator_left);
            mRight = rootView.findViewById(R.id.indicator_right);
            mCenter = (TextView) rootView.findViewById(R.id.indicator_center);
            int colorActive = ContextCompat.getColor(context, R.color.blue3);
            int colorInactive = ContextCompat.getColor(context, R.color.indicator_inactive);
            int colorWhite = ContextCompat.getColor(context, R.color.white);
            Drawable activeBg = ContextCompat.getDrawable(context, R.drawable.circle_indicator_active);
            Drawable inactiveBg = ContextCompat.getDrawable(context, R.drawable.circle_indicator_inactive);
            Drawable completeBg = ContextCompat.getDrawable(context, R.drawable.circle_indicator_complete);
            Drawable progress = ContextCompat.getDrawable(context, R.drawable.indicator_active);
            active = new ColorModel(colorActive, progress, colorActive, activeBg);
            inactive = new ColorModel(colorInactive, null, colorInactive, inactiveBg);
            complete = new ColorModel(colorActive, null, colorWhite, completeBg);
        }

        void setText(String text) {
            mCenter.setText(text);
        }

        void hideLeft() {
            mLeft.setVisibility(GONE);
        }

        void hideRight() {
            mRight.setVisibility(GONE);
        }

        void setActive() {
            setColorModel(active);
        }

        void setInactive() {
            setColorModel(inactive);
        }

        void setComplete() {
            setColorModel(complete);
        }

        private void setColorModel(ColorModel colorModel) {
            mLeft.setBackgroundColor(colorModel.getIndicatorColor());
            if (null == colorModel.getIndicatorProgress()) {
                mRight.setBackgroundColor(colorModel.getIndicatorColor());
            } else {
                mRight.setBackground(colorModel.getIndicatorProgress());
            }
            mCenter.setBackground(colorModel.getBg());
            mCenter.setTextColor(colorModel.getTextColor());
        }
    }

    private static class ColorModel {
        private final int mIndicatorLeftColor;
        private final Drawable mIndicatorProgress;
        private final int mTextColor;
        private final Drawable mBg;

        ColorModel(int indicatorLeftColor, Drawable indicatorProgress, int textColor, Drawable bg) {
            mIndicatorLeftColor = indicatorLeftColor;
            mIndicatorProgress = indicatorProgress;
            mTextColor = textColor;
            mBg = bg;
        }

        int getIndicatorColor() {
            return mIndicatorLeftColor;
        }

        Drawable getIndicatorProgress() {
            return mIndicatorProgress;
        }

        int getTextColor() {
            return mTextColor;
        }

        Drawable getBg() {
            return mBg;
        }
    }
}
