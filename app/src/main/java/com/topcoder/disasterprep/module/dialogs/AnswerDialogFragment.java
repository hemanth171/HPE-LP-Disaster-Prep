/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topcoder.disasterprep.R;

/**
 * The dialog fragment for the answer popup on module page screen.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class AnswerDialogFragment extends DialogFragment implements View.OnClickListener {
    /**
     * The fragment bundle argument name
     */
    private static final String FRAGMENT = "fragment";

    /**
     * The listener for the next button on answer dialog
     */
    public interface NextListener {
        /**
         * Called when the next button on answer dialog is clicked
         */
        void onNext();
    }

    /**
     * Create a new instance of AnswerDialogFragment instance with fragment resource id
     *
     * @param fragment the fragment resource id
     *
     * @return a new instance of AnswerDialogFragment instance
     */
    public static AnswerDialogFragment newInstance(int fragment) {
        AnswerDialogFragment frag = new AnswerDialogFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT, fragment);
        frag.setArguments(args);
        return frag;
    }

    /**
     * Create the dialog
     *
     * @param savedInstanceState the saved instance state
     * @return the created dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        int fragment = getArguments().getInt(FRAGMENT);
        View root = inflater.inflate(fragment, null);
        root.findViewById(R.id.next).setOnClickListener(this);
        return new AlertDialog.Builder(getActivity()).setView(root).create();
    }

    /**
     * Handle the click event
     *
     * @param v the view clicked
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                dismiss();
                ((NextListener) getTargetFragment()).onNext();
                break;
        }
    }
}