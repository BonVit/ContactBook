package com.vb.contactbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vb.contactbook.R;
import com.vb.contactbook.mvp.model.entity.Contact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bonar on 6/14/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {
    private List<Contact> mContacts;

    private Context mContext;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        mContacts = contacts;
        mContext = context;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater
                .inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = mContacts.get(position);
        holder.bindContact(contact);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private Contact mContact;

        /*ButterKnife view binding*/
        @BindView(R.id.contact_name)
        TextView mName;
        @BindView(R.id.contact_email)
        TextView mEmail;
        @BindView(R.id.contact_phone)
        TextView mPhone;

        public ContactViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bindContact(Contact contact) {
            mContact = contact;
            mName.setText(mContact.getFirstName() + " " + mContact.getFamilyName());
            mEmail.setText(mContact.getEmail());
            mPhone.setText(mContact.getPhoneNumber());
        }
    }
}


