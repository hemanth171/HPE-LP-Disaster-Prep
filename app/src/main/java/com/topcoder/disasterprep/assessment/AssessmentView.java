package com.topcoder.disasterprep.assessment;

interface AssessmentView {
    void showIntro(int level);

    void setViewTitle(String title);

    void showQuestions(int level);

    void showResult(int level, int score, int max, int color);

    void showDashboard();

    void showAssessmentBcp();

    void showAssessmentAnswers();
}
