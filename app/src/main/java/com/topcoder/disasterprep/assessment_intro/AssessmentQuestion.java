package com.topcoder.disasterprep.assessment_intro;

import android.support.annotation.Nullable;

import java.util.List;

class AssessmentQuestion {
    private List<Grade> grades;
    private int number;
    private String title;
    private String question;
    private String controlHint;

    public AssessmentQuestion(int number, String title, String question, String controlHint, List<Grade> grades) {
        this.number = number;
        this.title = title;
        this.question = question;
        this.controlHint = controlHint;
        this.grades = grades;
    }

    int getNumber() {
        return number;
    }

    String getTitle() {
        return title;
    }

    String getQuestion() {
        return question;
    }

    String getControlHint() {
        return controlHint;
    }

    @Nullable
    Grade getGrade(int grade) {
        if (1 <= grade && grade <= grades.size()) {
            return grades.get(grade - 1);
        }
        return null;
    }

}
