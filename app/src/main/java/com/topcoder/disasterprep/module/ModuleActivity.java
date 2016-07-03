package com.topcoder.disasterprep.module;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.assessment.AssessmentActivity;
import com.topcoder.disasterprep.dashboard.DashboardActivity;
import com.topcoder.disasterprep.module.bc.BCFinishFragment;
import com.topcoder.disasterprep.module.bc.BCFragment;
import com.topcoder.disasterprep.module.bc.BCIntroFragment;
import com.topcoder.disasterprep.module.help.HelpActivity;
import com.topcoder.disasterprep.module.progress.ModuleProgressActivity;

public class ModuleActivity extends AppCompatActivity implements View.OnClickListener, ModuleView {

    private static final String BC_INTRO_TAG = "bc_intro_tag";
    private static final String BC_TAG = "bc_tag";
    private static final String MODULE_RESULT_TAG = "module_result_tag";

    private int moduleLevel = 1;
    private View mMenuHelp;
    private TextView mTitleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        initViews();
        new ModulePresenter(this);
    }

    private void initViews() {
        findViewById(R.id.home).setOnClickListener(this);
        findViewById(R.id.progress).setOnClickListener(this);
        mMenuHelp = findViewById(R.id.help);
        mMenuHelp.setOnClickListener(this);
        mTitleView = (TextView) findViewById(R.id.title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home:
                showDashboard();
                break;
            case R.id.progress:
                Intent progress = new Intent(this, ModuleProgressActivity.class);
                progress.putExtra(IntentExtras.MODULE_TYPE, IntentExtras.MODULE_TYPE_BC);
                progress.putExtra(IntentExtras.MODULE_LEVEL, moduleLevel);
                startActivity(progress);
                break;
            case R.id.help:
                Intent help = new Intent(this, HelpActivity.class);
                startActivity(help);
                break;
        }
    }

    @Override
    public Bundle getIntentBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void showModuleIntro(int level) {
        moduleLevel = level;
        if (level <= 2) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, BCIntroFragment.newInstance(level), BC_INTRO_TAG);
            fragmentTransaction.commit();
        } else {
            showAssessment();
        }
    }

    @Override
    public void showModule(int level) {
        moduleLevel = level;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.replace(R.id.container, BCFragment.newInstance(level), BC_TAG);
        fragmentTransaction.addToBackStack(BC_TAG + String.valueOf(level));
        fragmentTransaction.commit();
    }

    @Override
    public void showHelpMenu(boolean isShow) {
        mMenuHelp.setVisibility(isShow? View.VISIBLE : View.GONE);
        mTitleView.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setTitle(String title) {
        mTitleView = (TextView) findViewById(R.id.title);
        mTitleView.setText(title);
        mTitleView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showModuleResult(int level) {
        moduleLevel = level;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.replace(R.id.container, BCFinishFragment.newInstance(level), MODULE_RESULT_TAG);
        fragmentTransaction.addToBackStack(MODULE_RESULT_TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void showDashboard() {
        Intent home = new Intent(this, DashboardActivity.class);
        startActivity(home);
    }

    @Override
    public void showAssessment() {
        Intent start = new Intent(this, AssessmentActivity.class);
        startActivity(start);
    }
}
