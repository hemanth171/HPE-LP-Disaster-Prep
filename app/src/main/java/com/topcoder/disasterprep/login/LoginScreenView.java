package com.topcoder.disasterprep.login;

import android.content.Context;

public interface LoginScreenView {
    void showError(boolean isShow);

    void showFirstTime();

    Context getViewContext();
}
