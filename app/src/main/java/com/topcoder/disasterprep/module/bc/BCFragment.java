package com.topcoder.disasterprep.module.bc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topcoder.disasterprep.NavigationView;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleView;

public class BCFragment extends android.app.Fragment implements BCView {

    public static final String BC_LEVEL = "bc_level";
    private LockableViewPager mPager;
    private NavigationView mNavigation;
    private BCPresenter mPresenter;
    private int level;
    private BCAdapter mAdapter;

    public static BCFragment newInstance(int level) {
        Bundle args = new Bundle();
        args.putInt(BC_LEVEL, level);

        BCFragment fragment = new BCFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (null != args) {
            level = args.getInt(BC_LEVEL, 1);
        } else {
            level = 1;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bc, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        mPresenter = new BCPresenter(this, level);
    }

    private void initViews(View view) {
        mPager = (LockableViewPager) view.findViewById(R.id.pager);
        mNavigation = (NavigationView) view.findViewById(R.id.navigation);
    }

    @Override
    public void setSteps(int[] steps, int[] ids, int page) {
        mAdapter = new BCAdapter(this.level, steps);
        View.OnClickListener OrOptionListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onOrOptionClicked(view.getId());
            }
        };
        mAdapter.setListener(page, OrOptionListener, ids);
        mPager.setAdapter(mAdapter);
        mNavigation.showProgress(true);
        mNavigation.setPager(mPager,
                new NavigationView.ResultListener() {
                    @Override
                    public void showResult() {
                        ((ModuleView) getActivity()).showModuleResult(BCFragment.this.level);
                        mPresenter.onShowResult(BCFragment.this.level, mPager.getAdapter().getCount() - 1);
                    }

                    @Override
                    public void showSkip() {
                        ((ModuleView) getActivity()).showSkip();
                    }
                },
                new NavigationView.StepListener() {
                    @Override
                    public void setStep(int step) {
                        mPresenter.onPageSelected(BCFragment.this.level, step - 1);
                    }
                });
    }

    @Override
    public void lockView(boolean isLock) {
        mNavigation.lock(isLock);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ModuleView) getActivity()).showHelpMenu(true);
        ((ModuleView) getActivity()).setTitle("Business Continuity Planning");
    }
}
