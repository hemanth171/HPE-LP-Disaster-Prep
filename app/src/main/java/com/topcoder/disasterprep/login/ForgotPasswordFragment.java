/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.login;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.topcoder.disasterprep.R;

/**
 * The fragment for the forgot password screen.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class ForgotPasswordFragment extends Fragment implements View.OnClickListener {

    /**
     * The username input
     */
    private TextView mUsername;

    /**
     * The username input container
     */
    private View mFields;

    /**
     * The error view
     */
    private View mError;

    /**
     * Inflate the fragment when creating the view.
     *
     * @param inflater the inflater
     * @param container the container
     * @param savedInstanceState the saved instance state
     * @return the forgot password screen view
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forgot_pwd, container, false);
    }

    /**
     * Call after the view is created
     *
     * @param view the created view
     * @param savedInstanceState the saved instance state
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    /**
     * Initialize the view
     *
     * @param view the view
     */
    private void initViews(View view) {
        view.findViewById(R.id.login).setOnClickListener(this);
        view.findViewById(R.id.send_email).setOnClickListener(this);
        mUsername = (TextView) view.findViewById(R.id.username);
        mFields = view.findViewById(R.id.fields_container);
        mError = view.findViewById(R.id.error_text);
        mUsername.setOnClickListener(this);
    }

    /**
     * Handle the click events
     *
     * @param v the view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.username:
                showError(false);
                break;
            case R.id.send_email:
                if (TextUtils.isEmpty(mUsername.getText().toString())) {
                    showError(true);
                } else {
                    Toast.makeText(getActivity(), "Please check your email!", Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            ((LoginView) getActivity()).showLogin();
                        }
                    }, 2000);

                }
                break;
            case R.id.login:
                ((LoginView) getActivity()).showLogin();
                break;
        }
    }

    /**
     * Show error or not
     *
     * @param isShow the flag to indicate the error view should be visible or not
     */
    public void showError(boolean isShow) {
        if (isShow) {
            mFields.setBackgroundResource(R.drawable.rect_error);
            mError.setVisibility(View.VISIBLE);
            mUsername.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login_user_ico, 0, R.drawable.icn_error, 0);
        } else {
            mFields.setBackgroundResource(android.R.color.transparent);
            mError.setVisibility(View.INVISIBLE);
            mUsername.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login_user_ico, 0, 0, 0);
        }
    }
}
