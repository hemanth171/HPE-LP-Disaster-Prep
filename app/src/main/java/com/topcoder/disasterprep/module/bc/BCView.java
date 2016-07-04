package com.topcoder.disasterprep.module.bc;

public interface BCView {
    void setSteps(int[] steps, int[] ids, int page);

    void lockView(boolean isLock);
}
