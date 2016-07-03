package com.topcoder.disasterprep.assessment_intro;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.topcoder.disasterprep.R;

public class ResultDialog extends DialogFragment implements View.OnClickListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.dialog_login_assesment_result, null);
        root.findViewById(R.id.sign_up).setOnClickListener(this);
        root.findViewById(R.id.login).setOnClickListener(this);
        return new AlertDialog.Builder(getActivity()).setView(root).create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                ((AssessmentView) getActivity()).login();
                dismiss();
                break;
            case R.id.sign_up:
                ((AssessmentView) getActivity()).signUp();
                dismiss();
                break;
        }
    }
}
