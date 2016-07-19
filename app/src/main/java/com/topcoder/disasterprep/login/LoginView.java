/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.login;

import android.os.Bundle;

/**
 * The view interface for the login activity
 *
 * @author TCSCODER
 * @version 1.0
 */
interface LoginView {

    Bundle getIntentBundle();

    void showLandingPage();

    void showLogin();

    void showSignup();

    void showFirstTime();

    void showScore();

    /**
     * Show the BC module screen.
     */
    void showBCModule();

    /**
     * Show the forgot password screen.
     */
    void showForgotPassword();

    void showDashboard();

    void showAssessment();
}
