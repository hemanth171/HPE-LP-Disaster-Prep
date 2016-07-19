/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.bc;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.dashboard.DashboardModel;
import com.topcoder.disasterprep.module.submodule.SubModuleFragment;

/**
 * The fragment for the Business Continuity Plan module.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class BCFragment extends SubModuleFragment {

    /**
     * The level 1 page fragments
     */
    private static final int[] LEVEL_1_FRAGMENTS = {
            R.layout.view_module_bc_step_1,
            R.layout.view_module_bc_step_2,
            R.layout.view_module_bc_step_3,
            R.layout.view_module_bc_step_4,
            R.layout.view_module_bc_step_5,
            R.layout.view_module_bc_step_6,
            R.layout.view_module_bc_step_7,
            R.layout.view_module_bc_step_8,
            R.layout.view_module_bc_step_9,
            R.layout.view_module_bc_step_10,
            R.layout.view_module_bc_step_11
    };

    /**
     * The level 2 page fragments
     */
    private static final int[] LEVEL_2_FRAGMENTS = {
            R.layout.view_module_bc_2_step_1,
            R.layout.view_module_bc_2_step_2,
            R.layout.view_module_bc_2_step_3,
            R.layout.view_module_bc_2_step_4,
            R.layout.view_module_bc_2_step_5,
            R.layout.view_module_bc_2_step_6,
            R.layout.view_module_bc_2_step_7
    };

    /**
     * Create a new instance of fragment showing the given page of on given level
     *
     * @param level the level
     * @param page the page
     * @return the fragment
     */
    public static BCFragment newInstance(int level, int page) {
        return (BCFragment) SubModuleFragment.newInstance(level, page, new BCFragment());
    }

    /**
     * Return the module type
     *
     * @return the module type
     */
    protected int getModuleType() {
        return DashboardModel.BC_MODULE_TYPE;
    }

    /**
     * Return the module title.
     *
     * @return the module title.
     */
    protected String getModuleTitle() {
        return "Business Continuity Planning";
    }

    /**
     * Return the button background drawable resource id (for skip button)
     *
     * @return  the button background drawable resource id
     */
    protected int getButtonBackground() {
        return R.drawable.button_bg1;
    }

    /**
     * Return the layout fragment resource id
     *
     * @return the layout fragment resource id
     */
    protected int getLayoutFragment() {
        return R.layout.fragment_bc;
    }

    /**
     * Return the page (or step) fragments of given level
     *
     * @param level the level
     * @return the page (or step) fragments
     */
    protected int[] getStepFragments(int level) {
        if (level == 1) {
            return LEVEL_1_FRAGMENTS;
        } else {
            // level == 2
            return LEVEL_2_FRAGMENTS;
        }
    }
}
