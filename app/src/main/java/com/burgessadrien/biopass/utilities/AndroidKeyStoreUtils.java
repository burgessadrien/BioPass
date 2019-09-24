package com.burgessadrien.biopass.utilities;

import android.app.KeyguardManager;
import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class AndroidKeyStoreUtils {

    private final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private final String KEY_ALIAS = "realmEncryptonKey";
    private final int AUTH_VALID_DURATION_IN_SECOND = 30;
    private final String KEY_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES;
    private final ByteOrder ORDER_FOR_ENCRYPTED_DATA = ByteOrder.BIG_ENDIAN;

    private final KeyStore keyStore = getKeyStore();
    private final Cipher cipher = getCipher();
    private final Context context;

    public AndroidKeyStoreUtils(Context context) {
        this.context = context;
    }

    public void generateInKey() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM, ANDROID_KEY_STORE);

        final KeyGenParameterSpec keySpec = new KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .setUserAuthenticationRequired(true)
                .setUserAuthenticationValidityDurationSeconds(AUTH_VALID_DURATION_IN_SECOND)
                .build();

        keyGenerator.init(keySpec);
        keyGenerator.generateKey();
    }

    public byte[] encryptAndSaveKey(byte[] key) {
        final byte[] iv;
        final byte[] encryptedKeyForRealm;
        try {
            final SecretKey secretKey= (SecretKey) keyStore.getKey(KEY_ALIAS, null);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            encryptedKeyForRealm = cipher.doFinal(key);
            iv = cipher.getIV();
        } catch (InvalidKeyException | UnrecoverableKeyException | NoSuchAlgorithmException
                | KeyStoreException | BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException("key for encryption is invalid", e);
        }
        final byte[] ivAndEncryptedKey = new byte[Integer.BYTES + iv.length + encryptedKeyForRealm.length];

        final ByteBuffer buffer = ByteBuffer.wrap(ivAndEncryptedKey);
        buffer.order(ORDER_FOR_ENCRYPTED_DATA);
        buffer.putInt(iv.length);
        buffer.put(iv);
        buffer.put(encryptedKeyForRealm);

        //SharedPrefUtils.save(context, ivAndEncryptedKey);

        return ivAndEncryptedKey;
    }

    public byte[] decryptKey(byte[] ivAndEncryptedKey) {
        final Cipher cipher = getCipher();
        final KeyStore keyStore = getKeyStore();

        final ByteBuffer buffer = ByteBuffer.wrap(ivAndEncryptedKey);
        buffer.order(ORDER_FOR_ENCRYPTED_DATA);

        final int ivLength = buffer.getInt();
        final byte[] iv = new byte[ivLength];
        final byte[] encryptedKey = new byte[ivAndEncryptedKey.length - Integer.SIZE - ivLength];

        buffer.get(iv);
        buffer.get(encryptedKey);

        try {
            final SecretKey key = (SecretKey) keyStore.getKey(KEY_ALIAS, null);
            final IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            return cipher.doFinal(encryptedKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("key is invalid.");
        } catch (UnrecoverableKeyException | NoSuchAlgorithmException | BadPaddingException
                | KeyStoreException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    private KeyStore getKeyStore(){
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
            keyStore.load(null);
            return keyStore;
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean keyStoreContainsKey() throws  KeyStoreException{
        return getKeyStore().containsAlias(KEY_ALIAS);
    }

    private Cipher getCipher() {
        final Cipher cipher;
        try {
            cipher = Cipher.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        return cipher;
    }

    private KeyguardManager getKeyguardManager() {
        return (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
    }
}

