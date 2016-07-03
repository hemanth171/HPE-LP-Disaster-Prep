package com.topcoder.disasterprep;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SimplePagerAdapter extends PagerAdapter {
    protected int[] views;
    protected String[] titles;

    public SimplePagerAdapter(int[] steps) {
        views = steps;
    }

    public SimplePagerAdapter(int[] steps, String[] titles) {
        views = steps;
        this.titles = titles;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View page = inflater.inflate(views[position], container, false);
        container.addView(page);
        return page;
    }

    @Override
    public int getCount() {
        if (null == views) {
            return 0;
        }
        return views.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (null != titles) {
            return titles[position];
        }
        return "";
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
