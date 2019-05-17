package com.burgessadrien.biopass.realm.utils;

import java.security.SecureRandom;

import io.realm.RealmConfiguration;

public class Encryption {

    public static byte[] generateKey() {
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        return key;
    }
}
