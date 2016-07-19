/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.dashboard;

import android.content.Context;
import android.support.annotation.DrawableRes;

import java.util.List;

/**
 * The view interface for the dashboard activity
 *
 * @author TCSCODER
 * @version 1.0
 */
interface DashboardView {
    void showScores(List<DashboardActivity.Score> scores);

    void showAvatar(@DrawableRes int avatar_small);

    Context getViewContext();

    void showModules(List<ModuleView.Module> modules);

    void showPlan(ModuleView.Module module);

    /**
     * Open the module screen of the given module type.
     *
     * @param moduleType the module type
     */
    void openModule(int moduleType);

    /**
     * Continue to the current level of module of the given module type
     *
     * @param moduleType the module type
     * @param level the current level
     * @param page the current page step
     */
    void continueInModule(int moduleType, int level, int page);

    void showNotImplemented();
}
