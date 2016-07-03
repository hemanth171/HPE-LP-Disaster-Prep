package com.topcoder.disasterprep.login;

import android.content.Context;
import android.graphics.drawable.Drawable;

public interface ScoreView {

    void showLowScore(int score, int maxScore, Drawable scoreColor, int color);

    Context getViewContext();
}
