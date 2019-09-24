package com.burgessadrien.biopass.utilities;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;

public class AuthenticationCallback extends BiometricPrompt.AuthenticationCallback {

    private String TAG;

    public AuthenticationCallback(String TAG) {
        this.TAG = TAG;
    }

    @Override

    //onAuthenticationError is called when a fatal error occurrs//

    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
        if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
            System.exit(0);
        } else {
            super.onAuthenticationError(errorCode, errString);
            //Print a message to Logcat//
            Log.d(TAG, "An unrecoverable error occurred");
        }
    }

    //onAuthenticationSucceeded is called when a fingerprint is matched successfully//

    @Override
    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);

        //Print a message to Logcat//
        Log.d(TAG, "Fingerprint recognised successfully");
    }

    //onAuthenticationFailed is called when the fingerprint doesn’t match//

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();

        //Print a message to Logcat//
        Log.d(TAG, "Fingerprint not recognised");
    }
}
