package com.vb.contactbook.mvp.view;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;

/**
 * Created by bonar on 6/13/2017.
 */

public interface MainView extends MvpView {
    void startLoginActivity();
    void addFragment(Fragment fragment);
    void replaceFragment(Fragment fragment);
    void setFabVisibility(boolean visibility);
}
