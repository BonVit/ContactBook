package com.vb.contactbook.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.vb.contactbook.R;
import com.vb.contactbook.mvp.presenter.LoginPresenter;
import com.vb.contactbook.mvp.view.LoginView;
import com.vb.contactbook.utils.SharedPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bonar on 6/13/2017.
 */

public class LoginActivity extends MvpAppCompatActivity implements LoginView,
        View.OnClickListener {
    private static final String TAG = "LoginActivity";

    /*Moxy mvp binding*/
    @InjectPresenter
    LoginPresenter mLoginPresenter;

    /*ButterKnife view binding*/
    @BindView(R.id.googleSignIn)
    SignInButton mGoogleSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mGoogleSignInButton.setOnClickListener(this);

        if(SharedPreferences.getUserId(this) != null) {
            mLoginPresenter.signInWithGoogle();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.googleSignIn:
                mLoginPresenter.signInWithGoogle();
                break;
        }
    }

    @Override
    public void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mLoginPresenter.getGoogleApiClient());
        startActivityForResult(signInIntent, mLoginPresenter.getGoogleSignInRequestCode());
    }

    @Override
    public void login() {
        Intent i = MainActivity.newIntent(this);
        startActivity(i);
    }

    @Override
    public void signInResult(boolean success) {
        if(success) {
            Toast.makeText(this, getResources().getString(R.string.login_success), Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(this, getResources().getString(R.string.google_sign_in_failed), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mLoginPresenter.handleActivityResult(requestCode, resultCode, data);
    }

    public static Intent newIntent(Context context) {
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return i;
    }
}
