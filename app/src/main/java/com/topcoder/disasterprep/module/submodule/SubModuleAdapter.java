/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.module.submodule;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.SimplePagerAdapter;
import com.topcoder.disasterprep.assessment_intro.AssessmentModel;
import com.topcoder.disasterprep.assessment_intro.GradeAnswerSeekView;

/**
 * The adapter for specific module.
 *
 * @author TCSCODER
 * @version 1.0
 */
public class SubModuleAdapter extends SimplePagerAdapter {

    /**
     * The child control listener
     */
    public interface SubModuleControlListener {
        /**
         * Handle the action
         *
         * @param view the child view
         */
        void handleChildControl(View view);
    }

    /**
     * The context
     */
    private Context context;

    /**
     * The listener
     */
    private SubModuleControlListener listener;

    /**
     * The click handler
     */
    private View.OnClickListener clickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listener.handleChildControl(view);
        }
    };

    private CompoundButton.OnCheckedChangeListener checkChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
            listener.handleChildControl(buttonView);
        }
    };

    /**
     * The item selected listener
     */
    private  AdapterView.OnItemSelectedListener itemSelectedListener = new  AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                listener.handleChildControl(view);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
    };

    /**
     * The touch listener
     */
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent event) {
            listener.handleChildControl(view);
            return false;
        }
    };

    /**
     * Constructor
     *
     * @param steps the steps
     * @param context the context
     * @param listener the listener
     */
    public SubModuleAdapter(int[] steps, Context context, SubModuleControlListener listener) {
        super(steps);
        this.context = context;
        this.listener = listener;
    }

    /**
     * Initialize the view for rendering
     *
     * @param container the view container
     * @param position the position
     * @return the view
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View page = (View) super.instantiateItem(container, position);

        // nr text
        View view = page.findViewById(R.id.nr_lv1_s1_challenge_text);
        if (view != null) {
            ((TextView) view).setText(Html.fromHtml(context.getString(R.string.nr_lv1_s1_challenge)));
        }
        view = page.findViewById(R.id.nr_lv2_s4_question);
        if (view != null) {
            ((TextView) view).setText(Html.fromHtml(context.getString(R.string.nr_lv2_s4_question)));
        }

        // seekView
        View seekView = page.findViewById(R.id.seekView);
        if (seekView != null) {
            ((GradeAnswerSeekView) seekView).setGrades(AssessmentModel.getGrades(context));
        }

        traverseUI(page);
        return page;
    }

    /**
     * Traverse the given view
     *
     * @param view the view
     */
    private void traverseUI(final View view) {
        if (view instanceof CheckBox) {
            ((CheckBox) view).setOnCheckedChangeListener(checkChangeListener);
        } else if (view instanceof RadioButton) {
            view.setOnClickListener(clickHandler);
        } else if (view instanceof EditText) {
            ((EditText) view).addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 0) {
                        listener.handleChildControl(view);
                    }
                }
            });
        } else if (view instanceof Spinner) {
            ((Spinner) view).setOnTouchListener(touchListener);
        } else if (view instanceof SeekBar) {
            // do nothing
        } else if (view instanceof Button) {
            view.setOnClickListener(clickHandler);
        } else if (view instanceof TextView) {
            if (((TextView) view).isClickable()) {
                view.setOnClickListener(clickHandler);
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup container = (ViewGroup) view;
            for (int i = 0; i < container.getChildCount(); ++i) {
                View child = container.getChildAt(i);
                traverseUI(child);
            }
        }
    }
}