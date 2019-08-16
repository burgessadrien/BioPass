package com.burgessadrien.biopass.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.burgessadrien.biopass.R;

import java.util.List;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder> {

    private List<String> notes;

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public NoteViewHolder(View noteView) {
            super(noteView.getRootView());
            this.textView = (TextView) noteView.findViewById(R.id.noteText);
        }

    }

    public NoteRecyclerViewAdapter(List<String> notes) {
        this.notes = notes;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NoteRecyclerViewAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View noteView = inflater.inflate(R.layout.item_note, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(noteView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(NoteViewHolder holder, final int position) {
        holder.textView.setText(notes.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return notes.size();
    }

}
