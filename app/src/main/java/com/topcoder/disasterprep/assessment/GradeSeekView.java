package com.topcoder.disasterprep.assessment;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.topcoder.disasterprep.R;

public class GradeSeekView extends FrameLayout {

    private SeekBar mSeekBar;

    public GradeSeekView(Context context) {
        super(context);
        initUI();
    }

    public GradeSeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public GradeSeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    private void initUI() {
        inflate(getContext(), R.layout.view_seek_grade, this);
        final TextView seekText = (TextView) findViewById(R.id.grade_text);
        final int[] colors = new int[]{R.color.grey1, R.color.red1, R.color.orange1, R.color.orange2, R.color.yellow1, R.color.green2, R.color.green1, R.color.blue1};
        final int max = 7;
        mSeekBar = (SeekBar) findViewById(R.id.seek);
        mSeekBar.setMax(max);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekText.setText(String.valueOf(progress));
                seekText.getBackground().setColorFilter(ContextCompat.getColor(getContext(), colors[progress]), PorterDuff.Mode.SRC_ATOP);
                int shiftIco;
                if (0 == progress) {
                    shiftIco = 0;
                } else if (max == progress) {
                    shiftIco = seekText.getWidth();
                } else {
                    shiftIco = seekText.getWidth() / 2;
                }
                float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
                seekText.setX(padding + progress * (seekBar.getWidth() - padding * 2 ) / max - shiftIco);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    Pair<Integer, Integer> getProgress() {
        return new Pair<>(mSeekBar.getProgress(), mSeekBar.getMax());
    }
}
