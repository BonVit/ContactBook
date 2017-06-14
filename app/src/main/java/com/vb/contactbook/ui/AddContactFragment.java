package com.vb.contactbook.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.vb.contactbook.R;
import com.vb.contactbook.mvp.model.entity.Contact;
import com.vb.contactbook.mvp.presenter.AddContactPresenter;
import com.vb.contactbook.mvp.view.AddContactView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by bonar on 6/13/2017.
 */

public class AddContactFragment extends MvpAppCompatFragment implements AddContactView,
        View.OnClickListener {

    public interface FragmentCallbacks {
        void onFragmentPopBack();
    }

    /*Moxy mvp binding*/
    @InjectPresenter
    AddContactPresenter mAddContactPresenter;

    /*ButterKnife view binding*/
    @BindView(R.id.firstNameInput)
    EditText mFirstNameInput;
    @BindView(R.id.familyNameInput)
    EditText mFamilyNameInput;
    @BindView(R.id.emailInput)
    EditText mEmailInput;
    @BindView(R.id.phoneInput)
    EditText mPhoneInput;
    @BindView(R.id.buttonSubmit)
    Button mAddContactButton;

    FragmentCallbacks mCallbacks;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSubmit:
                mAddContactPresenter.addContact(getContact());
                break;
        }
    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.setEmail(mEmailInput.getText().toString());
        contact.setFirstName(mFirstNameInput.getText().toString());
        contact.setFamilyName(mFamilyNameInput.getText().toString());
        contact.setPhoneNumber(mPhoneInput.getText().toString());
        return contact;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_contact, container, false);

        ButterKnife.bind(this, v);

        setHasOptionsMenu(true);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.add_contact));

        mCallbacks = (MainActivity) getActivity();

        mAddContactButton.setOnClickListener(this);

        initFormObservables();
        combineLatestEvents();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.add_contact_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mCallbacks.onFragmentPopBack();
                break;
        }

        return true;
    }

    @Override
    public void close() {
        mCallbacks.onFragmentPopBack();
    }


    /**
     * FORM VALIDATION BLOCK
     */
    private DisposableSubscriber<Boolean> mDisposableObserver;
    private Flowable<CharSequence> mEmailChangeObservable;
    private Flowable<CharSequence> mFirstNameChangeObservable;
    private Flowable<CharSequence> mFamilyNameChangeObservable;
    private Flowable<CharSequence> mPhoneChangeObservable;
    private void initFormObservables() {
        mEmailChangeObservable =
                RxTextView.textChanges(mEmailInput).skip(1).toFlowable(BackpressureStrategy.LATEST);
        mFamilyNameChangeObservable =
                RxTextView.textChanges(mFamilyNameInput).skip(1).toFlowable(BackpressureStrategy.LATEST);
        mFirstNameChangeObservable =
                RxTextView.textChanges(mFirstNameInput).skip(1).toFlowable(BackpressureStrategy.LATEST);
        mPhoneChangeObservable =
                RxTextView.textChanges(mPhoneInput).skip(1).toFlowable(BackpressureStrategy.LATEST);
        mDisposableObserver = null;
    }

    private void combineLatestEvents() {
        mDisposableObserver =
                new DisposableSubscriber<Boolean>() {
                    @Override
                    public void onNext(Boolean formValid) {
                        if (formValid) {
                            mAddContactButton.setEnabled(true);
                        } else {
                            mAddContactButton.setEnabled(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                };

        Flowable.combineLatest(
                mEmailChangeObservable,
                mFirstNameChangeObservable,
                mFamilyNameChangeObservable,
                mPhoneChangeObservable,
                (newEmail, newFirstName, newFamilyName, newPhone) -> {
                    boolean isEmailOkay = mAddContactPresenter.isEmailOkay(newEmail.toString());
                    boolean isFirstNameOkay = mAddContactPresenter.isNameOkay(newFirstName.toString());
                    boolean isFamilyNameOkay = mAddContactPresenter.isNameOkay(newFamilyName.toString());
                    boolean isPhoneOkay = mAddContactPresenter.isPhoneOkay(newPhone.toString());

                    if(isEmailOkay) {
                       mEmailInput.setError(null);
                    } else {
                        mEmailInput.setError("");
                    }

                    if(isFirstNameOkay) {
                        mFirstNameInput.setError(null);
                    } else {
                        mFirstNameInput.setError("");
                    }

                    if(isFamilyNameOkay) {
                        mFamilyNameInput.setError(null);
                    } else {
                        mFamilyNameInput.setError("");
                    }

                    if(isPhoneOkay) {
                        mPhoneInput.setError(null);
                    } else {
                        mPhoneInput.setError("");
                    }


                    return isEmailOkay && isFirstNameOkay && isFamilyNameOkay && isPhoneOkay;
                })
                .subscribe(mDisposableObserver);
    }
}
