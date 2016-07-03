package com.topcoder.disasterprep.module.bc;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleView;

public class BCFinishFragment extends Fragment implements View.OnClickListener {

    public static final String RESULT_LEVEL = "result_level";
    private int level;

    public static BCFinishFragment newInstance(int level) {
        Bundle args = new Bundle();
        args.putInt(RESULT_LEVEL, level);

        BCFinishFragment fragment = new BCFinishFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (null != args) {
            level = args.getInt(RESULT_LEVEL, 1);
        } else {
            level = 1;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout;
        switch (level) {
            case 1:
            default:
                layout = R.layout.fragment_bc_finish_1;
                break;
            case 2:
                layout = R.layout.fragment_bc_finish_2;
                break;
        }
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
