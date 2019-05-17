package com.burgessadrien.biopass;

import android.app.Application;
import android.widget.Toast;

import com.burgessadrien.biopass.realm.objects.User;
import com.burgessadrien.biopass.realm.utils.Encryption;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BioPassApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        byte[] key = Encryption.generateKey(); // change to only do on first open
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("biopass.realm")
                .schemaVersion(0) // modify with schema changes
                .encryptionKey(key)
                .build();
        Realm.deleteRealm(config); // delete when ready
        Realm.setDefaultConfiguration(config);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        User user = realm.createObject(User.class, UUID.randomUUID().toString());
        user.setName("Adrien");
        realm.commitTransaction();
    }
}
