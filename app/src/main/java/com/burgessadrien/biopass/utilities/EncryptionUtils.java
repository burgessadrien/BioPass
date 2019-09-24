package com.burgessadrien.biopass.utilities;

import java.security.SecureRandom;

public class EncryptionUtils {

    public static byte[] generateKey() {
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        return key;
    }
}
