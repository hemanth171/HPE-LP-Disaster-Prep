/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module;

import android.app.Fragment;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.dashboard.DashboardModel;
import com.topcoder.disasterprep.module.bc.BCFragment;
import com.topcoder.disasterprep.module.submodule.SubModuleFinishFragment;
import com.topcoder.disasterprep.module.submodule.SubModuleIntroFragment;

/**
 * The activity for the Business Continuity Plan module.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class BCModuleActivity extends ModuleActivity {
    /**
     * The fragment resource ids of the intro screens
     */
    private static final int[] INTRO_FRAGMENTS = {
            R.layout.fragment_bc_intro_1, R.layout.fragment_bc_intro_2
    };

    /**
     * The fragment resource ids of the finish screens
     */
    private static final int[] FINISH_FRAGMENTS = {
            R.layout.fragment_bc_finish_1, R.layout.fragment_bc_finish_2
    };

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
        return BCFragment.newInstance(level, page);
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
        return DashboardModel.BC_MODULE_TYPE;
    }
}
