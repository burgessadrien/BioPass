package com.burgessadrien.biopass.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.burgessadrien.biopass.R;
import com.burgessadrien.biopass.adapters.GroupRecyclerViewAdapter;
import com.burgessadrien.biopass.realm.dao.GroupDao;
import com.burgessadrien.biopass.realm.objects.Group;
import com.burgessadrien.biopass.viewModels.GroupListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

import io.realm.Realm;

import static io.realm.internal.SyncObjectServerFacade.getApplicationContext;

public class GroupFragment extends Fragment {

    private Realm realm;
    private GroupDao groupDao;

    private GroupListViewModel groupListViewModel;
    private List<Group> groupList = Collections.emptyList();

    private RecyclerView recyclerView;
    private GroupRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getActivity());
        realm = Realm.getDefaultInstance();
        groupDao = new GroupDao(realm);

        groupListViewModel = new ViewModelProvider(getActivity()).get(GroupListViewModel.class);
        groupListViewModel.getGroups().observe(getActivity(), groups -> {
            groupList = groups;
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

    private void setupFab(View view) {
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleFab(v);
            }
        });
    }

    private void handleFab(View view) {
        setupAddDialog();
    }

    private void setupAddDialog() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_group, null);
        TextView groupTitle = (TextView) dialogView.findViewById(R.id.dialogGroupTitle);
        EditText groupName = (EditText) dialogView.findViewById(R.id.dialogGroupName);
        Button groupButton = (Button) dialogView.findViewById(R.id.dialogGroupSubmit);
        groupTitle.setText("Add Group");

        groupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addGroup(groupName);
            }
        });

        dialog.setView(dialogView);
        dialog.show();
    }

    private void addGroup(EditText groupName) {
        Group group = new Group();
        group.setName(groupName.getText().toString());
        groupDao.save(group);
    }

    private void setupRecyclerView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.groupList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // this is data for recycler view
        List<Group> groups = realm.where(Group.class).findAll();

        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new GroupRecyclerViewAdapter(groups);
        recyclerView.setAdapter(mAdapter);
    }
}