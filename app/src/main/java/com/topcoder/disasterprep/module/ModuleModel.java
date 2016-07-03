package com.topcoder.disasterprep.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleModel {
    LevelInfo BC_1;
    LevelInfo BC_2;
    static ModuleModel instance;

    public static synchronized ModuleModel getInstance() {
        if (null == instance) {
            instance = new ModuleModel();
        }
        return instance;
    }

    private ModuleModel() {
        if (null == BC_1) {
            BC_1 = new LevelInfo("Business Continuity Planning",
                    new ArrayList<>(Arrays.asList(
                            new PageInfo("Staff Contact", 8, 0),
                            new PageInfo("Staff Contact Further Guidance", 8, 0),
                            new PageInfo("Staff Contact Details", 8, 0),
                            new PageInfo("Other Contacts", 8, 0),
                            new PageInfo("Other Contacts Further Guidance", 8, 0),
                            new PageInfo("Other Contact Details", 8, 0),
                            new PageInfo("Data Accessibility", 8, 0),
                            new PageInfo("Data Accessibility Further Guidance", 8, 0),
                            new PageInfo("Data Retrieval Detail", 8, 0),
                            new PageInfo("Essentials of operation", 8, 0),
                            new PageInfo("Reality Check", 8, 0)
                    )));
        }
        if (null == BC_2) {
            BC_2 = new LevelInfo("Business Continuity Planning",
                    new ArrayList<>(Arrays.asList(
                            new PageInfo("Getting Operational", 8, 0),
                            new PageInfo("Relocation", 8, 0),
                            new PageInfo("Staff Contact", 8, 0),
                            new PageInfo("Other Contacts", 8, 0),
                            new PageInfo("Other Contacts Further Guidance", 8, 0),
                            new PageInfo("Staff Contacts Details", 8, 0),
                            new PageInfo("Demand Impacts", 8, 0)
                    )));
        }
    }

    /**
     * @param level 1-based level
     * @param page  1-base page
     * @return title
     */
    public String getBCPageTitle(int level, int page) {
        switch (level) {
            case 1:
            default:
                return BC_1.pages.get(page - 1).title;
            case 2:
                return BC_2.pages.get(page - 1).title;
        }
    }

    /**
     * @param level    1-based
     * @param page     1-based
     * @param progress from 0 to max.
     */
    public void setBCPageProgress(int level, int page, int progress) {
        switch (level) {
            case 1:
            default:
                BC_1.pages.get(page - 1).progress = progress;
                break;
            case 2:
                BC_2.pages.get(page - 1).progress = progress;
                break;

        }
    }

    /**
     * @param level 1-based
     */
    public LevelInfo getBCLevelInfo(int level) {
        switch (level) {
            case 1:
            default:
                return BC_1;
            case 2:
                return BC_2;
        }
    }

    public static class LevelInfo {
        public String title;
        public List<PageInfo> pages;

        LevelInfo(String title, List<PageInfo> pages) {
            this.title = title;
            this.pages = pages;
        }

        public int getMaxSum() {
            int sum = 0;
            for (PageInfo page : pages) {
                sum += page.max;
            }
            return sum;
        }

        public int getProgressSum() {
            int sum = 0;
            for (PageInfo page : pages) {
                sum += page.progress;
            }
            return sum;
        }
    }

    public static class PageInfo {
        public String title;
        public int max;
        public int progress;

        PageInfo(String title, int max, int progress) {
            this.title = title;
            this.max = max;
            this.progress = progress;
        }
    }
}
