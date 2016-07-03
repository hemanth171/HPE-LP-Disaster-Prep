package com.topcoder.disasterprep.login;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.R;

import java.util.Random;

public class ScorePresenter extends Presenter<ScoreView> {

    public ScorePresenter(ScoreView view) {
        super(view);
        int max = 12;
        int score = new Random().nextInt(max / 2) + 1;
        Drawable progressColor = ContextCompat.getDrawable(view.getViewContext(), R.drawable.ring_orange1);
        int color = ContextCompat.getColor(view.getViewContext(), R.color.orange1);
        view.showLowScore(score, max, progressColor, color);
    }
}
