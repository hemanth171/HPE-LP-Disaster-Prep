package com.topcoder.disasterprep.assessment_intro;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.topcoder.disasterprep.R;

import java.util.List;

public class QuestionAdapter extends PagerAdapter {
    private List<AssessmentQuestion> questions;

    QuestionAdapter(List<AssessmentQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View question = inflater.inflate(R.layout.view_assessment_question, container, false);
        final AssessmentQuestion curQuestion = questions.get(position);
        ((TextView) question.findViewById(R.id.title)).setText(curQuestion.getTitle());
        ((TextView) question.findViewById(R.id.question)).setText(curQuestion.getQuestion());
        container.addView(question);
        return question;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
