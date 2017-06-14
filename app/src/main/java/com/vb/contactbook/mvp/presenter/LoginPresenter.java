package com.vb.contactbook.mvp.presenter;

import android.content.Intent;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.vb.contactbook.App;
import com.vb.contactbook.mvp.view.LoginView;

import javax.inject.Inject;

/**
 * Created by bonar on 6/13/2017.
 */

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {
    private static final String TAG = "LoginPresenter";

    private static final int GOOGLE_LOGIN_REQUEST_CODE = 1;

    @Inject
    GoogleApiClient mGoogleApiClient;

    public LoginPresenter() {
        Log.d(TAG, "constructor");

        App.getAppComponent().inject(this);
    }

    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void signInWithGoogle() {
        getViewState().googleSignIn();
    }

    public int getGoogleSignInRequestCode() {
        return GOOGLE_LOGIN_REQUEST_CODE;
    }

    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GOOGLE_LOGIN_REQUEST_CODE:
                GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleGoogleSignInResult(googleSignInResult);
                break;
        }
    }

    private void handleGoogleSignInResult(GoogleSignInResult googleSignInResult) {
        getViewState().signInResult(googleSignInResult.isSuccess());
        if(googleSignInResult.isSuccess()) {
            //Logged in
            getViewState().googleSignIn();
            onLoginSuccess();
        } else {
            //Logged out
            getViewState().googleSignIn();
        }
    }

    private void onLoginSuccess() {
        getViewState().login();
    }
}
