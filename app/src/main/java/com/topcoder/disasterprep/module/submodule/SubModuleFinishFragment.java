/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.submodule;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleView;

/**
 * The fragment for the module finish screen.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class SubModuleFinishFragment extends Fragment implements View.OnClickListener {

    /**
     * The result level bundle argument name
     */
    public static final String RESULT_LEVEL = "result_level";

    /**
     * The finish fragments bundle argument name
     */
    public static final String FINISH_FRAGMENTS = "finish_fragments";

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
     * @param level the level
     * @param fragments the fragment resource ids
     * @return the fragment instance
     */
    public static SubModuleFinishFragment newInstance(int level, int[] fragments) {
        Bundle args = new Bundle();
        args.putInt(RESULT_LEVEL, level);
        args.putIntArray(FINISH_FRAGMENTS, fragments);

        SubModuleFinishFragment fragment = new SubModuleFinishFragment();
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
        level = args.getInt(RESULT_LEVEL, 1);
        fragments = args.getIntArray(FINISH_FRAGMENTS);
    }

    /**
     * Inflate the fragment layout
     *
     * @param inflater the inflater
     * @param container the view container
     * @param savedInstanceState the saved instance state
     * @return the inflated view
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout = fragments[level - 1];
        return inflater.inflate(layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.next).setOnClickListener(this);
        view.findViewById(R.id.dashboard).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                ((ModuleView) getActivity()).showModuleIntro(level + 1);
                break;
            case R.id.dashboard:
                ((ModuleView) getActivity()).showDashboard();
                break;
        }
    }
}