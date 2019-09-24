package com.burgessadrien.biopass.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.burgessadrien.biopass.R;
import com.burgessadrien.biopass.adapters.EntryRecyclerViewAdapter;
import com.burgessadrien.biopass.realm.dao.EntryDao;
import com.burgessadrien.biopass.realm.objects.Entry;
import com.burgessadrien.biopass.viewModels.EntryListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;

public class EntryFragment extends Fragment {

    private Realm realm;
    private EntryDao entryDao;

    private EntryListViewModel entryListViewModel;
    private List<Entry> entryList = Collections.emptyList();

    private RecyclerView recyclerView;
    private EntryRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getActivity());
        realm = Realm.getDefaultInstance();
        entryDao = new EntryDao(realm);

        entryListViewModel = new ViewModelProvider(getActivity()).get(EntryListViewModel.class);
        entryListViewModel.getEntries().observe(getActivity(), groups -> {
            entryList = groups;
            if (mAdapter != null) {
                mAdapter.updateItems(groups);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupFab(view);
        setupRecyclerView(view);
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
        setupAddDialog();
    }

    private void setupAddDialog() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_entry, null);
        EditText entrySite = (EditText) dialogView.findViewById(R.id.dialogEntrySite);
        EditText entryUserName = (EditText) dialogView.findViewById(R.id.dialogEntryUsername);
        EditText entryPassword = (EditText) dialogView.findViewById(R.id.dialogEntryPassword);
        Button entryButton = (Button) dialogView.findViewById(R.id.dialogEntrySubmit);

        entryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addEntry(
                        entrySite,
                        entryUserName,
                        entryPassword
                );
            }
        });

        dialog.setView(dialogView);
        dialog.show();
    }

    private void addEntry(EditText site, EditText username, EditText password) {
        Entry entry = new Entry();
        entry.setSite(site.getText().toString());
        entry.setUsername(username.getText().toString());
        entry.setPassword(password.getText().toString());
        entryDao.save(entry);
    }

    private void setupRecyclerView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.groupList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // this is data for recycler view
        List<Entry> entries = realm.where(Entry.class).findAll();

        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new EntryRecyclerViewAdapter(entries);
        recyclerView.setAdapter(mAdapter);
    }
}