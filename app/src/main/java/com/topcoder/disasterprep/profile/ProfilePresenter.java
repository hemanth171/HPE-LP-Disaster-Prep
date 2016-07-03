package com.topcoder.disasterprep.profile;

import com.topcoder.disasterprep.Presenter;
import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.SharedUtils;

class ProfilePresenter extends Presenter<ProfileView> {

    public ProfilePresenter(ProfileView view) {
        super(view);
        view.showUserInfo(R.drawable.avatar_big, SharedUtils.getFullName(view.getViewContext()), SharedUtils.getEmail(view.getViewContext()));
    }
}
