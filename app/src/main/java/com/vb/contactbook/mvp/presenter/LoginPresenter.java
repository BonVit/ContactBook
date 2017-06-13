package com.vb.contactbook.mvp.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.vb.contactbook.mvp.view.LoginView;
import com.vb.contactbook.utils.GoogleUtils;

/**
 * Created by bonar on 6/13/2017.
 */

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "LoginPresenter";

    private static final int GOOGLE_LOGIN_REQUEST_CODE = 1;

    private GoogleSignInOptions mGoogleSignInOptions;
    private GoogleApiClient mGoogleApiClient;

    private FragmentActivity mFragmentActivity;

    public LoginPresenter() {
        Log.d(TAG, "constructor");
    }

    public void setup(FragmentActivity fragmentActivity) {
        mFragmentActivity = fragmentActivity;
        initGoogleClient();
    }

    private void initGoogleClient() {
        mGoogleSignInOptions = GoogleUtils.getGoogleSignInOptions();

        mGoogleApiClient = GoogleUtils.getGoogleApiClient(mFragmentActivity,
                this,
                mGoogleSignInOptions);
    }

    public void signInWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mFragmentActivity.
                startActivityForResult(signInIntent, GOOGLE_LOGIN_REQUEST_CODE);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        getViewState().googleSignIn(false);
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
        if(googleSignInResult.isSuccess()) {
            //Logged in
            getViewState().googleSignIn(true);
        } else {
            //Logged out
            getViewState().googleSignIn(false);
        }
    }
}
