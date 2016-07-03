package com.topcoder.disasterprep.module.progress;

import android.os.Bundle;

import com.topcoder.disasterprep.module.ModuleModel;

import java.util.List;

interface ModuleProgressView {
    Bundle getIntentBundle();

    void setPageTitle(String title);

    void setProgress(List<ModuleModel.PageInfo> pages);
}
