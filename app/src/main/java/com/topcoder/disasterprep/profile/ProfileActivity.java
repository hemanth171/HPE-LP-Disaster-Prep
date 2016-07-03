package com.topcoder.disasterprep.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.SimplePagerAdapter;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, ProfileView {

    private ImageView mAvatar;
    private TextView mName;
    private TextView mEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
        new ProfilePresenter(this);
    }

    private void initViews() {
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.notification).setOnClickListener(this);
        mAvatar = (ImageView) findViewById(R.id.avatar);
        mName = (TextView) findViewById(R.id.name);
        mEmail = (TextView) findViewById(R.id.email);
        SimplePagerAdapter adapter = new ProfileAdapter(
                new int[]{R.layout.view_profile_business, R.layout.view_profile_contacts, R.layout.view_profile_privacy},
                new String[]{"Business", "Contacts", "Privacy"});
        ((ViewPager) findViewById(R.id.pager)).setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.notification:
                Toast.makeText(this, "This feature will be implemented in future", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showUserInfo(@DrawableRes int avatar_big, String name, String email) {
        mAvatar.setImageResource(avatar_big);
        mName.setText(name);
        mEmail.setText(email);
    }

    @Override
    public Context getViewContext() {
        return this;
    }
}
