package com.topcoder.disasterprep.module.skip;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleView;

public class SkipFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skip, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.dashboard).setOnClickListener(this);
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
