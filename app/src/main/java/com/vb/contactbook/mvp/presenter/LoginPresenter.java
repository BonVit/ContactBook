package com.vb.contactbook.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.vb.contactbook.App;
import com.vb.contactbook.mvp.model.database.IDatabase;
import com.vb.contactbook.mvp.model.entity.User;
import com.vb.contactbook.mvp.view.LoginView;
import com.vb.contactbook.utils.SharedPreferences;

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

    @Inject
    Context context;

    @Inject
    IDatabase mDaoDatabase;

    @Inject
    GoogleSignInOptions mGoogleSignInOptions;

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
            GoogleSignInAccount signInAccount = googleSignInResult.getSignInAccount();
            User user = new User();
            user.setEmail(signInAccount.getEmail());
            user.setFirstName(signInAccount.getGivenName());
            user.setFamilyName(signInAccount.getFamilyName());
            user.setGoogleId(signInAccount.getId());
            mDaoDatabase.insert(user);

            SharedPreferences.setUserId(context, googleSignInResult.getSignInAccount().getId());
            onLoginSuccess();
        } else {
            //Logged out
            SharedPreferences.setUserId(context, null);
        }
    }

    private void onLoginSuccess() {
        getViewState().login();
    }
}
