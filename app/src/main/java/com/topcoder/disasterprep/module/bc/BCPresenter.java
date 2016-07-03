package com.topcoder.disasterprep.module.bc;

import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleModel;

import java.util.Random;

public class BCPresenter extends Presenter<BCView> {

    private static final int MAX = 8;

    public BCPresenter(BCView view, int level) {
        super(view);
        int[] steps;
        switch (level) {
            case 1:
            default:
                steps = new int[]{R.layout.view_module_bc_step_1,
                        R.layout.view_module_bc_step_2,
                        R.layout.view_module_bc_step_3,
                        R.layout.view_module_bc_step_4,
                        R.layout.view_module_bc_step_5,
                        R.layout.view_module_bc_step_6,
                        R.layout.view_module_bc_step_7,
                        R.layout.view_module_bc_step_8,
                        R.layout.view_module_bc_step_9,
                        R.layout.view_module_bc_step_10,
                        R.layout.view_module_bc_step_11};
                break;
            case 2:
                steps = new int[]{R.layout.view_module_bc_2_step_1,
                        R.layout.view_module_bc_2_step_2,
                        R.layout.view_module_bc_2_step_3,
                        R.layout.view_module_bc_2_step_4,
                        R.layout.view_module_bc_2_step_5,
                        R.layout.view_module_bc_2_step_6,
                        R.layout.view_module_bc_2_step_7};

        }
        view.setSteps(steps);
    }

    void onPageSelected(int level, int position) {
        ModuleModel.getInstance().setBCPageProgress(level, position + 1, new Random().nextInt(MAX) + 1);
        if (position > 0) {
            ModuleModel.getInstance().setBCPageProgress(level, position, MAX);
        }
    }

    void onShowResult(int level, int lastPosition) {
        ModuleModel.getInstance().setBCPageProgress(level, lastPosition + 1, MAX);
    }
}
