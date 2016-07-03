package com.topcoder.disasterprep.profile;

import android.content.Context;
import android.support.annotation.DrawableRes;

interface ProfileView {
    void showUserInfo(@DrawableRes int avatar_big, String name, String email);

    Context getViewContext();
}
