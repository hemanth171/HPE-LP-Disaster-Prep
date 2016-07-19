/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.login;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.topcoder.disasterprep.R;

/**
 * The fragment for the login screen
 *
 * @author TCSCODER
 * @version 1.0
 */
public class LoginScreenFragment extends Fragment implements View.OnClickListener, LoginScreenView {

    private TextView mUsername;
    private TextView mPassword;
    private LoginScreenPresenter mPresenter;
    private View mFields;
    private View mError;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        mPresenter = new LoginScreenPresenter(this);
    }

    /**
     * Initialize the view
     *
     * @param view the view to initialize
     */
    private void initViews(View view) {
        view.findViewById(R.id.next_step).setOnClickListener(this);
        view.findViewById(R.id.sign_up).setOnClickListener(this);
        view.findViewById(R.id.forgot_password).setOnClickListener(this);
        mUsername = (TextView) view.findViewById(R.id.username);
        mPassword = (TextView) view.findViewById(R.id.password);
        mFields = view.findViewById(R.id.fields_container);
        mError = view.findViewById(R.id.error_text);
        mUsername.setOnClickListener(this);
        mPassword.setOnClickListener(this);
    }

    /**
     * Handle the click events
     *
     * @param v the view clicked
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_step:
                mPresenter.onLogin(mUsername.getText().toString(), mPassword.getText().toString());
                break;
            case R.id.forgot_password:
                ((LoginView) getActivity()).showForgotPassword();
                break;
            case R.id.sign_up:
                ((LoginView) getActivity()).showSignup();
                break;
            case R.id.username:
            case R.id.password:
                showError(false);
                break;
        }
    }

    @Override
    public void showError(boolean isShow) {
        if (isShow) {
            mFields.setBackgroundResource(R.drawable.rect_error);
            mError.setVisibility(View.VISIBLE);
            mUsername.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login_user_ico, 0, R.drawable.icn_error, 0);
            mPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login_pass_ico, 0, R.drawable.icn_error, 0);
        } else {
            mFields.setBackgroundResource(android.R.color.transparent);
            mError.setVisibility(View.INVISIBLE);
            mUsername.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login_user_ico, 0, 0, 0);
            mPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login_pass_ico, 0, 0, 0);
        }
    }

    @Override
    public void showFirstTime() {
        ((LoginView) getActivity()).showFirstTime();
    }

    @Override
    public Context getViewContext() {
        return this.getActivity();
    }
}
