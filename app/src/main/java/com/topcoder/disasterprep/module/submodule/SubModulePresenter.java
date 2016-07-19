/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.submodule;

import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleModel;

import java.util.Random;

/**
 * The presenter for the sub module.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class SubModulePresenter extends Presenter<SubModuleView> {
    /**
     * The max progress value
     */
    private static final int MAX = 8;

    /**
     * The module type
     */
    private int moduleType;

    /**
     * Constructor
     *
     * @param view the view interface
     * @param moduleType the module type
     * @param page the page
     * @param levelPages the level pages
     */
    public SubModulePresenter(SubModuleView view, int moduleType, int page, int[] levelPages) {
        super(view);

        this.moduleType = moduleType;
        view.setSteps(levelPages, null, page);
        view.lockView(true);

    }

    /**
     * Called when moving to specific page
     *
     * @param level the level
     * @param position the position
     */
    void onPageSelected(int level, int position) {
        ModuleModel.getInstance().getModule(moduleType).setPageProgress(level, position + 1, new Random().nextInt(MAX) + 1);
        if (position > 0) {
            ModuleModel.getInstance().getModule(moduleType).setPageProgress(level, position, MAX);
        }
    }

    /**
     * Called when the result is shown
     *
     * @param level the level
     * @param lastPosition the last position
     */
    void onShowResult(int level, int lastPosition) {
        ModuleModel.getInstance().getModule(moduleType).setPageProgress(level, lastPosition + 1, MAX);
    }
}
