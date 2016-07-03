package com.topcoder.disasterprep.login;

import android.os.Bundle;

interface LoginView {

    Bundle getIntentBundle();

    void showLandingPage();

    void showLogin();

    void showSignup();

    void showFirstTime();

    void showScore();

    void showBCModule();

    void showDashboard();

    void showAssessment();
}
