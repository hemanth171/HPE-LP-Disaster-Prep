package com.topcoder.disasterprep.module;

import android.os.Bundle;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.Presenter;

public class ModulePresenter extends Presenter<ModuleView> {

    public ModulePresenter(ModuleView view) {
        super(view);
        Bundle bundle = view.getIntentBundle();
        if (null != bundle) {
            if (bundle.getBoolean(IntentExtras.MODULE_BC)) {
                if (bundle.getBoolean(IntentExtras.MODULE_INTRO)) {
                    view.showModuleIntro(1);
                }
            }
        }
    }
}
