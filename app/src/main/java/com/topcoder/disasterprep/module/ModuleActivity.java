/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.assessment.AssessmentActivity;
import com.topcoder.disasterprep.dashboard.DashboardActivity;
import com.topcoder.disasterprep.dashboard.DashboardModel;
import com.topcoder.disasterprep.login.LoginActivity;
import com.topcoder.disasterprep.module.help.HelpActivity;
import com.topcoder.disasterprep.module.progress.ModuleProgressActivity;
import com.topcoder.disasterprep.module.skip.SkipFragment;

/**
 * The abstract activity for the module screens
 *
 * @author TCSCODER
 * @version 1.0
 */
public abstract class ModuleActivity extends AppCompatActivity implements View.OnClickListener, ModuleView {
    /**
     * The start from intro bundle argument
     */
    public static final String START_FROM_INTRO = "start_from_intro";

    /**
     * The module level bundle argument
     */
    public static final String MODULE_LEVEL = "module_level";

    /**
     * The module page bundle argument
     */
    public static final String MODULE_PAGE = "module_page";

    /**
     * The tag for the intro screen
     */
    private static final String INTRO_TAG = "intro_tag";

    /**
     * The page for the module page screen
     */
    public static final String MODULE_TAG = "module_tag";
    private static final String MODULE_RESULT_TAG = "module_result_tag";
    private static final String SKIP_TAG = "tag_skip";

    private int moduleLevel = 1;
    private View mMenuHelp;
    private TextView mTitleView;

    /**
     * Create a new intent to show the intro screen from start
     *
     * @param context the context
     * @param moduleType the module type
     * @return the created intent
     */
    public static Intent newIntentFromStart(Context context, int moduleType) {
        Intent intent = getIntent(context, moduleType);
        if (intent != null) {
            intent.putExtra(START_FROM_INTRO, true);
        }
        return intent;
    }

    /**
     * Create a new intent to continue to the given level and page
     *
     * @param context the context
     * @param moduleType the module type
     * @param level the level
     * @param page the page
     * @return the created intent
     */
    public static Intent newIntentSkipTo(Context context, int moduleType, int level, int page) {
        Intent intent = getIntent(context, moduleType);
        if (intent != null) {
            intent.putExtra(MODULE_LEVEL, level);
            intent.putExtra(MODULE_PAGE, page);
        }
        return intent;
    }

    /**
     * Create the intent for given module type
     *
     * @param context the context
     * @param moduleType the module type
     * @return the created intent
     */
    private static Intent getIntent(Context context, int moduleType) {
        switch (moduleType) {
            case DashboardModel.BC_MODULE_TYPE:
                return new Intent(context, BCModuleActivity.class);
            case DashboardModel.LC_MODULE_TYPE:
                return new Intent(context, LCModuleActivity.class);
            case DashboardModel.CR_MODULE_TYPE:
                return new Intent(context, CRModuleActivity.class);
            case DashboardModel.NR_MODULE_TYPE:
                return new Intent(context, NRModuleActivity.class);
            case DashboardModel.ER_MODULE_TYPE:
                return new Intent(context, ERModuleActivity.class);
        }
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        initViews();
        new ModulePresenter(this);
    }

    /**
     * Initialize the view
     */
    private void initViews() {
        findViewById(R.id.home).setOnClickListener(this);
        findViewById(R.id.progress).setOnClickListener(this);

        int resId = getTitleBarBackgroundResource();
        if (resId > 0) {
            findViewById(R.id.titleBar).setBackgroundResource(resId);
        }

        mMenuHelp = findViewById(R.id.help);
        mMenuHelp.setOnClickListener(this);
        mTitleView = (TextView) findViewById(R.id.title);
    }

    /**
     * Get the title bar background resource id
     *
     * @return the title bar background resource id
     */
    protected int getTitleBarBackgroundResource() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home:
                showDashboard();
                break;
            case R.id.progress:
                Intent progress = new Intent(this, ModuleProgressActivity.class);
                progress.putExtra(IntentExtras.MODULE_TYPE, getModuleType());
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
    public void showDashboard() {
        Intent home = new Intent(this, DashboardActivity.class);
        startActivity(home);
    }

