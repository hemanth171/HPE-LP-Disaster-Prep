package com.topcoder.disasterprep;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.topcoder.disasterprep.R;

public class CircleScoreView extends FrameLayout {

    private ProgressBar mProgressBar;
    private TextView mProgressText;

    public CircleScoreView(Context context) {
        super(context);
        initUI(context);
    }

    public CircleScoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public CircleScoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    private void initUI(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = layoutInflater.inflate(R.layout.view_score, this, true);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        mProgressText = (TextView) rootView.findViewById(R.id.progress_text);
    }

    public void setScore(int score, int maxScore, Drawable scoreColor, int color) {
        mProgressText.setText(String.valueOf(score));
        mProgressText.setTextColor(color);
        mProgressBar.setProgressDrawable(0 == score ? mProgressBar.getBackground() : scoreColor);
        mProgressBar.setMax(maxScore);
        mProgressBar.setProgress(score);
    }
}
