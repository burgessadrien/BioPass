package com.burgessadrien.biopass;

import android.app.Application;
import android.widget.Toast;

import com.burgessadrien.biopass.realm.utils.Encryption;

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
                .schemaVersion(0)
                .encryptionKey(key)
                .build();
        Realm.setDefaultConfiguration(config);
        Toast.makeText(getApplicationContext(), "Application has started!", Toast.LENGTH_LONG).show();
    }
}