    @Override
    public void showAssessment() {
        Intent start = new Intent(this, AssessmentActivity.class);
        startActivity(start);
    }

    @Override
    public void showLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(IntentExtras.LOGIN_LOGIN, true);
        startActivity(intent);
    }

    /**
     * Show the skip screen
     *
     * @param dashboardResId the dashboard resource id
     */
    @Override
    public void showSkip(int dashboardResId) {
        addFragment( SkipFragment.newInstance(dashboardResId),SKIP_TAG, SKIP_TAG);
    }

    /**
     * Show the module intro screen
     *
     * @param level the level
     */
    @Override
    public void showModuleIntro(int level) {
        if (level <= 2) {
            moduleLevel = level;
            addFragment(getIntroFragment(level), INTRO_TAG, null, false);
        } else {
            if (getModuleType() == DashboardModel.BC_MODULE_TYPE) {
                showAssessment();
            } else if (getModuleType() == DashboardModel.LC_MODULE_TYPE) {
                Intent intent = ModuleActivity.newIntentFromStart(this, DashboardModel.NR_MODULE_TYPE);
                startActivity(intent);
            } else if (getModuleType() == DashboardModel.NR_MODULE_TYPE) {
                Intent intent = ModuleActivity.newIntentFromStart(this, DashboardModel.CR_MODULE_TYPE);
                startActivity(intent);
            } else if (getModuleType() == DashboardModel.CR_MODULE_TYPE) {
                Intent intent = ModuleActivity.newIntentFromStart(this, DashboardModel.ER_MODULE_TYPE);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Next module is not implemented right now", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Show the module page screen
     *
     * @param level the level
     */
    @Override
    public void showModule(int level) {
        showModule(level, 1);
    }

    /**
     * Show the module page screen
     *
     * @param level the level
     * @param page the page
     */
    @Override
    public void showModule(int level, int page) {
        moduleLevel = level;
        addFragment(getModuleFragment(level, page), MODULE_TAG, MODULE_TAG + String.valueOf(level));
    }

    /**
     * Show the module result screen
     *
     * @param level the level
     */
    @Override
    public void showModuleResult(int level) {
        moduleLevel = level;
        addFragment(getFinishFragment(level), MODULE_RESULT_TAG, MODULE_RESULT_TAG);
    }

    /**
     * Get the fragment instance of the intro screen
     *
     * @param level the current level
     * @return the fragment instance of the intro screen
     */
    protected abstract Fragment getIntroFragment(int level);

    /**
     * Return the fragment instance of module page screen
     *
     * @param level the current level
     * @param page the current page
     * @return the fragment instance of the module page screen
     */
    protected abstract Fragment getModuleFragment(int level, int page);

    /**
     * Get the fragment instance of the finish screen
     *
     * @param level the current level
     * @return the fragment instance of the finish screen
     */
    protected abstract Fragment getFinishFragment(int level);

    /**
     * Return the module type
     *
     * @return the module type
     */
    protected abstract int getModuleType();

    /**
     * Add fragment
     *
     * @param fragment the fragment to add
     * @param tag the tag
     * @param backStackTag the backstack tag
     */
    protected void addFragment(Fragment fragment, String tag, String backStackTag) {
        addFragment(fragment, tag, backStackTag, true);
    }

    /**
     * Add fragment
     *
     * @param fragment the fragment to add
     * @param tag the tag
     * @param backStackTag the backstack tag
     * @param animated should be animated or not
     */
    protected void addFragment(Fragment fragment, String tag, String backStackTag, boolean animated) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (animated) {
            fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        }
        fragmentTransaction.replace(R.id.container, fragment, tag);

        if (backStackTag != null) {
            fragmentTransaction.addToBackStack(backStackTag);
        }
        fragmentTransaction.commit();
    }
}
