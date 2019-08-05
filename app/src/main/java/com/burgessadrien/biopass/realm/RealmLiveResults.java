package com.burgessadrien.biopass.realm;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmLiveResults<T extends RealmObject> extends LiveData<List<T>> {

    private RealmResults<T> results;

    private final RealmChangeListener<RealmResults<T>> listener = new RealmChangeListener<RealmResults<T>>() {
        @Override
        public void onChange(RealmResults<T> results) {
            setValue(results);
        }
    };

    public void RealmLiveData(RealmResults<T> realmResults) {
        results = realmResults;
    }

    @Override
    protected void onActive() {
        results.addChangeListener(listener);
    }

    @Override
    protected void onInactive() { results.removeChangeListener(listener);}
}
