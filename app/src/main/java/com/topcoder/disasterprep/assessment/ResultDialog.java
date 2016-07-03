package com.topcoder.disasterprep.assessment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.CircleScoreView;

public class ResultDialog extends DialogFragment implements View.OnClickListener {
    public static final String LEVEL = "level";
    private static final String SCORE = "score";
    private static final String MAX = "max";
    private static final String COLOR = "color";

    private int level;
    private int colorRes;
    private int score;
    private int maxScore;

    public static ResultDialog newInstance(int level, int score, int maxScore, @ColorRes int scoreColorRes) {
        ResultDialog f = new ResultDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt(SCORE, score);
        args.putInt(MAX, maxScore);
        args.putInt(COLOR, scoreColorRes);
        args.putInt(LEVEL, level);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        colorRes = getArguments().getInt(COLOR);
        score = getArguments().getInt(SCORE);
        maxScore = getArguments().getInt(MAX);
        level = getArguments().getInt(LEVEL);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AssessmentView) getActivity()).setViewTitle("Assessment Level " + String.valueOf(level) + " Results");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int layout;
        switch (level) {
            case 1:
            default:
                layout = R.layout.dialog_assessment_result_1;
                break;
            case 2:
                layout = R.layout.dialog_assessment_result_2;
                break;
        }
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View root = inflater.inflate(layout, null);

        root.findViewById(R.id.next).setOnClickListener(this);
        root.findViewById(R.id.dashboard).setOnClickListener(this);
        CircleScoreView scoreView = (CircleScoreView) root.findViewById(R.id.progress_view);

        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.ring_orange1);
        int color = ContextCompat.getColor(getActivity(), colorRes);
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        scoreView.setScore(score, maxScore, drawable, color);
        return new AlertDialog.Builder(getActivity()).setView(root).create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                dismiss();
                ((AssessmentView) getActivity()).showIntro(level + 1);
                break;
            case R.id.dashboard:
                dismiss();
                ((AssessmentView) getActivity()).showDashboard();
                break;
        }
    }
}
