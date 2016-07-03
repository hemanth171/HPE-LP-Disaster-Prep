package com.topcoder.disasterprep.dashboard;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleModel;

import java.util.ArrayList;
import java.util.List;

class DashboardModel {

    static List<Score> getScores() {
        List<Score> scores =  new ArrayList<>(5);
        scores.add(new Score("Level 1", "28", R.color.blue1));
        scores.add(new Score("Level 2", "25", R.color.green1));
        scores.add(new Score("Level 3", "24", R.color.orange1));
        scores.add(new Score("Level 4", "17", R.color.orange2));
        scores.add(new Score("Level 5", "5", R.color.red1));
        return scores;
    }

    static List<Module> getModules() {
        List<Module> modules = new ArrayList<>(5);
        String[] levels = {"Lvl 1", "Lvl 2", "Lvl 3", "Lvl 4", "Lvl 5"};
        int[] max = {12, 12, 12, 12, 12};
        int[] caps = {R.drawable.icn_cap_1, R.drawable.icn_cap_2, R.drawable.icn_cap_3, R.drawable.icn_cap_4, R.drawable.icn_cap_5};
        modules.add(new Module("Business Continuity Plan", R.drawable.ring_blue_thin, R.drawable.circle_blue1, caps, levels, new int[]{ModuleModel.getInstance().getBCLevelInfo(1).getProgressSum(), ModuleModel.getInstance().getBCLevelInfo(2).getProgressSum(), 0, 0, 0}, new int[]{ModuleModel.getInstance().getBCLevelInfo(1).getMaxSum(), ModuleModel.getInstance().getBCLevelInfo(2).getMaxSum(), 12, 12, 3}));
        modules.add(new Module("Leadership and Culture", R.drawable.ring_green_thin, R.drawable.circle_green, caps, levels, new int[]{12, 12, 12, 3, 0}, max));
        modules.add(new Module("Networks and Relationships", R.drawable.ring_orange1_thin, R.drawable.circle_orange1, caps, levels, new int[]{12, 12, 5, 0, 0}, max));
        modules.add(new Module("Change Ready", R.drawable.ring_orange2_thin, R.drawable.circle_orange2, caps, levels, new int[]{12, 12, 2, 0, 0}, max));
        modules.add(new Module("Employee Resilience", R.drawable.ring_red_thin, R.drawable.circle_red, caps, levels, new int[]{12, 12, 2, 0, 0}, max));
        return modules;
    }

    static Module getPlan() {
        String[] levels = {"Lvl 1", "Lvl 2", "Lvl 3", "Lvl 4", "Lvl 5"};
        int[] max = {12, 12, 12, 12, 12};
        int[] checks = {R.drawable.icn_check_1, R.drawable.icn_check_2, R.drawable.icn_check_3, R.drawable.icn_check_4, R.drawable.icn_check_5};
        return new Module("Certificate and Plan", -1, -1, checks, levels, new int[]{12, 0, 0, 0, 0}, max);
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

    static class Module {
        private String title;
        @DrawableRes
        private int progressRes;
        @DrawableRes
        private int bgRes;
        private int[] iconsRes;
        private String[] levels;
        private int[] progresses;
        private int[] maxPregresses;

        Module(String title, int progressRes, int bgRes, int[] iconsRes, String[] levels, int[] progresses, int[] maxPregresses) {
            this.title = title;
            this.progressRes = progressRes;
            this.bgRes = bgRes;
            this.iconsRes = iconsRes;
            this.levels = levels;
            this.progresses = progresses;
            this.maxPregresses = maxPregresses;
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
