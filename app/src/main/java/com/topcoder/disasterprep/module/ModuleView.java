/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module;

import android.os.Bundle;

/**
 * The view interface for the module activity
 *
 * @author TCSCODER
 * @version 1.0
 */
public interface ModuleView {
    Bundle getIntentBundle();

    void showModuleIntro(int level);

    void showModule(int level);

    /**
     * Show the module page of the given level and page
     *
     * @param level the level
     * @param page the page
     */
    void showModule(int level, int page);

    void showHelpMenu(boolean isShow);

    void setTitle(String title);

    void showModuleResult(int level);

    void showDashboard();

    void showAssessment();

    void showLogin();

    /**
     * Show the skip screen with given resource id
     *
     * @param dashboardResId the dashboard resource id
     */
    void showSkip(int dashboardResId);
}
