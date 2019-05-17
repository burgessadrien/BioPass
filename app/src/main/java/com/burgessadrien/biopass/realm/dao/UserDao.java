package com.burgessadrien.biopass.realm.dao;

import androidx.annotation.NonNull;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import com.burgessadrien.biopass.realm.objects.User;

public class UserDao {

    private Realm mRealm;

    public UserDao(@NonNull Realm realm) {
        mRealm = realm;
    }

    public void save(final User user) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(user);
            }
        });
    }

    public void save(final List<User> userList) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(userList);
            }
        });
    }

    public RealmResults<User> loadAll() {
        return mRealm.where(User.class).findAll().sort("id");
    }
}
