package com.burgessadrien.biopass.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.burgessadrien.biopass.R;
import com.burgessadrien.biopass.realm.RealmManager;
import com.burgessadrien.biopass.utilities.AndroidKeyStoreUtils;
import com.burgessadrien.biopass.utilities.AuthenticationCallback;
import com.burgessadrien.biopass.utilities.EncryptionUtils;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = PrimeActivity.class.getName();
    private static final int REQ_UNLOCK = 1;

    private final AndroidKeyStoreUtils androidKeyStoreUtils = new AndroidKeyStoreUtils(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidKeyStoreUtils.unlockKeyStore(REQ_UNLOCK);

        showBiometricPrompt();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case REQ_UNLOCK:
                if (androidKeyStoreUtils.onUnlockKeyStoreResult(resultCode, data)) {
                    onKeystoreUnlocked();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    private void showBiometricPrompt() {
        //Create a thread pool with a single thread//
        Executor newExecutor = Executors.newSingleThreadExecutor();
        FragmentActivity activity = this;
        //Start listening for authentication events//
        final BiometricPrompt biometricPrompt = new BiometricPrompt(activity, newExecutor, new AuthenticationCallback(TAG));

        //Create the BiometricPrompt instance//
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Identify yourself!")
                .setDescription("Authenticate with biometric sensors")
                .setNegativeButtonText("Exit Application")
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

    private void onKeystoreUnlocked() {
        byte[] encryptedRealmKey = androidKeyStoreUtils.getEncryptedKey();
        try {
            if (encryptedRealmKey == null || !androidKeyStoreUtils.keyStoreContainsKey()) {
                final byte[] realmKey = EncryptionUtils.generateKey();
                androidKeyStoreUtils.generateInKeyStore();
                encryptedRealmKey = androidKeyStoreUtils.encryptAndSaveKey(realmKey);

                RealmManager.instantiate(getApplicationContext(), realmKey, true);
            }

            final byte[] realmKey = androidKeyStoreUtils.decryptKey(encryptedRealmKey);

            RealmManager.instantiate(getApplicationContext(), realmKey, false);
        } catch (NoSuchProviderException | InvalidAlgorithmParameterException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
