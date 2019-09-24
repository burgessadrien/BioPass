package com.burgessadrien.biopass.realm.dao;

import androidx.annotation.NonNull;

import java.util.List;

import io.realm.Realm;

import com.burgessadrien.biopass.realm.objects.Group;
import com.burgessadrien.biopass.realm.liveRealm.LiveRealmResults;

public class GroupDao {

    private Realm mRealm;

    public GroupDao(@NonNull Realm realm) {
        mRealm = realm;
    }

    public void save(Group group) {
        updateId(group);
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(group);
            }
        });
    }

    public void save(final List<Group> groupList) {
        groupList.stream().forEach(this::updateId);
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(groupList);
            }
        });
    }

    public LiveRealmResults<Group> loadAll() {
        return new LiveRealmResults<>(mRealm.where(Group.class)
                .findAllAsync()
                .sort("id")
        );
    }

    private void updateId(Group group) {
        group.setId(getNextId());
    }

    private Long getNextId() {
        Number id = mRealm.where(Group.class).max("id");
        return id != null ? id.longValue() + 1 : 1;
    }
}
