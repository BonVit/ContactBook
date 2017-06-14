package com.vb.contactbook.mvp.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.vb.contactbook.App;
import com.vb.contactbook.mvp.view.MainView;
import com.vb.contactbook.ui.AddContactFragment;
import com.vb.contactbook.ui.ContactsFragment;
import com.vb.contactbook.utils.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by bonar on 6/13/2017.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private static final String TAG = "MainPresenter";

    @Inject
    GoogleApiClient mGoogleApiClient;

    @Inject
    Context context;

    public MainPresenter() {
        Log.d(TAG, "constructor");

        App.getAppComponent().inject(this);
        getViewState().addFragment(new ContactsFragment());
    }

    public void logout() {
        Log.d(TAG, "Connected: " + mGoogleApiClient.isConnected());
        SharedPreferences.setUserId(context, null);
        getViewState().startLoginActivity();
    }

    public void onBackFromAddContact() {
        getViewState().replaceFragment(new ContactsFragment());
        getViewState().setFabVisibility(true);
    }

    public void onAddContact() {
        getViewState().replaceFragment(new AddContactFragment());
        getViewState().setFabVisibility(false);
    }

}
