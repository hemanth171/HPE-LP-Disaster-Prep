package com.topcoder.disasterprep.module.progress;

import android.os.Bundle;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.module.ModuleModel;

import java.util.Set;

class ModulePregressPresenter extends Presenter<ModuleProgressView> {

    public ModulePregressPresenter(ModuleProgressView view, int level) {
        super(view);
        Bundle bundle = view.getIntentBundle();
        if (null != bundle) {
            Set<String> keys = bundle.keySet();
            if (keys.contains(IntentExtras.MODULE_TYPE)) {
                if (IntentExtras.MODULE_TYPE_BC == bundle.getInt(IntentExtras.MODULE_TYPE)) {
                    ModuleModel.LevelInfo info = ModuleModel.getInstance().getBCLevelInfo(level);
                    view.setPageTitle(info.title);
                    view.setProgress(info.pages);
                }
            }
        }
    }
}
