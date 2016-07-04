package com.topcoder.disasterprep.login;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.topcoder.disasterprep.R;

public class SignupFragment extends Fragment implements View.OnClickListener, SignupView {

    private SignupPresenter mPresenter;
    private TextView mFirstName;
    private TextView mLastName;
    private TextView mBusinessName;
    private TextView mEmail;
    private TextView mPassword;
    private CompoundButton mAccepted;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        mPresenter = new SignupPresenter(this);
    }

    private void initViews(View view) {
        view.findViewById(R.id.next_step).setOnClickListener(this);
        view.findViewById(R.id.login).setOnClickListener(this);
        mFirstName = (TextView) view.findViewById(R.id.first_name);
        mLastName = (TextView) view.findViewById(R.id.last_name);
        mBusinessName = (TextView) view.findViewById(R.id.business_name);
        mEmail = (TextView) view.findViewById(R.id.email);
        mPassword = (TextView) view.findViewById(R.id.password);
        mAccepted = (CompoundButton) view.findViewById(R.id.accept_terms);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_step:
                mPresenter.onSignUp(mFirstName.getText().toString(), mLastName.getText().toString(),
                        mBusinessName.getText().toString(), mEmail.getText().toString(), mPassword.getText().toString(), mAccepted.isChecked());
                break;
            case R.id.login:
                ((LoginView) getActivity()).showLogin();
                break;
        }
    }

    @Override
    public void showFirstTime() {
        ((LoginView) getActivity()).showFirstTime();
    }

    @Override
    public void showAcceptedError() {
        Toast.makeText(getActivity(), "You must accept terms & conditions", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFillError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }
}
