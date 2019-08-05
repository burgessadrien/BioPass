package com.burgessadrien.biopass.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.burgessadrien.biopass.R;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private List<String> groups;

    public class GroupViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public GroupViewHolder(View groupView) {
            super(groupView.getRootView());
            this.textView = (TextView) groupView.findViewById(R.id.groupText);
        }

    }

    public GroupAdapter(List<String> groups) {
        this.groups = groups;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GroupAdapter.GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View groupView = inflater.inflate(R.layout.item_group, parent, false);
        GroupViewHolder viewHolder = new GroupViewHolder(groupView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(GroupViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(groups.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return groups.size();
    }

}
