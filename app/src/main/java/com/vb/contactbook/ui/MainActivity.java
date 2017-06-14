package com.vb.contactbook.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vb.contactbook.R;
import com.vb.contactbook.mvp.presenter.MainPresenter;
import com.vb.contactbook.mvp.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bonar on 6/13/2017.
 */

public class MainActivity extends MvpAppCompatActivity implements MainView,
        View.OnClickListener, AddContactFragment.FragmentCallbacks, ContactsFragment.OnLogOutCallback {
    public static final String TAG = "MainActivity";

    @Override
    public void onFragmentPopBack() {
        mMainPresenter.onBackFromAddContact();
    }

    /*Moxy mvp binding*/
    @InjectPresenter
    MainPresenter mMainPresenter;

    /*ButterKnife view binding*/
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    public static Intent newIntent(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFab.setOnClickListener(this);
    }

    @Override
    public void startLoginActivity() {
        Intent i = LoginActivity.newIntent(this);
        startActivity(i);
    }

    @Override
    public void addFragment(Fragment fragmentToAdd) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content);

        if (fragment == null) {
            fragment = fragmentToAdd;
            fm.beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                mMainPresenter.onAddContact();
                break;
        }
    }

    public void replaceFragment(Fragment fragmentToReplace) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content);

        if (fragment != null) {
            fragment = fragmentToReplace;
            fm.beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit();
        }


    }

    @Override
    public void setFabVisibility(boolean visibility) {
        if(visibility)
            mFab.setVisibility(View.VISIBLE);
        else
            mFab.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {

    }


    @Override
    public void onLogOut() {
        mMainPresenter.logout();
    }
}
