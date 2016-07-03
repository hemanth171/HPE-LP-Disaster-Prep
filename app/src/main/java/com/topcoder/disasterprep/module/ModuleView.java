package com.topcoder.disasterprep.module;

import android.os.Bundle;

public interface ModuleView {
    Bundle getIntentBundle();

    void showModuleIntro(int level);

    void showModule(int level);

    void showHelpMenu(boolean isShow);

    void setTitle(String title);

    void showModuleResult(int level);

    void showDashboard();

    void showAssessment();
}
