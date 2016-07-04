package com.topcoder.disasterprep.module.bc;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.SimplePagerAdapter;
import com.topcoder.disasterprep.module.ModuleModel;

class BCAdapter extends SimplePagerAdapter {
    private int level;
    private Question mQuestion;

    public BCAdapter(int level, int[] steps) {
        super(steps);
        this.level = level;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View page = (View) super.instantiateItem(container, position);
        TextView title = (TextView) page.findViewById(R.id.page_title);
        title.setText(ModuleModel.getInstance().getBCPageTitle(level, position + 1));
        if (null != mQuestion && mQuestion.getPosition() == position) {
            for (int i = 0; i < mQuestion.getIds().length; i++) {
                page.findViewById(mQuestion.getIds()[i]).setOnClickListener(mQuestion.getListener());
            }
        }
        return page;
    }

    void setListener(int position, View.OnClickListener orOptionListener, int[] ids) {
        if (null != ids && null != orOptionListener) {
            mQuestion = new Question(position, orOptionListener, ids);
        }
    }

    private static class Question {
        private int position;
        private View.OnClickListener listener;
        private int[] ids;

        private Question(int position, View.OnClickListener listener, int[] ids) {
            this.position = position;
            this.listener = listener;
            this.ids = ids;
        }

        private int getPosition() {
            return position;
        }

        private View.OnClickListener getListener() {
            return listener;
        }

        private int[] getIds() {
            return ids;
        }
    }
}
