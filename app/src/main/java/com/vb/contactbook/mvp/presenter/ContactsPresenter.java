package com.vb.contactbook.mvp.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vb.contactbook.App;
import com.vb.contactbook.mvp.model.database.IDatabase;
import com.vb.contactbook.mvp.model.entity.Contact;
import com.vb.contactbook.mvp.model.entity.User;
import com.vb.contactbook.mvp.view.ContactsView;
import com.vb.contactbook.utils.SharedPreferences;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by bonar on 6/13/2017.
 */

@InjectViewState
public class ContactsPresenter extends MvpPresenter<ContactsView> {

    private User user;
    private List<Contact> contacts;

    @Inject
    IDatabase mDatabase;

    @Inject
    Context mContext;

    public ContactsPresenter() {
        App.getAppComponent().inject(this);

        user = mDatabase.getUserByGoogleId(SharedPreferences.getUserId(mContext));
        contacts = mDatabase.getContacts(user.getId());
    }

    public List<Contact> getUserContacts() {
        return contacts;
    }

    public String getUserName() {
        return user.getFirstName() + " " + user.getFamilyName();
    }

    public void onSortContacts(boolean asc) {
        if (contacts.size() > 0) {
            Collections.sort(contacts, new Comparator<Contact>() {
                @Override
                public int compare(final Contact object1, final Contact object2) {
                    String name1 = object1.getFirstName() + " " + object1.getFamilyName();
                    String name2 = object2.getFirstName() + " " + object2.getFamilyName();
                    if(asc)
                        return name1.compareTo(name2);
                    else
                        return name2.compareTo(name1);
                }
            });
        }

        getViewState().updateUI();
    }



}
