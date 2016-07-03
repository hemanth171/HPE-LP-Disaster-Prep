package com.topcoder.disasterprep.login;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topcoder.disasterprep.CircleScoreView;
import com.topcoder.disasterprep.R;

public class ScoreFragment extends android.app.Fragment implements View.OnClickListener, ScoreView {

    private CircleScoreView mScoreView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        new ScorePresenter(this);
    }

    private void initViews(View view) {
        view.findViewById(R.id.module).setOnClickListener(this);
        view.findViewById(R.id.dashboard).setOnClickListener(this);
        mScoreView = (CircleScoreView) view.findViewById(R.id.progress_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.module:
                ((LoginView) getActivity()).showBCModule();
                break;
            case R.id.dashboard:
                ((LoginView) getActivity()).showDashboard();
                break;
        }
    }

    @Override
    public void showLowScore(int score, int maxScore, Drawable scoreColor, int color) {
        mScoreView.setScore(score, maxScore, scoreColor, color);
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }
}

