/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.submodule;

/**
 * The view interface for the sub module fragment
 *
 * @author TCSCODER
 * @version 1.0
 */
public interface SubModuleView {
    /**
     * Set the steps fragment resource ids
     *
     * @param steps the steps fragment resource ids
     * @param ids the ids
     * @param page the page
     */
    void setSteps(int[] steps, int[] ids, int page);

    /**
     * Lock the view or not
     *
     * @param isLock flag to indicate the navigation should be locked or not
     */
    void lockView(boolean isLock);
}
