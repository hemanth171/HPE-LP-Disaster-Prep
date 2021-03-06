/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module;

import android.app.Fragment;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.dashboard.DashboardModel;
import com.topcoder.disasterprep.module.submodule.SubModuleFinishFragment;
import com.topcoder.disasterprep.module.submodule.SubModuleIntroFragment;
import com.topcoder.disasterprep.module.er.ERFragment;

/**
 * The activity for the Employee Resilience module.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class ERModuleActivity extends ModuleActivity {
    /**
     * The fragment resource ids of the intro screens
     */
    private static final int[] INTRO_FRAGMENTS = {
            R.layout.fragment_er_intro_1, R.layout.fragment_er_intro_2
    };

    /**
     * The fragment resource ids of the finish screens
     */
    private static final int[] FINISH_FRAGMENTS = {
            R.layout.fragment_er_finish_1, R.layout.fragment_er_finish_2
    };

    /**
     * Get the title bar background resource id
     *
     * @return the title bar background resource id
     */
    @Override
    protected int getTitleBarBackgroundResource() {
        return R.color.red1;
    }

    /**
     * Get the fragment instance of the intro screen
     *
     * @param level the current level
     * @return the fragment instance of the intro screen
     */
    @Override
    protected Fragment getIntroFragment(int level) {
        return SubModuleIntroFragment.newInstance(level, INTRO_FRAGMENTS);
    }

    /**
     * Return the fragment instance of module page screen
     *
     * @param level the current level
     * @param page the current page
     * @return the fragment instance of the module page screen
     */
    @Override
    protected Fragment getModuleFragment(int level, int page) {
        return ERFragment.newInstance(level, page);
    }

    /**
     * Get the fragment instance of the finish screen
     *
     * @param level the current level
     * @return the fragment instance of the finish screen
     */
    @Override
    protected Fragment getFinishFragment(int level) {
        return SubModuleFinishFragment.newInstance(level, FINISH_FRAGMENTS);
    }

    /**
     * Return the module type
     *
     * @return the module type
     */
    @Override
    protected int getModuleType() {
        return DashboardModel.ER_MODULE_TYPE;
    }
}
