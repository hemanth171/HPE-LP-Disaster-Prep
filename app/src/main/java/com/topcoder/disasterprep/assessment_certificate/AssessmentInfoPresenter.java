package com.topcoder.disasterprep.assessment_certificate;

import android.os.Bundle;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.SharedUtils;

public class AssessmentInfoPresenter extends Presenter<AssessmentInfoView> {

    public AssessmentInfoPresenter(AssessmentInfoView view) {
        super(view);
        view.showPages(new int[]{R.layout.view_assessment_certificate, R.layout.view_assessment_answers, R.layout.view_assessment_bcp, R.layout.view_assessment_capabilities},
                new String[]{"Certificate", "Answers", "BCP", "Capabilities"},
                SharedUtils.getBusinessName(view.getViewContext()));
        Bundle bundle = view.getExtras();
        if (null != bundle) {
            if (IntentExtras.INFO_TYPE_BC == bundle.getInt(IntentExtras.INFO_TYPE)) {
                view.showPage(2);
            } else if (IntentExtras.INFO_TYPE_ANSWERS == bundle.getInt(IntentExtras.INFO_TYPE)) {
                view.showPage(1);
            }
        }
    }
}
