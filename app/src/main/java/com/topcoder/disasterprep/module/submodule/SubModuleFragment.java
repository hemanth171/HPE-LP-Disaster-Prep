/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.submodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.topcoder.disasterprep.NavigationView;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleModel;
import com.topcoder.disasterprep.module.ModuleView;
import com.topcoder.disasterprep.module.dialogs.AnswerDialogFragment;

/**
 * The abstract fragment for the page screen of the module.
 *
 * @author TCSCODER
 * @version 1.0
 */
public abstract class SubModuleFragment extends android.app.Fragment implements SubModuleView,
        SubModuleAdapter.SubModuleControlListener, AnswerDialogFragment.NextListener {
    /**
     * The module level bundle argument name
     */
    public static final String MODULE_LEVEL = "module_level";

    /**
     * The module page bundle argument name
     */
    public static final String MODULE_PAGE = "module_page";

    /**
     * The pager
     */
    private LockableViewPager mPager;

    /**
     * The navigation view
     */
    private NavigationView mNavigation;

    /**
     * The presenter
     */
    private SubModulePresenter mPresenter;

    /**
     * The adapter
     */
    private SubModuleAdapter mAdapter;

    /**
     * The current level
     */
    private int level;

    /**
     * The current page
     */
    private int page;

    /**
     * Set bundle arguments to the given fragment instance
     *
     * @param level the level
     * @param fragment the fragment instance
     * @return the updated fragment
     */
    protected static SubModuleFragment newInstance(int level, SubModuleFragment fragment) {
        Bundle args = new Bundle();
        args.putInt(MODULE_LEVEL, level);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Set bundle arguments to the given fragment instance
     *
     * @param level the level
     * @param fragment the fragment instance
     * @return the updated fragment
     */
    protected static SubModuleFragment newInstance(int level, int page, SubModuleFragment fragment) {
        Bundle args = new Bundle();
        args.putInt(MODULE_LEVEL, level);
        args.putInt(MODULE_PAGE, page);

        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Return the module type
     *
     * @return the module type
     */
    protected abstract int getModuleType();

    /**
     * Return the module title.
     *
     * @return the module title.
     */
    protected abstract String getModuleTitle();

    /**
     * Return the layout fragment resource id
     *
     * @return the layout fragment resource id
     */
    protected abstract int getLayoutFragment();

    /**
     * Return the page (or step) fragments of given level
     *
     * @param level the level
     * @return the page (or step) fragments
     */
    protected abstract int[] getStepFragments(int level);

    /**
     * Return the button background drawable resource id (for skip button)
     *
     * @return  the button background drawable resource id
     */
    protected abstract int getButtonBackground();

    /**
     * Load the bundle arguments
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        level = args.getInt(MODULE_LEVEL, 1);
        page = args.getInt(MODULE_PAGE, 0);

        // clear skip flag
        resetSkip();
    }

    /**
     * Inflate the view
     *
     * @param inflater the inflator
     * @param container the container view
     * @param savedInstanceState the saved instance state
     * @return the created view
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutFragment(), container, false);
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
        initViews(view);
        mPresenter = new SubModulePresenter(this, getModuleType(), page, getStepFragments(level));
    }

    /**
     * Initialize the view
     *
     * @param view the view
     */
    private void initViews(View view) {
        mPager = (LockableViewPager) view.findViewById(R.id.pager);
        mNavigation = (NavigationView) view.findViewById(R.id.navigation);
    }

    /**
     * Set the steps (or pages) to the fragment
     *
     * @param steps the steps (or pages) resource ids
     * @param ids the ids
     * @param page the page to jump to
     */
    @Override
    public void setSteps(int[] steps, int[] ids, int page) {
        mAdapter = new SubModuleAdapter(steps, this.getActivity(), this);

        mPager.setAdapter(mAdapter);
        mNavigation.showProgress(true);
        mNavigation.setPager(mPager,
                new NavigationView.ResultListener() {
                    @Override
                    public void showResult() {
                        ((ModuleView) getActivity()).showModuleResult(SubModuleFragment.this.level);

                        if (mPresenter != null) {
                            mPresenter.onShowResult(SubModuleFragment.this.level, mPager.getAdapter().getCount() - 1);
                        }
                    }

                    @Override
                    public void showSkip() {
                        handleSkip();
                    }
                },
                new NavigationView.StepListener() {
                    @Override
                    public void setStep(int step) {
                        if (mPresenter != null) {
                            mPresenter.onPageSelected(SubModuleFragment.this.level, step - 1);
                        }

                        if (!getModuleInfo().hasSeenStep(level, step)) {
                            getModuleInfo().setMaxSeenStep(level, step);
                            lockView(true);
                        } else {
                            lockView(false);
                        }
                    }
                });

        // move to page
        if (page > 0) {
            mPager.setCurrentItem(page - 1);
        }
    }

    /**
     * Handle child control action
     *
     * @param view the child view
     */
    public void handleChildControl(View view) {
        if (view != null && view.getId() == R.id.skip) {
            handleSkip();
            return;
        }

        // don't skip any more
        mNavigation.lock(false);

        // show dialog
        if (view != null) {
            switch (view.getId()) {
                case R.id.cr_lv2_s2_similar:
                    showAnswerDialog(R.layout.dialog_cr_similar);
                    break;
                case R.id.cr_lv2_s2_different:
                    showAnswerDialog(R.layout.dialog_cr_different);
                    break;
            }
        }
    }

    /**
     * Handle the skip action
     */
    private void handleSkip() {
        ((ModuleView) getActivity()).showSkip(getButtonBackground());
        getModuleInfo().setSkipInfo(this.level, this.mPager.getCurrentItem() + 1);
    }

    /**
     * Reset the skip state
     */
    private void resetSkip() {
        getModuleInfo().setSkipped(false);
    }

    /**
     * Get module info
     *
     * @return the module info
     */
    private ModuleModel.ModuleInfo getModuleInfo() {
        return ModuleModel.getInstance().getModule(this.getModuleType());
    }

    /**
     * Show the answer dialog
     *
     * @param fragment the fragment resource id
     */
    private void showAnswerDialog(int fragment) {
        AnswerDialogFragment dialog = AnswerDialogFragment.newInstance(fragment);
        dialog.setTargetFragment(this, 0);
        dialog.show(getFragmentManager(), "answer_dialog");
    }

    /**
     * Move to next page
     */
    @Override
    public void onNext() {
        mNavigation.moveNext();
    }

    /**
     * Lock the view or not
     *
     * @param isLock flag to indicate the navigation should be locked or not
     */
    @Override
    public void lockView(boolean isLock) {
        mNavigation.lock(isLock);
    }

    /**
     * Show help menu and set activity title
     */
    @Override
    public void onResume() {
        super.onResume();
        ((ModuleView) getActivity()).showHelpMenu(true);
        ((ModuleView) getActivity()).setTitle(getModuleTitle());
    }
}