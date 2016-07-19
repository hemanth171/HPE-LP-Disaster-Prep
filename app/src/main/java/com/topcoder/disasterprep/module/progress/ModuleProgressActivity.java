/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.progress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.topcoder.disasterprep.IntentExtras;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleModel;

import java.util.List;

/**
 * The activity for the module progress screen.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class ModuleProgressActivity extends AppCompatActivity implements View.OnClickListener, ModuleProgressView {

    /**
     * Initialize the view for the activity
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        initViews();

        int moduleLevel = getIntentBundle().getInt(IntentExtras.MODULE_LEVEL, 1);
        int moduleType = getIntentBundle().getInt(IntentExtras.MODULE_TYPE, 1);

        new ModulePregressPresenter(this, moduleLevel, moduleType);
    }

    private void initViews() {
        findViewById(R.id.close).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
        }
    }

    @Override
    public Bundle getIntentBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void setPageTitle(String title) {
        ((TextView) findViewById(R.id.title)).setText(title);
    }

    @Override
    public void setProgress(List<ModuleModel.PageInfo> pages) {
        RecyclerView.Adapter adapter = new ModuleProgressAdapter(pages, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
