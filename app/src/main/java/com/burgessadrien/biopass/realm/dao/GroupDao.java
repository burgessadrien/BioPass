package com.burgessadrien.biopass.realm.dao;

import androidx.annotation.NonNull;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import com.burgessadrien.biopass.realm.objects.Group;

public class GroupDao {

    private Realm mRealm;

    public GroupDao(@NonNull Realm realm) {
        mRealm = realm;
    }

    public void save(final Group group) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(group);
            }
        });
    }

    public void save(final List<Group> groupList) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(groupList);
            }
        });
    }

    public RealmResults<Group> loadAll() {
        return mRealm.where(Group.class).findAll().sort("id");
    }
}
