/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.assessment_intro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.NavigationView;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.login.LoginActivity;
import com.topcoder.disasterprep.module.submodule.LockableViewPager;

import java.util.List;

/**
 * The activity for the landing assessment screens before login.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class AssessmentActivity extends AppCompatActivity implements AssessmentView {

    private IndicatorProgressView mProgress;
    private NavigationView mNavigationView;

    /**
     * The custom view for the slider
     */
    private GradeAnswerSeekView mSeekView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_assessment);
        setTitle(getString(R.string.assessment));
        initViews();
        new AssessmentPresenter(this);
    }

    /**
     * Do some initialization
     */
    private void initViews() {
        mProgress = (IndicatorProgressView) findViewById(R.id.progress);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mSeekView = (GradeAnswerSeekView) findViewById(R.id.seekView);
    }

    @Override
    public void initProgress(int count, int step) {
        mProgress.setChildren(count);
        mProgress.setStep(1);
    }

    @Override
    public void initQuestions(List<AssessmentQuestion> questions) {
        PagerAdapter adapter = new QuestionAdapter(questions);
        LockableViewPager pager = (LockableViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(questions.size());
        pager.setAdapter(adapter);
        mNavigationView.setPager(pager,
                new NavigationView.ResultListener() {
                    @Override
                    public void showResult() {
                        new ResultDialog().show(getFragmentManager(), ResultDialog.class.getSimpleName());
                    }

                    @Override
                    public void showSkip() {

                    }
                },
                new NavigationView.StepListener() {
                    @Override
                    public void setStep(int step) {
                        mProgress.setStep(step);
                        mSeekView.setProgress(0);
                    }
                });
    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * Initialize the data for the seek view
     *
     * @param grades the grades data
     */
    @Override
    public void initGrade(final List<Grade> grades) {
        mSeekView.setGrades(grades);
    }

    @Override
    public void signUp() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(IntentExtras.LOGIN_SIGNUP, true);
        startActivity(intent);
    }

    @Override
    public void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(IntentExtras.LOGIN_LOGIN, true);
        startActivity(intent);
    }
}
