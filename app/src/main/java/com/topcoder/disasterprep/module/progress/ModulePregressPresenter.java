/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.progress;

import android.os.Bundle;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.module.ModuleModel;

import java.util.Set;

/**
 * The presenter for the module progress activity
 *
 * @author TCSCODER
 * @version 1.0
 */
class ModulePregressPresenter extends Presenter<ModuleProgressView> {

    /**
     * Constructor
     *
     * @param view the module progress view
     * @param level the current level
     * @param moduleType the module type
     */
    public ModulePregressPresenter(ModuleProgressView view, int level, int moduleType) {
        super(view);

        ModuleModel.LevelInfo info = ModuleModel.getInstance().getModule(moduleType).getLevel(level);
        view.setPageTitle(info.title);
        view.setProgress(info.pages);
    }
}
