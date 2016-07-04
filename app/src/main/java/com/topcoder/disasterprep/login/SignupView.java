package com.topcoder.disasterprep.login;

import android.content.Context;

public interface SignupView {
    void showFirstTime();

    void showAcceptedError();

    void showFillError(String error);

    Context getViewContext();
}
