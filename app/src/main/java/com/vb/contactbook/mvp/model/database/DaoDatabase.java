package com.vb.contactbook.mvp.model.database;

import android.content.Context;

import com.vb.contactbook.mvp.model.entity.Contact;
import com.vb.contactbook.mvp.model.entity.ContactDao;
import com.vb.contactbook.mvp.model.entity.DaoMaster;
import com.vb.contactbook.mvp.model.entity.DaoMaster.DevOpenHelper;
import com.vb.contactbook.mvp.model.entity.DaoSession;
import com.vb.contactbook.mvp.model.entity.User;
import com.vb.contactbook.mvp.model.entity.UserDao;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by bonar on 6/13/2017.
 */

public class DaoDatabase implements IDatabase {
    private static final String TAG = "DaoDatabase";

    private static final String DB_NAME = "contact-book-db";

    private DaoSession mDaoSession;

    public DaoDatabase(Context context) {
        Context mContext = context;
        DevOpenHelper helper = new DevOpenHelper(mContext, DB_NAME);
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public User getUserByGoogleId(String id) {
        UserDao userDao = mDaoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder()
                .where(UserDao.Properties.GoogleId.eq(id));

        return qb.uniqueOrThrow();
    }

    @Override
    public List<Contact> getContacts(Long id) {
        ContactDao contactDao = mDaoSession.getContactDao();
        QueryBuilder<Contact> qb = contactDao.queryBuilder()
                ;

        return qb.list();
    }

    public void insert(User user) {
        UserDao userDao = mDaoSession.getUserDao();
        userDao.insertOrReplace(user);
    }

    public void insert(Contact contact) {
        ContactDao contactDao = mDaoSession.getContactDao();
        contactDao.insertOrReplace(contact);
    }

}
