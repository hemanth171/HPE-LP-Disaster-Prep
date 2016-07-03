package com.topcoder.disasterprep.assessment_intro;

import android.content.Context;

import java.util.List;

interface AssessmentView {
    void initProgress(int count, int step);

    void initQuestions(List<AssessmentQuestion> questions);

    Context getContext();

    void initGrade(List<Grade> grades);

    void signUp();

    void login();
}
