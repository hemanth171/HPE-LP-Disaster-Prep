package com.topcoder.disasterprep.assessment_certificate;

import android.content.Context;
import android.os.Bundle;

public interface AssessmentInfoView {
    void showPages(int[] layouts, String[] titles, String businessName);

    Context getViewContext();

    Bundle getExtras();

    void showPage(int page);
}
