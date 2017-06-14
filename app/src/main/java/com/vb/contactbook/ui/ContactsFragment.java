package com.vb.contactbook.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vb.contactbook.R;
import com.vb.contactbook.adapter.ContactsAdapter;
import com.vb.contactbook.decoration.SimpleDividerItemDecoration;
import com.vb.contactbook.mvp.model.entity.Contact;
import com.vb.contactbook.mvp.presenter.ContactsPresenter;
import com.vb.contactbook.mvp.view.ContactsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bonar on 6/13/2017.
 */

public class ContactsFragment extends MvpAppCompatFragment implements ContactsView {

    /*Moxy mvp binding*/
    @InjectPresenter
    ContactsPresenter mContactsPresenter;

    /*ButterKnife view binding*/
    @BindView(R.id.contacts_recycler_view)
    RecyclerView mContactsView;

    ContactsAdapter mContactsAdapter;

    OnLogOutCallback mOnLogOutCallback;

    public interface OnLogOutCallback {
        void onLogOut();
    }

    @Override
    public void updateUI() {
        mContactsAdapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacts, container, false);

        ButterKnife.bind(this, v);

        setHasOptionsMenu(true);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);

        actionBar.setTitle(mContactsPresenter.getUserName());

        List<Contact> contacts = mContactsPresenter.getUserContacts();
        mContactsAdapter = new ContactsAdapter(getActivity(), contacts);
        mContactsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mContactsView.setAdapter(mContactsAdapter);
        mContactsView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        mOnLogOutCallback = ((MainActivity) getActivity());

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.fragment_contacts_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_main_menu_log_out:
                mOnLogOutCallback.onLogOut();
                break;
            case R.id.activity_main_menu_sort_asc:
                mContactsPresenter.onSortContacts(true);
                break;
            case R.id.activity_main_menu_sort_desc:
                mContactsPresenter.onSortContacts(false);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
