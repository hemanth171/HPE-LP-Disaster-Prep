/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module;

import android.os.Bundle;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.Presenter;

/**
 * The presenter for the module activity
 *
 * @author TCSCODER
 * @version 1.0
 */
public class ModulePresenter extends Presenter<ModuleView> {

    /**
     * Constructor
     *
     * @param view the module view
     */
    public ModulePresenter(ModuleView view) {
        super(view);
        Bundle bundle = view.getIntentBundle();

        if (bundle.getBoolean(ModuleActivity.START_FROM_INTRO, false)) {
            view.showModuleIntro(1);
        } else {
            int level = bundle.getInt(ModuleActivity.MODULE_LEVEL, 1);
            int page = bundle.getInt(ModuleActivity.MODULE_PAGE, 1);

            view.showModule(level, page);
        }

    }
}
