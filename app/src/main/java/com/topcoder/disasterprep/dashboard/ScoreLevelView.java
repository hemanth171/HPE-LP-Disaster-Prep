package com.topcoder.disasterprep.dashboard;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.topcoder.disasterprep.R;

public class ScoreLevelView extends LinearLayout {

    private TextView mTitle;
    private TextView mScore;

    public ScoreLevelView(Context context) {
        super(context);
        initUI();
    }

    public ScoreLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public ScoreLevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    private void initUI() {
        inflate(getContext(), R.layout.view_dashboard_score_level, this);
        mTitle = (TextView) findViewById(R.id.title);
        mScore = (TextView) findViewById(R.id.score);
    }

    void setTitle(String title) {
        mTitle.setText(title);
    }

    void setScore(String score) {
        mScore.setText(score);
    }

    void setColor(@ColorRes int color) {
        mScore.setTextColor(ContextCompat.getColor(this.getContext(), color));
    }
}
