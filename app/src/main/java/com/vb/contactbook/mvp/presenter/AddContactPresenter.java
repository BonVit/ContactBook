package com.vb.contactbook.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vb.contactbook.mvp.view.AddContactView;

/**
 * Created by bonar on 6/13/2017.
 */

@InjectViewState
public class AddContactPresenter extends MvpPresenter<AddContactView> implements AddContactView {

    public AddContactPresenter() {

    }

}
