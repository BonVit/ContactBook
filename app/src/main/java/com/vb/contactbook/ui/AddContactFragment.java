package com.vb.contactbook.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vb.contactbook.R;
import com.vb.contactbook.mvp.presenter.AddContactPresenter;
import com.vb.contactbook.mvp.view.AddContactView;

/**
 * Created by bonar on 6/13/2017.
 */

public class AddContactFragment extends MvpAppCompatFragment implements AddContactView {

    /*Moxy mvp binding*/
    @InjectPresenter
    AddContactPresenter mAddContactPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_contact, container, false);

        return v;
    }
}