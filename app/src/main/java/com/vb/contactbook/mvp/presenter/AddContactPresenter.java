package com.vb.contactbook.mvp.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vb.contactbook.App;
import com.vb.contactbook.mvp.model.database.IDatabase;
import com.vb.contactbook.mvp.model.entity.Contact;
import com.vb.contactbook.mvp.model.entity.User;
import com.vb.contactbook.mvp.view.AddContactView;
import com.vb.contactbook.utils.SharedPreferences;
import com.vb.contactbook.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by bonar on 6/13/2017.
 */

@InjectViewState
public class AddContactPresenter extends MvpPresenter<AddContactView> {

    @Inject
    IDatabase mDatabase;

    @Inject
    Context context;

    private User user;

    public AddContactPresenter() {
        App.getAppComponent().inject(this);

        user = mDatabase.getUserByGoogleId(SharedPreferences.getUserId(context));
    }

    public void addContact(Contact contact) {
        contact.setUserId(user.getId());
        mDatabase.insert(contact);
        getViewState().close();
    }

    public boolean isEmailOkay(String email) {
        return StringUtils.isMatching(email, StringUtils.EMAIL_PATTERN);
    }

    public boolean isNameOkay(String name) {
        return StringUtils.isMatching(name, StringUtils.NAME_PATTERN);
    }

    public boolean isPhoneOkay(String phone) {
        return StringUtils.isMatching(phone, StringUtils.PHONE_PATTERN);
    }

}
