package com.burgessadrien.biopass.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.burgessadrien.biopass.R;
import com.burgessadrien.biopass.activities.fragments.EntryFragment;
import com.burgessadrien.biopass.activities.fragments.GroupFragment;
import com.burgessadrien.biopass.activities.fragments.NoteFragment;
import com.google.android.material.tabs.TabLayout;

public class PrimeActivity extends AppCompatActivity {

    private TabPagerAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createTabPagerAdapter();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void createTabPagerAdapter() {
        adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GroupFragment(), "Groups");
        adapter.addFragment(new EntryFragment(), "Entries");
        adapter.addFragment(new NoteFragment(), "Notes");
    }

}
