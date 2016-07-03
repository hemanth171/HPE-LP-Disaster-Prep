package com.topcoder.disasterprep.assessment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.assessment_certificate.AssessmentInfoActivity;
import com.topcoder.disasterprep.dashboard.DashboardActivity;

public class AssessmentActivity extends AppCompatActivity implements AssessmentView {

    private static final String QUESTIONS_TAG = "assessment_questions_tag";
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        initViews();
        new AssessmentPresenter(this);
    }

    private void initViews() {
        mTitle = (TextView) findViewById(R.id.title);
    }

    @Override
    public void showIntro(int level) {
        if (level <= 2) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, IntroFragment.newInstance(level));
            fragmentTransaction.commit();
        }
    }

    @Override
    public void setViewTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void showQuestions(int level) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        transaction.replace(R.id.container, QuestionsFragment.newInstance(level));
        transaction.addToBackStack(QUESTIONS_TAG + String.valueOf(level));
        transaction.commit();
    }

    @Override
    public void showResult(int level, int score, int max, @ColorRes int color) {
        ResultDialog.newInstance(level, score, max, color).show(getFragmentManager(), ResultDialog.class.getSimpleName());
    }

    @Override
    public void showDashboard() {
        Intent start = new Intent(this, DashboardActivity.class);
        startActivity(start);
    }

    @Override
    public void showAssessmentBcp() {
        Intent start = new Intent(this, AssessmentInfoActivity.class);
        start.putExtra(IntentExtras.INFO_TYPE, IntentExtras.INFO_TYPE_BC);
        startActivity(start);
    }

    @Override
    public void showAssessmentAnswers() {
        Intent start = new Intent(this, AssessmentInfoActivity.class);
        start.putExtra(IntentExtras.INFO_TYPE, IntentExtras.INFO_TYPE_ANSWERS);
        startActivity(start);
    }
}
