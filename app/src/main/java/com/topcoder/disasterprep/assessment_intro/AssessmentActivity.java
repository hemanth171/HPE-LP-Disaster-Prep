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
import com.topcoder.disasterprep.module.bc.LockableViewPager;

import java.util.List;

public class AssessmentActivity extends AppCompatActivity implements AssessmentView {

    private IndicatorProgressView mProgress;
    private ImageView mGradeFace;
    private SeekBar mGradeSeek;
    private TextView mGradeTitle;
    private TextView mGradeHint;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_assessment);
        setTitle(getString(R.string.assessment));
        initViews();
        new AssessmentPresenter(this);
    }

    private void initViews() {
        mProgress = (IndicatorProgressView) findViewById(R.id.progress);
        mGradeFace = (ImageView) findViewById(R.id.grade_face);
        mGradeTitle = (TextView) findViewById(R.id.grade_title);
        mGradeHint = (TextView) findViewById(R.id.grade_hint);
        mGradeSeek = (SeekBar) findViewById(R.id.seek);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
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
                        mGradeSeek.setProgress(0);
                    }
                });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initGrade(final List<Grade> grades) {
        setGrade(grades.get(0));
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
                setGrade(grades.get(progress));
                mGradeFace.setX(progress * seekBar.getWidth() / seekBar.getMax() - shiftIco);
                mGradeTitle.setX(progress * seekBar.getWidth() / seekBar.getMax() - shiftTitle);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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

    private void setGrade(Grade grade) {
        mGradeTitle.setText(grade.getTitle());
        mGradeFace.setImageDrawable(grade.getIcon());
        mGradeFace.setBackground(grade.getBg());
        mGradeHint.setText(grade.getHint());
    }
}
