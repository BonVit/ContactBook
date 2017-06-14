package com.vb.contactbook.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bonar on 6/14/2017.
 */

@Module
public class GoogleModule {
    @Provides
    @NonNull
    @Singleton
    GoogleSignInOptions provideGoogleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestId()
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    GoogleApiClient provideGoogleApiClient(Context context,
                                           GoogleSignInOptions googleSignInOptions) {
        return new GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

}
