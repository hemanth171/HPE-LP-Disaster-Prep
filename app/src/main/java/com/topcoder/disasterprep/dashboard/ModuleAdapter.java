/*
 * Copyright (C) 2016 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.disasterprep.dashboard;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.topcoder.disasterprep.R;
import com.topcoder.disasterprep.module.ModuleModel;

import java.util.List;

/**
 * The adapter for the dashboard modules
 *
 * @author TCSCODER
 * @version 1.0
 */
public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {
    private static final int TYPE_MODULE = 0;
    private static final int TYPE_PLAN = 1;
    List<ModuleView.Module> modules;
    ModuleView.Module plan;
    DashboardView listener;

    public ModuleAdapter(List<ModuleView.Module> modules, DashboardView listener) {
        this.modules = modules;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_dashboard_module, parent, false));
    }

    /**
     * Bind the data to the dashboard module
     *
     * @param holder the view holder
     * @param position the position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int moduleType = 0;
        if (getItemViewType(position) == TYPE_MODULE) {
            ModuleView.Module module = modules.get(position);
            moduleType = module.moduleType;

            holder.title.setText(module.title);
            holder.module.setModuleChildren(module);
            holder.continueButton.setBackground(module.buttonBg);
        } else {
            moduleType = plan.moduleType;
            holder.title.setText(plan.title);
            holder.module.setPlanChildren(plan);
            holder.continueButton.setBackground(plan.buttonBg);
        }

        // show continue to button or not
        ModuleModel.ModuleInfo moduleInfo = ModuleModel.getInstance().getModule(moduleType);
        if (moduleInfo != null) {
            if (moduleInfo.isSkipped()) {
                holder.continueButton.setVisibility(View.VISIBLE);
                holder.continueSeprator.setVisibility(View.VISIBLE);
            } else {
                holder.continueButton.setVisibility(View.GONE);
                holder.continueSeprator.setVisibility(View.GONE);
            }
        }

        holder.continueButton.setTag(position);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if (null == plan) {
            return modules.size();
        }
        return modules.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (null == plan || position < modules.size()) {
            return TYPE_MODULE;
        }
        return TYPE_PLAN;
    }

    void addPlan(ModuleView.Module module) {
        plan = module;
    }

    /**
     * The view holder for the modules list
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ModuleView module;

        /**
         * The continue to current level button
         */
        Button continueButton;

        /**
         * The separator above the continue to current level button
         */
        View continueSeprator;


        /**
         * Constructor with the item view
         *
         * @param itemView the item view
         */
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            module = (ModuleView) itemView.findViewById(R.id.module);
            continueButton = (Button) itemView.findViewById(R.id.continue_level);
            continueSeprator = itemView.findViewById(R.id.continue_separator);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int tag = (int) view.getTag();

                    ModuleView.Module moduleData = (ModuleAdapter.this.getItemViewType(tag) == TYPE_MODULE) ? modules.get(tag) : plan;
                    listener.openModule(moduleData.moduleType);
                }
            });

            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int tag = (int) view.getTag();
                    ModuleView.Module moduleData = (ModuleAdapter.this.getItemViewType(tag) == TYPE_MODULE) ? modules.get(tag) : plan;

                    ModuleModel.ModuleInfo moduleInfo = ModuleModel.getInstance().getModule(moduleData.moduleType);
                    listener.continueInModule(moduleData.moduleType, moduleInfo.getCurrentLevel(), moduleInfo.getCurrentPage());
                }
            });
        }
    }
}
