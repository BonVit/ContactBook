package com.vb.contactbook.mvp.model.database;

import android.content.Context;

import com.vb.contactbook.mvp.model.entity.DaoMaster;
import com.vb.contactbook.mvp.model.entity.DaoMaster.DevOpenHelper;
import com.vb.contactbook.mvp.model.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by bonar on 6/13/2017.
 */

public class DaoDatabase implements IDatabase {
        private static final String TAG = "DaoDatabase";

        private static final String DB_NAME = "contact-book-db";

        private Context mContext;
        private DaoSession mDaoSession;

        public DaoDatabase(Context context) {
            mContext = context;
            DevOpenHelper helper = new DevOpenHelper(mContext, DB_NAME);
            Database db = helper.getWritableDb();
            mDaoSession = new DaoMaster(db).newSession();
        }

}
