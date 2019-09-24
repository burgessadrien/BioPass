package com.burgessadrien.biopass.realm;


import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmManager {

    private static Realm mRealm;

    public static Realm open() {
        mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    public static void close() {
        if(mRealm != null) {
            mRealm.close();
        }
    }

    private static void checkForOpenRealm() {
        if (mRealm == null || mRealm.isClosed()) {
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }

    public static void clear() {
        checkForOpenRealm();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

            }
        });
    }

    public static void instantiate(Context context, byte[] key, boolean create) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("biopass.realm")
                .schemaVersion(0) // modify with schema changes
                .encryptionKey(key)
                .build();
        if (create)
            Realm.deleteRealm(config); // delete when ready
        Realm.setDefaultConfiguration(config);
    }
}
