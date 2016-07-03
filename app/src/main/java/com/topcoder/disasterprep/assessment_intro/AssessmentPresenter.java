package com.topcoder.disasterprep.assessment_intro;

import com.topcoder.disasterprep.Presenter;

import java.util.List;

class AssessmentPresenter extends Presenter<AssessmentView> {

    public AssessmentPresenter(AssessmentView view) {
        super(view);
        List<AssessmentQuestion> questions = AssessmentModel.getQuestions(view.getContext());
        view.initProgress(questions.size(), 1);
        view.initQuestions(questions);
        view.initGrade(AssessmentModel.getGrades(view.getContext()));
    }
}
