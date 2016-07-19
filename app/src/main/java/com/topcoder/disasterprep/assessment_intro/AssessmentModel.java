/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.assessment_intro;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.topcoder.disasterprep.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The model for the landing assessment screens before login.
 */
public class AssessmentModel {

    public static List<Grade> getGrades(Context context) {
        return new ArrayList<>(Arrays.asList(
                new Grade("I don't know", getDrawable(context, R.drawable.grade_ico_1), "", getDrawable(context, R.drawable.circle_grey)),
                new Grade("No", getDrawable(context, R.drawable.grade_ico_2), "They know very little about the organization", getDrawable(context, R.drawable.circle_red)),
                new Grade("Maybe", getDrawable(context, R.drawable.grade_ico_3), "", getDrawable(context, R.drawable.circle_yellow)),
                new Grade("I think so", getDrawable(context, R.drawable.grade_ico_4), "", getDrawable(context, R.drawable.circle_green)),
                new Grade("Absolutely", getDrawable(context, R.drawable.grade_ico_5), "We have successfully done this before", getDrawable(context, R.drawable.circle_blue4))));
    }

    private static Drawable getDrawable(Context context, int grade_1) {
        return ContextCompat.getDrawable(context, grade_1);
    }


    static List<AssessmentQuestion> getQuestions(Context context) {
        List<AssessmentQuestion> questions = new ArrayList<>(5);
        List<Grade> grades = getGrades(context);
        String controlHint = "Slide to left or right to choose your answer";
        AssessmentQuestion q1 = new AssessmentQuestion(1, "1. Business Continuity Planning", "How confident are you " +
                "that you could re-establish your core operations with minimal disruption to keycustomers?",
                controlHint, grades);
        AssessmentQuestion q2 = new AssessmentQuestion(2, "2. Leadership and Culture", "Would there be good leadership from within your " +
                "organisation to get you throught this?", controlHint, grades);
        AssessmentQuestion q3 = new AssessmentQuestion(3, "3. Networks and Relationships", "Could you call on others to help?",
                controlHint, grades);
        AssessmentQuestion q4 = new AssessmentQuestion(4, "4. Change Ready", "Do your staff understand what would " +
                "need to be done to get through?", controlHint, grades);
        AssessmentQuestion q5 = new AssessmentQuestion(5, "5. Employee Resilience", "How do you think your staff will " +
                "individually cope with the stressful situation?", controlHint, grades);
        questions.add(q1.getNumber() - 1, q1);
        questions.add(q2.getNumber() - 1, q2);
        questions.add(q3.getNumber() - 1, q3);
        questions.add(q4.getNumber() - 1, q4);
        questions.add(q5.getNumber() - 1, q5);
        return questions;
    }

}
