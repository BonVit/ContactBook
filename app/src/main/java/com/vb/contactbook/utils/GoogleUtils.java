package com.vb.contactbook.utils;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

/**
 * Created by bonar on 6/13/2017.
 */

public class GoogleUtils {
    public static GoogleSignInOptions getGoogleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestId()
                .build();
    }

    public static GoogleApiClient getGoogleApiClient(FragmentActivity fragmentActivity,
                                                     OnConnectionFailedListener onConnectionFailedListener,
                                                     GoogleSignInOptions googleSignInOptions) {
        return new GoogleApiClient.Builder(fragmentActivity)
                .enableAutoManage(fragmentActivity, onConnectionFailedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }
}
