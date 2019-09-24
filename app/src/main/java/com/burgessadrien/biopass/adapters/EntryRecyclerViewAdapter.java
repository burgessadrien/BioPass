package com.burgessadrien.biopass.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.burgessadrien.biopass.R;
import com.burgessadrien.biopass.realm.objects.Entry;

import java.util.List;

public class EntryRecyclerViewAdapter extends RecyclerView.Adapter<EntryRecyclerViewAdapter.EntryViewHolder> {

    private List<Entry> entries;

    public class EntryViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public EntryViewHolder(View entryView) {
            super(entryView.getRootView());
            this.textView = (TextView) entryView.findViewById(R.id.entryText);
        }

    }

    public EntryRecyclerViewAdapter(List<Entry> entries) {
        this.entries = entries;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EntryRecyclerViewAdapter.EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View entryView = inflater.inflate(R.layout.item_entry, parent, false);
        EntryViewHolder viewHolder = new EntryViewHolder(entryView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EntryViewHolder holder, final int position) {
        holder.textView.setText(entries.get(position).getSite());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void updateItems(List<Entry> entries) {
        this.entries = entries;
        notifyDataSetChanged();
    }

}
