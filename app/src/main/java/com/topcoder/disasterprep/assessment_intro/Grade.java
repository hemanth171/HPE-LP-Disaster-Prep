package com.topcoder.disasterprep.assessment_intro;

import android.graphics.drawable.Drawable;

class Grade {
    private String title;
    private Drawable icon;
    private String hint;
    private Drawable bg;

    String getTitle() {
        return title;
    }

    Drawable getIcon() {
        return icon;
    }

    String getHint() {
        return hint;
    }

    Drawable getBg() {
        return bg;
    }

    Grade(String title, Drawable icon, String hint, Drawable bg) {
        this.title = title;
        this.icon = icon;
        this.hint = hint;
        this.bg = bg;
    }
}
