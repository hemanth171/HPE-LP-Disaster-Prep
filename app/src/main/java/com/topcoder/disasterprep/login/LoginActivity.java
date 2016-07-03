package com.topcoder.disasterprep.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.assessment_intro.AssessmentActivity;
import com.topcoder.disasterprep.dashboard.DashboardActivity;
import com.topcoder.disasterprep.module.ModuleActivity;

public class LoginActivity extends AppCompatActivity
        implements LoginView {
    private static final String LANDING_TAG = "landing_tag";
    private static final String LOGIN_TAG = "login_tag";
    private static final String SIGNUP_TAG = "signup_tag";
    private static final String FIRST_TIME_TAG = "first_time_tag";
    private static final String SCORE_TAG = "score_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new LoginPresenter(this);
    }

    @Override
    public Bundle getIntentBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void showLandingPage() {
        FragmentManager fragManager = getFragmentManager();
        Fragment replacingFragment = fragManager.findFragmentByTag(LANDING_TAG);
        if (null == replacingFragment) {
            replacingFragment = new LandingFragment();
        }
        FragmentTransaction fragmentTransaction = fragManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.replace(R.id.container, replacingFragment, LANDING_TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void showLogin() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment replacing = fragmentManager.findFragmentByTag(LOGIN_TAG);
        if (null == replacing) {
            replacing = new LoginScreenFragment();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.replace(R.id.container, replacing, LOGIN_TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void showSignup() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment replacing = fragmentManager.findFragmentByTag(SIGNUP_TAG);
        if (null == replacing) {
            replacing = new SignupFragment();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.replace(R.id.container, replacing, SIGNUP_TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void showFirstTime() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment replacing = fragmentManager.findFragmentByTag(FIRST_TIME_TAG);
        if (null == replacing) {
            replacing = new FirstTimeFragment();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.replace(R.id.container, replacing, FIRST_TIME_TAG);
        fragmentTransaction.addToBackStack(LANDING_TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void showScore() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment replacing = fragmentManager.findFragmentByTag(SCORE_TAG);
        if (null == replacing) {
            replacing = new ScoreFragment();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.replace(R.id.container, replacing, SCORE_TAG);
        fragmentTransaction.addToBackStack(SCORE_TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void showBCModule() {
        Intent start = new Intent(this, ModuleActivity.class);
        start.putExtra(IntentExtras.MODULE_BC, true);
        start.putExtra(IntentExtras.MODULE_INTRO, true);
        startActivity(start);
    }

    @Override
    public void showDashboard() {
        Intent start = new Intent(this, DashboardActivity.class);
        startActivity(start);
    }

    @Override
    public void showAssessment() {
        Intent start = new Intent(this, AssessmentActivity.class);
        startActivity(start);
    }

}
