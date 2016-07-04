package com.topcoder.disasterprep.login;

import android.text.TextUtils;

import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.SharedUtils;

public class SignupPresenter extends Presenter<SignupView> {

    public SignupPresenter(SignupView view) {
        super(view);
    }

    void onSignUp(String firstName, String lastName, String businessName, String email, String password, boolean accepted) {
        String error = valid(firstName, lastName, businessName, email, password);
        if (!TextUtils.isEmpty(error)) {
            view.showFillError(error);
        } else if (!accepted) {
            view.showAcceptedError();
        } else {
            SharedUtils.setUserInfo(view.getViewContext(), firstName, lastName, businessName, email, password);
            view.showFirstTime();
        }
    }

    private String valid(String firstName, String lastName, String businessName, String email, String password) {
        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||
                TextUtils.isEmpty(businessName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            return "Fill all fields";
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Add valid email";
        }
        return null;
    }
}
