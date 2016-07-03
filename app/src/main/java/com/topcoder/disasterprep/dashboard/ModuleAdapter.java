package com.topcoder.disasterprep.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.topcoder.disasterprep.R;

import java.util.List;

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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_MODULE) {
            ModuleView.Module module = modules.get(position);
            holder.title.setText(module.title);
            holder.module.setModuleChildren(module);
        } else {
            holder.title.setText(plan.title);
            holder.module.setPlanChildren(plan);
        }
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ModuleView module;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            module = (ModuleView) itemView.findViewById(R.id.module);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int tag = (int) view.getTag();
                    switch (tag) {
                        case 0:
                            listener.openBCModule();
                            break;
                        default:
                            listener.showNotImplemented();
                    }
                }
            });
        }
    }
}
