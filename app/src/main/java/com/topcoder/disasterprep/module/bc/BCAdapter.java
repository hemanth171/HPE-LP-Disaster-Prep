package com.topcoder.disasterprep.module.bc;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.SimplePagerAdapter;
import com.topcoder.disasterprep.module.ModuleModel;

class BCAdapter extends SimplePagerAdapter {
    private int level;

    public BCAdapter(int level, int[] steps) {
        super(steps);
        this.level = level;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View page = (View) super.instantiateItem(container, position);
        TextView title = (TextView) page.findViewById(R.id.page_title);
        title.setText(ModuleModel.getInstance().getBCPageTitle(level, position + 1));
        return page;
    }
}
