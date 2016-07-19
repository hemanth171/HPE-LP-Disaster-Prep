/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.nr;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.dashboard.DashboardModel;
import com.topcoder.disasterprep.module.submodule.SubModuleFragment;

/**
 * The fragment for the Networks and Relationships module.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class NRFragment extends SubModuleFragment {
    /**
     * The level 1 page fragments
     */
    private static final int[] LEVEL_1_FRAGMENTS = {
            R.layout.view_module_nr_step_1,
            R.layout.view_module_nr_step_2,
            R.layout.view_module_nr_step_3,
            R.layout.view_module_nr_step_4,
            R.layout.view_module_nr_step_5,
            R.layout.view_module_nr_step_6,
            R.layout.view_module_nr_step_7
    };

    /**
     * The level 2 page fragments
     */
    private static final int[] LEVEL_2_FRAGMENTS = {
            R.layout.view_module_nr_2_step_1,
            R.layout.view_module_nr_2_step_2,
            R.layout.view_module_nr_2_step_3,
            R.layout.view_module_nr_2_step_4
    };

    /**
     * Create a new instance of fragment showing the given page of on given level
     *
     * @param level the level
     * @param page the page
     * @return the fragment
     */
    public static NRFragment newInstance(int level, int page) {
        return (NRFragment) SubModuleFragment.newInstance(level, page, new NRFragment());
    }

    /**
     * Return the module type
     *
     * @return the module type
     */
    protected int getModuleType() {
        return DashboardModel.NR_MODULE_TYPE;
    }

    /**
     * Return the module title.
     *
     * @return the module title.
     */
    protected String getModuleTitle() {
        return "Networks and Relationships";
    }

    /**
     * Return the button background drawable resource id (for skip button)
     *
     * @return  the button background drawable resource id
     */
    protected int getButtonBackground() {
        return R.drawable.button_yellow_bg;
    }

    /**
     * Return the layout fragment resource id
     *
     * @return the layout fragment resource id
     */
    protected int getLayoutFragment() {
        return R.layout.fragment_nr;
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