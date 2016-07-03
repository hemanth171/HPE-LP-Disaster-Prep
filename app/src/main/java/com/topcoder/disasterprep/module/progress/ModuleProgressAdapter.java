package com.topcoder.disasterprep.module.progress;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleModel;

import java.util.List;

class ModuleProgressAdapter extends RecyclerView.Adapter<ModuleProgressAdapter.ViewHolder> {
    final List<ModuleModel.PageInfo> pages;
    private final Drawable mGreyTick;
    private final Drawable mGreenTick;

    public ModuleProgressAdapter(List<ModuleModel.PageInfo> pages, Context context) {
        mGreyTick = ContextCompat.getDrawable(context, R.drawable.icn_progress_check);
        mGreyTick.setColorFilter(ContextCompat.getColor(context, R.color.score_inactive), PorterDuff.Mode.SRC_ATOP);
        mGreenTick = ContextCompat.getDrawable(context, R.drawable.icn_progress_check_green);
        mGreenTick.setColorFilter(ContextCompat.getColor(context, R.color.green1), PorterDuff.Mode.SRC_ATOP);
        this.pages = pages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_module_progress, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ModuleModel.PageInfo page = pages.get(position);
        Context context = holder.itemView.getContext();
        holder.title.setText(page.title);
        int txtColor = ContextCompat.getColor(context, 0 == page.progress ? R.color.score_inactive : R.color.blue1);
        holder.title.setTextColor(txtColor);
        holder.icon.setImageDrawable(page.max == page.progress ? mGreenTick : mGreyTick);
        if (page.progress == 0) {
            holder.progress.setVisibility(View.INVISIBLE);
        } else {
            holder.progress.setMax(page.max);
            holder.progress.setProgress(page.progress);
            holder.progress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;
        ProgressBar progress;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.check);
            progress = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        }
    }
}
