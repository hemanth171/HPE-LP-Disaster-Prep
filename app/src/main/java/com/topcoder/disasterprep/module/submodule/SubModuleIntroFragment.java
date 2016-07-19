/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.submodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleActivity;

/**
 * The fragment for the introduction screen of the module level.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class SubModuleIntroFragment extends android.app.Fragment implements View.OnClickListener {
    /**
     * The intro level bundle argument
     */
    public static final String INTRO_LEVEL = "intro_level";

    /**
     * The fragments bundle argument
     */
    public static final String INTRO_FRAGMENTS = "finish_fragments";

    /**
     * The current level
     */
    private int level;

    /**
     * The fragment resource ids
     */
    private int[] fragments;

    /**
     * Create a new instance of fragment
     *
     * @param level the current level
     * @param fragments the fragment resource ids
     * @return the fragment instance
     */
    public static SubModuleIntroFragment newInstance(int level, int[] fragments) {
        Bundle args = new Bundle();
        args.putInt(INTRO_LEVEL, level);
        args.putIntArray(INTRO_FRAGMENTS, fragments);

        SubModuleIntroFragment fragment = new SubModuleIntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Load bundle arguments
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        level = args.getInt(INTRO_LEVEL, 1);
        fragments = args.getIntArray(INTRO_FRAGMENTS);
    }

    /**
     * Inflate the view
     *
     * @param inflater the inflator
     * @param container the view container
     * @param savedInstanceState the saved instance state
     * @return the created view
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout = fragments[level - 1];

        return inflater.inflate(layout, container, false);
    }

    /**
     * Initialize the view
     *
     * @param view the view
     * @param savedInstanceState the saved instance state
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.start).setOnClickListener(this);
    }

    /**
     * Hide help menu
     */
    @Override
    public void onResume() {
        super.onResume();
        ((ModuleActivity) getActivity()).showHelpMenu(false);
    }

    /**
     * Handle the start button click
     *
     * @param view the view clicked
     */
    @Override
    public void onClick(View view) {
        ((ModuleActivity) getActivity()).showModule(level);
    }
}
