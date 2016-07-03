package com.topcoder.disasterprep.assessment;

import com.topcoder.disasterprep.Presenter;

class AssessmentPresenter extends Presenter<AssessmentView> {

    AssessmentPresenter(AssessmentView view) {
        super(view);
        view.showIntro(1);
    }
}
