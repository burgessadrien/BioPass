package com.burgessadrien.biopass.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.burgessadrien.biopass.R;
import com.burgessadrien.biopass.activities.GroupAdapter;

import java.util.ArrayList;
import java.util.List;

public class GroupFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.groupList);
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

        mAdapter = new GroupAdapter(groups);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }
}