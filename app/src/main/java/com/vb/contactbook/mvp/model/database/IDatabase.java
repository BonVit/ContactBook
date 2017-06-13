package com.vb.contactbook.mvp.model.database;

import com.vb.contactbook.mvp.model.entity.Contact;
import com.vb.contactbook.mvp.model.entity.User;

/**
 * Created by bonar on 6/13/2017.
 */

public interface IDatabase {
    void insert(User user);
    void insert(Contact contact);
    User getUserByGoogleId(String id);
}
