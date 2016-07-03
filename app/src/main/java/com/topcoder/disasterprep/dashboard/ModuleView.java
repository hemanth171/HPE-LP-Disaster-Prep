package com.topcoder.disasterprep.dashboard;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.topcoder.disasterprep.R;

public class ModuleView extends LinearLayout {

    public ModuleView(Context context) {
        super(context);
        initUI();
    }

    public ModuleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public ModuleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    private void initUI() {
        setOrientation(HORIZONTAL);
    }

    void setModuleChildren(Module module) {
        removeAllViews();
        for (int i = 0, size = module.progress.length; i < size; i++) {
            int progress = module.progress[i];
            int maxProgress = module.maxProgress[i];
            ModuleLevelView level = new ModuleLevelView(getContext(), module.progressActive, maxProgress, progress);
            level.setTitle(module.levels[i], 0 == progress ? module.textColorInactive : module.textColorActive);
            level.updateIco(progress < maxProgress ? module.bgInactive : module.bgActive, module.icons[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            level.setLayoutParams(params);
            addView(level);
        }
    }

    void setPlanChildren(Module module) {
        removeAllViews();
        for (int i = 0, size = module.progress.length; i < size; i++) {
            int progress = module.progress[i];
            int maxProgress = module.maxProgress[i];
            ModuleLevelView level = new ModuleLevelView(getContext());
            level.makePlan();
            level.setTitle(module.levels[i], 0 == progress ? module.textColorInactive : module.textColorActive);
            if (progress == maxProgress) {
                module.icons[i].setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow1), PorterDuff.Mode.SRC_ATOP);
            }
            level.updateIco(null, module.icons[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            level.setLayoutParams(params);
            addView(level);
        }
    }

    static class ModuleLevelView extends FrameLayout {
        private TextView mTitle;
        private ProgressBar mProgressBar;
        private ImageView mProgressIco;

        public ModuleLevelView(Context context) {
            super(context);
            init();
        }

        ModuleLevelView(Context context, int progress, int max, int level) {
            super(context);
            init();
            setProgress(progress, level, max);
        }

        private void init() {
            inflate(getContext(), R.layout.view_dashboard_module_level, this);
            mTitle = (TextView) findViewById(R.id.title);
            mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
            mProgressIco = (ImageView) findViewById(R.id.progress_ico);
        }

        void setTitle(String level, int color) {
            mTitle.setText(level);
            mTitle.setTextColor(color);
        }

        void updateIco(Drawable bg, Drawable ico) {
            mProgressIco.setBackground(bg);
            mProgressIco.setImageDrawable(ico);
        }

        void setProgress(int progress, int level, int max) {
            mProgressBar.setProgressDrawable(ContextCompat.getDrawable(getContext(), level == 0 ? R.drawable.ring_grey_thin : progress));
            mProgressBar.setMax(max);
            mProgressBar.setProgress(level);
            mProgressBar.invalidate();
        }

        void makePlan() {
            mProgressBar.setVisibility(GONE);
            mProgressIco.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            mProgressIco.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
    }

    static class Module {
        final String title;
        @DrawableRes
        final int progressActive;
        final Drawable bgActive;
        final Drawable progressInactive;
        final Drawable bgInactive;
        final Drawable[] icons;
        final String[] levels;
        final int[] progress;
        final int[] maxProgress;
        final int textColorInactive;
        final int textColorActive;

        Module(String title, @DrawableRes int progressActive, Drawable bg, Drawable progressInactive, Drawable bgInactive, Drawable[] icons, String[] levels, int[] progress, int[] maxProgress, int textColorInactive, int textColorActive) {
            this.title = title;
            this.progressActive = progressActive;
            this.bgActive = bg;
            this.progressInactive = progressInactive;
            this.bgInactive = bgInactive;
            this.icons = icons;
            this.levels = levels;
            this.progress = progress;
            this.maxProgress = maxProgress;
            this.textColorInactive = textColorInactive;
            this.textColorActive = textColorActive;
        }
    }
}
