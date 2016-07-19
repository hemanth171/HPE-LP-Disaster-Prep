/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module;

import com.topcoder.disasterprep.dashboard.DashboardModel;

import java.util.ArrayList;
import java.util.List;

/**
 * The model data for the modules
 *
 * @author TCSCODER
 * @version 1.0
 */
public class ModuleModel {
    /**
     * The business continuity plan module data
     */
    public ModuleInfo BCModule;

    /**
     * The leadership and culture module data
     */
    public ModuleInfo LCModule;

    /**
     * The networks and relationships module data
     */
    public ModuleInfo NRModule;

    /**
     * The change ready module data
     */
    public ModuleInfo CRModule;

    /**
     * The employee resilience module data
     */
    public ModuleInfo ERModule;

    static ModuleModel instance;

    public static synchronized ModuleModel getInstance() {
        if (null == instance) {
            instance = new ModuleModel();
        }
        return instance;
    }

    /**
     * Get the module data of given module type
     *
     * @param moduleType  module type
     * @return the module data
     */
    public ModuleInfo getModule(int moduleType) {
        switch (moduleType) {
            case DashboardModel.BC_MODULE_TYPE:
                return BCModule;
            case DashboardModel.CR_MODULE_TYPE:
                return CRModule;
            case DashboardModel.ER_MODULE_TYPE:
                return ERModule;
            case DashboardModel.LC_MODULE_TYPE:
                return LCModule;
            case DashboardModel.NR_MODULE_TYPE:
                return NRModule;
        }
        return null;
    }

    /**
     * Initialize the module data
     */
    private ModuleModel() {
        if (BCModule == null) {
            String level1Title = "Business Continuity Planning";
            String[] level1PageTitles = {
                    "Staff Contact", "Staff Contact Further Guidance", "Staff Contact Details", "Other Contacts",
                    "Other Contacts Further Guidance", "Other Contact Details", "Data Accessibility", "Data Accessibility Further Guidance",
                    "Data Retrieval Detail", "Essentials of operation", "Reality Check"

            };
            String level2Title = "Business Continuity Planning";
            String[] level2PageTitles = {
                    "Getting Operational", "Relocation", "Staff Contact", "Other Contacts",
                    "Other Contacts Further Guidance", "Staff Contacts Details", "Demand Impacts"
            };
            BCModule = createModuleInfo(level1Title, level1PageTitles, level2Title, level2PageTitles);
        }

        if (LCModule == null) {
            String level1Title = "Leadership and Culture";
            String[] level1PageTitles = {
                    "Leadership", "Cultivating Open-Mindedness and Adaptability", "Data Accessibility Further Guidance",
                    "Staff Engagement", "Staff Engagement Further Guidance"
            };
            String level2Title = "Leadership and Culture";
            String[] level2PageTitles = {
                    "Decision Making", "Leadership"
            };

            LCModule = createModuleInfo(level1Title, level1PageTitles, level2Title, level2PageTitles);
        }

        if (NRModule == null) {
            String level1Title = "Networks and Relationships";
            String[] level1PageTitles = {
                    "Effective Partnerships", "Internal Resources", "Internal Resources Further Guidance", "Silos",
                    "Silos Further Guidance", "Leveraging Knowledge", "Leveraging Knowledge Further Guidance",
            };
            String level2Title = "Leadership and Culture";
            String[] level2PageTitles = {
                    "Internal Resources", "Leveraging Knowledge", "Effective Partnerships", "Internal Resources"
            };
            NRModule = createModuleInfo(level1Title, level1PageTitles, level2Title, level2PageTitles);
        }

        if (CRModule == null) {
            String level1Title = "Change Ready";
            String[] level1PageTitles = {
                    "Planning Strategies", "Planning Strategies Financial Further Guidance", "Unity of Purpose", "Proactive Posture"
            };
            String level2Title = "Change Ready";
            String[] level2PageTitles = {
                    "Stress Testing Plans", "Unity of Purpose", "Leadership"
            };
            CRModule = createModuleInfo(level1Title, level1PageTitles, level2Title, level2PageTitles);
        }

        if (ERModule == null) {
            String level1Title = "Employee Resilience";
            String[] level1PageTitles = {
                    "Working Together", "Working Together Further Guidance", "Individual Stress", "Individual Stress Further Guidance",
                    "Understanding"
            };
            String level2Title = "Employee Resilience";
            String[] level2PageTitles = {
                    "Understanding"
            };
            ERModule = createModuleInfo(level1Title, level1PageTitles, level2Title, level2PageTitles);
        }
    }

