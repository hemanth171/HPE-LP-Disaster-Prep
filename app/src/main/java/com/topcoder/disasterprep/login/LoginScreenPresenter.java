package com.topcoder.disasterprep.login;

import android.text.TextUtils;

import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.SharedUtils;

public class LoginScreenPresenter extends Presenter<LoginScreenView> {

    public LoginScreenPresenter(LoginScreenView view) {
        super(view);
    }

    void onLogin(String username, String password) {
        view.showError(false);
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || !isValid(username, password)) {
            view.showError(true);
        } else {
            view.showFirstTime();
        }
    }

    private boolean isValid(String username, String password) {
        return SharedUtils.getUsername(view.getViewContext()).equals(username) &&
                SharedUtils.getPassword(view.getViewContext()).equals(password);
    }
}
