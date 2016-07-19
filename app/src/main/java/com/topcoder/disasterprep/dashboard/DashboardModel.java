/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.dashboard;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The model for the dashboard activity
 *
 * @author TCSCODER
 * @version 1.0
 */
public class DashboardModel {

    /**
     * The business continuity plan module type
     */
    public static final int BC_MODULE_TYPE = 1;

    /**
     * The leadership and culture module type
     */
    public static final int LC_MODULE_TYPE = 2;

    /**
     * The networks and relationships module type
     */
    public static final int NR_MODULE_TYPE = 3;

    /**
     * The change ready module type
     */
    public static final int CR_MODULE_TYPE = 4;

    /**
     * The employee resilience module type
     */
    public static final int ER_MODULE_TYPE = 5;

    /**
     * The certificate and plan module type
     */
    public static final int CP_MODULE_TYPE = 6;

    /**
     * Get the scores data
     *
     * @return the scores data
     */
    static List<Score> getScores() {
        List<Score> scores =  new ArrayList<>(5);
        scores.add(new Score("Level 1", "28", R.color.blue1));
        scores.add(new Score("Level 2", "25", R.color.green1));
        scores.add(new Score("Level 3", "24", R.color.yellow1));
        scores.add(new Score("Level 4", "17", R.color.orange1));
        scores.add(new Score("Level 5", "5", R.color.red1));
        return scores;
    }

    /**
     * Get the modules data
     *
     * @return the modules data
     */
    static List<Module> getModules() {
        List<Module> modules = new ArrayList<>(5);
        String[] levels = {"Lvl 1", "Lvl 2", "Lvl 3", "Lvl 4", "Lvl 5"};
        int[] max = {12, 12, 12, 12, 12};
        int[] caps = {R.drawable.icn_cap_1, R.drawable.icn_cap_2, R.drawable.icn_cap_3, R.drawable.icn_cap_4, R.drawable.icn_cap_5};
        modules.add(new Module(BC_MODULE_TYPE, "Business Continuity Plan", R.drawable.ring_blue_thin, R.drawable.circle_blue1, R.drawable.button_bg1, caps, levels));
        modules.add(new Module(LC_MODULE_TYPE, "Leadership and Culture", R.drawable.ring_green_thin, R.drawable.circle_green, R.drawable.button_green_bg, caps, levels));
        modules.add(new Module(NR_MODULE_TYPE, "Networks and Relationships", R.drawable.ring_yellow_thin, R.drawable.circle_yellow, R.drawable.button_yellow_bg, caps, levels));
        modules.add(new Module(CR_MODULE_TYPE, "Change Ready", R.drawable.ring_orange1_thin, R.drawable.circle_orange1, R.drawable.button_orange_bg, caps, levels));
        modules.add(new Module(ER_MODULE_TYPE, "Employee Resilience", R.drawable.ring_red_thin, R.drawable.circle_red, R.drawable.button_red_bg, caps, levels));
        return modules;
    }

    /**
     * Get the plan data
     *
     * @return the plan data
     */
    static Module getPlan() {
        String[] levels = {"Lvl 1", "Lvl 2", "Lvl 3", "Lvl 4", "Lvl 5"};
        int[] max = {12, 12, 12, 12, 12};
        int[] checks = {R.drawable.icn_check_1, R.drawable.icn_check_2, R.drawable.icn_check_3, R.drawable.icn_check_4, R.drawable.icn_check_5};
        return new Module(CP_MODULE_TYPE, "Certificate and Plan", -1, -1, R.drawable.button_yellow_bg, checks, levels);
    }

    static class Score {
        private String title;
        private String score;
        @ColorRes
        private int color;

        Score(String title, String score, @ColorRes int color) {
            this.title = title;
            this.score = score;
            this.color = color;
        }

        String getTitle() {
            return title;
        }

        String getScore() {
            return score;
        }

        int getColor() {
            return color;
        }
    }

    /**
     * The module data
     */
    static class Module {
        /**
         * The module type
         */
        private int moduleType;
        private String title;

        /**
         * The continue to current level button background drawable resource
         */
        @DrawableRes
        private int buttonBgRes;

        @DrawableRes
        private int progressRes;
        @DrawableRes
        private int bgRes;
        private int[] iconsRes;
        private String[] levels;
        private int[] progresses;
        private int[] maxPregresses;

        /**
         * Constructor
         *
         * @param moduleType the module type
         * @param title the module title
         * @param progressRes the progress resources
         * @param bgRes the background resource
         * @param buttonBgRes the button background resource
         * @param iconsRes the icon resources
         * @param levels the levels
         */
        Module(int moduleType, String title, int progressRes, int bgRes, int buttonBgRes, int[] iconsRes, String[] levels) {
            this.moduleType = moduleType;
            this.title = title;
            this.progressRes = progressRes;
            this.buttonBgRes = buttonBgRes;
            this.bgRes = bgRes;
            this.iconsRes = iconsRes;
            this.levels = levels;

            this.progresses = new int[levels.length];
            this.maxPregresses = new int[levels.length];
            Arrays.fill(maxPregresses, 12);

            ModuleModel.ModuleInfo moduleInfo = ModuleModel.getInstance().getModule(this.moduleType);
            if (moduleInfo != null) {
                for (int i = 1; i <= moduleInfo.getLevelSize(); ++i) {
                    this.progresses[i - 1] = moduleInfo.getLevel(i).getProgressSum();
                    this.maxPregresses[i - 1] = moduleInfo.getLevel(i).getMaxSum();
                }
            }

            if (this.moduleType == CP_MODULE_TYPE) {
                this.progresses[0] = this.maxPregresses[0];
            }
        }

        /**
         * Get the module type
         *
         * @return the module type
         */
        int getModuleType() {
            return this.moduleType;
        }

        String getTitle() {
            return title;
        }

        int getProgressRes() {
            return progressRes;
        }

        int getBgRes() {
            return bgRes;
        }

        /**
         * Get the continue to current level button background drawable resource
         *
         * @return the continue to current level button background drawable resource
         */
        int getButtonBgRes() { return buttonBgRes; }

        int[] getIconsRes() {
            return iconsRes;
        }

        String[] getLevels() {
            return levels;
        }

        int[] getProgresses() {
            return progresses;
        }

        int[] getMaxProgresses() {
            return maxPregresses;
        }
    }
}
