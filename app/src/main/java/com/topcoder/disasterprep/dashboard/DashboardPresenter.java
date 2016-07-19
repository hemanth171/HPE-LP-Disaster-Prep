/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.dashboard;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.R;

import java.util.ArrayList;
import java.util.List;

/**
 * The presenter for the dashboard activity.
 *
 * @author TCSCODER
 * @version 1.0
 */
class DashboardPresenter extends Presenter<DashboardView> {

    public DashboardPresenter(DashboardView view) {
        super(view);
        List<DashboardActivity.Score> levels = convertScores(DashboardModel.getScores());
        view.showScores(levels);
        view.showAvatar(R.drawable.avatar_small);
        List<ModuleView.Module> modules = convertModules(DashboardModel.getModules());
        view.showModules(modules);
        view.showPlan(convertPlan(DashboardModel.getPlan()));
    }

    /**
     * Convert the dashboard plan data to ModuleView's module
     *
     * @param plan the dashboard plan data
     * @return the ModuleView's module data
     */
    private ModuleView.Module convertPlan(DashboardModel.Module plan) {
        int textColorInactive = ContextCompat.getColor(view.getViewContext(), R.color.score_inactive);
        int textColorActive = ContextCompat.getColor(view.getViewContext(), R.color.dashboard_grey);
        return new ModuleView.Module(plan.getModuleType(), plan.getTitle(), resToDrawable(plan.getButtonBgRes()), -1, null, null, null,
                resToDrawable(plan.getIconsRes()), plan.getLevels(), plan.getProgresses(),
                plan.getMaxProgresses(), textColorInactive, textColorActive);
    }

    /**
     * Convert the dashboard modules data to ModuleView's modules
     *
     * @param modules the dashboard modules data
     * @return the ModuleView's modules data
     */
    private List<ModuleView.Module> convertModules(List<DashboardModel.Module> modules) {
        int size = modules.size();
        List<ModuleView.Module> viewModules = new ArrayList<>(size);
        Drawable progressInactive = resToDrawable(R.drawable.ring_grey_thin);
        Drawable bgInactive = resToDrawable(R.drawable.circle_grey2);
//        bgInactive.setColorFilter(Color.RED, PorterDuff.Mode.OVERLAY);
        for (DashboardModel.Module module : modules) {
            int textColorInactive = ContextCompat.getColor(view.getViewContext(), R.color.score_inactive);
            int textColorActive = ContextCompat.getColor(view.getViewContext(), R.color.dashboard_grey);
            viewModules.add(new ModuleView.Module(module.getModuleType(), module.getTitle(), resToDrawable(module.getButtonBgRes()),
                    module.getProgressRes(), resToDrawable(module.getBgRes()),
                    progressInactive, bgInactive, resToDrawable(module.getIconsRes()),
                    module.getLevels(), module.getProgresses(), module.getMaxProgresses(),
                    textColorInactive, textColorActive));
        }
        return viewModules;
    }

    private Drawable[] resToDrawable(int[] iconsRes) {
        Drawable[] drawables = new Drawable[iconsRes.length];
        for (int i = 0; i < iconsRes.length; i++) {
            drawables[i] = resToDrawable(iconsRes[i]);
        }
        return drawables;
    }

    private Drawable resToDrawable(int res) {
        return ContextCompat.getDrawable(view.getViewContext(), res);
    }

    @NonNull
    private List<DashboardActivity.Score> convertScores(List<DashboardModel.Score> scores) {
        int size = scores.size();
        List<DashboardActivity.Score> levels = new ArrayList<>(size);
        for (DashboardModel.Score score :
                scores) {
            levels.add(new DashboardActivity.Score(score.getTitle(), score.getScore(), score.getColor()));
        }
        return levels;
    }

}
