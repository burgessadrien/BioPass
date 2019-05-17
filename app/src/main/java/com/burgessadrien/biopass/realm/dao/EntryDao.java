package com.burgessadrien.biopass.realm.dao;

import androidx.annotation.NonNull;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import com.burgessadrien.biopass.realm.objects.Entry;

public class EntryDao {

    private Realm mRealm;

    public EntryDao(@NonNull Realm realm) {
        mRealm = realm;
    }

    public void save(final Entry entry) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(entry);
            }
        });
    }

    public void save(final List<Entry> entryList) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(entryList);
            }
        });
    }

    public RealmResults<Entry> loadAll() {
        return mRealm.where(Entry.class).findAll().sort("id");
    }
}
