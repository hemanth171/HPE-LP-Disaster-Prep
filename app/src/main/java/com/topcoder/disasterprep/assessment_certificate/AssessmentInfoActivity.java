package com.topcoder.disasterprep.assessment_certificate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.SimplePagerAdapter;

public class AssessmentInfoActivity extends AppCompatActivity implements View.OnClickListener, AssessmentInfoView {

    private ViewPager mPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_assessment);
        initViews();
        Presenter p = new AssessmentInfoPresenter(this);
    }

    private void initViews() {
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.share).setOnClickListener(this);
        findViewById(R.id.load).setOnClickListener(this);
        mPager = (ViewPager) findViewById(R.id.pager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.share:
            case R.id.load:
                Toast.makeText(this, "This feature will be implemented in future", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void showPages(int[] layouts, String[] titles, String businessName) {
        SimplePagerAdapter adapter = new AssessmentInfoAdapter(layouts, titles, businessName);
        mPager.setAdapter(adapter);

    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public Bundle getExtras() {
        return getIntent().getExtras();
    }

    @Override
    public void showPage(int page) {
        mPager.setCurrentItem(page);
    }
}
