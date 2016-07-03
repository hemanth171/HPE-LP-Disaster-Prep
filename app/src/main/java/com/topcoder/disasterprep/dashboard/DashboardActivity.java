package com.topcoder.disasterprep.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleActivity;
import com.topcoder.disasterprep.profile.ProfileActivity;

import java.util.List;

public class DashboardActivity extends AppCompatActivity implements DashboardView, View.OnClickListener {

    private ViewGroup mScores;
    private ImageView mAvatar;
    private RecyclerView mRecyclerView;
    private ModuleAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initViews();
        new DashboardPresenter(this);
    }

    private void initViews() {
        mScores = (ViewGroup) findViewById(R.id.dashboard_score_levels);
        mAvatar = (ImageView) findViewById(R.id.avatar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.share).setOnClickListener(this);
        findViewById(R.id.notification).setOnClickListener(this);
        mAvatar.setOnClickListener(this);
    }

    @Override
    public void showScores(List<Score> scores) {
        for (Score score : scores) {
            ScoreLevelView levelView = new ScoreLevelView(this);
            levelView.setTitle(score.title);
            levelView.setScore(score.score);
            levelView.setColor(score.color);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            levelView.setLayoutParams(params);
            mScores.addView(levelView);
        }
    }

    @Override
    public void showAvatar(@DrawableRes int avatar_small) {
        mAvatar.setImageResource(avatar_small);
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void showModules(List<ModuleView.Module> modules) {
        mAdapter = new ModuleAdapter(modules, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showPlan(ModuleView.Module module) {
        mAdapter.addPlan(module);
    }

    @Override
    public void openBCModule() {
        Intent start = new Intent(this, ModuleActivity.class);
        start.putExtra(IntentExtras.MODULE_BC, true);
        start.putExtra(IntentExtras.MODULE_INTRO, true);
        startActivity(start);
    }

    @Override
    public void showNotImplemented() {
        Toast.makeText(this, "This feature will be implemented in future", Toast.LENGTH_LONG).show();
    }

    private void openProfile() {
        Intent start = new Intent(this, ProfileActivity.class);
        startActivity(start);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share:
            case R.id.notification:
                showNotImplemented();
                break;
            case R.id.avatar:
                openProfile();
                break;
        }
    }

    static class Score {
        String title;
        String score;
        @ColorRes
        int color;

        Score(String title, String score, @ColorRes int color) {
            this.title = title;
            this.score = score;
            this.color = color;
        }
    }
}
