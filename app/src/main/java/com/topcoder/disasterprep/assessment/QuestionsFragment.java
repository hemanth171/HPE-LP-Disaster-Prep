package com.topcoder.disasterprep.assessment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topcoder.disasterprep.R;

public class QuestionsFragment extends Fragment implements View.OnClickListener {
    public static final String INTRO_LEVEL = "intro_level";
    private int level;

    private GradeSeekView mSeek1;
    private GradeSeekView mSeek2;
    private GradeSeekView mSeek3;

    public static QuestionsFragment newInstance(int level) {
        Bundle args = new Bundle();
        args.putInt(INTRO_LEVEL, level);
        QuestionsFragment fragment = new QuestionsFragment();
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
                layout = R.layout.fragment_assessment_questions_1;
                break;
            case 2:
                layout = R.layout.fragment_assessment_questions_2;
                break;
        }
        return inflater.inflate(layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.result).setOnClickListener(this);
        mSeek1 = (GradeSeekView) view.findViewById(R.id.seek1);
        mSeek2 = (GradeSeekView) view.findViewById(R.id.seek2);
        mSeek3 = (GradeSeekView) view.findViewById(R.id.seek3);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AssessmentView) getActivity()).setViewTitle("Assessment Lvl " + level + " Questions");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.result:
                int score = mSeek1.getProgress().first + mSeek2.getProgress().first + mSeek3.getProgress().first;
                int max = mSeek1.getProgress().second + mSeek2.getProgress().second + mSeek3.getProgress().second;
                int color = score < max / 2 ? R.color.orange1 : R.color.green1;
                ((AssessmentView) getActivity()).showResult(level, score, max, color);
                break;
        }
    }
}
