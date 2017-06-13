package com.vb.contactbook.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vb.contactbook.mvp.view.MainView;

/**
 * Created by bonar on 6/13/2017.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private static final String TAG = "MainPresenter";

    public MainPresenter() {
        Log.d(TAG, "constructor");

    }

    public void logout() {
        getViewState().startLoginActivity();
    }

}
