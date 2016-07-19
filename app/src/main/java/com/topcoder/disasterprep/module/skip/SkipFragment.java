/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.skip;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleView;

/**
 * The fragment for the skip screen
 *
 * @author TCSCODER
 * @version 1.0
 */
public class SkipFragment extends Fragment implements View.OnClickListener {
    /**
     * The home button background resource id bundle argument name
     */
    private static final String HOME_BUTTON_BG_RES_ID = "home_button_bg_res_id";

    /**
     * The home button background resource id
     */
    private int homeButtonBgResId;

    /**
     * Create a new instance of skip fragment
     *
     * @param homeButtonBgResId the home button background resource id
     * @return the skip fragment instance
     */
    public static SkipFragment newInstance(int homeButtonBgResId) {
        Bundle args = new Bundle();
        args.putInt(HOME_BUTTON_BG_RES_ID, homeButtonBgResId);

        SkipFragment fragment = new SkipFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Load the bundle arguments
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (null != args) {
            homeButtonBgResId = args.getInt(HOME_BUTTON_BG_RES_ID, R.drawable.button_bg1);
        } else {
            homeButtonBgResId = R.drawable.button_bg1;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skip, container, false);
    }

    /**
     * Initialize the child views
     *
     * @param view the view
     * @param savedInstanceState the saved instance state
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button dashboardButton = (Button) view.findViewById(R.id.dashboard);
        dashboardButton.setBackgroundResource(homeButtonBgResId);
        dashboardButton.setOnClickListener(this);

        view.findViewById(R.id.logout).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ModuleView) getActivity()).showHelpMenu(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout:
                ((ModuleView) getActivity()).showLogin();
                break;
            case R.id.dashboard:
                ((ModuleView) getActivity()).showDashboard();
                break;
        }

    }
}
