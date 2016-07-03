package com.topcoder.disasterprep.assessment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topcoder.disasterprep.R;

public class IntroFragment extends Fragment implements View.OnClickListener {
    public static final String INTRO_LEVEL = "intro_level";

    private int level;

    public static IntroFragment newInstance(int level) {
        Bundle args = new Bundle();
        args.putInt(INTRO_LEVEL, level);
        IntroFragment fragment = new IntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (null != args) {
            level = args.getInt(INTRO_LEVEL, 1);
        } else {
            level = 1;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout;
        switch (level) {
            case 1:
            default:
                layout = R.layout.fragment_assessment_intro_1;
                break;
            case 2:
                layout = R.layout.fragment_assessment_intro_2;
                break;
        }
        return inflater.inflate(layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.start).setOnClickListener(this);
        view.findViewById(R.id.bcp).setOnClickListener(this);
        view.findViewById(R.id.answers).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AssessmentView) getActivity()).setViewTitle("Assessment Lvl " + level);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                ((AssessmentView) getActivity()).showQuestions(level);
                break;
            case R.id.bcp:
                ((AssessmentView) getActivity()).showAssessmentBcp();
                break;
            case R.id.answers:
                ((AssessmentView) getActivity()).showAssessmentAnswers();
                break;
        }
    }
}
