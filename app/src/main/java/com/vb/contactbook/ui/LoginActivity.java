package com.vb.contactbook.ui;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vb.contactbook.R;
import com.vb.contactbook.mvp.presenter.LoginPresenter;
import com.vb.contactbook.mvp.view.LoginView;

/**
 * Created by bonar on 6/13/2017.
 */

public class LoginActivity extends MvpAppCompatActivity implements LoginView {
    private static final String TAG = "LoginActivity";

    /*Moxy mvp binding*/
    @InjectPresenter
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
    }
}
