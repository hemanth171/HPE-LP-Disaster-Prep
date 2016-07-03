package com.topcoder.disasterprep.login;

import android.text.TextUtils;

import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.SharedUtils;

public class SignupPresenter extends Presenter<SignupView> {

    public SignupPresenter(SignupView view) {
        super(view);
    }

    void onSignUp(String firstName, String lastName, String businessName, String email, String password, boolean accepted) {
        if (!valid(firstName, lastName, businessName, email, password)) {
            view.showEmptyError();
        } else if (!accepted) {
            view.showAcceptedError();
        } else {
            SharedUtils.setUserInfo(view.getViewContext(), firstName, lastName, businessName, email, password);
            view.showFirstTime();
        }
    }

    private boolean valid(String firstName, String lastName, String businessName, String email, String password) {
        return !TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) &&
                !TextUtils.isEmpty(businessName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password);
    }
}
