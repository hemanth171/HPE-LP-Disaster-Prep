package com.topcoder.disasterprep.dashboard;

import android.content.Context;
import android.support.annotation.DrawableRes;

import java.util.List;

interface DashboardView {
    void showScores(List<DashboardActivity.Score> scores);

    void showAvatar(@DrawableRes int avatar_small);

    Context getViewContext();

    void showModules(List<ModuleView.Module> modules);

    void showPlan(ModuleView.Module module);

    void openBCModule();

    void showNotImplemented();
}
