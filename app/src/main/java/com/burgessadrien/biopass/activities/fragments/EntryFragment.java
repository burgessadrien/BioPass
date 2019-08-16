package com.burgessadrien.biopass.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.burgessadrien.biopass.R;
import com.burgessadrien.biopass.adapters.EntryRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class EntryFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_entry, container, false);

        setupFab(rootView);
        setupRecyclerView(rootView);

        return rootView;
    }

    private void setupFab(View rootView) {
        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleFab(view);
            }
        });
    }

    private void handleFab(View view) {
        Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void setupAddDialog() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_entry, null);
        TextView groupTitle = (TextView) dialogView.findViewById(R.id.dialogGroupTitle);
        EditText groupName = (EditText) dialogView.findViewById(R.id.dialogGroupName);
        Button groupButton = (Button) dialogView.findViewById(R.id.dialogGroupSubmit);
        groupTitle.setText("Add Group");

        groupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });

        dialog.setView(dialogView);
        dialog.show();
    }

    private void setupRecyclerView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.entryList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // this is data fro recycler view
        String groupArray[] = {
                "banana",
                "apple",
                "pear",
                "fhjdskhfks",
                "dsfsfs"
        };

        List<String> groups = new ArrayList<String>();
        for(String group : groupArray)
            groups.add(group);

        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new EntryRecyclerViewAdapter(groups);
        recyclerView.setAdapter(mAdapter);
    }
}