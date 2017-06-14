package com.vb.contactbook.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by bonar on 6/13/2017.
 */

public interface LoginView extends MvpView {
    void googleSignIn();
    void login();
    void signInResult(boolean success);
}