    /**
     * Create module data
     *
     * @param level1Title the level1 title
     * @param level1PageTitles the level1 page titles
     * @param level2Title the level2 title
     * @param level2PageTitles the level2 page titles
     * @return the module data
     */
    private static ModuleInfo createModuleInfo(String level1Title, String[] level1PageTitles, String level2Title, String[] level2PageTitles) {
        List<LevelInfo> levels = new ArrayList<>();

        if (level1Title != null) {
            levels.add(createLevelInfo(level1Title, level1PageTitles));
        }
        if (level2Title != null) {
            levels.add(createLevelInfo(level2Title, level2PageTitles));
        }
        return new ModuleInfo(levels);
    }

    /**
     * Create the level data
     *
     * @param levelTitle the level title
     * @param pageTitles the page titles
     * @return the level data
     */
    private static LevelInfo createLevelInfo(String levelTitle, String[] pageTitles) {
        List<PageInfo> pages = new ArrayList<>();
        for (String pageTitle : pageTitles) {
            pages.add(new PageInfo(pageTitle, 8, 0));
        }
        return new LevelInfo(levelTitle, pages);
    }

    /**
     * The module info data
     */
    public static class ModuleInfo {
        /**
         * The module is skipped or not
         */
        private boolean skipped = false;

        /**
         * The current level
         */
        private int currentLevel = 1;

        /**
         * The current page
         */
        private int currentPage = 1;

        /**
         * The max seen level
         */
        private int maxSeenLevel = 1;

        /**
         * The max skip step
         */
        private int maxSeenStep = 0;

        /**
         * The levels
         */
        public List<LevelInfo> levels;

        /**
         * Constructor with levels
         *
         * @param levels the levels
         */
        ModuleInfo(List<LevelInfo> levels) {
            this.levels = levels;
        }

        /**
         * Get the level data
         *
         * @param level the level
         * @return the level data
         */
        public LevelInfo getLevel(int level) {
            return this.levels.get(level - 1);
        }

        /**
         * Get the level count
         *
         * @return the level count
         */
        public int getLevelSize() {
            return this.levels.size();
        }

        /**
         * Set page progress
         *
         * @param level the level
         * @param page the page
         * @param progress the progress
         */
        public void setPageProgress(int level, int page, int progress) {
            this.levels.get(level - 1).pages.get(page - 1).progress = progress;
        }

        /**
         * Get page title
         *
         * @param level the level
         * @param page the page
         * @return the page title
         */
        public String getPageTitle(int level, int page) {
            return this.levels.get(level - 1).pages.get(page - 1).title;
        }

        /**
         * Set the skip info
         *
         * @param currentLevel the current level
         * @param currentPage the current page
         */
        public void setSkipInfo(int currentLevel, int currentPage) {
            this.currentLevel = currentLevel;
            this.currentPage = currentPage;
            this.skipped = true;
        }

        /**
         * Get the current level
         * @return the current level
         */
        public int getCurrentLevel() {
            return this.currentLevel;
        }

        /**
         * Get the current page
         *
         * @return the current page
         */
        public int getCurrentPage() {
            return this.currentPage;
        }

        /**
         * Return the skipped flag
         *
         * @return the skipped flag
         */
        public boolean isSkipped() {
            return this.skipped;
        }

        /**
         * Set the skipped flag
         *
         * @param skipped the skipped flag
         */
        public void setSkipped(boolean skipped) {
            this.skipped = skipped;
        }

        /**
         * Set the max skip step
         *
         * @param maxSeenStep the max skip step
         */
        public void setMaxSeenStep(int maxSeenLevel, int maxSeenStep) {
            this.maxSeenStep = maxSeenStep;
            this.maxSeenLevel = maxSeenLevel;
        }

        public boolean hasSeenStep(int level, int step) {
            if (this.maxSeenLevel > level) {
                return true;
            } else if (this.maxSeenLevel == level && this.maxSeenStep > step) {
                return true;
            }

            return false;
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
