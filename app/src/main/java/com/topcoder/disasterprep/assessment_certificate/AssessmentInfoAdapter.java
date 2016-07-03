package com.topcoder.disasterprep.assessment_certificate;

import android.view.ViewGroup;
import android.widget.TextView;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.SimplePagerAdapter;

public class AssessmentInfoAdapter extends SimplePagerAdapter {
    private String businessName;

    public AssessmentInfoAdapter(int[] layouts, String[] titles, String businessName) {
        super(layouts, titles);
        this.businessName = businessName;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup page = (ViewGroup) super.instantiateItem(container, position);
        if (0 == position) {
            ((TextView) page.findViewById(R.id.business_name)).setText(businessName);
        }
        return page;
    }
}
