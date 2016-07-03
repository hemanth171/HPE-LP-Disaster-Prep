package com.topcoder.disasterprep.login;

import android.os.Bundle;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.Presenter;

import java.util.Set;

class LoginPresenter extends Presenter<LoginView> {

    LoginPresenter(LoginView view) {
        super(view);
        Bundle bundle = view.getIntentBundle();
        if (null != bundle) {
            Set key = bundle.keySet();
            if (key.contains(IntentExtras.LOGIN_LOGIN) && bundle.getBoolean(IntentExtras.LOGIN_LOGIN)) {
                view.showLogin();
            } else if (key.contains(IntentExtras.LOGIN_SIGNUP) && bundle.getBoolean(IntentExtras.LOGIN_SIGNUP)) {
                view.showSignup();
            } else {
                view.showLandingPage();
            }
        } else {
            view.showLandingPage();
        }
    }
}
