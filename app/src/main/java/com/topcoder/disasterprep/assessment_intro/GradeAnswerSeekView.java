/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.assessment_intro;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleActivity;
import com.topcoder.disasterprep.module.submodule.SubModuleFragment;
import com.topcoder.disasterprep.module.submodule.SubModuleView;

import java.util.List;

/**
 * The custom view for the seek bar.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class GradeAnswerSeekView extends LinearLayout {

    /**
     * The face image view
     */
    private ImageView mGradeFace;

    /**
     * The seek bar
     */
    private SeekBar mGradeSeek;

    /**
     * The grade title
     */
    private TextView mGradeTitle;

    /**
     * The grade hint
     */
    private TextView mGradeHint;

    /**
     * The grades data
     */
    private List<Grade> grades;

    /**
     * Constructor with context
     *
     * @param context the context
     */
    public GradeAnswerSeekView(Context context) {
        super(context);
        initUI();
    }

    /**
     * Constructor with context and attrs
     *
     * @param context the context
     * @param attrs the attributes set
     */
    public GradeAnswerSeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    /**
     * Constructor with context, attrs and default style attribute
     *
     * @param context the context
     * @param attrs the attributes set
     * @param defStyleAttr the default style attribute
     */
    public GradeAnswerSeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    /**
     * Set the grades data
     *
     * @param grades the grades data
     */
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
        setGrade(grades.get(0));
    }

    /**
     * Set the progress
     *
     * @param progress the progress
     */
    public void setProgress(int progress) {
        mGradeSeek.setProgress(progress);
    }

    /**
     * Set the grade for inner child views
     *
     * @param grade the grade data
     */
    private void setGrade(Grade grade) {
        mGradeTitle.setText(grade.getTitle());
        mGradeFace.setImageDrawable(grade.getIcon());
        mGradeFace.setBackground(grade.getBg());
        mGradeHint.setText(grade.getHint());
    }

    /**
     * Initialize the UI.
     */
    private void initUI() {
        inflate(getContext(), R.layout.view_seek_grade_answer, this);

        mGradeFace = (ImageView) findViewById(R.id.grade_face);
        mGradeTitle = (TextView) findViewById(R.id.grade_title);
        mGradeHint = (TextView) findViewById(R.id.grade_hint);
        mGradeSeek = (SeekBar) findViewById(R.id.seek);

        mGradeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int shiftIco;
                int shiftTitle;
                if (0 == progress) {
                    shiftIco = 0;
                    shiftTitle = 0;
                } else if (seekBar.getMax() == progress) {
                    shiftIco = mGradeFace.getWidth();
                    shiftTitle = mGradeTitle.getWidth();
                } else {
                    shiftIco = mGradeFace.getWidth() / 2;
                    shiftTitle = mGradeTitle.getWidth() / 2;
                }

                if (grades != null) {
                    setGrade(grades.get(progress));
                }

                mGradeFace.setX(progress * seekBar.getWidth() / seekBar.getMax() - shiftIco);
                mGradeTitle.setX(progress * seekBar.getWidth() / seekBar.getMax() - shiftTitle);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Context context = GradeAnswerSeekView.this.getContext();
                if (context instanceof Activity) {
                    Fragment fragment = ((Activity) context).getFragmentManager().findFragmentByTag(ModuleActivity.MODULE_TAG);
                    if (fragment != null && fragment instanceof SubModuleView) {
                        ((SubModuleView) fragment).lockView(false);
                    }
                }
            }
        });
    }

}