package com.topcoder.disasterprep.module.bc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleActivity;

public class BCIntroFragment extends android.app.Fragment implements View.OnClickListener {

    public static final String INTRO_LEVEL = "intro_level";
    private int level;

    public static BCIntroFragment newInstance(int level) {
        Bundle args = new Bundle();
        args.putInt(INTRO_LEVEL, level);

        BCIntroFragment fragment = new BCIntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (null != args) {
            level = args.getInt(INTRO_LEVEL, 1);
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
                layout = R.layout.fragment_bc_intro_1;
                break;
            case 2:
                layout = R.layout.fragment_bc_intro_2;
                break;
        }
        return inflater.inflate(layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.start).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ModuleActivity) getActivity()).showHelpMenu(false);
    }

    @Override
    public void onClick(View view) {
        ((ModuleActivity) getActivity()).showModule(level);
    }
}
